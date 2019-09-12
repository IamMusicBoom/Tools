package com.wma.tools.horoscope;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.view.ViewGroup;

import com.wma.tools.R;
import com.wma.tools.databinding.FragmentWordDetailBinding;
import com.wma.tools.databinding.ItemDicationaryDetailBinding;
import com.wma.tools.model.horoscope.DetailBaseModel;
import com.wma.tools.model.horoscope.DetailModel;
import com.wma.wmalib.base.fragment.BaseFragment;
import com.wma.wmalib.base.fragment.BaseListFragment;
import com.wma.wmalib.callback.HttpCallBack;
import com.wma.wmalib.common.LogUtils;
import com.wma.wmalib.utils.DpUtils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王明骜 on 19-9-6 上午11:06.
 */
public class DetailFragment extends BaseListFragment<String, ItemDicationaryDetailBinding,FragmentWordDetailBinding> {
    private int mCurType;
    public static final int ID_TYPE = 0;
    public static final int WORD_TYPE = 1;
    WeakReference<FragmentWordDetailBinding> mWeakBinding;
    private List<String> mList = new ArrayList<>();
    @Override
    protected void createContentView(ViewGroup container, FragmentWordDetailBinding binding) {
        mWeakBinding = new WeakReference<>(binding);
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.fragment_word_detail;
    }

    @Override
    public void create(Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        mCurType = arguments.getInt("type");
        String key = arguments.getString("key");
        mRecyclerView = mWeakBinding.get().recyclerView;
        switch (mCurType){
            case ID_TYPE:
                getDataById(key);
                break;
            case WORD_TYPE:
                getDataByWord(key);
                break;
        }
    }

    private void getDataByWord(String key) {
        new DetailModel().getDataByWord(key, new HttpCallBack<DetailModel>() {
            @Override
            public void onBegin() {
                showLoading();
            }

            @Override
            public void onComplete() {
                hideLoading();
            }

            @Override
            public void onSuccess(DetailModel detailModel) {
                LogUtils.d(TAG,detailModel.getResult().getZi());
                DetailBaseModel.ResultBean result = detailModel.getResult();
                handleList(result);
            }

            @Override
            public void onFail(String e) {
                hideLoading();
                LogUtils.d(TAG,e);
                showEmptyView(true);
            }

            @Override
            public void onError(Throwable e) {
                hideLoading();
                LogUtils.d(TAG,e.toString());
                showEmptyView(true);
            }
        });
    }

    private void getDataById(String key) {
        new DetailModel().getDataById(key, new HttpCallBack<DetailModel>() {
            @Override
            public void onBegin() {
                showLoading();
            }

            @Override
            public void onComplete() {
                hideLoading();
            }

            @Override
            public void onSuccess(DetailModel detailModel) {
                LogUtils.d(TAG,detailModel.getResult().getZi());
                DetailBaseModel.ResultBean result = detailModel.getResult();
                handleList(result);

            }

            @Override
            public void onFail(String e) {
                hideLoading();
                LogUtils.d(TAG,e);
                showEmptyView(true);
            }

            @Override
            public void onError(Throwable e) {
                hideLoading();
                LogUtils.d(TAG,e.toString());
                showEmptyView(true);
            }
        });
    }

    private void handleList(DetailBaseModel.ResultBean result) {
        mList.add("简介");
        mList.addAll(result.getJijie());
        mList.add("详细介绍");
        mList.addAll(result.getXiangjie());
        handleData(mList);
        mWeakBinding.get().tvBihua.setText(result.getBihua() + " 画");
        mWeakBinding.get().tvBushou.setText("部首：" +result.getBushou());
        mWeakBinding.get().tvPinYin.setText(result.getPinyin());
        mWeakBinding.get().tvZi.setText(result.getZi());
    }


    public static DetailFragment newInstance(int type,String key){
        DetailFragment fragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type",type);
        bundle.putString("key",key);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected View getEmptyView() {
        return mWeakBinding.get().emptyView;
    }

    @Override
    public void getListData() {

    }

    @Override
    public int getItemLayout(int viewType) {
        return R.layout.item_dicationary_detail;
    }

    @Override
    public void bindItemData(ItemDicationaryDetailBinding binding, String info, int position) {
        if(info.equals("简介") || info.equals("详细介绍")){
            binding.tvDetail.setTextSize(20);
            binding.tvDetail.setTextColor(Color.BLACK);
            TextPaint paint = binding.tvDetail.getPaint();
            paint.setFakeBoldText(true);
        }else{
            binding.tvDetail.setTextSize(15);
            binding.tvDetail.setTextColor(Color.DKGRAY);
            TextPaint paint = binding.tvDetail.getPaint();
            paint.setFakeBoldText(false);
        }
        binding.tvDetail.setText(info);

    }
}
