package com.neo.shiro.service;

import com.neo.constants.Constants;
import com.neo.domain.SysUser;
import com.neo.exception.user.UserBlockedException;
import com.neo.exception.user.UserNotExistsException;
import com.neo.sevice.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 登录校验方法
 */
@Component
public class SysLoginService {
    @Autowired
    private SysPasswordService sysPasswordService;

    @Autowired
    private SysUserService userInfoService;

    /**
     * 登录
     */
    public SysUser login(String username, String password) {
        // 用户名或密码为空
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new UserNotExistsException();
        }
        SysUser user = userInfoService.findByUsername(username);
        // 查询不到用户信息
        if (user == null) {
            throw new UserNotExistsException();
        }
        // 用户被锁定
        if (Constants.Lock.equals(user.getState())) {
            throw new UserBlockedException();
        }
        sysPasswordService.validate(user, password);
        return user;
    }
}
