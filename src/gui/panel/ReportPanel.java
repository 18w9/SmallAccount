package gui.panel;

import service.ReportService;
import util.ChartUtil;
import util.GUIUtil;

import javax.swing.*;
import java.awt.*;

public class ReportPanel extends WorkingPanel{
    static {
        // 类静态初始化块，用于设置应用程序的外观和感觉。
        GUIUtil.useLNF();
    }
    // ReportPanel 的单例实例，确保整个应用程序中只有一个 ReportPanel 实例。
    public static ReportPanel instance = new ReportPanel();

    //用于显示图表的标签
    public JLabel l = new JLabel();

    //ReportPanel 的构造函数，初始化面板并添加组件。
    public ReportPanel() {
        // 将标签 l 添加到面板中，用于显示图表。
        this.add(l);
    }
    public static void main(String[] args) {
        // 使用 GUIUtil 工具类的 showPanel 方法显示 ReportPanel 的单例实例。
        GUIUtil.showPanel(ReportPanel.instance);
    }

    //更新面板上显示的数据，调用 ChartUtil 的 getImage 方法生成当前月份的消费图表，并更新标签 l 的图标
    @Override
    public void updateData() {
        // 调用 ReportService 的 listThisMonthRecords 方法获取当前月份的记录数据。
        Image i = ChartUtil.getImage(new ReportService().listThisMonthRecords(), 400, 260);
        // 创建一个新的 ImageIcon 对象，用于显示图表。
        ImageIcon icon = new ImageIcon(i);
        // 设置标签 l 的图标为生成的图标。
        l.setIcon(icon);
    }

    //为面板添加事件监听器的方法，由于 ReportPanel 可能不需要额外的事件监听器，此方法当前为空
    @Override
    public void addListener() {
        // 留空，如果需要添加事件监听器，可以在这里实现。
    }
}
