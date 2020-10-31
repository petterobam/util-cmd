package com.github.terminal;

import com.github.terminal.cmd.BaseCmdUtils;
import com.github.terminal.util.CmdClassUtils;

import java.util.List;

/**
 * 入口类
 *
 * @author 欧阳洁
 * @date 2020/9/23 14:02
 */
public class MainApp {
    private static List<Class<? extends BaseCmdUtils>> cmdUtilList =  CmdClassUtils.getAllSubClass(BaseCmdUtils.class);
    /**
     * 命令执行入口
     *
     * @param args
     */
    public static void main(String[] args) {
        // args = new String[] {  };
        // args = new String[] { "-h" };
        // args = new String[] { "-t","-f","now" };
        if (null == args || args.length < 1) {
            System.out.println(" 请传入工具类型参数：-help | -h（帮助）！");
            return;
        }
        String type = args[0];
        String action = args.length > 1 ? args[1] : null;
        String[] params = reGetParams(args);

        // excute cmd
        MainApp.excute(type, action, params);
    }

    /**
     * 重新获取执行参数
     *
     * @param args
     * @return
     */
    private static String[] reGetParams(String[] args) {
        int paramSize = args.length;
        paramSize--;
        if (args.length > 1) {
            paramSize--;
        }
        String[] params = new String[paramSize];
        int ind = 0;
        for (int i = args.length - paramSize; i < args.length; i++) {
            params[ind++] = args[i];
        }
        return params;
    }

    /**
     * 通用执行函数
     *
     * @param type
     * @param action
     * @param params
     */
    private static void excute(String type, String action, String[] params) {
        try {
            // help go excute
            if ("-help".equals(type) || "-h".equals(type)) {
                MainApp.help();
                return;
            }
            // util go excute
            for (Class<? extends BaseCmdUtils> c : MainApp.cmdUtilList) {
                if (Boolean.TRUE.equals(CmdClassUtils.invokeStaticMethod(c, "canDeal", type))) {
                    CmdClassUtils.invokeStaticMethod(c, "excute", action, params);
                    return;
                }
            }
            // other go excute
            System.out.println(" 未知工具类型：" + type);
            System.out.println(" 请运行：util -h 查看帮助");
        } catch (Exception e) {
            // exception go excute
            System.out.println("----------------------------------!! 异常发生 !!--------------------------------");
            e.printStackTrace();
            System.out.println("--------------------------------------------------------------------------------");
        }
    }

    private static void help() {
        System.out.println(" Terminal命令行工具 V0.0.1");
        System.out.println("------------------------------------------------------------------------------");
        System.out.println(" util [工具类型] [动作类型] [参数...（多个空格分隔，含空格的参数用 \" \" 括起来）]");
        try {
            for (Class<? extends BaseCmdUtils> c : MainApp.cmdUtilList) {
                CmdClassUtils.invokeStaticMethod(c, "help");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
