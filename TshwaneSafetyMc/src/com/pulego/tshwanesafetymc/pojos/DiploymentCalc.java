package com.pulego.tshwanesafetymc.pojos;

public class DiploymentCalc {
    private int id;
    private String date;
    private String shift;
    private int total_members;
    private int total_vehicles;  
    private int total_bikes;
    private String progress;
    
	public DiploymentCalc() {
		super();
	}

	public DiploymentCalc(int id, String date, String shift, int total_members,
			int total_vehicles, int total_bikes, String progress) {
		super();
		this.id = id;
		this.date = date;
		this.shift = shift;
		this.total_members = total_members;
		this.total_vehicles = total_vehicles;
		this.total_bikes = total_bikes;
		this.progress = progress;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getShift() {
		return shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}

	public int getTotal_members() {
		return total_members;
	}

	public void setTotal_members(int total_members) {
		this.total_members = total_members;
	}

	public int getTotal_vehicles() {
		return total_vehicles;
	}

	public void setTotal_vehicles(int total_vehicles) {
		this.total_vehicles = total_vehicles;
	}

	public int getTotal_bikes() {
		return total_bikes;
	}

	public void setTotal_bikes(int total_bikes) {
		this.total_bikes = total_bikes;
	}

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}
    
}
