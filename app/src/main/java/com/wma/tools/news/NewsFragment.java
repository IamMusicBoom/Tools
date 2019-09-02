package com.wma.tools.news;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import com.wma.tools.R;
import com.wma.tools.databinding.FragmentNewsBinding;
import com.wma.tools.model.IAllApi;
import com.wma.tools.utils.Common;
import com.wma.wmalib.base.fragment.BaseTabFragment;
import com.wma.wmalib.http.HttpUtils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王明骜 on 19-8-6 下午3:48.
 */
public class NewsFragment extends BaseTabFragment<FragmentNewsBinding> {
    public WeakReference<FragmentNewsBinding> mWeakBinding;
    String titles[] = {"头条", "社会", "国内", "国际", "娱乐", "体育", "军事", "科技", "财经", "时尚"};

    @Override
    protected void createContentView(ViewGroup container, FragmentNewsBinding binding) {
        mWeakBinding = new WeakReference<>(binding);
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    public void create(Bundle savedInstanceState) {
        HttpUtils.httpUtils.init(IAllApi.NEWS_HOST);



    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
        initViews();
    }

    @Override
    public List<Fragment> getFragments() {
        List<Fragment> list = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            list.add(NewsListFragment.newInstance(Common.keyType.get(titles[i])));
        }
        return list;
    }

    @Override
    public ViewPager getViewPager() {
        return mWeakBinding.get().viewpager;
    }

    @Override
    public TabLayout getTabLayout() {
        return mWeakBinding.get().tabLayout;
    }

    @Override
    public List<String> getTitles() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            list.add(titles[i]);
        }
        return list;
    }

    @Override
    public List<Drawable> getIcons() {
        return null;
    }


}
