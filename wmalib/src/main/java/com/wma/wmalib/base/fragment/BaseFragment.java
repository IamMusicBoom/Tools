package com.wma.wmalib.base.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.wma.wmalib.loading.LoadingHandler;
import com.wma.wmalib.widget.NavigationBar;

import app.BaseAppContext;

/**
 * Created by 王明骜 on 19-8-6 下午3:31.
 */
public abstract class BaseFragment<T extends ViewDataBinding> extends Fragment {


    /**
     * 是否初始化过布局
     */
    public boolean isViewInitiated = false;

    public boolean isFirstLoad = true;
    /**
     * 当前界面是否可见
     */
    public boolean isVisibleToUser = false;

    Context mContext = BaseAppContext.getInstance();

    public NavigationBar mNavBar;


    @Override
    public void onDestroyView() {
        super.onDestroyView();

        isViewInitiated = false;

        isFirstLoad = true;

        isVisibleToUser = false;

        mNavBar = null;

    }





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return this.createRootView(container);
    }

    private View createRootView(ViewGroup container) {
        LinearLayout mRootView = new LinearLayout(mContext);
        mRootView.setOrientation(LinearLayout.VERTICAL);
        mRootView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

        //title 部分
        mRootView.addView(createNavBar());

        //内容部分
        mRootView.addView(createContentView(container));
        return mRootView;
    }

    private View createContentView(ViewGroup container) {
        T mContentBinding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), getContentLayoutId(), container, false);
        createContentView(container,mContentBinding);
        return mContentBinding.getRoot();
    }

    protected abstract void createContentView(ViewGroup container, T binding);


    private View createNavBar() {
        mNavBar = new NavigationBar(mContext);
        return mNavBar.getNavBinding().getRoot();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewInitiated = true;
        create(savedInstanceState);
        initRecyclerView();
        if(isFirstLoad && isViewInitiated && isVisibleToUser){
            loadData();
            isFirstLoad = false;
        }
    }

    public void initRecyclerView() {

    }

    protected  void loadData(){

    }

    public void setTitle(String title, View.OnClickListener listener) {
        mNavBar.setNavBarVisible();
        mNavBar.setTitle(title, listener);
    }

    public void setRightText(String msg, int imgId, View.OnClickListener listener) {
        mNavBar.setNavBarVisible();
        mNavBar.registerRightText(msg, imgId, listener);
    }

    public void setLeftText(String msg, int imgId, View.OnClickListener listener) {
        mNavBar.setNavBarVisible();
        mNavBar.registerLeftText(msg, imgId, listener);
    }

    public abstract int getContentLayoutId();

    public abstract void create(Bundle savedInstanceState);


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;

        if(this.isVisibleToUser && isFirstLoad && isViewInitiated){
            isFirstLoad = false;
            loadData();
        }
    }


    // region Loading dialog

    private LoadingHandler _loadingHandler;

    public void showLoading() {
        if (_loadingHandler == null)
            _loadingHandler = new LoadingHandler(getActivity());
        _loadingHandler.showLoading();
    }

    public void showLoading(String message) {
        if (_loadingHandler != null)
            _loadingHandler.showLoading(message);
    }

    public void updateLoading(String message) {

        if (_loadingHandler != null)
            _loadingHandler.updateLoading(message);
    }

    public void hideLoading() {
        if (_loadingHandler != null)
            _loadingHandler.hideLoading();
    }



}
