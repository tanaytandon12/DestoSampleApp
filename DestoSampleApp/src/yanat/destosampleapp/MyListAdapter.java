package yanat.destosampleapp;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyListAdapter extends BaseAdapter {

	private ArrayList<HashMap<String, String>> myList;
	private Context context;

	public MyListAdapter(Context context,
			ArrayList<HashMap<String, String>> myList) {
		this.context = context;
		this.myList = myList;
	}

	@Override
	public int getCount() {
		return myList.size();
	}

	@Override
	public Object getItem(int position) {
		return myList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater mInflater = (LayoutInflater) context
					.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(R.layout.my_list_view, null);
		}

		TextView numText = (TextView) convertView.findViewById(R.id.numberText);
		TextView nameText = (TextView) convertView.findViewById(R.id.nameText);
		TextView typeText = (TextView) convertView.findViewById(R.id.typeText);

		HashMap<String, String> data = new HashMap<String, String>();
		data = myList.get(position);

		numText.setText(data.get("number"));
		Log.d("tagsfd",data.get("numer") + " " + data.get("name") + " " + data.get("type"));
		nameText.setText(data.get("name"));
		typeText.setText(data.get("type"));

		return convertView;
	}

}
