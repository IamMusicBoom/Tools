package com.wma.wmalib.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.wma.wmalib.common.LogUtils;
import com.wma.wmalib.widget.NavigationBar;

import app.BaseAppContext;

/**
 * Created by 王明骜 on 19-8-6 下午3:31.
 */
public abstract  class BaseFragment<T extends ViewDataBinding> extends Fragment {
    public LinearLayout mRootView;
    Context mContext = BaseAppContext.getInstance();

    public NavigationBar mNavBar;

    T mContentBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return this.createRootView();
    }

    private View createRootView() {
        mRootView = new LinearLayout(mContext);
        mRootView.setOrientation(LinearLayout.VERTICAL);
        mRootView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

        //title 部分
        mRootView.addView(createNavBar());

        //内容部分
        mRootView.addView(createContentView());
        return mRootView;
    }

    private View createContentView() {
        mContentBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext),getContentLayoutId(),null,false);
        return mContentBinding.getRoot();
    }

    private View createNavBar() {
        mNavBar = new NavigationBar(mContext);
        return mNavBar.getNavBinding().getRoot();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onCreate(savedInstanceState,mContentBinding);
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

    public abstract int getContentLayoutId();

    public abstract void onCreate(Bundle savedInstanceState, T binding);
}
