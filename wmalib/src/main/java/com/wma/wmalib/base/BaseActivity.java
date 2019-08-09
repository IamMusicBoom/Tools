package com.wma.wmalib.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.android.debug.hv.ViewServer;
import com.wma.wmalib.common.LogUtils;
import com.wma.wmalib.widget.NavigationBar;

import app.AppManager;
import app.BaseAppContext;

/**
 * Created by 王明骜 on 19-8-5 下午4:08.
 */
public abstract class BaseActivity<T extends ViewDataBinding> extends AppCompatActivity {
    AppManager appManager;

   public LinearLayout mRootView;

    Context mContext = BaseAppContext.getInstance();

    public NavigationBar mNavBar;


    T mContentBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(this.createRootView());
        //添加Activity 方便统一管理
        appManager = AppManager.getAppManager();
        appManager.addActivity(this);
        appManager.addMemory(this);

        //设置App Hierarchy View 可以查看
        ViewServer.get(this).addWindow(this);

        onCreate(savedInstanceState,mContentBinding);


    }

    public abstract void onCreate(Bundle savedInstanceState, T binding);

    public abstract int getContentLayoutId();

    /**
     * 创建根视图
     * @return
     */
    private View createRootView() {
        mRootView = new LinearLayout(mContext);
        mRootView.setOrientation(LinearLayout.VERTICAL);
        mRootView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

        //title 部分
        mRootView.addView(createNavBar());


        mRootView.addView(createContentView());

        return mRootView;
    }

    /**
     * 创建内容区域
     * @return
     */
    private View createContentView() {
        mContentBinding = DataBindingUtil.inflate(LayoutInflater.from(this),getContentLayoutId(),mRootView,false);
        return mContentBinding.getRoot();
    }

    /**
     * 创建title
     * @return
     */
    private View createNavBar() {
        mNavBar = new NavigationBar(mContext);
        return mNavBar.getNavBinding().getRoot();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        if(appManager != null){
            appManager.finalize(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //设置App Hierarchy View 可以查看
        ViewServer.get(this).removeWindow(this);

        appManager.finishActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //设置App Hierarchy View 可以查看
        ViewServer.get(this).setFocusedWindow(this);
    }

    public void setTitle(String title,View.OnClickListener listener){
        mNavBar.setNavBarVisible();
        mNavBar.setTitle(title,listener);
    }

    public void setRightText(String msg,int imgId,View.OnClickListener listener){
        mNavBar.setNavBarVisible();
        mNavBar.registerRightText(msg,imgId,listener);
    }
    public void setLeftText(String msg,int imgId,View.OnClickListener listener){
        mNavBar.setNavBarVisible();
        mNavBar.registerLeftText(msg,imgId,listener);
    }
}
