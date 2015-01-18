package com.gee12.tableModels;

/**
 *
 * @author Иван
 */
import com.gee12.structures.DataField;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class DataSimpleTableModel extends AbstractTableModel {

    private static final String[] columnNames = {"Наименование", "Значение", "Тип", "Ячейка"};

    private List<DataField> data = null;
    
    public DataSimpleTableModel() {
        data = new ArrayList<>();
    }
    
    public void setData(List<DataField> data) {
        this.data = data;
        fireTableDataChanged();
    }
    
    public List<DataField> getData() {
        return data;
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
