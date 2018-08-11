package com.ashin.wanandroid.network;

import android.content.Context;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public abstract class BaseObserver<T> implements Observer<BaseResult<T>> {


    protected Context mContext;

    public BaseObserver(){};

    public BaseObserver(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onSubscribe(Disposable d) {
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(BaseResult<T> tBaseResult) {

    }
}
