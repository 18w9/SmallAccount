package entity;

/**
 * 实体类
 *他负责配置文件的管理，id代表配置文件的编号，key代表配置文件的键，value代表配置文件的值
 */
public class Config {
    private int id;
    private String key;
    private String value;

    public Config(){

    }

    public Config(int id,String key,String value){
        this.id=id;
        this.key=key;
        this.value=value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
