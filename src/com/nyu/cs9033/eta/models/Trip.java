package com.nyu.cs9033.eta.models;

import java.util.Calendar;

public class Trip
// implements Parcelable
{
	@SuppressWarnings("unused")
	private static final String TAG = "Trip";

	public String tripName;
	// public String tripDate;
	// public String tripTime;
	public Person[] tripPersons;
	// Parcelable[] tripPersonParcellable;
	public String tripLocation;
	public Calendar tripDate;

	// public static final Parcelable.Creator<Trip> CREATOR = new
	// Parcelable.Creator<Trip>() {
	// public Trip createFromParcel(Parcel p) {
	// return new Trip(p);
	// }
	//
	// public Trip[] newArray(int size) {
	// return new Trip[size];
	// }
	// };

	/**
	 * Create a Trip model object from a Parcel
	 * 
	 * @param p
	 *            The Parcel used to populate the Model fields.
	 */

	public Trip() {

	}

	// public Trip(Parcel p) {
	//
	// Parcelable[] Parr = p
	// .readParcelableArray(Person.class.getClassLoader());
	// this.tripPersons = new Person[Parr.length];
	//
	// for (int i = 0; i < Parr.length; i++) {
	// Log.i(TAG, "Added " + i);
	// this.tripPersons[i] = (Person) Parr[i];
	// }
	// String[] strarr = p.createStringArray();
	//
	// tripName = strarr[0];
	// SimpleDateFormat s = new SimpleDateFormat(strarr[1]);
	// tripDate = s.getCalendar();
	// tripLocation = strarr[3];
	// }

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

	// @Override
	// public void writeToParcel(Parcel p, int flags) {
	//
	// p.writeParcelableArray(this.tripPersons, flags);
	// p.writeStringArray(new String[] { tripName, tripDate, tripTime,
	// tripLocation });
	// // TODO - fill in here
	// }
	/**
	 * Feel free to add additional functions as necessary below.
	 */
	public Trip(String tripName, String tripLocation, Calendar tripDate,
			Person[] tripPersons) {
		this.tripDate = tripDate;
		// this.tripTime = tripTime;
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

	/**
	 * Do not implement
	 */
	// @Override
	// public int describeContents() {
	// // Do not implement!
	// return 0;
	// }
}
