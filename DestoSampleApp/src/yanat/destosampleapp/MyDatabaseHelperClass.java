package yanat.destosampleapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDatabaseHelperClass extends SQLiteOpenHelper {

	// the database version
	protected static final int DATABASE_VERSION = 1;

	// the database name
	protected static final String DATABASE_NAME = "ContactsManager";

	// the table name
	protected static final String TABLE_NAME = "contacts1";

	// the table column names
	protected static final String KEY_NAME = "name";
	protected static final String KEY_NUM = "number";
	protected static final String KEY_CELL = "cell";
	protected static final String KEY_TYPE = "type";

	public MyDatabaseHelperClass(Context context, String name,
			CursorFactory factory, int version) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// SQL query to create the database
		String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
				+ KEY_NAME + " TEXT, " + KEY_NUM + " TEXT, " + KEY_CELL
				+ " TEXT, " + KEY_TYPE + " TEXT)";

		// execute the sql query
		db.execSQL(CREATE_CONTACTS_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

		// Create tables again
		onCreate(db);
	}

	public void addContact(String name, String number, String cell, String type) {
		// get an instance of the database
		SQLiteDatabase db = this.getWritableDatabase();

		// create the object used to store a set of values that the
		// ContentResolver can process.
		ContentValues values = new ContentValues();

		// add the name
		values.put(KEY_NAME, name);
		// add the number
		values.put(KEY_NUM, number);
		// add the cell
		values.put(KEY_CELL, cell);
		// add the call type
		values.put(KEY_TYPE, type);

		// insert the rows
		 Log.d("tag", String.valueOf(db.insert(TABLE_NAME, null, values)));

		// close the database connection
		db.close();
	}

	public Cursor getContact(String cell) {
		// get an instance of the database
		SQLiteDatabase db = this.getReadableDatabase();

		// the column array
		String[] columns = { KEY_NAME, KEY_NUM, KEY_TYPE };

		// the selection string
		String selection = KEY_CELL + " = ? ";

		// the selection arguments
		String[] selectionArgs = { cell };

		// get the cursor object
		Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs,
				null, null, null);

		// return the cursor
		return cursor;
	}

}
