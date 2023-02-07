package com.example.Employee.ResponseTemplateVO;

import java.util.Optional;

import com.example.Employee.Entity.Employee;

public class ResponseTemplateVO {

	Optional<Employee> employee;
	Department department;
	public ResponseTemplateVO(Optional<Employee> employee, Department department) {
		super();
		this.employee = employee;
		this.department = department;
	}
	public Optional<Employee> getEmployee() {
		return employee;
	}
	public void setEmployee(Optional<Employee> employee2) {
		this.employee = employee2;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public ResponseTemplateVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
