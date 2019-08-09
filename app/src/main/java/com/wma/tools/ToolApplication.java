package com.wma.tools;

import com.wma.tools.model.IAllApi;

import app.BaseAppContext;

/**
 * Created by 王明骜 on 19-8-9 上午10:35.
 */
public class ToolApplication extends BaseAppContext {
    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public String getHost() {
        return IAllApi.HOST;
    }
}
