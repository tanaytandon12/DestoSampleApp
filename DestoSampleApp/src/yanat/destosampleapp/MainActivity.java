package yanat.destosampleapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	String name, number, type;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// create database helper class object
		final MyDatabaseHelperClass db = new MyDatabaseHelperClass(
				getApplicationContext(), MyDatabaseHelperClass.DATABASE_NAME,
				null, MyDatabaseHelperClass.DATABASE_VERSION);

		TextView textView1 = (TextView) findViewById(R.id.textView1);
		TextView textView2 = (TextView) findViewById(R.id.textView2);
		TextView textView3 = (TextView) findViewById(R.id.textView3);
		TextView textView4 = (TextView) findViewById(R.id.textView4);
		TextView textView5 = (TextView) findViewById(R.id.textView5);

		Button button = (Button) findViewById(R.id.gotoButton);

		// get the intents
		Intent intent = getIntent();

		// if the activity was not started manually
		if (null != intent) {

			// get the number
			number = intent.getStringExtra("number");

			// get the name
			name = intent.getStringExtra("name");

			// get the type
			type = getCallType();

		}

		// handle the click event for the first cell
		textView1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				db.addContact(name, number, "one", type);
				Toast.makeText(getApplicationContext(),
						"Details added for " + number + " with " + name,
						Toast.LENGTH_LONG).show();
			}
		});

		// handle the click event for the second cell
		textView2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				db.addContact(name, number, "two", type);
				Toast.makeText(getApplicationContext(),
						"Details added for " + number + " with " + name,
						Toast.LENGTH_LONG).show();
			}
		});

		// handle the click event for the third cell
		textView3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				db.addContact(name, number, "three", type);
				Toast.makeText(getApplicationContext(),
						"Details added for " + number + " with " + name,
						Toast.LENGTH_LONG).show();
			}
		});

		// handle the click event for cell 4
		textView4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				db.addContact(name, number, "four", type);
				Toast.makeText(getApplicationContext(),
						"Details added for " + number + " with " + name,
						Toast.LENGTH_LONG).show();
			}
		});

		// handle the click event for cell 5
		textView5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				db.addContact(name, number, "five", type);
				Toast.makeText(getApplicationContext(),
						"Details added for " + number + " with " + name,
						Toast.LENGTH_LONG).show();
			}
		});

		// click on the goto button to view all the records

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),
						ViewActivity.class);
				startActivity(intent);
			}
		});

	}

	private String getCallType() {
		Cursor cur = getApplicationContext().getContentResolver().query(
				CallLog.Calls.CONTENT_URI, null, null, null, null);
		try {
			if (cur.moveToFirst()) {
				switch (cur.getInt(cur.getColumnIndex(CallLog.Calls.TYPE))) {
				case CallLog.Calls.INCOMING_TYPE:
					return "incoming";
				case CallLog.Calls.OUTGOING_TYPE:
					return "outgoing";
				case CallLog.Calls.MISSED_TYPE:
					return "missed";
				}
			}
		} finally {
			if (cur != null)
				cur.close();
		}

		return null;
	}

}
