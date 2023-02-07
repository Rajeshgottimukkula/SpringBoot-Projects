package com.rajesh.Department.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Department {
    @Id
	private Long departmentId;
	private String departmentName;
	private String departmentAddress;
	
	public void setDepartmentId(Long departmentId) {
		this.departmentId=departmentId;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName=departmentName;
	}
	public void setDepartmentAddress(String departmentAddress) {
		this.departmentAddress=departmentAddress;
	}
	public Long getDepartmentId() {
		return this.departmentId;
	}
	public String getDepartmentName() {
		return this.departmentName;
	}
	public String getDepartmentAddress() {
		return this.departmentAddress;
	}
	public Department(Long departmentId,String departmentName,String departmentAddress) {
		this.departmentId=departmentId;
		this.departmentName=departmentName;
		this.departmentAddress=departmentAddress;
	}
	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
