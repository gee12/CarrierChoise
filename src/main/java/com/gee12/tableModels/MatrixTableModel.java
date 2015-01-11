package com.gee12.tableModels;

/**
 *
 * @author Иван
 */
import com.gee12.structures.Matrix;
import javax.swing.table.AbstractTableModel;

public class MatrixTableModel extends AbstractTableModel {

    private String[] columnNames = null;
    Matrix matrix = null;
    
    public MatrixTableModel(String[] columnNames) {
        this.columnNames = columnNames;
        this.matrix = new Matrix();
    }
    
    public void setData(Matrix matr) {
        this.matrix = matr;
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
        return matrix.getRows();
    }

    @Override
    public Object getValueAt(int row, int col) {
        return matrix.getAt(row, col);
    }

    @Override
    public Class getColumnClass(int col) {
        return Double.class;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return true;
    }

    @Override
    public void setValueAt(Object v, int row, int col) {
        matrix.setAt(row, col, Double.parseDouble(String.valueOf(v)));
        fireTableCellUpdated(row, col);
    }
}
