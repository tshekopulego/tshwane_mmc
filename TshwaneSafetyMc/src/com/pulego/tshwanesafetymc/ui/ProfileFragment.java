package com.pulego.tshwanesafetymc.ui;

import com.pulego.tshwanesafetymc.R;
import com.pulego.tshwanesafetymc.helper.DatabaseController;
import com.pulego.tshwanesafetymc.pojos.LogonUser;
import com.pulego.tshwanesafetymc.urlconnectors.UrlConnectLogin;

import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileFragment extends Fragment {
	private UrlConnectLogin urlLogin;
	String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;
	
	public ProfileFragment(){
		
	}
   @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
	   View view=inflater.inflate(R.layout.fragment_profile_layout,container, false);
		
		ActionBar actionBar = getActivity().getActionBar();
		 
       // Screen handling while hiding ActionBar icon.
      actionBar.setDisplayShowHomeEnabled(true);

       // Screen handling while hiding Actionbar title.
       actionBar.setDisplayShowTitleEnabled(true);

       // Creating ActionBar tabs.
       actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
       
       TextView txtfulName = (TextView)view.findViewById(R.id.txtName);
       TextView txtContact =(TextView) view.findViewById(R.id.txtContact);
       TextView txtEmail = (TextView) view.findViewById(R.id.txtEmail);
       TextView txtUsername = (TextView) view.findViewById(R.id.txtUser_name);
       
      // SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE); 
      // Editor editor = pref.edit();
       sharedpreferences  =  getActivity().getApplicationContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
       
       if(sharedpreferences != null){
	       String username = sharedpreferences.getString("USERNAME", null);
	       String fullnames = sharedpreferences.getString("FULLNAME", null);
	       String email = sharedpreferences.getString("EMAIL", null);
	       String contact = sharedpreferences.getString("CONTACT", null);
	       //Adding text to the layout from the session
	           txtfulName.setText(fullnames);
	    	   txtContact.setText(contact);
	    	   txtEmail.setText(email);
	    	   txtUsername.setText(username);
	    	   
       }else
       {
    	   Toast.makeText(getActivity(), "Inligal Login", Toast.LENGTH_SHORT).show();
       }
    // initialize db
 	  /* DatabaseController mdb=new DatabaseController(getActivity().getApplicationContext());
 	   try {
 		  LogonUser user = mdb.getUserDetails();
 	       
 	       if(user!= null)
 	       {
 	    	   txtfulName.setText(user.getUser_full_name());
 	    	   txtContact.setText(user.getUser_phone());
 	    	   txtEmail.setText(user.getUser_email());
 	    	   txtUsername.setText(user.getUser_name());
 	    	   
 	       }
		} catch (Exception e) {
			// TODO: handle exception
			Log.e("Exception:","Occured in Profile is " +e.getMessage());
		}
       */
 	   
       return view;
	}
}
