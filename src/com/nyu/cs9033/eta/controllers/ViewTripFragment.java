package com.nyu.cs9033.eta.controllers;

import java.util.Calendar;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nyu.cs9033.eta.R;

public class ViewTripFragment extends Fragment {

	private static String TAG = "EditTripFragment";

	Activity activity = null;
	public String tripName;
	public Calendar tripDate;
	public String tripTime;
	public String tripLocation;

	public String getTripLocation() {
		return tripLocation;
	}

	public void setTripLocation(String tripLocation) {
		this.tripLocation = tripLocation;
	}

	int year, month, day, hour, min;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.view_trip_layout, null, true);
		return root;
	}

	public void updateViews() {
		try {
			Log.d(TAG, "in Trip updateViews");
			View v = this.getView();
			if (v == null) {
				Log.d(TAG, "View is null");
			} else {
				Log.d(TAG, "View is not null");
			}
			((TextView) getView().findViewById(R.id.viewTripName))
					.setText(tripName);
			((TextView) getView().findViewById(R.id.viewTripLocation))
					.setText(tripLocation);
			((TextView) getView().findViewById(R.id.viewTripDate)).setText(year
					+ "-" + month + "-" + day + " " + hour + ":" + min);
		} catch (Exception e) {
			Log.d(TAG, "Trip:" + e.toString());
		}

	}

	public String getTripName() {
		return tripName;
	}

	public void setTripName(String tripName) {
		this.tripName = tripName;
	}

	public Calendar getTripDate() {
		return tripDate;
	}

	public void setTripDate(Calendar tripDate) {
		this.tripDate = tripDate;
		year = tripDate.get(Calendar.YEAR);
		month = tripDate.get(Calendar.MONTH);
		day = tripDate.get(Calendar.DAY_OF_MONTH);
		hour = tripDate.get(Calendar.HOUR_OF_DAY);
		min = tripDate.get(Calendar.MINUTE);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.activity = activity;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		tripDate = Calendar.getInstance();
		year = tripDate.get(Calendar.YEAR);
		month = tripDate.get(Calendar.MONTH);
		day = tripDate.get(Calendar.DAY_OF_MONTH);
		hour = tripDate.get(Calendar.HOUR_OF_DAY);
		min = tripDate.get(Calendar.MINUTE);
	}

	@Override
	public void onResume() {
		super.onResume();
		updateViews();
	}
}
