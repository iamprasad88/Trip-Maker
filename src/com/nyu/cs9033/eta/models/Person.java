package com.nyu.cs9033.eta.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Person implements Parcelable {

	public static final String TAG = "Person";

	String Name;
	String CurrentLocation;

	public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>() {
		public Person createFromParcel(Parcel p) {
			return new Person(p);
		}

		public Person[] newArray(int size) {
			return new Person[size];
		}
	};

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
	}

	/**
	 * Create a Person model object from arguments
	 * 
	 * @param args
	 *            Add arbitrary number of arguments to instantiate trip class.
	 */
	public Person(String args) {

		// TODO - fill in here, please note you must have more arguments here
		Name = args;
		CurrentLocation = "";
	}

	@Override
	public void writeToParcel(Parcel p, int flags) {

		// TODO - fill in here
		p.writeStringArray(new String[] { this.Name, this.CurrentLocation });
	}

	/**
	 * Feel free to add additional functions as necessary below.
	 */
	public Person(String name, String currentLoaction) {
		this.Name = name;
		this.CurrentLocation = currentLoaction;
	}

	public String getCurrentLocation() {
		return CurrentLocation;
	}

	public String getName() {
		return Name;
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
