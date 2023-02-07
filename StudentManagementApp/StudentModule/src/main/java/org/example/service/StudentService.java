package org.example.service;

import lombok.extern.log4j.Log4j2;
import org.example.VO.ResponseTemplateVO;
import org.example.entity.Course;
import org.example.entity.Student;
import org.example.entity.Task;
import org.example.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    RestTemplate restTemplate;

    public Student registerStudent(Student student) {
        // TODO Auto-generated method stub


        log.info("Saving the student "+student+" to the database");
        return studentRepository.save(student);
    }



    public List<Student> getAllStudents() {
        // TODO Auto-generated method stub
        log.info("Getting all the students info");
        return studentRepository.findAll();
    }



    public ResponseTemplateVO getStudentById(int id) {
        // TODO Auto-generated method stub

        ResponseTemplateVO vo=new ResponseTemplateVO();
        log.info("Getting the student with id "+id);

        Student student=studentRepository.findById(id).get();



        CourseService courseService=new CourseService();
        TaskService taskService=new TaskService();

        Course course = courseService.getCourseByName(student.getCourseName());

        org.example.VO.Course course1= org.example.VO.Course.builder()
                .courseId(course.getCourseId())
                .courseName(course.getCourseName())
                .courseDuration(course.getCourseDuration())
                .build();

        //Course course=restTemplate.getForObject("http://localhost:8031/course/"+student.getCourseName(), Course.class);
        //Task task=restTemplate.getForObject("http://localhost:8032/task/"+student.getTaskId(), Task.class);
Task task= taskService.getTaskById(student.getTaskId()).get();

        org.example.VO.Task task1= org.example.VO.Task.builder()
                .taskId(task.getTaskId())
                .taskName(task.getTaskName())
                .taskDeadline(task.getTaskDeadline()).build();

       // vo.setCourse(course);
        vo.setCourse(course1);
       vo.setStudent(student);
        vo.setTask(task1);
        return vo;
    }




}