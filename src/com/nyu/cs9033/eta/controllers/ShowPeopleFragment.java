package com.nyu.cs9033.eta.controllers;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nyu.cs9033.eta.R;
import com.nyu.cs9033.eta.models.Person;

public class ShowPeopleFragment extends Fragment {
	@SuppressWarnings("unused")
	private static String TAG = "ShowPeopleFragment";
	Activity activity;
	ArrayList<Person> persons;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.show_people_layout, null, true);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		persons = new ArrayList<Person>();
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.activity = activity;
	}

	public void addPerson(String contactId) {

		for (Person p : persons) {
			if (p.getID().equals(contactId)) {
				Toast.makeText(this.activity, "Contact already added",
						Toast.LENGTH_SHORT).show();
				return;
			}
		}

		try {
			String name = "";
			int hasNum = 0;
			String phNumber = "";
			Uri Contact = ContactsContract.Contacts.CONTENT_URI;
			Uri Phone = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

			Cursor contactResult = activity.getContentResolver().query(
					Contact,
					new String[] {
							ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
							ContactsContract.Contacts.HAS_PHONE_NUMBER },
					ContactsContract.Contacts._ID + "=?",
					new String[] { contactId }, null);

			contactResult.moveToFirst();
			int nameIndex = contactResult
					.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY);
			int hasNumIndex = contactResult
					.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER);
			do {

				name = contactResult.getString(nameIndex);
				hasNum = contactResult.getInt(hasNumIndex);

				if (hasNum == 1) {
					Cursor numberResult = activity
							.getContentResolver()
							.query(Phone,
									new String[] { ContactsContract.CommonDataKinds.Phone.NUMBER },
									ContactsContract.CommonDataKinds.Phone.CONTACT_ID
											+ "=?", new String[] { contactId },
									null);
					numberResult.moveToFirst();
					int numIndex = numberResult
							.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
					do {
						phNumber = numberResult.getString(numIndex);
					} while (numberResult.moveToNext());
				}

			} while (contactResult.moveToNext());

			Toast.makeText(this.activity,
					"Added Name:" + name + " Ph:" + phNumber,
					Toast.LENGTH_SHORT).show();
			String location = "LOC";
			LinearLayout ll = (LinearLayout) this.getView();
			LinearLayout child = new LinearLayout(this.activity);
			child = (LinearLayout) ((LayoutInflater) this.activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
					.inflate(R.layout.view_person_row, null, true);
			((TextView) child.findViewById(R.id.view_person_row_name))
					.setText(name);
			((TextView) child.findViewById(R.id.view_person_row_ph_number))
					.setText(phNumber);
			((TextView) child.findViewById(R.id.view_person_row_location))
					.setText(location);
			ll.addView(child);
			persons.add(new Person(contactId, name, phNumber));
		} catch (RuntimeException e) {
			Toast.makeText(this.activity, "Cannot Add this Contact",
					Toast.LENGTH_SHORT).show();
		}
	}

	Person[] getPersons() {
		return persons.toArray(new Person[] {});
	}

	public void setPersons(Person[] p) {
		persons.clear();
		for (Person i : p) {
			persons.add(i);
		}
		updateViews();
	}

	private void updateViews() {

		LinearLayout ll = (LinearLayout) this.getView();
		ll.removeAllViews();
		// LinearLayout child = new LinearLayout(this.activity);
		LinearLayout child;

		for (Person p : persons) {

			child = (LinearLayout) ((LayoutInflater) this.activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
					.inflate(R.layout.view_person_row, null, true);
			((TextView) child.findViewById(R.id.view_person_row_name))
					.setText(p.Name);
			((TextView) child.findViewById(R.id.view_person_row_ph_number))
					.setText(p.PhoneNumber);
			((TextView) child.findViewById(R.id.view_person_row_location))
					.setText(p.CurrentLocation);
			ll.addView(child);
		}
	}

	void clear() {

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		updateViews();
	}

}
