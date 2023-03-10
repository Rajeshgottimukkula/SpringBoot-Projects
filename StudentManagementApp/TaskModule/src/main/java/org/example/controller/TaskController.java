package org.example.controller;

import java.util.List;
import java.util.Optional;

import org.example.entity.Task;
import org.example.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/task")
public class TaskController {

	
	
	@Autowired
	TaskService taskService;
	
	@PostMapping()
	public Task saveTask(@RequestBody Task task) {
		return taskService.saveTask(task);
	}
	
	@GetMapping("/")
	public List<Task> getAllTasks(){
		return taskService.getAllTasks();
	}
	
	@GetMapping("/{id}")
	public Optional<Task> getTaskById(@PathVariable int id) {
		return taskService.getTaskById(id);
	}
}
