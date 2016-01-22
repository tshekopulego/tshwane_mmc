package com.pulego.tshwanesafetymc.urlconnectors;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.StrictMode;
import android.util.Log;

import com.pulego.tshwanesafetymc.httpconfig.CustomHttpClient;
import com.pulego.tshwanesafetymc.httpconfig.JSONParser;
import com.pulego.tshwanesafetymc.pojos.ChangePassword;

public class UrlConnectChangePassword {
   ChangePassword passwordObject;
   
   // Creating JSON Parser object
   JSONParser jParser = new JSONParser();
   //Activity context
   private Context context;
   // JSON Node names
   private static final String TAG_SUCCESS = "success";
   private static final String TAG_REPORT = "reports";
   private static String url_create_product = "http://196.33.249.226/android/tshwanesafety/managementconsole/reset_password_request.php";
   // products JSONArray
   JSONArray reports = null;
	public UrlConnectChangePassword(ChangePassword passwordObject) {
		super();
		this.passwordObject = passwordObject;
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
		  .detectDiskReads().detectDiskWrites().detectNetwork() // StrictMode is most commonly used to catch accidental disk or network access on the application's main thread
		  .penaltyLog().build());
	}
	public String changeUserPassword(){
		   String success=null;
		   String payNo=passwordObject.getPayNumber();
		   String email=passwordObject.getEmail();
	       String passwd =passwordObject.getPassword();
			
					    // declare parameters that are passed to PHP script i.e. the name "birthyear" and its value submitted by user   
				          ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
				          
				          // define the parameter
				          postParameters.add(new BasicNameValuePair("pay_number", payNo));
				          postParameters.add(new BasicNameValuePair("email_address",email));
				          postParameters.add(new BasicNameValuePair("password",passwd));
				       
				          JSONObject json = jParser.makeHttpRequest(url_create_product,
				                    "POST", postParameters);
				 
				            // check log cat fro response
				            Log.d("Password Reset", json.toString());
				          
				            // check for success tag
				            try {
				                int intSuccess = json.getInt(TAG_SUCCESS);
				 
				                if (intSuccess == 1) {
				                	 success="success";
					            }else{
					               success="unsuccess";
					            }
				            } catch (JSONException e) {
				            	Log.e("log_tag", "Error parsing data "+e.toString());
				            }
				         /* String response = null;
				          String returnString;
				          // call executeHttpPost method passing necessary parameters 192.168.43.88
				          //
				          try {
				     response = CustomHttpClient.executeHttpPost("http://196.33.249.226/android/tshwanesafety/managementconsole/reset_password_request.php",postParameters);
						       
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
						     
					       
				          } catch (Exception e) {
						     Log.e("log_tag","Error in http connection!!" + e.toString());     
						   }*/
				   
		   return success;   
	   }
}
