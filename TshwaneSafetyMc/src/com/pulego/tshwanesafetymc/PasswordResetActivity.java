package com.pulego.tshwanesafetymc;

import com.pulego.tshwanesafetymc.pojos.ChangePassword;
import com.pulego.tshwanesafetymc.urlconnectors.UrlConnectChangePassword;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class PasswordResetActivity extends Activity {
	private ProgressDialog pDialog; 
    private Dialog dialog;
	EditText txtPayNumber,txtEmailAddress,txtPassword,txtRe_enterPassword;
    String password,payNo,email;
    ChangePassword passwordObject;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_password_reset);
		// Show the Up button in the action bar.
		setupActionBar();
		ActionBar actionBar = getActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00B800")));
        actionBar.setIcon(R.drawable.icon);
        
        txtPayNumber =(EditText)findViewById(R.id.txtPayNo);
        txtEmailAddress =(EditText)findViewById(R.id.txtEmailAddress);
        txtPassword = (EditText)findViewById(R.id.txtPasswordReset);
        txtRe_enterPassword = (EditText)findViewById(R.id.txtRePasswordReset);
        payNo = txtPayNumber.getText().toString();
        email = txtEmailAddress.getText().toString();
        
        if(payNo.isEmpty()==false){
        	if(email.isEmpty()==false){
        		 password = comparePassword(txtPassword.getText().toString(),txtRe_enterPassword.getText().toString());
        	    if(password != null){
        	    	Toast.makeText(getApplicationContext(), "Please wait changes on prograss..", Toast.LENGTH_SHORT).show();
        	    	passwordObject=new ChangePassword(payNo, email, password);
        	    	//Content
        	    	new ChangePasswordRequest().execute();
        	    }
        	}else{
        		Toast.makeText(getApplicationContext(), "Enter email address", Toast.LENGTH_SHORT).show();
        	}
        }else{
        	Toast.makeText(getApplicationContext(), "Enter pay number", Toast.LENGTH_SHORT).show();
        }
	}

	private String comparePassword(String string, String string2) {
		// TODO Auto-generated method stub
		String passwd=null;
		if(string.equals(string2)){
			passwd = string;
		}else{
			Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_SHORT).show();
			txtPassword.setText("");
			txtRe_enterPassword.setText("");
			txtPassword.setFocusable(true);
		}
		return passwd;
	}
	class ChangePasswordRequest extends AsyncTask<String, String, String> {
	   	 
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(getApplicationContext());
            pDialog.setMessage("Signing in please wait..");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
 
        /**
         * Creating product
         * */
        protected String doInBackground(String... args) {
        	
        	UrlConnectChangePassword urlChangePassword = new UrlConnectChangePassword(passwordObject);
        	String response=urlChangePassword.changeUserPassword();
        	if(response.equalsIgnoreCase("success")){
        		Intent intent= new Intent(getApplicationContext(), MainActivity.class);
        		startActivity(intent);
        	}else{
        		Toast.makeText(getApplicationContext(), "request unsuccessful re-try", Toast.LENGTH_SHORT).show();
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
	
	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.password_reset, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
