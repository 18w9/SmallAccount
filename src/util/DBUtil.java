package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    //数据库文件的路径
    //静态常量，指定了SQLite数据库文件的位置
    static final String database = "D:\\JavaWork\\MySmallAccount\\db\\data";

    //静态代码块，用于在类加载时加载SQLite JDBC驱动
    //通过使用Class.forName加载JDBC驱动类，确保JVM能够识别SQLite数据库
    static {
        try {
            // 加载 SQLite JDBC 驱动类
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            // 如果 SQLite JDBC 驱动类没有找到，则打印堆栈跟踪
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        // 构建 JDBC 连接 URL，包括数据库文件的路径
        String url = String.format("jdbc:sqlite:%s", database);
        // 使用 DriverManager 获取数据库连接
        return DriverManager.getConnection(url);
    }

    public static void main(String[] args) {
        try {
            Class.forName("org.sqlite.JDBC");
            System.out.println("SQLite JDBC驱动加载成功！");
        } catch (ClassNotFoundException e) {
            System.out.println("错误：未找到SQLite JDBC驱动。");
            e.printStackTrace();
        }
    }
}
