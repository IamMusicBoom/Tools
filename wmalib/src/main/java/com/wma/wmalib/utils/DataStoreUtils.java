package com.wma.wmalib.utils;

import android.content.Context;
import android.content.SharedPreferences;

import app.BaseAppContext;

/**
 * Created by 王明骜 on 19-9-4 下午2:06.
 */
public class DataStoreUtils {
    static Context mContext = BaseAppContext.getInstance();
    static SharedPreferences sp = mContext.getSharedPreferences(mContext.getPackageName(),mContext.MODE_PRIVATE);

    static final String IS_FIRST_TIME = "is_first_time";


    //=================== 第一次运行 ===================

    public static boolean isFirstTime(){
       return sp.getBoolean(IS_FIRST_TIME,false);
    }

    public static void setIsFirstTime(boolean isFirstTime){
        SharedPreferences.Editor edit = sp.edit();
        edit.putBoolean(IS_FIRST_TIME,isFirstTime);
        edit.apply();
    }

}
