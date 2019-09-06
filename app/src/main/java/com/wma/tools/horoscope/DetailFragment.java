package com.wma.tools.horoscope;

import android.os.Bundle;
import android.view.ViewGroup;

import com.wma.tools.R;
import com.wma.tools.databinding.FragmentWordDetailBinding;
import com.wma.wmalib.base.fragment.BaseFragment;

/**
 * Created by 王明骜 on 19-9-6 上午11:06.
 */
public class DetailFragment extends BaseFragment<FragmentWordDetailBinding> {
    @Override
    protected void createContentView(ViewGroup container, FragmentWordDetailBinding binding) {

    }

    @Override
    public int getContentLayoutId() {
        return R.layout.fragment_word_detail;
    }

    @Override
    public void create(Bundle savedInstanceState) {

    }
}
