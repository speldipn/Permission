package com.example.jyo05.permission;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class WebActivity extends AppCompatActivity {

  Button btnBack;
  Button btnGo;
  EditText editUrl;
  WebView webView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_web);

    init();
  }

  private void init() {
    final String defaultUrl = "naver.com";

    webView = findViewById(R.id.webView);
    webView.setWebViewClient(new WebViewClient());
    webView.setWebChromeClient(new WebChromeClient());

    webView.getSettings().setJavaScriptEnabled(true);
    webView.getSettings().setSupportZoom(true);
    webView.getSettings().setBuiltInZoomControls(true);

    editUrl = findViewById(R.id.editUrl);
    editUrl.setText(defaultUrl);

    btnBack = findViewById(R.id.btnBack);
    btnBack.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        webView.goBack();
      }
    });

    btnGo = findViewById(R.id.btnGo);
    btnGo.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String url = editUrl.getText().toString();
        if(!url.startsWith("http://")) {
          url = "http://" + url;
        }
        webView.loadUrl(url);
      }
    });
    btnGo.callOnClick();
  }
}
