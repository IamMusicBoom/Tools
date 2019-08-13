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
        String url = "https://06imgmini.eastday.com/mobile/20190809/2019080908_7d70064570134db9b069b736af3fcc19_0811_mwpm_03200403.jpg";
        GlideUtil.getInstance().loadImage(ToolApplication.getInstance(),binding.img,url,false);
    }
}
