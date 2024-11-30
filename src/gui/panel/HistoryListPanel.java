package gui.panel;

import entity.Record;
import gui.listener.HistoryListListener;
import gui.model.RecordTableModel;
import service.RecordService;
import util.ColorUtil;
import util.GUIUtil;

import javax.swing.*;
import java.awt.*;

/**
 * HistoryListPanel 是 HistoryPanel 中的历史记录面板
 * @see HistoryPanel
 *
 * @author xenv
 */
public class HistoryListPanel extends WorkingPanel {
    static {
        // 类静态初始化块，用于设置应用程序的外观和感觉。
        GUIUtil.useLNF();
    }

    // HistoryListPanel 的单例实例，确保整个应用程序中只有一个 HistoryListPanel 实例。
    public static HistoryListPanel instance = new HistoryListPanel();

    // 记录操作的按钮。
    public JButton bAdd = new JButton("新增");
    public JButton bEdit = new JButton("编辑");
    public JButton bDelete = new JButton("删除");

    //记录数据模型，用于向表格提供数据
    private RecordTableModel rtm = new RecordTableModel();
    // 记录信息的表格，使用 RecordTableModel 作为数据模型。
    private JTable t = new JTable(rtm);

    //HistoryListPanel 的私有构造函数，初始化面板并添加组件。
    private HistoryListPanel() {
        // 设置按钮颜色为蓝色。
        GUIUtil.setColor(ColorUtil.blueColor, bAdd, bEdit, bDelete);
        // 创建一个滚动面板，用于包含记录信息表格。
        JScrollPane sp = new JScrollPane(t);
        // 创建一个面板，用于包含按钮。
        JPanel pSubmit = new JPanel();
        // 将按钮添加到面板。
        pSubmit.add(bAdd);
        pSubmit.add(bEdit);
        pSubmit.add(bDelete);

        // 设置面板的布局管理器为 BorderLayout。
        this.setLayout(new BorderLayout());
        // 将滚动面板添加到面板的中心区域。
        this.add(sp, BorderLayout.CENTER);
        // 将按钮面板添加到面板的南部区域。
        this.add(pSubmit, BorderLayout.SOUTH);

        // 添加事件监听器到按钮。
        this.addListener();
    }

    public static void main(String[] args) {
        // 使用 GUIUtil 工具类的 showPanel 方法显示 HistoryListPanel 的单例实例。
        GUIUtil.showPanel(HistoryListPanel.instance);
    }

    //检查是否有记录被选中
    public boolean checkSelected() {
        // 检查表格是否有选中的行。
        return t.getSelectedRow() >= 0;
    }

    //获取选中的记录
    public Record getSelectedRecord() {
        // 获取选中行的索引。
        int index = t.getSelectedRow();
        // 返回记录列表中对应的记录对象。
        return rtm.rs.get(index > 0 ? index : 0);
    }

    //更新面板上显示的数据
    @Override
    public void updateData() {
        // 从服务层获取当前月份的记录数据，并更新数据模型。
        rtm.rs = new RecordService().listMonth(MonthPickerPanel.instance.date);
        // 更新表格的显示。
        t.updateUI();
    }

    //为按钮添加事件监听器
    @Override
    public void addListener() {
        // 创建历史记录列表事件监听器。
        HistoryListListener l = new HistoryListListener();
        // 为按钮添加动作监听器。
        bDelete.addActionListener(l);
        bEdit.addActionListener(l);
        bAdd.addActionListener(l);
    }
}
