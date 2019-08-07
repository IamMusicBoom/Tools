package com.wma.wmalib.common;

import android.util.Log;

/**
 * Created by 王明骜 on 19-8-6 下午3:37.
 */
public class LogUtils {

    public static void d(String TAG,String msg){
        Log.d(Common.TAG,TAG + ": " + msg);
    }
    public static void w(String TAG,String msg){
        Log.w(Common.TAG,TAG + ": " + msg);
    }
    public static void e(String TAG,String msg){
        Log.e(Common.TAG,TAG + ": " + msg);
    }
    public static void i(String TAG,String msg){
        Log.i(Common.TAG,TAG + ": " + msg);
    }
    public static void v(String TAG,String msg){
        Log.v(Common.TAG,TAG + ": " + msg);
    }
}
