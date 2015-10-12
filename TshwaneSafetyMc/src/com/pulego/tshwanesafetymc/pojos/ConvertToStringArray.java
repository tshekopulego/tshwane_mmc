package com.pulego.tshwanesafetymc.pojos;

import java.util.List;

public class ConvertToStringArray {
	private int id;
	
    public ConvertToStringArray() {
		super();
	}
	public String[] getStringArrayIncidentsDate(List<Incidents> list){
    	String[] arrayDate =new String[list.size()];
    	for(int i=0;i>list.size();i++){
    		arrayDate[i]=list.get(i).getDateOfIncidents();
    	}
		return arrayDate;
    }
    public int[] getStringArrayIncidentsTotal(List<Incidents> list){
    	int[] arraytotal =new int[list.size()];
    	for(int i=0;i>list.size();i++){
    		arraytotal[i]=list.get(i).getTotalNoOfIncidents();
    	}
		return arraytotal;
    }
    public String[] getStringArrayStatusDate(List<Status> list){
    	String[] arrayDate =new String[list.size()];
    	for(int i=0;i>list.size();i++){
    		arrayDate[i]=list.get(i).getStatusName();
    	}
		return arrayDate;
    }
    public int[] getStringArrayStatusTotal(List<Status> list){
    	int[] arraytotal =new int[list.size()];
    	for(int i=0;i>list.size();i++){
    		arraytotal[i]=list.get(i).getStatusTotal();
    	}
		return arraytotal;
    }
    public String[] getStringArrayTypeDate(List<Type> list){
    	String[] arrayDate =new String[list.size()];
    	for(int i=0;i>list.size();i++){
    		arrayDate[i]=list.get(i).getTypeName();
    	}
		return arrayDate;
    }
    public int[] getStringArrayTypeTotal(List<Type> list){
    	int[] arraytotal =new int[list.size()];
    	for(int i=0;i>list.size();i++){
    		arraytotal[i]=list.get(i).getTotalType();
    	}
		return arraytotal;
    }
}
