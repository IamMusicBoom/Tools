package com.wma.tools.news;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.widget.TextView;

import com.wma.tools.R;
import com.wma.tools.databinding.ActivityPictureDetailBinding;
import com.wma.wmalib.base.activity.BaseActivity;
import com.wma.wmalib.widget.WViewPager;

import java.util.ArrayList;

public class PictureDetailActivity extends BaseActivity<ActivityPictureDetailBinding> {

    private static final String STATE_POSITION = "STATE_POSITION";
    public static final String EXTRA_IMAGE_INDEX = "image_index";
    public static final String EXTRA_IMAGE_URLS = "image_urls";
    public static final String EXTRA_IMAGE_URL = "image_url";
    ActivityPictureDetailBinding mBinding;
    private WViewPager mPager;
    private int pagerPosition;
    private TextView indicator;
    @Override
    public void onCreate(Bundle savedInstanceState, ActivityPictureDetailBinding binding) {
        mBinding = binding;

        pagerPosition = getIntent().getIntExtra(EXTRA_IMAGE_INDEX, 0);
        ArrayList<String> urls = getIntent().getStringArrayListExtra(EXTRA_IMAGE_URLS);
        String url = getIntent().getStringExtra(EXTRA_IMAGE_URL);

        if (urls == null && !TextUtils.isEmpty(url)) {
            urls = new ArrayList<>();
            urls.add(url);
        }
        mPager = mBinding.pager;
        ImagePagerAdapter mAdapter = new ImagePagerAdapter(getSupportFragmentManager(), urls);
        mPager.setAdapter(mAdapter);
        indicator = mBinding.indicator;

        CharSequence text = getString(R.string.viewpager_indicator, pagerPosition+1, mPager.getAdapter().getCount());
        indicator.setText(text);

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageSelected(int arg0) {
                pagerPosition = arg0;
                CharSequence text = getString(R.string.viewpager_indicator, pagerPosition + 1, mPager.getAdapter().getCount());
                indicator.setText(text);
            }

        });
        if (savedInstanceState != null) {
            pagerPosition = savedInstanceState.getInt(STATE_POSITION);
        }

        mPager.setCurrentItem(pagerPosition);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_POSITION, mPager.getCurrentItem());
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_picture_detail;
    }

    private class ImagePagerAdapter extends FragmentStatePagerAdapter {

        public ArrayList<String> fileList;

        public ImagePagerAdapter(FragmentManager fm, ArrayList<String> fileList) {
            super(fm);
            this.fileList = fileList;
        }

        @Override
        public int getCount() {
            return fileList == null ? 0 : fileList.size();
        }

        @Override
        public Fragment getItem(int position) {
            String url = fileList.get(position);
            return PictureDetailFragment.newInstance(url);

        }
    }
}
