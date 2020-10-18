package com.github.terminal.test;

import com.github.terminal.MainApp;
import org.junit.Test;

/**
 * 主程序测试
 *
 * @author 欧阳洁
 * @date 2020/10/18 12:47
 */
public class TestMainApp {
    @Test
    public void testHelp() {
        MainApp.main(new String[]{"-h"});
    }

    @Test
    public void testTime() {
        MainApp.main(new String[]{"-t", "-f", "now"});
    }

    @Test
    public void testCron() {
        MainApp.main(new String[]{"-c", "-e", "1"});
        MainApp.main(new String[]{"-c", "-e", "4", "0", "20"});
        MainApp.main(new String[]{"-c", "-e", "3", "2,4", "20"});
        MainApp.main(new String[]{"-c", "-d", "0 0 0/1 * * ?"});
    }
}
