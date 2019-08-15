package com.wma.tools;

import com.squareup.leakcanary.LeakCanary;
import com.wma.tools.model.IAllApi;
import com.wma.wmalib.http.HttpUtils;

import app.BaseAppContext;

/**
 * Created by 王明骜 on 19-8-9 上午10:35.
 */
public class ToolApplication extends BaseAppContext {
    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
        HttpUtils.getInstance().init(IAllApi.NEWS_HOST);
    }
}
