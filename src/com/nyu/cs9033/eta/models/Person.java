package com.nyu.cs9033.eta.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable {

	public static final String TAG = "Person";
	public String ID;
	public String Name;
	public String CurrentLocation;
	public String PhoneNumber;

	public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>() {
		public Person createFromParcel(Parcel p) {
			return new Person(p);
		}

		public Person[] newArray(int size) {
			return new Person[size];
		}
	};

	public Person() {
	}

	/**
	 * Create a Person model object from a Parcel
	 * 
	 * @param p
	 *            The Parcel used to populate the Model fields.
	 */
	public Person(Parcel p) {

		// TODO - fill in here

		String[] strarr = p.createStringArray();
		this.Name = strarr[0];
		this.CurrentLocation = strarr[1];
		this.PhoneNumber = strarr[2];
	}

	/**
	 * Create a Person model object from arguments
	 * 
	 * @param args
	 *            Add arbitrary number of arguments to instantiate trip class.
	 */
	public Person(String... args) {
		ID = args[0];
		Name = args[1];
		PhoneNumber = args[2];
		if (args.length >= 4) {
			CurrentLocation = args[3];
		}
	}

	@Override
	public void writeToParcel(Parcel p, int flags) {

		p.writeStringArray(new String[] { this.Name, this.CurrentLocation });
	}

	/**
	 * Feel free to add additional functions as necessary below.
	 */

	public String getCurrentLocation() {
		return CurrentLocation;
	}

	public String getName() {
		return Name;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public String getID() {
		return ID;
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
