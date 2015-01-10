package com.gee12.panels;

/**
 *
 * @author Иван
 */
import com.gee12.structures.Carrier;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class CurriersTableModel extends AbstractTableModel {

    private String[] columnNames = {"Рей\nтинг", "Перевозчик", "Грузо\nподъемн.","Кол-во\nповтор."};
    private List<Carrier> data = null;
    
    public CurriersTableModel() {
        data = new ArrayList<>();
    }
    
    public void setData(List<Carrier> data) {
        this.data = data;
        fireTableDataChanged();
    }
    
    public void addRow(Carrier obj) {
        data.add(obj);
        fireTableDataChanged();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    
    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public Object getValueAt(int row, int col) {
        switch(col) {
            case 0: 
                return row + 1;
            case 1: 
                return data.get(row).getName();
            case 2:
                return data.get(row).getCapacity();
            case 3:
                return data.get(row).getCapacityRepeatNum();
            default:
                return null;
        }
    }

    @Override
    public Class getColumnClass(int col) {
        switch(col) {
            case 0: 
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return Double.class;
            case 3:
                return Integer.class;
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    @Override
    public void setValueAt(Object v, int row, int col) {
    }
}
