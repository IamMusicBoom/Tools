package com.wma.wmalib.base.fragment;

import android.databinding.ViewDataBinding;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.wma.wmalib.base.adapter.BaseFragmentPagerAdapter;

import java.util.List;

/**
 * Created by 王明骜 on 19-8-9 下午2:03.
 */
public abstract class BaseTabFragment<T extends ViewDataBinding> extends BaseFragment<T> {
    public BaseFragmentPagerAdapter mAdapter;

    public abstract List<Fragment> getFragments();

    public abstract ViewPager getViewPager();

    public abstract TabLayout getTabLayout();

    public abstract List<String> getTitles();

    public abstract List<Drawable> getIcons();


    public void initViews() {
        if (mAdapter == null) {
            mAdapter = new BaseFragmentPagerAdapter(getChildFragmentManager(), getFragments(), getTitles());
        }
        getViewPager().setAdapter(mAdapter);


        getTabLayout().setupWithViewPager(getViewPager());
        getTabLayout().removeAllTabs();
        for (int i = 0; i < getFragments().size(); i++) {
            TabLayout.Tab tab = getTabLayout().newTab();
            if(getTitles() != null){
                tab.setText(getTitles().get(i));
            }
            if(getIcons()!=null){
                tab.setIcon(getIcons().get(i));
            }
            getTabLayout().addTab(tab);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mAdapter = null;
    }
}
