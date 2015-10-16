package com.pulego.tshwanesafetymc.helper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.pulego.tshwanesafetymc.pojos.Incidents;
import com.pulego.tshwanesafetymc.pojos.ObjectStatus;
import com.pulego.tshwanesafetymc.pojos.ObjectType;

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
            + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TAG_NAME + " TEXT,"
            + KEY_CREATED_AT + " DATETIME" + ")";
 
    
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
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		 // on upgrade drop older tables
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NO_OF_INCIDENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NO_OF_TYPES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NO_OF_STATUS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TAG);
 
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
	/*
	 * Getting type
	 * */
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
}
