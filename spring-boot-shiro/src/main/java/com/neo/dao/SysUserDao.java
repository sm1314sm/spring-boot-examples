package com.neo.dao;

import com.neo.domain.SysPermission;
import com.neo.domain.SysRole;
import com.neo.domain.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserDao {
    /**
     * 通过username查找用户信息
     */
    SysUser findByUsername(@Param("username") String username);

    /**
     * 通过userId查找角色组信息
     */
    List<SysRole> findRoleByUserId(@Param("userId") Integer userId);

    /**
     * 通过roleId查找权限组信息
     */
    List<SysPermission> findPermissionByRoleId(@Param("roleId") Integer roleId);
}