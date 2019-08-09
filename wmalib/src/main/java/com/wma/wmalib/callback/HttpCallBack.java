package com.wma.wmalib.callback;

/**
 * Created by 王明骜 on 19-8-9 上午10:50.
 */
public interface HttpCallBack<T> {
    void onBegin();
    void onComplete();
    void onSuccess(T t);
    void  onFail(String e);
    void  onError(Throwable e);
}
