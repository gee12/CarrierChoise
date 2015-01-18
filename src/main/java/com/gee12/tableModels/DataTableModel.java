package com.gee12.tableModels;

/**
 *
 * @author Иван
 */
import com.gee12.other.Util;
import com.gee12.structures.DataField;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class DataTableModel extends AbstractTableModel {

    private String[] columnNames = {"Наименование", "Значение", "Тип", "Удалить"};
    public static final String[] COMBO_STATES = Util.getNames(DataField.Types.class);

    private List<DataField> data = null;
    
    public DataTableModel() {
        data = new ArrayList<>();
    }
    
    public void setData(List<DataField> data) {
        this.data = data;
        fireTableDataChanged();
    }
    
    public List<DataField> getData() {
        return data;
    }
    
    public void addRow(DataField obj) {
        data.add(obj);
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
    public Class getColumnClass(int col) {
//        return data.get(col).getClass();
        switch(col) {
            case 0: 
                return String.class;
            case 1:
                return String.class;
            case 2:
                return DataField.Types.class;
            case 3:
                return String.class;
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int row, int col) {
        switch(col) {
            case 0: 
                return data.get(row).getName();
            case 1:
                return data.get(row).getValue();
            case 2:
                return data.get(row).getType();
            case 3:
                return "Удалить";
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return true;
    }

    @Override
    public void setValueAt(Object v, int row, int col) {
        switch(col) {
            case 0:
                data.get(row).setName((String) v);
                break;
            case 1:
                data.get(row).setValue((String) v);
                break;
            case 2:
                data.get(row).setType(DataField.Types.valueOf((String) v));
                break;
            default:
                break;
        }
        fireTableRowsUpdated(row, col);
    }
}
