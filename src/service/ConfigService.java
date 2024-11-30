package service;

import dao.ConfigDAO;
import entity.Config;

/**
 * 业务类 ConfigService 对 config 数据库的业务进行封装
 * ConfigService 类提供了对应用程序配置数据的操作服务。
 * 它负责管理预算等配置信息，包括初始化、获取和更新配置值。
 */
public class ConfigService {
    // 定义预算配置的键
    public static final String budget = "budget";
    // 定义默认预算值
    private static final String default_budget = "500";

    // ConfigDAO 实例，用于访问配置数据
    private static ConfigDAO dao = new ConfigDAO();

    //类静态初始化块，用于初始化数据库的配置数据。
    static {
        init();
    }

    //初始化数据库的配置数据，如果数据库中不存在配置项，则添加默认配置。
    public static void init() {
        // 使用预算键和默认预算值初始化
        init(budget, default_budget);
    }

    //初始化指定键的配置数据，如果数据库中不存在指定键的配置项，则添加默认值。
    private static void init(String key, String value) {
        // 从数据库中获取指定键的配置项
        Config config = dao.getByKey(key);
        // 如果不存在，则添加新的配置项
        if (config == null) {
            Config c = new Config();
            c.setKey(key);
            c.setValue(value);
            dao.add(c);
        }
    }

    //根据键获取配置项的值。
    public String get(String key) {
        return dao.getByKey(key).getValue();
    }

    //更新配置项的值。
    public void update(String key, String value) {
        Config c = dao.getByKey(key);
        c.setValue(value);
        dao.update(c);
    }

    //获取预算值，并将其转换为整数。
    public int getIntBudget() {
        return Integer.parseInt(get(budget));
    }
}
