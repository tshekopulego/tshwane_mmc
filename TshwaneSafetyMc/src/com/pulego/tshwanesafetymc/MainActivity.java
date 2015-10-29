package com.pulego.tshwanesafetymc;

import com.pulego.tshwanesafetymc.httpconfig.ConnectionDetector;
import com.pulego.tshwanesafetymc.urlconnectors.UrlConnectHome;
import com.pulego.tshwanesafetymc.urlconnectors.UrlConnectStrengthReport;

import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private EditText txtEmail,txtPassword;
    private String email,password;
    private boolean isInternetAvailable=false;;
    private ConnectionDetector c;
    private ProgressDialog pDialog; 
    private Dialog dialog;
    
    UrlConnectHome urlconnect;
    UrlConnectStrengthReport urlcreatereport;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ActionBar actionBar = getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00B800")));
        actionBar.setIcon(R.drawable.icon);
        
        txtEmail=(EditText)findViewById(R.id.email);
        
        txtPassword=(EditText)findViewById(R.id.password);
        
        TextView linkPasswordRecovery=(TextView)findViewById(R.id.btnPasswordrecovery);
       
        Button btnLogin=(Button)findViewById(R.id.email_sign_in_button);
        
        c=new ConnectionDetector(getApplicationContext());
        isInternetAvailable=c.isConnectingToInternet();
        
        btnLogin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				email=txtEmail.getText().toString();
				password=txtPassword.getText().toString();
				
				if(isInternetAvailable){
					
					urlconnect = new UrlConnectHome(getApplicationContext());
				   // urlcreatereport = new UrlConnectStrengthReport(getApplicationContext());
					 //**************************************************************************
						 new	CheckLoginDetails().execute();
					 //*****************************************************************************
				}else{
					showAlertDialog(MainActivity.this, "No Internet Connection",
                            "You don't have internet connection.", false);
				}
			}
		});
        linkPasswordRecovery.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent recovery=new Intent(getApplicationContext(), PasswordResetActivity.class);
				startActivity(recovery);
			}
		});
    }

    class CheckLoginDetails extends AsyncTask<String, String, String> {
   	 
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Signing in please wait..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
 
        /**
         * Creating product
         * */
        protected String doInBackground(String... args) {
        	//Initializing the home page
        	try{
	        	urlconnect.initialization();
	        	
	        	//urlcreatereport.populateStrengthReportResult();
	        	
	        	//urlcreatereport.populateDiploymentTB();
	        	
	    		final Intent home=new Intent(getApplicationContext(), HomeActivity.class);
				home.putExtra("USER_EMAIL", email);
				
				startActivity(home);
        	}catch(Exception err){
        		Toast.makeText(getApplicationContext(), err.getMessage(), Toast.LENGTH_LONG).show();
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
    public void showAlertDialog(Context context, String title, String message, Boolean status) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
 
        // Setting Dialog Title
        alertDialog.setTitle(title);
 
        // Setting Dialog Message
        alertDialog.setMessage(message);
         
        // Setting alert dialog icon
        alertDialog.setIcon((status) ? R.drawable.success : R.drawable.fail);
 
        // Setting OK Button
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            	Intent intent = new Intent(Intent.ACTION_MAIN);
            	intent.setClassName("com.android.phone", "com.android.phone.NetworkSetting");
            	startActivity(intent);
            }
        });
 
        // Showing Alert Message
        alertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
