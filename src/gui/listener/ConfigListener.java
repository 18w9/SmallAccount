package gui.listener;

import gui.panel.ConfigPanel;
import gui.panel.WorkingPanel;
import service.ConfigService;
import util.GUIUtil;
import util.SQLUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfigListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        // 获取配置面板的单例实例，用于访问面板上的组件和方法。
        ConfigPanel p = ConfigPanel.instance;

        // 检查是否是更新按钮被点击。
        if (p.bSubmit == e.getSource()) {
            // 验证文本框中的输入是否为有效的数字。
            if (! GUIUtil.checkNumber(p.tfBudget, "本月预算")) {
                // 如果输入无效，则不执行任何操作并返回。
                return;
            }
            // 创建配置服务实例，用于执行配置相关的数据库操作。
            ConfigService service = new ConfigService();
            // 更新本月预算配置项。
            service.update(ConfigService.budget, p.tfBudget.getText());
            // 显示设置成功的提示消息。
            JOptionPane.showMessageDialog(p, "设置成功");
        }

        // 检查是否是重置数据库按钮被点击。
        if (p.bTruncate == e.getSource()) {
            // 弹出确认对话框，让用户确认是否清空所有数据。
            if (JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(p, "确实清空所有数据？")) {
                // 如果用户取消，则不执行任何操作并返回。
                return;
            }
            // 调用 SQLUtil 类的 truncate 方法，清空所有数据。
            SQLUtil.truncate();
            // 重新初始化数据库配置项。
            ConfigService.init();
            // 显示重置成功的提示消息。
            JOptionPane.showMessageDialog(p, "重置成功");
        }
    }
}
