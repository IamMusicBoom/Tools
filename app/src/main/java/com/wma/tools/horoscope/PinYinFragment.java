package com.wma.tools.horoscope;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.wma.tools.R;
import com.wma.tools.databinding.FragmentPinYinBinding;
import com.wma.tools.databinding.ItemPinYinBinding;
import com.wma.tools.model.horoscope.Model;
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
public class PinYinFragment extends BaseListFragment<PinYinModel.ResultBean, ItemPinYinBinding, FragmentPinYinBinding> {
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
        mParent = (DictionaryFragment) getParentFragment();
        setLeftText("返回", R.drawable.ic_back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mParent.back();
            }
        });
        mRecyclerView = (RecyclerView) ((ViewGroup) mWeakBinding.get().getRoot()).getChildAt(0);

    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        getListData();
    }

    private void getData() {
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
                    handleData(result);
                    FileUtils.write(getContext(),"PinYin",new Gson().toJson(pinYinModel));
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
            Model pinYinModel = new Gson().fromJson(pinYin, Model.class);
            List<Model.ResultBean> result = pinYinModel.getResult();
            handleData(result);
        }
    }

    @Override
    protected View getEmptyView() {
        return ((ViewGroup) mWeakBinding.get().getRoot()).getChildAt(1);
    }

    @Override
    public void getListData() {
        getData();
    }

    @Override
    public int getItemLayout(int viewType) {
        return R.layout.item_pin_yin;
    }

    @Override
    public void bindItemData(ItemPinYinBinding itemPinYinBinding, PinYinModel.ResultBean info, int position) {
        itemPinYinBinding.tvItem.setText(info.getPinyin());
        itemPinYinBinding.tvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mParent.goNext();
            }
        });
    }


}
