package com.wma.tools.news;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.wma.tools.R;
import com.wma.wmalib.base.activity.BaseWebViewActivity;
import com.wma.wmalib.databinding.ActivityWebviewBinding;

public class NewsDetailActivity extends BaseWebViewActivity {

    String url = "";
    @Override
    public void onCreate(Bundle savedInstanceState, ActivityWebviewBinding activityWebviewBinding) {
        super.onCreate(savedInstanceState, activityWebviewBinding);
        setLeftText("返回", R.drawable.ic_back, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        url =  getIntent().getStringExtra("url");
        if(!TextUtils.isEmpty(url)){
            url = url.replace("http://","https://");
        }
    }

    @Override
    protected String getUrl() {
        return url;
    }
}
