package com.neo.utils;

/**
 * 字符串工具类
 */
public class StringUtil extends org.apache.commons.lang3.StringUtils {
    /**
     * 是否包含字符串
     */
    public static boolean inStringIgnoreCase(String str, String... strs) {
        if (str != null && strs != null) {
            for (String s : strs) {
                if (str.equalsIgnoreCase(trim(s))) {
                    return true;
                }
            }
        }
        return false;
    }
}
