package com.pulego.tshwanesafetymc.convertors;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.pulego.tshwanesafetymc.helper.DatabaseOpenHelper;
import com.pulego.tshwanesafetymc.pojos.ConvertToStringArray;
import com.pulego.tshwanesafetymc.pojos.Incidents;

public class IncidentsConverted {
	private Context ctxt;
    private DatabaseOpenHelper db;
    private ConvertToStringArray stringConvertor;
    
	public IncidentsConverted(Context ctxt) {
		super();
		this.ctxt = ctxt;
		stringConvertor=new ConvertToStringArray();
	}
    public String[] getArrayIncidentsDate(){
    	List<Incidents> objIncidents = new ArrayList<Incidents>();
    	stringConvertor=new ConvertToStringArray();
    	db=new DatabaseOpenHelper(ctxt);
    	objIncidents= db.getAllIncidents();
    	db.closeDB();
		return null;//stringConvertor.getStringArrayIncidentsDate(objIncidents);
    	
    }
    public int[] getArrayIncidentsTotal(){
    	List<Incidents> objIncidents = new ArrayList<Incidents>();
    	stringConvertor=new ConvertToStringArray();
    	db = new DatabaseOpenHelper(ctxt);
    	objIncidents= db.getAllIncidents();
    	db.closeDB();
		return null;// stringConvertor.getStringArrayIncidentsTotal(objIncidents);
    }
}
