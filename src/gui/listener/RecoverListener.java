package gui.listener;

import gui.panel.BackupPanel;
import util.SQLUtil;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class RecoverListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        // 获取备份面板的单例实例，用于访问面板上的组件和方法。
        BackupPanel p = BackupPanel.instance;

        // 创建一个文件选择器，用于让用户选择数据库文件。
        JFileChooser fc = new JFileChooser();
        // 设置文件选择器默认选中的文件名为 "xiaoxiao.db"。
        fc.setSelectedFile(new File("xiaoxiao.db"));
        // 为文件选择器设置一个过滤器，只允许选择以 ".db" 结尾的文件。
        fc.setFileFilter(new FileFilter() {
            @Override
            public String getDescription() {
                // 返回过滤器的描述，这将显示在文件选择器的底部。
                return ".db";
            }

            @Override
            public boolean accept(File f) {
                // 检查文件名是否以 ".db" 结尾，忽略大小写。
                return f.getName().toLowerCase().endsWith(".db");
            }
        });

        // 显示打开对话框，让用户选择一个文件。
        int returnVal = fc.showOpenDialog(p);
        // 获取用户选择的文件。
        File file = fc.getSelectedFile();
        // 如果用户点击了批准按钮（通常是“打开”或“确定”按钮），则执行恢复操作。
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                // 调用 SQLUtil 类的 recover 方法，从选定的文件恢复数据库。
                SQLUtil.recover(file.getAbsolutePath());
                // 显示恢复成功的提示消息。
                JOptionPane.showMessageDialog(p, "恢复成功");
            } catch (Exception e1) {
                // 如果恢复过程中发生异常，打印异常堆栈跟踪以便于调试。
                e1.printStackTrace();
                // 显示恢复失败的提示消息，包括错误信息。
                JOptionPane.showMessageDialog(p, "恢复失败\r\n,错误:\r\n" + e1.getMessage());
            }
        }
    }

}
