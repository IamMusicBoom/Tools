package com.wma.tools;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import com.wma.tools.databinding.ActivityMainBinding;
import com.wma.tools.horoscope.HoroscopeFragment;
import com.wma.tools.me.MeFragment;
import com.wma.tools.news.NewsFragment;
import com.wma.tools.weather.WeatherFragment;
import com.wma.wmalib.base.BaseTabActivity;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseTabActivity<ActivityMainBinding> {
    ActivityMainBinding mBinding;





    @Override
    public List<Fragment> getFragments() {
        List<Fragment> list = new ArrayList<>();
        list.add(new NewsFragment());
        list.add(new WeatherFragment());
        list.add(new HoroscopeFragment());
        list.add(new MeFragment());
        return list;
    }
    @Override
    public ViewPager getViewPager() {
        return mBinding.viewpager;
    }

    @Override
    public ViewGroup getIndicatorGroup() {
        return null;
    }


    @Override
    public void onCreate(Bundle savedInstanceState, ActivityMainBinding binding) {
        mBinding = binding;
        initViews();
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_main;
    }
}
