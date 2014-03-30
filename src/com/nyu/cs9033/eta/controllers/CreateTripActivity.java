package com.nyu.cs9033.eta.controllers;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nyu.cs9033.eta.R;
import com.nyu.cs9033.eta.models.Person;
import com.nyu.cs9033.eta.models.Trip;

public class CreateTripActivity extends Activity {
	@SuppressWarnings("unused")
	private static final String TAG = "CreateTripActivity";

	ArrayList<Person> persons;
	Button addButton;
	Button saveButton;
	EditText personName;
	EditText currentLocation;
	EditText tripName;
	EditText tripDate;
	EditText tripTime;
	EditText tripLocation;
	TextView tripPersons;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_trip_activity);
		// Initialize Persons array
		// Log.v(TAG, "Done with UI. Init Data");
		tripPersons = (TextView) findViewById(R.id.tripPersonsList);
		persons = new ArrayList<Person>();
		// Get Buttons
		addButton = (Button) findViewById(R.id.addButton);
		saveButton = (Button) findViewById(R.id.saveButton);
		// Get Trip Text Fields
		tripName = (EditText) findViewById(R.id.tripName);
		tripDate = (EditText) findViewById(R.id.tripDate);
		tripTime = (EditText) findViewById(R.id.tripTime);
		tripLocation = (EditText) findViewById(R.id.tripLocation);
		// Get Person text Fields
		personName = (EditText) findViewById(R.id.personName);
		currentLocation = (EditText) findViewById(R.id.currentLocation);

		// Log.v(TAG, "Init Listeners");
		// Set listener for Add button
		addButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				TextView tripPersons = CreateTripActivity.this.tripPersons;
				String personName = CreateTripActivity.this.personName
						.getText().toString();
				String currentLocation = CreateTripActivity.this.currentLocation
						.getText().toString();

				if (personName.equals("") == false) {
					// Log.v("AddButton", "Added " + personName + " : "
					// + currentLocation);
					CreateTripActivity.this.persons.add(new Person(personName,
							currentLocation));
					CreateTripActivity.this.personName.setText(R.string.blank);
					CreateTripActivity.this.currentLocation
							.setText(R.string.blank);
					tripPersons.setText(tripPersons.getText() + personName
							+ "(" + currentLocation + ")\n");
				}
			}
		});

		// Set Listener for Save button
		saveButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				String personName = CreateTripActivity.this.personName
						.getText().toString();
				String currentLocation = CreateTripActivity.this.currentLocation
						.getText().toString();

				if (personName.equals("") == false) {
					// Log.v("AddButton", "Added " + personName + " : "
					// + currentLocation);
					CreateTripActivity.this.persons.add(new Person(personName,
							currentLocation));
					CreateTripActivity.this.personName.setText(R.string.blank);
					CreateTripActivity.this.currentLocation
							.setText(R.string.blank);
				}

				Trip trip = createTrip();
				Intent resultIntent = new Intent();
				// Log.v(TAG, "Create New Intent");
				resultIntent.putExtra("trip", trip);
				// Log.v(TAG, "Loaded Trip");
				setResult(Activity.RESULT_OK, resultIntent);
				// Log.v(TAG, "Set Result");
				finish();
				// Log.v(TAG, "Finished");
			}
		});
		// TODO - fill in here
	}

	/**
	 * This method should be used to instantiate a Trip model.
	 * 
	 * @return The Trip as represented by the View.
	 */
	public Trip createTrip() {

		// Log.v(TAG, "Creating Trip");
		// TODO - fill in here
		String tripName = CreateTripActivity.this.tripName.getText().toString();
		// Log.v(TAG, "Added Name");
		String tripLocation = CreateTripActivity.this.tripLocation.getText()
				.toString();
		// Log.v(TAG, "Added Loc");
		String tripDate = CreateTripActivity.this.tripDate.getText().toString();
		// Log.v(TAG, "Added Date");
		String tripTime = CreateTripActivity.this.tripTime.getText().toString();
		// Log.v(TAG, "Added Time");
		Person[] tripPersons = new Person[CreateTripActivity.this.persons
				.size()];

		CreateTripActivity.this.persons.toArray(tripPersons);
		// Log.v(TAG, "Created Persons");
		Trip trip = new Trip(tripName, tripLocation, tripDate, tripTime,
				tripPersons);
		// Log.v(TAG, "Added Persons");
		// Log.v(TAG, "Done Creating Trip");
		return trip;
	}

	/**
	 * For HW2 you should treat this method as a way of sending the Trip data
	 * back to the main Activity
	 * 
	 * Note: If you call finish() here the Activity eventually end and pass an
	 * Intent back to the previous Activity using setResult().
	 * 
	 * @return whether the Trip was successfully persisted.
	 */
	public boolean persistTrip(Trip trip) {

		// TODO - fill in here

		return false;
	}

	/**
	 * This method should be used when a user wants to cancel the creation of a
	 * Trip.
	 * 
	 * Note: You most likely want to call this if your activity dies during the
	 * process of a trip creation or if a cancel/back button event occurs.
	 * Should return to the previous activity without a result using finish()
	 * and setResult().
	 */
	public void cancelTripCreation() {

		// TODO - fill in here
	}
}
