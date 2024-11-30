package service;

import dao.RecordDAO;
import entity.Record;
import gui.page.SpendPage;
import util.DateUtil;

import java.util.List;

/**
 * 业务类 SpendService 对 多个 数据库的业务进行封装 供 SpendPanel 使用
 * SpendService 类提供了获取消费页面数据的服务。
 * 它负责计算和提供本月消费、今日消费、日均消费、本月剩余、日均可用、距离月末天数和消费百分比等信息。
 */
public class SpendService {
    //获取消费页面的数据
    public SpendPage getSpendPage() {
        // 创建记录DAO实例，用于访问记录数据
        RecordDAO dao = new RecordDAO();

        // 获取当前月的所有消费记录
        List<Record> thisMonthRecords = dao.listThisMonth();
        // 获取今天的消费记录
        List<Record> todayRecords = dao.listToday();
        // 获取当前月的总天数
        int thisMonthTotalday = DateUtil.thisMonthTotalDay();

        // 初始化消费统计变量
        int monthSpend = 0;
        int todaySpend = 0;
        int avgSpendPerDay = 0;
        int monthAvailable = 0;
        int dayAvgAvailable = 0;
        int monthLeftDay = 0;
        int usagePercentage = 0;

        // 获取预算配置值，并转换为整数
        int monthBudget = new ConfigService().getIntBudget();

        // 计算本月总消费
        for (Record record : thisMonthRecords) {
            monthSpend += record.getSpend();
        }

        // 计算今日总消费
        for (Record record : todayRecords) {
            todaySpend += record.getSpend();
        }

        // 计算日均消费
        avgSpendPerDay = monthSpend / thisMonthTotalday;

        // 计算本月剩余金额
        monthAvailable = monthBudget - monthSpend;

        // 获取本月剩余天数
        monthLeftDay = DateUtil.thisMonthLeftDay();

        // 计算日均可用金额
        dayAvgAvailable = monthAvailable / monthLeftDay;

        // 计算消费百分比
        usagePercentage = monthSpend * 100 / monthBudget;

        // 创建SpendPage对象，包含所有计算出的消费数据
        return new SpendPage(monthSpend, todaySpend, avgSpendPerDay, monthAvailable, dayAvgAvailable, monthLeftDay, usagePercentage);
    }
}
