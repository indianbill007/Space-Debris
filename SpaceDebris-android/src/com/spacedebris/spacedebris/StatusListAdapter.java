package com.spacedebris.spacedebris;

import java.util.ArrayList;

import com.globussoft.imageloaderlib.ImageLoader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class StatusListAdapter extends BaseAdapter {

	// Declare Variables
	Context context;
	LayoutInflater inflater;
	ArrayList<ListMOdel> list;
	ListMOdel tempvalue;
	ImageLoader loader;

	public StatusListAdapter(Context context,ArrayList<ListMOdel> listmodel_array) {
		this.context = context;
       list=listmodel_array;
       loader=new ImageLoader(context);
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

	public View getView(final int position, View convertView, ViewGroup parent) 
	{
		// Declare Variables
		TextView name,level;
		ImageView img;
		
        
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View itemView = inflater.inflate(R.layout.status_item, parent, false);
		tempvalue=new ListMOdel();
		tempvalue =(ListMOdel) list.get(position);
		
	img=(ImageView) itemView.findViewById(R.id.listimage);
	name=(TextView) itemView.findViewById(R.id.Name);
		level=(TextView) itemView.findViewById(R.id.level);
	
		 String url="https://graph.facebook.com/"+ tempvalue.getFB_ID()+ "/picture?type=small";
			loader.DisplayImage(url,img);

	
	//img.setImageBitmap(tempvalue.getImage());
	name.setText(tempvalue.getName());
	level.setText(""+tempvalue.getLevel());

	
		return itemView;
	}
}
