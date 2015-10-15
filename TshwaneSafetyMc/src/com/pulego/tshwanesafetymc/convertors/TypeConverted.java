package com.pulego.tshwanesafetymc.convertors;

import java.util.ArrayList;
import java.util.List;

import com.pulego.tshwanesafetymc.helper.DatabaseOpenHelper;
import com.pulego.tshwanesafetymc.pojos.ConvertToStringArray;
import com.pulego.tshwanesafetymc.pojos.ObjectType;

import android.content.Context;

public class TypeConverted {
	private Context ctxt;
    private DatabaseOpenHelper db;
    private ConvertToStringArray stringConvertor;
    
	public TypeConverted(Context ctxt) {
		super();
		this.ctxt = ctxt;
		stringConvertor=new ConvertToStringArray();
	}
    public String[] getArrayTypeName(){
    	List<ObjectType> objType = new ArrayList<ObjectType>();
    	db=new DatabaseOpenHelper(ctxt);
    	objType= db.getAllType();
    	db.closeDB();
		return stringConvertor.getStringArrayTypeName(objType);
    	
    }
    public int[] getArrayTypeTotal(){
    	List<ObjectType> objType = new ArrayList<ObjectType>();
    	db=new DatabaseOpenHelper(ctxt);
    	objType= db.getAllType();
    	db.closeDB();
		return stringConvertor.getStringArrayTypeTotal(objType);
    }
}
