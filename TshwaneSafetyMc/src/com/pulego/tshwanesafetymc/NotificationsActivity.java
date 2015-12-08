package com.pulego.tshwanesafetymc;

import com.loopj.android.image.SmartImageView;
import com.pulego.tshwanesafetymc.httpconfig.ServerUtilities;


import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.widget.TextView;

public class NotificationsActivity extends Activity {
	String subtitle;
	String title;
	String pictureurl;
	String datetime;
	String duration;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_notifications);
		setContentView(R.layout.notification_detail);
		 ActionBar actionBar = getActionBar();
	        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00B800")));
	
	      
	      Bundle extras = getIntent().getExtras();
			title = extras.getString("title");
			subtitle = extras.getString("message");
			
			pictureurl = extras.getString("pictureurl");
			datetime = extras.getString("datetime");

			TextView titleTextView = (TextView) findViewById(R.id.title);
			titleTextView.setText(title);

			TextView subtitleTextView = (TextView) findViewById(R.id.subTitle);
			subtitleTextView.setText(subtitle);
			
			TextView datetimeTextView = (TextView) findViewById(R.id.duration);
			datetimeTextView.setText(datetime);
			

			SmartImageView img = (SmartImageView) findViewById(R.id.loaderImageView);
			img.setImageUrl(pictureurl);
			
			ServerUtilities.getTimeDifference(datetime, datetimeTextView);
	}
 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.notifications, menu);
		return true;
	}

}
