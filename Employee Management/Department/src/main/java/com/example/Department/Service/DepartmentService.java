package com.example.Department.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Department.Entity.Department;
import com.example.Department.Repo.DepartmentRepo;

@Service
public class DepartmentService {

	@Autowired
	DepartmentRepo dr;
	public Department saveDepartment(Department department) {
		// TODO Auto-generated method stub
		return dr.save(department);
	}
	public List<Department> getDepartments() {
		// TODO Auto-generated method stub
		return dr.findAll();
	}
	public Department getDepartment(int id) {
		// TODO Auto-generated method stub
		return dr.findById(id).get();
	}

}
