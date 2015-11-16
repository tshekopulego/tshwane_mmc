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
		
		String output = "";
	      TextView title = (TextView) findViewById(R.id.txtTitleNews);
	      TextView message = (TextView) findViewById(R.id.txtMessageNews);
	      TextView notifyDate = (TextView) findViewById(R.id.txtDateNotifi);
	    
	      // take the data and the extras of the intent
	     // Uri url = getIntent().getData();
	      Bundle extras = getIntent().getExtras();
	      output +=  extras.getString("NotifyMessage");
	      
	      // if there are extras, add them to the output string
	      if(extras != null){
	    	  output = output + "\n from: " +extras.getString("from");
			 
	      }
	      title.setText(extras.getString("NotifyTitle"));
	      message.setText(output);
	      notifyDate.setText(extras.getString("NotifyDate"));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.notifications, menu);
		return true;
	}

}
