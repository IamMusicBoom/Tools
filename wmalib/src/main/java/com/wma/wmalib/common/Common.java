package com.wma.wmalib.common;

import android.os.Build;

import com.wma.wmalib.BuildConfig;

/**
 * Created by 王明骜 on 19-8-6 下午3:41.
 */
public class Common {
    public static final String TAG = "WMA-Lib";

    public static final boolean DEBUG = BuildConfig.DEBUG;

    /**
     * RecyclerView 形状
     */
    public static final int LINEAR = 0;

    public static final int GRID = 1;
}
