package com.rajesh.Department.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rajesh.Department.entity.Department;
import com.rajesh.Department.service.DepartmentService;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

	
	@Autowired
	DepartmentService ds;
	
	@PostMapping("/")
	public Department saveDepartment(@RequestBody Department department) {
		return ds.saveUser(department);
	}
	
	@GetMapping("/")
	public List<Department> getDepartments(){
		return ds.getDepartments();
	}
	@GetMapping("/{id}")
	public Department getDepartmentById(@PathVariable Long id) {
		return ds.getDepartmentById(id);
	}
	
	@DeleteMapping("/{id}")
	public String deleteDepartmentById(@PathVariable Long id) {
		ds.deleteDepartmentById(id);
		return "Department has been deleted succesfully with the id "+id;
	}
	@PutMapping("/{extDepartmentId}")
	public Department updateDepartmentById(@RequestBody Department newDepartment,@PathVariable Long extDepartmentId) {
		return ds.updateDepartmentById(newDepartment,extDepartmentId);
	}
}
