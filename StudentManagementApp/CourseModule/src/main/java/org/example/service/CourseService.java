package org.example.service;

import java.util.List;
import java.util.Optional;

import org.example.entity.Course;
import org.example.repo.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CourseService {

	
	@Autowired
	CourseRepo courseRepo;
	
	
	
	public Course saveCourse(Course course) {
		// TODO Auto-generated method stub
		return courseRepo.save(course);
	}



	public List<Course> getAllCourses() {
		// TODO Auto-generated method stub
		return courseRepo.findAll();
	}




	public Course getCourseByName(String name) {
		// TODO Auto-generated method stub
		return courseRepo.findByCourseName(name).get();
	}

}
