package com.nyu.cs9033.eta.controllers;

import com.nyu.cs9033.eta.models.Person;
import com.nyu.cs9033.eta.models.Trip;
import com.nyu.cs9033.eta.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ViewTripActivity extends Activity {
	public static final String TAG = "ViewTripActivity";
	Trip trip = null;

	// LinearLayout layout;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_trip);
		// layout = (LinearLayout) findViewById(R.id.view_trip_layout);
		Bundle extras = getIntent().getExtras();
		Log.v(TAG, "Got Intent");

		EditText TV = (EditText) findViewById(R.id.viewTripTextView);

		if (extras != null) {
			// Log.v(TAG, "Got Intent");
			trip = (Trip) extras.getParcelable("trip");
			StringBuffer sb = new StringBuffer();
			if (trip != null) {
				Log.v(TAG, "Got Intent2");
				sb.append("Trip Name : " + trip.getTripDate() + "\n");
				sb.append("Trip Location : " + trip.getTripDate() + "\n");
				sb.append("Trip Date : " + trip.getTripDate() + "\n");
				sb.append("Trip Time : " + trip.getTripTime() + "\n\n");
				sb.append("The following people will be joining:-\n");
				Person[] parr = trip.getTripPersons();
				for (Person p : parr) {
					sb.append(p.getName() + "(" + p.getCurrentLocation() + ")\n");
				}
				TV.setText(sb.toString());
			} else {
				TV.setText(R.string.trip_not_created);
			}
		}

	}

	/**
	 * Create the most recent trip that was passed to TripViewer.
	 * 
	 * @param i
	 *            The Intent that contains the most recent trip data.
	 * 
	 * @return The Trip that was most recently passed to TripViewer, or null if
	 *         there is none.
	 */
	public Trip getMostRecentTrip(Intent i) {

		// TODO - fill in here

		return null;
	}

	/**
	 * Populate the View using a Trip model.
	 * 
	 * @param trip
	 *            The Trip model used to populate the View.
	 */
	public void initView(Trip trip) {

		// TODO - fill in here
	}
}
