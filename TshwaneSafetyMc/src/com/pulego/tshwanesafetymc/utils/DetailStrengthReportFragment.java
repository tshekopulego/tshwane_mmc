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
import android.widget.LinearLayout;
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
	
	 ProgressDialog PD;
	
	TextView txtTotBikes,txtTotMembers,txtTotVehicle;
	
	TableLayout tl;
    TableRow tr;
    TextView label;
    
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
    	// urlConnectReport.populateStrengthReportResult();
    	 
    	 table = (TableLayout) rootView.findViewById(R.id.table);
    	 
    	 txtTotBikes = (TextView) rootView.findViewById(R.id.txtTotNoBikes);

    	 txtTotMembers = (TextView) rootView.findViewById(R.id.txtTotNoMembers);

    	 txtTotVehicle = (TextView) rootView.findViewById(R.id.txtTotNoCars);
    	 
    	 db = new DatabaseOpenHelper(rootView.getContext());
    	 
    	 date =getArguments().getString(KEY_DATE);
    	 
    	 shift = getArguments().getString(KEY_SHIFT);
    	 
    	// new CheckLoginDetails().execute();

    	 
    	 Log.d("Details ", date + " "+ shift );
    	 
    	 int bikes = getArguments().getInt(KEY_BIKES);
    	 
    	 int members = getArguments().getInt(KEY_MEMBERS);
    	 
    	 int vehicles = getArguments().getInt(KEY_VEHICLE);
    	 
    	 Log.d("Details ",bikes+" "+members+" "+vehicles);
    	 
    	 txtTotBikes.setText(""+bikes);
    	 
    	 txtTotMembers.setText(""+members);
    	 
    	 txtTotVehicle.setText(""+vehicles);
    	 
    	
         new Thread(new Runnable() {
             public void run() {
            	 
            	         
  
            	 getActivity().runOnUiThread(new Runnable() {
                      
                     @Override
                     public void run() {
                    	 Cursor c = db.readEntry(date, shift);
                         if(c==null){
                            Log.d("Cursor Data :", "Oops Cursor is empty");
                         }else{ 
                          	addData(c);
                         }                             
                     }
                 });
                  
             }
         }).start();

    	 
        //populateStrengthTable(date,shift);
    	// new CheckLoginDetails().execute();
    	 
    	 return rootView;
    }
     private void BuildTable() {

 		//sqlcon.open();
 		Cursor c = db.readEntry(date, shift);
       if(c==null){
    	   Log.d("Cursor Data :", "Oops Cursor is empty");
       }
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
 				tv.setTextColor(Color.BLACK);
 				tv.setTextSize(18);
 				tv.setPadding(0, 5, 0, 5);
                
 				tv.setText(c.getString(j));

 				row.addView(tv);
 				 Log.d("Check Table Status:", "In");
 			}

 			c.moveToNext();

 			table.addView(row);
 			
 			 Log.d("Check Table Status:", "Out");

 		}
 		db.closeDB();
 	}
     @SuppressWarnings({ "rawtypes" })
     public void addData(Cursor c) {
  
         addHeader();
          
         //for (Iterator i = users.iterator(); i.hasNext();) {
  
            // Users p = (Users) i.next();
          if(c.moveToFirst()){
        	  do{
	             /** Create a TableRow dynamically **/
	             tr = new TableRow(getActivity().getApplicationContext());
	  
	             /** Creating a TextView to add to the row **/
	             label = new TextView(getActivity().getApplicationContext());
	             label.setText(c.getString(c.getColumnIndex("region")));
	             label.setId(c.getInt(c.getColumnIndex("_id")));
	             label.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
	                     LayoutParams.WRAP_CONTENT));
	             label.setPadding(5, 5, 5, 5);
	             label.setBackgroundColor(Color.GRAY);
	             LinearLayout Ll = new LinearLayout(getActivity().getApplicationContext());
	             LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,
	                     LayoutParams.WRAP_CONTENT);
	             params.setMargins(5, 2, 2, 2);
	             //Ll.setPadding(10, 5, 5, 5);
	             Ll.addView(label,params);
	             tr.addView((View)Ll); // Adding textView to tablerow.
	  
	             /** Creating Qty Button **/
	             TextView bikes = new TextView(getActivity().getApplicationContext());
	             bikes.setText(c.getInt(c.getColumnIndex("bikes")));
	             bikes.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
	                     LayoutParams.WRAP_CONTENT));
	             bikes.setPadding(5, 5, 5, 5);
	             bikes.setBackgroundColor(Color.GRAY);
	             Ll = new LinearLayout(getActivity().getApplicationContext());
	             params = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,
	                     LayoutParams.WRAP_CONTENT);
	             params.setMargins(0, 2, 2, 2);
	             //Ll.setPadding(10, 5, 5, 5);
	             Ll.addView(bikes,params);
	             tr.addView((View)Ll); // Adding textview to tablerow.
	  
	             /** Creating Qty Button **/
	             TextView vehicles = new TextView(getActivity().getApplicationContext());
	             vehicles.setText(c.getInt(c.getColumnIndex("vehicles")));
	             vehicles.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
	                     LayoutParams.WRAP_CONTENT));
	             vehicles.setPadding(5, 5, 5, 5);
	             vehicles.setBackgroundColor(Color.RED);
	             Ll = new LinearLayout(getActivity().getApplicationContext());
	             params = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,
	                     LayoutParams.WRAP_CONTENT);
	             params.setMargins(0, 5, 5, 5);
	             //Ll.setPadding(10, 5, 5, 5);
	             Ll.addView(vehicles,params);
	             tr.addView((View)Ll); // Adding textview to tablerow.
	             
	             /** Creating Qty Button **/
	             TextView members = new TextView(getActivity().getApplicationContext());
	             members.setText(c.getInt(c.getColumnIndex("members")));
	             members.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
	                     LayoutParams.WRAP_CONTENT));
	             members.setPadding(5, 5, 5, 5);
	             members.setBackgroundColor(Color.RED);
	             Ll = new LinearLayout(getActivity().getApplicationContext());
	             params = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,
	                     LayoutParams.WRAP_CONTENT);
	             params.setMargins(0, 5, 5, 5);
	             //Ll.setPadding(10, 5, 5, 5);
	             table.addView(members,params);
	             tr.addView((View)Ll); // Adding textview to tablerow.
	             
	             
	              // Add the TableRow to the TableLayout
	             table.addView(tr, new TableLayout.LayoutParams(
	                     LayoutParams.FILL_PARENT,
	                     LayoutParams.WRAP_CONTENT));
        	  }while(c.moveToNext());
         }
     }
     public void addHeader(){
         /** Create a TableRow dynamically **/
         tr = new TableRow(getActivity().getApplicationContext());
  
         /** Creating a TextView to add to the row **/
         label = new TextView(getActivity().getApplicationContext());
         label.setText("Regions");
         label.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                 LayoutParams.WRAP_CONTENT));
         label.setPadding(5, 5, 5, 5);
         label.setBackgroundColor(Color.RED);
         LinearLayout Ll = new LinearLayout(getActivity().getApplicationContext());
         LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,
                 LayoutParams.WRAP_CONTENT);
         params.setMargins(5, 5, 5, 5);
         //Ll.setPadding(10, 5, 5, 5);
         Ll.addView(label,params);
         tr.addView((View)Ll); // Adding textView to tablerow.
  
         /** Creating Qty Button **/
         TextView bikes = new TextView(getActivity().getApplicationContext());
         bikes.setText("No of Bike");
         bikes.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                 LayoutParams.WRAP_CONTENT));
         bikes.setPadding(5, 5, 5, 5);
         bikes.setBackgroundColor(Color.RED);
         Ll = new LinearLayout(getActivity().getApplicationContext());
         params = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,
                 LayoutParams.WRAP_CONTENT);
         params.setMargins(0, 5, 5, 5);
         //Ll.setPadding(10, 5, 5, 5);
         Ll.addView(bikes,params);
         tr.addView((View)Ll); // Adding textview to tablerow.
         
         /** Creating Qty Button **/
         TextView vehicles = new TextView(getActivity().getApplicationContext());
         vehicles.setText("No of vehicles");
         vehicles.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                 LayoutParams.WRAP_CONTENT));
         vehicles.setPadding(5, 5, 5, 5);
         vehicles.setBackgroundColor(Color.RED);
         Ll = new LinearLayout(getActivity().getApplicationContext());
         params = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,
                 LayoutParams.WRAP_CONTENT);
         params.setMargins(0, 5, 5, 5);
         //Ll.setPadding(10, 5, 5, 5);
         Ll.addView(vehicles,params);
         tr.addView((View)Ll); // Adding textview to tablerow.
         
         /** Creating Qty Button **/
         TextView members = new TextView(getActivity().getApplicationContext());
         members.setText("No of Members");
         members.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                 LayoutParams.WRAP_CONTENT));
         members.setPadding(5, 5, 5, 5);
         members.setBackgroundColor(Color.RED);
         Ll = new LinearLayout(getActivity().getApplicationContext());
         params = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,
                 LayoutParams.WRAP_CONTENT);
         params.setMargins(0, 5, 5, 5);
         //Ll.setPadding(10, 5, 5, 5);
         Ll.addView(members,params);
         tr.addView((View)Ll); // Adding textview to tablerow.
         
          // Add the TableRow to the TableLayout
         table.addView(tr, new TableLayout.LayoutParams(
                 LayoutParams.FILL_PARENT,
                 LayoutParams.WRAP_CONTENT));
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
        	
        	
            	new Thread(new Runnable() {
                    public void run() {
                       
                      Cursor c = db.readEntry(date, shift);
                        if(c==null){
                           Log.d("Cursor Data :", "Oops Cursor is empty");
                        }else{ 
                         	addData(c);
                        }                  
         
                    }
                }).start();
            	
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
