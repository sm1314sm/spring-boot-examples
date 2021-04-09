package com.neo.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 通用响应对象 @ApiModel
 * 对象属性 @ApiModelProperty
 */
@ApiModel(description = "响应对象")
public class BaseResult<T> {
    private static final String SUCCESS_CODE = "200";

    private static final String SUCCESS_MESSAGE = "请求成功";

    @ApiModelProperty(value = "响应码", name = "code", required = true, example = SUCCESS_CODE)
    private String code;

    @ApiModelProperty(value = "响应消息", name = "msg", required = true, example = SUCCESS_MESSAGE)
    private String msg;

    @ApiModelProperty(value = "响应数据", name = "data")
    private T data;

    private BaseResult(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private BaseResult(String code, String msg) {
        this(code, msg, null);
    }

    private BaseResult(T data) {
        this(SUCCESS_CODE, SUCCESS_MESSAGE, data);
    }

    public static <T> BaseResult<T> failWithCodeAndMsg(String code, String msg) {
        return new BaseResult<>(code, msg, null);
    }

    public static <T> BaseResult<T> successWithData(T data) {
        return new BaseResult<>(data);
    }

    public static <T> BaseResult<T> buildWithParam(ResponseParam param) {
        return new BaseResult<>(param.getCode(), param.getMsg(), null);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static class ResponseParam {
        private String code;

        private String msg;

        private ResponseParam(String code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public static ResponseParam buildParam(String code, String msg) {
            return new ResponseParam(code, msg);
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }
}
