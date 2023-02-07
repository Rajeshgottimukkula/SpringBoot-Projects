package org.example.controller;

import java.util.List;
import java.util.Optional;

import org.example.entity.Course;
import org.example.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/course")
public class CourseController {

	
	
	@Autowired
	CourseService courseService;
	
	
	
	@PostMapping("/")
	public Course saveCourse(@RequestBody Course course) {
		return courseService.saveCourse(course);
	}
	
	@GetMapping("/")
	public List<Course> getAllCourses(){
		return courseService.getAllCourses();
	}
	
	@GetMapping("/{name}")
	public Course getCourseByName(@PathVariable("name") String name) {
		return courseService.getCourseByName(name);
	}
}
