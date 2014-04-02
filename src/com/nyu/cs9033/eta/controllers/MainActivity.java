package com.nyu.cs9033.eta.controllers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.nyu.cs9033.eta.R;

public class MainActivity extends Activity {
	@SuppressWarnings("unused")
	private static final String TAG = "MainActivity";
	private Button createTripButton;
	private Button viewTripButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		createTripButton = (Button) findViewById(R.id.createTripButton);
		viewTripButton = (Button) findViewById(R.id.viewTripButton);

		startTripCreator();
		startTripViewer();
	}

	/**
	 * Receive result from CreateTripActivity here. Can be used to save instance
	 * of Trip object which can be viewed in the ViewTripActivity.
	 */
	// @Override
	// public void onActivityResult(int requestCode, int resultCode, Intent
	// data) {
	// // TODO - fill in here
	// // if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
	// // Trip trip = data.getParcelableExtra("trip");
	// // }
	// }

	/**
	 * This method should start the Activity responsible for creating a Trip.
	 */
	public void startTripCreator() {

		createTripButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent createTripIntent = new Intent(MainActivity.this,
						CreateTripActivity.class);
				startActivity(createTripIntent);
			}
		});

	}

	/**
	 * This method should start the Activity responsible for viewing a Trip.
	 */
	public void startTripViewer() {

		// TODO - fill in here
		viewTripButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent viewTripIntent = new Intent(MainActivity.this,
						TripHistoryActivity.class);
				startActivity(viewTripIntent);
			}
		});
	}
}
