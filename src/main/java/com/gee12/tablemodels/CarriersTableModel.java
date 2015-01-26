package com.gee12.tablemodels;

/**
 *
 * @author Иван
 */
import com.gee12.structures.Carrier;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.table.AbstractTableModel;

public class CarriersTableModel extends AbstractTableModel {

    private String[] columnNames = {"Рей\nтинг", "Перевозчик", "Грузо\nподъемн.","Кол-во\nповтор."," "};
    private List<Carrier> data = null;
    
    public CarriersTableModel() {
        data = new ArrayList<>();
    }
    
    public void setData(List<Carrier> data) {
        this.data = data;
        fireTableDataChanged();
    }
    
    public List<Carrier> getData() {
        return data;
    }
    
    public void addRow(Carrier car) {
        data.add(car);
        fireTableDataChanged();
    }
    
    public void deleteRow(int row) {
        data.remove(row);
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
                return data.get(row).getRepeatNum();
             case 4:
                return "Удалить";           
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
            case 4:
                return String.class;            
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        switch(col) {
            case 4:
                return true;            
            default:
                return false;
        }
    }

    @Override
    public void setValueAt(Object v, int row, int col) {
        switch(col) {
            case 1:
                data.get(row).setName((String) v);
                break;
            case 2:
                data.get(row).setCapacity(Double.parseDouble((String)v));
                break;
            case 3:
                data.get(row).setRepeatNum(Integer.parseInt((String)v));
                break;
            default:
                break;
        }        
        fireTableDataChanged();
    }
    
}
