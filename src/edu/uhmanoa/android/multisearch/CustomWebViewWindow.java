package edu.uhmanoa.android.multisearch;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

public class CustomWebViewWindow extends RelativeLayout {

	RelativeLayout layoutHolder;
	ProgressBar progressBar;
	WebView webView;
	ImageButton imageButton;
	
	public static final String WIKIPEDIA_URL = "http://en.wikipedia.org/wiki/";
	public static final String NTY_URL = "http://query.nytimes.com/search/sitesearch/#/";
	public static final String NYT_24HRS = "/24hours/";
	public static final String LEAVE_COMPARE_MODE_TEXT = "Leave Compare Mode";
	public static final String GOOGLE_URL = "https://www.google.com/search?q=";
	public static final String TUMBLR_URL = "http://www.tumblr.com/tagged/";
	public static final String PINTEREST_URL = "http://pinterest.com/search/pins/?q=";
	public static final String AMAZON_URL = "http://www.amazon.com/s/ref=nb_sb_noss?url=search-alias%3Daps&field-keywords=";
	public static final String TWITTER_URL = "https://twitter.com/search?q=";
	
	@SuppressLint("SetJavaScriptEnabled")
	
	public CustomWebViewWindow(Context context) {
		super(context);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.webview_window, this, true);
		layoutHolder = (RelativeLayout) findViewById(R.id.window);

		progressBar = (ProgressBar) findViewById(R.id.progressBar);
		webView = (WebView) findViewById(R.id.webView);
		imageButton = (ImageButton) findViewById(R.id.exitButton);
		Log.w("CWBVW, ", "layoutHolder:  " + layoutHolder);
		Log.w("CWBVW, ", "imageButton:  " + imageButton);
		Log.w("CWBVW, ", "webView:  " + webView);
		Log.w("CWBVW, ", "progressBar:  " + progressBar);
		imageButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				leaveCompareMode();
			}
		});
		
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setWebChromeClient(new CustomWebView(progressBar));
		webView.setWebViewClient(new WebViewClient());
		
/*		Log.w("CSW", "get params:  "  + this.getLayoutParams());*/
	}
	public void leaveCompareMode() {
		webView.setAlpha((float) 0.5);
		imageButton.setVisibility(View.GONE);
	}
	public void beginCompareMode() {
		webView.setAlpha(1);
		imageButton.setVisibility(View.VISIBLE);
	}
	public void launchWebsite(int webSite, String mTerm) {
		switch(webSite) {
			case SearchResults.WIKIPEDIA:{
				webView.loadUrl(WIKIPEDIA_URL + mTerm);
				break;
			}
			case SearchResults.NYT:{
				webView.loadUrl(NTY_URL + mTerm + NYT_24HRS);
				break;
			}
			case SearchResults.GOOGLE:{
				webView.loadUrl(GOOGLE_URL + mTerm);
				break;
			}
			case SearchResults.TUMBLR:{
				webView.loadUrl(TUMBLR_URL + mTerm);
				break;
			}
			case SearchResults.PINTEREST:{
				webView.loadUrl(PINTEREST_URL + mTerm);
				break;
			}
			case SearchResults.AMAZON:{
				webView.loadUrl(AMAZON_URL + mTerm);
				break;
			}
			case SearchResults.TWITTER:{
				webView.loadUrl(TWITTER_URL + mTerm);
				break;
			}
		}

	}
}
