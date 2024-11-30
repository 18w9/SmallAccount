package gui.listener;

import entity.Category;
import gui.panel.*;
import service.RecordService;
import util.GUIUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * RecordPanel 的监听器，按钮后会 增改Record
 * 增改由 RecordPanel.instance.updateId 决定 ，默认为 -1 ，可能被 HistoryListPanel 修改
 *
 * @author xenv
 * @see RecordPanel
 * @see HistoryListPanel
 * @see HistoryListListener
 */
public class RecordListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        // 获取记录面板的单例实例，用于访问面板上的组件和方法。
        RecordPanel p = RecordPanel.instance;
        //判断分类数据是否为空，为空则不执行后续操作。
        if(p.cbModel.cs.size() == 0){
            JOptionPane.showMessageDialog(p, "请先添加分类");
            return;
        }
        // 获取用户输入的花费金额。
        String spend = p.tfSpend.getText();

        // 验证输入的花费金额是否为有效的数字。
        if (! GUIUtil.checkNumber(p.tfSpend, "输入的金额")) {
            return;
        }

        // 如果输入的金额为0，提示用户输入金额有误。
        if (spend.equals("0")) {
            JOptionPane.showMessageDialog(p, "输入金额有误");
            return;
        }

        // 获取用户选择的分类。
        Category c = p.getSelectedCategory();

        // 获取用户输入的备注。
        String comment = p.tfComment.getText();

        // 获取用户选择的日期。
        Date d = p.datepick.getDate();

        // 根据 updateId 确定是添加模式还是编辑模式。
        if (p.updateId < 0) { // 默认的添加模式
            // 添加新的记录。
            new RecordService().add(Integer.parseInt(spend), c.getId(), comment, d);
            // 提示用户添加成功。
            JOptionPane.showMessageDialog(p, "添加成功");

            // 切换到支出面板。
            MainPanel.instance.workingPanel.show(SpendPanel.instance);
        } else { // 修改模式
            // 更新选中的记录。
            new RecordService().update(p.updateId, Integer.parseInt(spend), c.getId(), comment, d);
            // 提示用户修改成功。
            JOptionPane.showMessageDialog(p, "修改成功");

            // 切换到历史记录面板。
            MainPanel.instance.workingPanel.show(HistoryPanel.instance);

            // 重置 updateId 为 -1，表示退出编辑模式。
            p.updateId = -1;

            // 刷新历史记录列表面板的数据。
            HistoryListPanel.instance.updateData();
        }
    }

}
