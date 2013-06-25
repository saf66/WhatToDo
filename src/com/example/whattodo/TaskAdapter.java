package com.example.whattodo;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class TaskAdapter extends ArrayAdapter<TaskItem> {

	private static class TaskItemHolder {
		CheckBox checked;
		TextView title;
		TextView subtitle;
	}

	private int layoutResourceId;
	LayoutInflater inflater;

	public TaskAdapter(Context context, int layoutResourceId, ArrayList<TaskItem> data) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;

		inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		TaskItemHolder holder = null;

		if (row == null) {
			row = inflater.inflate(layoutResourceId, parent, false);

			holder = new TaskItemHolder();
			holder.checked = (CheckBox) row.findViewById(R.id.checked);
			holder.title = (TextView) row.findViewById(R.id.title);
			holder.subtitle = (TextView) row.findViewById(R.id.subtitle);

			row.setTag(holder);
		} else {
			holder = (TaskItemHolder) row.getTag();
		}

		TaskItem o = getItem(position);
		holder.checked.setChecked(o.isChecked());
		holder.title.setText(o.getTitle());
		holder.subtitle.setText(o.getSubtitle());

		return row;
	}

}
