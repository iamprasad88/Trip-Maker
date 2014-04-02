package com.nyu.cs9033.eta.controllers.databasehelpers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.nyu.cs9033.eta.models.Person;
import com.nyu.cs9033.eta.models.Trip;

public class TripDataBaseHelper extends SQLiteOpenHelper {

	private static final String TAG = "TripDataBaseHelper";

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "trips";

	private static final String TABLE_TRIP = "trip";
	private static final String COLUMN_TRIP_ID = "_id"; // convention
	private static final String COLUMN_TRIP_DATE = "date";
	private static final String COLUMN_TRIP_LOCATION = "location";
	private static final String COLUMN_TRIP_NAME = "name";

	private static final String TABLE_PERSON = "persons";
	private static final String COLUMN_PERSON_ID = "_id"; // convention
	private static final String COLUMN_PERSON_LOCATION = "location";
	private static final String COLUMN_PERSON_PH = "ph";
	private static final String COLUMN_PERSON_NAME = "name";
	private static final String COLUMN_PERSON_TID = "tid";

	private String colsTripHistoryList[] = { COLUMN_TRIP_ID, COLUMN_TRIP_NAME };

	// private String colsTrip[] = { COLUMN_TRIP_ID, COLUMN_TRIP_DATE,
	// COLUMN_TRIP_LOCATION, COLUMN_TRIP_NAME };

	public TripDataBaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		Log.d(TAG, "DB locked in onCreate");
		db.execSQL("CREATE TABLE " + TABLE_TRIP + "( " + COLUMN_TRIP_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_TRIP_DATE
				+ " TEXT, " + COLUMN_TRIP_LOCATION + " TEXT, "
				+ COLUMN_TRIP_NAME + " TEXT)");

