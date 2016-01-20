package com.pulego.tshwanesafetymc.urlconnectors;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.StrictMode;
import android.util.Log;

import com.pulego.tshwanesafetymc.helper.DatabaseController;
import com.pulego.tshwanesafetymc.helper.DatabaseOpenHelper;
import com.pulego.tshwanesafetymc.httpconfig.CustomHttpClient;
import com.pulego.tshwanesafetymc.httpconfig.JSONParser;
import com.pulego.tshwanesafetymc.pojos.Login;
import com.pulego.tshwanesafetymc.pojos.LogonUser;
import com.pulego.tshwanesafetymc.pojos.NotificationsObj;

public class UrlConnectLogin {
   private Login logon;
   private String URL_CONNECT_LOGIN="http://196.33.249.226/android/tshwanesafety/managementconsole/login.php";
   // Creating JSON Parser object
   JSONParser jParser = new JSONParser();
   //Activity context
   private Context context;
   // JSON Node names
   private static final String TAG_SUCCESS = "success";
   private static final String TAG_REPORT = "reports";

   // products JSONArray
   JSONArray reports = null;
   //SQlite database 
    DatabaseOpenHelper db;
	public UrlConnectLogin(Context context) {
		super();
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
		  .detectDiskReads().detectDiskWrites().detectNetwork() // StrictMode is most commonly used to catch accidental disk or network access on the application's main thread
		  .penaltyLog().build());
		this.context = context;
	}
	
   public UrlConnectLogin(Login logon,Context context) {
	super();
	StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
	  .detectDiskReads().detectDiskWrites().detectNetwork() // StrictMode is most commonly used to catch accidental disk or network access on the application's main thread
	  .penaltyLog().build());
	this.logon = logon;
	this.context = context;
}

public String verifyUserLoginDetails(){
	   String success=null;
	   String username=logon.getUsername();
       String passwd =logon.getPassword();
		
				    // declare parameters that are passed to PHP script i.e. the name "birthyear" and its value submitted by user   
			          ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
			          
			          // define the parameter
			          postParameters.add(new BasicNameValuePair("username",username));
			       postParameters.add(new BasicNameValuePair("password",passwd));
			       
			          String response = null;
			          String returnString;
			          // call executeHttpPost method passing necessary parameters 192.168.43.88
			          //
			          try {
			     response = CustomHttpClient.executeHttpPost("http://196.33.249.226/android/tshwanesafety/managementconsole/login.php",postParameters);
					       
			     // store the result returned by PHP script that runs MySQL query
			     String result = response.toString();  
			              
				      //parse json data
				         try{
				                 returnString = "";
				        
				             JSONObject json_data = new JSONObject(response);
				             
				            if(json_data.getInt("success")==1){
			                    success="success";
				            }else{
				               success="unsuccess";
				            }
				             Log.e("log_tag", "I parse here "+returnString);
				         }catch(JSONException e){
				                 Log.e("log_tag", "Error parsing data "+e.toString());
				         }
					     
				         try{
				          
				          //Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
				       Log.e("log_tag", "Error parsing data "+result);
				         }
				         catch(Exception e){
				          Log.e("log_tag","Error in Display!" + e.toString());;          
				         }   
			    
			          } catch (Exception e) {
					     Log.e("log_tag","Error in http connection!!" + e.toString());     
					   }
			   
	   return success;   
   }
   public LogonUser verifyLogins(){
	   //user login details
	   String username=logon.getUsername();
       String passwd =logon.getPassword();

	   
	// Building Parameters
       List<NameValuePair> params = new ArrayList<NameValuePair>();
       // getting JSON string from URL
       JSONObject json = jParser.makeHttpRequest(URL_CONNECT_LOGIN, "POST", params);
       
       // define the parameter
       params.add(new BasicNameValuePair("username",username));
       params.add(new BasicNameValuePair("password",passwd));
       
       // Check your log cat for JSON reponse
       Log.d("User logins : ", json.toString());
       
       LogonUser logonUser = null;
       try {
           // Checking for SUCCESS TAG
           int success = json.getInt(TAG_SUCCESS);
        
   	      
           if (success == 1) {
        	// initialize db
        	   DatabaseController mdb=new DatabaseController(context);
        	     
               
               // Getting Array of Products
               reports = json.getJSONArray(TAG_REPORT);
              
               // looping through All Products
               for (int i = 0; i < reports.length(); i++) {
                   JSONObject c = reports.getJSONObject(i);
                   
                   logonUser = new LogonUser();
                   
                   // Storing each json item in variable
                   logonUser.setUser_id(c.getInt("id"));
                   logonUser.setUser_name(c.getString("user_name"));
                   logonUser.setUser_paysal(c.getString("user_paysal"));
                   logonUser.setUser_phone(c.getString("user_phone"));
                   logonUser.setUser_email(c.getString("user_email"));
                   logonUser.setProfile_img(c.getString("profile_img"));
                  long id= mdb.insertLogins(logonUser);
                 }
           } else {
               // no products found
        	   Log.e("invalid_user", "User not found");
             }
       } catch (JSONException e) {
           e.printStackTrace();
       }
       return logonUser;
   }

public LogonUser verifyLogins(Login login) {
	// TODO Auto-generated method stub
	 //user login details
	   String username=login.getUsername();
    String passwd =login.getPassword();

	   
	// Building Parameters
    List<NameValuePair> params = new ArrayList<NameValuePair>();
 // define the parameter
    params.add(new BasicNameValuePair("username",username));
    params.add(new BasicNameValuePair("password",passwd));
    // getting JSON string from URL
    JSONObject json = jParser.makeHttpRequest(URL_CONNECT_LOGIN, "POST", params);

    // Check your log cat for JSON reponse
    Log.d("User logins : ", json.toString());
    
    LogonUser logonUser = null;
    try {
        // Checking for SUCCESS TAG
        int success = json.getInt(TAG_SUCCESS);
     
	      
        if (success == 1) {
     	// initialize db
     	   DatabaseController mdb=new DatabaseController(context);
            
            // Getting Array of Products
            reports = json.getJSONArray(TAG_REPORT);
           
            // looping through All Products
            //for (int i = 0; i < reports.length(); i++) {
                JSONObject c = reports.getJSONObject(0);
                if(c!=null){
                logonUser = new LogonUser();
                
                // Storing each json item in variable
                logonUser.setUser_id(c.getInt("id"));
                logonUser.setUser_name(c.getString("user_name"));
                logonUser.setUser_full_name(c.getString("user_full_name"));
                logonUser.setUser_paysal(c.getString("user_paysal"));
                logonUser.setUser_phone(c.getString("user_phone"));
                logonUser.setUser_email(c.getString("user_email"));
                logonUser.setProfile_img(c.getString("profile_img"));
               long id= mdb.insertLogins(logonUser);
                }
             // }
        } else {
            // no products found
     	   Log.e("invalid_user", "User not found");
          }
    } catch (JSONException e) {
        e.printStackTrace();
    }
    return logonUser;
}
}
