package com.pulego.tshwanesafetymc.pojos;

public class ObjectStatus {
  private int id;
  private String statusName;
  private int statusTotal;
public ObjectStatus() {
	super();
}
public ObjectStatus(String statusName, int statusTotal) {
	super();
	this.statusName = statusName;
	this.statusTotal = statusTotal;
}
public ObjectStatus(int id, String statusName, int statusTotal) {
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
