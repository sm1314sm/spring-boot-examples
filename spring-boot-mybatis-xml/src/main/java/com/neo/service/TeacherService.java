package com.neo.service;

import com.neo.domain.Student;
import com.neo.domain.Teacher;

import java.util.List;

public interface TeacherService {
    List<Teacher> queryTeacherList();

    void insertTeacher(Teacher teacher) throws Exception;
}
