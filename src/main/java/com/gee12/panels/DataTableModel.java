package com.gee12.panels;

/**
 *
 * @author Иван
 */
import com.gee12.structures.DataField;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class DataTableModel extends AbstractTableModel {

    private String[] columnNames = {"Наименование", "Значение"};
    private List<DataField> data = null;
    
    public DataTableModel() {
        data = new ArrayList<>();
    }
    
    public void setData(List<DataField> data) {
        this.data = data;
        fireTableDataChanged();
    }
    
    public void addRow(DataField obj) {
        data.add(obj);
        fireTableDataChanged();
    }

    //Выдает количество колонок
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    
    //Выдает название колонки
    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    //Выдает количество строк
    @Override
    public int getRowCount() {
        return data.size();
    }

    //Выдает значение ячейки
    @Override
    public Object getValueAt(int row, int col) {
        switch(col) {
            case 0: 
                return data.get(row).getName();
            case 1:
                return data.get(row).getValue();
            default:
                return null;
        }
    }

    //Возвращает класс колонки
    @Override
    public Class getColumnClass(int col) {
        switch(col) {
            case 0: 
                return data.get(0).getName().getClass();
            case 1:
                return data.get(0).getValue().getClass();
            default:
                return null;
        }
    }

    //Возвращает, можно ли редактировать ячейку
    @Override
    public boolean isCellEditable(int row, int col) {
        switch (col) {
            case 0:
            case 1:
                return true;
            default:
                return false;
         }
    }

    //Установка нового значения
    @Override
    public void setValueAt(Object v, int row, int col) {
        switch(col) {
            case 0: 
                data.get(row).setName((String)v);
            case 1:
                data.get(row).setValue((String)v);
            default:
                break;
        }
        fireTableCellUpdated(row, col);
    }
}
