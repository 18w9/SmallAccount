package gui.panel;

import gui.listener.ConfigListener;
import service.ConfigService;
import util.ColorUtil;
import util.GUIUtil;

import javax.swing.*;
import java.awt.*;

/**
 * 界面类 ConfigPanel 设置页 设置预算
 */
public class ConfigPanel extends WorkingPanel{
    static {
        // 类静态初始化块，用于设置应用程序的外观和感觉。
        GUIUtil.useLNF();
    }

    // ConfigPanel 的单例实例，确保整个应用程序中只有一个 ConfigPanel 实例。
    public static ConfigPanel instance = new ConfigPanel();

    // 标签，显示“本月预算(￥)”。
    private JLabel lBudget = new JLabel("本月预算(￥)");
    // 文本框，用于输入本月预算。
    public JTextField tfBudget = new JTextField();
    // 按钮，用于重置数据库。
    public JButton bTruncate = new JButton("重置数据库");
    // 按钮，用于更新配置信息。
    public JButton bSubmit = new JButton("更新");

    //ConfigPanel 的构造函数，初始化面板并添加组件。
    public ConfigPanel() {
        // 设置标签和按钮的颜色。
        GUIUtil.setColor(ColorUtil.grayColor, lBudget);
        GUIUtil.setColor(ColorUtil.blueColor, bSubmit);

        // 创建北部面板，用于包含预算相关的组件。
        JPanel pNorth = new JPanel();
        // 创建南部面板，用于包含重置数据库按钮。
        JPanel pSouth = new JPanel();
        // 设置组件之间的间隔。
        int gap = 40;
        // 设置北部面板的布局为 GridLayout，3行1列，间隔为 gap。
        pNorth.setLayout(new GridLayout(3, 1, gap, gap));

        // 将标签、文本框和更新按钮添加到北部面板。
        pNorth.add(lBudget);
        pNorth.add(tfBudget);
        pNorth.add(bSubmit);
        // 设置面板的布局管理器为 BorderLayout。
        this.setLayout(new BorderLayout());
        // 将北部面板添加到面板的北部。
        this.add(pNorth, BorderLayout.NORTH);

        // 将重置数据库按钮添加到南部面板。
        pSouth.add(bTruncate);
        // 将南部面板添加到面板的南部。
        this.add(pSouth, BorderLayout.SOUTH);

        // 添加事件监听器到按钮。
        addListener();
    }

    //为按钮添加事件监听器。
    public void addListener() {
        // 创建配置事件监听器。
        ConfigListener l = new ConfigListener();
        // 为更新按钮添加动作监听器。
        bSubmit.addActionListener(l);
        // 为重置数据库按钮添加动作监听器。
        bTruncate.addActionListener(l);
    }

    //更新面板上显示的数据。
    public void updateData() {
        // 创建配置服务实例。
        ConfigService service = new ConfigService();
        // 从配置服务获取本月预算，并设置到文本框。
        tfBudget.setText(service.get(ConfigService.budget));
        // 获取文本框的焦点。
        tfBudget.grabFocus();
    }


    public static void main(String[] args) {
        // 使用 GUIUtil 工具类的 showPanel 方法显示 ConfigPanel 的单例实例。
        GUIUtil.showPanel(ConfigPanel.instance);
    }
}
