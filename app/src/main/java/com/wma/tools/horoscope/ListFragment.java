package com.wma.tools.horoscope;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wma.tools.R;
import com.wma.tools.databinding.FragmentPinYinBinding;
import com.wma.tools.databinding.ItemPinYinBinding;
import com.wma.tools.model.horoscope.BuShouBaseModel;
import com.wma.tools.model.horoscope.BuShouModel;
import com.wma.tools.model.horoscope.DataModel;
import com.wma.tools.model.horoscope.PinYinBaseModel;
import com.wma.tools.model.horoscope.PinYinModel;
import com.wma.tools.utils.SPUtils;
import com.wma.wmalib.base.fragment.BaseListFragment;
import com.wma.wmalib.callback.HttpCallBack;
import com.wma.wmalib.common.LogUtils;
import com.wma.wmalib.utils.FileUtils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王明骜 on 19-8-7 上午10:59.
 */
public class ListFragment extends BaseListFragment<DataModel, ItemPinYinBinding, FragmentPinYinBinding> {
    private final String TAG = this.getClass().getSimpleName();
    WeakReference<FragmentPinYinBinding> mWeakBinding;
    DictionaryFragment mParent;
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
        int position = arguments.getInt("position");
        mParent = (DictionaryFragment) getParentFragment();
        mRecyclerView = (RecyclerView) ((ViewGroup) mWeakBinding.get().getRoot()).getChildAt(0);
        getData(position);

    }

    @Override
    protected void lazyLoad() {

        getListData();
    }

    private void getData(int position) {
        switch (position){
            case 1:
                getBuShou();
                break;
            case 2:
                getPinYin();
                break;
        }
    }

    private void getBuShou() {
        if (!SPUtils.isLoadBuShou()) {
            Log.d("WMA-WMA", "getBuShou: from web");
            new BuShouModel().getDatas(new HttpCallBack<BuShouModel>() {
                @Override
                public void onBegin() {
                    showLoading();
                }

                @Override
                public void onComplete() {
                    hideLoading();
                }

                @Override
                public void onSuccess(BuShouModel pinYinModel) {
                    List<BuShouModel.ResultBean> result = pinYinModel.getResult();
                    List<DataModel> list = new ArrayList<>();
                    for (int i = 0; i < result.size(); i++) {
                        BuShouBaseModel.ResultBean resultBean = result.get(i);
                        list.add(new DataModel(resultBean.getId(),resultBean.getBihua(),resultBean.getBushou()));
                    }
                    handleData(list);
                    FileUtils.write(getContext(),"BuShou",new Gson().toJson(list));
                    SPUtils.setIsLoadBuShou(true);
                }

                @Override
                public void onFail(String e) {
                    showEmptyView(true);
                    LogUtils.d(TAG, e);
                }

                @Override
                public void onError(Throwable e) {
                    hideLoading();
                    LogUtils.d(TAG, e.toString());
                }
            });
        }else{
            Log.d("WMA-WMA", "getBuShou: from local");
            String buShou = FileUtils.read(getContext(), "BuShou");
            List<DataModel> list  =  new Gson().fromJson(buShou, new TypeToken<List<DataModel>>() {
            }.getType());
            handleData(list);
        }
    }

    private void getPinYin() {
        if (!SPUtils.isLoadPinYin()) {
            new PinYinModel().getDatas(new HttpCallBack<PinYinModel>() {
                @Override
                public void onBegin() {
                    showLoading();
                }

                @Override
                public void onComplete() {
                    hideLoading();
                }

                @Override
                public void onSuccess(PinYinModel pinYinModel) {
                    List<PinYinModel.ResultBean> result = pinYinModel.getResult();
                    List<DataModel> list = new ArrayList<>();
                    for (int i = 0; i < result.size(); i++) {
                        PinYinBaseModel.ResultBean resultBean = result.get(i);
                        list.add(new DataModel(resultBean.getId(),resultBean.getPinyin_key(),resultBean.getPinyin()));
                    }
                    handleData(list);
                    FileUtils.write(getContext(),"PinYin",new Gson().toJson(list));
                    SPUtils.setIsLoadPinYin(true);
                }

                @Override
                public void onFail(String e) {
                    showEmptyView(true);
                    LogUtils.d(TAG, e);
                }

                @Override
                public void onError(Throwable e) {
                    hideLoading();
                    LogUtils.d(TAG, e.toString());
                }
            });
        }else{
            String pinYin = FileUtils.read(getContext(), "PinYin");
            List<DataModel> list  =  new Gson().fromJson(pinYin, new TypeToken<List<DataModel>>() {
            }.getType());
            handleData(list);
        }
    }

    @Override
    protected View getEmptyView() {
        return ((ViewGroup) mWeakBinding.get().getRoot()).getChildAt(1);
    }

    @Override
    public void getListData() {

    }

    @Override
    public int getItemLayout(int viewType) {
        return R.layout.item_pin_yin;
    }

    @Override
    public void bindItemData(ItemPinYinBinding itemPinYinBinding, DataModel info, int position) {
        itemPinYinBinding.tvItem.setText(info.getValue());
        itemPinYinBinding.tvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    public static ListFragment newInstance(int position){
        ListFragment listFragment = new ListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position",position);
        listFragment.setArguments(bundle);
        return listFragment;
    }
}
