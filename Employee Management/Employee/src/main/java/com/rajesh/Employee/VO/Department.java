package com.rajesh.Employee.VO;

public class Department {
private String departmentName;
private String departmentAddress;
public Department(String departmentName, String departmentAddress) {
	super();
	this.departmentName = departmentName;
	this.departmentAddress = departmentAddress;
}
public Department() {
	super();
	// TODO Auto-generated constructor stub
}
public String getDepartmentName() {
	return departmentName;
}
public void setDepartmentName(String departmentName) {
	this.departmentName = departmentName;
}
public String getDepartmentAddress() {
	return departmentAddress;
}
public void setDepartmentAddress(String departmentAddress) {
	this.departmentAddress = departmentAddress;
}
}
