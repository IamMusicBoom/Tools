package com.wma.tools;


import android.os.Bundle;
import com.wma.tools.databinding.ActivityMainBinding;
import com.wma.wmalib.WMABaseActivity;



public class MainActivity extends WMABaseActivity<ActivityMainBinding> {


    @Override
    public void onCreate(Bundle savedInstanceState, ActivityMainBinding binding) {

    }


    @Override
    public int getContentLayoutId() {
        return R.layout.activity_main;
    }
}
