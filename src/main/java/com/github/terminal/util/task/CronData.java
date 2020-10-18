package com.github.terminal.util.task;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * @author 欧阳洁
 * @date 2020/10/18 12:53
 */
public class CronData {
    /**
     * 所选作业类型:
     * 1  -> 每天
     * 2  -> 每月
     * 3  -> 每周
     * 4  -> 间隔（每隔2个小时，每隔30分钟）
     */
    Integer jobType;

    /**
     * 一周的哪几天
     */
    int[] dayOfWeeks;

    /**
     * 一个月的哪几天
     */
    int[] dayOfMonths;

    /**
     * 秒
     */
    int second;

    /**
     * 分
     */
    int minute;

    /**
     * 时
     */
    int hour;

    public Integer getJobType() {
        return jobType;
    }

    public void setJobType(Integer jobType) {
        this.jobType = jobType;
    }

    public int[] getDayOfWeeks() {
        return dayOfWeeks;
    }

    public void setDayOfWeeks(int[] dayOfWeeks) {
        this.dayOfWeeks = dayOfWeeks;
    }

    public int[] getDayOfMonths() {
        return dayOfMonths;
    }

    public void setDayOfMonths(int[] dayOfMonths) {
        this.dayOfMonths = dayOfMonths;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public String jobTypeName() {
        String result = null;
        switch (jobType) {
            case 1:
                result = "每天";
                break;
            case 2:
                result = "每月";
                break;
            case 3:
                result = "每周";
                break;
            case 4:
                result = "间隔";
                break;
            default:
                result = "未知";
        }
        return result + "(" + jobType + ")";
    }

    public void settingDays(String dayStr) {
        String[] dayStrs = dayStr.split(",");
        int[] days = new int[dayStrs.length];
        for (int i = 0; i < dayStrs.length; i++) {
            days[i] = NumberUtils.toInt(dayStrs[i], 0);
        }
        if (this.getJobType() != null) {
            if (this.getJobType() == 3) {
                this.setDayOfWeeks(days);
            }
            if (this.getJobType() == 2) {
                this.setDayOfMonths(days);
            }
        }
    }
}
