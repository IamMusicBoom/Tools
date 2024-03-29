package com.wma.wmalib.base.fragment;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.wma.wmalib.base.GridSpaceItemDecoration;
import com.wma.wmalib.base.SpaceItemDecoration;
import com.wma.wmalib.base.adapter.BaseRecyclerViewAdapter;
import com.wma.wmalib.common.WCommon;
import com.wma.wmalib.pulltorefresh.library.PullToRefreshBase;
import com.wma.wmalib.pulltorefresh.library.PullToRefreshRecyclerView;

import java.util.List;

/**
 * Created by 王明骜 on 19-9-4 下午4:58.
 */
public abstract class BaseRefreshListFragment<T,E extends ViewDataBinding,H extends ViewDataBinding> extends BaseFragment<H> implements PullToRefreshBase.OnRefreshListener2 {


    protected int mPage = 1;
    //    protected int mTotal = 0;//必须要设置
    protected PullToRefreshRecyclerView mRecyclerView;
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
        if(mRecyclerView==null){
            return;
        }
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
        mRecyclerView.onRefreshComplete();
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
        mRecyclerView.getRefreshableView().addItemDecoration(new GridSpaceItemDecoration(span, spacingInPixels, false));
        mRecyclerView.getRefreshableView().setLayoutManager(new GridLayoutManager(getActivity(), span, 1, false));
    }

    public void setLinearLayout(int orientation, int spacingInPixels) {
        mRecyclerView.getRefreshableView().setLayoutManager(new LinearLayoutManager(getActivity(), orientation, false));
        mRecyclerView.getRefreshableView().addItemDecoration(new SpaceItemDecoration(spacingInPixels));
    }


    protected int spacingInPixels() {
        return 0;
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
    public void initRecyclerView(boolean isRevert) {
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
            mRecyclerView.setOnRefreshListener(this);
        }

        if (mRecyclerView != null) {
            if (style() == WCommon.LINEAR) {
                mRecyclerView.getRefreshableView().setLayoutManager(new LinearLayoutManager(getActivity(), orientation(), isRevert));
                mRecyclerView.getRefreshableView().addItemDecoration(new SpaceItemDecoration(spacingInPixels()));
            } else {
                mRecyclerView.getRefreshableView().addItemDecoration(new GridSpaceItemDecoration(span(), spacingInPixels(), false));
                mRecyclerView.getRefreshableView().setLayoutManager(new GridLayoutManager(getActivity(), span(), orientation(), isRevert));
            }
        }
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        mPage = 1;
        getListData();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {

        getListData();
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
