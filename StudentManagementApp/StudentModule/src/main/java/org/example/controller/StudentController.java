package org.example.controller;


import org.example.VO.ResponseTemplateVO;
import org.example.entity.Student;
import org.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {




    @Autowired
    StudentService studentService;


    @PostMapping("/registerStudent")
    public Student registerStudent(@RequestBody Student student) {

        return studentService.registerStudent(student);

    }


    @GetMapping("/allStudents")
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseTemplateVO getStudentById(@PathVariable int id) {
        return studentService.getStudentById(id);

    }
}

