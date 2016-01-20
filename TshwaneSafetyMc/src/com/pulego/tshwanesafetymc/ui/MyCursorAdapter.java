package com.pulego.tshwanesafetymc.ui;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;

public class MyCursorAdapter extends SimpleCursorAdapter {

	public MyCursorAdapter(Context context, int layout, Cursor c,
			String[] from, int[] to, int flags) {
		super(context, layout, c, from, to, flags);
		// TODO Auto-generated constructor stub
	}
	 @Override 
	  public View getView(int position, View convertView, ViewGroup parent) {  
	 
	   //get reference to the row
	   View view = super.getView(position, convertView, parent); 
	   //check for odd or even to set alternate colors to the row background
	   if(position % 2 == 0){  
	    view.setBackgroundColor(Color.rgb(238, 233, 233));
	   }
	   else {
	    view.setBackgroundColor(Color.rgb(255, 255, 255));
	   }
	   return view;  
	  }  
	 
}
