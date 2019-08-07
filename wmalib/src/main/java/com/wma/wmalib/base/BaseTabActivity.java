package com.wma.wmalib.base;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by 王明骜 on 19-8-7 上午10:18.
 */
public abstract class BaseTabActivity<T extends ViewDataBinding> extends BaseActivity<T> {
    public MyAdapter adapter;

    public abstract List<Fragment> getFragments();

    public abstract ViewPager getViewPager();

    public abstract ViewGroup getIndicatorGroup();


    @Override
    public void onCreate(Bundle savedInstanceState, T binding) {

    }

    public void initViews() {
        if (adapter == null) {
            adapter = new MyAdapter(getSupportFragmentManager(), getFragments());
        }
        getViewPager().setAdapter(adapter);
    }

    class MyAdapter extends BaseFragmentPagerAdapter {
        List<Fragment> fragments;

        public MyAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public List<Fragment> getFragments() {
            return fragments;
        }
    }

}
