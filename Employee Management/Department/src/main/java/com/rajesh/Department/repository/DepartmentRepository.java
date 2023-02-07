package com.rajesh.Department.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rajesh.Department.entity.Department;
@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long>{

}
