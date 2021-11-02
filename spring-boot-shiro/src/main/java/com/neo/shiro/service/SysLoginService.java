package com.neo.shiro.service;

import com.neo.constants.Constants;
import com.neo.domain.SysUser;
import com.neo.exception.user.UserBlockedException;
import com.neo.exception.user.UserNotExistException;
import com.neo.exception.user.UserOrPasswordIsNullException;
import com.neo.sevice.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class SysLoginService {
    @Autowired
    private SysUserService userInfoService;

    @Autowired
    private SysValidateService sysPasswordService;

    /**
     * 登录校验
     */
    public SysUser login(String username, String password) {
        // 用户名或密码为空
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new UserOrPasswordIsNullException();
        }
        // 根据用户名查询用户
        SysUser sysUser = userInfoService.findByUsername(username);
        // 用户不存在
        if (sysUser == null) {
            throw new UserNotExistException();
        }
        // 用户被锁定
        if (Constants.Lock.equals(sysUser.getState())) {
            throw new UserBlockedException();
        }
        // 验证密码是否正确
        sysPasswordService.validate(sysUser, password);
        return sysUser;
    }
}
