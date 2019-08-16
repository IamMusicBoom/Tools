package com.wma.tools.me;

import android.os.Bundle;
import android.view.ViewGroup;

import com.wma.tools.R;
import com.wma.tools.databinding.FragmentMeBinding;
import com.wma.wmalib.base.fragment.BaseFragment;

/**
 * Created by 王明骜 on 19-8-7 上午11:03.
 */
public class MeFragment extends BaseFragment<FragmentMeBinding> {
    @Override
    protected void createContentView(ViewGroup container, FragmentMeBinding binding) {

    }

    @Override
    public int getContentLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    public void create(Bundle savedInstanceState) {

    }

}
