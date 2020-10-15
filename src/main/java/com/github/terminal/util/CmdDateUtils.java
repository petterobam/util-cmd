package com.github.terminal.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 日期工具类
 *
 * @author 欧阳洁
 * @date 2020/8/28 14:11
 */
public class CmdDateUtils {
    /**
     * 通过正则式获取查询截止时间
     *
     * @param parameter
     * @return
     */
    public static Date getBeforDateOfRegex(String parameter) {
        return getBeforDateOfRegex(null, parameter);
    }

    /**
     * 通过正则式获取查询截止时间
     *
     * @param parameter
     * @return
     */
    public static Date getBeforDateOfRegex(Date date, String parameter) {
        if (null == date) {
            date = new Date();
        }
        if (null == parameter) {
            return date;
        }
        // 纯数字默认天数变化
        if (NumberUtils.isNumber(parameter)) {
            return DateUtils.addDays(date, NumberUtils.toInt(parameter, 0));
        }
        // 正则式
        boolean isBefore = false;
        if (parameter.startsWith("-")) {
            isBefore = true;
        }
        // 几天几小时几分钟的数据筛选，负代表 before （±?d?h?m），例如：-4d5h3m、1h、-30m、87m 等
        String regex = "[-]{0,1}([0-9]+d){0,1}([0-9]+h){0,1}([0-9]+m){0,1}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(parameter);
        if (m.find()) {
            for (int i = 1; i <= 3; i++) {
                String groupDataStr = m.group(i);
                int num = 0;
                if (null == groupDataStr || groupDataStr.length() < 1) {
                    continue;
                } else {
                    num = NumberUtils.toInt(groupDataStr.substring(0, groupDataStr.length() - 1), 0);
                    if (isBefore) {
                        num = -num;
                    }
                }
                if (StringUtils.endsWithIgnoreCase(groupDataStr, "d")) {
                    date = DateUtils.addDays(date, num);
                } else if (StringUtils.endsWithIgnoreCase(groupDataStr, "h")) {
                    date = DateUtils.addHours(date, num);
                } else if (StringUtils.endsWithIgnoreCase(groupDataStr, "m")) {
                    date = DateUtils.addMinutes(date, num);
                }
            }
        }
        return date;
    }

    /**
     * 获取今天短时间字符串格式yyyy-MM-dd
     *
     * @return
     */
    public static String getFormatStr(Date date, String formatRegex) {
        if (null == formatRegex) {
            formatRegex = "yyyy-MM-dd";
        }
        SimpleDateFormat formatter = new SimpleDateFormat(formatRegex);
        return formatter.format(date);
    }

    /**
     * 获取今天短时间字符串格式yyyy-MM-dd
     *
     * @return
     */
    public static Date getDate(String dateStr, String formatRegex, Date _default) {
        if (null == formatRegex) {
            formatRegex = "yyyy-MM-dd";
        }
        SimpleDateFormat formatter = new SimpleDateFormat(formatRegex);
        try {
            return formatter.parse(dateStr);
        } catch (ParseException e) {
            return _default;
        }
    }
}
