package app;

import android.support.multidex.MultiDexApplication;

/**
 * Created by 王明骜 on 19-8-5 下午4:51.
 */
public class WMABaseAppContext extends MultiDexApplication {
    private static WMABaseAppContext appContext;
    protected final static int DEFAULT_MEMMORY = 32;

    public void onCreate() {
        super.onCreate();
        appContext = this;
    }

    public static WMABaseAppContext getInstance() {
        return appContext;
    }
}
