package gui.frame;

import gui.panel.MainPanel;
import util.GUIUtil;

import javax.swing.*;

/**
 * 程序主窗体
 * 设置了程序窗体的长宽标题和退出操作等
 */

public class MainFrame extends JFrame {
    //类静态初始化块，用于设置应用程序的外观和感觉。
    static {
        // 使用GUIUtil工具类的useLNF方法设置外观和感觉。
        GUIUtil.useLNF();
    }

    //主框架的单例实例
    public static MainFrame instance = new MainFrame();

    private MainFrame() {
        // 设置窗口的初始大小。
        this.setSize(513, 450);
        // 设置窗口标题。
        this.setTitle("小小记账本");
        // 设置窗口的内容面板为MainPanel的单例实例。
        this.setContentPane(MainPanel.instance);
        // 设置窗口在屏幕中居中显示。
        this.setLocationRelativeTo(null);
        // 设置窗口不允许调整大小。
        this.setResizable(false);
        // 设置窗口的默认关闭操作为退出应用程序。
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        // 使主框架实例可见，显示窗口。
        instance.setVisible(true);
    }
}