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
import com.wma.tools.utils.LocateTools;
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
        getLocation();
        goMain();

    }

    private void goMain() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
                            FileUtils.write(mContext, "P_"+province, new Gson().toJson(model));
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
            LocateTools.startLocate();
        }
    }






    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        PermissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults, this);

    }

    @Override
    public void onPermissionsAllGranted(int requestCode, List<String> perms, boolean isAllGranted) {
        if (isAllGranted) {
            LocateTools.startLocate();
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
    }
}
