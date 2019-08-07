package com.wma.wmalib.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by 王明骜 on 19-8-7 上午11:08.
 */
public abstract class BaseFragmentPagerAdapter extends FragmentPagerAdapter {
    public BaseFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return getFragments() != null ? getFragments().get(i) : null;
    }

    @Override
    public int getCount() {
        return getFragments() != null ? getFragments().size() : 0;
    }

    public abstract List<Fragment> getFragments();
}
