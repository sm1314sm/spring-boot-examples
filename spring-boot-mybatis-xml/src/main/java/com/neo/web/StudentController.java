package com.neo.web;

import com.neo.domain.Student;
import com.neo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}
