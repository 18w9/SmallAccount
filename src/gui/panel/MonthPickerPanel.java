package gui.panel;

import gui.listener.MonthPickerListener;
import util.DateUtil;
import util.GUIUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

/**
 * MonthPickerPanel 是 HistoryPanel 中的月份选择器
 * 外部通过调用 MonthPickerPanel.instance.date 来获取选择器的时间
 * 本类写死了起始年份并保证了一定的年份使用的扩展性
 *
 * @author xenv
 * @see HistoryPanel
 */

public class MonthPickerPanel extends WorkingPanel{
    static {
        // 类静态初始化块，用于设置应用程序的外观和感觉。
        GUIUtil.useLNF();
    }
    // MonthPickerPanel 的单例实例，确保整个应用程序中只有一个 MonthPickerPanel 实例。
    public static MonthPickerPanel instance = new MonthPickerPanel();

    //起始时间，写死为2017
    private final int startYear = 2017;
    //当前面板实例的时间，初始化为当前月的第一天，
    public Date date = DateUtil.monthBegin();
    // 月份选择器，使用 JComboBox 组件。
    public JComboBox<Integer> cbMonth = new JComboBox<>(makeMonths());
    // 年份选择器，使用 JComboBox 组件。
    public JComboBox<Integer> cbYear = new JComboBox<>(makeYears());
    // 查询按钮，用于提交用户选择的年月。
    private JButton bSubmit = new JButton("查询");
    //MonthPickerPanel 的私有构造函数，初始化面板并添加组件
    private MonthPickerPanel() {
        this.setLayout(new GridLayout(1, 3, 8, 8)); // 设置面板的布局管理器为 GridLayout，一行三列。
        // 调整到当前月
        cbYear.setSelectedIndex(DateUtil.thisYear() - startYear); // 设置年份选择器的选中项为当前年份。
        cbMonth.setSelectedIndex(DateUtil.thisMonth() - 1); // 设置月份选择器的选中项为当前月份（从0开始）。
        this.add(cbYear); // 将年份选择器添加到面板。
        this.add(cbMonth); // 将月份选择器添加到面板。
        this.add(bSubmit); // 将查询按钮添加到面板。
        addListener(); // 添加事件监听器。
    }

    //创建年份选择器的数据数组
    private Integer[] makeYears() {
        int thisYear = DateUtil.thisYear(); // 获取当前年份。
        Integer[] result = new Integer[thisYear - startYear + 1]; // 创建年份数组。
        for (int i = 0; i <= thisYear - startYear; i++) {
            result[i] = startYear + i; // 填充年份数组。
        }
        return result;
    }

    //创建月份选择器的数据数组
    private Integer[] makeMonths() {
        Integer[] result = new Integer[12]; // 创建月份数组。
        for (int i = 0; i < 12; i++) {
            result[i] = i + 1; // 填充月份数组。
        }
        return result;
    }

    //更新面板数据的方法，当前未实现任何逻辑。
    @Override
    public void updateData() {
        // 留空，如果需要更新面板数据，可以在这里添加代码。
    }

    //为查询按钮添加事件监听器
    @Override
    public void addListener() {
        bSubmit.addActionListener(new MonthPickerListener()); // 为查询按钮添加 MonthPickerListener 监听器。
    }
}
