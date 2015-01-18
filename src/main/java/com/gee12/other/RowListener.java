package com.gee12.other;

import javax.swing.JTable;

/**
 *
 * @author Иван
 */
public interface RowListener {
    
//    public enum Types {
//        EDIT,
//        DELETE
//    }
    
//    private int editCol;
//    private int deleteCol;
//    
//    public static void setColumns(int editCol, int deleteCol) {
//        this.editCol = editCol;
//        this.deleteCol = deleteCol;
//    }
    
    public abstract Object editRow(JTable table, int row);
    public abstract void deleteRow(JTable table, int row);
//    public abstract void doSomethingWithRow(JTable table, int row);

//    public int getEditCol() {
//        return editCol;
//    }
//
//    public int getDeleteCol() {
//        return deleteCol;
//    }
    
}
