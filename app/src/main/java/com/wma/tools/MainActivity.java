package com.wma.tools;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.wma.tools.databinding.ActivityMainBinding;
import com.wma.tools.horoscope.DictionaryFragment;
import com.wma.tools.me.MeFragment;
import com.wma.tools.news.NewsFragment;
import com.wma.tools.weather.WeatherFragment;
import com.wma.wmalib.base.activity.BaseTabActivity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseTabActivity<ActivityMainBinding> {
    WeakReference<ActivityMainBinding> mWeakBinding;
    @Override
    public List<Fragment> getFragments() {
        List<Fragment> list = new ArrayList<>();
        list.add(new NewsFragment());
        list.add(new WeatherFragment());
        list.add(new DictionaryFragment());
        list.add(new MeFragment());
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
        mWeakBinding = new WeakReference<>(binding);
        initViews();
        mWeakBinding.get().viewpager.setOffscreenPageLimit(3);
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
