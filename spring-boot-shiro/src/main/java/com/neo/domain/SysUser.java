package com.neo.domain;

import java.io.Serializable;
import java.util.List;

public class SysUser implements Serializable{
    // 编号
    private Integer id;

    // 名称
    private String name;

    // 帐号
    private String username;

    // 密码
    private String password;

    // 加密密码的盐
    private String salt;

    // 用户状态
    private byte state;

    // 用户 - 角色关系定义
    private List<SysRole> roleList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    public List<SysRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<SysRole> roleList) {
        this.roleList = roleList;
    }
}