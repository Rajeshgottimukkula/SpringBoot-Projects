package org.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Task {

	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
	private int taskId;
	private String taskName;
	private String taskDeadline;
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTaskDeadline() {
		return taskDeadline;
	}
	public void setTaskDeadline(String taskDeadline) {
		this.taskDeadline = taskDeadline;
	}
	public Task() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Task(int taskId, String taskName, String taskDeadline) {
		super();
		this.taskId = taskId;
		this.taskName = taskName;
		this.taskDeadline = taskDeadline;
	}
	
}
