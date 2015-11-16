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
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
//import android.support.v4.app.TaskStackBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class NotificationFragment extends Fragment{
	
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
        
        dbHelper = new DatabaseOpenHelper(rootView.getContext());
        
        lstNotifications = (ListView) rootView.findViewById(R.id.lstNotifications);
        
        displayListView();
        // new LoadStrengthReport().execute();
        
        return rootView;
 
	}
	
	private void displayListView() {
		 
		  final Cursor cursor = dbHelper.getAllNotifications();
		  if(cursor !=null){
		 dbHelper.closeDB();
		 
		  //the desired columns to be bound
		  String[] columns = new String[] {
		    DatabaseOpenHelper.NOTIFI_TITLE,
		    DatabaseOpenHelper.NOTIFI_MESSAGE,
		    DatabaseOpenHelper.NOTIFI_STATUS,
		    DatabaseOpenHelper.NOTIFI_NOTIFICATIONDATE,
		  };
		  String[] col = new String[]{"title","message","status","notificationdate"};
		  //the XML defined views which the data will be bound to
		  int[] to = new int[] { 
		    R.id.txtNotificationTitle,
		    R.id.txtNotificationMessage,
		    R.id.txtNotificationStatus,
		    R.id.txtNotificationTime
		  };
		 
		  //create the adapter using the cursor pointing to the desired data 
		  //as well as the layout information
		/*	  MyCursorAdapter dataAdapter = new MyCursorAdapter(
		    rootView.getContext(), R.layout.notitication_design_layout, 
		    cursor, 
		    col, 
		    to,
		    0);*/
	 SimpleCursorAdapter dataAdapter = new SimpleCursorAdapter(rootView.getContext(), R.layout.notitication_design_layout, cursor, col, to) ;
		 
		  // Assign adapter to ListView
		  lstNotifications.setAdapter(dataAdapter);
		  
		  lstNotifications.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View view, int position,
					long id) {
				// TODO Auto-generated method stub
				cursor.moveToPosition(position);
				String title = cursor.getString(cursor.getColumnIndex("title"));
				String msg = cursor.getString(cursor.getColumnIndex("message"));
				String date =  cursor.getString(cursor.getColumnIndex("notificationdate"));
				getNotificationDetails(title, msg, date);
				//displayNotification(title, msg, date);
			}
		});
		  
		  }else{
			  Toast.makeText(rootView.getContext(), "Cursor is empty", Toast.LENGTH_LONG).show();
		  }
		 
		 }
	
	public void displayNotification(String title,String message,String notyDate) {
	      // Invoking the default notification service
	      NotificationCompat.Builder  mBuilder = new NotificationCompat.Builder(getActivity().getApplicationContext());	

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
	      resultIntent.putExtra("from", "Tshwane Safety dashboard");
	      
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
        	//try {
        		//load data on the list view
        		 displayListView();
				
		//	} catch (Exception e) {
				// TODO: handle exception
			//	Toast.makeText(rootView.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
			//	Log.d("Strength Report Frag Error :", e.getMessage());
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
	public void getNotificationDetails(String title,String msg,String date) {
		// TODO Auto-generated method stub
		try{
		/*final Dialog dialog = new Dialog(rootView.getContext());

		// tell the Dialog to use the dialog.xml as it's layout description
		dialog.setContentView(R.layout.notification_details);
		dialog.setTitle("Notification Details");
		dialog.cancel();
		TextView txtTitle = (TextView) dialog.findViewById(R.id.txtTitleNews);
		TextView txtMsg = (TextView) dialog.findViewById(R.id.txtMessageNews);
		TextView txtDate = (TextView) dialog.findViewById(R.id.txtDateNotifi);
		txtTitle.setText(title);
		txtMsg.setText(msg);
		txtDate.setText(date);
		Button dialogButton = (Button) dialog.findViewById(R.id.btnClose);
		dialogButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				dialog.dismiss();
				
			}
		});
		dialog.show();*/
			
		   Intent notifyMe = new Intent(getActivity().getApplicationContext(), NotificationsActivity.class);
		   notifyMe.putExtra("NotifyTitle", title);
		   notifyMe.putExtra("NotifyMessage", msg);
		   notifyMe.putExtra("NotifyDate", date);
		   notifyMe.putExtra("from", "Tshwane Safety dashboard");
		   startActivity(notifyMe);
		}catch(Exception e){
			Log.e("Error ", e.getMessage());
		}

		

	}
	
}
