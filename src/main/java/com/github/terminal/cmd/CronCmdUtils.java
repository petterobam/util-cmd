package com.github.terminal.cmd;

import com.github.terminal.util.task.CronData;
import com.github.terminal.util.task.CronUtil;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Date;

/**
 * Cron表达式CMD生成工具
 *
 * @author 欧阳洁
 * @date 2020/10/18 13:26
 */
public class CronCmdUtils extends BaseCmdUtils {
    public static boolean excute(String action, String[] params) {
        if (!BaseCmdUtils.excute(action, params)) {
            CronCmdUtils.help();
            return false;
        }
        System.out.println(" 工具类型：Corn表达式【-c】");
        Date date = null;
        switch (action) {
            case "-expression":
            case "-e":
                System.out.println(" 动作：生成【Corn表达式】");
                if (params.length < 1 || !NumberUtils.isNumber(params[0])) {
                    System.out.println(" 请传入动作相应数量的参数：-e [作业类型:1-每天，2-每月，3-每周，4-间隔] [1<作业类型<4:每（周/月）的那几天，逗号分隔] [秒] [分] [时]");
                    System.out.println(" PS:作业类型<4时，[秒] [分] [时] 按顺序读取，为空默认 0");
                    System.out.println(" PS:作业类型=4时，[秒] [分] [时] 按顺序读取，谁先大于 0 就间隔谁执行");
                    return false;
                }
                CronData cronData = new CronData();
                cronData.setJobType(NumberUtils.toInt(params[0]));
                System.out.println(" 作业类型：" + cronData.jobTypeName());
                int paramIndex = 1;
                if (cronData.getJobType() == 2 || cronData.getJobType() == 3) {
                    if (params.length > paramIndex) {
                        cronData.settingDays(params[paramIndex++]);
                    } else {
                        System.out.println(" " + cronData.jobTypeName() + " 请传入需要那几天执行！");
                        return false;
                    }
                }
                cronData.setSecond(params.length > paramIndex ? NumberUtils.toInt(params[paramIndex++]) : 0);
                cronData.setMinute(params.length > paramIndex ? NumberUtils.toInt(params[paramIndex++]) : 0);
                cronData.setHour(params.length > paramIndex ? NumberUtils.toInt(params[paramIndex++]) : 0);
                System.out.println(" 解读Corn需求：" + CronUtil.createDescription(cronData));
                System.out.println(" 生成Cron表达式：" + CronUtil.createExpression(cronData));
                return true;
            case "-description":
            case "-d":
                System.out.println(" 动作：解析【Cron表达式】");
                if (params.length < 1) {
                    System.out.println(" 请传入动作相应数量的参数：-d [Cron 表达式：用 \" \" 括起来]");
                    return false;
                }
                System.out.println(" Cron表达式：" + params[0]);
                CronData cron = CronUtil.getCronDataByExpression(params[0]);
                System.out.println(" 作业类型：" + cron.jobTypeName());
                System.out.println(" 解读Corn需求：" + CronUtil.createDescription(cron));
                System.out.println(" 生成Cron表达式：" + CronUtil.createExpression(cron));
                return true;
            default:
                System.out.println(" 未知动作：" + action);
                System.out.println(" 支持动作：-expression(e)【表达式】| -description(d)【描述】");
                System.out.println(" 请重试！");
                return false;
        }
    }


    public static void help() {
        System.out.println("  [cron] Corn表达式工具！");
        System.out.println("   1. 工具类型参数: -cron(c)【Cron】！");
        System.out.println("   2. 动作类型参数：-expression(e)【表达式】| -description(d)【描述】！");
    }

    public static boolean canDeal(String type) {
        return "-cron".equals(type) || "-c".equals(type);
    }
}
