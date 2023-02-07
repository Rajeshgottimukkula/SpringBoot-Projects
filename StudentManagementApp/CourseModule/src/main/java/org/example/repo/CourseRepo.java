package org.example.repo;

import java.util.Optional;

import javax.transaction.Transactional;

import org.example.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CourseRepo extends JpaRepository<Course,Integer>{
    @Transactional
	Optional<Course> findByCourseName(String name);

}
