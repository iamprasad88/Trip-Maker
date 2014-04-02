package com.nyu.cs9033.eta.controllers;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.nyu.cs9033.eta.R;

public class EditTripFragment extends Fragment implements
		DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

	@SuppressWarnings("unused")
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
		((TextView) getView().findViewById(R.id.editTripLocation))
				.setText(tripLocation);
	}

	int year, month, day, hour, min;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.edit_trip_layout, null, true);

		Button t = (Button) root.findViewById(R.id.setdate);
		t.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				DatePickerDialog d = new DatePickerDialog(getActivity(),
						EditTripFragment.this, year, month, day);
				d.show();
			}
		});

		return root;
	}

	public void updateTrip() {
		tripName = ((EditText) getView().findViewById(R.id.editTripName))
				.getText().toString();
		tripLocation = ((TextView) getView()
				.findViewById(R.id.editTripLocation)).getText().toString();

	}

	public void updateViews() {
		((EditText) getView().findViewById(R.id.editTripName))
				.setText(tripName);
		((TextView) getView().findViewById(R.id.editTripLocation))
				.setText(tripLocation);
		((TextView) getView().findViewById(R.id.EditTripDate)).setText(year
				+ "-" + month + "-" + day + " " + hour + ":" + min);

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
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {

		this.year = year;
		month = monthOfYear;
		day = dayOfMonth;
		TimePickerDialog tp = new TimePickerDialog(getActivity(),
				EditTripFragment.this, hour, min, true);
		tp.show();
	}

	@Override
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		hour = hourOfDay;
		min = minute;
		((TextView) getView().findViewById(R.id.EditTripDate)).setText(year
				+ "-" + month + "-" + day + " " + hour + ":" + min);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setTripDate(Calendar.getInstance());
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
