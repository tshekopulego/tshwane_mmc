package com.pulego.tshwanesafetymc;

import com.loopj.android.image.SmartImageView;
import com.pulego.tshwanesafetymc.httpconfig.ServerUtilities;
import com.pulego.tshwanesafetymc.utils.InboxFragment;
import com.pulego.tshwanesafetymc.utils.NotificationFragment;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
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
		/*String output = "";
	      TextView title = (TextView) findViewById(R.id.txtTitleNews);
	      TextView message = (TextView) findViewById(R.id.txtMessageNews);
	      TextView notifyDate = (TextView) findViewById(R.id.txtDateNotifi);
	      Button btnClose = (Button)findViewById(R.id.btnClose);
	    
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
		
	      btnClose.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent fragIntent = new Intent(getApplicationContext(), NotificationFragment.class);
				startActivity(fragIntent);
				//FragmentManager frgManager = getFragmentManager();
				//Fragment fragment = new NotificationFragment();
				//frgManager.beginTransaction().replace(R.id.content_frame, fragment)
				//.commit();
			}
		});
	      */
	      
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
