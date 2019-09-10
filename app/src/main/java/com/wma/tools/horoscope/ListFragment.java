package com.wma.tools.horoscope;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
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
import com.wma.wmalib.base.adapter.BaseRecyclerViewAdapter;
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
    WeakReference<FragmentPinYinBinding> mWeakBinding;
    DictionaryFragment mParent;
    List<DataModel> mList;

//    int mCurPos;
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

        int mCurPos = arguments.getInt("position");
        mParent = (DictionaryFragment) getParentFragment();
        mRecyclerView = (RecyclerView) ((ViewGroup) mWeakBinding.get().getRoot()).getChildAt(0);
        mRecyclerView.addItemDecoration(new CeilingItemDecoration(getActivity(), new CeilingItemDecoration.GroupController() {
            @Override
            public String getGroupName(int pos) {
                return mList.get(pos).getKey();
            }
        }));
        getData(mCurPos);
    }

    @Override
    protected void lazyLoad() {

        getListData();
    }

    private void getData(int position) {
        switch (position){
            case DictionaryKindActivity.BU_SHOU:
                getBuShou();
                break;
            case DictionaryKindActivity.PIN_YIN:
                getPinYin();
                break;
        }
    }

    private void getBuShou() {
        if (!SPUtils.isLoadBuShou()) {
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
                    mList = new ArrayList<>();
                    for (int i = 0; i < result.size(); i++) {
                        BuShouBaseModel.ResultBean resultBean = result.get(i);
                        mList.add(new DataModel(resultBean.getId(),resultBean.getBihua()+" 画",resultBean.getBushou()));
                    }
                    handleData(mList);
                    FileUtils.write(getContext(),"BuShou",new Gson().toJson(mList));
                    SPUtils.setIsLoadBuShou(true);
                    getAdapter().setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener<DataModel>() {
                        @Override
                        public void onItemClick(View covertView, int position, DataModel data) {
                            ((DictionaryKindActivity) getActivity()).goNext(data.getValue(),data.getValue());
                        }
                    });
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
            String buShou = FileUtils.read(getContext(), "BuShou");
            mList =  new Gson().fromJson(buShou, new TypeToken<List<DataModel>>() {
            }.getType());
            handleData(mList);
            getAdapter().setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener<DataModel>() {
                @Override
                public void onItemClick(View covertView, int position, DataModel data) {
                    ((DictionaryKindActivity) getActivity()).goNext(data.getValue(),data.getValue());
                }
            });
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
                     mList = new ArrayList<>();
                    for (int i = 0; i < result.size(); i++) {
                        PinYinBaseModel.ResultBean resultBean = result.get(i);
                        mList.add(new DataModel(resultBean.getId(),resultBean.getPinyin_key(),resultBean.getPinyin()));
                    }
                    handleData(mList);
                    FileUtils.write(getContext(),"PinYin",new Gson().toJson(mList));
                    SPUtils.setIsLoadPinYin(true);
                    getAdapter().setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener<DataModel>() {
                        @Override
                        public void onItemClick(View covertView, int position, DataModel data) {
                            ((DictionaryKindActivity) getActivity()).goNext(data.getValue(),data.getValue());
                        }
                    });
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
            mList =  new Gson().fromJson(pinYin, new TypeToken<List<DataModel>>() {
            }.getType());
            handleData(mList);
            getAdapter().setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener<DataModel>() {
                @Override
                public void onItemClick(View covertView, int position, DataModel data) {
                    ((DictionaryKindActivity) getActivity()).goNext(data.getValue(),data.getValue());
                }
            });
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
    }


    public static ListFragment newInstance(int position){
        ListFragment listFragment = new ListFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position",position);
        listFragment.setArguments(bundle);
        return listFragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mAdapter = null;
        mParent = null;
        mRecyclerView = null;
        if(mList != null){
            mList.clear();
            mList = null;
        }
    }
}
