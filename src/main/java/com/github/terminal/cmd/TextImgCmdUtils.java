package com.github.terminal.cmd;

import com.github.terminal.util.CmdTextImgUtils;

/**
 * 表格控制台命令
 *
 * @author 欧阳洁
 * @date 2020/10/29 17:28
 */
public class TextImgCmdUtils extends BaseCmdUtils {
    public static boolean excute(String action, String[] params) {
        if (!BaseCmdUtils.excute(action, params)) {
            TextImgCmdUtils.help();
            return false;
        }
        System.out.println(" 工具类型：文字图片【-ti】");
        switch (action) {
            case "-file":
            case "-f":
                System.out.println(" 动作：文件绝对路径【-f】");
                if (params.length < 1) {
                    System.out.println(" 请传入动作的参数：-f [图片绝对路径]");
                    System.out.println(" PS:文件绝对路径含空格需要用双引号括起来");
                    return false;
                }
                System.out.println(" 图片文件路径：【" + params[0] + "】");
                System.out.println(" 图片文字结果：↓↓");
                CmdTextImgUtils.printPicByPath(params[0]);
                break;
            case "-url":
            case "-u":
                System.out.println(" 动作：文件绝对路径【-f】");
                if (params.length < 1) {
                    System.out.println(" 请传入动作的参数：-网络 [图片网络路径]");
                    System.out.println(" PS:文件绝对路径含空格需要用双引号括起来");
                    return false;
                }
                System.out.println(" 图片网络路径：【" + params[0] + "】");
                System.out.println(" 图片文字结果：↓↓");
                CmdTextImgUtils.printPicByUrl(params[0]);
                break;
            default:
                System.out.println(" 未知动作：" + action);
                System.out.println(" 支持动作：-file(f)【文件绝对路径】| -url(u)【网络路径】");
                System.out.println(" 请重试！");
                break;
        }
        return true;
    }


    public static void help() {
        System.out.println("  [textImage] 文字图片工具！");
        System.out.println("   1. 工具类型参数: -textImage(ti)【文字图片】");
        System.out.println("   2. 动作类型参数：-file(f)【图片绝对路径】| -url(u)【网络路径】");
        System.out.println("   3. 表达式：-ti [-f|-u] [图片路径]");
    }

    public static boolean canDeal(String type) {
        return "-textImage".equals(type) || "-ti".equals(type);
    }
}
