package com.wma.tools.weather;

import android.os.Bundle;

import com.wma.tools.R;
import com.wma.tools.ToolApplication;
import com.wma.tools.databinding.FragmentWeatherBinding;
import com.wma.wmalib.base.fragment.BaseFragment;
import com.wma.wmalib.glide.GlideUtil;

/**
 * Created by 王明骜 on 19-8-7 上午11:02.
 */
public class WeatherFragment extends BaseFragment<FragmentWeatherBinding> {
    @Override
    public int getContentLayoutId() {
        return R.layout.fragment_weather;
    }

    @Override
    public void onCreate(Bundle savedInstanceState, FragmentWeatherBinding binding) {

    }
}
