package com.wma.wmalib.common;

import android.util.Log;

/**
 * Created by 王明骜 on 19-8-6 下午3:37.
 */
public class LogUtils {

    public static void d(String TAG,String msg){
        Log.d(WCommon.TAG,TAG + ": " + msg);
    }
    public static void d(String msg){
        Log.d(WCommon.TAG,msg);
    }
    public static void w(String TAG,String msg){
        Log.w(WCommon.TAG,TAG + ": " + msg);
    }
    public static void w(String msg){
        Log.w(WCommon.TAG, msg);
    }
    public static void e(String TAG,String msg){
        Log.e(WCommon.TAG,TAG + ": " + msg);
    }
    public static void e(String msg){
        Log.e(WCommon.TAG, msg);
    }
    public static void i(String TAG,String msg){
        Log.i(WCommon.TAG,TAG + ": " + msg);
    }
    public static void i(String msg){
        Log.i(WCommon.TAG, msg);
    }
    public static void v(String TAG,String msg){
        Log.v(WCommon.TAG,TAG + ": " + msg);
    }
    public static void v(String msg){
        Log.v(WCommon.TAG, msg);
    }
}
