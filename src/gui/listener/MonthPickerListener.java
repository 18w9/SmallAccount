package gui.listener;

import gui.panel.HistoryListPanel;
import gui.panel.MonthPickerPanel;
import util.DateUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * MonthPickerPanel 的监听器，按钮后会修改 MonthPickerPanel.instance.date 为 选择月份的月初
 *
 * @author xenv
 * @see MonthPickerPanel
 */
public class MonthPickerListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        //获取月份选择面板的单例实例，用于访问面板上的组件和方法
        MonthPickerPanel p = MonthPickerPanel.instance;
        //获取用户选择的年份
        Integer year = (Integer) p.cbYear.getSelectedItem();
        //获取用户选择的月份
        Integer month = (Integer) p.cbMonth.getSelectedItem();

        /**
         * 使用 SimpleDateFormat 获取所选月份月初的 Date 对象。
         * 格式化字符串 "yyyy-MM" 用于创建日期字符串，例如 "2024-05"。
         */
        try {
            // 格式化年和月，然后解析成 Date 对象，代表所选月份的第一天。
            p.date = new SimpleDateFormat("yyyy-MM").parse(String.format("%4d-%02d", year, month));
            // 更新 HistoryListPanel，重新获取数据。
            HistoryListPanel.instance.updateData();
        } catch (ParseException e1) {
            // 如果解析日期字符串时发生错误，打印堆栈跟踪。
            e1.printStackTrace();
        }
    }
}
