package com.example.drgreenthumb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.drgreenthumb.R;

public class supplies extends AppCompatActivity {

    private WebView webView;
    private String url1 = "https://www.homedepot.com/b/Outdoors-Garden-Center/N-5yc1vZbx6k/Ntk-extended/Ntt-";
    private String url2 = "?NCNI-5";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplies);

        Bundle bundle = getIntent().getExtras();

        String search = bundle.getString("SEARCH");

        search.replace(" ", "+");

        webView = findViewById(R.id.webView);

        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.setOverScrollMode(WebView.OVER_SCROLL_NEVER);
        webView.loadUrl(url1 + search + url2);
    }
}
