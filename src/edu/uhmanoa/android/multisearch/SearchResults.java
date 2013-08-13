package edu.uhmanoa.android.multisearch;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;

@SuppressLint("SetJavaScriptEnabled")

public class SearchResults extends Activity implements OnClickListener{
	public static final String WIKIPEDIA_URL = "http://en.wikipedia.org/wiki/";
	public static final String NTY_URL = "http://query.nytimes.com/search/sitesearch/#/";
	public static final String NYT_24HRS = "/24hours/";
	public static final String LEAVE_COMPARE_MODE_TEXT = "Leave Compare Mode";
	public static final String GOOGLE_URL = "https://www.google.com/search?q=";
	public static final String TUMBLR_URL = "http://www.tumblr.com/tagged/";
	public static final String PINTEREST_URL = "http://pinterest.com/search/pins/?q=";
	public static final String AMAZON_URL = "http://www.amazon.com/s/ref=nb_sb_noss?url=search-alias%3Daps&field-keywords=";
	public static final String TWITTER_URL = "https://twitter.com/search?q=";
	
	public static final int NYT = 1;
	public static final int WIKIPEDIA = 2;
	public static final int GOOGLE = 3;
	public static final int TUMBLR = 4;
	public static final int PINTEREST = 5;
	public static final int AMAZON = 6;
	public static final int TWITTER = 7;

	
	WebView mResult;
	WebView mResult2;
	ImageButton mGoToWikipedia;
	ImageButton mGoToNewYorkTimes;
	ImageButton mGoGoogle;
	ImageButton mGoTumblr;
	ImageButton mGoPinterest;
	ImageButton mGoAmazon;
	ImageButton mGoTwitter;
	
	LinearLayout mButtonLayout;
	LinearLayout mPlaceHolderOne;
	LinearLayout mPlaceHolderTwo;
	
	Button mCompareButton;
	String mTerm;
	View mDivider;
	ProgressBar mPBOne;
	ProgressBar mPBTwo;
	
	ImageButton mViewClicked;
	
	boolean mCompareMode;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_results);
		Intent thisIntent = this.getIntent();
		mTerm = thisIntent.getStringExtra(Welcome.SEARCH_TERM);
		
		mCompareButton = (Button) findViewById(R.id.compareButton);
		mCompareButton.setOnClickListener(this);
		
		mPlaceHolderOne = (LinearLayout) findViewById(R.id.windowOne);
		mPlaceHolderTwo = (LinearLayout) findViewById(R.id.windowTwo);
		
		CustomWebViewWindow window = new CustomWebViewWindow(this);		
		window.launchWebsite(AMAZON, mTerm);
		mPlaceHolderOne.addView(window);
		mButtonLayout = (LinearLayout) findViewById(R.id.buttonLayout);
		
		mGoAmazon = createAndAddButton(AMAZON);
		mGoPinterest = createAndAddButton(PINTEREST);
		mGoGoogle = createAndAddButton(GOOGLE);
		mGoToNewYorkTimes = createAndAddButton(NYT);
		mGoToWikipedia = createAndAddButton(WIKIPEDIA);
		mGoTumblr = createAndAddButton(TUMBLR);
		mGoTwitter = createAndAddButton(TWITTER);
		
/*		mCompareButton = (Button) findViewById(R.id.compareButton);
		mDivider = findViewById(R.id.divider);
		mPBOne = (ProgressBar) findViewById(R.id.progressBarOne);
		mPBTwo = (ProgressBar) findViewById(R.id.progressBarTwo);
		
		mButtonLayout = (LinearLayout) findViewById(R.id.buttonLayout);
		
		mGoAmazon = createAndAddButton(AMAZON);
		mGoPinterest = createAndAddButton(PINTEREST);
		mGoGoogle = createAndAddButton(GOOGLE);
		mGoToNewYorkTimes = createAndAddButton(NYT);
		mGoToWikipedia = createAndAddButton(WIKIPEDIA);
		mGoTumblr = createAndAddButton(TUMBLR);
		mGoTwitter = createAndAddButton(TWITTER);
		
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
		launchWebsite(mResult, WIKIPEDIA);*/
/*		nMainLayout = (LinearLayout) findViewById(R.id.mainLayout);
		CustomWebViewWindow window = new CustomWebViewWindow(this);
		CustomWebViewWindow windowTwo = new CustomWebViewWindow(this);
		window.launchWebsite(GOOGLE, mTerm);
		windowTwo.launchWebsite(AMAZON, mTerm);
		//add it above scrollview
		
		nMainLayout.addView(window);
		nMainLayout.addView(windowTwo);*/
		
