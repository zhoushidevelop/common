package com.zhou.commonlibrary.base;

import android.content.Context;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * @author Administrator
 * @date 2018/7/13 0013
 * @des 类描述：
 * 1.获取绑定View实例传递到子类中进行调用!
 * <p>
 * 2.注销View实例
 * <p>
 * 3.创建 Model 实例
 * <p>
 * 4.注销Model实例
 * <p>
 * 5.通过RxJava进行绑定activity和fragment生命周期绑定
 */
public class BasePresenter<V, M> {
    public Context mContext;
    public M mModel;
    public V mView;

    protected CompositeSubscription mCompositeSubscription;

    //解绑
    protected void unSubscribe() {
        if (null != mCompositeSubscription && !mCompositeSubscription.isUnsubscribed()) {
            mCompositeSubscription.unsubscribe();
        }
    }

    protected void addSubscribe(Subscription subscription) {
        if (null == mCompositeSubscription) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);

    }

    public boolean isBindView() {
        return null == this.mView;
    }

    public void onAttach(M model, V view) {
        this.mModel = model;
        this.mView = view;

    }

    public void onDestory() {
        this.mView = null;
        this.mModel = null;
        unSubscribe();
    }
}
