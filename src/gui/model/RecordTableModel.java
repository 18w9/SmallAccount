package gui.model;

import dao.CategoryDAO;
import entity.Record;
import gui.panel.MonthPickerPanel;
import service.RecordService;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.util.List;

/**
 * HistoryListPanel 的 Table 的数据模型，实现了 TableModel 接口
 * RecordTableModel 类实现了 TableModel 接口，为 JTable 组件提供 Record 数据模型。
 * 它负责管理表格的数据，并与 JTable 组件进行交互，处理数据的获取和更新。
 */

public class RecordTableModel implements TableModel {
    //表格的列名数组
    private String[] columnNames =new String[]{"ID", "花费", "分类", "备注", "日期"};
    //表格的行数据，即记录数据列表，这里使用RecordService获取指定月份的记录数据
    public List<Record> rs = new RecordService().listMonth(MonthPickerPanel.instance.date);

    //获取表格的行数
    @Override
    public int getRowCount() {
        return rs.size();
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
        // 获取当前行的记录对象
        Record record = rs.get(rowIndex);
        if (0 == columnIndex) {
            // 第一列返回记录的ID
            return record.getId();
        }
        if (1 == columnIndex) {
            // 第二列返回记录的花费
            return record.getSpend();
        }
        if (2 == columnIndex) {
            // 第三列返回记录的分类名称
            int cid = record.getCid();
            return new CategoryDAO().get(cid).getName();
        }
        if (3 == columnIndex) {
            // 第四列返回记录的备注
            return record.getComment();
        }
        if (4 == columnIndex) {
            // 第五列返回记录的日期
            return record.getDate();
        }
        return null;
    }

    //设置指定单元格的值，由于表格不可编辑，此方法不实现任何操作
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        // 表格不可编辑，不实现任何操作
    }

    //添加一个 TableModelListener 监听器，用于监听表格数据的变化
    @Override
    public void addTableModelListener(TableModelListener l) {
        // 不实现任何操作
    }

    //移除一个 TableModelListener 监听器
    @Override
    public void removeTableModelListener(TableModelListener l) {
        // 不实现任何操作
    }
}
