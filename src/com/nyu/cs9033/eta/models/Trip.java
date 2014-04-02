package com.nyu.cs9033.eta.models;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Trip {
	@SuppressWarnings("unused")
	private static final String TAG = "Trip";

	public String ID;
	public String tripName;
	public Person[] tripPersons;
	public String tripLocation;
	public Calendar tripDate;

	/**
	 * Create a Trip model object from a Parcel
	 * 
	 * @param p
	 *            The Parcel used to populate the Model fields.
	 */

	public Trip() {
		// ID = "-1";
	}

	/**
	 * Feel free to add additional functions as necessary below.
	 */
	public Trip(String tripName, String tripLocation, Calendar tripDate,
			Person[] tripPersons) {
		this.tripDate = tripDate;
		this.tripLocation = tripLocation;
		this.tripName = tripName;
		this.tripPersons = tripPersons;
		// ID = "-1";

	}

	@SuppressLint("SimpleDateFormat")
	public Trip(String tripName, String tripLocation, String tripDate,
			Person[] tripPersons) {
		this.tripDate = (new SimpleDateFormat(tripDate)).getCalendar();
		this.tripLocation = tripLocation;
		this.tripName = tripName;
		this.tripPersons = tripPersons;
	}

	public Calendar getTripDate() {
		return tripDate;
	}

	public String getTripLocation() {
		return tripLocation;
	}

	public String getTripName() {
		return tripName;
	}

	public Person[] getTripPersons() {
		return tripPersons;
	}

}
