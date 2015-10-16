package com.pulego.tshwanesafetymc.pojos;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

public class ConvertToStringArray {
	private int id;
	
    public ConvertToStringArray() {
		super();
	}
	public String[] getStringArrayIncidentsDate(ArrayList<Incidents> list){
    	String[] arrayDate =new String[list.size()];
    	for(int i=0;i>list.size();i++){
    		arrayDate[i]=list.get(i).getDateOfIncidents();
    		Log.d("Data in Convertor", list.get(i).getDateOfIncidents());
    	}
		return arrayDate;
    }
    public int[] getStringArrayIncidentsTotal(ArrayList<Incidents> list){
    	int[] arraytotal =new int[list.size()];
    	for(int i=0;i>arraytotal.length;i++){
    		arraytotal[i]=list.get(i).getTotalNoOfIncidents();
    		Log.d("Data in Convertor", ""+list.get(i).getTotalNoOfIncidents());
    	}
		return arraytotal;
    }
    public String[] getStringArrayStatusName(List<ObjectStatus> list){
    	String[] arrayDate =new String[list.size()];
    	for(int i=0;i>list.size();i++){
    		arrayDate[i]=list.get(i).getStatusName();
    		
    	}
		return arrayDate;
    }
    public int[] getStringArrayStatusTotal(List<ObjectStatus> list){
    	int[] arraytotal =new int[list.size()];
    	for(int i=0;i>list.size();i++){
    		arraytotal[i]=list.get(i).getStatusTotal();
    	}
		return arraytotal;
    }
    public String[] getStringArrayTypeName(List<ObjectType> list){
    	String[] arrayDate =new String[list.size()];
    	for(int i=0;i>list.size();i++){
    		arrayDate[i]=list.get(i).getTypeName();
    	}
		return arrayDate;
    }
    public int[] getStringArrayTypeTotal(List<ObjectType> list){
    	int[] arraytotal =new int[list.size()];
    	for(int i=0;i>list.size();i++){
    		arraytotal[i]=list.get(i).getTotalType();
    	}
		return arraytotal;
    }
}
