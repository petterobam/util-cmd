package com.github.terminal.cmd;

import com.github.terminal.util.CmdTextTableUtils;

/**
 * 表格控制台命令
 *
 * @author 欧阳洁
 * @date 2020/10/29 17:28
 */
public class TextTableCmdUtils extends BaseCmdUtils {
    public static boolean excute(String action, String[] params) {
        if (!BaseCmdUtils.excute(action, params)) {
            TimeCmdUtils.help();
            return false;
        }
        System.out.println(" 工具类型：文字表格【-tt】");
        String[] tableTextArr;
        String spilt = ",";
        switch (action) {
            case "-spilt":
            case "-s":
                System.out.println(" 动作：自定义分隔符【-s】");
                if (params.length < 2) {
                    System.out.println(" 请传入动作相应分隔符和参数：-s [分隔符] [第一行文本，每列分隔符分隔] [第二行文本...] ... [第N行文本...]");
                    System.out.println(" PS:文本分隔符可以自定义，若用空格分隔需要用双引号括起来，相应每行的文本也需要用双引号括起来");
                    return false;
                }
                spilt = params[0];
                tableTextArr = new String[params.length - 1];
                for (int i = 1; i < params.length; i++) {
                    tableTextArr[i - 1] = params[i];
                }
                break;
            default:
                tableTextArr = new String[params.length + 1];
                tableTextArr[0] = action;
                for (int i = 0; i < params.length; i++) {
                    tableTextArr[i + 1] = params[i];
                }
                break;
        }
        System.out.println(" 分隔符：【" + spilt + "】");
        CmdTextTableUtils.printResult(tableTextArr, spilt);
        return true;
    }


    public static void help() {
        System.out.println("  [textTable] 文字表格工具！");
        System.out.println("   1. 工具类型参数: -textTable(tt)【文字表格】");
        System.out.println("   2. 动作类型参数：-spilt(s)【分隔符】，可忽略");
        System.out.println("   3. 表达式：-tt (-s [分隔符，不定义默认用英文逗号]) [第一行文本，每列分隔符分隔] [第二行文本...] ... [第N行文本...]");
    }

    public static boolean canDeal(String type) {
        return "-textTable".equals(type) || "-tt".equals(type);
    }
}
