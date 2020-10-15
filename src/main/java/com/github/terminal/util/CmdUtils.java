package com.github.terminal.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * cmd 工具
 *
 * @author 欧阳洁
 * @date 2020/9/30 17:00
 */
public class CmdUtils {
    private static final String osName = System.getProperty("os.name").toLowerCase();
    /**
     * 执行cmd命令
     *
     * @param cmd
     */
    public static String cmdExec(String cmd) {
        StringBuffer cmdConnect = new StringBuffer(cmd).append("\n");
        Runtime rt = Runtime.getRuntime();
        InputStream isNormal = null;
        InputStream isError = null;
        Process process = null;
        ByteArrayOutputStream baos = null;
        try {
            process = rt.exec(cmdConnect.toString());
            baos = new ByteArrayOutputStream();
            int i;
            isNormal = process.getInputStream();
            if (null != isNormal) {
                while ((i = isNormal.read()) != -1) {
                    baos.write(i);
                }
            }
            isError = process.getErrorStream();
            if (null != isError) {
                while ((i = isError.read()) != -1) {
                    baos.write(i);
                }
            }
            String str = baos.toString();
            // System.out.println("执行cmd命令[" + cmd + "]==> " + str);
            return str;
        } catch (IOException e) {
            e.printStackTrace();
            return "Exception";
        } finally {
            try {
                if (null != isNormal) {
                    isNormal.close();
                }
                if (null != isError) {
                    isError.close();
                }
                if (null != baos) {
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 是否为windows系统
     *
     * @return
     */
    public static boolean isWindows() {
        return osName.indexOf("windows") >= 0;
    }
}
