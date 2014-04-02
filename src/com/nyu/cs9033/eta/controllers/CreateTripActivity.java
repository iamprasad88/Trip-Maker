package com.nyu.cs9033.eta.controllers;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.VisibleRegion;
import com.nyu.cs9033.eta.R;
import com.nyu.cs9033.eta.controllers.databasehelpers.TripDataBaseHelper;
import com.nyu.cs9033.eta.models.Trip;

public class CreateTripActivity extends Activity {

	private static final String TAG = "CreateTripActivity";

	private static final int CONTACT_PICK = 0;
	public Trip trip;
	private ShowPeopleFragment spf;
	private EditTripFragment etf;
	private MapFragment map;

	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.create_trip_activity);
		FragmentManager fm = getFragmentManager();
		FragmentTransaction txn = fm.beginTransaction();
		etf = new EditTripFragment();
		spf = new ShowPeopleFragment();
		map = new MapFragment();
		txn.add(R.id.create_trip_llayout, etf, "Create Trip");
		txn.add(R.id.show_people_in_ct_llayout, spf, "Show People");
		txn.add(R.id.map, map, "Show Map");
		txn.commit();

		Button b = (Button) findViewById(R.id.setLocation);

		// map.setMenuVisibility(true);
		b.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d(TAG, "Clicked Map Button");
				GoogleMap m = map.getMap();
				LatLng fleft, fright, nleft;
				VisibleRegion r = m.getProjection().getVisibleRegion();

				fleft = r.farLeft;
				fright = r.farRight;
				nleft = r.nearLeft;

				double lat = (fleft.latitude + fright.latitude) / 2;
				double lon = (fleft.longitude + nleft.longitude) / 2;
				etf.setTripLocation("Lat: " + lat + " Long:" + lon);
			}
		});
		if (savedInstanceState != null
				&& savedInstanceState.containsKey("tripid")) {
			TripDataBaseHelper t = new TripDataBaseHelper(this);
			this.trip = t.getTrip((Long.parseLong(savedInstanceState
					.getString("tripid"))));
			// spf.setPersons();
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.add_person:
			Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,
					ContactsContract.Contacts.CONTENT_URI);
			startActivityForResult(contactPickerIntent, CONTACT_PICK);
			break;
		case R.id.cancel_trip:
			cancelTripCreation();
			break;
		case R.id.save_trip:
			boolean err = false;
			String errorStr = "";

			trip = new Trip();
			etf.updateTrip();
			trip.tripDate = etf.tripDate;
			trip.tripName = etf.tripName;
			if (trip.tripName == null || trip.tripName.equals("")) {
				err = true;
				errorStr += "Trip Name cannot be blank\n";
			}
			trip.tripPersons = spf.getPersons();
			if (trip.tripPersons == null || trip.tripPersons.length == 0) {
				err = true;
				errorStr += "Please add atleast one contact\n";
			}
			trip.tripLocation = etf.tripLocation;
			if (trip.tripLocation == null || trip.tripLocation.equals("")) {
				err = true;
				errorStr += "Trip Location cannot be blank";
			}
			if (err) {
				Toast.makeText(this, errorStr, Toast.LENGTH_SHORT).show();
			} else {
				Toast.makeText(this, "Saving", Toast.LENGTH_SHORT).show();

				if (persistTrip(trip))
					Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
				finish();
			}
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			switch (requestCode) {
			case CONTACT_PICK:
				try {
					String contactId = data.getData().getLastPathSegment()
							.toString();
					Log.d(TAG, "Contact ID = " + contactId);
					// FragmentManager fm = getFragmentManager();
					// if (fm == null) {
					// Log.d(TAG, "fm is null");
					// }

					if (spf == null) {
						Log.d(TAG, "spf is null");
					}
					spf.addPerson(contactId);

					Log.d(TAG, "Added Person");

					break;
				} catch (Exception e) {
					Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT)
							.show();
				}
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.edit_trip, menu);
		return super.onCreateOptionsMenu(menu);
	}

	/**
	 * This method should be used to instantiate a Trip model.
	 * 
	 * @return The Trip as represented by the View.
	 */
	public Trip createTrip() {

		return null;
	}

	public boolean persistTrip(Trip trip) {

		TripDataBaseHelper dbh = new TripDataBaseHelper(this);

		try {
			dbh.insertTrip(trip);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void cancelTripCreation() {
		trip = null;
		this.finish();
	}

	@Override
	protected void onResume() {
		super.onResume();
		map.getMap().setMyLocationEnabled(true);
	}
}