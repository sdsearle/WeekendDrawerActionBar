package com.example.admin.weekenddraweractionbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.SearchView;

public class WebViewerActivity extends AppCompatActivity {

    WebView webView;
    android.widget.SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_viewer);
        webView = (WebView) findViewById(R.id.wvWebView);
        webView.getSettings().setJavaScriptEnabled(true);
        searchView = (android.widget.SearchView) findViewById(R.id.svSearch);
        Log.d("TAG", "onCreate: ");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
//                CharSequence cs = searchView.getQuery();
//                s = cs.toString();
                if (!s.contains("https://")){
                    String tmp = s;
                    s = "https://";
                    s += tmp;

                }
                webView.setWebViewClient(new WebViewClient() {

                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        view.loadUrl(url);
                        return true;
                    }
                });
                webView.loadUrl(s);
//                Log.d("TAG", "onClick: " + cs.toString());
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }

        });
    }
}
