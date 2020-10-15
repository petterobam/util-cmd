package com.github.terminal.cmd;

import com.github.terminal.util.CmdDateUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Date;

/**
 * 时间 cmd 工具
 *
 * @author 欧阳洁
 * @date 2020/9/25 10:46
 */
public class TimeCmdUtils extends BaseCmdUtils {
    public static boolean excute(String action, String[] params) {
        if (!BaseCmdUtils.excute(action, params)) {
            TimeCmdUtils.help();
            return false;
        }
        System.out.println(" 工具类型：时间【-t】");
        Date date = null;
        switch (action) {
            case "-format":
            case "-f":
                System.out.println(" 动作：获得【指定日期】格式化样式");
                if(params.length < 1){
                    System.out.println(" 请传入动作相应数量的业务参数：-f [日期指定：now(当前时间)|timestamp(毫秒)|±?d?h?m(前/后几天几时几分)] [格式化：yyyy-MM-dd HH:mm:ss(例子，自定义)]");
                    return false;
                }
                System.out.println(" 日期指定：" + params[0]);
                if ("now".equals(params[0])) {
                    date = new Date();
                }
                if (null == date && (params[0].startsWith("-") || params[0].startsWith("+"))) {
                    date = CmdDateUtils.getBeforDateOfRegex(params[0]);
                }
                if (null == date && !NumberUtils.isNumber(params[0])) {
                    System.out.println(" ERROR：[日期指定] 非法，无法识别！");
                    return true;
                }
                if (null == date) {
                    date = new Date(NumberUtils.toLong(params[0]));
                }
                String format = params.length < 2 ? "yyyy-MM-dd HH:mm:ss" : params[1].replaceAll("'", "").replaceAll("\"", "");
                System.out.println(" 格式：" + format);
                System.out.println(" 格式化的日期：" + CmdDateUtils.getFormatStr(date, format));
                return true;
            case "-stamp":
            case "-s":
                System.out.println(" 动作：获得【指定日期】的时间戳");
                if(params.length < 1){
                    System.out.println(" 请传入动作相应数量的业务参数：-s [日期指定：now(当前时间)|±?d?h?m(前/后几天几时几分)|格式：yyyy-MM-dd或yyyy-MM-dd HH:mm:ss]");
                    return false;
                }
                System.out.println(" 日期指定：" + params[0]);
                date = null;
                if ("now".equals(params[0])) {
                    date = new Date();
                }
                if (null == date) {
                    if ((params[0].startsWith("-") || params[0].startsWith("+"))) {
                        date = CmdDateUtils.getBeforDateOfRegex(params[0]);
                    }
                }
                if (null == date) {
                    String format2 = params[0].replaceAll("'", "").replaceAll("\"", "");
                    if (format2.length() == 10) {
                        date = CmdDateUtils.getDate(params[0], "yyyy-MM-dd", null);
                    }
                    if (format2.length() == 19) {
                        date = CmdDateUtils.getDate(params[0], "yyyy-MM-dd HH:mm:ss", null);
                    }
                }
                if (null == date) {
                    System.out.println(" ERROR：[日期指定] 非法，无法识别！");
                    return false;
                }
                System.out.println(" 识别为：" + CmdDateUtils.getFormatStr(date, "yyyy-MM-dd HH:mm:ss"));
                System.out.println(" 时间戳：" + date.getTime());
                return true;
            default:
                System.out.println(" 未知动作：" + action);
                System.out.println(" 支持动作：-formate(f)【格式化指定日期】 | -stamp(s) 获取指定日期的时间戳");
                System.out.println(" 请重试！");
                return false;
        }
    }

    public static void help() {
        System.out.println("  [time] 日期时间业务！");
        System.out.println("   1. 工具类型参数: -time(t)【日期】！");
        System.out.println("   2. 动作类型参数：-format(f)【格式化】| -stamp(s)【时间戳】！");
    }

    public static boolean canDeal(String type) {
        return "-time".equals(type) || "-t".equals(type);
    }
}
