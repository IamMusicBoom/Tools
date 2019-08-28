package com.wma.tools.model.weather;

import com.wma.tools.model.IAllApi;
import com.wma.wmalib.base.BaseModel;
import com.wma.wmalib.callback.HttpCallBack;
import com.wma.wmalib.http.HttpUtils;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 王明骜 on 19-8-13 上午10:19.
 */
public class WeatherModel extends BaseModel<IAllApi> {
    public IAllApi iAllApi;
    /**
     * reason : 查询成功!
     * result : {"city":"成都","realtime":{"temperature":"32","humidity":"63","info":"晴","wid":"00","direct":"东风","power":"1级","aqi":"85"},"future":[{"date":"2019-08-13","temperature":"24/35℃","weather":"多云","wid":{"day":"01","night":"01"},"direct":"持续无风向"},{"date":"2019-08-14","temperature":"23/35℃","weather":"多云转晴","wid":{"day":"01","night":"00"},"direct":"持续无风向"},{"date":"2019-08-15","temperature":"23/33℃","weather":"晴","wid":{"day":"00","night":"00"},"direct":"持续无风向"},{"date":"2019-08-16","temperature":"23/32℃","weather":"晴","wid":{"day":"00","night":"00"},"direct":"持续无风向"},{"date":"2019-08-17","temperature":"24/34℃","weather":"多云","wid":{"day":"01","night":"01"},"direct":"持续无风向"}]}
     * error_code : 0
     */

    private String reason;
    private ResultBean result;
    private int error_code;

    @Override
    public IAllApi getModel(Class<IAllApi> classes) {
        if (iAllApi == null) {
            synchronized (classes) {
                iAllApi = HttpUtils.retrofit.create(classes);
            }
        }
        return iAllApi;
    }
    public WeatherModel(){
        getModel(IAllApi.class);
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }


    public static class ResultBean {
        /**
         * city : 成都
         * realtime : {"temperature":"32","humidity":"63","info":"晴","wid":"00","direct":"东风","power":"1级","aqi":"85"}
         * future : [{"date":"2019-08-13","temperature":"24/35℃","weather":"多云","wid":{"day":"01","night":"01"},"direct":"持续无风向"},{"date":"2019-08-14","temperature":"23/35℃","weather":"多云转晴","wid":{"day":"01","night":"00"},"direct":"持续无风向"},{"date":"2019-08-15","temperature":"23/33℃","weather":"晴","wid":{"day":"00","night":"00"},"direct":"持续无风向"},{"date":"2019-08-16","temperature":"23/32℃","weather":"晴","wid":{"day":"00","night":"00"},"direct":"持续无风向"},{"date":"2019-08-17","temperature":"24/34℃","weather":"多云","wid":{"day":"01","night":"01"},"direct":"持续无风向"}]
         */

        private String city;
        private RealtimeBean realtime;
        private List<FutureBean> future;

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public RealtimeBean getRealtime() {
            return realtime;
        }

        public void setRealtime(RealtimeBean realtime) {
            this.realtime = realtime;
        }

        public List<FutureBean> getFuture() {
            return future;
        }

        public void setFuture(List<FutureBean> future) {
            this.future = future;
        }

        public static class RealtimeBean {
            /**
             * temperature : 32
             * humidity : 63
             * info : 晴
             * wid : 00
             * direct : 东风
             * power : 1级
             * aqi : 85
             */

            private String temperature;
            private String humidity;
            private String info;
            private String wid;
            private String direct;
            private String power;
            private String aqi;

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getHumidity() {
                return humidity;
            }

            public void setHumidity(String humidity) {
                this.humidity = humidity;
            }

            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
            }

            public String getWid() {
                return wid;
            }

            public void setWid(String wid) {
                this.wid = wid;
            }

            public String getDirect() {
                return direct;
            }

            public void setDirect(String direct) {
                this.direct = direct;
            }

            public String getPower() {
                return power;
            }

            public void setPower(String power) {
                this.power = power;
            }

            public String getAqi() {
                return aqi;
            }

            public void setAqi(String aqi) {
                this.aqi = aqi;
            }
        }

        public static class FutureBean {
            /**
             * date : 2019-08-13
             * temperature : 24/35℃
             * weather : 多云
             * wid : {"day":"01","night":"01"}
             * direct : 持续无风向
             */

            private String date;
            private String temperature;
            private String weather;
            private WidBean wid;
            private String direct;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getTemperature() {
                return temperature;
            }

            public void setTemperature(String temperature) {
                this.temperature = temperature;
            }

            public String getWeather() {
                return weather;
            }

            public void setWeather(String weather) {
                this.weather = weather;
            }

            public WidBean getWid() {
                return wid;
            }

            public void setWid(WidBean wid) {
                this.wid = wid;
            }

            public String getDirect() {
                return direct;
            }

            public void setDirect(String direct) {
                this.direct = direct;
            }

            public static class WidBean {
                /**
                 * day : 01
                 * night : 01
                 */

                private String day;
                private String night;

                public String getDay() {
                    return day;
                }

                public void setDay(String day) {
                    this.day = day;
                }

                public String getNight() {
                    return night;
                }

                public void setNight(String night) {
                    this.night = night;
                }
            }
        }
    }

    public void getDatas(String dist,final HttpCallBack<WeatherModel> callBack) {
        HashMap<String,String> map = new HashMap<>();
        map.put("key","795896001e17442acb2ca48ae1ea3167");
        Observable<WeatherModel> weather = iAllApi.getWeather(dist,map);
        weather.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WeatherModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        callBack.onBegin();
                    }

                    @Override
                    public void onNext(WeatherModel weatherModel) {
                        if (weatherModel.getError_code() != 0) {
                            callBack.onFail(weatherModel.getReason());
                        } else if (weatherModel.getResult() == null) {
                            callBack.onFail("返回数据为空...");
                        } else {
                            callBack.onSuccess(weatherModel);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onError(e);
                    }

                    @Override
                    public void onComplete() {
                        callBack.onComplete();
                    }
                });

    }
}
