package com.neo.mapper;

import com.neo.domain.Teacher;

import java.util.List;

public interface TeacherMapper {
    List<Teacher> queryTeacherList();

    void insertTeacher(Teacher teacher);
}
