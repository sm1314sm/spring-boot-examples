package com.neo.exception.user;

/**
 * 用户不存在异常类
 */
public class UserNotExistException extends UserBaseException {
    private static final long serialVersionUID = 1L;

    public UserNotExistException() {
        super("user.not.exists", null);
    }
}
