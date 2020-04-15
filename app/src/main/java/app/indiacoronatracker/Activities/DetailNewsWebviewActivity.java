package app.indiacoronatracker.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import app.indiacoronatracker.R;

public class DetailNewsWebviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news_webview);
        WebView webView = findViewById(R.id.webview);

        Intent i = getIntent();
        String url = i.getStringExtra("url");

        WebSettings webSettings = webView.getSettings();
        webView.setWebViewClient(new MyWebViewClient());
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl(url);

    }

    class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

}
