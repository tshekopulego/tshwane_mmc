package com.pulego.tshwanesafetymc.pojos;

import java.util.List;

public class ConvertToStringArray {
	private int id;
	
    public ConvertToStringArray() {
		super();
	}
	public String[] getStringIncidentsDate(List<Incidents> list){
    	String[] arrayDate =new String[list.size()];
    	for(int i=0;i>list.size();i++){
    		arrayDate[i]=list.get(i).getDateOfIncidents();
    	}
		return arrayDate;
    }
    public int[] getStringIncidentsTotal(List<Incidents> list){
    	int[] arraytotal =new int[list.size()];
    	for(int i=0;i>list.size();i++){
    		arraytotal[i]=list.get(i).getTotalNoOfIncidents();
    	}
		return arraytotal;
    }
    public String[] getStringStatusDate(List<Status> list){
    	String[] arrayDate =new String[list.size()];
    	for(int i=0;i>list.size();i++){
    		arrayDate[i]=list.get(i).getStatusName();
    	}
		return arrayDate;
    }
    public int[] getStringStatusTotal(List<Status> list){
    	int[] arraytotal =new int[list.size()];
    	for(int i=0;i>list.size();i++){
    		arraytotal[i]=list.get(i).getStatusTotal();
    	}
		return arraytotal;
    }
    public String[] getStringTypeDate(List<Type> list){
    	String[] arrayDate =new String[list.size()];
    	for(int i=0;i>list.size();i++){
    		arrayDate[i]=list.get(i).getTypeName();
    	}
		return arrayDate;
    }
    public int[] getStringTypeTotal(List<Type> list){
    	int[] arraytotal =new int[list.size()];
    	for(int i=0;i>list.size();i++){
    		arraytotal[i]=list.get(i).getTotalType();
    	}
		return arraytotal;
    }
}
