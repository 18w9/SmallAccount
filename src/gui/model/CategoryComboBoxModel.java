package gui.model;

import entity.Category;
import service.CategoryService;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.util.List;

/**
 * CategoryPanel 的 ComboBox 的数据模型，实现了 ComboBoxModel<> 接口
 * CategoryComboBoxModel 类实现了 ComboBoxModel 接口，为 JComboBox 组件提供 Category 数据模型。
 * 它负责管理下拉列表的数据，并与 JComboBox 组件进行交互，处理选中项的变化。
 */
public class CategoryComboBoxModel implements ComboBoxModel<Category> {
    //从服务层获取的分类数据列表
    public List<Category> cs = new CategoryService().list();
    //获取当前选中的分类对象
    public Category c;
    //构造函数，初始化 ComboBoxModel
    public CategoryComboBoxModel() {
        // 如果分类列表不为空，则将第一个分类设置为默认选中项
        if (!cs.isEmpty()) {
            c = cs.get(0);
        }
    }
    //返回下拉列表中的项目数
    @Override
    public int getSize() {
        return cs.size();
    }

    //返回下拉列表中指定索引处的项目
    @Override
    public Category getElementAt(int index) {
        return cs.get(index);
    }

    //添加一个 ListDataListener 监听器，用于监听下拉列表数据的变化
    @Override
    public void addListDataListener(ListDataListener l) {
        // 此实现中未处理监听器的添加
    }
    //移除一个 ListDataListener 监听器
    @Override
    public void removeListDataListener(ListDataListener l) {
        // 此实现中未处理监听器的移除
    }

    //设置选中项，当下拉列表中的项被选中时，此方法被调用，以更新模型中的选中项。
    @Override
    public void setSelectedItem(Object anItem) {
        c = (Category) anItem;
    }

    //设置选中项，当下拉列表中的项被选中时，此方法被调用，以更新模型中的选中项。
    @Override
    public Object getSelectedItem() {
        return c;
    }
}
