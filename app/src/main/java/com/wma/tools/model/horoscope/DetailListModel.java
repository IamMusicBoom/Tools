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
 * Created by 王明骜 on 19-9-9 上午10:39.
 */
public class DetailListModel extends DetailListBaseModel {

    IAllApi iAllApi;

    //http://v.juhe.cn/xhzd/bushou?key=ad57bd1cd8356e243f3e7ae9ad27418f
    @Override
    public IAllApi getModel(Class<IAllApi> classes) {
        if (iAllApi == null) {
            synchronized (classes) {
                iAllApi = HttpUtils.retrofit.create(classes);
            }
        }
        return iAllApi;
    }

    public DetailListModel(){
        getModel(IAllApi.class);
    }



    public void getBuShouData(String key,final HttpCallBack<DetailListModel> callBack){
        HttpUtils.httpUtils.init(IAllApi.DICTIONARY_HOST);
        Map<String, String> map = new HashMap<>();
        map.put("word",key);
        Observable<DetailListModel> bushou = iAllApi.getBuShouDetailList("ad57bd1cd8356e243f3e7ae9ad27418f",map);
        bushou.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DetailListModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        callBack.onBegin();
                    }

                    @Override
                    public void onNext(DetailListModel detailListModel) {
                        if (detailListModel.getError_code() != 0) {
                            callBack.onFail(detailListModel.getReason());
                        } else if (detailListModel.getResult() == null) {
                            callBack.onFail("返回数据为空...");
                        } else {
                            callBack.onSuccess(detailListModel);
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


    public void getPinYinData(String key,final HttpCallBack<DetailListModel> callBack){
        HttpUtils.httpUtils.init(IAllApi.DICTIONARY_HOST);
        Map<String, String> map = new HashMap<>();
        map.put("word",key);
        Observable<DetailListModel> pinYin = iAllApi.getPinYinDetailList("ad57bd1cd8356e243f3e7ae9ad27418f",map);
        pinYin.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DetailListModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        callBack.onBegin();
                    }

                    @Override
                    public void onNext(DetailListModel detailListModel) {
                        if (detailListModel.getError_code() != 0) {
                            callBack.onFail(detailListModel.getReason());
                        } else if (detailListModel.getResult() == null) {
                            callBack.onFail("返回数据为空...");
                        } else {
                            callBack.onSuccess(detailListModel);
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
