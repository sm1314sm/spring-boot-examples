package com.neo.sevice.impl;

import com.neo.dao.SysUserDao;
import com.neo.domain.SysPermission;
import com.neo.domain.SysRole;
import com.neo.domain.SysUser;
import com.neo.sevice.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserDao userInfoDao;

    @Override
    public SysUser findByUsername(String username) {
        SysUser sysUser = userInfoDao.findByUsername(username);
        if (sysUser != null) {
            Integer userId = sysUser.getId();
            List<SysRole> roleList = userInfoDao.findRoleByUserId(userId);
            for (int i = 0; i < roleList.size(); i++) {
                SysRole sysRole = roleList.get(i);
                Integer roleId = sysRole.getId();
                List<SysPermission> permissionList = userInfoDao.findPermissionByRoleId(roleId);
                sysRole.setPermissions(permissionList);
            }
            sysUser.setRoleList(roleList);
        }
        return sysUser;
    }
}