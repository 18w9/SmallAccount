package entity;

/**
 * 实体类Category分类
 *他代表一个支出类型，比如：交通、餐饮、购物等，id是自增主键，name是分类名称
 */
public class Category {

    private int id;
    private String name;

    private int recordNumber;

    public Category() {
    }

    public Category(int id,String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRecordNumber() {
        return recordNumber;
    }

    public void setRecordNumber(int recordNumber) {
        this.recordNumber = recordNumber;
    }
    @Override
    public String toString(){
        return name;
    }
}
