package service;

import dao.CategoryDAO;
import dao.RecordDAO;
import entity.Category;
import entity.Record;

import java.util.List;

/**
 * 业务类 CategoryService 对category数据库的业务进行封装
 * CategoryService 类提供了对分类（Category）数据的操作服务。
 * 它负责与数据库中的分类和记录信息进行交互，包括列出分类、添加新分类、更新分类和删除分类。
 */
public class CategoryService {
    // 类DAO用于访问分类数据
    private CategoryDAO categoryDAO = new CategoryDAO();
    // 记录DAO用于访问记录数据
    private RecordDAO recordDAO = new RecordDAO();
    //列出所有分类，并为每个分类附加记录数量。
    public List<Category> list(){
        // 从分类DAO获取所有分类的列表
        List<Category> cs = categoryDAO.list();
        //遍历每个分类
        for(Category c:cs){
            //为当前分类获取其所有记录的列表
            List<Record> rs = recordDAO.list(c.getId());
            //设置分类的记录数量为对应记录列表的大小
            c.setRecordNumber(rs.size());
        }
        // 使用lambda表达式对分类列表进行排序，根据记录数量降序排列
        cs.sort((c1, c2) -> c2.getRecordNumber() - c1.getRecordNumber());
        return cs;
    }

    //添加一个新的分类
    public void add(String name){
        //创建一个新的分类对象
        Category c = new Category();
        //设置新分类新名称
        c.setName(name);
        //通过分类DAO添加新分类
        categoryDAO.add(c);
    }
    //更新一个分类
    public void update(int id, String name) {
        // 创建一个新的分类对象
        Category c = new Category();
        // 设置分类的ID和新名称
        c.setId(id);
        c.setName(name);
        // 通过分类DAO更新分类
        categoryDAO.update(c);
    }

     //删除一个分类
     public void delete(int id){
        //通过分类DAO删除分类
        categoryDAO.delete(id);
    }
}
