package com.neo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.StringJoiner;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Subject {
    private int uId;
    private String uName;

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Subject.class.getSimpleName() + "[", "]")
                .add("uId=" + uId)
                .add("uName='" + uName + "'")
                .toString();
    }
}
