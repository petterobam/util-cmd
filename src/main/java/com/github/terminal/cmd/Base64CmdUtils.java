package com.github.terminal.cmd;

import org.apache.commons.io.Charsets;

import java.util.Base64;

/**
 * Base64命令行工具
 *
 * @author 欧阳洁
 * @date 2020/10/11 19:33
 */
public class Base64CmdUtils extends BaseCmdUtils {
    private static final Base64.Decoder decoder = Base64.getDecoder();
    private static final Base64.Encoder encoder = Base64.getEncoder();


    public static boolean excute(String action, String[] params) {
        if (!BaseCmdUtils.excute(action, params)) {
            Base64CmdUtils.help();
            return false;
        }
        System.out.println(" 工具类型：Base64业务【-b64】");
        switch (action) {
            case "-decode":
            case "-d":
                System.out.println(" 动作：解密Base64");
                if (params.length < 1) {
                    System.out.println(" 请传入动作相应的业务参数：-d [encodeUrl]");
                    return false;
                }
                System.out.println(" 加密的Base64：\"" + params[0] + "\"");
                System.out.println(" 解密后的Base64：\"" + new String(decoder.decode(params[0]), Charsets.UTF_8) + "\"");
                return true;
            case "-encode":
            case "-e":
                System.out.println(" 动作：加密Base64");
                if (params.length < 1) {
                    System.out.println(" 请传入动作相应的业务参数：-e [originUrl]");
                    return false;
                }
                System.out.println(" 原Base64：\"" + params[0] + "\"");
                System.out.println(" 加密后的Base64：\"" + encoder.encodeToString(params[0].getBytes(Charsets.UTF_8)) + "\"");
                return true;
            default:
                System.out.println(" 未知动作：" + action);
                System.out.println(" 支持动作：-decode(d)【解密】|-encode(e)【加密】");
                System.out.println(" 请重试！");
                return false;
        }
    }

    public static void help() {
        System.out.println("  [base64] Base64业务！");
        System.out.println("   1. 工具类型参数: -base64【b64】！");
        System.out.println("   2. 动作类型参数：-decode(d)【解密】| -encode(e)【加密】！");
    }
}
