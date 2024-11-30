package gui.panel;

import gui.listener.RecoverListener;
import util.ColorUtil;
import util.GUIUtil;

import javax.swing.*;

/**
 * 界面类 恢复页
 */
public class RecoverPanel extends WorkingPanel{
    static {
        // 类静态初始化块，用于设置应用程序的外观和感觉。
        GUIUtil.useLNF();
    }
    // RecoverPanel 的单例实例，确保整个应用程序中只有一个 RecoverPanel 实例。
    public static RecoverPanel instance = new RecoverPanel();

    //恢复按钮，用户点击此按钮时触发数据库的恢复操作
    private JButton bRecover = new JButton("恢复");

    //RecoverPanel 的构造函数，初始化面板并添加组件
    public RecoverPanel() {
        // 设置恢复按钮的颜色为蓝色。
        GUIUtil.setColor(ColorUtil.blueColor, bRecover);
        // 将恢复按钮添加到面板中。
        this.add(bRecover);
        // 为面板添加事件监听器。
        addListener();
    }

    public static void main(String[] args) {
        // 使用 GUIUtil 工具类的 showPanel 方法显示 RecoverPanel 的单例实例。
        GUIUtil.showPanel(RecoverPanel.instance);
    }

    //更新面板数据的方法，当前未实现任何逻辑
    @Override
    public void updateData() {
        // 留空，如果需要更新面板数据，可以在这里添加代码。
    }

    //为面板添加事件监听器的方法，创建一个 RecoverListener 实例，并将其设置为恢复按钮的动作监听器
    @Override
    public void addListener() {
        // 创建恢复事件监听器。
        RecoverListener listener = new RecoverListener();
        // 为恢复按钮添加动作监听器。
        bRecover.addActionListener(listener);
    }
}
