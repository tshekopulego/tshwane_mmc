package com.pulego.tshwanesafetymc.convertors;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.pulego.tshwanesafetymc.helper.DatabaseOpenHelper;
import com.pulego.tshwanesafetymc.pojos.ConvertToStringArray;
import com.pulego.tshwanesafetymc.pojos.ObjectStatus;
import com.pulego.tshwanesafetymc.pojos.ObjectType;

public class StatusConverted {
	private Context ctxt;
    private DatabaseOpenHelper db;
    private ConvertToStringArray stringConvertor;
    
	public StatusConverted(Context ctxt) {
		super();
		this.ctxt = ctxt;
		stringConvertor=new ConvertToStringArray();
	}
    public String[] getArrayStatusName(){
    	List<ObjectStatus> objStatus = new ArrayList<ObjectStatus>();
    	db=new DatabaseOpenHelper(ctxt);
    	objStatus= db.getAllSatus();
    	db.closeDB();
		return stringConvertor.getStringArrayStatusName(objStatus);
    	
    }
    public int[] getArrayStatusTotal(){
    	List<ObjectStatus> objStatus = new ArrayList<ObjectStatus>();
    	db=new DatabaseOpenHelper(ctxt);
    	objStatus= db.getAllSatus();
    	db.closeDB();
		return stringConvertor.getStringArrayStatusTotal(objStatus);
    }
}
