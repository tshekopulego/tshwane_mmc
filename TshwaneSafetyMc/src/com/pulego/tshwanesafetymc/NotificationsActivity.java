package com.pulego.tshwanesafetymc;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class NotificationsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notifications);
		
		String output = "Inside the activity of Notification two: ";
	      TextView dataIntent = (TextView) findViewById(R.id.text2);
	    
	      // take the data and the extras of the intent
	     // Uri url = getIntent().getData();
	      Bundle extras = getIntent().getExtras();
	      output += extras.getString("NotifyTitle")+"\n"+ extras.getString("NotifyMessage")+"\n\t\t"+extras.getString("NotifyDate");
	      
	      // if there are extras, add them to the output string
	      if(extras != null){
	    	  output = output + "\n from: " +extras.getString("from");
			 
	      }
	      dataIntent.setText(output);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.notifications, menu);
		return true;
	}

}
