package com.gee12.tableModels;

/**
 *
 * @author Иван
 */
import com.gee12.structures.Criterion;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class CriterionsTableModel extends AbstractTableModel {

    private String[] columnNames = {"Наименование", "Значение", "Тип"};
    private List<Criterion> data = null;
    
    public CriterionsTableModel() {
        data = new ArrayList<>();
    }
    
    public void setData(List<Criterion> data) {
        this.data = data;
        fireTableDataChanged();
    }
    
    public void addRow(Criterion obj) {
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
    public Class getColumnClass(int col) {
        switch(col) {
            case 0: 
                return String.class;
            case 1:
                return String.class;
            case 2:
                return Criterion.Types.class;
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int row, int col) {
//        switch (col) {
//            case 0:
//            case 1:
//                return true;
//            default:
//                return false;
//         }
        return false;
    }

    @Override
    public void setValueAt(Object v, int row, int col) {
//        switch(col) {
//            case 0: 
//                data.get(row).setName((String)v);
//                break;
//            case 1:
//                data.get(row).setValue((String)v);
//                break;
//            default:
//                break;
//        }
//        fireTableCellUpdated(row, col);
    }
}
