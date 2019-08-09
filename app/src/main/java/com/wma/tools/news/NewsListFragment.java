package com.wma.tools.news;

import android.os.Bundle;
import android.os.Looper;
import android.support.design.widget.TabLayout;
import android.util.Log;

import com.wma.tools.R;
import com.wma.tools.databinding.FragmentNewsBinding;
import com.wma.tools.databinding.FragmentNewsListBinding;
import com.wma.tools.model.BModel;
import com.wma.tools.model.news.NewsModel;
import com.wma.wmalib.base.BaseFragment;
import com.wma.wmalib.base.BaseListFragment;
import com.wma.wmalib.callback.HttpCallBack;

/**
 * Created by 王明骜 on 19-8-9 下午2:12.
 */
public class NewsListFragment extends BaseListFragment<FragmentNewsListBinding> {
    FragmentNewsListBinding mBinding;
    FragmentNewsBinding mParentBinding;
    NewsFragment mParent;
    @Override
    public int getContentLayoutId() {
        return R.layout.fragment_news_list;
    }

    @Override
    public void onCreate(Bundle savedInstanceState, FragmentNewsListBinding binding) {
        mBinding = binding;
        mParent = ((NewsFragment) getParentFragment());
        mParentBinding = ((NewsFragment) getParentFragment()).mBinding;
    }

    private void goGetDatas(String s) {
        new NewsModel().getDatas(s, new HttpCallBack<BModel.ResultBean>() {
            @Override
            public void onBegin() {
//                Log.d("WMA-WMA", "onBegin: ");
                showLoading();
            }

            @Override
            public void onComplete() {
//                Log.d("WMA-WMA", "onComplete: ");
                hideLoading();
            }

            @Override
            public void onSuccess(BModel.ResultBean resultBean) {
//                Log.d("WMA-WMA", "onSuccess: ");
                mBinding.tv.setText(resultBean.getData().get(0).getTitle());
            }

            @Override
            public void onFail(String e) {
//                Log.d("WMA-WMA", "onFail: " + e);
                mBinding.tv.setText(e);

            }

            @Override
            public void onError(Throwable e) {
//                Log.d("WMA-WMA", "onError: " + e);

            }
        });
    }

    @Override
    protected void loadData() {
        Log.d("WMA-WMA", "loadData: " + this);
        String s = mParent.keyType.get(mParent.adapter.getPageTitle(mParentBinding.viewpager.getCurrentItem()));
        goGetDatas(s);
    }
}
