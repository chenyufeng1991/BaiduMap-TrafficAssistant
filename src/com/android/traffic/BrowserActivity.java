package com.android.traffic;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.traffic.R;

public class BrowserActivity extends Activity implements OnClickListener {

	private Context context;
	private WebView mWebView;
	private ProgressDialog mProgressDialog;

	private TextView show_title;
	private ImageView back;
	private ImageView refresh;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_browser);
		this.context = this;

		initView();
		initListener();
		mWebView.loadUrl("http://www.qq.com/");
		mWebView.setWebViewClient(new WebViewClient() {

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {

				view.loadUrl(url);
				return true;
			}

		});

		WebSettings webSettings = mWebView.getSettings();
		webSettings.setJavaScriptEnabled(true);// 支持JSP页面；
		webSettings.setLoadsImagesAutomatically(true);// 支持自动加载页面；
		webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

		mWebView.setWebChromeClient(new WebChromeClient() {

			@Override
			public void onProgressChanged(WebView view, int newProgress) {

				if (newProgress == 100) {
					closeProgressDialog();
				} else {
					openProgressDialog(newProgress);

				}

			}

			@Override
			public void onReceivedTitle(WebView view, String title) {
				show_title.setText(title);// 设置该网页的标题；
				super.onReceivedTitle(view, title);
			}
		});

	}

	private void initListener() {
		back.setOnClickListener(this);
		refresh.setOnClickListener(this);

	}

	protected void openProgressDialog(int newProgress) {

		if (mProgressDialog == null) {
			mProgressDialog = new ProgressDialog(context);
			mProgressDialog.setTitle("正在加载");
			mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			mProgressDialog.setProgress(newProgress);
			mProgressDialog.show();
		} else {
			mProgressDialog.setProgress(newProgress);
		}

	}

	protected void closeProgressDialog() {
		if (mProgressDialog != null && mProgressDialog.isShowing()) {
			mProgressDialog.dismiss();
			mProgressDialog = null;
		}

	}

	private void initView() {
		mWebView = (WebView) findViewById(R.id.id_webview);
		back = (ImageView) findViewById(R.id.id_back);
		refresh = (ImageView) findViewById(R.id.id_refresh);
		show_title = (TextView) findViewById(R.id.id_title);

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {

			if (mWebView.canGoBack()) {
				mWebView.goBack();
				return true;
			} else {

			}
		}

		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.id_back:
			AlphaAnimation alphaAnimation0 = new AlphaAnimation(1.0F, 0F);
			alphaAnimation0.setDuration(500);
			back.startAnimation(alphaAnimation0);
			if (mWebView.canGoBack()) {
				mWebView.goBack();
			} else {
				finish();
			}

			break;

		case R.id.id_refresh:

			AlphaAnimation alphaAnimation = new AlphaAnimation(1.0F, 0F);
			alphaAnimation.setDuration(500);
			refresh.startAnimation(alphaAnimation);

			mWebView.reload();

			break;

		}

	}

}
