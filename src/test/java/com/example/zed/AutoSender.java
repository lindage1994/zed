package com.example.zed;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;

/**
 * @Author: zed
 * @Date: 2019/8/3 17:54
 * @Description:
 */
public class AutoSender {
    public static void main(String[] args) throws AWTException {
        String[] sentences = {"@Jessie","Jessie姐姐去吗？", "不去的话我一会儿再来问"};
        Robot robot = new Robot();// 创建Robot对象
        robot.delay(3000);// 延迟三秒，预留出选中对话框的时间，括号内的单位为毫秒
        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        for (int j = 0; j < 20; j++) {//循环次数
            int time = j % sentences.length;
            Transferable tText = new StringSelection(sentences[time]);
            clip.setContents(tText, null);
            // 以下两行按下了ctrl+v，完成粘贴功能
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);

            robot.keyRelease(KeyEvent.VK_CONTROL);// 释放ctrl按键
            robot.delay(800);// 延迟一下
            robot.keyPress(KeyEvent.VK_ENTER);// 回车
        }
    }
}
