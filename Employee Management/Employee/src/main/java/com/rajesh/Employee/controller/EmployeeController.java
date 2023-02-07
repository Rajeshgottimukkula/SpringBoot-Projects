//package com.rajesh.Employee.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
//import com.rajesh.Employee.VO.Department;
//import com.rajesh.Employee.VO.ResponseTemplateVO;
//import com.rajesh.Employee.entity.Employee;
//import com.rajesh.Employee.repository.EmployeeRepository;
//import com.rajesh.Employee.service.EmployeeService;
//
//@RestController
//@RequestMapping("/employees")
//public class EmployeeController {
//
//	@Autowired
//	EmployeeRepository er;
//	@Autowired
//	EmployeeService es;
//	@PostMapping("/")
//	public Employee saveEmployee(@RequestBody Employee employee) {
//		return es.saveEmployee(employee);
//	}
////	@GetMapping("/")
////	public List<Employee> getEmployees(){
////		return es.getEmployees();
////	}
//	
//	@Autowired
//	RestTemplate restTemplate;
//	
////	ResponseTemplateVO vo=new ResponseTemplateVO();
////	@GetMapping("/{employeeId}")
////	public ResponseTemplateVO getEmployeeWithDepartment(@PathVariable Long employeeId) {
////		Employee employee=er.findById(employeeId).get();
////		Department dept=restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/"+employee.getDepartmentId(), Department.class);
////		vo.setEmployee(employee);
////		vo.setDepartment(dept);
////		return vo;
////	}
//}
