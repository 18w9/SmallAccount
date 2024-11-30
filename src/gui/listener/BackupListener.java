package gui.listener;

import gui.panel.BackupPanel;
import util.SQLUtil;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * BackupPanel的监听器，监听按钮动作后进行导出操作
 */
public class BackupListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        // 获取备份面板的实例。
        BackupPanel p = BackupPanel.instance;

        // 创建一个文件选择器，用于让用户选择文件保存位置。
        JFileChooser fc = new JFileChooser();
        // 设置默认选择的文件名为 "xiaoxiao.db"。
        fc.setSelectedFile(new File("xiaoxiao.db"));

        // 定义文件过滤器，只允许选择以 ".db" 结尾的文件。
        fc.setFileFilter(new FileFilter() {
            @Override
            public String getDescription() {//
                // 返回过滤器的描述，这将显示在文件选择器的底部。
                return ".db";
            }

            @Override
            public boolean accept(File f) {
                // 检查文件名是否以 ".db" 结尾，忽略大小写。
                return f.getName().toLowerCase().endsWith(".db");
            }
        });

        // 显示保存对话框，让用户选择保存位置。
        int returnVal = fc.showSaveDialog(p);
        // 获取用户选择的文件。
        File file = fc.getSelectedFile();

        // 检查用户是否点击了保存按钮。
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            // 如果保存的文件名没有以 ".db" 结尾，自动加上 ".db"。
            System.out.println(file);
            if (! file.getName().toLowerCase().endsWith(".db"))
                file = new File(file.getParent(), file.getName() + ".db");
            System.out.println(file);

            try {
                // 调用 SQLUtil 类的备份方法，备份数据库到指定文件。
                SQLUtil.backup(file.getAbsolutePath());
                // 显示备份成功的消息，包括备份文件的路径。
                JOptionPane.showMessageDialog(p, "备份成功,备份文件位于:\r\n" + file.getAbsolutePath());
            } catch (Exception e1) {
                // 捕获并处理备份过程中可能发生的异常。
                e1.printStackTrace();
                // 显示备份失败的消息，包括错误信息。
                JOptionPane.showMessageDialog(p, "备份失败\r\n,错误:\r\n" + e1.getMessage());
            }
        }
    }

}
