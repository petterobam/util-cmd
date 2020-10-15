package com.github.terminal;

import com.github.terminal.cmd.Base64CmdUtils;
import com.github.terminal.cmd.TimeCmdUtils;
import com.github.terminal.cmd.UrlCmdUtils;

/**
 * 入口类
 *
 * @author 欧阳洁
 * @date 2020/9/23 14:02
 */
public class MainApp {

    public static void main(String[] args) {
        int paramSize = args.length;
        if(null == args || args.length < 1){
            System.out.println(" 请传入工具类型参数：-help | -h（帮助）！");
            return;
        }
        String type = args[0];
        paramSize--;
        String action = null;
        if (args.length > 1) {
            action = args[1];
            paramSize--;
        }

        String[] params = new String[paramSize];
        int ind = 0;
        for (int i = args.length - paramSize; i < args.length; i++) {
            params[ind++] = args[i];
        }

        switch (type) {
            case "-time":
            case "-t":
                TimeCmdUtils.excute(action, params);
                break;
            case "-url":
            case "-u":
                UrlCmdUtils.excute(action, params);
                break;
            case "-base64":
            case "-b64":
                Base64CmdUtils.excute(action, params);
                break;
            case "-help":
            case "-h":
                MainApp.help();
                TimeCmdUtils.help();
                UrlCmdUtils.help();
                Base64CmdUtils.help();
                break;
            default:
                System.out.println(" 未知工具类型：" + type);
                System.out.println(" 请运行：util -h 查看帮助");
        }
    }

    private static void help() {
        System.out.println(" Terminal命令行工具 V0.0.1");
        System.out.println("------------------------------------------------------------------------------");
        System.out.println(" util [工具类型] [动作类型] [参数...（多个空格分隔，含空格的参数用 \" \" 括起来）]");
    }
}
