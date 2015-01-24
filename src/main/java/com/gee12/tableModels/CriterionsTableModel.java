package com.gee12.tableModels;

/**
 *
 * @author Иван
 */
import com.gee12.other.Util;
import com.gee12.structures.Criterion;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class CriterionsTableModel extends AbstractTableModel {

    private static final String[] columnNames = {"Наименование", "Значение", "Тип", "", ""};
    public static final String[] COMBO_STATES = //Util.getNames(Criterion.Types.class);
    {Criterion.Types.BASE.name(), Criterion.Types.OTHER.name()
    };
    
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
                return data.get(row).getName();
            case 1:
                return data.get(row).getValue();
            case 2:
                return data.get(row).getType();
            case 3:
                return data.get(row).getFormula();
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
                return String.class;
            case 1:
                return String.class;
            case 2:
                return Criterion.Types.class;
            case 3:
                return String.class;
            case 4:
                return String.class;
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        switch(col) {
            case 0: 
                return true;
            case 1:
                return false;
            case 2:
                return true;
            case 3:
                return true;
            case 4:
                return true;
            default:
                return false;
        }
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
                data.get(row).setType(Criterion.Types.valueOf((String) v));
                break;
            case 3:
                data.get(row).setFormula((String) v);
                break;
            default:
                break;
        }
        fireTableCellUpdated(row, col);
    }
}
