package com.nyu.cs9033.eta.controllers;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nyu.cs9033.eta.R;
import com.nyu.cs9033.eta.controllers.databasehelpers.TripDataBaseHelper;
import com.nyu.cs9033.eta.models.Trip;

public class ViewTripActivity extends ListActivity {
	@SuppressWarnings("unused")
	private static final String TAG = "ViewTripActivity";
	Trip trip = null;

	// LinearLayout layout;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.view_trip);

			TripDataBaseHelper tdbh = new TripDataBaseHelper(this);
			String from[] = tdbh.getColsTripHistoryList();
			int to[] = new int[] { R.id.textView1, R.id.textView2 };
			Cursor c = tdbh.getAllTrips();

			@SuppressWarnings("deprecation")
			SimpleCursorAdapter a = new SimpleCursorAdapter(this,
					R.layout.view_trip_row, c, from, to);
			this.setListAdapter(a);
		} catch (Exception e) {
			Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();

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

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);

		String tripID = ((TextView) l.findViewById(R.id.textView1)).getText()
				.toString();
		try {
			TripDataBaseHelper td = new TripDataBaseHelper(this);
			Trip t = td.getTrip(Long.parseLong(tripID));

			Intent i = new Intent(this, SavedTripActivity.class);
			i.putExtra("tripName", t.tripName);
			i.putExtra("tripDate", t.tripDate);
			i.putExtra("tripTime", t.tripTime);
			Log.v("Here", t.tripName);
			startActivity(i);
		} catch (Exception e) {
			Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
		}
	}
}
