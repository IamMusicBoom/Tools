package com.wma.tools.model;

import com.wma.tools.model.news.NewsModel;
import com.wma.tools.model.weather.CityModel;
import com.wma.tools.model.weather.WeatherModel;
import com.wma.tools.model.weather.WidsModel;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by 王明骜 on 19-8-9 上午10:24.
 */
public interface IAllApi {

    static String NEWS_HOST = "https://v.juhe.cn/";

    @GET("toutiao/index")
    Observable<NewsModel> getNews(@Query("type") String type, @QueryMap Map<String,String> map);


    /**
     * https://web.juhe.cn:8080/constellation/getAll?consName=巨蟹座&type=today&key=8136c5ccf17fa37c236858e9d66ee91b
     */
    static String HOROSCOPE_HOST = "https://web.juhe.cn:8080/constellation/getAll";


    /**
     * http://apis.juhe.cn/simpleWeather/query?city=双流&key=795896001e17442acb2ca48ae1ea3167
     */
    static String WEATHER_HOST = "https://apis.juhe.cn/simpleWeather/";



    @GET("query")
    Observable<WeatherModel> getWeather(@Query("city") String city,@QueryMap Map<String,String> map);

    /**
     * "//http://apis.juhe.cn/simpleWeather/wids?key=795896001e17442acb2ca48ae1ea3167"
     *
     */

    @GET("wids")
    Observable<WidsModel> getWids(@Query("key") String key);


    /**
     * //http://apis.juhe.cn/simpleWeather/cityList?key=795896001e17442acb2ca48ae1ea3167
     */
    @GET("cityList")
    Observable<CityModel> getCities(@Query("key") String key);
}