/*		LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View window = inflater.inflate(R.layout.webview_window, null);
		ImageButton button = (ImageButton) window.findViewById(R.id.exitButton);
		button.setVisibility(View.VISIBLE);*/
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
			case AMAZON:{
				window.loadUrl(AMAZON_URL + mTerm);
				break;
			}
			case TWITTER:{
				window.loadUrl(TWITTER_URL + mTerm);
				break;
			}
		}

	}

	@Override
	public void onClick(View view) {
		if (view instanceof ImageButton) {
			ImageButton thisView = (ImageButton) view;
			if (!mCompareMode) {
				if (!thisView.equals(mViewClicked)) {
					if (mViewClicked != null) {
						mViewClicked.setImageAlpha(75);
					}
					else { //initial create
						if (!thisView.equals(mGoToWikipedia)) {
							mGoToWikipedia.setImageAlpha(75);
						}
					}
					thisView.setImageAlpha(255);
					mViewClicked = thisView;
				}
			}	
		}

		switch(view.getId()) {
			case WIKIPEDIA:{
				setWhichWindow(WIKIPEDIA);
				break;
			}
			case NYT:{
				setWhichWindow(NYT);
				break;
			}
			case GOOGLE:{
				setWhichWindow(GOOGLE);
				break;
			}
			case TUMBLR:{
				setWhichWindow(TUMBLR);
				break;
			}
			case PINTEREST:{
				setWhichWindow(PINTEREST);
				break;
			}
			case AMAZON:{
				setWhichWindow(AMAZON);
				break;
			}
			case TWITTER:{
				setWhichWindow(TWITTER);
				break;
			}
			case R.id.compareButton:{
				mPlaceHolderTwo.setVisibility(View.VISIBLE);
				CustomWebViewWindow windowTwo = new CustomWebViewWindow(this);
				windowTwo.launchWebsite(AMAZON, mTerm);
				
/*				if (mCompareButton.getText().equals(LEAVE_COMPARE_MODE_TEXT)){
					mCompareButton.setText("Compare");
					mDivider.setVisibility(View.GONE);
					mResult2.setVisibility(View.GONE);
					mCompareMode = false;
				}
				else {
					mCompareMode = true;
					mCompareButton.setText(LEAVE_COMPARE_MODE_TEXT);
					mDivider.setVisibility(View.VISIBLE);
					mResult2.setVisibility(View.VISIBLE);
				}*/
				break;
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
	
	//Creates the buttons and adds it to the ScrollView
	public ImageButton createAndAddButton (int type) {
		ImageButton button = new ImageButton(this);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
		
		int leftRightMargin = convertDipToPix(5);
		int topBottomMargin = convertDipToPix(10);
		int layoutHeightWidth = convertDipToPix(50);
		
		params.leftMargin = leftRightMargin;
		params.rightMargin = leftRightMargin;
		params.topMargin = topBottomMargin;
		params.bottomMargin = topBottomMargin;
		params.gravity = Gravity.CENTER;
		params.height = layoutHeightWidth;
		params.width = layoutHeightWidth;
	
		button.setLayoutParams(params);
		button.setId(type);
		button.setOnClickListener(this);
		button.setImageAlpha(100);
		//so only see image
		button.setBackgroundColor(Color.TRANSPARENT);
		switch (type){
			case AMAZON:{
				button.setImageResource(R.drawable.amazon);
				break;
			}
			case PINTEREST:{
				button.setImageResource(R.drawable.pinterest);
				mGoPinterest = button;
				break;
			}
			case TUMBLR:{
				button.setImageResource(R.drawable.tumblr);
				break;
			}
			case GOOGLE:{
				button.setImageResource(R.drawable.google);
				break;
			}
			case NYT:{
				button.setImageResource(R.drawable.new_york_times_icon);
				break;
			}
			case WIKIPEDIA:{
				//since default
				button.setImageAlpha(255);
				button.setImageResource(R.drawable.wikipedia_icon);
				break;
			}
			case TWITTER:{
				button.setImageResource(R.drawable.twitter);
			}
		}
		mButtonLayout.addView(button);
		
		return button;
	}
	
	public int convertDipToPix(int dip) {
		Resources r = this.getResources();
		return (int) TypedValue.applyDimension(
		        TypedValue.COMPLEX_UNIT_DIP,
		        dip, 
		        r.getDisplayMetrics()
		);
	}
}
