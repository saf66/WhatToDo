package com.example.whattodo;

import java.util.ArrayList;

import android.os.Bundle;
import android.os.Vibrator;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;

// http://stackoverflow.com/questions/13648181/refresh-data-in-listview-when-data-from-server
// http://stackoverflow.com/questions/1111980/how-to-handle-screen-orientation-change-when-progress-dialog-and-background-thre
public class MainActivity extends Activity implements OnItemClickListener,OnItemLongClickListener {

	private static final String TAG = MainActivity.class.getName();

	private ArrayList<TaskItem> data = new ArrayList<TaskItem>();

	private Vibrator vibe;
	private TaskAdapter adapter;

	@Override
	//[TODO] rotate event causes reload of data, interrupts longer-lived processes
	//[TODO] switch to details view on click
	//[TODO] open context menu on long click -> delete
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

		ListView lv = (ListView) findViewById(R.id.list);
		lv.setOnItemClickListener(this);
		lv.setOnItemLongClickListener(this);

		adapter = new TaskAdapter(this, R.layout.row, data);
		lv.setAdapter(adapter);
	}

	@Override
	public void onRestart() {
		super.onRestart();
	}

	@Override
	public void onStart() {
		super.onStart();

		// fill with demo data
		data.clear();
		for (int i = 0; i < 20; i++) {
			data.add(new TaskItem(false, "SF services", "Pending"));
			data.add(new TaskItem(true, "SF Advertisement", "Completed"));
		}
		adapter.notifyDataSetChanged();
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onStop() {
		super.onStop();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle option selection
		switch (item.getItemId()) {
			case R.id.action_settings:
				//startActivity(new Intent(this, SettingsActivity.class));
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Log.i(TAG, "onItemClick: " + parent.getItemAtPosition(position).toString());
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
		Log.i(TAG, "onItemLongClick: " + parent.getItemAtPosition(position).toString());
		vibe.vibrate(20);
		return true;
	}

}
