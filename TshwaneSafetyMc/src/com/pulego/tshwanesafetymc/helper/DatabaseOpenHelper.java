package com.pulego.tshwanesafetymc.helper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.pulego.tshwanesafetymc.pojos.DiploymentCalc;
import com.pulego.tshwanesafetymc.pojos.Incidents;
import com.pulego.tshwanesafetymc.pojos.NotificationsObj;
import com.pulego.tshwanesafetymc.pojos.ObjectStatus;
import com.pulego.tshwanesafetymc.pojos.ObjectType;
import com.pulego.tshwanesafetymc.pojos.StrengthReport;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseOpenHelper extends SQLiteOpenHelper {
	// Logcat tag
    private static final String LOG = "DatabaseHelper";
 
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "tshwaneSafetyManagerConsole.sqlite";
    
    // Table Names
    public static final String TABLE_STRENGTH_REPORT = "tbl_strength_report";
    public static final String TABLE_STRENGTH_QUERY = "tbl_strength_query";
    public static final String TABLE_NOTIFICATION = "tbl_notifications";
    public static final String TABLE_LOGIN = "tbl_login";
 
    // Common column names
    public static final String KEY_ID = "_id";
    public static final String KEY_CREATED_AT = "created_at";
    
    //login column names
    public static final String ROW_USERNAME ="username";
    public static final String ROW_USER_FULLNAME="user_full_name";
    public static final String ROW_USER_PHONE="user_phone";
    public static final String ROW_USER_PAYSAL="user_paysal";
    public static final String ROW_USER_PROFILEPIC="profile_img";
    public static final String ROW_USER_EMAIL="user_email";
    public static final String ROW_USER_LOGIN_STATUS="user_status";
    //Strength report column
    public static final String ID = "_id";
    public static final String DATE ="date";
    public static final String SHIFT ="shift";
    public static final String REPORTED_BY = "reported_by";
    public static final String SUPERVISOR = "supervisor";
    public static final String REGION = "region";
    public static final String MEMBERS = "members";
    public static final String VEHICLES = "vehicles";
    public static final String BIKES = "bikes";
    public static final String REGION_OB = "region_ob";
    public static final String NODAL_OB = "nodal_ob";
    public static final String REMARKS = "remarks";
    public static final String NODAL_REMARKS = "nodal_remarks";
    public static final String NODAL_OB_CAPTUREDBY = "nodal_ob_capturedby";
   	
   	//diployment calculations column
    public static final String TOT_BIKES = "tot_bikes";
    public static final String TOT_MEMBERS = "tot_members";
    public static final String TOT_VEHICLES = "tot_vehicles";
    public static final String PROGRASS = "prograss";
   	
   	//Notifications column
   	public static final String NOTIFI_TITLE="title";
   	public static final String NOTIFI_MESSAGE="message";
   	public static final String NOTIFI_SENDER="sent_by";
   	public static final String NOTIFI_SENDDATE="date_sent";
   	public static final String NOTIFI_STATUS="status";
   	public static final String NOTIFI_URLIMAGE="pictureurl";
   	public static final String NOTIFI_CATEGORYIMG="category_img";
   	public static final String NOTIFI_NOTIFICATIONDATE="notificationdate";
   	public static final String NOTIFI_PUBLISHEDBY="publishedby";
   	public static final String NOTIFI_UPDATEDDATE="updated_date";
   	public static final String NOTIFI_UPDATEDBY="updated_by";
 // login table create statement
    private static final String CREATE_TABLE_STRENGTH_REPORT = "CREATE TABLE "
            + TABLE_STRENGTH_REPORT + "(" + KEY_ID + " INTEGER PRIMARY KEY," + DATE
            + " TEXT,"  + SHIFT
            + " TEXT," + REPORTED_BY
            + " TEXT," + SUPERVISOR
            + " TEXT," + REGION
            + " TEXT," + BIKES 
            + " INTEGER,"+ MEMBERS
            + " INTEGER,"+ VEHICLES
            + " INTEGER," + REGION_OB
            + " TEXT," + NODAL_OB
            + " TEXT," + REMARKS
            + " TEXT," + NODAL_REMARKS
            + " TEXT," + NODAL_OB_CAPTUREDBY
            + " TEXT," + KEY_CREATED_AT
            + " DATETIME" + ")";

 // strength report table create statement
    private static final String CREATE_TABLE_LOGIN = "CREATE TABLE "
            + TABLE_LOGIN + "(" + KEY_ID + " INTEGER PRIMARY KEY," + ROW_USERNAME
            + " TEXT,"  + ROW_USER_EMAIL
            + " TEXT," + ROW_USER_FULLNAME
            + " TEXT," + ROW_USER_PAYSAL
            + " TEXT," + ROW_USER_PHONE
            + " TEXT," + ROW_USER_PROFILEPIC
            + " TEXT," + ROW_USER_LOGIN_STATUS
            + " TEXT,"  + KEY_CREATED_AT
            + " DATETIME" + ")";

 // strength calculations table create statement
    private static final String CREATE_TABLE_STRENGTH_QUERY = "CREATE TABLE "
            + TABLE_STRENGTH_QUERY + "(" + KEY_ID + " INTEGER PRIMARY KEY," + DATE
            + " TEXT,"  + SHIFT
            + " TEXT," + TOT_BIKES 
            + " INTEGER,"+ TOT_MEMBERS
            + " INTEGER,"+ TOT_VEHICLES
            + " INTEGER," + PROGRASS
            + " TEXT,"  + KEY_CREATED_AT
            + " DATETIME" + ")";
    
    // Notification table create statement
    private static final String CREATE_TABLE_NOFICATION = "CREATE TABLE "
            + TABLE_NOTIFICATION + "(" + KEY_ID + " INTEGER PRIMARY KEY," + NOTIFI_TITLE
            + " TEXT,"  + NOTIFI_MESSAGE
            + " TEXT,"+ NOTIFI_NOTIFICATIONDATE
            + " TEXT,"+ NOTIFI_PUBLISHEDBY
            + " TEXT,"+ NOTIFI_STATUS
            + " TEXT,"+ NOTIFI_UPDATEDDATE
            + " TEXT,"+ NOTIFI_URLIMAGE
            + " TEXT,"+ NOTIFI_CATEGORYIMG
            + " TEXT,"+ NOTIFI_SENDER
            + " TEXT,"+ NOTIFI_UPDATEDBY
            + " TEXT,"+ NOTIFI_SENDDATE
            + " TEXT," + KEY_CREATED_AT
            + " DATETIME" + ")";
    
	public DatabaseOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// creating required tables
        db.execSQL(CREATE_TABLE_NOFICATION);
        db.execSQL(CREATE_TABLE_STRENGTH_REPORT);
        db.execSQL(CREATE_TABLE_STRENGTH_QUERY);
        db.execSQL(CREATE_TABLE_LOGIN);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		 // on upgrade drop older tables
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_STRENGTH_QUERY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STRENGTH_REPORT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTIFICATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN);
        // create new tables
        onCreate(db);
	}

	 /**
	* Creating notifications
	*/
	public long fillNotificationsTB(NotificationsObj notification) {
	   SQLiteDatabase db = this.getWritableDatabase();
	
	   ContentValues values = new ContentValues();
	   values.put(KEY_ID, notification.getId());
	   values.put(NOTIFI_CATEGORYIMG, notification.getCategory_img());
	   values.put(NOTIFI_MESSAGE, notification.getMessage());
	   values.put(NOTIFI_NOTIFICATIONDATE, notification.getNotificationdate());
	   values.put(NOTIFI_PUBLISHEDBY, notification.getPublishedby());
	   values.put(NOTIFI_SENDDATE, notification.getDate_sent());
	   values.put(NOTIFI_SENDER, notification.getSent_by());
	   values.put(NOTIFI_STATUS, notification.getStatus());
	   values.put(NOTIFI_TITLE, notification.getTitle());
	   values.put(NOTIFI_UPDATEDBY, notification.getUpdated_by());
	   values.put(NOTIFI_UPDATEDDATE, notification.getUpdated_date());
	   values.put(NOTIFI_URLIMAGE, notification.getPictureurl());
	   values.put(KEY_CREATED_AT, getDateTime());
	   long notification_id=0;
	   
		   if(isValidId(notification.getId(), TABLE_NOTIFICATION)==false){
			   // insert row
			   notification_id = db.insert(TABLE_NOTIFICATION, null, values);
		   }
	   db.close();
	   return notification_id;
	}
	public Cursor getAllNotifications(){
		SQLiteDatabase db= this.getReadableDatabase();
		//String sql ="SELECT id As _id,"+NOTIFI_TITLE+","+NOTIFI_MESSAGE+","+NOTIFI_STATUS+","+NOTIFI_NOTIFICATIONDATE+" FROM "+TABLE_NOTIFICATION;
		//Cursor mCursor=db.rawQuery(sql, null);
		Cursor mCursor = db.query(TABLE_NOTIFICATION,new String[]{KEY_ID,NOTIFI_TITLE,NOTIFI_MESSAGE,NOTIFI_STATUS,NOTIFI_NOTIFICATIONDATE}, null, null, null, null, null);
		if(mCursor!=null){
			mCursor.moveToFirst();
		}
		return mCursor;
	}
	public ArrayList<String[]> getAllNotification() {
	
		ArrayList<String[]> sl = new ArrayList<String[]>();
	
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor res = db
				.rawQuery(
						"select * from tbl_notifications where _id > 0 order by notificationdate desc",
						null);
		res.moveToFirst();
		while (res.isAfterLast() == false) {
	
			sl.add(new String[] {
					res.getString(res.getColumnIndex(NOTIFI_TITLE)),
					res.getString(res.getColumnIndex(NOTIFI_NOTIFICATIONDATE)),
					res.getString(res.getColumnIndex(NOTIFI_MESSAGE)),
					"http://test.tshwanesafety.co.za/dashboard/notification/img/"+res.getString(res
							.getColumnIndex(NOTIFI_CATEGORYIMG)) });
	
			res.moveToNext();
		}
		return sl;
	}
	     public boolean isValidId(int id,String table){
	    	 boolean isValid = false;
	    	 String selectQuery = "SELECT * FROM " + table +" WHERE "+KEY_ID + "="+id;
		   	 String query = "SELECT name FROM sqlite_master WHERE type='table' AND name='"+table+"'";
		        Log.d(LOG, selectQuery);
		 
		        SQLiteDatabase db = this.getReadableDatabase();
		        if(db.rawQuery(query, null).getCount()==1){
			        Cursor c = db.rawQuery(selectQuery, null);
			        
			        if(c.moveToFirst()){
			        	isValid =true;
			        }
		        }else{
		        	onCreate(db);
		        }
		        return isValid;
	     }
	 /**
	     * Creating strength report
	     */
		public long createStrengthReport(StrengthReport strengthReport) {
			
	        SQLiteDatabase db = this.getWritableDatabase();
	 
	        ContentValues values = new ContentValues();
	        values.put(KEY_ID, strengthReport.getId());
	        values.put(DATE, strengthReport.getDate());
	        values.put(SHIFT, strengthReport.getShift());
	        values.put(REPORTED_BY, strengthReport.getReported_by());
	        values.put(SUPERVISOR, strengthReport.getSupervisor());
	        values.put(REGION, strengthReport.getRegion());
	        values.put(BIKES, strengthReport.getBikes());
	        values.put(MEMBERS, strengthReport.getMembers());
	        values.put(VEHICLES, strengthReport.getVehicles());
	        values.put(REGION_OB, strengthReport.getRegion_ob());
	        values.put(NODAL_OB, strengthReport.getNodal_ob());
	        values.put(REMARKS, strengthReport.getRemarks());
	        values.put(NODAL_REMARKS, strengthReport.getNodal_remarks());
	        values.put(NODAL_OB_CAPTUREDBY, strengthReport.getNodal_ob_capturedby());
	        values.put(KEY_CREATED_AT, getDateTime());
	        long _id=0;
	        if(isValidId(strengthReport.getId(),TABLE_STRENGTH_REPORT)){
	        	_id=0;
	        }else{
		        // insert row
		        _id = db.insert(TABLE_STRENGTH_REPORT, null, values);
	        }
	        return _id;
	    }
		/*
		 * Getting type
		 * */
		 public List<StrengthReport> getAllStrengthReportRecordsPerShift(String date,String shift) {
			 
		        List<StrengthReport> strength = new ArrayList<StrengthReport>();
		        
		        //String selectQuery = "SELECT  * FROM " + TABLE_STRENGTH_REPORT;
		        String strDate =getDateFormat(date);
		        String strShift =getStringShift(shift);
		       // String selectQuery = "SELECT  * FROM " + TABLE_STRENGTH_REPORT +" WHERE "+SHIFT + "="+strShift +" AND "+DATE +"="+strDate;
		        String selectQuery = "SELECT  * FROM " + TABLE_STRENGTH_REPORT +" WHERE "+SHIFT + "='"+strShift +"' AND substr(date,1,10) = '"+date+"'";
		        Log.e(LOG, selectQuery);
		 
		        SQLiteDatabase db = this.getReadableDatabase();
		        Cursor c = db.rawQuery("SELECT  * FROM " + TABLE_STRENGTH_REPORT +" WHERE shift=? AND substr(date,1,10)=?", new String[]{strShift,date});
		 
		        // looping through all rows and adding to list
		        if (c.moveToFirst()) {
		            do {
		                StrengthReport s = new StrengthReport();
		                s.setId(c.getInt((c.getColumnIndex(KEY_ID))));
		                s.setDate(c.getString(c.getColumnIndex(DATE)));
		                s.setShift(c.getString(c.getColumnIndex(SHIFT)));
		                s.setReported_by(c.getString(c.getColumnIndex(REPORTED_BY)));
		                s.setSupervisor(c.getString(c.getColumnIndex(SUPERVISOR)));
		                s.setRegion(c.getString(c.getColumnIndex(REGION)));
		                s.setBikes(c.getInt((c.getColumnIndex(BIKES))));
		                s.setMembers(c.getInt(c.getColumnIndex(MEMBERS)));
		                s.setVehicles(c.getInt(c.getColumnIndex(VEHICLES)));
		                s.setRegion_ob(c.getString(c.getColumnIndex(REGION_OB)));
		                s.setNodal_ob(c.getString(c.getColumnIndex(NODAL_OB)));
		                s.setRemarks(c.getString(c.getColumnIndex(REMARKS)));
		                s.setNodal_remarks(c.getString(c.getColumnIndex(NODAL_REMARKS)));
		                s.setNodal_ob_capturedby(c.getString(c.getColumnIndex(NODAL_OB_CAPTUREDBY)));
		                
		                // adding to tags list
		                strength.add(s);
		                
		                Log.d("Strength report", c.getString(c.getColumnIndex(SHIFT)));
		                
		            } while (c.moveToNext());
		            db.close();
		        }
		        return strength;
		    }
		 private String getStringShift(String shift2) {
			// TODO Auto-generated method stub
			 String shift=null;
			 if(shift2.length()!="5am-9pm".length()){
				 String fstData = shift2.substring(1, shift2.indexOf("-"));
				 String sndData = shift2.substring(shift2.indexOf("-")+1,shift2.length());
				 shift = fstData +" - "+sndData;
			 }else{
				 shift=shift2;
			 }
			return shift;
		}

			 public List<DiploymentCalc> getAllDiploymentReportIDs() {
			        List<DiploymentCalc> diployment = new ArrayList<DiploymentCalc>();
			        String selectQuery = "SELECT  * FROM " + TABLE_STRENGTH_QUERY;
			 
			        Log.e(LOG, selectQuery);
			 
			        SQLiteDatabase db = this.getReadableDatabase();
			        Cursor c = db.rawQuery(selectQuery, null);
			 
			        // looping through all rows and adding to list
			        if (c.moveToFirst()) {
			            do {
			                DiploymentCalc d = new DiploymentCalc();
			                d.setId(c.getInt((c.getColumnIndex(KEY_ID))));
			              
			                // adding to tags list
			                diployment.add(d);
			            } while (c.moveToNext());
			            db.close();
			        }
			        return diployment;
			    }
	
	// closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
    /**
     * get datetime
     * */
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
    private String getDateFormat(String date){
    	String strDate="";
    	if(date.length()!= "yyyy-MM-dd".length()){
	    	Date _date = new Date(date);
	    	
	    	SimpleDateFormat dateFormat = new SimpleDateFormat(
	                "yyyy-MM-dd");
	    	strDate =dateFormat.format(_date);
    	}else{
    		strDate = date;
    	}
    	return strDate;
    }
	public long addDiploymentCalculations(DiploymentCalc diploymentCalc) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = this.getWritableDatabase();
		
		 ContentValues values = new ContentValues();
	        values.put(KEY_ID, diploymentCalc.getId());
	        values.put(DATE, diploymentCalc.getDate());
	        values.put(SHIFT, diploymentCalc.getShift());
	        values.put(TOT_BIKES, diploymentCalc.getTotal_bikes());
	        values.put(TOT_MEMBERS, diploymentCalc.getTotal_members());
	        values.put(TOT_VEHICLES, diploymentCalc.getTotal_vehicles());
	        values.put(PROGRASS, diploymentCalc.getProgress());
	        values.put(KEY_CREATED_AT, getDateTime());
	        long _id;
	       if(isValidId(diploymentCalc.getId(), TABLE_STRENGTH_QUERY)){
	    	   _id=0;
	       }else{
	        // insert row
	            _id = db.insert(TABLE_STRENGTH_QUERY, null, values);
	       }
	        return _id;
	}
	/*
	 * Getting type
	 * */
	 public List<DiploymentCalc> getDiploymentCalcRecords() {
	        List<DiploymentCalc> diployment = new ArrayList<DiploymentCalc>();
	        String selectQuery = "SELECT  * FROM " + TABLE_STRENGTH_QUERY;
	 
	        Log.e(LOG, selectQuery);
	 
	        SQLiteDatabase db = this.getReadableDatabase();
	        Cursor c = db.rawQuery(selectQuery, null);
	 
	        // looping through all rows and adding to list
	        if (c.moveToFirst()) {
	            do {
	                DiploymentCalc d = new DiploymentCalc();
	                d.setId(c.getInt((c.getColumnIndex(KEY_ID))));
	                d.setDate(c.getString(c.getColumnIndex(DATE)));
	                d.setShift(c.getString(c.getColumnIndex(SHIFT)));
	                d.setTotal_bikes(c.getInt((c.getColumnIndex(TOT_BIKES))));
	                d.setTotal_members(c.getInt(c.getColumnIndex(TOT_MEMBERS)));
	                d.setTotal_vehicles(c.getInt(c.getColumnIndex(TOT_VEHICLES)));
	                d.setProgress(c.getString(c.getColumnIndex(PROGRASS)));
	                
	                // adding to tags list
	                diployment.add(d);
	                
	                Log.d("Strength report diployment", c.getString(c.getColumnIndex(SHIFT)));
	                
	            } while (c.moveToNext());
	            db.close();
	        }
	        return diployment;
	    }

	public Cursor readEntry(String date2, String shift2) {
		// TODO Auto-generated method stub
        SQLiteDatabase db = this.getReadableDatabase();
        
		String strDate =getDateFormat(date2);
        String strShift =getStringShift(shift2);
        Log.d(LOG, strShift);
        String selectQuery = "SELECT  * FROM " + TABLE_STRENGTH_REPORT +" WHERE "+SHIFT + "='"+strShift +"' AND substr(date,1,10) = '"+date2+"'";
        
        Log.d(LOG, selectQuery);
 

       // Cursor c = db.rawQuery(selectQuery, null);
        Cursor c = db.rawQuery("SELECT * FROM "+TABLE_STRENGTH_REPORT+ " WHERE shift =? AND substr(date,1,10) =?", new String[]{strShift,date2});
        
        if(c.moveToFirst()){
        	Log.d(LOG, "Wow the cursor has rows!!! Lol");
        }
		return c;
	}
	   

}
