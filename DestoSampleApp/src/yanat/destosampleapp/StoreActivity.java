package yanat.destosampleapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.PhoneLookup;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StoreActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_store);

		final EditText editName = (EditText) findViewById(R.id.editName);
		final EditText editNum = (EditText) findViewById(R.id.editNum);

		Intent intent = getIntent();

		// get the number
		String num = intent.getStringExtra("incomingNumber");

		// get the name if it exists in the contact list
		String name = checkIfNumberExists(getApplicationContext(), num);

		if (name == null) {
			// ask the user to enter a name
			editName.setHint("Please Enter a user name");
		} else {
			// if the name exists in the contact list then set the value
			editName.setText(name);
		}
		
		//set the value of the number field
		editNum.setText(num);

		// the log button
		Button logButton = (Button) findViewById(R.id.logButton);

		// the click listener for the click button
		logButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				
				// get the name of the user
				String name = editName.getText().toString();
				
				// get the number of the user
				String num = editNum.getText().toString();
				
				// set the intent to log the contents
				Intent intent = new Intent(getApplicationContext(),MainActivity.class);
				
				// put values to be sent in the intents
				intent.putExtra("name", name);
				intent.putExtra("number", num);
				
				// start the next activity
				startActivity(intent);
			}
		});

	}

	// check if the number exists in the contact list
	protected static String checkIfNumberExists(Context context, String number) {

		// the URI to check if then number exists
		Uri lookupUri = Uri.withAppendedPath(PhoneLookup.CONTENT_FILTER_URI,
				Uri.encode(number));

		// the array of the columns to be selected
		String[] mPhoneNumberProjection = { PhoneLookup.NUMBER,
				PhoneLookup.DISPLAY_NAME };

		// the where clause
		String selection = PhoneLookup.NUMBER + " = '" + number + "'";

		// the cursor which returns the result
		Cursor cur = context.getContentResolver().query(lookupUri,
				mPhoneNumberProjection, selection, null, null);

		try {
			if (cur.moveToFirst()) {
				return cur.getString(cur
						.getColumnIndex(PhoneLookup.DISPLAY_NAME));
			}
		} finally {
			if (cur != null)
				cur.close();
		}
		return null;
	}

}
