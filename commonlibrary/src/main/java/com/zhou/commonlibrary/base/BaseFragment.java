package com.zhou.commonlibrary.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle.components.support.RxFragment;
import com.zhou.commonlibrary.utils.TUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Administrator
 * @date 2018/7/13 0013
 * @des
 */
public abstract class BaseFragment<M extends BaseModel, P extends BasePresenter> extends RxFragment {
    public P mPresenter;
    public M mModel;

    protected Unbinder mUnbinder;
    protected Context mContext;
    private boolean isViewPrepared;//view准备完毕
    private boolean hasFetchData;//是否触发过懒加载
    private View rootView;


    /**
     * 懒加载的方式获取数据，仅在满足fragment可见和视图已经准备好的时候调用一次
     */
    protected abstract void lazyFetchData();

    protected abstract int getLayoutId();

    protected abstract void initPresenter();

    protected abstract void initView(Bundle bundle);

    protected abstract void initEvent();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutId(), container, false);
        }
        mUnbinder = ButterKnife.bind(this, rootView);
        mContext = this.getActivity();
        mPresenter = TUtil.getT(this, 1);
        mModel = TUtil.getT(this, 0);
        if (null != mPresenter) {
            mPresenter.mContext = this.mContext;
        }
        initPresenter();
        initView(savedInstanceState);
        initEvent();
        return rootView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            lazyFetchDataIfPrepared();
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewPrepared = true;
        lazyFetchDataIfPrepared();

    }

    /**
     * 进行懒加载
     */
    private void lazyFetchDataIfPrepared() {
        // 用户可见fragment && 没有加载过数据 && 视图已经准备完毕
        if (getUserVisibleHint() && !hasFetchData && isViewPrepared) {
            hasFetchData = true;
            lazyFetchData();
        }

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestory();
            mPresenter = null;
        }
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }


}

