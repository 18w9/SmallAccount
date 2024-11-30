package dao;

import entity.Record;
import util.DBUtil;
import util.DateUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Dao类-Record，对record表进行增删改车操作，还做了一些冗余的方法，不再过多注释
 */
public class RecordDAO {
    //添加一条新的消费记录到数据库
    public void add(Record record){
    //定义SQL插入语句，用于将新的消费记录插入到Record表中
    String sql ="insert into record ('spend','cid','comment','date')values(?,?,?,?)";

        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)){

            //获取数据库连接和预编译的SQL语句对象
            //DBUtil.getConnection()方法用于获取数据库连接，prepareStatement()方法用于预编译SQL语句，返回一个PreparedStatement对象，用于执行SQL语句。
            //try-with-resources语句块，用于自动关闭数据库连接和预编译的SQL语句对象，避免资源泄露。

            //设置预编译SQL语句中的参数
            ps.setInt(1,record.getSpend());//设置花费金额参数
            ps.setInt(2,record.getCid());//设置分类cid参数
            ps.setString(3,record.getComment());//设置备注参数
            ps.setDate(4, DateUtil.util2sql(record.getDate()));//设置日期参数，使用DateUtil类将Date对象转换为SQL Date

            //执行SQL插入语句，将记录添加到数据库
            ps.execute();

            //获取由数据库生成的主键（ID）
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                //如果结果集中有数据，则获取生成的ID
                int id = rs.getInt(1);
                record.setId(id);//将生成的ID
            }

        } catch (SQLException e) {
            // 捕获并处理可能发生的 SQL 异常。
            e.printStackTrace();
        }
    }
    //更新数据库的消费记录
    public int update(Record record){
        // 定义 SQL 更新语句，用于更新 record 表中的记录。
        String sql = "update record set `spend` = ? , `cid` = ? , `comment` = ? , `date` = ? where id = ?";

        // 初始化结果变量，用于存储更新操作影响的行数。
        int result = 0;

        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            // 使用 DBUtil 获取数据库连接。
            // 通过 try-with-resources 语句确保数据库连接和 PreparedStatement 在操作完成后被自动关闭。

            // 设置 PreparedStatement 的参数。
            ps.setInt(1, record.getSpend()); // 设置花费金额参数。
            ps.setInt(2, record.getCid());   // 设置分类 ID 参数。
            ps.setString(3, record.getComment()); // 设置备注参数。
            ps.setDate(4, DateUtil.util2sql(record.getDate())); // 设置日期参数，使用 DateUtil 类将 Date 对象转换为 SQL Date。
            ps.setInt(5, record.getId()); // 设置 WHERE 子句中的 ID 参数，用于指定要更新的记录。

            // 执行更新操作，并获取影响的行数。
            result = ps.executeUpdate();
        } catch (SQLException e) {
            // 捕获并处理可能发生的 SQL 异常。
            e.printStackTrace();
        }
        // 返回更新操作影响的行数。
        return result;
    }

    // 删除数据库的消费记录
    public int delete(int id){
        // 定义 SQL 删除语句，用于从 record 表中删除指定 ID 的记录。
        String sql = "delete from record where id = ?";
        // 初始化结果变量，用于存储删除操作影响的行数。
        int result = 0;
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            // 使用 DBUtil 获取数据库连接。
            // 通过 try-with-resources 语句确保数据库连接和 PreparedStatement 在操作完成后被自动关闭。

            // 设置 PreparedStatement 的参数。
            ps.setInt(1, id); // 设置 WHERE 子句中的 ID 参数。

            // 执行删除操作，并获取影响的行数。
            result = ps.executeUpdate();
        } catch (SQLException e) {
            // 捕获并处理可能发生的 SQL 异常。
            e.printStackTrace();
        }
        // 返回删除操作影响的行数。
        return result;
    }
    // 根据ID获取消费记录
    public Record get(int id) {
        // 初始化 Record 对象，用于存储查询结果。
        Record record = null;
        // 定义 SQL 查询语句，用于从 record 表中查询指定 ID 的记录。
        String sql = "select * from record where id = ?";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            // 使用 DBUtil 获取数据库连接。
            // 通过 try-with-resources 语句确保数据库连接和 PreparedStatement 在操作完成后被自动关闭。

            // 设置 PreparedStatement 的参数。
            ps.setInt(1, id); // 设置 WHERE 子句中的 ID 参数。

            // 执行查询操作。
            ResultSet rs = ps.executeQuery();
            // 检查结果集是否包含数据。
            if (rs.next()) {
                // 如果结果集中有数据，则创建一个新的 Record 对象，并设置其属性值。
                record = new Record(rs.getInt("id"),
                        rs.getInt("spend"),
                        rs.getInt("cid"),
                        rs.getString("comment"),
                        rs.getDate("date"));
            }
        } catch (SQLException e) {
            // 捕获并处理可能发生的 SQL 异常。
            e.printStackTrace();
        }
        // 返回 Record 对象。
        return record;
    }

    //从数据库中查询一定数量的记录，并按ID降序排列
    public List<Record> list(int start, int count){
        //定义SQl查询语句，使用LIMIT子句来限制查询结果的数量
        String sql = "select * from record order by id desc limit ?,?";
        //初始化一个列表，用于存储查询结果
        List<Record> records = new ArrayList<>();
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            // 使用 DBUtil 获取数据库连接，并创建一个预编译的 SQL 语句对象。
            // try-with-resources 语句确保数据库连接和 PreparedStatement 在操作完成后被自动关闭。

            // 设置 SQL 语句中的参数。
            ps.setInt(1, start); // 设置查询的起始位置（偏移量）。
            ps.setInt(2, count); // 设置查询的记录数。

            // 执行查询操作，并获取查询结果集。
            ResultSet rs = ps.executeQuery();
            // 遍历结果集，并对每一行数据创建一个 Record 对象。
            while (rs.next()) {
                Record record = new Record(rs.getInt("id"),
                        rs.getInt("spend"),
                        rs.getInt("cid"),
                        rs.getString("comment"),
                        rs.getDate("date"));
                // 将创建的 Record 对象添加到列表中。
                records.add(record);
            }
        } catch (SQLException e) {
            // 捕获并处理可能发生的 SQL 异常。
            e.printStackTrace();
        }
        // 返回包含查询结果的列表。
        return records;
    }

    //获取所有消费记录的列表
    public List<Record> list() {
        // 调用另一个 list 方法，从第 0 条记录开始，获取最大数量的记录（Short.MAX_VALUE）。
        return list(0, Short.MAX_VALUE);
    }

    //根据id获取消费记录
    public List<Record> list(int cid) {
        String sql = "select * from record where `cid` = ?";
        List<Record> records = new ArrayList<>();
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, cid); // 设置 WHERE 子句中的分类 ID 参数。
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Record record = new Record(rs.getInt("id"),
                        rs.getInt("spend"),
                        rs.getInt("cid"),
                        rs.getString("comment"),
                        rs.getDate("date"));
                records.add(record);
            }
        } catch (SQLException e) {
            // 捕获并处理可能发生的 SQL 异常。
            e.printStackTrace();
        }
        // 返回包含查询结果的列表。
        return records;
    }

    //根据日期获取
    public List<Record> list(java.util.Date day) {
        String sql = "select * from record where `date` = ?";
        List<Record> records = new ArrayList<>();
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setDate(1,DateUtil.util2sql(day));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Record record = new Record(rs.getInt("id"),
                        rs.getInt("spend"),
                        rs.getInt("cid"),
                        rs.getString("comment"),
                        rs.getDate("date"));
                records.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }

    //获取今天的所有消费记录。
    public List<Record> listToday() {
        return list(new java.util.Date());
    }

    //获取指定日期范围内的所有消费记录。
    public List<Record> list(java.util.Date start, java.util.Date end) {
        String sql = "select * from record where `date` >= ? and `date` <= ?";
        List<Record> records = new ArrayList<>();
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setDate(1, DateUtil.util2sql(start));
            ps.setDate(2, DateUtil.util2sql(end));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Record record = new Record(rs.getInt("id"),
                        rs.getInt("spend"),
                        rs.getInt("cid"),
                        rs.getString("comment"),
                        rs.getDate("date"));
                records.add(record);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }

    //获取本月的所有消费记录
    public List<Record> listThisMonth(){
        return list(DateUtil.monthBegin(), DateUtil.monthEnd());
    }

    //获取消费记录的总数量
    public int getTotal() {
        String sql = "select count(*) from record";
        try (Connection c = DBUtil.getConnection();
             Statement s = c.createStatement()) {
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
