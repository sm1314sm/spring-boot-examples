package com.neo.exception.base;

import com.neo.utils.MessageUtil;
import org.springframework.util.StringUtils;
import sun.misc.MessageUtils;

/**
 * 基础异常
 */
public class BaseException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * 所属模块
     */
    private String module;

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误码对应的参数
     */
    private Object[] args;

    /**
     * 错误消息
     */
    private String message;

    /**
     * 构造器
     */
    public BaseException(String module, String code, Object[] args, String message) {
        this.module = module;
        this.code = code;
        this.args = args;
        this.message = MessageUtil.message(code, args);
    }

    public String getModule() {
        return module;
    }

    public String getCode() {
        return code;
    }

    public Object[] getArgs() {
        return args;
    }

    public String getMessage() {
        return message;
    }
}
