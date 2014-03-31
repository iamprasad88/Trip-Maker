package com.nyu.cs9033.eta.controllers;

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
import android.widget.Toast;

import com.nyu.cs9033.eta.R;
import com.nyu.cs9033.eta.controllers.databasehelpers.TripDataBaseHelper;
import com.nyu.cs9033.eta.models.Trip;

public class CreateTripActivity extends Activity {
	@SuppressWarnings("unused")
	private static final String TAG = "CreateTripActivity";

	private static final int CONTACT_PICK = 0;
	public Trip trip;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.create_trip_activity);
		FragmentManager fm = getFragmentManager();
		FragmentTransaction txn = fm.beginTransaction();
		EditTripFragment etf = new EditTripFragment();
		ShowPeopleFragment spf = new ShowPeopleFragment();
		txn.add(R.id.create_trip_llayout, etf, "Create Trip");
		txn.add(R.id.show_people_in_ct_llayout, spf, "Show People");
		txn.commit();

		if (savedInstanceState != null
				&& savedInstanceState.containsKey("tripid")) {
			TripDataBaseHelper t = new TripDataBaseHelper(this);
			this.trip = t.getTrip((Long.parseLong(savedInstanceState
					.getString("tripid"))));
			// spf.setPersons();
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		outState.putParcelable("trip", trip);
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

			FragmentManager fm = getFragmentManager();
			ShowPeopleFragment sp = (ShowPeopleFragment) fm
					.findFragmentByTag("Show People");
			EditTripFragment et = (EditTripFragment) fm
					.findFragmentByTag("Create Trip");
			trip = new Trip();
			et.updateTrip();
			trip.tripDate = et.tripDate;
			trip.tripTime = et.tripTime;
			// trip.tripLocation = et.tripLocation;
			trip.tripName = et.tripName;
			trip.tripPersons = sp.getPersons();
			Toast.makeText(this, "Saving", Toast.LENGTH_SHORT).show();
			Log.v(TAG, "TripDate=" + trip.tripDate);

			if (persistTrip(trip))
				Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
			finish();
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

				String contactId = data.getData().getLastPathSegment()
						.toString();
				FragmentManager fm = getFragmentManager();
				ShowPeopleFragment f = (ShowPeopleFragment) fm
						.findFragmentByTag("Show People");
				f.addPerson(contactId);

				break;
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

	/**
	 * For HW2 you should treat this method as a way of sending the Trip data
	 * back to the main Activity
	 * 
	 * Note: If you call finish() here the Activity eventually end and pass an
	 * Intent back to the previous Activity using setResult().
	 * 
	 * @return whether the Trip was successfully persisted.
	 */
	public boolean persistTrip(Trip trip) {

		TripDataBaseHelper dbh = new TripDataBaseHelper(this);

		try {
			dbh.insertTrip(trip);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * This method should be used when a user wants to cancel the creation of a
	 * Trip.
	 * 
	 * Note: You most likely want to call this if your activity dies during the
	 * process of a trip creation or if a cancel/back button event occurs.
	 * Should return to the previous activity without a result using finish()
	 * and setResult().
	 */
	public void cancelTripCreation() {

	}
}