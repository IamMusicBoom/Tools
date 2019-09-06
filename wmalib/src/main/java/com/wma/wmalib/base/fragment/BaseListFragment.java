package com.wma.wmalib.base.fragment;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wma.wmalib.base.GridSpaceItemDecoration;
import com.wma.wmalib.base.SpaceItemDecoration;
import com.wma.wmalib.base.adapter.BaseRecyclerViewAdapter;
import com.wma.wmalib.common.WCommon;
import com.wma.wmalib.pulltorefresh.library.PullToRefreshBase;
import com.wma.wmalib.pulltorefresh.library.PullToRefreshRecyclerView;

import java.util.List;

/**
 * Created by 王明骜 on 19-8-9 下午2:13.
 *
 * E 是 itemView 的 binding
 * H 是 Fragment 布局的 binding
 */


public abstract class BaseListFragment<T,E extends ViewDataBinding,H extends ViewDataBinding> extends BaseFragment<H> {


    protected int mPage = 1;
//    protected int mTotal = 0;//必须要设置
    protected RecyclerView mRecyclerView;
    public BaseIViewDataRecyclerAdapter mAdapter;
    protected View mEmptyView;
//    public boolean only_from_start;

    public BaseIViewDataRecyclerAdapter getAdapter() {
        return this.mAdapter;
    }

//    public void setMode(PullToRefreshBase.Mode mode) {
//        mRecyclerView.setMode(mode);
//        if (mode == PullToRefreshBase.Mode.PULL_FROM_START) {
//            only_from_start = true;
//        }
//    }



    public void handleData(List<T> _data) {
        if (mAdapter == null) {
            mAdapter = new BaseIViewDataRecyclerAdapter();
//            setLinearLayout();
            mRecyclerView.setAdapter(mAdapter);
        }
        if (mPage == 1) {
            mAdapter.clearItems();
        }

        if (mPage == 1 && (_data == null || _data.size() == 0)) {
            showEmptyView(true);
        } else {
            showEmptyView(false);
            mAdapter.addItems(_data);
        }
//        mRecyclerView.onRefreshComplete();
//        if (!only_from_start) {
//            if (mTotal <= mAdapter.getData().size())
//                mRecyclerView.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
//            else
//                mRecyclerView.setMode(PullToRefreshBase.Mode.BOTH);
//            if (mTotal == 0 && mPage == 1) {
//                mAdapter.clearItems();
//            }
//        }
        mPage++;
    }

    public void resetAdapter() {
        mAdapter.clearItems();
        mAdapter = new BaseIViewDataRecyclerAdapter();
        mRecyclerView.setAdapter(mAdapter);
        showEmptyView(true);
    }

    public void clearData() {
        if (mAdapter ==null)
            return;
        mAdapter.clearItems();
    }


    public void setGridLayout(int span, int spacingInPixels) {
        mRecyclerView.addItemDecoration(new GridSpaceItemDecoration(span, spacingInPixels, false));
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), span, 1, false));
    }

    public void setLinearLayout(int orientation, int spacingInPixels) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), orientation, false));
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(spacingInPixels));
    }


    protected int spacingInPixels() {
        return 20;
    }

    protected int orientation() {
        return LinearLayoutManager.VERTICAL;
    }

    protected int span() {
        return 1;
    }

    protected int style() {
        return WCommon.LINEAR;
    }

    /**
     * listview的时候 会显示占位符
     *
     * @return
     */
    protected abstract View getEmptyView();

    protected void emptyViewOnClick() {
        mPage = 1;
        getListData();
    }

    protected void showEmptyView(boolean show) {
        if (mEmptyView == null)
            return;
        if (mEmptyView.getVisibility() == View.VISIBLE && show) {
            return;
        }else if(mEmptyView.getVisibility() == View.GONE && !show){
            return;
        }

        mEmptyView.setVisibility(show ? View.VISIBLE : View.GONE);

        if(mEmptyView.getVisibility() == View.VISIBLE){
            mRecyclerView.setVisibility(View.GONE);
        }else{
            mRecyclerView.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mRecyclerView = null;
        mAdapter = null;
        mEmptyView = null;
    }

    @Override
    public void initRecyclerView() {
        mEmptyView = getEmptyView();
        if (mEmptyView != null) {
            mEmptyView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    emptyViewOnClick();
                }
            });
        }

        if (mRecyclerView != null) {
            if (style() == WCommon.LINEAR) {
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), orientation(), false));
//                mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
//                mRecyclerView.addItemDecoration(new SpaceItemDecoration(spacingInPixels()));
            } else {
                mRecyclerView.addItemDecoration(new GridSpaceItemDecoration(span(), spacingInPixels(), false));
                mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), span(), orientation(), false));
            }
        }
    }


    public abstract void getListData();

    public abstract int getItemLayout(int viewType);

    public abstract void bindItemData(E e, T info, int position);

    public class BaseIViewDataRecyclerAdapter extends BaseRecyclerViewAdapter<T, E> {

        @Override
        protected int getItemLayoutId(int viewType) {
            return getItemLayout(viewType);
        }

        @Override
        protected void bindData(E e, T info, int position) {
            bindItemData(e, info, position);
        }
    }


}
