package edu.uhmanoa.android.multisearch;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

@SuppressLint("SetJavaScriptEnabled")

public class SearchResults extends Activity implements OnClickListener{
	public static final String WIKIPEDIA_URL = "http://en.wikipedia.org/wiki/";
	public static final String NTY_URL = "http://query.nytimes.com/search/sitesearch/#/";
	public static final String NYT_24HRS = "/24hours/";
	public static final String COMPARE_MODE_TEXT = "Leave Compare Mode";
	public static final String GOOGLE_URL = "https://www.google.com/search?q=";
	public static final String TUMBLR_URL = "http://www.tumblr.com/tagged/";
	public static final String PINTEREST_URL = "http://pinterest.com/search/pins/?q=";
	
	public static final int NYT = 1;
	public static final int WIKIPEDIA = 2;
	public static final int GOOGLE = 3;
	public static final int TUMBLR = 4;
	public static final int PINTEREST = 5;
	
	WebView mResult;
	WebView mResult2;
	ImageButton mGoToWikipedia;
	ImageButton mGoToNewYorkTimes;
	ImageButton mGoGoogle;
	ImageButton mGoTumblr;
	ImageButton mGoPinterest;
	Button mCompareButton;
	String mTerm;
	View mDivider;
	ProgressBar mPBOne;
	ProgressBar mPBTwo;
	
	boolean mCompareMode;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_results);
		Intent thisIntent = this.getIntent();
		mTerm = thisIntent.getStringExtra(Welcome.SEARCH_TERM);
		
		mGoToWikipedia = (ImageButton) findViewById(R.id.wikipediaButton);
		mGoToNewYorkTimes = (ImageButton) findViewById(R.id.newYorkTimesButton);
		mGoGoogle = (ImageButton) findViewById(R.id.googleButton);
		mGoTumblr = (ImageButton) findViewById(R.id.tumblrButton);
		mGoPinterest = (ImageButton) findViewById(R.id.pinterestButton);
		mCompareButton = (Button) findViewById(R.id.compareButton);
		mDivider = findViewById(R.id.divider);
		mPBOne = (ProgressBar) findViewById(R.id.progressBarOne);
		mPBTwo = (ProgressBar) findViewById(R.id.progressBarTwo);
		
		mGoToWikipedia.setOnClickListener(this);
		mGoToNewYorkTimes.setOnClickListener(this);
		mGoGoogle.setOnClickListener(this);
		mGoTumblr.setOnClickListener(this);
		mGoPinterest.setOnClickListener(this);
		mCompareButton.setOnClickListener(this);
		
		Toast.makeText(getApplicationContext(), "Searching for:  " + mTerm, Toast.LENGTH_SHORT).show();
		
		mResult = (WebView) findViewById(R.id.result);
		mResult2 = (WebView) findViewById(R.id.result2);
		
		mResult.getSettings().setJavaScriptEnabled(true);
		mResult2.getSettings().setJavaScriptEnabled(true);
		
		mResult.setWebChromeClient(new CustomWebView(mPBOne));
		mResult.setWebViewClient(new WebViewClient());
		mResult2.setWebChromeClient(new CustomWebView(mPBTwo));
		mResult2.setWebViewClient(new WebViewClient());
		
		//default is the Wikipedia result
		launchWebsite(mResult, WIKIPEDIA);
	}
	
	public void launchWebsite(WebView window, int webSite) {
		switch(webSite) {
			case WIKIPEDIA:{
				window.loadUrl(WIKIPEDIA_URL + mTerm);
				break;
			}
			case NYT:{
				window.loadUrl(NTY_URL + mTerm + NYT_24HRS);
				break;
			}
			case GOOGLE:{
				window.loadUrl(GOOGLE_URL + mTerm);
				break;
			}
			case TUMBLR:{
				window.loadUrl(TUMBLR_URL + mTerm);
				break;
			}
			case PINTEREST:{
				window.loadUrl(PINTEREST_URL + mTerm);
				break;
			}
		}

	}

	@Override
	public void onClick(View view) {
		switch(view.getId()) {
			case R.id.wikipediaButton:{
				setWhichWindow(WIKIPEDIA);
				break;
			}
			case R.id.newYorkTimesButton:{
				setWhichWindow(NYT);
				break;
			}
			case R.id.googleButton:{
				setWhichWindow(GOOGLE);
				break;
			}
			case R.id.tumblrButton:{
				setWhichWindow(TUMBLR);
				break;
			}
			case R.id.pinterestButton:{
				setWhichWindow(PINTEREST);
				break;
			}
			case R.id.compareButton:{
				if (mCompareButton.getText().equals(COMPARE_MODE_TEXT)){
					mCompareButton.setText("Compare");
					mDivider.setVisibility(View.GONE);
					mResult2.setVisibility(View.GONE);
					mCompareMode = false;
				}
				else {
					mCompareMode = true;
					mCompareButton.setText(COMPARE_MODE_TEXT);
					mDivider.setVisibility(View.VISIBLE);
					mResult2.setVisibility(View.VISIBLE);
				}
			}
		}
	}
	
	public void setWhichWindow(int type) {
		if (mCompareMode) {
			//if there is something in the result window
			if (!mResult.getUrl().equals("")) {
				launchWebsite(mResult2, type);
			}
		}
		else {
			launchWebsite(mResult, type);
		}
	}
	
}
