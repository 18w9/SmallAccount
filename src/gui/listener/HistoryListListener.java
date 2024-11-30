package gui.listener;

import entity.Category;
import entity.Record;
import gui.panel.HistoryListPanel;
import gui.panel.MainPanel;
import gui.panel.MonthPickerPanel;
import gui.panel.RecordPanel;
import service.RecordService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class HistoryListListener  implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        // 创建记录服务实例，用于执行记录相关的数据库操作。
        RecordService service = new RecordService();
        // 获取历史记录列表面板的单例实例，用于访问面板上的组件和方法。
        HistoryListPanel p = HistoryListPanel.instance;
        // 获取触发事件的按钮对象。
        JButton b = (JButton) e.getSource();

        // 如果点击的是新增按钮，则打开记录面板以新增记录。
        if (p.bAdd == b) {
            // 将主面板的工作区域显示为记录面板。
            MainPanel.instance.workingPanel.show(RecordPanel.instance);
            // 设置记录面板的日期选择器为当前月份。
            RecordPanel.instance.datepick.setDate(MonthPickerPanel.instance.date);
        }
        // 如果点击的是编辑按钮，则打开记录面板以编辑选中的记录。
        if (p.bEdit == b) {
            // 检查是否有记录被选中。
            if (!p.checkSelected()) {
                JOptionPane.showMessageDialog(p, "请先选择一行");
            } else {
                // 获取选中的记录对象。
                Record r = p.getSelectedRecord();
                // 设置记录面板的更新ID，以便在提交时使用修改记录而不是新增记录。
                RecordPanel.instance.updateId = r.getId();
                // 将主面板的工作区域显示为记录面板。
                MainPanel.instance.workingPanel.show(RecordPanel.instance);
                // 将选中记录的数据设置到记录面板的输入字段中。
                RecordPanel.instance.tfSpend.setText(String.valueOf(r.getSpend()));
                RecordPanel.instance.tfComment.setText(r.getComment());
                // 根据记录的分类ID设置记录面板的分类选择器。
                RecordPanel.instance.cbCategory.setSelectedIndex(getModelId(r.getCid()));
                // 设置记录面板的日期选择器为记录的日期。
                RecordPanel.instance.datepick.setDate(r.getDate());
            }
        }
        // 如果点击的是删除按钮，则删除选中的记录。
        if (p.bDelete == b) {
            // 检查是否有记录被选中。
            if (!p.checkSelected()) {
                JOptionPane.showMessageDialog(p, "请先选择一行");
                return;
            }
            // 弹出确认对话框，让用户确认是否删除记录。
            if (JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(p, "确实删除？")) {
                return;
            }
            // 删除选中记录的ID对应的记录。
            service.delete(p.getSelectedRecord().getId());
            // 更新历史记录列表面板的数据。
            p.updateData();
        }
    }

    // 获取模型里的id而不是数据库的id
    private int getModelId(int cid) {
        List<Category> categoryComBoxList = RecordPanel.instance.cbModel.cs;
        for (int i = 0; i < categoryComBoxList.size(); i++) {
            if (categoryComBoxList.get(i).getId() == cid) {
                return i;
            }
        }
        return 0; //若分类已经删除则返回第0个分类
    }
}
