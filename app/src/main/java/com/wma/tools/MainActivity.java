package com.wma.tools;


import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.wma.tools.databinding.ActivityMainBinding;
import com.wma.tools.horoscope.HoroscopeFragment;
import com.wma.tools.me.MeFragment;
import com.wma.tools.news.NewsFragment;
import com.wma.tools.weather.WeatherFragment;
import com.wma.wmalib.base.BaseFragmentPagerAdapter;
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
    public TabLayout getTabLayout() {
        return mBinding.tabLayout;
    }

    @Override
    public List<String> getTitles() {
        List<String> list = new ArrayList<>();
        list.add(getResources().getString(R.string.news));
        list.add(getResources().getString(R.string.weather));
        list.add(getResources().getString(R.string.horoscope));
        list.add(getResources().getString(R.string.me));
        return list;
    }

    @Override
    public List<Drawable> getIcons() {
        List<Drawable> list = new ArrayList<>();
        list.add(getResources().getDrawable(R.drawable.home_new));
        list.add(getResources().getDrawable(R.drawable.home_weather));
        list.add(getResources().getDrawable(R.drawable.home_horoscope));
        list.add(getResources().getDrawable(R.drawable.home_me));
        return list;
    }


    @Override
    public void onCreate(Bundle savedInstanceState, ActivityMainBinding binding) {
        mBinding = binding;
        initViews();
        mBinding.viewpager.setOffscreenPageLimit(2);
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_main;
    }
}
