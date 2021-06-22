package com.neo.exception.user;

/**
 * 用户锁定异常类
 */
public class UserBlockedException extends UserBaseException {
    private static final long serialVersionUID = 1L;

    public UserBlockedException() {
        super("user.blocked", null);
    }
}
