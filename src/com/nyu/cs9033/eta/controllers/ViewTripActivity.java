package com.nyu.cs9033.eta.controllers;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nyu.cs9033.eta.R;
import com.nyu.cs9033.eta.controllers.databasehelpers.TripDataBaseHelper;
import com.nyu.cs9033.eta.models.Person;
import com.nyu.cs9033.eta.models.Trip;

public class ViewTripActivity extends Activity {

	private static String TAG = "ViewTripActivity";

//	private ShowPeopleFragment spf;
	private ViewTripFragment vtf;

	Trip trip;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_trip_activity);

		Log.d(TAG, "onCreate " + TAG);
		Bundle b = getIntent().getExtras();

		String tripId = b.getString("tripID");

		TripDataBaseHelper tdbh = new TripDataBaseHelper(this);

		trip = tdbh.getTrip(Long.parseLong(tripId));
		Log.d(TAG, "back to onCreate " + TAG);
		if (trip == null) {
			Log.d(TAG, "trip is null");
		} else {
			Log.d(TAG, "tripID = " + trip.ID + "/" + tripId);
		}
		FragmentManager fm = getFragmentManager();
		FragmentTransaction txn = fm.beginTransaction();
		vtf = new ViewTripFragment();
		// spf = new ShowPeopleFragment();
		txn.add(R.id.viewTripLLayout, vtf, "View Trip");
		// txn.add(R.id.viewPeopleLLayout, spf, "Show People");
		txn.commit();

		vtf.setTripName(trip.tripName);
		Log.d(TAG, "trip name set");
		vtf.setTripDate(trip.tripDate);
		Log.d(TAG, "trip Date set");
		vtf.setTripLocation(trip.tripLocation);
		Log.d(TAG, "trip Loc set");
		// spf.setPersons(trip.tripPersons);
		// Log.d(TAG, "trip Person set");
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		LinearLayout ll = (LinearLayout) findViewById(R.id.viewPeopleLLayout);
		for (Person p : trip.tripPersons) {
			LinearLayout child = (LinearLayout) (this.getLayoutInflater()
					.inflate(R.layout.view_person_row, null));
			// .inflate(R.layout.view_person_row, null, true);
			Log.d(TAG, "In People show 3");
			((TextView) child.findViewById(R.id.view_person_row_name))
					.setText(p.Name);
			((TextView) child.findViewById(R.id.view_person_row_ph_number))
					.setText(p.PhoneNumber);
			Log.d(TAG, "In People show 4");
			((TextView) child.findViewById(R.id.view_person_row_location))
					.setText(p.CurrentLocation);
			Log.d(TAG, "In People show 5");
			ll.addView(child);
			Log.d(TAG, "In People show 6");
		}
	}
}
