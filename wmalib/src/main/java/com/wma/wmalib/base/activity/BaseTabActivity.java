package com.wma.wmalib.base.activity;

import android.databinding.ViewDataBinding;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.wma.wmalib.base.adapter.BaseFragmentPagerAdapter;

import java.util.List;

/**
 * Created by 王明骜 on 19-8-7 上午10:18.
 */
public abstract class BaseTabActivity<T extends ViewDataBinding> extends BaseActivity<T> {
    public BaseFragmentPagerAdapter mAdapter;

    public abstract List<Fragment> getFragments();

    public abstract ViewPager getViewPager();

    public abstract TabLayout getTabLayout();

    public abstract List<String> getTitles();

    public abstract List<Drawable> getIcons();


    public void initViews() {
        if (mAdapter == null) {
            mAdapter = new BaseFragmentPagerAdapter(getSupportFragmentManager(), getFragments(), getTitles());
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
    protected void onDestroy() {
        super.onDestroy();
        mAdapter =null;
    }
}
