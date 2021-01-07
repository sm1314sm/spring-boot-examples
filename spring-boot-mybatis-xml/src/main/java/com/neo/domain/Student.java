package com.neo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.StringJoiner;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Student {
    private int sId;
    private String sName;
    private List<Teacher> sTeacher;

    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public List<Teacher> getsTeacher() {
        return sTeacher;
    }

    public void setsTeacher(List<Teacher> sTeacher) {
        this.sTeacher = sTeacher;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Student.class.getSimpleName() + "[", "]")
                .add("sId=" + sId)
                .add("sName='" + sName + "'")
                .add("sTeacher=" + sTeacher)
                .toString();
    }
}
