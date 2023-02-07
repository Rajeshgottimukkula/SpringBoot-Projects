package com.example.Employee.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Builder;

@Entity
@Builder
public class Employee {
    @Id
	private int employeeId;
	
	private String employeeName;
	private String employeeContactNumber;
	private int departmentId;
	public Employee(int employeeId, String employeeName, String employeeContactNumber, int departmentId) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.employeeContactNumber = employeeContactNumber;
		this.departmentId = departmentId;
	}
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeContactNumber() {
		return employeeContactNumber;
	}
	public void setEmployeeContactNumber(String employeeContactNumber) {
		this.employeeContactNumber = employeeContactNumber;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
}
