package org.example.VO;


import org.example.entity.Student;

public class ResponseTemplateVO {


    private Student student;
    private Task task;
    //private Course course;
    private Course course;
    public ResponseTemplateVO(Student student,Task task, Course course) {
        super();
        this.student = student;
        this.task = task;
        this.course = course;
    }
    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
    }
    public Task getTask() {
        return task;
    }
    public void setTask(Task task) {
        this.task = task;
    }
    public Course getCourse() {
        return course;
    }
    public void setCourse(Course course) {
        this.course = course;
    }
    public ResponseTemplateVO() {
        super();
        // TODO Auto-generated constructor stub
    }

}
