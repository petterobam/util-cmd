package com.github.terminal.cmd;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Url命令行工具
 *
 * @author 欧阳洁
 * @date 2020/10/11 19:33
 */
public class UrlCmdUtils extends BaseCmdUtils {

    public static boolean excute(String action, String[] params) {
        if (!BaseCmdUtils.excute(action, params)) {
            UrlCmdUtils.help();
            return false;
        }
        System.out.println(" 工具类型：URL【-u】");
        switch (action) {
            case "-decode":
            case "-d":
                System.out.println(" 动作：解密URL");
                if (params.length < 1) {
                    System.out.println(" 请传入动作相应的参数：-d [encodeUrl]");
                    return false;
                }
                System.out.println(" 加密的URL：\"" + params[0] + "\"");
                try {
                    System.out.println(" 解密后的URL：\"" + URLDecoder.decode(params[0], "utf-8") + "\"");
                } catch (UnsupportedEncodingException e) {
                    System.out.println(" 解密后失败！");
                }
                return true;
            case "-encode":
            case "-e":
                System.out.println(" 动作：加密URL");
                if (params.length < 1) {
                    System.out.println(" 请传入动作相应的参数：-e [originUrl]");
                    return false;
                }
                System.out.println(" 原URL：\"" + params[0] + "\"");
                try {
                    System.out.println(" 加密后的URL：\"" + URLEncoder.encode(params[0], "utf-8") + "\"");
                } catch (UnsupportedEncodingException e) {
                    System.out.println(" 加密失败！");
                }
                return true;
            default:
                System.out.println(" 未知动作：" + action);
                System.out.println(" 支持动作：-decode(d)【解密】|-encode(e)【加密】");
                System.out.println(" 请重试！");
                return false;
        }
    }

    public static void help() {
        System.out.println("  [url] URL工具！");
        System.out.println("   1. 工具类型参数: -url【u】！");
        System.out.println("   2. 动作类型参数：-decode(d)【解密】| -encode(e)【加密】！");
    }

    public static boolean canDeal(String type) {
        return "-url".equals(type) || "-u".equals(type);
    }
}
