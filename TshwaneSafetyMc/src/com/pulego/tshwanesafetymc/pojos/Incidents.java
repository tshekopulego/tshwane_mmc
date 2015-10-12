package com.pulego.tshwanesafetymc.pojos;

public class Incidents {
   private int id;
   private String dateOfIncidents;
   private int totalNoOfIncidents;
	public Incidents() {
		super();
	}
	public Incidents(String dateOfIncidents, int totalNoOfIncidents) {
		super();
		this.dateOfIncidents = dateOfIncidents;
		this.totalNoOfIncidents = totalNoOfIncidents;
	}
	public Incidents(int id, String dateOfIncidents, int totalNoOfIncidents) {
		super();
		this.id = id;
		this.dateOfIncidents = dateOfIncidents;
		this.totalNoOfIncidents = totalNoOfIncidents;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDateOfIncidents() {
		return dateOfIncidents;
	}
	public void setDateOfIncidents(String dateOfIncidents) {
		this.dateOfIncidents = dateOfIncidents;
	}
	public int getTotalNoOfIncidents() {
		return totalNoOfIncidents;
	}
	public void setTotalNoOfIncidents(int totalNoOfIncidents) {
		this.totalNoOfIncidents = totalNoOfIncidents;
	}
	
   
}
