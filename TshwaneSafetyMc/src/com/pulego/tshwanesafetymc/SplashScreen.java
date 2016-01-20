package com.pulego.tshwanesafetymc;

import com.pulego.tshwanesafetymc.urlconnectors.UrlConnectHome;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;

public class SplashScreen extends Activity {
	UrlConnectHome urlconnect;
	String MyPREFERENCES = "MyPrefs" ;
	String IS_LOGIN ="USRLOGEDIN";
    SharedPreferences sharedpreferences;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		//getActionBar().hide();
		
		 sharedpreferences  =  getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
		 urlconnect = new UrlConnectHome(getApplicationContext());
		 
        /****** Create Thread that will sleep for 5 seconds *************/        
        Thread background = new Thread() {
            public void run() {
                 
                try {
                    // Thread will sleep for 5 seconds
                    sleep(5*1000);
                    if(sharedpreferences.getBoolean(IS_LOGIN, false)){
                    	
                    	urlconnect.initialization();
                    	
                    	Log.d("Server Response 1", sharedpreferences.getString("USERNAME", null));
                		Log.d("Server Response 2",sharedpreferences.getString("FULLNAME", null));
                    	
                    	final Intent home=new Intent(getApplicationContext(), HomeActivity.class);
            			startActivity(home);
	                }else{
	                    // After 5 seconds redirect to another intent
	                    Intent i=new Intent(getBaseContext(),MainActivity.class);
	                    startActivity(i);
	                }  
                    //Remove activity
                    finish();
                     
                } catch (Exception e) {
                 
                }
            }
        };
         
        // start thread
        background.start();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash_screen, menu);
		return true;
	}

}
