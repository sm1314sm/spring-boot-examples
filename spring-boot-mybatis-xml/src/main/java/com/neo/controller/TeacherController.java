package com.neo.controller;

import com.neo.domain.Student;
import com.neo.domain.Teacher;
import com.neo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping("/getTeachers")
    public List<Teacher> getTeachers() {
        return teacherService.queryTeacherList();
    }

    @PostMapping("/insertTeacher")
    public void insertTeacher(@RequestBody Teacher teacher) {
        try {
            teacherService.insertTeacher(teacher);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("kk");
    }
}
