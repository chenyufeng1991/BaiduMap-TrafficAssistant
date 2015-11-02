package com.android.traffic;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.android.traffic.R;

public class DialActivity extends Activity implements OnItemClickListener,
		OnClickListener {

	private Context context;
	private String info;
	private String ID;
	private String name;

	private ContentResolver contentResolver;

	private ListView listView;
	private ArrayAdapter<String> arrayAdapter;
	private ArrayList<String> arrayList;

	private String pureNumber;

	private EditText editText;
	private Button button;
	private ListView listView2;
	private ArrayAdapter<String> arrayAdapter2;
	private ArrayList<String> arrayList2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_dial);
		this.context = this;
		initView();
		listView.setOnItemClickListener(this);
		listView2.setOnItemClickListener(this);
		button.setOnClickListener(this);
		listView.setAdapter(arrayAdapter);
		listView2.setAdapter(arrayAdapter2);
		contentResolver = getContentResolver();
		Cursor cursor = contentResolver.query(Contacts.CONTENT_URI,
				new String[] { Contacts._ID, Contacts.DISPLAY_NAME }, null,
				null, null);

		if (cursor != null) {
			while (cursor.moveToNext()) {
				ID = cursor.getString(cursor.getColumnIndex(Contacts._ID));
				name = cursor.getString(cursor
						.getColumnIndex(Contacts.DISPLAY_NAME));
				// 打印ID和名字；
				// Log.i("TAG", "ID=" + ID + ";Name=" + name);

				Cursor cursor2 = contentResolver.query(Phone.CONTENT_URI,
						new String[] { Phone.NUMBER, Phone.TYPE },
						Phone.CONTACT_ID + "=" + ID, null, null);
				if (cursor2 != null) {
					while (cursor2.moveToNext()) {
						int type = cursor2.getInt(cursor2
								.getColumnIndex(Phone.TYPE));
						// 这里只打印移动电话；
						if (type == Phone.TYPE_MOBILE) {
							info = name
									+ ":"
									+ cursor2.getString(cursor2
											.getColumnIndex(Phone.NUMBER));
							arrayList.add(info);
							Log.i("TAG", info);
						}
					}
					cursor2.close();
				}

			}
			cursor.close();
		}

	}

	private void initView() {
		listView = (ListView) findViewById(R.id.id_listView);
		button = (Button) findViewById(R.id.id_search);
		editText = (EditText) findViewById(R.id.id_contacts);

		arrayList = new ArrayList<>();
		arrayAdapter = new ArrayAdapter<>(context,
				android.R.layout.simple_list_item_1, arrayList);

		listView2 = (ListView) findViewById(R.id.id_listView2);
		arrayList2 = new ArrayList<>();
		arrayAdapter2 = new ArrayAdapter<>(context,
				android.R.layout.simple_list_item_1, arrayList2);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		switch (parent.getId()) {
		case R.id.id_listView:
			String showInfo = arrayList.get(position);
			// Toast.makeText(context, showInfo, Toast.LENGTH_SHORT).show();

			int start = showInfo.indexOf(":");
			pureNumber = showInfo.substring(start + 1).trim();
			Toast.makeText(context, pureNumber, Toast.LENGTH_SHORT).show();

			Intent intent = new Intent();
			intent.setAction("android.intent.action.CALL");
			intent.setData(Uri.parse("tel:" + pureNumber));
			startActivity(intent);

			break;

		case R.id.id_listView2:
			String showInfo2 = arrayList2.get(position);
			// Toast.makeText(context, showInfo, Toast.LENGTH_SHORT).show();

			int start2 = showInfo2.indexOf(":");
			pureNumber = showInfo2.substring(start2 + 1).trim();
			Toast.makeText(context, pureNumber, Toast.LENGTH_SHORT).show();

			Intent intent2 = new Intent();
			intent2.setAction("android.intent.action.CALL");
			intent2.setData(Uri.parse("tel:" + pureNumber));
			startActivity(intent2);

			break;

		}

	}

	@Override
	public void onClick(View v) {
		arrayList2.clear();
		String edit = editText.getText().toString().trim();
		for (int i = 0; i < arrayList.size(); i++) {
			String name = arrayList.get(i);
			int end = name.indexOf(":");
			String searchName = name.substring(0, end);
			if (searchName.contains(edit)) {
				arrayList2.add(arrayList.get(i));
			}
			arrayAdapter2.notifyDataSetChanged();

		}

	}
}
