package com.wma.tools.horoscope;

import android.os.Bundle;
import android.view.ViewGroup;

import com.wma.tools.R;
import com.wma.tools.databinding.FragmentHoroscopeBinding;
import com.wma.wmalib.base.fragment.BaseFragment;

/**
 * Created by 王明骜 on 19-8-7 上午10:59.
 */
public class HoroscopeFragment extends BaseFragment<FragmentHoroscopeBinding> {
    @Override
    protected void createContentView(ViewGroup container, FragmentHoroscopeBinding binding) {

    }

    @Override
    public int getContentLayoutId() {
        return R.layout.fragment_horoscope;
    }

    @Override
    public void create(Bundle savedInstanceState) {

    }

}
