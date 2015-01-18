package com.gee12.other;

import javax.swing.JTable;

/**
 *
 * @author Иван
 */
public interface DeleteRowListener {
    public abstract void deleteRow(JTable table, int row);
}
