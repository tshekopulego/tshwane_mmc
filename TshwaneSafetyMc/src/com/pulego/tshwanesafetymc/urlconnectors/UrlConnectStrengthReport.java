package com.pulego.tshwanesafetymc.urlconnectors;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.pulego.tshwanesafetymc.helper.DatabaseOpenHelper;
import com.pulego.tshwanesafetymc.httpconfig.JSONParser;
import com.pulego.tshwanesafetymc.pojos.DiploymentCalc;
import com.pulego.tshwanesafetymc.pojos.Incidents;
import com.pulego.tshwanesafetymc.pojos.StrengthReport;

import android.content.Context;
import android.os.StrictMode;
import android.util.Log;

public class UrlConnectStrengthReport {
   private String URL_CONNECT_STRENGTH_REPORT="http://196.33.249.226/android/tshwanesafety/managementconsole/get_strength_report.php";
   //private String URL_CONNECT_STRENGTH_REPORT="http://test.tshwanesafety.co.za/android/tshwanesafety/managementconsole/get_strength_report.php";
   private String URL_CONNECT_STRENGTH_QUERY="http://196.33.249.226/android/tshwanesafety/managementconsole/get_strength_query.php";
   // Creating JSON Parser object
   JSONParser jParser = new JSONParser();
   //Activity context
   private Context context;
   // JSON Node names
   private static final String TAG_SUCCESS = "success";
   private static final String TAG_REPORT = "reports";

   // products JSONArray
   JSONArray reports = null;
   //SQlite database 
    DatabaseOpenHelper db;
    //strength report class
    StrengthReport strengthReport;
    DiploymentCalc diploymentCalc;
    
	public UrlConnectStrengthReport(Context context) {
	super();
	this.context  = context;
	StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
	  .detectDiskReads().detectDiskWrites().detectNetwork() // StrictMode is most commonly used to catch accidental disk or network access on the application's main thread
	  .penaltyLog().build());
    }
    public void populateStrengthReportResult(){
    	StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
  	  .detectDiskReads().detectDiskWrites().detectNetwork() // StrictMode is most commonly used to catch accidental disk or network access on the application's main thread
  	  .penaltyLog().build());
    	 int count=0;
         // Building Parameters
         List<NameValuePair> params = new ArrayList<NameValuePair>();
         // getting JSON string from URL
         JSONObject json = jParser.makeHttpRequest(URL_CONNECT_STRENGTH_REPORT, "GET", params);

         // Check your log cat for JSON reponse
         Log.d("All Incidents: ", json.toString());

         try {
             // Checking for SUCCESS TAG
             int success = json.getInt(TAG_SUCCESS);
          
     	      
             if (success == 1) {
          	// initialize db
          	      db=new DatabaseOpenHelper(context);
          	      
          	    List<StrengthReport> ids = db.getAllStrengthReportIDs();
      
                 // Getting Array of Products
                 reports = json.getJSONArray(TAG_REPORT);
                
                 // looping through All Products
                 for (int i = 0; i < reports.length(); i++) {
                     JSONObject c = reports.getJSONObject(i);

                     // Storing each json item in variable
                     int id = c.getInt("id"); 
                     String date = c.getString("date");
                     String shift = c.getString("shift");
                     String reported_by = c.getString("reported_by");
                     String supervisor = c.getString("supervisor");
                     String region = c.getString("region");
                     int members = c.getInt("members");
                     int vehicles = c.getInt("vehicles");
                     int bikes = c.getInt("bikes");
                     String region_ob = c.getString("region_ob");
                     String nodal_ob = c.getString("nodal_ob");
                     String remarks = c.getString("remarks");
                     String nodal_remarks = c.getString("nodal_remarks");
                     String nodal_ob_capturedby = c.getString("nodal_ob_capturedby");
                     Log.d("id",""+id);
                   
                      strengthReport =new StrengthReport(id, date, shift, reported_by,
                    		 supervisor, region, members, vehicles, bikes,
                    		 region_ob, nodal_ob, remarks, nodal_remarks,
                    		 nodal_ob_capturedby);
                      
                    if(ids.isEmpty()){// change to ids.size() == 0
                    
                     Log.d("Url Connect Strength", "Data here now ids is empty");
                     //db=new DatabaseOpenHelper(context);
                     db.createStrengthReport(strengthReport);
                    } else{
                    	 for(int x=0; x < ids.size(); x++){
                    		 if(id != ids.get(x).getId()){
                    			// db=new DatabaseOpenHelper(context);
                                 db.createStrengthReport(strengthReport);
                    		 }
                    	 }
                    	 
                    }
                  
                   // db.closeDB();
                 }
                 db.closeDB();
             } else {
                 // no products found
          	   Log.e("log_incidents", "No data found");
               }
         } catch (JSONException e) {
             e.printStackTrace();
         }
    }
    public void populateDiploymentTB(){
    	StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
    	  .detectDiskReads().detectDiskWrites().detectNetwork() // StrictMode is most commonly used to catch accidental disk or network access on the application's main thread
    	  .penaltyLog().build());
      	 int count=0;
           // Building Parameters
           List<NameValuePair> params = new ArrayList<NameValuePair>();
           // getting JSON string from URL
           JSONObject json = jParser.makeHttpRequest(URL_CONNECT_STRENGTH_QUERY, "GET", params);

           // Check your log cat for JSON reponse
           Log.d("All Strength report: ", json.toString());

           try {
               // Checking for SUCCESS TAG
               int success = json.getInt(TAG_SUCCESS);
            
       	      
               if (success == 1) {
            	// initialize db
            	      db=new DatabaseOpenHelper(context);
            	      
            	    List<DiploymentCalc> ids = db.getAllDiploymentReportIDs();
        
                   // Getting Array of Products
                   reports = json.getJSONArray(TAG_REPORT);
                  
                   // looping through All Products
                   for (int i = 0; i < reports.length(); i++) {
                       JSONObject c = reports.getJSONObject(i);

                       // Storing each json item in variable
                       int id = c.getInt("id"); 
                       String date = c.getString("date");
                       String shift = c.getString("shift");;
                       int members = c.getInt("total_members");
                       int vehicles = c.getInt("total_vehicles");
                       int bikes = c.getInt("total_bikes");
                       String progress = c.getString("progress");
                      
                       Log.d("id",""+id);
                     
                       diploymentCalc=new DiploymentCalc(id, date, shift, members, vehicles, bikes, progress);
                       
                       
                      if(ids.isEmpty()){// change to ids.size() == 0
                    	   Log.d("Url Connect Strength", "Data here now ids is empty");
                    	   
                            db.addDiploymentCalculations(diploymentCalc);
                      } else{
                      	 for(int x=0; x < ids.size(); x++){
                      		 if(id != ids.get(x).getId()){
                      			// db=new DatabaseOpenHelper(context);
                      			db.addDiploymentCalculations(diploymentCalc);
                      		 }
                      	 }
                      	 
                      }
                    
                     // db.closeDB();
                   }
                   db.closeDB();
               } else {
                   // no products found
            	   Log.e("log_incidents", "No data found");
                 }
           } catch (JSONException e) {
               e.printStackTrace();
           }
    }
	public String getURL_CONNECT_STRENGTH_REPORT() {
		return URL_CONNECT_STRENGTH_REPORT;
	}
	
	public void setURL_CONNECT_STRENGTH_REPORT(String uRL_CONNECT_STRENGTH_REPORT) {
		URL_CONNECT_STRENGTH_REPORT = uRL_CONNECT_STRENGTH_REPORT;
	}
}
