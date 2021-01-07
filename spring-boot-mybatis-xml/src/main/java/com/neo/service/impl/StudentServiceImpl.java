package com.neo.service.impl;

import com.neo.mapper.StudentMapper;
import com.neo.domain.Student;
import com.neo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> queryStudentList() {
        return studentMapper.queryStudentList();
    }
}
