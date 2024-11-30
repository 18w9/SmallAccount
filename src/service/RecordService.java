package service;

import dao.RecordDAO;
import entity.Record;
import util.DateUtil;

import java.util.Date;
import java.util.List;

/**
 * 业务类 RecordService 对 record 数据库的业务进行封装
 * RecordService 类提供了对记录（Record）数据的操作服务。
 * 它负责管理消费记录的添加、更新、删除以及获取指定月份的记录列表。
 * @author xenv
 */
public class RecordService {
    // 记录DAO实例，用于访问记录数据
    private static RecordDAO dao = new RecordDAO();

    //添加一条新的消费记录。
    public void add(int spend, int cid, String comment, Date date) {
        // 创建一个新的记录对象
        Record r = new Record();
        // 设置记录的属性
        r.setSpend(spend);
        r.setCid(cid);
        r.setComment(comment);
        r.setDate(date);
        // 通过记录DAO添加记录
        dao.add(r);
    }

    //更新一条消费记录。
    public void update(int id, int spend, int cid, String comment, Date date) {
        // 创建一个新的记录对象
        Record r = new Record();
        // 设置记录的属性
        r.setSpend(spend);
        r.setId(id);
        r.setCid(cid);
        r.setComment(comment);
        r.setDate(date);
        // 通过记录DAO更新记录
        dao.update(r);
    }

    //删除一条消费记录。
    public void delete(int id) {
        // 通过记录DAO删除记录
        dao.delete(id);
    }

    //获取指定月份的记录列表。
    public List<Record> listMonth(Date startDay) {
        // 计算指定月份的最后一天的日期
        Date endDay = DateUtil.monthEnd(startDay);
        // 通过记录DAO获取指定月份的记录列表
        return dao.list(startDay, endDay);
    }
}
