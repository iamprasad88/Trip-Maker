package com.nyu.cs9033.eta.models;

public class Person {

	@SuppressWarnings("unused")
	private static final String TAG = "Person";

	public String ID;
	public String Name;
	public String CurrentLocation;
	public String PhoneNumber;

	public Person() {
	}

	public Person(String ID, String Name, String PhoneNumber) {
		this.ID = ID;
		this.Name = Name;
		this.PhoneNumber = PhoneNumber;
		CurrentLocation = "";
	}

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
}
