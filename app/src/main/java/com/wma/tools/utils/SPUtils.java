package com.wma.tools.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.wma.tools.ToolApplication;
import com.wma.wmalib.pulltorefresh.library.PullToRefreshBase;

/**
 * Created by 王明骜 on 19-8-14 下午4:41.
 */
public class SPUtils {
    private static String IS_LOAD_CITY = "is_load_city";
    private static String IS_LOAD_WID = "is_load_wid";

    private static Context mContext = ToolApplication.getInstance();

    private static SharedPreferences sp = mContext.getSharedPreferences(ToolApplication.class.getPackage().getName(),Context.MODE_PRIVATE);

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
}
