package com.neo.exception.user;

import com.neo.exception.base.BaseException;

/**
 * 用户信息异常类
 */
public class UserBaseException extends BaseException {
    private static final long serialVersionUID = 1L;

    public UserBaseException(String code, Object[] args) {
        super("user", code, args, null);
    }
}
