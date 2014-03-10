package com.nyu.cs9033.eta.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class Trip implements Parcelable {
	private static final String TAG = "Trip";

	String tripName;
	String tripDate;
	String tripTime;
	Person[] tripPersons;
	Parcelable[] tripPersonParcellable;
	String tripLocation;

	public static final Parcelable.Creator<Trip> CREATOR = new Parcelable.Creator<Trip>() {
		public Trip createFromParcel(Parcel p) {
			return new Trip(p);
		}

		public Trip[] newArray(int size) {
			return new Trip[size];
		}
	};

	/**
	 * Create a Trip model object from a Parcel
	 * 
	 * @param p
	 *            The Parcel used to populate the Model fields.
	 */
	public Trip(Parcel p) {

		Parcelable[] Parr = p
				.readParcelableArray(Person.class.getClassLoader());
		this.tripPersons = new Person[Parr.length];

		for (int i = 0; i < Parr.length; i++) {
			Log.v(TAG, "Added " + i);
			this.tripPersons[i] = (Person) Parr[i];
		}
		String[] strarr = p.createStringArray();

		tripName = strarr[0];
		tripDate = strarr[1];
		tripTime = strarr[2];
		tripLocation = strarr[3];
	}

	/**
	 * Create a Trip model object from arguments
	 * 
	 * @param args
	 *            Add arbitrary number of arguments to instantiate trip class.
	 */
	// public Trip(String args) {
	//
	// // TODO - fill in here, please note you must have more arguments here
	// }

	@Override
	public void writeToParcel(Parcel p, int flags) {

		p.writeParcelableArray(this.tripPersons, flags);
		p.writeStringArray(new String[] { tripName, tripDate, tripTime,
				tripLocation });
		// TODO - fill in here
	}

	/**
	 * Feel free to add additional functions as necessary below.
	 */
	public Trip(String tripName, String tripLocation, String tripDate,
			String tripTime, Person[] tripPersons) {
		this.tripDate = tripDate;
		this.tripTime = tripTime;
		this.tripLocation = tripLocation;
		this.tripName = tripName;
		this.tripPersons = tripPersons;

	}

	public String getTripDate() {
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

	public String getTripTime() {
		return tripTime;
	}

	/**
	 * Do not implement
	 */
	@Override
	public int describeContents() {
		// Do not implement!
		return 0;
	}
}
