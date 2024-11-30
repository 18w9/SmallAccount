package gui.listener;

import gui.panel.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * MainPanel 的监听器，监听 Toolbar 的按钮操作并切换面板
 * workingPanel有方法 .show(WorkingPanel p) 可以居中显示子面板 并 更新数据
 * @see MainPanel
 */
public class ToolBarListener implements ActionListener {
    /**
     * 处理动作事件的方法。
     * 当工具栏中的任意按钮被点击时，此方法被调用。
     *
     * @param e 动作事件对象，包含触发事件的源组件信息。
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // 获取主面板的单例实例，用于访问和控制主面板。
        MainPanel p = MainPanel.instance;

        // 获取触发事件的按钮对象。
        JButton b = (JButton) e.getSource();

        // 如果点击的是备份按钮，则在主面板的工作区域显示备份面板。
        if (b == p.bBackup) {
            p.workingPanel.show(BackupPanel.instance);
        }
        // 如果点击的是分类按钮，则在主面板的工作区域显示分类面板。
        if (b == p.bCategory) {
            p.workingPanel.show(CategoryPanel.instance);
        }
        // 如果点击的是恢复按钮，则在主面板的工作区域显示恢复面板。
        if (b == p.bRecover) {
            p.workingPanel.show(RecoverPanel.instance);
        }
        // 如果点击的是配置按钮，则在主面板的工作区域显示配置面板。
        if (b == p.bConfig) {
            p.workingPanel.show(ConfigPanel.instance);
        }
        // 如果点击的是记录按钮，则在主面板的工作区域显示记录面板。
        if (b == p.bRecord) {
            p.workingPanel.show(RecordPanel.instance);
        }
        // 如果点击的是报表按钮，则在主面板的工作区域显示报表面板。
        if (b == p.bReport) {
            p.workingPanel.show(ReportPanel.instance);
        }
        // 如果点击的是支出按钮，则在主面板的工作区域显示支出面板。
        if (b == p.bSpend) {
            p.workingPanel.show(SpendPanel.instance);
        }
        // 如果点击的是历史记录按钮，则在主面板的工作区域显示历史记录面板。
        if (b == p.bHistory) {
            p.workingPanel.show(HistoryPanel.instance);
        }
    }
}
