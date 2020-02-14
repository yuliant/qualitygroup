package com.example.android.qualityjatim.global;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.android.qualityjatim.R;

public class GlobalMarketActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_market);
        WebView myWebView = findViewById(R.id.webViewGlobal);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String title = "Market Global";
        this.setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                view.loadUrl("javascript:alert('Loding berhasil')");
            }
        });

        myWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, final android.webkit.JsResult result) {
                Toast.makeText(GlobalMarketActivity.this, message, Toast.LENGTH_LONG).show();
                result.confirm();
                return true;
            }
        });
        myWebView.loadUrl("https://www.rumah.com/rumah-dijual/di-area-sidoarjo-idji26/dibawah-200juta-rupiah");
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
