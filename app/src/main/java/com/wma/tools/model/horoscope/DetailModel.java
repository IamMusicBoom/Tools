package com.wma.tools.model.horoscope;

import com.wma.tools.model.IAllApi;
import com.wma.wmalib.callback.HttpCallBack;
import com.wma.wmalib.http.HttpUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 王明骜 on 19-9-9 下午3:04.
 */
public class DetailModel extends DetailBaseModel{

    IAllApi iAllApi;

    @Override
    public IAllApi getModel(Class<IAllApi> classes) {
        if (iAllApi == null) {
            synchronized (classes) {
                iAllApi = HttpUtils.retrofit.create(classes);
            }
        }
        return iAllApi;
    }

    public DetailModel(){
        getModel(IAllApi.class);
    }

    public void getDataById(String key, final HttpCallBack<DetailModel> callBack){
        HttpUtils.httpUtils.init(IAllApi.DICTIONARY_HOST);
        Map<String, String> map = new HashMap<>();
        map.put("word",key);
        Observable<DetailModel> bushou = iAllApi.getDataById("ad57bd1cd8356e243f3e7ae9ad27418f",map);
        bushou.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DetailModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        callBack.onBegin();
                    }

                    @Override
                    public void onNext(DetailModel detailModel) {
                        if (detailModel.getError_code() != 0) {
                            callBack.onFail(detailModel.getReason());
                        } else if (detailModel.getResult() == null) {
                            callBack.onFail("返回数据为空...");
                        } else {
                            callBack.onSuccess(detailModel);
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
    public void getDataByWord(String key, final HttpCallBack<DetailModel> callBack){
        HttpUtils.httpUtils.init(IAllApi.DICTIONARY_HOST);
        Map<String, String> map = new HashMap<>();
        map.put("word",key);
        Observable<DetailModel> bushou = iAllApi.getDataByWord("ad57bd1cd8356e243f3e7ae9ad27418f",map);
        bushou.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DetailModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        callBack.onBegin();
                    }

                    @Override
                    public void onNext(DetailModel detailModel) {
                        if (detailModel.getError_code() != 0) {
                            callBack.onFail(detailModel.getReason());
                        } else if (detailModel.getResult() == null) {
                            callBack.onFail("返回数据为空...");
                        } else {
                            callBack.onSuccess(detailModel);
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
