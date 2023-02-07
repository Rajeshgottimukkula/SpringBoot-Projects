package com.rajesh.Employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rajesh.Employee.entity.Employee;
import com.rajesh.Employee.repository.EmployeeRepository;

@Service
public class EmployeeService {

	
	@Autowired
	EmployeeRepository er;
	public Employee saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return er.save(employee);
	}
	public List<Employee> getEmployees() {
		// TODO Auto-generated method stub
		return er.findAll();
	}

}
