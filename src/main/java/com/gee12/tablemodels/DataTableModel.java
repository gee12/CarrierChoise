package com.gee12.tablemodels;

/**
 *
 * @author Иван
 */
import com.gee12.other.Utils;
import com.gee12.structures.DataField;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class DataTableModel extends AbstractTableModel {

    private static final String[] columnNames = {"№", "Наименование", "Значение", "Тип", ""};
    public static final String[] COMBO_STATES = Utils.getNames(DataField.Types.class);
    
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
        switch(col) {
            case 0: 
                return String.class;
            case 1: 
                return String.class;
            case 2:
                return String.class;
            case 3:
                return DataField.Types.class;
            case 4:
                return String.class;
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int row, int col) {
        switch(col) {
            case 0: 
                return row + 1;
            case 1: 
                return data.get(row).getName();
            case 2:
                return data.get(row).getValue();
            case 3:
                return data.get(row).getType();
            case 4:
                return "Удалить";
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        if (col == 0) return false;
        return true;
    }

    @Override
    public void setValueAt(Object v, int row, int col) {
        switch(col) {
            case 1:
                data.get(row).setName((String) v);
                break;
            case 2:
                data.get(row).setValue((String) v);
                break;
            case 3:
                data.get(row).setType(DataField.Types.getType((String) v));
                break;
            default:
                break;
        }
        fireTableRowsUpdated(row, col);
    }
}
