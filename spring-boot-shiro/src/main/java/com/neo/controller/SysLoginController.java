package com.neo.controller;

import com.neo.exception.user.UserBlockedException;
import com.neo.exception.user.UserNotExistException;
import com.neo.exception.user.UserPasswordNotMatchException;
import com.neo.exception.user.UserPasswordRetryLimitExceedException;
import com.neo.utils.ResultUtil;
import com.neo.utils.ServletUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SysLoginController {
    /**
     * 首页
     */
    @GetMapping("/index")
    public String index() {
        return "/index";
    }

    /**
     * 登录页面
     */
    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    /**
     * 登录页面
     */
    @ResponseBody
    @PostMapping("/login")
    public ResultUtil ajaxLogin(String username, String password) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return ResultUtil.success();
        } catch (AuthenticationException e) {
            return ResultUtil.error(e.getMessage());
        }
    }

    /**
     * 未授权页面
     */
    @GetMapping("/unauth")
    public String unauth() {
        return "/unauth";
    }
}