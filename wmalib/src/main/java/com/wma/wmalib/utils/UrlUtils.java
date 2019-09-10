package com.wma.wmalib.utils;

import com.wma.wmalib.common.LogUtils;
import com.wma.wmalib.common.WCommon;

import java.net.URLEncoder;

/**
 * Created by 王明骜 on 19-9-9 上午11:23.
 */
public class UrlUtils {


    public static String toURLEncoded(String paramString) {
        if (paramString == null || paramString.equals("")) {
            LogUtils.d(WCommon.TAG,"toURLEncoded error:"+paramString);
            return "";
        }

        try
        {
            String str = new String(paramString.getBytes(), "UTF-8");
            str = URLEncoder.encode(str, "UTF-8");
            return str;
        }
        catch (Exception localException)
        {
            LogUtils.d(WCommon.TAG, localException.toString());
        }

        return "";
    }
}
