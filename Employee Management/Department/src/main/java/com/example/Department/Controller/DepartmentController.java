package com.example.Department.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Department.Entity.Department;
import com.example.Department.Service.DepartmentService;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

	@Autowired
	DepartmentService ds;
	
	
	@PostMapping("/")
	public Department saveDepartment(@RequestBody Department department) {
		return ds.saveDepartment(department);
	}
	@GetMapping("/")
	public List<Department> getDepartments(){
		return ds.getDepartments();
	}
	@GetMapping("/{departmentId}")
	public Department getDepartment(@PathVariable("departmentId") int id) {
		return ds.getDepartment(id);
	}
}
