package com.gee12.tablemodels;

/**
 *
 * @author Иван
 */
import com.gee12.other.Utils;
import com.gee12.structures.Criterion;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class CriterionsTableModel extends AbstractTableModel {

    private static final String[] columnNames = {"№", "Наименование", "Значение", "Эталон", "Тип", "", ""};
    public static final String[] COMBO_STATES = Utils.getNames(Criterion.Types.class);
    
    private List<Criterion> data = null;
    
    public CriterionsTableModel() {
        data = new ArrayList<>();
    }
    
    public void setData(List<Criterion> data) {
        this.data = data;
        fireTableDataChanged();
    }
    
    public List<Criterion> getData() {
        return data;
    }
    
    public void addRow(Criterion obj) {
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
    public Object getValueAt(int row, int col) {
        switch(col) {
            case 0: 
                return row + 1;
            case 1: 
                return data.get(row).getName();
            case 2:
                return data.get(row).getValue();
            case 3:
                return data.get(row).getMax();
            case 4:
                return data.get(row).getType();
            case 5:
                return data.get(row).getFormula();
            case 6:
                return "Удалить";
            default:
                return null;
        }
    }

    @Override
    public Class getColumnClass(int col) {
        switch(col) {
            case 4:
                return Criterion.Types.class;
            default:
                return String.class;
        }
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        switch(col) {
            case 0: 
                return false;
            case 1: 
                return true;
            case 2:
                return false;
            case 3:
                return true;
            case 4:
                return true;
            case 5:
                return true;
            case 6:
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
                data.get(row).setValue((String) v);
                break;
            case 3:
                data.get(row).setMax((String) v);
                break;
            case 4:
                data.get(row).setType(Criterion.Types.getType((String) v));
                break;
            case 5:
                data.get(row).setFormula((String) v);
                break;
            default:
                break;
        }
        fireTableCellUpdated(row, col);
    }
}
