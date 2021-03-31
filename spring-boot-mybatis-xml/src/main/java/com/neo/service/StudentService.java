package com.neo.service;

import com.neo.domain.Student;

import java.util.List;

public interface StudentService {
    List<Student> queryStudentList();

    void insertStudent(Student student) throws Exception;
}
