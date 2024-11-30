package service;

import dao.RecordDAO;
import entity.Record;
import util.DateUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 业务类ReportService对record数据库的业务进行封装，供ReportPanel使用
 * 此类提供了报告服务，用于生成和统计消费记录
 */
public class ReportService {
    //计算指定日期的消费总额
    private int getDaySpend(Date d, List<Record> monthRawDate){
        int daySpend = 0;
        //遍历整个月的消费记录
        for(Record record:monthRawDate){
            //如果记录的日期和指定日期相同，则累加消费金额
            if(record.getDate().equals(d)){
                daySpend += record.getSpend();
            }
        }
        //返回指定日期的消费金额
        return daySpend;
    }

    //获取当前月份每天的消费记录表
    public List<Record> listThisMonthRecords(){
        //创建记录Dao实例
        RecordDAO dao = new RecordDAO();
        //获取当月所有消费记录
        List<Record> monthRawData = dao.listThisMonth();
        //创建结果列表，用于存储每天的消费记录
        List<Record> result = new ArrayList<>();
        // 获取当前月的第一天
        Date monthBegin = DateUtil.monthBegin();
        // 获取当前月的总天数
        int monthTotalDay = DateUtil.thisMonthTotalDay();
        // 创建日历实例，用于计算日期
        Calendar c = Calendar.getInstance();
        // 遍历当前月的每一天
        for (int i = 0; i < monthTotalDay; i++) {
            // 创建新的消费记录实例
            Record r = new Record();
            // 设置日历时间为当前月的第一天，然后加上偏移量i
            c.setTime(monthBegin);
            c.add(Calendar.DATE, i);
            // 计算当前月的第i天的日期
            Date theDayOfThisMonth = c.getTime();
            // 调用 getDaySpend 方法计算这一天的消费总额
            int daySpend = getDaySpend(theDayOfThisMonth, monthRawData);
            // 设置消费记录的消费金额
            r.setSpend(daySpend);
            // 将消费记录添加到结果列表
            result.add(r);
        }
        // 返回包含当前月每天消费记录的列表
        return result;
    }
}
