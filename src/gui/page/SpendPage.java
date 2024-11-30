package gui.page;
/**
 * SpendPanel的页面类 ，用来装载所有数据
 * SpendPage 类用于表示一个页面上显示的消费数据。
 * 它包含了本月消费、今日消费、日均消费、本月剩余、日均可用、距离月末天数和消费百分比等信息。
 */

public class SpendPage {
    //本月消费的字符串表示，前面带有货币标号
    public String monthSpend;
    // 今日消费的字符串表示，前面带有货币符号
    public String todaySpend;
    // 日均消费的字符串表示，前面带有货币符号
    public String avgSpendPerDay;
    // 本月剩余的字符串表示，前面带有货币符号
    public String monthAvailable;
    // 日均可用的字符串表示，前面带有货币符号
    public String dayAvgAvailable;
    // 距离月末的天数，后面带有“天”字
    public String monthLeftDay;
    // 消费百分比
    public int usagePercentage;
    // 是否超支的布尔值
    public boolean isOverSpend = false;

    public SpendPage(int monthSpend, int todaySpend, int avgSpendPerDay, int monthAvailable, int dayAvgAvailable, int monthLeftDay, int usagePercentage) {
        // 初始化本月消费、今日消费、日均消费的字符串表示
        this.monthSpend = "￥" + monthSpend;
        this.todaySpend = "￥" + todaySpend;
        this.avgSpendPerDay = "￥" + avgSpendPerDay;

        // 判断是否超支
        if (monthAvailable < 0) {
            isOverSpend = true;
        } else {
            isOverSpend = false;
        }

        // 根据是否超支设置本月剩余和日均可用的字符串表示
        if (!isOverSpend) {
            this.monthAvailable = "￥" + monthAvailable;
            this.dayAvgAvailable = "￥" + dayAvgAvailable;
        } else {
            this.monthAvailable = "超支￥" + (-monthAvailable);
            this.dayAvgAvailable = "￥" + 0;
        }

        // 设置距离月末的天数
        this.monthLeftDay = monthLeftDay + "天";
        // 设置消费百分比
        this.usagePercentage = usagePercentage;
    }
}
