package com.spacedebris.spacedebris;

import java.util.ArrayList;
import java.util.HashMap;

import com.globussoft.imageloaderlib.ImageLoader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomListAdapter extends BaseAdapter {

	// Declare Variables
	Context context;
	LayoutInflater inflater;
	ArrayList<ListMOdel> list;
    ListMOdel tempvalue;
	

    ImageLoader loader;
	public CustomListAdapter(Context context,ArrayList<ListMOdel> listmodel_array) {
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

	public View getView(final int position, View convertView, ViewGroup parent) {
		// Declare Variables
		TextView rank,name,score;
		ImageView img;
		
        
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View itemView = inflater.inflate(R.layout.listitem, parent, false);
		tempvalue=new ListMOdel();
		tempvalue =(ListMOdel) list.get(position);
		
	img=(ImageView) itemView.findViewById(R.id.listimage);
	name=(TextView) itemView.findViewById(R.id.Rank);
	rank=(TextView) itemView.findViewById(R.id.Name);
	score=(TextView) itemView.findViewById(R.id.score);
	
		
	int finalrank=position+1;
	 String url="https://graph.facebook.com/"+ tempvalue.getFB_ID()+ "/picture?type=small";
	loader.DisplayImage(url,img);
	
	
	//img.setImageBitmap(tempvalue.getImage());
	rank.setText("Rank:"+finalrank);
	name.setText(tempvalue.getName());
	score.setText("Score: "+tempvalue.getScore());
	
		return itemView;
	}
}
