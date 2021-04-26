package com.neo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.StringJoiner;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Teacher {
    private int tId;
    private String tName;
    private List<Student> tStudent;
    private Subject subject;

    public int gettId() {
        return tId;
    }

    public void settId(int tId) {
        this.tId = tId;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public List<Student> gettStudent() {
        return tStudent;
    }

    public void settStudent(List<Student> tStudent) {
        this.tStudent = tStudent;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Teacher.class.getSimpleName() + "[", "]")
                .add("tId=" + tId)
                .add("tName='" + tName + "'")
                .add("tStudent=" + tStudent)
                .add("subject=" + subject)
                .toString();
    }
}
