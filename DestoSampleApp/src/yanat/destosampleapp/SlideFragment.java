package yanat.destosampleapp;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class SlideFragment extends Fragment {

	private static Context context;
	ViewGroup rootView;

	public SlideFragment() {
	}

	public static SlideFragment create(int position, Context mcontext) {
		SlideFragment frag = new SlideFragment();
		Bundle args = new Bundle();
		args.putInt("CELL", position);
		frag.setArguments(args);
		context = mcontext;
		return frag;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// get the cell number
		int cellNumber = getArguments().getInt("CELL");

		rootView = (ViewGroup) inflater.inflate(R.layout.my_activity_list,
				container, false);

		// get the listview
		ListView list = (ListView) rootView.findViewById(R.id.callList);

		String cell = null;

		switch (cellNumber) {
		case 0:
			cell = "one";
			break;

		case 1:
			cell = "two";
			break;

		case 2:
			cell = "three";
			break;

		case 3:
			cell = "four";
			break;

		case 4:
			cell = "five";
			break;

		default:
			break;
		}

		// create database helper class object
		MyDatabaseHelperClass db = new MyDatabaseHelperClass(context,
				MyDatabaseHelperClass.DATABASE_NAME, null,
				MyDatabaseHelperClass.DATABASE_VERSION);

		// declare the array list to put to the adapter
		ArrayList<HashMap<String, String>> myList = new ArrayList<HashMap<String, String>>();

		try {

			// get the contact cursor
			Cursor cursor = db.getContact(cell);

			if (null != cursor) {

				Log.d("tag", String.valueOf(cursor.getCount()));

				// iterate through the cursor
				while (cursor.moveToNext()) {
					// declare the map to which values are to be added
					HashMap<String, String> myMap = new HashMap<String, String>();

					// add the phone number
					myMap.put("number", cursor.getString(0));

					Log.d("tag0", cursor.getString(0));

					// add the name
					myMap.put("name", cursor.getString(1));

					Log.d("tag1", cursor.getString(1));

					// add the type
					myMap.put("type", cursor.getString(2));

					Log.d("tag2", cursor.getString(2));

					// add the map to the array list
					myList.add(myMap);

				}
			} else {
				// declare the map to which values are to be added
				HashMap<String, String> myMap = new HashMap<String, String>();
				// add the phone number
				myMap.put(MyDatabaseHelperClass.KEY_NUM, "No number exists");

				// add the name
				myMap.put(MyDatabaseHelperClass.KEY_NAME, "No name");

				// add the type
				myMap.put(MyDatabaseHelperClass.KEY_TYPE,
						"You have not recieved any calls");
				Log.d("tag", "else part");

				// add the map to the array list
				myList.add(myMap);

			}

			cursor.close();

		} catch (NullPointerException ex) {
			// declare the map to which values are to be added
			HashMap<String, String> myMap = new HashMap<String, String>();

			// add the phone number
			myMap.put(MyDatabaseHelperClass.KEY_NUM, "No number exists");

			// add the name
			myMap.put(MyDatabaseHelperClass.KEY_NAME, "No name");

			// add the type
			myMap.put(MyDatabaseHelperClass.KEY_TYPE,
					"You have not recieved any calls");

			// add the map to the array list
			myList.add(myMap);

			Log.d("tag", "null pointer exception part");
		}

		// the list adapter for the list
		MyListAdapter listAdapter = new MyListAdapter(context, myList);

		// set the adapter for the list view
		list.setAdapter(listAdapter);

		return rootView;
	}
}
