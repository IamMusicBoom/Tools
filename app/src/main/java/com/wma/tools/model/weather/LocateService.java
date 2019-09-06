package com.wma.tools.model.weather;

import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.JobIntentService;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.wma.tools.ToolApplication;
import com.wma.tools.model.weather.view.LocatingView;
import com.wma.tools.utils.SPUtils;
import com.wma.tools.utils.Utils;
import com.wma.wmalib.utils.LocationUtils;

/**
 * Created by 王明骜 on 19-9-5 上午10:09.
 */
public class LocateService extends Service {
    LocationManager mLocationManager;


    /**
     * 开始定位
     */
    public void startLocate() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mLocationManager = (LocationManager) ToolApplication.getInstance().getSystemService(Context.LOCATION_SERVICE);
                boolean providerEnabled = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
//        Log.d(TAG, "startLocate: providerEnabled = " + providerEnabled);
                if (providerEnabled) { //GPS已开启

                    Criteria criteria = new Criteria();
                    criteria.setAccuracy(Criteria.ACCURACY_FINE);//高精度
                    criteria.setAltitudeRequired(false);//无海拔要求   criteria.setBearingRequired(false);//无方位要求
                    criteria.setCostAllowed(true);//允许产生资费
                    criteria.setPowerRequirement(Criteria.POWER_LOW);//低功耗

//            criteria.setAccuracy(Criteria.ACCURACY_FINE);
//            //设置定位精准度
//            criteria.setAltitudeRequired(false);
//            //是否要求海拔
//            criteria.setBearingRequired(true);
//            //是否要求方向
//            criteria.setCostAllowed(true);
//            //是否要求收费
//            criteria.setSpeedRequired(true);
//            //是否要求速度
//            criteria.setPowerRequirement(Criteria.NO_REQUIREMENT);
//            //设置电池耗电要求
//            criteria.setBearingAccuracy(Criteria.ACCURACY_HIGH);
//            //设置方向精确度
//            criteria.setSpeedAccuracy(Criteria.ACCURACY_HIGH);
//            //设置速度精确度
//            criteria.setHorizontalAccuracy(Criteria.ACCURACY_HIGH);
//            //设置水平方向精确度
//            criteria.setVerticalAccuracy(Criteria.ACCURACY_HIGH);
//            //设置垂直方向精确度

                    // 获取最佳服务对象
                    String provider = mLocationManager.getBestProvider(criteria, true);
                    /**
                     * 绑定监听
                     * 参数1，设备：有GPS_PROVIDER和NETWORK_PROVIDER两种，前者是GPS,后者是GPRS以及WIFI定位
                     * 参数2，位置信息更新周期.单位是毫秒
                     * 参数3，位置变化最小距离：当位置距离变化超过此值时，将更新位置信息
                     * 参数4，监听
                     * 备注：参数2和3，如果参数3不为0，则以参数3为准；参数3为0，则通过时间来定时更新；两者为0，则随时刷新
                     */
                    //TODO 设定为固定的网络定位
                    Looper.prepare();
                    mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
                    Looper.loop();
                } else {
                    Looper.prepare();
                    Toast.makeText(ToolApplication.getInstance(), "请打开GPS", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }
            }
        }).start();

    }


    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            String countryName = LocationUtils.getCountryName(ToolApplication.getInstance(), location.getLatitude(), location.getLongitude());
            String provinceName = LocationUtils.getProvinceName(ToolApplication.getInstance(), location.getLatitude(), location.getLongitude());
            String cityName = LocationUtils.getCityName(ToolApplication.getInstance(), location.getLatitude(), location.getLongitude());
            String dist = LocationUtils.getStreet(ToolApplication.getInstance(), location.getLatitude(), location.getLongitude());
            Log.d("WMA-WMA", "onLocationChanged: countryName = " + countryName + " provinceName = " + provinceName + " cityName = " + cityName + " dist = " + dist);
            Log.d("WMA-WMA", "onLocationChanged: " + !TextUtils.isEmpty(countryName));
            Log.d("WMA-WMA", "onLocationChanged: " + !countryName.equals("unknown"));
            if (!TextUtils.isEmpty(countryName) && !countryName.equals("unknown")) {
                String s = Utils.formatProvince(provinceName);
                SPUtils.setCurProvince(s);
                SPUtils.setCurCity(Utils.formatCity(cityName));
                SPUtils.setCurDist(Utils.formatDist(dist));
                ToolApplication.getInstance().sendBroadcast(new Intent("com.wma.tools.locateSuccess"));
                SPUtils.setLocateState(LocatingView.LOCATE_SUCCESS);
            } else {
                ToolApplication.getInstance().sendBroadcast(new Intent("com.wma.tools.locateFail"));
                SPUtils.setLocateState(LocatingView.LOCATE_FAIL);
            }
            mLocationManager.removeUpdates(locationListener);
            stopSelf();
            //位置信息变化时触发
//            Log.d(TAG, "定位方式：" + location.getProvider());
//            Log.d(TAG, "纬度：" + location.getLatitude());
//            Log.d(TAG, "经度：" + location.getLongitude());
//            Log.d(TAG, "海拔：" + location.getAltitude());
//            Log.d(TAG, "时间：" + location.getTime());
//            Log.d(TAG, "国家: " + LocationUtils.getCountryName(ToolApplication.getInstance(), location.getLatitude(), location.getLongitude()));
//            Log.d(TAG, "城市名字: " + LocationUtils.getCityName(ToolApplication.getInstance(), location.getLatitude(), location.getLongitude()));
//            Log.d(TAG, "省份: " + LocationUtils.getProvinceName(ToolApplication.getInstance(), location.getLatitude(), location.getLongitude()));
//            Log.d(TAG, "街道: " + LocationUtils.getStreet(ToolApplication.getInstance(), location.getLatitude(), location.getLongitude()));
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
//            Log.d(TAG, "onStatusChanged: ");
            //GPS状态变化时触发
            switch (status) {
                case LocationProvider.AVAILABLE:
//                    Log.d(TAG, "onStatusChanged: 当前GPS状态为可见状态");
                    break;
                case LocationProvider.OUT_OF_SERVICE:
//                    Log.d(TAG, "onStatusChanged: 当前GPS状态为服务区外状态");
                    break;
                case LocationProvider.TEMPORARILY_UNAVAILABLE:
//                    Log.d(TAG, "onStatusChanged: 当前GPS状态为暂停服务状态");
                    break;
            }
        }

        @Override
        public void onProviderEnabled(String provider) {
            //GPS开启时触发
//            Log.d(TAG, "onProviderEnabled: ");
        }

        @Override
        public void onProviderDisabled(String provider) {
            //GPS禁用时触发
//            Log.d(TAG, "onProviderDisabled: ");
        }
    };


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startLocate();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
