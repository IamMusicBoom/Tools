package com.wma.tools.me;

import android.os.Bundle;

import com.wma.tools.R;
import com.wma.tools.databinding.FragmentMeBinding;
import com.wma.wmalib.base.fragment.BaseFragment;

/**
 * Created by 王明骜 on 19-8-7 上午11:03.
 */
public class MeFragment extends BaseFragment<FragmentMeBinding> {
    @Override
    public int getContentLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    public void onCreate(Bundle savedInstanceState, FragmentMeBinding binding) {

    }
}
