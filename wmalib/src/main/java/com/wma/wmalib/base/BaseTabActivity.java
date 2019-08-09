package com.wma.wmalib.base;

import android.databinding.ViewDataBinding;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import java.util.List;

/**
 * Created by 王明骜 on 19-8-7 上午10:18.
 */
public abstract class BaseTabActivity<T extends ViewDataBinding> extends BaseActivity<T> {
    public BaseFragmentPagerAdapter adapter;

    public abstract List<Fragment> getFragments();

    public abstract ViewPager getViewPager();

    public abstract TabLayout getTabLayout();

    public abstract List<String> getTitles();

    public abstract List<Drawable> getIcons();


    public void initViews() {
        if (adapter == null) {
            adapter = new BaseFragmentPagerAdapter(getSupportFragmentManager(), getFragments(), getTitles());
        }
        getViewPager().setAdapter(adapter);


        getTabLayout().setupWithViewPager(getViewPager());
        getTabLayout().removeAllTabs();
        for (int i = 0; i < getFragments().size(); i++) {
            TabLayout.Tab tab = getTabLayout().newTab();
            tab.setText(getTitles().get(i));
            tab.setIcon(getIcons().get(i));
            getTabLayout().addTab(tab);
        }

    }
}
