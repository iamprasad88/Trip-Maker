package com.nyu.cs9033.eta.controllers;

import com.nyu.cs9033.eta.models.Trip;
import com.nyu.cs9033.eta.R;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;

public class MainActivity extends Activity {

	private static final String TAG = "MainActivity";
	private Button createTripButton;
	private Button viewTripButton;

	private Trip trip = null;

	// private Intent createTripIntent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		trip = null;
		// Log.v(TAG, "in Main Activity -- 1");
		// TODO - fill in here

		createTripButton = (Button) findViewById(R.id.createTripButton);
		viewTripButton = (Button) findViewById(R.id.viewTripButton);

		startTripCreator();
		startTripViewer();
	}

	/**
	 * Receive result from CreateTripActivity here. Can be used to save instance
	 * of Trip object which can be viewed in the ViewTripActivity.
	 */
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO - fill in here
		// Log.v(TAG, "Back to MainActivity");
		if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
			// Log.v(TAG, "Result flag is OK");
			trip = data.getParcelableExtra("trip");
			// Log.v(TAG, "Get Trip");
		}
	}

	/**
	 * This method should start the Activity responsible for creating a Trip.
	 */
	public void startTripCreator() {

		// TODO - fill in here
		createTripButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent createTripIntent = new Intent(MainActivity.this,
						CreateTripActivity.class);
				startActivityForResult(createTripIntent, 1);
				// Log.v("OnClickListener", "In OnClick");
			}
		});

	}

	public Trip getTrip() {
		return trip;
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
						ViewTripActivity.class);

				viewTripIntent.putExtra("trip", MainActivity.this.getTrip());
				startActivity(viewTripIntent);
			}
		});
	}

	/**
	 * This method should be called when a Trip is returned to the main
	 * activity. Remember that the Trip will not be returned as a Trip object;
	 * it will be in some persisted form. The method that calls onReceivedTrip
	 * should extract a Trip object from however it receives it, and pass that
	 * object to onReceivedTrip.
	 * 
	 * @param trip
	 *            The Trip that has been created in the trip creator Activity.
	 */
	public void onReceivedTrip(Trip trip) {

		// TODO - fill in here
	}
}
