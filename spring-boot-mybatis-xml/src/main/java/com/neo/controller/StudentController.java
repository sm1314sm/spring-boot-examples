package com.neo.controller;

import com.neo.domain.Student;
import com.neo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/getStudents")
    public List<Student> getStudents() {
        return studentService.queryStudentList();
    }

    @PostMapping("/insertStudent")
    public void insertStudent(@RequestBody Student student) {
        try {
            studentService.insertStudent(student);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
