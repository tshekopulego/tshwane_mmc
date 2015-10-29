package com.pulego.tshwanesafetymc.helper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.pulego.tshwanesafetymc.pojos.DiploymentCalc;
import com.pulego.tshwanesafetymc.pojos.Incidents;
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
    private static final String DATABASE_NAME = "tshwaneSafetyManagerConsole.db";
    
    // Table Names
    private static final String TABLE_NO_OF_INCIDENTS = "tbl_no_of_incidents";
    private static final String TABLE_NO_OF_STATUS = "tbl_no_of_status";
    private static final String TABLE_NO_OF_TYPES = "tbl_no_of_types";
    private static final String TABLE_TAG = "tbl_tag";
    private static final String TABLE_STRENGTH_REPORT = "tbl_strength_report";
    private static final String TABLE_STRENGTH_QUERY = "tbl_strength_query";
 
    // Common column names
    private static final String KEY_ID = "id";
    private static final String KEY_CREATED_AT = "created_at";
 
    // NOTES Table - column nmaes
    private static final String KEY_INCIDENT_DATE = "incidents_date";
    private static final String KEY_INCIDENT_TOTAL = "incidents_total";
    
    // NOTES Table - column nmaes
    private static final String KEY_TYPE_NAME = "type_name";
    private static final String KEY_TYPE_TOTAL = "type_total";
    
    // NOTES Table - column nmaes
    private static final String KEY_STATUS_NAME = "status_name";
    private static final String KEY_STATUS_TOTAL = "status_total";
 
    // TAGS Table - column names
    private static final String KEY_TAG_NAME = "tag_name";
 
    // NOTE_TAGS Table - column names
    private static final String KEY_TODO_ID = "todo_id";
    private static final String KEY_TAG_ID = "tag_id";
    
    //Strength report column
    private static final String ID = "id";
   	private static final String DATE ="date";
   	private static final String SHIFT ="shift";
   	private static final String REPORTED_BY = "reported_by";
   	private static final String SUPERVISOR = "supervisor";
   	private static final String REGION = "region";
   	private static final String MEMBERS = "members";
   	private static final String VEHICLES = "vehicles";
   	private static final String BIKES = "bikes";
   	private static final String REGION_OB = "region_ob";
   	private static final String NODAL_OB = "nodal_ob";
   	private static final String REMARKS = "remarks";
   	private static final String NODAL_REMARKS = "nodal_remarks";
   	private static final String NODAL_OB_CAPTUREDBY = "nodal_ob_capturedby";
   	
   	//diployment calculations column
   	private static final String TOT_BIKES = "tot_bikes";
   	private static final String TOT_MEMBERS = "tot_members";
   	private static final String TOT_VEHICLES = "tot_vehicles";
   	private static final String PROGRASS = "prograss";
   	
    // Table Create Statements
    // Todo table create statement
    private static final String CREATE_TABLE_NO_INCIDENTS = "CREATE TABLE "
            + TABLE_NO_OF_INCIDENTS + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_INCIDENT_DATE
            + " TEXT," + KEY_INCIDENT_TOTAL + " INTEGER," + KEY_CREATED_AT
            + " DATETIME" + ")";
    
    // Todo table create statement
    private static final String CREATE_TABLE_NO_STATUS = "CREATE TABLE "
            + TABLE_NO_OF_STATUS + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_STATUS_NAME
            + " TEXT," + KEY_STATUS_TOTAL + " INTEGER," + KEY_CREATED_AT
            + " DATETIME" + ")";
    
    // Todo table create statement
    private static final String CREATE_TABLE_NO_TYPE = "CREATE TABLE "
            + TABLE_NO_OF_TYPES + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TYPE_NAME
            + " TEXT," + KEY_TYPE_TOTAL + " INTEGER," + KEY_CREATED_AT
            + " DATETIME" + ")";
 
    // Tag table create statement
    private static final String CREATE_TABLE_TAG = "CREATE TABLE " + TABLE_TAG
            + "(" + KEY_ID + " INTEGER RIMARY KEY," + KEY_TAG_NAME + " TEXT,"
            + KEY_CREATED_AT + " DATETIME" + ")";
    
 // Todo table create statement
    private static final String CREATE_TABLE_STRENGTH_REPORT = "CREATE TABLE "
            + TABLE_STRENGTH_REPORT + "(" + ID + " INTEGER PRIMARY KEY," + DATE
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

 // Todo table create statement
    private static final String CREATE_TABLE_STRENGTH_QUERY = "CREATE TABLE "
            + TABLE_STRENGTH_QUERY + "(" + ID + " INTEGER PRIMARY KEY," + DATE
            + " TEXT,"  + SHIFT
            + " TEXT," + TOT_BIKES 
            + " INTEGER,"+ TOT_MEMBERS
            + " INTEGER,"+ TOT_VEHICLES
            + " INTEGER," + PROGRASS
            + " TEXT,"  + KEY_CREATED_AT
            + " DATETIME" + ")";
    
    
	public DatabaseOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// creating required tables
        db.execSQL(CREATE_TABLE_NO_INCIDENTS);
        db.execSQL(CREATE_TABLE_TAG);
        db.execSQL(CREATE_TABLE_NO_STATUS);
        db.execSQL(CREATE_TABLE_NO_TYPE);
        db.execSQL(CREATE_TABLE_STRENGTH_REPORT);
        db.execSQL(CREATE_TABLE_STRENGTH_QUERY);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		 // on upgrade drop older tables
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NO_OF_INCIDENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NO_OF_TYPES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NO_OF_STATUS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TAG);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STRENGTH_REPORT);
 
        // create new tables
        onCreate(db);
	}
	public void deleteAll() {
      deleteAllI();
      deleteAllS();
      deleteAllT();
    }
	public void deleteAllS() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NO_OF_STATUS, null, null);
    }
	public void deleteAllT() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NO_OF_TYPES, null, null);
    }
	public void deleteAllI() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NO_OF_INCIDENTS, null, null);
        
    }
	 /**
     * Creating incidents
     */
	public long createIncident(Incidents incidents) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_INCIDENT_DATE, incidents.getDateOfIncidents());
        values.put(KEY_INCIDENT_TOTAL, incidents.getTotalNoOfIncidents());
        values.put(KEY_CREATED_AT, getDateTime());
 
        // insert row
        long incidents_id = db.insert(TABLE_NO_OF_INCIDENTS, null, values);
        db.close();
        return incidents_id;
    }
	
	/**
	 * Getting incedents
	 */
	 public List<Incidents> getAllIncidents() {
	        List<Incidents> incidents = new ArrayList<Incidents>();
	        String selectQuery = "SELECT  * FROM " + TABLE_NO_OF_INCIDENTS;
	 
	        Log.e(LOG, selectQuery);
	 
	        SQLiteDatabase db = this.getReadableDatabase();
	        Cursor c = db.rawQuery(selectQuery, null);
	 
	        // looping through all rows and adding to list
	        if (c.moveToFirst()) {
	            do {
	                Incidents i = new Incidents();
	                i.setId(c.getInt((c.getColumnIndex(KEY_ID))));
	                i.setDateOfIncidents(c.getString(c.getColumnIndex(KEY_INCIDENT_DATE)));
	                i.setTotalNoOfIncidents(c.getInt(c.getColumnIndex(KEY_INCIDENT_TOTAL)));
	                Log.d("db process", c.getString(c.getColumnIndex(KEY_INCIDENT_DATE))+" and "+c.getInt(c.getColumnIndex(KEY_INCIDENT_TOTAL)));
	                // adding to incidents list
	                incidents.add(i);
	            } while (c.moveToNext());
	            db.close();
	        }
	       
	        return incidents;
	    }
	 /**
	     * Creating strength report
	     */
		public long createStrengthReport(StrengthReport strengthReport) {
	        SQLiteDatabase db = this.getWritableDatabase();
	 
	        ContentValues values = new ContentValues();
	        values.put(ID, strengthReport.getId());
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
	 
	        // insert row
	        long _id = db.insert(TABLE_STRENGTH_REPORT, null, values);
	        
	        return _id;
	    }
		/*
		 * Getting type
		 * */
		 public List<StrengthReport> getAllStrengthReportRecordsPerShift(String date,String shift) {
			 
		        List<StrengthReport> strength = new ArrayList<StrengthReport>();
		        
		        //String selectQuery = "SELECT  * FROM " + TABLE_STRENGTH_REPORT;
		        String strDate =getDateFormat(date);
		        
		        String selectQuery = "SELECT  * FROM " + TABLE_STRENGTH_REPORT +" WHERE "+SHIFT + "="+shift +" AND "+DATE +"="+strDate;
			   	 
		        Log.e(LOG, selectQuery);
		 
		        SQLiteDatabase db = this.getReadableDatabase();
		        Cursor c = db.rawQuery(selectQuery, null);
		 
		        // looping through all rows and adding to list
		        if (c.moveToFirst()) {
		            do {
		                StrengthReport s = new StrengthReport();
		                s.setId(c.getInt((c.getColumnIndex(ID))));
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
		 /*
			 * Getting type
			 * */
			 public List<StrengthReport> getAllStrengthReportIDs() {
			        List<StrengthReport> strength = new ArrayList<StrengthReport>();
			        String selectQuery = "SELECT  * FROM " + TABLE_STRENGTH_REPORT;
			 
			        Log.e(LOG, selectQuery);
			 
			        SQLiteDatabase db = this.getReadableDatabase();
			        Cursor c = db.rawQuery(selectQuery, null);
			 
			        // looping through all rows and adding to list
			        if (c.moveToFirst()) {
			            do {
			                StrengthReport s = new StrengthReport();
			                s.setId(c.getInt((c.getColumnIndex(ID))));
			              
			                // adding to tags list
			                strength.add(s);
			            } while (c.moveToNext());
			            db.close();
			        }
			        return strength;
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
			                d.setId(c.getInt((c.getColumnIndex(ID))));
			              
			                // adding to tags list
			                diployment.add(d);
			            } while (c.moveToNext());
			            db.close();
			        }
			        return diployment;
			    }
	 /**
     * Creating type
     */
	public long createType(ObjectType type) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_TYPE_NAME, type.getTypeName());
        values.put(KEY_TYPE_TOTAL, type.getTotalType());
        values.put(KEY_CREATED_AT, getDateTime());
 
        // insert row
        long type_id = db.insert(TABLE_NO_OF_TYPES, null, values);
 
        return type_id;
    }
	/*
	 * Getting type
	 * */
	 public List<ObjectType> getAllType() {
	        List<ObjectType> type = new ArrayList<ObjectType>();
	        String selectQuery = "SELECT  * FROM " + TABLE_NO_OF_TYPES;
	 
	        Log.e(LOG, selectQuery);
	 
	        SQLiteDatabase db = this.getReadableDatabase();
	        Cursor c = db.rawQuery(selectQuery, null);
	 
	        // looping through all rows and adding to list
	        if (c.moveToFirst()) {
	            do {
	                ObjectType t = new ObjectType();
	                t.setId(c.getInt((c.getColumnIndex(KEY_ID))));
	                t.setTypeName(c.getString(c.getColumnIndex(KEY_TYPE_NAME)));
	                t.setTotalType(c.getInt((c.getColumnIndex(KEY_TYPE_TOTAL))));
	                // adding to tags list
	                type.add(t);
	            } while (c.moveToNext());
	            db.close();
	        }
	        return type;
	    }
	 /**
     * Creating status
     */
	public long createSatus(ObjectStatus status) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_STATUS_NAME, status.getStatusName());
        values.put(KEY_STATUS_TOTAL, status.getStatusTotal());
        values.put(KEY_CREATED_AT, getDateTime());
 
        // insert row
        long type_id = db.insert(TABLE_NO_OF_STATUS, null, values);
 
        return type_id;
    }
	/*
	 * Getting Status
	 * */
	 public List<ObjectStatus> getAllSatus() {
	        List<ObjectStatus> status = new ArrayList<ObjectStatus>();
	        String selectQuery = "SELECT  * FROM " + TABLE_NO_OF_STATUS;
	 
	        Log.e(LOG, selectQuery);
	 
	        SQLiteDatabase db = this.getReadableDatabase();
	        Cursor c = db.rawQuery(selectQuery, null);
	 
	        // looping through all rows and adding to list
	        if (c.moveToFirst()) {
	            do {
	                ObjectStatus s = new ObjectStatus();
	                s.setId(c.getInt((c.getColumnIndex(KEY_ID))));
	                s.setStatusName(c.getString(c.getColumnIndex(KEY_STATUS_NAME)));
	                s.setStatusTotal(c.getInt((c.getColumnIndex(KEY_STATUS_TOTAL))));
	                // adding to tags list
	                status.add(s);
	            } while (c.moveToNext());
	        }
	        return status;
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
	        values.put(ID, diploymentCalc.getId());
	        values.put(DATE, diploymentCalc.getDate());
	        values.put(SHIFT, diploymentCalc.getShift());
	        values.put(TOT_BIKES, diploymentCalc.getTotal_bikes());
	        values.put(TOT_MEMBERS, diploymentCalc.getTotal_members());
	        values.put(TOT_VEHICLES, diploymentCalc.getTotal_vehicles());
	        values.put(PROGRASS, diploymentCalc.getProgress());
	        values.put(KEY_CREATED_AT, getDateTime());
	 
	        // insert row
	        long _id = db.insert(TABLE_STRENGTH_QUERY, null, values);
	        
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
	                d.setId(c.getInt((c.getColumnIndex(ID))));
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
	   

}
