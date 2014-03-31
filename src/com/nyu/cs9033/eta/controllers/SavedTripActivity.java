package com.nyu.cs9033.eta.controllers;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.nyu.cs9033.eta.R;

public class SavedTripActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_saved_trip_activity);
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			((TextView) findViewById(R.id.savedtripname)).setText(extras
					.getString("tripName"));
			((TextView) findViewById(R.id.savedtripdate)).setText(extras
					.getString("tripDate"));
			((TextView) findViewById(R.id.savedtriptime)).setText(extras
					.getString("tripTime"));
		}

	}
}
