package edu.uhmanoa.android.multisearch;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

@SuppressLint("SetJavaScriptEnabled")

public class SearchResults extends Activity implements OnClickListener{
	public static final String WIKIPEDIA_URL = "http://en.wikipedia.org/wiki/";
	public static final String NTY_URL = "http://query.nytimes.com/search/sitesearch/#/";
	public static final String NTY_24HRS = "/24hours/";
	public static final String COMPARE_MODE_TEXT = "Leave Compare Mode";
	public static final int NYT = 1;
	public static final int WIKIPEDIA = 2;
	
	WebView mResult;
	WebView mResult2;
	ImageButton mGoToWikipedia;
	ImageButton mGoToNewYorkTimes;
	Button mCompareButton;
	String mTerm;
	View mDivider;
	boolean mCompareMode;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_results);
		Intent thisIntent = this.getIntent();
		mTerm = thisIntent.getStringExtra(Welcome.SEARCH_TERM);
		
		mGoToWikipedia = (ImageButton) findViewById(R.id.wikipediaButton);
		mGoToNewYorkTimes = (ImageButton) findViewById(R.id.newYorkTimesButton);
		mCompareButton = (Button) findViewById(R.id.compareButton);
		mDivider = findViewById(R.id.divider);
		
		mGoToWikipedia.setOnClickListener(this);
		mGoToNewYorkTimes.setOnClickListener(this);
		mCompareButton.setOnClickListener(this);
		
		Toast.makeText(getApplicationContext(), "Searching for:  " + mTerm, Toast.LENGTH_SHORT).show();
		
		mResult = (WebView) findViewById(R.id.result);
		mResult2 = (WebView) findViewById(R.id.result2);
		
		mResult.getSettings().setJavaScriptEnabled(true);
		mResult2.getSettings().setJavaScriptEnabled(true);
		mResult2.setWebViewClient(new WebViewClient());
		
		//default is the Wikipedia result
		openWikipedia(mResult);
	}
	
	public void openWikipedia(WebView window) {
		window.loadUrl(WIKIPEDIA_URL + mTerm);
	}

	public void openNYT(WebView window) {
		Log.w("SR", NTY_URL + mTerm + NTY_24HRS);
		window.loadUrl(NTY_URL + mTerm + NTY_24HRS);
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
				switch (type) {
					case NYT:{
						openNYT(mResult2);
						break;
					}
					case WIKIPEDIA:{
						openWikipedia(mResult2);
						break;
					}
				}
			}
		}
		else {
			switch (type) {
				case NYT:{
					openNYT(mResult);
					break;
				}
				case WIKIPEDIA:{
					openWikipedia(mResult);
					break;
				}
		}
		}
	}
	
}
