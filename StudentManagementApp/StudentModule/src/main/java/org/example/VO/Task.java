package org.example.VO;

import lombok.Builder;

@Builder
public class Task {



    public Task(int taskId, String taskName, String taskDeadline) {
        super();
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskDeadline = taskDeadline;
    }
    public Task() {
        super();
        // TODO Auto-generated constructor stub
    }
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
    private int taskId;
    private String taskName;
    private String taskDeadline;
}
