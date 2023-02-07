package com.rajesh.Department.service;

import java.util.List;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Objects;
import com.rajesh.Department.entity.Department;
import com.rajesh.Department.repository.DepartmentRepository;

@Service
public class DepartmentService {

	@Autowired
	DepartmentRepository dr;
	
	public Department saveUser(Department department) {
		// TODO Auto-generated method stub
		return dr.save(department);
	}

	public List<Department> getDepartments() {
		// TODO Auto-generated method stub
		return dr.findAll();
	}

	public Department getDepartmentById(Long id) {
		// TODO Auto-generated method stub
		return dr.findById(id).get();
	}

	public void deleteDepartmentById(Long id) {
		// TODO Auto-generated method stub
		dr.deleteById(id);
		
	}

	public Department updateDepartmentById(Department newDepartment, Long extDepartmentId) {
		// TODO Auto-generated method stub
		Department extDepartment=dr.findById(extDepartmentId).get();
		if(java.util.Objects.nonNull(newDepartment.getDepartmentId())) {
			extDepartment.setDepartmentId(newDepartment.getDepartmentId());
		}
	    if(java.util.Objects.nonNull(newDepartment.getDepartmentName())) {
	    	extDepartment.setDepartmentName(newDepartment.getDepartmentName());
	    }
	    if(java.util.Objects.nonNull(newDepartment.getDepartmentAddress())) {
	    	extDepartment.setDepartmentAddress(newDepartment.getDepartmentAddress());
	    }
		
		return dr.save(extDepartment);
	}

}
