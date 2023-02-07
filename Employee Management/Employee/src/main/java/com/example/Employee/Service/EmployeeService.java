package com.example.Employee.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.Employee.Entity.Employee;
import com.example.Employee.Error.EmployeeNotFoundException;
import com.example.Employee.Error.InvalidNameException;
import com.example.Employee.Repository.EmployeeRepo;
import com.example.Employee.ResponseTemplateVO.Department;
import com.example.Employee.ResponseTemplateVO.ResponseTemplateVO;

import lombok.extern.log4j.Log4j2;
@Log4j2
@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepo er;
	
	
	
	
	
	
	public Employee getEmployeeById(int id) {
		Optional<Employee> employee=er.findById(id);
		if(employee.isPresent()) {
			return employee.get();
		}
		else {
			return null;
		}
	}
	
	public Employee saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
		log.info("Saving employee");
		return er.save(employee);
	}


	public List<Employee> getEmployees() {
		// TODO Auto-generated method stub
		log.info("getting all the employees");
		return er.findAll();
	}


	public void deleteEmployee(String name) {
		// TODO Auto-generated method stub
		er.deleteByEmployeeName(name);
		
	}

	ResponseTemplateVO vo=new ResponseTemplateVO();
	@Autowired
	RestTemplate restTemplate; 

	public ResponseTemplateVO getEmployee(String name) {
		
		String regexUserName = "^[A-Za-z\\s]+$";
		
	if(name.matches(regexUserName)) {
		// TODO Auto-generated method stub
		Optional<Employee> employee= er.findByEmployeeName(name);
		log.info("getting employee with name");
		if(employee.isEmpty()) {
			throw new EmployeeNotFoundException("Employee with Name "+name+" not found, please try again!");
		}
		else {
		
		
		Department department=restTemplate.getForObject("http://localhost:8062/departments/"+ employee.get().getDepartmentId(), Department.class);
		vo.setDepartment(department);
		vo.setEmployee(employee);
		log.info("returning vo object with employee and department");
		return vo;
		}
	}
		else {
			throw new InvalidNameException("Invalid name, Name should not consist digits and special characters!");
		}
	
		
		
	}

}
