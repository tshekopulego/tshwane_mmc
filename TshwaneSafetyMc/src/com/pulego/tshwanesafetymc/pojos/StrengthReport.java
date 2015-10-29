package com.pulego.tshwanesafetymc.pojos;

public class StrengthReport {
	private int id;
	private String date;
	private String shift;
	private String reported_by;
	private String supervisor;
	private String region;
	private int members;
	private int vehicles;
	private int bikes;
	private String region_ob;
	private String nodal_ob;
	private String remarks;
	private String nodal_remarks;
	private String nodal_ob_capturedby;
	public StrengthReport() {
		super();
	}
	public StrengthReport(int id, String date, String shift,
			String reported_by, String supervisor, String region, int members,
			int vehicles, int bikes, String region_ob, String nodal_ob,
			String remarks, String nodal_remarks, String nodal_ob_capturedby) {
		super();
		this.id = id;
		this.date = date;
		this.shift = shift;
		this.reported_by = reported_by;
		this.supervisor = supervisor;
		this.region = region;
		this.members = members;
		this.vehicles = vehicles;
		this.bikes = bikes;
		this.region_ob = region_ob;
		this.nodal_ob = nodal_ob;
		this.remarks = remarks;
		this.nodal_remarks = nodal_remarks;
		this.nodal_ob_capturedby = nodal_ob_capturedby;
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
	public String getReported_by() {
		return reported_by;
	}
	public void setReported_by(String reported_by) {
		this.reported_by = reported_by;
	}
	public String getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public int getMembers() {
		return members;
	}
	public void setMembers(int members) {
		this.members = members;
	}
	public int getVehicles() {
		return vehicles;
	}
	public void setVehicles(int vehicles) {
		this.vehicles = vehicles;
	}
	public int getBikes() {
		return bikes;
	}
	public void setBikes(int bikes) {
		this.bikes = bikes;
	}
	public String getRegion_ob() {
		return region_ob;
	}
	public void setRegion_ob(String region_ob) {
		this.region_ob = region_ob;
	}
	public String getNodal_ob() {
		return nodal_ob;
	}
	public void setNodal_ob(String nodal_ob) {
		this.nodal_ob = nodal_ob;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getNodal_remarks() {
		return nodal_remarks;
	}
	public void setNodal_remarks(String nodal_remarks) {
		this.nodal_remarks = nodal_remarks;
	}
	public String getNodal_ob_capturedby() {
		return nodal_ob_capturedby;
	}
	public void setNodal_ob_capturedby(String nodal_ob_capturedby) {
		this.nodal_ob_capturedby = nodal_ob_capturedby;
	}
	
	
	
}
