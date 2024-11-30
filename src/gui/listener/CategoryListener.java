package gui.listener;

import entity.Category;
import gui.panel.CategoryPanel;
import service.CategoryService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CategoryListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        // 创建分类服务实例，用于执行分类相关的数据库操作。
        CategoryService categoryService = new CategoryService();
        // 获取分类面板的单例实例，用于访问面板上的组件和方法。
        CategoryPanel p = CategoryPanel.instance;

        // 获取触发事件的按钮对象。
        JButton b = (JButton) e.getSource();
        // 如果点击的是新增按钮，则执行新增分类操作。
        if (p.bAdd == b) {
            // 弹出输入对话框，让用户输入新分类的名称。
            String name = JOptionPane.showInputDialog(null);
            // 如果用户取消输入或输入为空，则不执行操作。
            if (null == name || 0 == name.length()) {
                JOptionPane.showMessageDialog(p, "名称不为空");
                return;
            }
            // 调用分类服务添加新分类。
            categoryService.add(name);
        }
        // 如果点击的是编辑按钮，则执行编辑分类操作。
        if (p.bEdit == b) {
            // 检查是否有分类被选中。
            if (!p.checkSelected()) {
                JOptionPane.showMessageDialog(p, "请先选择一行");
                return;
            }
            // 获取选中的分类对象。
            Category c = p.getSelectedCategory();
            // 弹出输入对话框，让用户修改分类的名称。
            String name = JOptionPane.showInputDialog("修改名称", c.getName());
            // 如果用户取消输入或输入为空，则不执行操作。
            if (null == name || 0 == name.length()) {
                JOptionPane.showMessageDialog(p, "名称不为空");
                return;
            }
            // 调用分类服务更新分类名称。
            categoryService.update(c.getId(), name);
        }
        // 如果点击的是删除按钮，则执行删除分类操作。
        if (p.bDelete == b) {
            // 检查是否有分类被选中。
            if (!p.checkSelected()) {
                JOptionPane.showMessageDialog(p, "请先选择一行");
                return;
            }
            // 获取选中的分类对象。
            Category c = p.getSelectedCategory();
            // 如果分类下还有数据，则不能删除。
            if (0 != c.getRecordNumber()) {
                JOptionPane.showMessageDialog(p, "分类下还有数据");
                return;
            }
            // 弹出确认对话框，让用户确认是否删除分类。
            if (JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(p, "确实删除？")) {
                return;
            }
            // 调用分类服务删除分类。
            categoryService.delete(c.getId());
        }
        // 更新分类面板上的数据。
        p.updateData();
    }
}
