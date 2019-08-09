package com.wma.wmalib.base;

import com.wma.wmalib.http.HttpUtils;

import java.io.Serializable;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by 王明骜 on 19-8-8 下午1:34.
 */
public abstract class BaseModel<T> implements Serializable {

    public abstract T getModel(Class<T> classes);


}
