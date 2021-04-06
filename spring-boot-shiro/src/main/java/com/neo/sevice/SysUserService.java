package com.neo.sevice;

import com.neo.domain.SysUser;

public interface SysUserService {
    /**
     * 通过username查找用户信息
     */
    SysUser findByUsername(String username);
}