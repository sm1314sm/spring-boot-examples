package com.neo.utils;

import java.util.HashMap;

/**
 * 操作消息提醒
 */
public class ResultUtil extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    public static final String CODE_TAG = "code";

    /**
     * 返回内容
     */
    public static final String MSG_TAG = "msg";

    /**
     * 数据对象
     */
    public static final String DATA_TAG = "data";

    /**
     * 状态类型
     */
    public enum Type {
        /**
         * 成功
         */
        SUCCESS(200),
        /**
         * 错误
         */
        ERROR(500);

        private final int value;

        Type(int value) {
            this.value = value;
        }

        public int value() {
            return this.value;
        }
    }

    /**
     * 初始化一个新创建的 AjaxResult对象
     */
    public ResultUtil() {
    }

    /**
     * 初始化一个新创建的 AjaxResult对象
     */
    public ResultUtil(Type type, String msg) {
        super.put(CODE_TAG, type.value);
        super.put(MSG_TAG, msg);
    }

    /**
     * 初始化一个新创建的 AjaxResult对象
     */
    public ResultUtil(Type type, String msg, Object data) {
        super.put(CODE_TAG, type.value);
        super.put(MSG_TAG, msg);
        if (data != null) {
            super.put(DATA_TAG, data);
        }
    }

    /**
     * 返回成功消息
     */
    public static ResultUtil success() {
        return ResultUtil.success("操作成功");
    }

    /**
     * 返回成功数据
     */
    public static ResultUtil success(Object data) {
        return ResultUtil.success("操作成功", data);
    }

    /**
     * 返回成功消息
     */
    public static ResultUtil success(String msg) {
        return ResultUtil.success(msg, null);
    }

    /**
     * 返回成功消息
     */
    public static ResultUtil success(String msg, Object data) {
        return new ResultUtil(Type.SUCCESS, msg, data);
    }

    /**
     * 返回错误消息
     */
    public static ResultUtil error() {
        return ResultUtil.error("操作失败");
    }

    /**
     * 返回错误消息
     */
    public static ResultUtil error(Object data) {
        return ResultUtil.error("操作失败", data);
    }

    /**
     * 返回错误消息
     */
    public static ResultUtil error(String msg) {
        return ResultUtil.error(msg, null);
    }

    /**
     * 返回错误消息
     */
    public static ResultUtil error(String msg, Object data) {
        return new ResultUtil(Type.ERROR, msg, data);
    }
}
