package gui.panel;

import util.GUIUtil;

import java.awt.*;

public class HistoryPanel extends WorkingPanel{
    static {
        // 类静态初始化块，用于设置应用程序的外观和感觉。
        GUIUtil.useLNF();
    }

    // HistoryPanel 的单例实例，确保整个应用程序中只有一个 HistoryPanel 实例。
    public static HistoryPanel instance = new HistoryPanel();

    //HistoryPanel 的私有构造函数，初始化面板并添加组件
    private HistoryPanel() {
        // 设置面板的布局管理器为 BorderLayout，以便在北部和中心区域放置子面板。
        this.setLayout(new BorderLayout());
        // 将月份选择器面板添加到面板的北部区域。
        this.add(MonthPickerPanel.instance, BorderLayout.NORTH);
        // 将历史记录列表面板添加到面板的中心区域。
        this.add(HistoryListPanel.instance, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        // 使用 GUIUtil 工具类的 showPanel 方法显示 HistoryPanel 的单例实例。
        GUIUtil.showPanel(HistoryPanel.instance);
    }

    //更新面板上显示的数据
    @Override
    public void updateData() {
        // 更新历史记录列表面板的数据。
        HistoryListPanel.instance.updateData();
    }

    //为面板添加事件监听器的方法
    @Override
    public void addListener() {
        // 留空，如果需要添加事件监听器，可以在这里实现。
    }
}
