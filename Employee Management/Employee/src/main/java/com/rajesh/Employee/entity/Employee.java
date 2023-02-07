package com.rajesh.Employee.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee {
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private Long employeeId;
private String employeeName;
private String employeeContact;
private Long departmentId;
public Long getEmployeeId() {
	return employeeId;
}
public void setEmployeeId(Long employeeId) {
	this.employeeId = employeeId;
}
public String getEmployeeName() {
	return employeeName;
}
public void setEmployeeName(String employeeName) {
	this.employeeName = employeeName;
}
public String getEmployeeContact() {
	return employeeContact;
}
public void setEmployeeContact(String employeeContact) {
	this.employeeContact = employeeContact;
}
public Long getDepartmentId() {
	return departmentId;
}
public void setDepartmentId(Long departmentId) {
	this.departmentId = departmentId;
}
public Employee(Long employeeId, String employeeName, String employeeContact, Long departmentId) {
	super();
	this.employeeId = employeeId;
	this.employeeName = employeeName;
	this.employeeContact = employeeContact;
	this.departmentId = departmentId;
}
public Employee() {
	super();
	// TODO Auto-generated constructor stub
}


}
