package cn.edu.qtech.util;

import javax.swing.table.DefaultTableModel;

/**
 * @author Created by Tao on 6/30/2017.
 */
public class TableModelUnEdit extends DefaultTableModel {
    public TableModelUnEdit(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }

    public boolean isCellEditable(int row, int column) {
        return false;
    }
}