package com.wma.tools.horoscope;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.wma.tools.R;
import com.wma.tools.databinding.FragmentPinYinBinding;
import com.wma.tools.databinding.ItemDetailBinding;
import com.wma.tools.databinding.ItemPinYinBinding;
import com.wma.tools.model.horoscope.DetailListBaseModel;
import com.wma.tools.model.horoscope.DetailListModel;
import com.wma.wmalib.base.fragment.BaseListFragment;
import com.wma.wmalib.callback.HttpCallBack;
import com.wma.wmalib.common.LogUtils;
import com.wma.wmalib.utils.UrlUtils;

import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by 王明骜 on 19-9-9 上午10:27.
 */
public class ListDetailFragment extends BaseListFragment<DetailListBaseModel.ResultBean.ListBean, ItemDetailBinding, FragmentPinYinBinding> {
    WeakReference<FragmentPinYinBinding> mWeakBinding;
    private final String TAG = ListDetailFragment.this.getClass().getSimpleName();
    @Override
    protected View getEmptyView() {
        return null;
    }

    @Override
    public void getListData() {

    }

    @Override
    public int getItemLayout(int viewType) {
        return R.layout.item_detail;
    }

    @Override
    public void bindItemData(ItemDetailBinding itemDetailBinding, final DetailListBaseModel.ResultBean.ListBean info, int position) {
        itemDetailBinding.tvBihua.setText(info.getBihua());
        itemDetailBinding.tvBushou.setText(info.getBushou());
        itemDetailBinding.tvPinYin.setText(info.getPinyin());
        itemDetailBinding.tvZi.setText(info.getZi());
        if(info.getPinyin().contains(",")){
            itemDetailBinding.tvDuo.setVisibility(View.VISIBLE);
        }else{
            itemDetailBinding.tvDuo.setVisibility(View.INVISIBLE);
        }
        itemDetailBinding.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((DictionaryKindActivity) getActivity()).goNext(info.getZi(),info.getId());
            }
        });
    }

    @Override
    protected void createContentView(ViewGroup container, FragmentPinYinBinding binding) {
        mWeakBinding = new WeakReference<>(binding);
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.fragment_pin_yin;
    }

    @Override
    public void create(Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        String key = arguments.getString("key");
        final int position = arguments.getInt("position");
        mRecyclerView = (RecyclerView) ((ViewGroup) mWeakBinding.get().getRoot()).getChildAt(0);
        mEmptyView =  ((ViewGroup) mWeakBinding.get().getRoot()).getChildAt(1);
        getData(position, key);
    }

    private void getData(int position, String key) {
        switch (position){
            case DictionaryKindActivity.PIN_YIN:
                getPinYinData(key);
                break;
            case DictionaryKindActivity.BU_SHOU:
                getBuShouData(key);
                break;
        }
    }

    private void getBuShouData(String key) {
        new DetailListModel().getBuShouData(key, new HttpCallBack<DetailListModel>() {
            @Override
            public void onBegin() {
                showLoading();
            }

            @Override
            public void onComplete() {
                hideLoading();
            }

            @Override
            public void onSuccess(DetailListModel detailListModel) {
                List<DetailListBaseModel.ResultBean.ListBean> list = detailListModel.getResult().getList();
                handleData(list);
            }

            @Override
            public void onFail(String e) {
                hideLoading();
                LogUtils.d(TAG,e);
            }

            @Override
            public void onError(Throwable e) {
                hideLoading();
                LogUtils.d(TAG,e.toString());
            }
        });
    }

    private void getPinYinData(String key) {
        new DetailListModel().getPinYinData(key, new HttpCallBack<DetailListModel>() {
            @Override
            public void onBegin() {
                showLoading();
            }

            @Override
            public void onComplete() {
                hideLoading();
            }

            @Override
            public void onSuccess(DetailListModel detailListModel) {
                List<DetailListBaseModel.ResultBean.ListBean> list = detailListModel.getResult().getList();
                handleData(list);
            }

            @Override
            public void onFail(String e) {
                hideLoading();
                LogUtils.d(TAG,e);
            }

            @Override
            public void onError(Throwable e) {
                hideLoading();
                LogUtils.d(TAG,e.toString());
            }
        });

    }


    public static ListDetailFragment newInstance(String key, int position) {
        ListDetailFragment fragment = new ListDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString("key", key);
        bundle.putInt("position", position);
        fragment.setArguments(bundle);
        return fragment;
    }

}
