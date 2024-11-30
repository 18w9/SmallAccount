package gui.panel;

import dao.RecordDAO;
import entity.Record;
import gui.page.SpendPage;
import service.ConfigService;
import service.SpendService;
import util.CircleProgressBar;
import util.ColorUtil;
import util.DateUtil;
import util.GUIUtil;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * 业务类 SpendService 对 多个 数据库的业务进行封装 供 SpendPanel 使用
 * SpendService 类提供了获取消费页面数据的服务。
 * 它负责计算和提供本月消费、今日消费、日均消费、本月剩余、日均可用、距离月末天数和消费百分比等信息。
 */
public class SpendPanel extends WorkingPanel{
    // 在类加载时设置外观和感觉
    static {
        GUIUtil.useLNF();
    }

    // SpendPanel 的单例实例
    public static SpendPanel instance = new SpendPanel();

    // 定义显示标签和值的 JLabel 组件
    private JLabel lMonthSpend = new JLabel("本月消费");
    private JLabel lTodaySpend = new JLabel("今日消费");
    private JLabel lAvgSpendPerDay = new JLabel("日均消费");
    private JLabel lMonthLeft = new JLabel("本月剩余");
    private JLabel lDayAvgAvailable = new JLabel("日均可用");
    private JLabel lMonthLeftDay = new JLabel("距离月末");

    private JLabel vMonthSpend = new JLabel("￥2300");
    private JLabel vTodaySpend = new JLabel("￥25");
    private JLabel vAvgSpendPerDay = new JLabel("￥120");
    private JLabel vMonthAvailable = new JLabel("￥2084");
    private JLabel vDayAvgAvailable = new JLabel("￥389");
    private JLabel vMonthLeftDay = new JLabel("15天");

    // 定义圆形进度条组件
    private CircleProgressBar bar;

    private SpendPanel() {
        //设置 SpendPanel 的布局管理器为 BorderLayout。
        this.setLayout(new BorderLayout());
        //创建一个 CircleProgressBar 实例，这是一个自定义的进度条组件，用于显示圆形的进度条。
        bar = new CircleProgressBar();
        //为圆形进度条设置背景颜色。
        bar.setBackgroundColor(ColorUtil.blueColor);

        //使用 GUIUtil 的 setColor 方法设置一系列组件的前景色为灰色。这些组件包括显示标签和数值的 JLabel，用于显示消费信息的各个部分。
        GUIUtil.setColor(ColorUtil.grayColor, lMonthSpend, lTodaySpend, lAvgSpendPerDay, lMonthLeft, lDayAvgAvailable,
                lMonthLeftDay, vAvgSpendPerDay, vMonthAvailable, vDayAvgAvailable, vMonthLeftDay);
        //使用 GUIUtil 的 setColor 方法设置本月消费和今日消费的数值显示 JLabel 的前景色为蓝色。
        GUIUtil.setColor(ColorUtil.blueColor, vMonthSpend, vTodaySpend);

        vMonthSpend.setFont(new Font("微软雅黑", Font.BOLD, 23));
        vTodaySpend.setFont(new Font("微软雅黑", Font.BOLD, 23));

        this.add(center(), BorderLayout.CENTER);
        this.add(south(), BorderLayout.SOUTH);

    }

    //创建并返回南部面板，该面板使用 GridLayout 布局管理器，按行顺序排列标签和数值。
    private JPanel south() {
        // 创建一个新的 JPanel 实例
        JPanel p = new JPanel();
        // 设置面板的布局管理器为 GridLayout，这里设置为 2 行 4 列
        p.setLayout(new GridLayout(2, 4));
        // 向面板中添加标签和数值组件
        p.add(lAvgSpendPerDay);
        p.add(lMonthLeft);
        p.add(lDayAvgAvailable);
        p.add(lMonthLeftDay);
        p.add(vAvgSpendPerDay);
        p.add(vMonthAvailable);
        p.add(vDayAvgAvailable);
        p.add(vMonthLeftDay);
        return p;
    }

    private JPanel center() {
        // 创建一个新的 JPanel 实例
        JPanel p = new JPanel();
        // 设置面板的布局管理器为 BorderLayout
        p.setLayout(new BorderLayout());
        // 向面板的西部区域添加 west 组件，向中心区域添加 east 组件
        p.add(west(), BorderLayout.WEST);
        p.add(east(), BorderLayout.CENTER);
        // 返回配置好的面板
        return p;
    }

    private Component west() {
        // 创建一个新的 JPanel 实例
        JPanel p = new JPanel();
        // 设置面板的布局管理器为 GridLayout，这里设置为 4 行 1 列
        p.setLayout(new GridLayout(4, 1));
        // 向面板中添加本月消费和今日消费的标签和数值组件
        p.add(lMonthSpend);
        p.add(vMonthSpend);
        p.add(lTodaySpend);
        p.add(vTodaySpend);
        // 返回配置好的面板
        return p;
    }

    private Component east() {
        return bar;
    }

    public static void main(String[] args) {

        GUIUtil.showPanel(SpendPanel.instance);
    }


    @Override
    //更新面板上显示的数据，此方法从 SpendService 获取最新的消费数据，并更新面板上显示的数值和进度条
    public void updateData() {
        // 从 SpendService 获取最新的消费数据
        SpendPage spend = new SpendService().getSpendPage();

        // 更新本月消费数值显示
        vMonthSpend.setText(spend.monthSpend);

        // 更新今日消费数值显示
        vTodaySpend.setText(spend.todaySpend);

        // 更新日均消费数值显示
        vAvgSpendPerDay.setText(spend.avgSpendPerDay);

        // 更新日均可用数值显示
        vDayAvgAvailable.setText(spend.dayAvgAvailable);

        // 更新本月剩余数值显示
        vMonthAvailable.setText(spend.monthAvailable);

        // 更新距离月末天数数值显示
        vMonthLeftDay.setText(spend.monthLeftDay);

        // 更新圆形进度条的进度
        bar.setProgress(spend.usagePercentage);

        // 根据是否超支更新数值显示的前景色
        if (spend.isOverSpend) {
            vMonthAvailable.setForeground(ColorUtil.warningColor); // 超支时显示警告色
            vMonthSpend.setForeground(ColorUtil.warningColor); // 超支时显示警告色
            vTodaySpend.setForeground(ColorUtil.warningColor); // 超支时显示警告色
        } else {
            vMonthAvailable.setForeground(ColorUtil.grayColor); // 未超支时显示灰色
            vMonthSpend.setForeground(ColorUtil.blueColor); // 未超支时显示蓝色
            vTodaySpend.setForeground(ColorUtil.blueColor); // 未超支时显示蓝色
        }

        // 根据消费百分比更新进度条的前景色
        bar.setForegroundColor(ColorUtil.getByPercentage(spend.usagePercentage));

        // 添加事件监听器（如果需要）
        addListener();
    }

    @Override
    public void addListener() {

    }
}
