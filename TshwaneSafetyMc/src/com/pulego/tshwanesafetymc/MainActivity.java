package com.pulego.tshwanesafetymc;

import com.pulego.tshwanesafetymc.httpconfig.ConnectionDetector;
import com.pulego.tshwanesafetymc.pojos.Login;
import com.pulego.tshwanesafetymc.pojos.LogonUser;
import com.pulego.tshwanesafetymc.urlconnectors.UrlConnectHome;
import com.pulego.tshwanesafetymc.urlconnectors.UrlConnectLogin;
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
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private EditText txtUsername,txtPassword;
    private String username,password;
    private boolean isInternetAvailable=false;;
    private ConnectionDetector c;
    private ProgressDialog pDialog; 
    private Dialog dialog;
    
    UrlConnectHome urlconnect;
    UrlConnectLogin logon;
    UrlConnectStrengthReport urlcreatereport;
    
    
    String MyPREFERENCES = "MyPrefs" ;
    String IS_LOGIN ="USRLOGEDIN";
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedpreferences  =  getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        urlconnect = new UrlConnectHome(getApplicationContext());
        
       /* if(sharedpreferences.getBoolean(IS_LOGIN, false)){
        	
            	urlconnect.initialization();
            	
            	Log.d("Server Response 1", sharedpreferences.getString("USERNAME", null));
        		Log.d("Server Response 2",sharedpreferences.getString("FULLNAME", null));
            	
            	final Intent home=new Intent(getApplicationContext(), HomeActivity.class);
    			startActivity(home);
        }else{    */    	
		        	
		        ActionBar actionBar = getActionBar();
		        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00B800")));
		        actionBar.setIcon(R.drawable.icon);
		
		        
		        txtUsername=(EditText)findViewById(R.id.email);
		        
		        txtPassword=(EditText)findViewById(R.id.password);
		        
		        TextView linkPasswordRecovery=(TextView)findViewById(R.id.btnPasswordrecovery);
		       
		        Button btnLogin=(Button)findViewById(R.id.email_sign_in_button);
		        
		       
		        
		        c=new ConnectionDetector(getApplicationContext());
		        isInternetAvailable=c.isConnectingToInternet();
		        
		        btnLogin.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						username=txtUsername.getText().toString();
						password=txtPassword.getText().toString();
						
						if(isInternetAvailable){
							logon = new UrlConnectLogin(getApplicationContext());
							
						   // urlcreatereport = new UrlConnectStrengthReport(getApplicationContext());
							//if(username != null || !username.equals("")){
							if(username.length()>0){
								Log.d("Username:", "username is"+username);
								//if(password != null || !password.equals("")){
							 if(password.length()>0){
									Log.d("Password:", "password is"+password);
							 //**************************************************************************
								 new	CheckLoginDetails().execute();
									
								/*	Login login =new Login(username, password);
						        	
						        	LogonUser user= logon.verifyLogins(login);
						        	if(user!=null){
						        		Log.d("Server Response 1", user.getUser_name());
						        		Log.d("Server Response 2", user.getUser_full_name());
						        	urlconnect.initialization();
						    		final Intent home=new Intent(getApplicationContext(), HomeActivity.class);
									home.putExtra("USER_EMAIL", username);
						        	
									startActivity(home);
						        	}else{
						        		Toast.makeText(getApplicationContext(), "Invalid logins", Toast.LENGTH_SHORT).show();
						        	}
						        	*/
							 //*****************************************************************************
								}else{
									Toast.makeText(getApplicationContext(), "Invalid password", Toast.LENGTH_SHORT).show();
								}
							}else{
								Toast.makeText(getApplicationContext(), "Invalid username", Toast.LENGTH_SHORT).show();
							}
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
        //}
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
	        	
	        	Login login =new Login(username, password);
	        	
	        	LogonUser user= logon.verifyLogins(login);
	        	if(user!=null){
	        		Log.d("Server Response 1", user.getUser_name());
	        		Log.d("Server Response 2", user.getUser_full_name());
	        		
	        		editor = sharedpreferences.edit();
	        		editor.putBoolean(IS_LOGIN, true);
	                editor.putString("USERNAME", user.getUser_name());
	                editor.putString("FULLNAME", user.getUser_full_name());
	                editor.putString("EMAIL", user.getUser_email());
	                editor.putString("CONTACT", user.getUser_phone());
	                editor.commit();
	        	urlconnect.initialization();
	    		final Intent home=new Intent(getApplicationContext(), HomeActivity.class);
				home.putExtra("USER_EMAIL", username);
	        	
				startActivity(home);
	        	}else{
	        		pDialog.dismiss();
	        		Toast.makeText(getApplicationContext(), "Invalid logins", Toast.LENGTH_SHORT).show();
	        	}
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
            	intent.setClassName("com.android.phone", "com.android.phone.Settings");
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
