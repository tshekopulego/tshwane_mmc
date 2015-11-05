package com.pulego.tshwanesafetymc.utils;

import com.pulego.tshwanesafetymc.NotificationsActivity;
import com.pulego.tshwanesafetymc.R;
import com.pulego.tshwanesafetymc.helper.DatabaseOpenHelper;

import android.app.ActionBar;
import android.app.Dialog;
import android.app.Fragment;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
//import android.support.v4.app.TaskStackBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class NotificationFragment extends Fragment {
	
	private View rootView;
	
	private DatabaseOpenHelper dbHelper;
	
	private ListView lstNotifications;
	
    private NotificationManager myNotificationManager;
	   
    private int notificationId = 112;
	    
    private int numNotification = 0;
    
    private ProgressDialog pDialog; 
	
	private Dialog dialog;
   
	
    public NotificationFragment(){
    	
    }
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		rootView = inflater.inflate(R.layout.fragment_notifications_layout,container, false);
		
		ActionBar actionBar = getActivity().getActionBar();
		 
        // Screen handling while hiding ActionBar icon.
       actionBar.setDisplayShowHomeEnabled(true);
 
        // Screen handling while hiding Actionbar title.
        actionBar.setDisplayShowTitleEnabled(true);
 
        // Creating ActionBar tabs.
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        
        dbHelper = new DatabaseOpenHelper(getActivity().getApplicationContext());
        
        lstNotifications = (ListView) rootView.findViewById(R.id.lstNotifications);
        
       // displayListView();
        new LoadStrengthReport().execute();
        
        return rootView;
 
	}
	
	private void displayListView() {
		 
		  Cursor cursor = dbHelper.getAllNotifications();
		 
		  //the desired columns to be bound
		  String[] columns = new String[] {
		    DatabaseOpenHelper.NOTIFI_TITLE,
		    DatabaseOpenHelper.NOTIFI_MESSAGE,
		    DatabaseOpenHelper.NOTIFI_STATUS,
		    DatabaseOpenHelper.NOTIFI_NOTIFICATIONDATE,
		  };
		 
		  //the XML defined views which the data will be bound to
		  int[] to = new int[] { 
		    R.id.txtNotificationTitle,
		    R.id.txtNotificationMessage,
		    R.id.txtNotificationStatus,
		    R.id.txtNotificationTime
		  };
		 
		  //create the adapter using the cursor pointing to the desired data 
		  //as well as the layout information
		  MyCursorAdapter dataAdapter = new MyCursorAdapter(
		    rootView.getContext(), R.layout.notitication_design_layout, 
		    cursor, 
		    columns, 
		    to,
		    0);
		 
		 
		  // Assign adapter to ListView
		  lstNotifications.setAdapter(dataAdapter);
		 
		 
		 }
	
	public void displayNotification(String title,String message,String notyDate) {
	      // Invoking the default notification service
	      NotificationCompat.Builder  mBuilder = new NotificationCompat.Builder(rootView.getContext());	

	      mBuilder.setContentTitle("New Notification with implicit intent");
	      mBuilder.setContentText("New notification from Tshwane Safety dashboard received...");
	      mBuilder.setTicker("Tshwane Safety: New Notification Received!");
	      mBuilder.setSmallIcon(R.drawable.ic_list_notification);
	      
	      NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();

	      /* String[] events = new String[3];
	       events[0] = new String("1) Message for implicit intent");
	       events[1] = new String("2) big view Notification");
	       events[2] = new String("3) from javacodegeeks!");*/

	       // Sets a title for the Inbox style big view
	       inboxStyle.setBigContentTitle(title);
	       // Moves events into the big view
	      // for (int i=0; i < events.length; i++) {
	          inboxStyle.addLine(message);
	       //}
	       mBuilder.setStyle(inboxStyle);
	        
	      // Increase notification number every time a new notification arrives
	      mBuilder.setNumber(++numNotification);

	      // when the user presses the notification, it is auto-removed
	      mBuilder.setAutoCancel(true);
	      
	      // Creates an implicit intent 
	      Intent resultIntent = new Intent(rootView.getContext(), NotificationsActivity.class);
	      resultIntent.putExtra("NotifyTitle", title);
	      resultIntent.putExtra("NotifyMessage", message);
	      resultIntent.putExtra("NotifyDate", notyDate);
	      /*Intent resultIntent = new Intent("com.example.javacodegeeks.TEL_INTENT", 
	    		  Uri.parse("Tel:123456789"));*/
	      resultIntent.putExtra("from", "Tshwane Safety Management Console");
	      
	      TaskStackBuilder stackBuilder = TaskStackBuilder.create(getActivity().getApplicationContext());
	      stackBuilder.addParentStack(NotificationsActivity.class);

	      stackBuilder.addNextIntent(resultIntent);
	      PendingIntent resultPendingIntent =
	         stackBuilder.getPendingIntent(
	            0,
	            PendingIntent.FLAG_ONE_SHOT
	         );
	      mBuilder.setContentIntent(resultPendingIntent);

	      myNotificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);

	      myNotificationManager.notify(notificationId, mBuilder.build());   

	   }
	
	public class LoadStrengthReport extends AsyncTask<String, String, String> {
	   	 
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
        	try {
        		//load data on the list view
        		 displayListView();
				
			} catch (Exception e) {
				// TODO: handle exception
			//	Toast.makeText(rootView.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
				Log.d("Strength Report Frag Error :", e.getMessage());
			}
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
