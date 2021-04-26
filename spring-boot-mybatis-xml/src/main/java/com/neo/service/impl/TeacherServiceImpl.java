package com.neo.service.impl;

import com.neo.mapper.TeacherMapper;
import com.neo.domain.Teacher;
import com.neo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public List<Teacher> queryTeacherList() {
        return teacherMapper.queryTeacherList();
    }

    @Override
    public void insertTeacher(Teacher teacher) {
        int a = 1 / 0;
        System.out.println("11");
        teacherMapper.insertTeacher(teacher);
    }
}
