package app;

import android.support.multidex.MultiDexApplication;

import com.wma.wmalib.http.HttpUtils;

/**
 * Created by 王明骜 on 19-8-5 下午4:51.
 */
public abstract class BaseAppContext extends MultiDexApplication {
    private static BaseAppContext appContext;
    protected final static int DEFAULT_MEMMORY = 32;

    public void onCreate() {
        super.onCreate();
        appContext = this;
    }


    public static BaseAppContext getInstance() {
        return appContext;
    }
}
