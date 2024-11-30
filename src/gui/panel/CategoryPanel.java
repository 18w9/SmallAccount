package gui.panel;

import entity.Category;
import gui.listener.CategoryListener;
import gui.model.CategoryTableModel;
import service.CategoryService;
import util.ColorUtil;
import util.GUIUtil;

import javax.swing.*;
import java.awt.*;

public class CategoryPanel extends WorkingPanel{
    static {
        // 类静态初始化块，用于设置应用程序的外观和感觉。
        GUIUtil.useLNF();
    }

    // CategoryPanel 的单例实例，确保整个应用程序中只有一个 CategoryPanel 实例。
    public static CategoryPanel instance = new CategoryPanel();

    // 分类操作的按钮。
    public JButton bAdd = new JButton("新增");
    public JButton bEdit = new JButton("编辑");
    public JButton bDelete = new JButton("删除");

    // 分类数据模型，用于向表格提供数据。
    private CategoryTableModel ctm = new CategoryTableModel();
    // 分类信息的表格，使用 CategoryTableModel 作为数据模型。
    private JTable t = new JTable(ctm);

    //CategoryPanel 的构造函数，初始化面板并添加组件。
    public CategoryPanel() {
        // 设置按钮颜色为蓝色。
        GUIUtil.setColor(ColorUtil.blueColor, bAdd, bEdit, bDelete);
        // 创建一个滚动面板，用于包含分类信息表格。
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
        // 使用 GUIUtil 工具类的 showPanel 方法显示 CategoryPanel 的单例实例。
        GUIUtil.showPanel(CategoryPanel.instance);
    }

    //检查是否有分类被选中。
    public boolean checkSelected() {
        // 检查表格是否有选中的行。
        return t.getSelectedRow() >= 0;
    }

    //获取选中的分类。
    public Category getSelectedCategory() {
        // 获取选中行的索引。
        int index = t.getSelectedRow();
        // 返回分类列表中对应的分类对象。
        return ctm.cs.get(index > 0 ? index : 0);
    }

    //更新面板上显示的数据。
    public void updateData() {
        // 从服务层获取分类数据，并更新数据模型。
        ctm.cs = new CategoryService().list();
        // 更新表格的显示。
        t.updateUI();
        // 根据分类数据的大小启用或禁用编辑和删除按钮。
        if (0 == ctm.cs.size()) {
            bEdit.setEnabled(false);
            bDelete.setEnabled(false);
        } else {
            bEdit.setEnabled(true);
            bDelete.setEnabled(true);
        }
    }

    //为按钮添加事件监听器。
    public void addListener() {
        // 创建分类事件监听器。
        CategoryListener categoryListener = new CategoryListener();
        // 为按钮添加动作监听器。
        bDelete.addActionListener(categoryListener);
        bEdit.addActionListener(categoryListener);
        bAdd.addActionListener(categoryListener);
    }
}
