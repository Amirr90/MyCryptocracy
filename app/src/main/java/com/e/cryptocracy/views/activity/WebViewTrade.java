package com.e.cryptocracy.views.activity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.e.cryptocracy.R;
import com.e.cryptocracy.utility.AppConstant;

public class WebViewTrade extends AppCompatActivity {

    String tradeUrl;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_trade);

        tradeUrl = getIntent().getStringExtra(AppConstant.TRADE_URL);
        if (tradeUrl != null) {
            WebView theWebPage = new WebView(this);
            theWebPage.getSettings().setJavaScriptEnabled(true);
            theWebPage.getSettings().setPluginState(WebSettings.PluginState.ON);
            setContentView(theWebPage);
            theWebPage.loadUrl(tradeUrl);

        }


    }
}