		db.execSQL("create table " + TABLE_PERSON + "( " + COLUMN_PERSON_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ COLUMN_PERSON_LOCATION + " text, " + COLUMN_PERSON_NAME
				+ " text, " + COLUMN_PERSON_TID + " integer, "
				+ COLUMN_PERSON_PH + " text)");
		// db.endTransaction();
		// db.close();
		Log.d(TAG, "DB unlocked in onCreate");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.v("DB Helper Upgrade", "Upgrade");
		// db.beginTransaction();
		Log.d(TAG, "DB locked in onUpgrade");
		db.execSQL("drop table if exists " + TABLE_TRIP);
		db.execSQL("drop table if exists " + TABLE_PERSON);
		// db.endTransaction();
		// db.close();
		Log.d(TAG, "DB unlocked in onUpgrade");
		onCreate(db);
	}

	public long insertTrip(Trip trip) {
		SQLiteDatabase db = getWritableDatabase();
		ContentValues cv = new ContentValues();
		if (trip.ID != null) {
			cv.put(COLUMN_TRIP_ID, trip.ID);
			Log.d(TAG, "Trip ID was set to " + trip.ID);
		}
		cv.put(COLUMN_TRIP_NAME, trip.tripName);
		cv.put(COLUMN_TRIP_DATE,
				trip.tripDate.get(Calendar.YEAR) + "-"
						+ trip.tripDate.get(Calendar.MONTH)
						+ trip.tripDate.get(Calendar.DAY_OF_MONTH) + " "
						+ trip.tripDate.get(Calendar.HOUR) + ":"
						+ trip.tripDate.get(Calendar.MINUTE) + ":00");
		cv.put(COLUMN_TRIP_LOCATION, trip.tripLocation);
		// db.beginTransaction();
		Log.d(TAG, "DB locked in insertTrip");
		Log.d(TAG, "Started Insert Trip");
		long tripID = db.insertWithOnConflict(TABLE_TRIP, null, cv,
				SQLiteDatabase.CONFLICT_REPLACE);
		Log.d(TAG, "Trip " + tripID + " was inserted/updated");

		Log.d(TAG, "Finished Insert Trip");
		if (insertPerson(tripID, trip)) {
			// db.setTransactionSuccessful();
			// db.endTransaction();
			db.close();
			Log.d(TAG, "DB unlocked in insertTrip");
			return tripID;
		} else
			return -1L;
	}

	public boolean insertPerson(long tripID, Trip trip) {
		try {
			Person[] persons = trip.tripPersons;
			SQLiteDatabase db = getWritableDatabase();
			// db.beginTransaction();
			Log.d(TAG, "DB locked in insertPerson");
			Log.d(TAG, "Started Insert Person");
			for (Person p : persons) {

				ContentValues cv = new ContentValues();
				cv.put(COLUMN_PERSON_LOCATION, p.CurrentLocation);
				cv.put(COLUMN_PERSON_PH, p.PhoneNumber);
				cv.put(COLUMN_PERSON_NAME, p.Name);
				cv.put(COLUMN_PERSON_TID, tripID);
				db.insertWithOnConflict(TABLE_PERSON, null, cv,
						SQLiteDatabase.CONFLICT_REPLACE);
			}
			// db.setTransactionSuccessful();
			// db.endTransaction();
			db.close();
			Log.d(TAG, "DB unlocked in insertPerson");
			Log.d(TAG, "Finished Insert Persons");
			return true;
		} catch (Exception e) {
			Log.d(TAG, e.toString());
			return false;
		}
	}

	public String[] getColsTripHistoryList() {
		return colsTripHistoryList;
	}

	public Cursor getAllTrips() {
		SQLiteDatabase db = getReadableDatabase();
		Log.d(TAG, "DB locked in getAllTrips");
		Cursor c = db.query(TripDataBaseHelper.TABLE_TRIP, colsTripHistoryList,
				null, null, null, null, null, null);
		return c;
	}

	public ArrayList<Person> getTripPersons(long trip_id) {
		SQLiteDatabase db = getReadableDatabase();
		Log.d(TAG, "DB locked in ongetTripPersons");
		Cursor c = db.rawQuery("SELECT * FROM " + TABLE_PERSON + " WHERE "
				+ COLUMN_PERSON_TID + " = " + trip_id, null);
		c.moveToFirst();

		ArrayList<Person> alp = new ArrayList<Person>();
		while (!c.isAfterLast()) {
			Person p = new Person();
			p.Name = c.getString(c.getColumnIndex(COLUMN_PERSON_NAME));
			p.ID = c.getString(c.getColumnIndex(COLUMN_PERSON_TID));
			p.PhoneNumber = c.getString(c.getColumnIndex(COLUMN_PERSON_PH));
			p.CurrentLocation = c.getString(c
					.getColumnIndex(COLUMN_PERSON_LOCATION));
			alp.add(p);
			c.moveToNext();
		}
		c.close();
		Log.d(TAG, "DB unlocked in getTripPersons");
		return alp;
	}

	@SuppressLint("SimpleDateFormat")
	public Trip getTrip(long trip_id) {
		SQLiteDatabase db = getReadableDatabase();
		Log.d(TAG, "DB locked in getTrip");
		Cursor c = db.rawQuery("SELECT * FROM " + TABLE_TRIP + " WHERE "
				+ COLUMN_TRIP_ID + " = " + trip_id, null);
		c.moveToFirst();

		Trip t = new Trip();
		t.ID = c.getString(c.getColumnIndex(COLUMN_TRIP_ID));
		t.tripDate = (new SimpleDateFormat(c.getString(c
				.getColumnIndex(COLUMN_TRIP_DATE)))).getCalendar();
		t.tripLocation = c.getString(c.getColumnIndex(COLUMN_TRIP_LOCATION));
		t.tripName = c.getString(c.getColumnIndex(COLUMN_TRIP_NAME));
		t.tripPersons = getTripPersons(trip_id).toArray(new Person[] {});
		c.close();
		Log.d(TAG, "DB unlocked in getTrip");
		return t;
	}
}
