package com.wma.tools;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.haha.perflib.Main;
import com.wma.tools.model.IAllApi;
import com.wma.tools.model.weather.CityModel;
import com.wma.tools.model.weather.WidsModel;
import com.wma.tools.utils.Common;
import com.wma.tools.utils.SPUtils;
import com.wma.wmalib.callback.HttpCallBack;
import com.wma.wmalib.http.HttpUtils;
import com.wma.wmalib.utils.FileUtils;
import com.wma.wmalib.utils.LocationUtils;
import com.wma.wmalib.utils.PermissionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class WelComeActivity extends Activity implements PermissionUtils.PermissionCallbacks {

    static String TAG = "WMA-WMA";
    static Context mContext = ToolApplication.getInstance();

    private static final int GET_DATA = 0;


    private static LocationManager mLocationManager;

    private static final int REQUEST_PERMISSION_CODE = 12;

    private static String[] permissions = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };

    public static Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case GET_DATA://获取数据
                    getWids();
                    getCity();
                    break;
            }
            return false;
        }
    });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        HttpUtils.httpUtils.init(IAllApi.WEATHER_HOST);
        handler.sendEmptyMessage(GET_DATA);
//        getLocation();
        goMain();

    }

    private void goMain() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        }).start();
    }

    private static void getCity() {
        if (SPUtils.getIsLoadCity()) {

        } else {
            new CityModel().getDatas(new HttpCallBack<CityModel>() {
                @Override
                public void onBegin() {

                }

                @Override
                public void onComplete() {

                }

                @Override
                public void onSuccess(CityModel cityModel) {
                    handleCity(cityModel);
                }

                @Override
                public void onFail(String e) {

                }

                @Override
                public void onError(Throwable e) {

                }
            });
        }
    }

    private static void getWids() {
        if (SPUtils.getIsLoadWid()) {
            String read = FileUtils.read(mContext, Common.WIDS);
            Common.WID_MAP = new Gson().fromJson(read, new TypeToken<HashMap<String, String>>() {
            }.getType());
        } else {
            new WidsModel().getDatas(new HttpCallBack<WidsModel>() {
                @Override
                public void onBegin() {

                }

                @Override
                public void onComplete() {

                }

                @Override
                public void onSuccess(WidsModel widsModel) {
                    List<WidsModel.ResultBean> result = widsModel.getResult();
                    HashMap<String, String> map = new HashMap<>();
                    for (int i = 0; i < result.size(); i++) {
                        String weather = result.get(i).getWeather();
                        String wid = result.get(i).getWid();
                        map.put(wid, weather);
                    }
                    Common.WID_MAP = map;
                    String s = new Gson().toJson(map);
                    FileUtils.write(mContext, Common.WIDS, s);
                    SPUtils.setIsLoadWid(true);
                }

                @Override
                public void onFail(String e) {
                    Log.d(TAG, "onFail: " + e);
                }

                @Override
                public void onError(Throwable e) {
                    Log.d(TAG, "onError: " + e);
                }
            });
        }
    }

    private static void handleCity(final CityModel cityModel) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                long l = System.currentTimeMillis();
                List<CityModel.ResultBean> result = cityModel.getResult();
                HashSet<String> set = new HashSet();
                for (int i = 0; i < result.size(); i++) {
                    CityModel.ResultBean city = result.get(i);
                    String province = city.getProvince();
                    set.add(province);
                }
                Object[] objects = set.toArray();
                for (int i = 0; i < objects.length; i++) {
                    String province = (String) objects[i];
                    ArrayList<CityModel.ResultBean> list = new ArrayList<>();
                    for (int j = 0; j < result.size(); j++) {
                        if (province.equals(result.get(j).getProvince())) {
                            list.add(result.get(j));
                        }
                        if (j == result.size() - 1) {
                            CityModel model = new CityModel();
                            model.setResult(list);
                            FileUtils.write(mContext, province, new Gson().toJson(model));
                        }

                    }
                }

                SPUtils.setIsLoadCity(true);
            }
        }).start();

    }


    /**
     * 获取位置信息
     */
    private void getLocation() {

        if (!PermissionUtils.hasPermissions(this, permissions)) {
            PermissionUtils.requestPermissions(this, REQUEST_PERMISSION_CODE, permissions);
        } else {
            startLocate();
        }
    }

    /**
     * 开始定位
     */
    private void startLocate() {
        mLocationManager = (LocationManager) ToolApplication.getInstance().getSystemService(Context.LOCATION_SERVICE);
        boolean providerEnabled = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        Log.d("WMA-WMA", "startLocate: providerEnabled = " + providerEnabled);
        if (providerEnabled) { //GPS已开启

            Criteria criteria = new Criteria();
            criteria.setAccuracy(Criteria.ACCURACY_FINE);//高精度
            criteria.setAltitudeRequired(false);//无海拔要求   criteria.setBearingRequired(false);//无方位要求
            criteria.setCostAllowed(true);//允许产生资费   criteria.setPowerRequirement(Criteria.POWER_LOW);//低功耗

// 获取最佳服务对象
            String provider = mLocationManager.getBestProvider(criteria,true);
            /**
             * 绑定监听
             * 参数1，设备：有GPS_PROVIDER和NETWORK_PROVIDER两种，前者是GPS,后者是GPRS以及WIFI定位
             * 参数2，位置信息更新周期.单位是毫秒
             * 参数3，位置变化最小距离：当位置距离变化超过此值时，将更新位置信息
             * 参数4，监听
             * 备注：参数2和3，如果参数3不为0，则以参数3为准；参数3为0，则通过时间来定时更新；两者为0，则随时刷新
             */
            mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        } else {
            Toast.makeText(this, "请打开GPS", Toast.LENGTH_SHORT).show();
        }
    }



    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            //位置信息变化时触发
            Log.d("WMA-WMA", "定位方式：" + location.getProvider());
            Log.d("WMA-WMA", "纬度：" + location.getLatitude());
            Log.d("WMA-WMA", "经度：" + location.getLongitude());
            Log.d("WMA-WMA", "海拔：" + location.getAltitude());
            Log.d("WMA-WMA", "时间：" + location.getTime());


            Log.d("WMA-WMA", "国家: " + LocationUtils.getCountryName(ToolApplication.getInstance(),location.getLatitude(),location.getLongitude()));
            Log.d("WMA-WMA", "地理位置: "+ LocationUtils.getAddress(ToolApplication.getInstance(),location.getLatitude(),location.getLongitude()));
            Log.d("WMA-WMA", "所在地: " + LocationUtils.getLocality(ToolApplication.getInstance(),location.getLatitude(),location.getLongitude()));
            Log.d("WMA-WMA", "街道: " + LocationUtils.getStreet(ToolApplication.getInstance(),location.getLatitude(),location.getLongitude()));
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.d("WMA-WMA", "onStatusChanged: ");
            //GPS状态变化时触发
            switch (status) {
                case LocationProvider.AVAILABLE:
                    Log.d("WMA-WMA", "onStatusChanged: 当前GPS状态为可见状态");
                    break;
                case LocationProvider.OUT_OF_SERVICE:
                    Log.d("WMA-WMA", "onStatusChanged: 当前GPS状态为服务区外状态");
                    break;
                case LocationProvider.TEMPORARILY_UNAVAILABLE:
                    Log.d("WMA-WMA", "onStatusChanged: 当前GPS状态为暂停服务状态");
                    break;
            }
        }

        @Override
        public void onProviderEnabled(String provider) {
            //GPS开启时触发
            Log.d("WMA-WMA", "onProviderEnabled: ");
        }

        @Override
        public void onProviderDisabled(String provider) {
            //GPS禁用时触发
            Log.d("WMA-WMA", "onProviderDisabled: ");
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        PermissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults, this);

    }

    @Override
    public void onPermissionsAllGranted(int requestCode, List<String> perms, boolean isAllGranted) {
        if (isAllGranted) {
            startLocate();
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (PermissionUtils.somePermissionPermanentlyDenied(this, perms)) {
            PermissionUtils.showDialogGoToAppSettting(this);
        } else {
            PermissionUtils.showPermissionReason(requestCode, this, permissions, "需要定位权限");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mLocationManager != null && locationListener != null){
            mLocationManager.removeUpdates(locationListener);
        }
        locationListener = null;
        mLocationManager = null;
    }
}
