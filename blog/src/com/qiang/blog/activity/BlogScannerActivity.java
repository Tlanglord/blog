package com.qiang.blog.activity;

import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Html.ImageGetter;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.qiang.blog.R;

public class BlogScannerActivity extends Activity {

	private TextView mScannerNewsTitle;
	private WebView mWebView;

	private String mNewsTitle;
	private String mNewsContent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//getWindow().requestFeature(Window.FEATURE_PROGRESS);
		setContentView(R.layout.activity_scanner);
		getData();
		initView();
		initData();
		initEvent();
	}

	private void getData() {
		Intent intent = getIntent();
		mNewsTitle = intent.getStringExtra("title");
		mNewsContent = intent.getStringExtra("content");

	}

	private void initView() {
		mScannerNewsTitle = (TextView) findViewById(R.id.scanner_news_title);
		mWebView = (WebView) findViewById(R.id.scanner_news_content);
	}

	private void initData() {
		// mScannerNewsContent.setText(Html
		// .fromHtml(mNewsContent, imgGetter, null));

		// WebSettings settings = mWebView.getSettings();
		// settings.setSupportZoom(true);
		// settings.setJavaScriptEnabled(true);
		// settings.setUseWideViewPort(true);
		// settings.setLoadWithOverviewMode(true);

		WebSettings settings = mWebView.getSettings();
		settings.setAppCacheEnabled(false);
		settings.setLoadWithOverviewMode(true);
		settings.setSupportZoom(true);
		settings.setJavaScriptEnabled(true);
		settings.setUseWideViewPort(true);
		settings.setLoadWithOverviewMode(true);

		mWebView.setWebViewClient(new WebViewClient() {

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				return super.shouldOverrideUrlLoading(view, url);
			}

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
			}

			@Override
			public void onLoadResource(WebView view, String url) {
				super.onLoadResource(view, url);
			}

		});
		mWebView.loadUrl(mNewsContent);// , "text/html", "");
		mScannerNewsTitle.setText(mNewsTitle);
	}

	private void initEvent() {
	}

	ImageGetter imgGetter = new Html.ImageGetter() {
		@Override
		public Drawable getDrawable(String source) {
			Drawable drawable = null;
			URL url;
			try {
				url = new URL(source);
				drawable = Drawable.createFromStream(url.openStream(), ""); // »ñÈ¡ÍøÂ·Í¼Æ¬
			} catch (Exception e) {
				return null;
			}
			drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
					drawable.getIntrinsicHeight());
			return drawable;
		}
	};

	public void onBackPressed() {

		if (mWebView.canGoBack()) {
			mWebView.goBack();
		} else {
			finish();
		}
	};
}