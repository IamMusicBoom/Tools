package com.wma.tools;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.wma.tools.databinding.ActivityMainBinding;
import com.wma.wmalib.base.BaseActivity;



public class MainActivity extends BaseActivity<ActivityMainBinding> {


    @Override
    public void onCreate(Bundle savedInstanceState, ActivityMainBinding binding) {

    }


    @Override
    public int getContentLayoutId() {
        return R.layout.activity_main;
    }
}
