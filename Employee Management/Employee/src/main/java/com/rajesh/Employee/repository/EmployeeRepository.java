package com.rajesh.Employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rajesh.Employee.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Long>{

}
