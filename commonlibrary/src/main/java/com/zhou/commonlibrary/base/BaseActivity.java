package com.zhou.commonlibrary.base;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.Window;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.zhou.commonlibrary.R;
import com.zhou.commonlibrary.utils.TUtil;
import com.zhou.commonlibrary.widget.StatusBarCompat;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<P extends BasePresenter, M extends BaseModel> extends RxAppCompatActivity {

    public P mPresenter;
    public M mModel;


    protected Unbinder mUnbinder;
    protected Context mContext;

    protected abstract int getLayoutId();

    protected abstract void initPresenter();

    protected abstract void initView(Bundle bundle);

    protected abstract void initEvent();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doBeforeSetcontentView();
        setContentView(getLayoutId());
        mUnbinder = ButterKnife.bind(this);
        mContext = this;
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        if (null != mPresenter) {
            mPresenter.mContext = this.mContext;
        }
        initPresenter();
        initView(savedInstanceState);
        initEvent();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.onDestory();
            mPresenter = null;
        }
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        AppManager.getAppManager().finishActivity(this);
    }

    /**
     * 设置layout前配置
     */
    protected void doBeforeSetcontentView() {
        // 把actvity放到application栈中管理
        AppManager.getAppManager().addActivity(this);
        // 无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // 默认着色状态栏
        SetStatusBarColor();

    }

    /**
     * 着色状态栏（4.4以上系统有效）
     */
    protected void SetStatusBarColor() {
        StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this, R.color.colorPrimary));
    }

    /**
     * 着色状态栏（4.4以上系统有效）
     */
    protected void SetStatusBarColor(int color) {
        StatusBarCompat.setStatusBarColor(this, color);
    }

    /**
     * 沉浸状态栏（4.4以上系统有效）
     */
    protected void SetTranslanteBar() {
        StatusBarCompat.translucentStatusBar(this);
    }


}
