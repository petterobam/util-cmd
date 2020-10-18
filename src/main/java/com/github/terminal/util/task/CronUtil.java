package com.github.terminal.util.task;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * Cron表达式工具类
 */
public class CronUtil {
    /**
     * 方法摘要：构建Cron表达式
     *
     * @param cronData
     * @return String
     */
    public static String createExpression(CronData cronData) {
        StringBuffer cronExp = new StringBuffer();

        if (null == cronData.getJobType()) {
            // 执行周期未配置
            return "ERROR:执行周期未配置";
        }

        if (cronData.getJobType() == 4) {
            if (cronData.getSecond() > 0) {
                cronExp.append("0/").append(cronData.getSecond()).append(" ");
                cronExp.append("* ");
                cronExp.append("* ");
            } else if (cronData.getMinute() > 0) {
                cronExp.append("* ");
                cronExp.append("0/").append(cronData.getMinute()).append(" ");
                cronExp.append("* ");
            } else if (cronData.getHour() > 0) {
                cronExp.append("* ");
                cronExp.append("* ");
                cronExp.append("0/").append(cronData.getHour()).append(" ");
            }
            cronExp.append("* ");
            cronExp.append("* ");
            cronExp.append("?");
        } else {
            //秒
            cronExp.append(cronData.getSecond()).append(" ");
            //分
            cronExp.append(cronData.getMinute()).append(" ");
            //小时
            cronExp.append(cronData.getHour()).append(" ");

            // 每天
            if (cronData.getJobType() == 1) {
                //日
                cronExp.append("* ");
                //月
                cronExp.append("* ");
                //周
                cronExp.append("?");
            }
            // 按每周
            else if (cronData.getJobType() == 3) {
                // 一个月中第几天
                cronExp.append("? ");
                // 月份
                cronExp.append("* ");
                // 周
                int[] weeks = cronData.getDayOfWeeks();
                for (int i = 0; i < weeks.length; i++) {
                    if (i == 0) {
                        cronExp.append(weeks[i]);
                    } else {
                        cronExp.append(",").append(weeks[i]);
                    }
                }

            }
            // 按每月
            else if (cronData.getJobType() == 2) {
                // 一个月中的哪几天
                int[] days = cronData.getDayOfMonths();
                for (int i = 0; i < days.length; i++) {
                    if (i == 0) {
                        cronExp.append(days[i]);
                    } else {
                        cronExp.append(",").append(days[i]);
                    }
                }
                // 月份
                cronExp.append(" * ");
                // 周
                cronExp.append("?");
            }
        }
        return cronExp.toString();
    }

    /**
     * 方法摘要：生成计划的详细描述
     *
     * @param cronData
     * @return String
     */
    public static String createDescription(CronData cronData) {
        StringBuffer description = new StringBuffer();
        // 计划执行开始时间
        if (cronData.getJobType() == 4) {
            description.append("每间隔");
            if (cronData.getSecond() > 0) {
                description.append(cronData.getSecond()).append("秒");
            } else if (cronData.getMinute() > 0) {
                description.append(cronData.getMinute()).append("分");
            } else if (cronData.getHour() > 0) {
                description.append(cronData.getHour()).append("时");
            }
            description.append("执行一次");
        } else {
            // 按每天
            if (cronData.getJobType() == 1) {
                description.append("每天");
            }
            // 按每周
            else if (cronData.getJobType() == 3) {
                if (cronData.getDayOfWeeks() != null && cronData.getDayOfWeeks().length > 0) {
                    String days = "";
                    for (int i : cronData.getDayOfWeeks()) {
                        days += "周" + i;
                    }
                    description.append("每周的").append(days).append("的");
                }
            }
            // 按每月
            else if (cronData.getJobType() == 2) {
                // 选择月份
                if (cronData.getDayOfMonths() != null && cronData.getDayOfMonths().length > 0) {
                    String days = "";
                    for (int i : cronData.getDayOfMonths()) {
                        days += i + "号";
                    }
                    description.append("每月的").append(days).append("的");
                }
            }
            description.append(cronData.getHour()).append("时");
            description.append(cronData.getMinute()).append("分");
            description.append(cronData.getSecond()).append("秒");
            description.append("执行");
        }
        return description.toString();
    }

    public static CronData getCronDataByExpression(String param) {
        String[] cronStrs = param.split(" ");
        CronData cron = new CronData();
        int jobType = 1;
        if (cronStrs.length > 0) {
            if (cronStrs[0].contains("/")) {
                cron.setSecond(NumberUtils.toInt(cronStrs[0].split("/")[1]));
                jobType = 4;
            } else {
                cron.setSecond(NumberUtils.toInt(cronStrs[0]));
            }
        }
        if (cronStrs.length > 1) {
            if (cronStrs[1].contains("/")) {
                cron.setMinute(NumberUtils.toInt(cronStrs[1].split("/")[1]));
                jobType = 4;
            } else {
                cron.setMinute(NumberUtils.toInt(cronStrs[1]));
            }
        }
        if (cronStrs.length > 2) {
            if (cronStrs[2].contains("/")) {
                cron.setHour(NumberUtils.toInt(cronStrs[2].split("/")[1]));
                jobType = 4;
            } else {
                cron.setHour(NumberUtils.toInt(cronStrs[2]));
            }
        }

        if (cronStrs.length > 4 && cronStrs[4].contains(",")) {
            String[] dayStrs = cronStrs[4].split(",");
            int[] days = new int[dayStrs.length];
            for (int i = 0; i < dayStrs.length; i++) {
                days[i] = NumberUtils.toInt(dayStrs[i], 0);
            }
            cron.setDayOfMonths(days);
            jobType = 2;
        } else if (cronStrs.length > 5 && cronStrs[5].contains(",")) {
            String[] dayStrs = cronStrs[5].split(",");
            int[] days = new int[dayStrs.length];
            for (int i = 0; i < dayStrs.length; i++) {
                days[i] = NumberUtils.toInt(dayStrs[i], 0);
            }
            cron.setDayOfMonths(days);
            jobType = 3;
        }
        cron.setJobType(jobType);
        return cron;
    }
}
