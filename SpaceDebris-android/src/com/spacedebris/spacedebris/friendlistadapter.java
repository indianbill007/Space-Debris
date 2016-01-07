package com.spacedebris.spacedebris;

import java.util.ArrayList;

import com.globussoft.imageloaderlib.ImageLoader;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class friendlistadapter extends BaseAdapter implements
		CompoundButton.OnCheckedChangeListener {

	// Declare Variables

	Context context;
	LayoutInflater inflater;
	ArrayList<FriendListModel> list;
	FriendListModel tempvalue;
	
	public int counter = 0;

	public SparseBooleanArray mCheckStates;

	public friendlistadapter(Context context,
			ArrayList<FriendListModel> listmodel_array) {
		this.context = context;
		list = listmodel_array;
		mCheckStates = new SparseBooleanArray(listmodel_array.size());

		for (int i = 0; i < listmodel_array.size(); ++i) {

			setChecked(i, false);
		}
		
		if (listmodel_array.size()>50) {
			for (int i = 0; i < 50; ++i) {

				counter++;
				if (counter>1) {
					MainActivity.counter.setText(counter + " friends selected");
				}
				else {
					MainActivity.counter.setText(counter + " friend selected");
				}
				setChecked(i, true);
			}
		}
		else {
			for (int i = 0; i < listmodel_array.size(); ++i) {
				counter++;
				if (counter>1) {
					MainActivity.counter.setText(counter + " friends selected");
				}
				else {
					MainActivity.counter.setText(counter + " friend selected");
				}
				
				setChecked(i, true);
			}
		}
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		// Declare Variables
		TextView name;
		ImageView img;
		CheckBox check;

		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View itemView = inflater
				.inflate(R.layout.friendlistitem, parent, false);
		tempvalue = new FriendListModel();
		tempvalue = (FriendListModel) list.get(position);

		
		name = (TextView) itemView.findViewById(R.id.friend_name);
		check = (CheckBox) itemView.findViewById(R.id.chkbutton);


		name.setText(tempvalue.getName());

		check.setTag(position);
		check.setChecked(mCheckStates.get(position, false));
		check.setOnCheckedChangeListener(this);

		return itemView;
	}

	public boolean isChecked(int position) {
		return mCheckStates.get(position, false);
	}

	public void setChecked(int position, boolean isChecked) {
		mCheckStates.put(position, isChecked);

	}

	public void toggle(int position) {
		setChecked(position, !isChecked(position));

	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

		mCheckStates.put((Integer) buttonView.getTag(), isChecked);

		System.out.println("isChecked " + isChecked);

		if (isChecked) {
			counter++;

		} else {
			counter--;
		}

		
		
		if (counter>1) {
			MainActivity.counter.setText(counter + " friends selected");
		}
		else {
			MainActivity.counter.setText(counter + " friend selected");
		}

		
		
	}
	
	
	
}
