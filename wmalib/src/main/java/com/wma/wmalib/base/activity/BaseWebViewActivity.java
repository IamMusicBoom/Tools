package com.wma.wmalib.base.activity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.wma.wmalib.R;
import com.wma.wmalib.databinding.ActivityWebviewBinding;

/**
 * Created by 王明骜 on 19-8-12 下午5:05.
 */
public abstract class BaseWebViewActivity extends BaseActivity<ActivityWebviewBinding> {

    private String _title;
    private boolean _firstResume = true;
    private boolean _loadSuccess = false;
    ActivityWebviewBinding binding;



    @Override
    public void onCreate(Bundle savedInstanceState, ActivityWebviewBinding activityWebviewBinding) {
        binding = activityWebviewBinding;
        //FIXME  设置左右按钮

        WebSettings settings = binding.webView.getSettings();
        binding.webView.setBackgroundColor(0);
        settings.setUseWideViewPort(true);
        binding.webView.setBackgroundColor(Color.TRANSPARENT);
//        if (Build.VERSION.SDK_INT >= 19) {//硬件加速器的使用
//            mWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
//        } else {
//            mWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//        }
        settings.setLoadWithOverviewMode(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        binding.webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        settings.setJavaScriptEnabled(true);
        binding.webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
//                showLoading("正在加载页面…");
                _loadSuccess = true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
//                hideLoading();

                if (_loadSuccess) {
                    if (_title == null) {
                        setTitle(view.getTitle());
                    }
                } else {
                    //ToastManager.manager.show(WebViewActivity.this, "加载页面失败, 请刷新重试");
                }
            }

            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                _loadSuccess = false;
            }
        });
    }

    @Override
    public int getContentLayoutId() {
        return R.layout.activity_webview;
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (_firstResume) {
            binding.webView.loadUrl(getUrl());
            _firstResume = false;
        }
    }

    public void onBackPressed() {
        if (binding.webView.canGoBack()) {
            binding.webView.goBack();
        } else {
            goBack();
        }
    }

    private void goBack() {
        finish();
    }

    protected abstract String getUrl();
}
