package com.wma.wmalib.base.activity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
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
    private boolean _loadSuccess = false;
    ActivityWebviewBinding binding;

    View mEmptyView;

    @Override
    public void onCreate(Bundle savedInstanceState, ActivityWebviewBinding activityWebviewBinding) {
        binding = activityWebviewBinding;
        //FIXME  设置左右按钮

        WebSettings settings = binding.webView.getSettings();
        binding.webView.setBackgroundColor(0);
        mEmptyView = findViewById(R.id.empty_view);
        settings.setUseWideViewPort(true);
        binding.webView.setBackgroundColor(Color.TRANSPARENT);
//        if (Build.VERSION.SDK_INT >= 19) {//硬件加速器的使用
//            mWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
//        } else {
//            mWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
//        }
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);

        /* 设置WebView是否可以由JavaScript自动打开窗口，默认为false，通常与JavaScript的window.open()配合使用。*/
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        /* 设置缓存模式,我这里使用的默认,不做多讲解 */
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        /* 设置为true表示支持使用js打开新的窗口 */
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        /* 大部分网页需要自己保存一些数据,这个时候就的设置下面这个属性 */
        settings.setDomStorageEnabled(true);
        /* 设置为使用webview推荐的窗口 */
        settings.setUseWideViewPort(true);
        /* 设置网页自适应屏幕大小 ---这个属性应该是跟上面一个属性一起用 */
        settings.setLoadWithOverviewMode(true);
        /* HTML5的地理位置服务,设置为true,启用地理定位 */
        settings.setGeolocationEnabled(true);
        /* 设置是否允许webview使用缩放的功能,我这里设为false,不允许 */
        settings.setBuiltInZoomControls(false);
        /* 提高网页渲染的优先级 */
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);

        binding.webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        binding.webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                _loadSuccess = true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                if(_loadSuccess){
                }else{
                    if (mEmptyView.getVisibility() == View.GONE) {
                        mEmptyView.setVisibility(View.VISIBLE);
                        binding.webView.setVisibility(View.GONE);
                    }
                }

            }

            @Override
            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                _loadSuccess = false;
            }
        });

        mEmptyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.webView.loadUrl(getUrl());
                mEmptyView.setVisibility(View.GONE);
                binding.webView.setVisibility(View.VISIBLE);
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
            binding.webView.loadUrl(getUrl());
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
