package com.gee12.tablemodels;

/**
 *
 * @author Иван
 */
import com.gee12.structures.Matrix;
import com.gee12.structures.MatrixCriterion;
import java.util.Arrays;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class MatrixCriterionsTableModel extends AbstractTableModel {

    private String[] columnNames = new String[] {"Критерий","Значение","Груз-ть"};
    private List<MatrixCriterion> data = null;
    private Double[] capacities = null;
    
    public MatrixCriterionsTableModel() {
        this.data = Arrays.asList(MatrixCriterion.BASE_CRITERIONS);
    }
    
    private void resetCriterions(Matrix m) {
        for (MatrixCriterion crit : data) {
            crit.reBuild(m);
        }
    }
    
    public void setData(Matrix m, Double[] capacities) {
        resetCriterions(m);
        this.capacities = capacities;
        fireTableDataChanged();
    }
    
    public List<MatrixCriterion> getData() {
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
                return Double.class;
            case 2:
                return Double.class;
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    @Override
    public Object getValueAt(int row, int col) {
        switch(col) {
            case 0: 
                return data.get(row).getName();
            case 1:
                return data.get(row).getValue();
            case 2:
                return data.get(row).getCapacity(capacities);
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object v, int row, int col) {
    }
}
