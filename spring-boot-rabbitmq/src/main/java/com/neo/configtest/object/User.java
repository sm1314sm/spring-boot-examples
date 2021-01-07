package com.neo.configtest.object;

import java.io.Serializable;
import java.util.StringJoiner;

public class User implements Serializable {
    private static final long serialVersionUID = -2203590215208552218L;

    private String userName;

    private String passWord;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("userName='" + userName + "'")
                .add("passWord='" + passWord + "'")
                .toString();
    }
}
