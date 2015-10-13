package com.pulego.tshwanesafetymc.pojos;

public class ObjectType {
    private int id;
    private String typeName;
    private int totalType;
	public ObjectType() {
		super();
	}
	public ObjectType(String typeName, int totalType) {
		super();
		this.typeName = typeName;
		this.totalType = totalType;
	}
	public ObjectType(int id, String typeName, int totalType) {
		super();
		this.id = id;
		this.typeName = typeName;
		this.totalType = totalType;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public int getTotalType() {
		return totalType;
	}
	public void setTotalType(int totalType) {
		this.totalType = totalType;
	}
    
}
