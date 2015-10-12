package com.pulego.tshwanesafetymc.pojos;

public class Status {
  private int id;
  private String statusName;
  private int statusTotal;
public Status() {
	super();
}
public Status(String statusName, int statusTotal) {
	super();
	this.statusName = statusName;
	this.statusTotal = statusTotal;
}
public Status(int id, String statusName, int statusTotal) {
	super();
	this.id = id;
	this.statusName = statusName;
	this.statusTotal = statusTotal;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getStatusName() {
	return statusName;
}
public void setStatusName(String statusName) {
	this.statusName = statusName;
}
public int getStatusTotal() {
	return statusTotal;
}
public void setStatusTotal(int statusTotal) {
	this.statusTotal = statusTotal;
}
  
  
}
