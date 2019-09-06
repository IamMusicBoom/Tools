package com.wma.tools.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.EditText;

import com.wma.tools.ToolApplication;
import com.wma.tools.model.weather.view.LocatingView;
import com.wma.wmalib.pulltorefresh.library.PullToRefreshBase;

/**
 * Created by 王明骜 on 19-8-14 下午4:41.
 */
public class SPUtils {
    private static String IS_LOAD_CITY = "is_load_city";
    private static String IS_LOAD_WID = "is_load_wid";

    private static String CUR_CITY = "cur_city";
    private static String CUR_DIST = "cur_dist";
    private static String CUR_PROVINCE = "cur_province";


    private static String IS_LOAD_PIN_YIN = "is_load_pin_yin";

    private static String LOCATE_STATE = "locate_state";


    private static Context mContext = ToolApplication.getInstance();

    private static SharedPreferences sp = mContext.getSharedPreferences(ToolApplication.class.getPackage().getName(),Context.MODE_PRIVATE);

    //================== 天气模块 ==================


    public static void setIsLoadCity(boolean isLoad){
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean(IS_LOAD_CITY,isLoad);
        edit.apply();
    }
    public static boolean getIsLoadCity(){
        boolean aBoolean = sp.getBoolean(IS_LOAD_CITY, false);
        return aBoolean;
    }

    public static void setIsLoadWid(boolean isLoad){
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean(IS_LOAD_WID,isLoad);
        edit.apply();
    }
    public static boolean getIsLoadWid(){
        boolean aBoolean = sp.getBoolean(IS_LOAD_WID, false);
        return aBoolean;
    }

    public static void setCurCity(String city){
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(CUR_CITY,city);
        edit.apply();
    }
    public static String getCurCity(){
        String city = sp.getString(CUR_CITY, "");
        return city;
    }

    public static void setCurDist(String dist){
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(CUR_DIST,dist);
        edit.apply();
    }

    public static String getCurDist(){
        String dist = sp.getString(CUR_DIST, "");
        return dist;
    }

    public static void setCurProvince(String province){
        SharedPreferences.Editor edit = sp.edit();
        edit.putString(CUR_PROVINCE,province);
        edit.apply();
    }
    public static String getCurProvince(){
        String province = sp.getString(CUR_PROVINCE, "");
        return province;
    }


    public static int getLocateState(){
        int anInt = sp.getInt(LOCATE_STATE, LocatingView.UNLOCATE);
        return anInt;
    }

    public static void setLocateState(int locateState){
        SharedPreferences.Editor edit = sp.edit();
        edit.putInt(LOCATE_STATE,locateState);
        edit.apply();
    }


    //================== 天气模块 ==================




    //================== 字典模块 ==================

    public static boolean isLoadPinYin(){
        return sp.getBoolean(IS_LOAD_PIN_YIN,false);
    }
    public static void setIsLoadPinYin(boolean isLoadPinYin){
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean(IS_LOAD_PIN_YIN,isLoadPinYin);
        edit.apply();
    }


}
