package org.example.service;

import java.util.List;
import java.util.Optional;

import org.example.entity.Task;
import org.example.repo.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class TaskService {

	
	@Autowired
	TaskRepo taskRepo;
	
	
	
	public Task saveTask(Task task) {
		// TODO Auto-generated method stub
		return taskRepo.save(task);
	}



	public List<Task> getAllTasks() {
		// TODO Auto-generated method stub
		return taskRepo.findAll();
	}
	
	
	
	public Optional<Task> getTaskById(int id) {
		return taskRepo.findById(id);
	}

}
