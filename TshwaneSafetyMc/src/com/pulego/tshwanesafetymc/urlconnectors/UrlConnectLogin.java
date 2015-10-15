package com.pulego.tshwanesafetymc.urlconnectors;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.StrictMode;
import android.util.Log;

import com.pulego.tshwanesafetymc.httpconfig.CustomHttpClient;
import com.pulego.tshwanesafetymc.pojos.Login;

public class UrlConnectLogin {
   private Login logon;
 
	public UrlConnectLogin() {
		super();
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
		  .detectDiskReads().detectDiskWrites().detectNetwork() // StrictMode is most commonly used to catch accidental disk or network access on the application's main thread
		  .penaltyLog().build());
	}
	
   public UrlConnectLogin(Login logon) {
	super();
	StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
	  .detectDiskReads().detectDiskWrites().detectNetwork() // StrictMode is most commonly used to catch accidental disk or network access on the application's main thread
	  .penaltyLog().build());
	this.logon = logon;
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
				 //,"http://192.168.43.88/tshwaneManagementConsole/login.php"
			      // "http://192.168.43.156/jsonscript.php",
			    		 // your ip address if using localhost server
			     //  "http://omega.uta.edu/~kmr2464/jsonscript.php",  // in case of a remote server
			       
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
}
