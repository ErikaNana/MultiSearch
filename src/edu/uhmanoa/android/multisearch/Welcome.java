package edu.uhmanoa.android.multisearch;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Welcome extends Activity implements OnClickListener {
	
	Button mSearchButton;
	EditText mSearchField;
	TextView mWelcomeText;;
	
	public static final String SEARCH_TERM = "search term";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		
		mSearchButton = (Button) findViewById(R.id.searchButton);
		mSearchField = (EditText) findViewById(R.id.inputSearchTerm);
		mWelcomeText = (TextView) findViewById(R.id.welcomeTitleText);
		
		mSearchButton.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		String searchText = mSearchField.getText().toString();
		if (!searchText.isEmpty()) {
			Intent startSearchResults = new Intent(this, SearchResults.class);
			startSearchResults.putExtra(SEARCH_TERM, searchText);
			this.startActivity(startSearchResults);			
		}
		else {
			buildNoSearchTermDialog();
		}
	}
	protected void buildNoSearchTermDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this, AlertDialog.THEME_DEVICE_DEFAULT_DARK)
		.setMessage("No search term provided!  Can't continue without one.")
		.setTitle(R.string.app_name);
		builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				return;
			}
		});
		AlertDialog dialog = builder.create();
		dialog.setCanceledOnTouchOutside(false);
		dialog.setCancelable(false);
		dialog.show();
	}
}
