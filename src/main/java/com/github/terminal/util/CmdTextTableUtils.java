package com.github.terminal.util;

/**
 * 控制台输出文字表格工具
 *
 * @author 欧阳洁
 * @date 2020/10/29 17:22
 */
public class CmdTextTableUtils {
    /**
     * 文字表格打印
     * @param tableTextArr
     */
    public static void printResult(String[] tableTextArr, String splitStr) {
        if (null == splitStr) {
            splitStr = ",";
        }
        String[] tempA = tableTextArr[0].split(splitStr);
        int maxLen = tempA.length;
        for (int i = 1; i < tableTextArr.length; i++) {
            tempA = tableTextArr[i].split(splitStr);
            if (maxLen < tempA.length) {
                maxLen = tempA.length;
            }
        }

        String[][] valueA = new String[tableTextArr.length][maxLen];
        for (int i = 0; i < valueA.length; i++) {
            for (int j = 0; j < valueA[0].length; j++) {
                valueA[i][j] = "";
            }
        }

        for (int i = 0; i < tableTextArr.length; i++) {
            tempA = tableTextArr[i].split(splitStr);
            for (int j = 0; j < tempA.length; j++) {
                valueA[i][j] = tempA[j];
            }
        }

        int[] maxJ = new int[maxLen];
        for (int j = 0; j < maxLen; j++) {
            for (int i = 0; i < tableTextArr.length; i++) {
                if (valueA[i][j].length() > maxJ[j]) {
                    maxJ[j] = valueA[i][j].length();
                }
            }
        }

        StringBuilder opera = new StringBuilder(" +");
        for (int j = 0; j < maxJ.length; j++) {
            for (int k = 0; k < maxJ[j]; k++) {
                opera.append('-');
            }
            opera.append('-').append('+');
        }

        for (int i = 0; i < valueA.length; i++) {
            System.out.println(opera);
            System.out.print(" |");
            for (int j = 0; j < valueA[0].length; j++) {
                int len = maxJ[j] - valueA[i][j].length();
                String format;
                if (len == 0) {
                    format = "" + "%s";
                } else {
                    format = "%" + len + "s";
                }
                System.out.print(valueA[i][j]);
                System.out.printf(format, "");
                System.out.print(" |");
            }
            System.out.println();
        }
        System.out.println(opera);
        return;
    }
}
