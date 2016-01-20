package com.pulego.tshwanesafetymc.helper;

import com.pulego.tshwanesafetymc.pojos.LogonUser;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DatabaseController extends DatabaseOpenHelper{

	public DatabaseController(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
    public long insertLogins(LogonUser user){
    	SQLiteDatabase db =this.getWritableDatabase();
    	ContentValues values=new ContentValues();
    	values.put(KEY_ID,  user.getUser_id());
    	values.put(ROW_USERNAME, user.getUser_name());
    	values.put(ROW_USER_FULLNAME, user.getUser_full_name());
    	values.put(ROW_USER_PAYSAL, user.getUser_paysal());
    	values.put(ROW_USER_PHONE, user.getUser_phone());
    	values.put(ROW_USER_EMAIL, user.getUser_email());
    	values.put(ROW_USER_LOGIN_STATUS, "Active");
    	long id=0;
    	if(isValidId(user.getUser_id(), TABLE_LOGIN)){
    	   id =0;
    	}else{
    		updateCurrentUser();
        	id= db.insert(DatabaseOpenHelper.TABLE_LOGIN, null, values);
        	Log.d("DATA INSERTED", "id = "+id);
    	}
    	return id;
    }
    public LogonUser getUserDetails(){
    	SQLiteDatabase db =this.getReadableDatabase();
    	String row_query="SELECT * FROM "+TABLE_LOGIN+" WHERE user_status='Active'";
    	Cursor c=db.rawQuery(row_query, null);
    	LogonUser login = new LogonUser();
    	if(c.moveToFirst()){
    		if(c.moveToNext()){
	    		login.setProfile_img(c.getString(c.getColumnIndex(ROW_USER_PROFILEPIC)));
	    		login.setUser_email(c.getString(c.getColumnIndex(ROW_USER_EMAIL)));
	    		login.setUser_full_name(c.getString(c.getColumnIndex(ROW_USER_FULLNAME)));
	    		login.setUser_id(c.getInt(c.getColumnIndex(KEY_ID)));
	    		login.setUser_paysal(c.getString(c.getColumnIndex(ROW_USER_PAYSAL)));
	    		login.setUser_phone(c.getString(c.getColumnIndex(ROW_USER_PHONE)));
    		}
    	}
		return login;
    	
    }
    public void updateCurrentUser(){
    	SQLiteDatabase db =this.getWritableDatabase();
    	String sql_update = "UPDATE "+TABLE_LOGIN+" SET user_status='InActive'";
    	db.rawQuery(sql_update, null);
    }
}
