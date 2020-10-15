package com.github.terminal.cmd;

import org.apache.commons.lang3.StringUtils;

/**
 * 基础 cmd 工具
 * @author 欧阳洁
 * @date 2020/9/25 10:54
 */
public class BaseCmdUtils {
    public static boolean excute(String action, String[] params) {
        if (StringUtils.isBlank(action)) {
            System.out.println("请传入动作类型参数！");
            return false;
        }
        return true;
    }
}
