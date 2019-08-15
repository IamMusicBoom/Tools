package com.wma.tools.model.weather;

import com.wma.tools.model.IAllApi;
import com.wma.wmalib.base.BaseModel;
import com.wma.wmalib.callback.HttpCallBack;
import com.wma.wmalib.http.HttpUtils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 王明骜 on 19-8-14 上午9:40.
 */
public class WidsModel extends BaseModel<IAllApi> {
    IAllApi iAllApi;

    //http://apis.juhe.cn/simpleWeather/wids?key=795896001e17442acb2ca48ae1ea3167
    @Override
    public IAllApi getModel(Class<IAllApi> classes) {
        if (iAllApi == null) {
            synchronized (classes) {
                iAllApi = HttpUtils.retrofit.create(classes);
            }
        }
        return iAllApi;
    }

    public WidsModel() {
        getModel(IAllApi.class);
    }
    /**
     * reason : 查询成功
     * result : [{"wid":"00","weather":"晴"},{"wid":"01","weather":"多云"},{"wid":"02","weather":"阴"},{"wid":"03","weather":"阵雨"},{"wid":"04","weather":"雷阵雨"},{"wid":"05","weather":"雷阵雨伴有冰雹"},{"wid":"06","weather":"雨夹雪"},{"wid":"07","weather":"小雨"},{"wid":"08","weather":"中雨"},{"wid":"09","weather":"大雨"},{"wid":"10","weather":"暴雨"},{"wid":"11","weather":"大暴雨"},{"wid":"12","weather":"特大暴雨"},{"wid":"13","weather":"阵雪"},{"wid":"14","weather":"小雪"},{"wid":"15","weather":"中雪"},{"wid":"16","weather":"大雪"},{"wid":"17","weather":"暴雪"},{"wid":"18","weather":"雾"},{"wid":"19","weather":"冻雨"},{"wid":"20","weather":"沙尘暴"},{"wid":"21","weather":"小到中雨"},{"wid":"22","weather":"中到大雨"},{"wid":"23","weather":"大到暴雨"},{"wid":"24","weather":"暴雨到大暴雨"},{"wid":"25","weather":"大暴雨到特大暴雨"},{"wid":"26","weather":"小到中雪"},{"wid":"27","weather":"中到大雪"},{"wid":"28","weather":"大到暴雪"},{"wid":"29","weather":"浮尘"},{"wid":"30","weather":"扬沙"},{"wid":"31","weather":"强沙尘暴"},{"wid":"53","weather":"霾"}]
     * error_code : 0
     */

    private String reason;
    private int error_code;
    private List<ResultBean> result;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }


    public static class ResultBean {
        /**
         * wid : 00
         * weather : 晴
         */

        private String wid;
        private String weather;

        public String getWid() {
            return wid;
        }

        public void setWid(String wid) {
            this.wid = wid;
        }

        public String getWeather() {
            return weather;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }
    }

    public void getDatas(final HttpCallBack<WidsModel> callBack) {
        Observable<WidsModel> wids = iAllApi.getWids("795896001e17442acb2ca48ae1ea3167");
        wids.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<WidsModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        callBack.onBegin();
                    }

                    @Override
                    public void onNext(WidsModel widsModel) {
                        if (widsModel.getError_code() != 0) {
                            callBack.onFail(widsModel.getReason());
                        } else if (widsModel.getResult() == null) {
                            callBack.onFail("返回数据为空...");
                        } else {
                            callBack.onSuccess(widsModel);
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
