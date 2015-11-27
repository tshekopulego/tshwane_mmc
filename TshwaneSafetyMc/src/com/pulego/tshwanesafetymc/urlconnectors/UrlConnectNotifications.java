package com.pulego.tshwanesafetymc.urlconnectors;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.pulego.tshwanesafetymc.NotificationsActivity;
import com.pulego.tshwanesafetymc.R;
import com.pulego.tshwanesafetymc.helper.DatabaseOpenHelper;
import com.pulego.tshwanesafetymc.httpconfig.JSONParser;
import com.pulego.tshwanesafetymc.pojos.NotificationsObj;
import com.pulego.tshwanesafetymc.utils.NotificationFragment;


public class UrlConnectNotifications {
   NotificationsObj notifications;
   List<NotificationsObj> lstNotificationList;
   private String URL_CONNECT_NOTIFICATIONS="http://196.33.249.226/android/tshwanesafety/managementconsole/get_notifications.php";
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
    
	
    
	public UrlConnectNotifications(Context c) {
		super();
		context=c;
		lstNotificationList =new ArrayList<NotificationsObj>();
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
	  	  .detectDiskReads().detectDiskWrites().detectNetwork() // StrictMode is most commonly used to catch accidental disk or network access on the application's main thread
	  	  .penaltyLog().build());
	}
	public void fillNotifications(){
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
  	  .detectDiskReads().detectDiskWrites().detectNetwork() // StrictMode is most commonly used to catch accidental disk or network access on the application's main thread
  	  .penaltyLog().build());
    	 int count=0;
         // Building Parameters
         List<NameValuePair> params = new ArrayList<NameValuePair>();
         // getting JSON string from URL
         JSONObject json = jParser.makeHttpRequest(URL_CONNECT_NOTIFICATIONS, "GET", params);

         // Check your log cat for JSON reponse
         Log.d("All Notification : ", json.toString());

         try {
             // Checking for SUCCESS TAG
             int success = json.getInt(TAG_SUCCESS);
          
     	      
             if (success == 1) {
          	// initialize db
          	      db=new DatabaseOpenHelper(context);
          	     
                 
                 // Getting Array of Products
                 reports = json.getJSONArray(TAG_REPORT);
                
                 // looping through All Products
                 for (int i = 0; i < reports.length(); i++) {
                     JSONObject c = reports.getJSONObject(i);
                     
                     notifications = new NotificationsObj();
                     
                     // Storing each json item in variable
                     notifications.setId(c.getInt("id")); 
                     notifications.setTitle(c.getString("title"));
                     notifications.setMessage(c.getString("message"));
                     notifications.setNotificationdate(c.getString("notificationdate"));
                     notifications.setCategory_img(c.getString("category_img"));
                     notifications.setDate_sent(c.getString("date_sent"));
                     notifications.setPictureurl(c.getString("pictureurl"));
                     notifications.setPublishedby(c.getString("publishedby"));
                     notifications.setSent_by(c.getString("sent_by"));
                     notifications.setStatus(c.getString("status"));
                     notifications.setUpdated_by(c.getString("updated_by"));
                     
                    long _id = db.fillNotificationsTB(notifications);
                    if(_id==0){
                    	Log.d("Notification id :", "Already exist");
                    }else{
                    	
                    	if(c.getString("title") != null){
                    		lstNotificationList.add(notifications);
                    	 // NotificationFragment notifyObject = new NotificationFragment();
                    	 // notifyObject.displayNotification(c.getString("title"), c.getString("message"), c.getString("notificationdate"));
                    	}
                    }
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
	public List<NotificationsObj> getAllNotifications(){
		
		return lstNotificationList;
	}
	
}
