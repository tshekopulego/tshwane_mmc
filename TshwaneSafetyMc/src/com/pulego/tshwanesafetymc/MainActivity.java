package com.pulego.tshwanesafetymc;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
    EditText txtEmail,txtPassword;
    String email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtEmail=(EditText)findViewById(R.id.email);
        txtPassword=(EditText)findViewById(R.id.password);
        Button btnLogin=(Button)findViewById(R.id.email_sign_in_button);
        
        btnLogin.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				email=txtEmail.getText().toString();
				password=txtPassword.getText().toString();
				final Intent home=new Intent(getApplicationContext(), HomeActivity.class);
				home.putExtra("USER_EMAIL", email);
				startActivity(home);
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
