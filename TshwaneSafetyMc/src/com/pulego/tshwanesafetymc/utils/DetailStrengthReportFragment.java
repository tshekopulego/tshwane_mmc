package com.pulego.tshwanesafetymc.utils;

import java.util.List;

import com.pulego.tshwanesafetymc.R;
import com.pulego.tshwanesafetymc.helper.DatabaseOpenHelper;
import com.pulego.tshwanesafetymc.pojos.StrengthReport;
import com.pulego.tshwanesafetymc.urlconnectors.UrlConnectStrengthReport;

import android.app.Dialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.database.Cursor;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView.FindListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class DetailStrengthReportFragment extends Fragment {
    UrlConnectStrengthReport urlConnectReport;
   // UrlConnectStrengthReport urlcreatereport;
    private View rootView;
    
    private TableLayout table;
    
    DatabaseOpenHelper db;
    
    public static final String KEY_SHIFT = "shift";
	public static final String KEY_DATE = "date";
	
	public static final String KEY_MEMBERS = "members";
	public static final String KEY_BIKES = "bikes";
	public static final String KEY_VEHICLE = "vehicle";
	
	private String shift;
	private String date;
	
    private ProgressDialog pDialog; 
	
	private Dialog dialog;  
	
	TextView txtTotBikes,txtTotMembers,txtTotVehicle;
    
	public DetailStrengthReportFragment() {
		super();
	}
	
     @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
    		Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	 rootView=inflater.inflate(R.layout.fragment_strength_details_layout, container, false);
    	
    	 urlConnectReport = new UrlConnectStrengthReport(rootView.getContext());
    	//populate strength report on the db
    	 urlConnectReport.populateStrengthReportResult();
    	 
    	 table = (TableLayout) rootView.findViewById(R.id.table);
    	 
    	 txtTotBikes = (TextView) rootView.findViewById(R.id.txtTotNoBikes);

    	 txtTotMembers = (TextView) rootView.findViewById(R.id.txtTotNoMembers);

    	 txtTotVehicle = (TextView) rootView.findViewById(R.id.txtTotNoCars);
    	 
    	 db = new DatabaseOpenHelper(rootView.getContext());
    	 
    	 date =getArguments().getString(KEY_DATE);
    	 
    	 shift = getArguments().getString(KEY_SHIFT);
    	 
    	 Log.d("Details ", date + " "+ shift +" "+getArguments().getInt(KEY_BIKES));
    	 
    	 int bikes = getArguments().getInt(KEY_BIKES);
    	 
    	 int members = getArguments().getInt(KEY_MEMBERS);
    	 
    	 int vehicles = getArguments().getInt(KEY_VEHICLE);
    	 
    	 Log.d("Details ",bikes+" "+members+" "+vehicles);
    	 
    	 txtTotBikes.setText(""+bikes);
    	 
    	 txtTotMembers.setText(""+members);
    	 
    	 txtTotVehicle.setText(""+vehicles);
    	 
    	
    	 BuildTable();
    	 
    	// populateStrengthTable(date,shift);
    	// new CheckLoginDetails().execute();
    	 
    	 return rootView;
    }
     private void BuildTable() {

 		//sqlcon.open();
 		Cursor c = db.readEntry(date, shift);

 		int rows = c.getCount();
 		int cols = c.getColumnCount();

 		c.moveToFirst();

 		// outer for loop
 		for (int i = 0; i < rows; i++) {

 			TableRow row = new TableRow(rootView.getContext());
 			row.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
 					LayoutParams.WRAP_CONTENT));

 			// inner for loop
 			for (int j = 0; j < cols; j++) {

 				TextView tv = new TextView(rootView.getContext());
 				tv.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
 						LayoutParams.WRAP_CONTENT));
 				tv.setBackgroundResource(R.drawable.button_layer);
 				tv.setGravity(Gravity.CENTER);
 				tv.setTextSize(18);
 				tv.setPadding(0, 5, 0, 5);

 				tv.setText(c.getString(j));

 				row.addView(tv);

 			}

 			c.moveToNext();

 			table.addView(row);

 		}
 		db.closeDB();
 	}
     
     public void populateStrengthTable(String strDate,String strShift){
 		TableLayout t1;
        
         TableLayout tl = (TableLayout)table;
 		//Create table row header to hold the column headings

 		TableRow tr_head = new TableRow(rootView.getContext());
 		tr_head.setId(10);
 		tr_head.setBackgroundColor(Color.GRAY);
 		tr_head.setLayoutParams(new LayoutParams(
 		LayoutParams.MATCH_PARENT,
 		LayoutParams.WRAP_CONTENT));
 		
        //Add two data sections to the table row
 		
 		TextView label_regions = new TextView(rootView.getContext());
 		label_regions.setId(20);
 		label_regions.setText("Regions");
 		label_regions.setTextColor(Color.WHITE);
 		label_regions.setPadding(5, 5, 5, 5);
		 tr_head.addView(label_regions);
 		
		 int[] images = {R.drawable.ic_list_person,R.drawable.ic_list_motorcycle,R.drawable.ic_list_car};
 		
		 /*
		 ImageView imageView = new ImageView(rootView.getContext());
	     imageView.setId(21);
	     imageView.setImageResource(R.drawable.ic_list_person);
	     // imageView.setBackgroundColor(Color.GREEN);
	     imageView.setPadding(5, 5, 5, 5);
	     tr_head.addView(imageView);// add th
		 
	     ImageView imageView2 = new ImageView(rootView.getContext());
	     imageView.setId(23);
	     imageView.setImageResource(R.drawable.ic_list_motorcycle);
	    // imageView.setBackgroundColor(Color.GREEN);
	     imageView.setPadding(5, 5, 5, 5);
			 tr_head.addView(imageView);// add th
			 
			 ImageView imageView3 = new ImageView(rootView.getContext());
    	     imageView.setId(25);
    	     imageView.setImageResource(R.drawable.ic_list_car);
    	     //imageView.setBackgroundColor(Color.GREEN);
    	     imageView.setPadding(5, 5, 5, 5);
				 tr_head.addView(imageView);// add th
				 
				 */
 		int index=0;
 		
      /*  for(int img=0;img > images.length ; img++){
 		//TextView label_date = new TextView(this);
        	ImageView imageView = new ImageView(rootView.getContext());
        	     imageView.setId(20+index);
        	     imageView.setImageResource(images[img]);
        	     imageView.setBackgroundColor(Color.GREEN);
        	     imageView.setPadding(5, 5, 5, 5);
 				 tr_head.addView(imageView);// add the column to the table row here
 				 index++;
        }*/
 			
 		//After adding the columns to the table row its time to add the table row the the main table layout that we fetched at the start

 		tl.addView(tr_head, new TableLayout.LayoutParams(
 						 LayoutParams.MATCH_PARENT,
 						 LayoutParams.WRAP_CONTENT));
 						 
 		//Now in similar fashion you can query the database or any array to get the repeat content, create the column, set properties, and add the same to the table row

 		int count=0;
 		
     	
  

         try {
        	 
               List<StrengthReport> reports = db.getAllStrengthReportRecordsPerShift(strDate, strShift);
                db.closeDB();
             if (reports != null) {
                 
                 // looping through All records
                 for (int i = 0; i < reports.size(); i++) {
                	 
                    StrengthReport objReport = reports.get(i);

                  // Create the table row
            			TableRow tr = new TableRow(rootView.getContext());
            			if(count%2!=0) tr.setBackgroundColor(Color.GRAY);
            			tr.setId(100+count);
            			tr.setLayoutParams(new LayoutParams(
            			LayoutParams.MATCH_PARENT,
            			LayoutParams.WRAP_CONTENT));
            			
            			
            			//Create two columns to add as table data
 	       			 // Create a TextView to add date
 	       			TextView labelREGION = new TextView(rootView.getContext());
	 	       		labelREGION.setId(200+count); 
		 	       	labelREGION.setText(objReport.getRegion());
		 	        labelREGION.setPadding(2, 0, 8, 0);
		 	        labelREGION.setTextColor(Color.WHITE);
 	       			tr.addView(labelREGION);
 	       			
 	       			TextView labelMEMBER = new TextView(rootView.getContext());
 	         		labelMEMBER.setId(200+count);
 	            	labelMEMBER.setText(objReport.getMembers());
 	                labelMEMBER.setPadding(2, 0, 8, 0);
 	                labelMEMBER.setTextColor(Color.WHITE);
 	       			tr.addView(labelMEMBER);
 	       			
 	       			TextView labelBIKE = new TextView(rootView.getContext());
 	       	    	labelBIKE.setId(200+count);
		 	       	labelBIKE.setText(objReport.getBikes());
		 	        labelBIKE.setTextAlignment(15);
		 	        labelBIKE.setPadding(2, 0, 8, 0);
		 	        labelBIKE.setTextColor(Color.WHITE);
		 	       			tr.addView(labelBIKE);
 	       			
 	       			TextView labelVEH = new TextView(rootView.getContext());
 	       			labelVEH.setId(200+count);
 	       			labelVEH.setText(objReport.getVehicles());
 	       		    labelVEH.setPadding(2, 0, 8, 0);
 	       			labelVEH.setTextColor(Color.WHITE);
 	       			tr.addView(labelVEH);
 	       			
 	       			// finally add this to the table row
 	       			tl.addView(tr, new TableLayout.LayoutParams(
 	       									LayoutParams.WRAP_CONTENT,
 	       									LayoutParams.WRAP_CONTENT));
                    count++;
                     
                 }
             } else {
                 // no products found
                Toast.makeText(rootView.getContext(), "No data found", Toast.LENGTH_SHORT).show();
             }
         } catch (Exception e) {
             e.printStackTrace();
         }
 	}
     class CheckLoginDetails extends AsyncTask<String, String, String> {
       	 
         /**
          * Before starting background thread Show Progress Dialog
          * */
         @Override
         protected void onPreExecute() {
             super.onPreExecute();
             pDialog = new ProgressDialog(rootView.getContext());
             pDialog.setMessage("Loading please wait..");
             pDialog.setIndeterminate(false);
             pDialog.setCancelable(true);
             pDialog.show();
         }
  
         /**
          * Creating product
          * */
         protected String doInBackground(String... args) {
        	// try {
        	//populate strength report on the db
        	// urlConnectReport.populateStrengthReportResult();
        	 
        		 populateStrengthTable(date, shift);
			//} catch (Exception e) {
				// TODO: handle exception
				//Log.d("Details Error", e.getMessage());
				
			//}
         	  
            return null;
         }
  
         /**
          * After completing background task Dismiss the progress dialog
          * **/
         protected void onPostExecute(String file_url) {
             // dismiss the dialog once done
             pDialog.dismiss();
         }
    }
}
