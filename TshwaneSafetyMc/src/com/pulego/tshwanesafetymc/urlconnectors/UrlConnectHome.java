package com.pulego.tshwanesafetymc.urlconnectors;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.StrictMode;
import android.util.Log;

import com.pulego.tshwanesafetymc.helper.DatabaseOpenHelper;
import com.pulego.tshwanesafetymc.httpconfig.JSONParser;
import com.pulego.tshwanesafetymc.pojos.Incidents;
import com.pulego.tshwanesafetymc.pojos.ObjectStatus;
import com.pulego.tshwanesafetymc.pojos.ObjectType;

public class UrlConnectHome {
    private ObjectStatus objStatus;
    private ObjectType objType;
    private Incidents objIncidents;
    private Context context;
    //urls to connect
    String url_request_type="http://196.33.249.226/android/tshwanesafety/managementconsole/get_type_request.php";
    String url_request_status="http://196.33.249.226/android/tshwanesafety/managementconsole/get_status_request.php";
    String url_request_incidents="http://196.33.249.226/android/tshwanesafety/managementconsole/get_incidents_request.php";
	
 // Creating JSON Parser object
    JSONParser jParser = new JSONParser();
    
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_REPORT = "reports";
 
    // products JSONArray
    JSONArray reports = null;
    
    DatabaseOpenHelper db;
    
   public UrlConnectHome(Context context) {
		super();
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
		  .detectDiskReads().detectDiskWrites().detectNetwork() // StrictMode is most commonly used to catch accidental disk or network access on the application's main thread
		  .penaltyLog().build());
		this.context=context;
		objIncidents = new Incidents();
		objType = new ObjectType();
		objStatus = new ObjectStatus();
	}
   public void initialization(){
	   StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
		  .detectDiskReads().detectDiskWrites().detectNetwork() // StrictMode is most commonly used to catch accidental disk or network access on the application's main thread
		  .penaltyLog().build());
	   populateTblIncidents();
	   populateTblStatus();
	   populateTblType();
	   Log.d("Initialization_log", "Initialization successfully completed");
   }
   public void populateTblIncidents(){
	   int count=0;
       // Building Parameters
       List<NameValuePair> params = new ArrayList<NameValuePair>();
       // getting JSON string from URL
       JSONObject json = jParser.makeHttpRequest(url_request_incidents, "GET", params);

       // Check your log cat for JSON reponse
       Log.d("All Incidents: ", json.toString());

       try {
           // Checking for SUCCESS TAG
           int success = json.getInt(TAG_SUCCESS);

           if (success == 1) {
               // initailize db
        	    db=new DatabaseOpenHelper(context);
               // Getting Array of Products
               reports = json.getJSONArray(TAG_REPORT);

               // looping through All Products
               for (int i = 0; i < reports.length(); i++) {
                   JSONObject c = reports.getJSONObject(i);

                   // Storing each json item in variable
                   String date = c.getString("date") ;
                   int total = c.getInt("totalcount");
                   Log.d("Data",date);
                  objIncidents=new Incidents(date, total);
                  db.createIncident(objIncidents);
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
   public void populateTblStatus(){
	   int count=0;
       // Building Parameters
       List<NameValuePair> params = new ArrayList<NameValuePair>();
       // getting JSON string from URL
       JSONObject json = jParser.makeHttpRequest(url_request_status, "GET", params);

       // Check your log cat for JSON reponse
       Log.d("All Status: ", json.toString());

       try {
           // Checking for SUCCESS TAG
           int success = json.getInt(TAG_SUCCESS);

           if (success == 1) {
        	// initailize db
       	    db=new DatabaseOpenHelper(context);
               // Getting Array of Products
               reports = json.getJSONArray(TAG_REPORT);

               // looping through All Products
               for (int i = 0; i < reports.length(); i++) {
                   JSONObject c = reports.getJSONObject(i);

                   // Storing each json item in variable
                   String statusName = c.getString("status") ;
                   int total = c.getInt("totalcount");
                   Log.d("Data",statusName);
                   objStatus = new ObjectStatus(statusName, total);
                   db.createSatus(objStatus);
               }
               db.closeDB();
           } else {
               // no products found
        	   Log.e("log_status", "No data found");
             }
       } catch (JSONException e) {
           e.printStackTrace();
       }
   }
   public void populateTblType(){
	   int count=0;
       // Building Parameters
       List<NameValuePair> params = new ArrayList<NameValuePair>();
       // getting JSON string from URL
       JSONObject json = jParser.makeHttpRequest(url_request_type, "GET", params);

       // Check your log cat for JSON reponse
       Log.d("All Types: ", json.toString());

       try {
           // Checking for SUCCESS TAG
           int success = json.getInt(TAG_SUCCESS);

           if (success == 1) {
        	// initailize db
       	    db=new DatabaseOpenHelper(context);
               // Getting Array of Products
               reports = json.getJSONArray(TAG_REPORT);

               // looping through All Products
               for (int i = 0; i < reports.length(); i++) {
                   JSONObject c = reports.getJSONObject(i);

                   // Storing each json item in variable
                   String typeName = c.getString("type") ;
                   int total = c.getInt("totalcount");
                   Log.d("Data",typeName);
                   objType = new ObjectType(typeName, total);
                   db.createType(objType);
                   
               }
             db.closeDB();
           } else {
               // no products found
        	   Log.e("log_type", "No data found");
             }
       } catch (JSONException e) {
           e.printStackTrace();
       }
   }
}
