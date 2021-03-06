package com.beabox.hjy.tt;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.app.base.init.BaseActivity;
import com.app.base.init.MyApplication;
import com.umeng.message.PushAgent;

/**
 * 晚九点说明页面
 */
public class NineIntroduceActivity extends BaseActivity {

	private static final String url = "http://wx.codefan.cn/9dian/";
    View backBut;
    WebView webView;
    ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	MyApplication.getInstance().addActivity(this);
    	PushAgent.getInstance(this).onAppStart();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nine_clock_activity_introduce);
        setupView();
        addListener();
    }

    @Override
    public void setupView() {

        backBut = findViewById(R.id.backBtn);
        webView = (WebView) findViewById(R.id.webView);
        loading = (ProgressBar) findViewById(R.id.loading);

        WebSettings settings = webView.getSettings();
        // 这样网页就可加载JavaScript了
        settings.setJavaScriptEnabled(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setBuiltInZoomControls(true);// 显示放大缩小按钮
        settings.setSupportZoom(true);// 允许放大缩小
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                loading.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                loading.setVisibility(View.GONE);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                loading.setVisibility(View.GONE);
            }
        });
        webView.requestFocusFromTouch();
        webView.loadUrl(url);

    }

    @Override
    public void addListener() {
        backBut.setOnClickListener(this);

    }

    @Override
    public void sendMessageHandler(int messageCode) {

    }

    @Override
    public void dataCallBack(Object tag,  int statusCode,Object result) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.backBtn:
                finish();
                break;
        }

    }
    
    @Override
	protected String getActivityName() {
		return "晚九点说明";
	}
}
