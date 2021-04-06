package com.neo.util;

import com.neo.constants.Constants;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

/**
 * permission 工具类
 */
public class PermissionUtils {
    private static final Logger log = LoggerFactory.getLogger(PermissionUtils.class);

    /**
     * 查看数据的权限
     */
    public static final String VIEW_PERMISSION = "no.view.permission";

    /**
     * 创建数据的权限
     */
    public static final String CREATE_PERMISSION = "no.create.permission";

    /**
     * 修改数据的权限
     */
    public static final String UPDATE_PERMISSION = "no.update.permission";

    /**
     * 删除数据的权限
     */
    public static final String DELETE_PERMISSION = "no.delete.permission";

    /**
     * 导出数据的权限
     */
    public static final String EXPORT_PERMISSION = "no.export.permission";

    /**
     * 其他数据的权限
     */
    public static final String PERMISSION = "no.permission";

    /**
     * 权限错误消息提醒
     */
    public static String getMsg(String permissionsStr) {
        String permission = StringUtils.substringBetween(permissionsStr, "[", "]");
        String msg = MessageUtils.message(PERMISSION, permission);
        if (StringUtils.endsWithIgnoreCase(permission, Constants.ADD_PERMISSION)) {
            msg = MessageUtils.message(CREATE_PERMISSION, permission);
        } else if (StringUtils.endsWithIgnoreCase(permission, Constants.EDIT_PERMISSION)) {
            msg = MessageUtils.message(UPDATE_PERMISSION, permission);
        } else if (StringUtils.endsWithIgnoreCase(permission, Constants.REMOVE_PERMISSION)) {
            msg = MessageUtils.message(DELETE_PERMISSION, permission);
        } else if (StringUtils.endsWithIgnoreCase(permission, Constants.EXPORT_PERMISSION)) {
            msg = MessageUtils.message(EXPORT_PERMISSION, permission);
        } else if (StringUtils.endsWithAny(permission,
                new String[]{Constants.VIEW_PERMISSION, Constants.LIST_PERMISSION})) {
            msg = MessageUtils.message(VIEW_PERMISSION, permission);
        }
        return msg;
    }
}
