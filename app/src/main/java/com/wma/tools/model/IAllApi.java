package com.wma.tools.model;

import com.wma.tools.model.news.NewsModel;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by 王明骜 on 19-8-9 上午10:24.
 */
public interface IAllApi {

    static String HOST = "https://v.juhe.cn/";

    @GET("toutiao/index")
    Observable<BModel> getNews(@Query("type") String type, @QueryMap Map<String,String> map);
}
