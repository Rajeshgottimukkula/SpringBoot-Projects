package com.example.Employee.Repository;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Employee.Entity.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
	
	@Transactional
	void deleteByEmployeeName(String name);

	@Transactional
	Optional<Employee> findByEmployeeName(String name);

	Employee findByEmployeeId(int id);

}
