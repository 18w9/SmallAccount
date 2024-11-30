package gui.panel;

import gui.listener.ToolBarListener;
import util.CenterPanel;
import util.GUIUtil;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel{
    // 静态初始化块，用于设置外观和感觉。
    static {
        GUIUtil.useLNF();
    }

    // MainPanel 的单例实例，确保整个应用程序中只有一个主面板实例。
    public static MainPanel instance = new MainPanel();
    //各个按钮
    private JToolBar tb = new JToolBar();
    public JButton bSpend = new JButton();
    public JButton bRecord = new JButton();
    public JButton bHistory = new JButton();
    public JButton bCategory = new JButton();
    public JButton bReport = new JButton();
    public JButton bConfig = new JButton();
    public JButton bBackup = new JButton();
    public JButton bRecover = new JButton();

    // 工作面板，用于显示主要内容
    public CenterPanel workingPanel;

    private MainPanel() {

        // 设置按钮的图标和提示文本
        GUIUtil.setImageIcon(bSpend, "home.png", "本月一览");
        GUIUtil.setImageIcon(bRecord, "record.png", "记一笔");
        GUIUtil.setImageIcon(bHistory, "history.png", "历史消费");
        GUIUtil.setImageIcon(bReport, "report.png", "月消费报表");
        GUIUtil.setImageIcon(bCategory, "category.png", "消费分类");
        GUIUtil.setImageIcon(bConfig, "config.png", "设置");
        GUIUtil.setImageIcon(bBackup, "backup.png", "备份");
        GUIUtil.setImageIcon(bRecover, "restore.png", "恢复");

        // 将按钮添加到工具栏
        tb.add(bSpend);
        tb.add(bRecord);
        tb.add(bHistory);
        tb.add(bReport);
        tb.add(bCategory);
        tb.add(bConfig);
        tb.add(bBackup);
        tb.add(bRecover);
        tb.setFloatable(false);// 设置工具栏不可浮动

        // 初始化工作面板，设置缩放比例为0.85
        workingPanel = new CenterPanel(0.85);

        // 设置布局管理器为边界布局
        this.setLayout(new BorderLayout());
        // 将工具栏添加到面板的北部
        this.add(tb, BorderLayout.NORTH);
        // 将工作面板添加到面板的中心
        this.add(workingPanel, BorderLayout.CENTER);

        // 添加按钮的事件监听器
        addListeners();
    }

    private void addListeners() {// 创建工具栏监听器实例
        ToolBarListener l = new ToolBarListener();
        // 为每个按钮添加动作监听器
        bSpend.addActionListener(l);
        bHistory.addActionListener(l);
        bBackup.addActionListener(l);
        bCategory.addActionListener(l);
        bConfig.addActionListener(l);
        bRecord.addActionListener(l);
        bRecover.addActionListener(l);
        bReport.addActionListener(l);
    }

}
