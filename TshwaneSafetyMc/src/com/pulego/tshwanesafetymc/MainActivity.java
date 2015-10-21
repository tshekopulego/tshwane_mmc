package com.pulego.tshwanesafetymc;

import com.pulego.tshwanesafetymc.urlconnectors.UrlConnectHome;

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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    EditText txtEmail,txtPassword;
    String email,password;
    
    private ProgressDialog pDialog; 
    private Dialog dialog;
    
    UrlConnectHome urlconnect;
    
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
        
        btnLogin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				email=txtEmail.getText().toString();
				password=txtPassword.getText().toString();
				urlconnect=new UrlConnectHome(getApplicationContext());
				 //**************************************************************************
					 new	CheckLoginDetails().execute();
				 //*****************************************************************************
		
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
        	urlconnect.initialization();
    		final Intent home=new Intent(getApplicationContext(), HomeActivity.class);
			home.putExtra("USER_EMAIL", email);
			
			startActivity(home);
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
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
