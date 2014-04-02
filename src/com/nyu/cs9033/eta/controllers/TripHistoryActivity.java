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

public class TripHistoryActivity extends ListActivity {

	private static final String TAG = "TripHistoryActivity";
	Trip trip = null;

	// LinearLayout layout;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_trip_history);
		initView();

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
	public void initView() {
		Log.d(TAG, "initView");
		try {
			TripDataBaseHelper tdbh = new TripDataBaseHelper(this);
			String from[] = tdbh.getColsTripHistoryList();
			int to[] = new int[] { R.id.view_trip_row_tripid,
					R.id.view_trip_row_tripname };
			Log.d(TAG, "Creating cursor");
			Cursor c = tdbh.getAllTrips();

			@SuppressWarnings("deprecation")
			SimpleCursorAdapter a = new SimpleCursorAdapter(this,
					R.layout.view_trip_row, c, from, to);
			Log.d(TAG, "Created cursor");
			this.setListAdapter(a);
			Log.d(TAG, "Created List");
		} catch (Exception e) {
			Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();

		}
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);

		String tripID = ((TextView) v.findViewById(R.id.view_trip_row_tripid))
				.getText().toString();
		try {
			// Toast.makeText(this, tripID + " was clicked.",
			// Toast.LENGTH_SHORT)
			// .show();

			Intent i = new Intent(this, ViewTripActivity.class);
			i.putExtra("tripID", tripID);
			startActivity(i);
		} catch (Exception e) {
			Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
		}
	}
}
