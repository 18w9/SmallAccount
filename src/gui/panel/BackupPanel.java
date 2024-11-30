package gui.panel;

import gui.listener.BackupListener;
import util.ColorUtil;
import util.GUIUtil;

import javax.swing.*;

/**
 * 备份页
 */
public class BackupPanel extends WorkingPanel{
    //类静态初始化块，用于设置应用程序的外观和感觉。
    static {
        GUIUtil.useLNF();
    }

    //BackupPanel 的单例实例，确保整个应用程序中只有一个 BackupPanel 实例。
    public static BackupPanel instance = new BackupPanel();

    //备份按钮，用户点击此按钮将触发备份操作。
    private JButton bBackup = new JButton("备份");

    //BackupPanel 的构造函数，初始化面板并添加备份按钮。
    public BackupPanel() {
        // 设置备份按钮的颜色为蓝色。
        GUIUtil.setColor(ColorUtil.blueColor, bBackup);
        // 将备份按钮添加到面板中。
        this.add(bBackup);
        // 添加事件监听器到备份按钮。
        addListener();
    }

    public static void main(String[] args) {
        // 使用 GUIUtil 工具类的 showPanel 方法显示 BackupPanel 的单例实例。
        GUIUtil.showPanel(BackupPanel.instance);
    }

    //更新面板数据的方法，当前未实现任何逻辑。
    @Override
    public void updateData() {
        // 留空，如果需要更新面板数据，可以在这里添加代码。
    }

    //这个方法创建一个 BackupListener 实例，并将其设置为备份按钮的动作监听器。
    @Override
    public void addListener() {
        // 创建 BackupListener 实例，用于处理备份按钮的点击事件。
        BackupListener listener = new BackupListener();
        // 将 BackupListener 设置为备份按钮的动作监听器。
        bBackup.addActionListener(listener);
    }
}
