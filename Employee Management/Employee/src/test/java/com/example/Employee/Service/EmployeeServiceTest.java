package com.example.Employee.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.example.Employee.Entity.Employee;
import com.example.Employee.Repository.EmployeeRepo;
import com.example.Employee.ResponseTemplateVO.Department;
import com.example.Employee.ResponseTemplateVO.ResponseTemplateVO;
@SpringBootTest
class EmployeeServiceTest {
	@Mock
	EmployeeRepo er;
	
	@Mock
	RestTemplate restTemplate;
	
	@InjectMocks
	EmployeeService employeeService=new EmployeeService();
	
	
	@DisplayName("Saving employee-success scenario")
	@Test
	void test_When_Employee_saved_succesfully() {
		//Mocking the internal calls of a method
		
		Employee employee=getMockEmployee();
		
		Mockito.when(er.findByEmployeeName(anyString()))
		.thenReturn(Optional.of(employee));
		
		Mockito.when(restTemplate.getForObject("http://localhost:8062/departments/"+ employee.getDepartmentId(), Department.class))
		.thenReturn(getMockDepartment());
		
		
		
		
		//Actual method
		ResponseTemplateVO vo=employeeService.getEmployee("Rajesh Gottimukkula");
		
		
		
		
		
		
		//Verify
		Mockito.verify(er,times(1)).findByEmployeeName(anyString());
		Mockito.verify(restTemplate,times(1)).getForObject("http://localhost:8062/departments/"+ employee.getDepartmentId(), Department.class);
		
		
		
		
		
		
		
		
		
		//Assertions
		Assertions.assertNotNull(vo);
		Assertions.assertEquals(employee.getEmployeeName(),vo.getEmployee().get().getEmployeeName());
		
		
		
		
	}


	private Department getMockDepartment() {
		// TODO Auto-generated method stub
		return Department.builder()
				.departmentId(1)
				.departmentName("BCM BU")
				.departmentAddress("Waverock, Hyderabad")
				.build();
	}


	private Employee getMockEmployee() {
		// TODO Auto-generated method stub
		return Employee.builder()
				.employeeId(1)
				.employeeName("Rajesh Gottimukkula")
				.employeeContactNumber("8099099004")
				.departmentId(1)
				.build();
	}
	
	
	
	
	

}
