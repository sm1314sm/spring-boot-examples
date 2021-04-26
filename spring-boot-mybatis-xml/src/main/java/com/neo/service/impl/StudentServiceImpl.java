package com.neo.service.impl;

import com.neo.mapper.StudentMapper;
import com.neo.domain.Student;
import com.neo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> queryStudentList() {
        return studentMapper.queryStudentList();
    }

    @Override
    @Transactional
    public void insertStudent(Student student) {
        System.out.println("11b");
        studentMapper.insertStudent(student);
        int a = 1 / 0;
        System.out.println("11");
        studentMapper.insertStudent(student);
    }
}
