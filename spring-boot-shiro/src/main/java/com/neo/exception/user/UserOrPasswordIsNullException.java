package com.neo.exception.user;

public class UserOrPasswordIsNullException extends UserBaseException {
    private static final long serialVersionUID = 1L;

    public UserOrPasswordIsNullException() {
        super("user.or.password.is.null", null);
    }
}
