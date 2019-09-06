package com.wma.tools.model.horoscope;

import com.wma.tools.model.IAllApi;
import com.wma.wmalib.callback.HttpCallBack;
import com.wma.wmalib.http.HttpUtils;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 王明骜 on 19-9-6 下午1:35.
 */
public class BuShouModel extends BuShouBaseModel {
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

    public BuShouModel(){
        getModel(IAllApi.class);
    }
    public void getDatas(final HttpCallBack<BuShouModel> callBack) {
        HttpUtils.httpUtils.init(IAllApi.DICTIONARY_HOST);
        Observable<BuShouModel> pinYin = iAllApi.getBuShou("ad57bd1cd8356e243f3e7ae9ad27418f");
        pinYin.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BuShouModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        callBack.onBegin();
                    }

                    @Override
                    public void onNext(BuShouModel bushou) {
                        if (bushou.getError_code() != 0) {
                            callBack.onFail(bushou.getReason());
                        } else if (bushou.getResult() == null) {
                            callBack.onFail("返回数据为空...");
                        } else {
                            callBack.onSuccess(bushou);
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
