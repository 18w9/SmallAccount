package gui.panel;

import entity.Category;
import gui.listener.RecordListener;
import gui.model.CategoryComboBoxModel;
import org.jdesktop.swingx.JXDatePicker;
import service.CategoryService;
import util.ColorUtil;
import util.GUIUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

/**
 * 界面类 RecordPanel 记一笔页面
 *
 */
public class RecordPanel extends WorkingPanel{
    static {
        // 类静态初始化块，用于设置应用程序的外观和感觉。
        GUIUtil.useLNF();
    }
    // RecordPanel 的单例实例，确保整个应用程序中只有一个 RecordPanel 实例。
    public static RecordPanel instance = new RecordPanel();
    // 用于标识当前操作是添加还是编辑的变量。
    // 如果 updateId > 0，则为编辑模式；如果 < 0，则为添加模式。
    public int updateId = -1;

    // 标签和输入字段，用于记录的花费、分类、备注和日期。
    private JLabel lSpend = new JLabel("花费(￥)");
    private JLabel lCategory = new JLabel("分类");
    private JLabel lComment = new JLabel("备注");
    private JLabel lDate = new JLabel("日期");
    public JTextField tfSpend = new JTextField("0");
    public CategoryComboBoxModel cbModel = new CategoryComboBoxModel();// 创建一个自定义的分类选择器模型。
    public JComboBox<Category> cbCategory = new JComboBox<>(cbModel);
    public JTextField tfComment = new JTextField();
    public JXDatePicker datepick = new JXDatePicker(new Date());

    // 保存按钮，用于提交记录。
    private JButton bSubmit = new JButton("保存");

    //RecordPanel 的构造函数，初始化面板并添加组件
    public RecordPanel() {
        // 设置标签颜色为灰色，设置按钮颜色为蓝色。
        GUIUtil.setColor(ColorUtil.grayColor, lSpend, lCategory, lComment, lDate);
        GUIUtil.setColor(ColorUtil.blueColor, bSubmit);

        // 创建输入字段和按钮的面板。
        JPanel pInput = new JPanel();
        JPanel pSubmit = new JPanel();
        int gap = 40;
        pInput.setLayout(new GridLayout(4, 2, gap, gap)); // 设置输入面板的布局为 GridLayout。

        // 将标签和输入字段添加到输入面板。
        pInput.add(lSpend);
        pInput.add(tfSpend);
        pInput.add(lCategory);
        pInput.add(cbCategory);
        pInput.add(lComment);
        pInput.add(tfComment);
        pInput.add(lDate);
        pInput.add(datepick);

        // 将保存按钮添加到提交面板。
        pSubmit.add(bSubmit);

        // 设置面板的布局管理器为 BorderLayout，并添加输入面板和提交面板。
        this.setLayout(new BorderLayout());
        this.add(pInput, BorderLayout.NORTH);
        this.add(pSubmit, BorderLayout.CENTER);

        // 添加事件监听器到按钮。
        addListener();
    }

    //更新面板上显示的数据
    @Override
    public void updateData() {
        // 从服务层获取分类数据，并更新分类选择器。
        cbModel.cs = new CategoryService().list();
        cbCategory.updateUI();// 更新
        // 清空输入字段，并设置日期选择器为当前日期。
        tfSpend.setText("");
        tfComment.setText("");
        datepick.setDate(new Date());
        // 获取输入字段的焦点。
        tfSpend.grabFocus();
    }

    //获取选中的分类
    public Category getSelectedCategory() {
        // 返回分类选择器中选中的分类。
        return (Category) cbCategory.getSelectedItem();
    }

    //为按钮添加事件监听器
    @Override
    public void addListener() {
        // 为保存按钮添加 RecordListener 监听器。
        bSubmit.addActionListener(new RecordListener());
    }
}
