package edu.uhmanoa.android.multisearch;

import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

public class CustomWebView extends WebChromeClient{
	ProgressBar mProgressBar;
	
	public CustomWebView(ProgressBar progressBar) {
		mProgressBar = progressBar;
	}
	@Override

	public void onProgressChanged(WebView view, int newProgress) {

		super.onProgressChanged(view, newProgress);
		
		mProgressBar.setProgress(newProgress);
		if (newProgress == 100) {
			mProgressBar.setVisibility(View.GONE);
	   
		} 
		else{
			mProgressBar.setVisibility(View.VISIBLE);
	   
		}

	}
}
