package com.example.Employee.Controller;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.Employee.Entity.Employee;
import com.example.Employee.Repository.EmployeeRepo;
import com.example.Employee.ResponseTemplateVO.Department;
import com.example.Employee.ResponseTemplateVO.ResponseTemplateVO;
import com.example.Employee.Service.EmployeeService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Controller
//@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	EmployeeService es;
	
	
	@Autowired
	EmployeeRepo er;
	
	@PostMapping("/register")
	public String saveEmployee(@ModelAttribute Employee employee,HttpSession session) {
		//return es.saveEmployee(employee);
		es.saveEmployee(employee);
		session.setAttribute("msg", "Employee with name "+employee.getEmployeeName()+" Succesfully saved");
		
		return "redirect:/";
	}
	
	@PostMapping("/update")
	public String updateEmployee(@ModelAttribute Employee e,HttpSession session) {
		es.saveEmployee(e);
		session.setAttribute("msg", "Employee with ID "+e.getEmployeeId()+" Succesfully updated");
		return "redirect:/";
	}
	
	@GetMapping("/delete/{name}")
	public String deleteEmployee1(@PathVariable("name") String name,HttpSession session) {
		es.deleteEmployee(name);
		session.setAttribute("msg", "Employee with name "+name+" has been succesfully deleted");
		return "redirect:/";
	}
	
	
	@GetMapping("/userDetails")
	public String userDetails() {
		return "naveen_index.html";
	}
	
	@GetMapping("/addemp")
	public String addEmp() {
		return "add_emp.html";
	}
	
//	
//	@GetMapping("/")
//	public List<Employee> getEmployees(){
//		
//		return es.getEmployees();
//	}
	
	@GetMapping("/edit/{id}")
	public String editEmpById(@PathVariable("id") int id,Model m) {
		Employee employee=es.getEmployeeById(id);
		m.addAttribute("emp", employee);
		return "employee_edit.html";
	}
	
	
	@GetMapping("/")
	public String getAllEmployees(Model m) {
		
		List<Employee> employees=es.getEmployees();
		m.addAttribute("employees",employees);
		System.out.println(employees);
		
		return "index.html";
	}
	
	ResponseTemplateVO vo=new ResponseTemplateVO();
	
	
	private static final String  SERVICE_A="serviceA";
	
	@Autowired
	RestTemplate restTemplate;
	
	
//	@GetMapping("/{name}")
//	//@CircuitBreaker(name=SERVICE_A,fallbackMethod="departmentServiceFallback")
//	public ResponseTemplateVO getEmployee(@PathVariable("name") String name) {
//		
//		
//		
//		return es.getEmployee(name);
//		
//	}
//	
//	public String departmentServiceFallback(Exception e) {
//		return " Something is wrong with Department service, please try again";
//	}
	
	
	@DeleteMapping("/{name}")
	public String deleteEmployee(@PathVariable("name") String name) {
		es.deleteEmployee(name);
		return "Employee with name "+name+" has been deleted succesfully";
	}
	
	
	@PutMapping("/{id}")
	public Employee updateEmployee(@RequestBody Employee employee,@PathVariable("id") int id) {
		Employee existingData=er.findById(id).get();
		if(Objects.nonNull(employee.getEmployeeId())) {
			existingData.setEmployeeId(employee.getEmployeeId());
			
		}
		if(Objects.nonNull(employee.getEmployeeName())) {
			existingData.setEmployeeName(employee.getEmployeeName());
		}
		if(Objects.nonNull(employee.getEmployeeContactNumber())) {
			existingData.setEmployeeContactNumber(employee.getEmployeeContactNumber());
		}
		if(Objects.nonNull(employee.getEmployeeContactNumber())) {
			existingData.setEmployeeContactNumber(employee.getEmployeeContactNumber());
		}
		if(Objects.nonNull(employee.getDepartmentId())) {
			existingData.setDepartmentId(employee.getDepartmentId());
		}
		return er.save(existingData);
	}
	
}
