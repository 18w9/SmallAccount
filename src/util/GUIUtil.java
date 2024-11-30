package util;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class GUIUtil {
    //图片的文件夹命令，用于加载图标和其他图像资源
    private static final String imgFolder = "D:\\JavaWork\\MySmallAccount\\resources\\img";

    //定义皮肤，使用特定的外观和感觉
    public static void useLNF(){
        try{
            javax.swing.UIManager.setLookAndFeel("com.pagosoft.plaf.PgsLookAndFeel");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //检查文本框输入的是否为有效数字
    public static boolean checkNumber(JTextField textField,String name){
        //获取文本框中的文本，并取出前后空白字符
        String text =textField.getText().trim();
        //检查文本是否为空或只包含空白字符
        if(text.length() <= 0){
            // 如果文本为空，显示错误消息，提示用户该字段不能为空。
            JOptionPane.showMessageDialog(null, "输入有误，" + name + "不能为空");
            // 获取文本框的焦点，以便用户可以立即输入。
            textField.grabFocus();
            return false; // 返回 false，表示输入无效。
        }
        // 尝试将文本转换为整数。
        try {
            Integer.parseInt(text);
        } catch (Exception e) {
            // 如果转换失败（即文本不是有效的整数），显示错误消息。
            JOptionPane.showMessageDialog(null, "输入有误，" + name + "不是数字");
            return false; // 返回 false，表示输入无效。
        }
        // 如果代码执行到这里，说明文本是有效的数字，返回 true。
        return true;
    }

    //
    public static void showPanel(JPanel p, double stretch){
        //设置外观和感觉
        GUIUtil.useLNF();
        JFrame f = new JFrame();
        f.setLocationRelativeTo(null);//屏幕中央
        f.setSize(500,500);
        // 创建一个CenterPanel对象，用于包含并可能缩放传入的面板，stretch参数控制缩放比例。
        CenterPanel cp = new CenterPanel(stretch, true);//-----------------------------------------
        f.setContentPane(cp);//设置内容面板
        f.setDefaultCloseOperation(3);//设置默认关闭操作为退出程序
        f.setVisible(true);//
        // 使用CenterPanel的show方法将传入的面板p显示在CenterPanel中。
        cp.show(p);
    }

    public static void showPanel(JPanel p) {
        showPanel(p, 0.85);
    }

    public static void setImageIcon(JButton b, String fileName, String tip) {
        // 构建图片文件的完整路径，并创建一个ImageIcon对象。
        ImageIcon i = new ImageIcon((new File(imgFolder, fileName)).getAbsolutePath());
        // 将创建的ImageIcon对象设置为按钮的图标。
        b.setIcon(i);
        // 设置按钮的首选尺寸，这里设置为61x81像素。
        b.setPreferredSize(new Dimension(61, 81));
        // 设置按钮的提示文本。
        b.setToolTipText(tip);
        // 设置按钮文本的垂直位置为底部。
        b.setVerticalTextPosition(JButton.BOTTOM);
        // 设置按钮文本的水平位置为居中。
        b.setHorizontalTextPosition(JButton.CENTER);
        // 设置按钮上的文本，这里使用与提示文本相同的字符串。
        b.setText(tip);
    }

    //设置一个或多个组件的颜色
    public static void setColor(Color color, JComponent... cs){
        //遍历传入的JComponent数组
        for(JComponent c:cs){
            //为每个组件设置前景色
            c.setForeground(color);
        }
    }
}
