package org.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Student {



    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int studentId;
    private String studentName;
    private String courseName;
    private int taskId;
    public int getStudentId() {
        return studentId;
    }
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public String getCourseName() {
        return courseName;
    }
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public int getTaskId() {
        return taskId;
    }
    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
    public Student(int studentId, String studentName, String courseName, int taskId) {
        super();
        this.studentId = studentId;
        this.studentName = studentName;
        this.courseName = courseName;
        this.taskId = taskId;
    }
    public Student() {
        super();
        // TODO Auto-generated constructor stub
    }



}

