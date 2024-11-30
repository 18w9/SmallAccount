package gui.model;

import entity.Category;
import service.CategoryService;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.List;
/**
 * CategoryPanel 的 Table 的数据模型，实现了 TableModel 接口
 * CategoryTableModel 类实现了 TableModel 接口，为 JTable 组件提供 Category 数据模型。
 * 它负责管理表格的数据，并与 JTable 组件进行交互，处理数据的获取和更新。
 */

public class CategoryTableModel implements TableModel {
    //表格的列名数组
    private String[] columnNames = new String[]{"分类名称", "消费次数"};
    //表格的行数据，即分类数据列表
    public List<Category> cs = new CategoryService().list();

    //获取表格的行数
    @Override
    public int getRowCount() {
        return cs.size();
    }

    //获取表格的列数
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    //获取指定列索引的列名
    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    //获取指定列的列类类型
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    //判断指定单元格是否可编辑
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    //获取指定单元格的值
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (0 == columnIndex) {
            // 第一列返回分类名称
            return cs.get(rowIndex).getName();
        }
        if (1 == columnIndex) {
            // 第二列返回消费次数
            return cs.get(rowIndex).getRecordNumber();
        }
        return null;
    }

    //设置指定单元格的值
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        // 表格不可编辑，不实现任何操作
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        // 不实现任何操作
    }


    @Override
    public void removeTableModelListener(TableModelListener l) {
        // 不实现任何操作
    }
}
