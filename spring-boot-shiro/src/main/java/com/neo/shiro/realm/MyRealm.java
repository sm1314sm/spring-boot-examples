package com.neo.shiro.realm;

import com.neo.domain.SysPermission;
import com.neo.domain.SysRole;
import com.neo.domain.SysUser;
import com.neo.exception.user.UserBlockedException;
import com.neo.exception.user.UserNotExistException;
import com.neo.exception.user.UserPasswordNotMatchException;
import com.neo.exception.user.UserPasswordRetryLimitExceedException;
import com.neo.sevice.SysUserService;
import com.neo.shiro.service.SysLoginService;
import com.neo.shiro.service.SysPasswordService;
import com.neo.utils.MessageUtil;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class MyRealm extends AuthorizingRealm {
    protected final Logger logger = LoggerFactory.getLogger(MyRealm.class);

    @Autowired
    private SysUserService userInfoService;

    @Autowired
    private SysPasswordService sysPasswordService;

    @Autowired
    private SysLoginService sysLoginService;

    /**
     * 主要是用来获取权限的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        SysUser sysUser = (SysUser) principals.getPrimaryPrincipal();
        for (SysRole role : sysUser.getRoleList()) {
            authorizationInfo.addRole(role.getRole());
            for (SysPermission p : role.getPermissions()) {
                authorizationInfo.addStringPermission(p.getPermission());
            }
        }
        return authorizationInfo;
    }

    /**
     * 主要是用来身份认证的
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        // 获取用户的输入账号
        String username = upToken.getUsername();
        // 获取用户的输入密码
        String password = new String(upToken.getPassword());
        // 查询用户信息
        SysUser sysUser;
        try {
            sysUser = sysLoginService.login(username, password);
        } catch (UserBlockedException e) {
            throw new AuthenticationException(e.getMessage());
        } catch (UserNotExistException e) {
            throw new AuthenticationException(e.getMessage());
        } catch (UserPasswordNotMatchException e) {
            throw new AuthenticationException(e.getMessage());
        } catch (UserPasswordRetryLimitExceedException e) {
            throw new AuthenticationException(e.getMessage());
        } catch (Exception e) {
            throw new AuthenticationException(e.getMessage());
        }
        return new SimpleAuthenticationInfo(sysUser, password, getName());
    }
}