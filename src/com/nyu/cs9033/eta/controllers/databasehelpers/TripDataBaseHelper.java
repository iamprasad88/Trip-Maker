package com.nyu.cs9033.eta.controllers.databasehelpers;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.nyu.cs9033.eta.models.Person;
import com.nyu.cs9033.eta.models.Trip;

public class TripDataBaseHelper extends SQLiteOpenHelper {
	@SuppressWarnings("unused")
	private static final String TAG = "TripDataBaseHelper";

	private static final int DATABASE_VERSION = 7;
	private static final String DATABASE_NAME = "trips";

	private static final String TABLE_TRIP = "trip";
	private static final String COLUMN_TRIP_ID = "_id"; // convention
	private static final String COLUMN_TRIP_DATE = "date";
	private static final String COLUMN_TRIP_TIME = "time";
	private static final String COLUMN_TRIP_LOCATION = "location";
	private static final String COLUMN_TRIP_NAME = "name";

	private static final String TABLE_PERSON = "persons";
	private static final String COLUMN_PERSON_ID = "_id"; // convention
	private static final String COLUMN_PERSON_LOCATION = "location";
	private static final String COLUMN_PERSON_PH = "ph";
	private static final String COLUMN_PERSON_NAME = "name";
	private static final String COLUMN_PERSON_TID = "tid";

	private String colsTripHistoryList[] = { COLUMN_TRIP_ID, COLUMN_TRIP_NAME };
	private String colsTrip[] = { COLUMN_TRIP_ID, COLUMN_TRIP_DATE,
			COLUMN_TRIP_TIME, COLUMN_TRIP_LOCATION, COLUMN_TRIP_NAME };

	public TripDataBaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL("CREATE TABLE " + TABLE_TRIP + "( " + COLUMN_TRIP_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TRIP_DATE
				+ " TEXT, " + COLUMN_TRIP_LOCATION + " TEXT, "
				+ COLUMN_TRIP_TIME + " TEXT, " + COLUMN_TRIP_NAME + " TEXT)");

		db.execSQL("create table " + TABLE_PERSON + "( " + COLUMN_PERSON_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ COLUMN_PERSON_LOCATION + " text, " + COLUMN_PERSON_NAME
				+ " text, " + COLUMN_PERSON_TID + " integer, "
				+ COLUMN_PERSON_PH + " text)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.v("DB Helper Upgrade", "Upgrade");
		db.execSQL("drop table if exists " + TABLE_TRIP);
		db.execSQL("drop table if exists " + TABLE_PERSON);
		onCreate(db);
	}

	public long insertTrip(Trip trip) {
		ContentValues cv = new ContentValues();
		cv.put(COLUMN_TRIP_NAME, trip.tripName);
		cv.put(COLUMN_TRIP_DATE, trip.tripDate);
		cv.put(COLUMN_TRIP_TIME, trip.tripTime);
		cv.put(COLUMN_TRIP_LOCATION, trip.tripLocation);
		long tripID = getWritableDatabase().insert(TABLE_TRIP, null, cv);
		insertPerson(tripID, trip);
		return tripID;
	}

	public void insertPerson(long tripID, Trip trip) {
		Person[] persons = trip.tripPersons;
		for (Person p : persons) {

			ContentValues cv = new ContentValues();
			cv.put(COLUMN_PERSON_ID, p.ID);
			cv.put(COLUMN_PERSON_LOCATION, p.CurrentLocation);
			cv.put(COLUMN_PERSON_NAME, p.Name);
			cv.put(COLUMN_PERSON_TID, tripID);
			getWritableDatabase().insert(TABLE_TRIP, null, cv);
		}
	}

	public String[] getColsTripHistoryList() {
		return colsTripHistoryList;
	}

	public Cursor getAllTrips() {
		Cursor c = getReadableDatabase().query(TripDataBaseHelper.TABLE_TRIP,
				colsTrip, null, null, null, null, null, null);
		return c;
	}

	public ArrayList<Person> getTripPersons(long trip_id) {
		Cursor c = getReadableDatabase().rawQuery(
				"SELECT * FROM " + TABLE_PERSON + " WHERE " + COLUMN_PERSON_TID
						+ " = " + trip_id, null);
		c.moveToFirst();

		ArrayList<Person> alp = new ArrayList<Person>();
		while (!c.isAfterLast()) {
			Person p = new Person();
			p.Name = c.getString(c.getColumnIndex(COLUMN_PERSON_NAME));
			p.ID = c.getString(c.getColumnIndex(COLUMN_PERSON_TID));
			p.PhoneNumber = c.getString(c.getColumnIndex(COLUMN_PERSON_PH));
			alp.add(p);
			c.moveToNext();
		}
		c.close();

		return alp;
	}

	public Trip getTrip(long trip_id) {
		Cursor c = getReadableDatabase().rawQuery(
				"SELECT * FROM " + TABLE_TRIP + " WHERE " + COLUMN_TRIP_ID
						+ " = " + trip_id, null);
		c.moveToFirst();

		Trip t = new Trip();
		t.tripDate = c.getString(c.getColumnIndex(COLUMN_TRIP_DATE));
		t.tripTime = c.getString(c.getColumnIndex(COLUMN_TRIP_TIME));
		t.tripLocation = c.getString(c.getColumnIndex(COLUMN_TRIP_LOCATION));
		t.tripName = c.getString(c.getColumnIndex(COLUMN_TRIP_NAME));
		t.tripPersons = getTripPersons(trip_id).toArray(new Person[] {});
		c.close();

		return t;
	}

}
