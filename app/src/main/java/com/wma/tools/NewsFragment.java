package com.wma.tools;

import android.os.Bundle;

import com.wma.tools.databinding.FragmentNewsBinding;
import com.wma.wmalib.base.BaseFragment;

/**
 * Created by 王明骜 on 19-8-6 下午3:48.
 */
public class NewsFragment extends BaseFragment<FragmentNewsBinding> {

    @Override
    public int getContentLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    public void onCreate(Bundle savedInstanceState, FragmentNewsBinding binding) {
        setTitle("ddd",null);
    }
}
