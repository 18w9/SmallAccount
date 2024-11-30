package startup;

import gui.frame.MainFrame;
import gui.panel.MainPanel;
import gui.panel.SpendPanel;
import util.GUIUtil;

import javax.swing.*;

/**
 * 启动类 Bootstrap
 * main为本程序的总入口
 * 会加载一个 MainFrame 程序窗体 和 一个 MainPanel 程序 主面板 ，并且初始化 MainPanel 中的 workingPanel
 * workingPanel有方法 .show(WorkingPanel p) 可以居中显示子面板 并 更新数据
 * 使用swing的阻塞机制，优先加载整个启动窗口
 *
 * @see MainFrame
 * @see MainPanel
 */
public class Bootstrap {
    public static void main(String[] args) throws Exception {
        // 在类加载时设置外观和感觉
        GUIUtil.useLNF();

        // 使用SwingUtilities.invokeAndWait方法确保在事件分发线程（EDT）上执行以下代码
        // 这对于更新Swing组件是必要的，以确保线程安全。
        SwingUtilities.invokeAndWait(new Runnable() {
            @Override
            public void run() {
                // 设置主框架的可见性为true，使其在屏幕上显示。
                MainFrame.instance.setVisible(true);

                // 居中显示子面板，并更新数据。
                // 这里调用MainPanel的workingPanel的show方法，
                // 将SpendPanel作为参数传入，显示在workingPanel中。
                MainPanel.instance.workingPanel.show(SpendPanel.instance);
            }
        });
    }
}

