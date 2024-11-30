package util;

import gui.panel.WorkingPanel;

import javax.swing.*;
import java.awt.*;

/**
 *工具类CenterPanel 剧中面板，实现一个可以有一个子面板并且该子面板居中的面板
 */
public class CenterPanel extends JPanel{
    private double rate;//拉伸比例
    private JComponent c;//要居中的子组件，提供了许多用于绘制、事件处理和可访问性的通用功能，这些功能被其子类继承和使用
    private boolean stretch;//是否拉伸子组件

    public CenterPanel(double rate,boolean stretch){
        this.setLayout(null);//设置布局管理器为null，即不设置布局管理器，使用默认的布局管理器
        this.rate=rate;
        this.stretch=stretch;
    }

    public CenterPanel(double rate) {
        this(rate, true);
    }
    // swing在更新界面的时候会调用这个方法，重写 JPanel 的 repaint 方法，用于在 Swing 组件更新界面时调整并居中显示子组件

    public void repaint() {
        // 首先检查子组件 c 是否为 null，如果不是，则进行后续操作
        if (null != c) {
            // 获取当前容器（即 CenterPanel 本身）的大小
            //Dimension用于表示组件的尺寸，宽和高等
            Dimension containerSize = this.getSize();
            // 获取子组件的首选大小，这通常是子组件希望被显示的大小
            Dimension componentSize = c.getPreferredSize();

            // 如果设置为拉伸（stretch 为 true）
            if (stretch) {
                // 根据拉伸比例（rate）和容器的大小计算子组件的新大小
                c.setSize((int) (containerSize.width * rate),
                        (int) (containerSize.height * rate));
            } else {
                // 如果不拉伸，则使用子组件的首选大小
                c.setSize(componentSize);
            }

            // 计算子组件的新位置，使其在容器中居中显示
            // 通过计算容器大小和子组件大小的差值，然后除以 2 得到居中的偏移量
            c.setLocation(containerSize.width / 2 - c.getSize().width / 2,
                    containerSize.height / 2 - c.getSize().height / 2);
        }
        // 最后，调用父类（JPanel）的 repaint 方法来实际重绘界面
        // 这一步是必要的，因为它会触发组件的重绘过程，包括上述对子组件位置和大小的调整
        super.repaint();
    }

    //新建一个CenterPanel后可以用show方法，就可以将 一个子面板 进行居中显示
    public void show(JComponent p) {
        this.c = p;
        //获取当前面板的所有子面板
        Component[] cs = getComponents();
        //全部清除
        for (Component c : cs) {
            remove(c);
        }
        //加进来一个子面板
        add(p);
        //如果是一个实现了 WorkingPanel 的子类，会执行它的 updateData() 方法

        //检查传入的组件 p 是否是 WorkingPanel 类的实例。
        //如果是，则调用该实例的 updateData 方法来更新数据。
        if ((p instanceof WorkingPanel)) {
            // 将 p 强制转换为 WorkingPanel 类型，因为已经确认它是 WorkingPanel 的实例。
            ((WorkingPanel) p).updateData();
        }
        //居中处理
        this.updateUI();
    }
    //测试用
    public static void main(String[] args) {
        // 创建一个新的 JFrame 实例，它将作为应用程序的主窗口
        JFrame f = new JFrame();
        f.setSize(400, 400);
        f.setLocationRelativeTo(null);//居中
        // 创建一个 CenterPanel 实例，设置拉伸比例为 0.85，并且设置为拉伸模式
        CenterPanel cp = new CenterPanel(0.85, true);
        // 将 CenterPanel 设置为 JFrame 的内容面板，这样 CenterPanel 就会填充整个窗口
        f.setContentPane(cp);
        // 设置 JFrame 的默认关闭操作为退出程序（JFrame.EXIT_ON_CLOSE）
        f.setDefaultCloseOperation(3);
        // 使 JFrame 可见，显示在屏幕上
        f.setVisible(true);
        // 创建一个新的 JButton 实例，按钮上显示文本 "abc"
        JButton b = new JButton("abc");
        // 使用 CenterPanel 的 show 方法将 JButton 居中显示在 CenterPanel 中
        cp.show(b);
    }

}
