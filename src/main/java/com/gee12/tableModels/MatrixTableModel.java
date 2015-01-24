package com.gee12.tableModels;

/**
 *
 * @author Иван
 */
import com.gee12.structures.Matrix;
import java.util.Locale;
import javax.swing.table.AbstractTableModel;

public class MatrixTableModel extends AbstractTableModel {

    private Object[] columnNames = null;
    Matrix matrix = null;
    
    public MatrixTableModel(Object[] columnNames) {
        this.columnNames = columnNames;
        this.matrix = new Matrix();
    }
    
    public void setData(Matrix matr) {
        this.matrix = matr;
        fireTableDataChanged();
    }
    
    public Matrix getData() {
        return matrix;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    
    @Override
    public String getColumnName(int col) {
//        return String.valueOf(columnNames[col]);
//        return String.format(Locale.US, "%.2f", columnNames[col]);
        return (String)columnNames[col];
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
//        matrix.setAt(row, col, Double.parseDouble(String.valueOf(v)));
        matrix.setAt(row, col, Double.parseDouble(
                String.format(Locale.US, "%.2f", v)));
        fireTableCellUpdated(row, col);
    }
}
