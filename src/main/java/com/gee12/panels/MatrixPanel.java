/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gee12.panels;

import com.gee12.mSpanCellTable.AttributiveCellTableModel;
import com.gee12.mSpanCellTable.CellSpan;
import com.gee12.mSpanCellTable.MultiSpanCellTable;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.AbstractListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.ListModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Иван
 */
public class MatrixPanel extends JPanel {

    private final JTable matrixTable;
    
    public MatrixPanel() {

        Object[][] data = new Object[][]{
            {"Грузо\nподъемность", "10"},
            {"", "15"},
            {"", "20"}};

        AttributiveCellTableModel fixedModel = new AttributiveCellTableModel(data, new Object[]{"", ""}) {
            public boolean CellEditable(int row, int col) {
                return false;
            }
        };

        CellSpan cellAtt = (CellSpan) fixedModel.getCellAttribute();
        cellAtt.combine(new int[]{0, 1, 2}, new int[]{0});

        // model
        String[] columns = new String[]{"200", "300", "400"};
        MatrixTableModel model = new MatrixTableModel(columns);

        // table
        matrixTable = new JTable(model) {
            protected JTableHeader createDefaultTableHeader() {
                return new GroupableTableHeader(columnModel);
            }
        };
        matrixTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        matrixTable.setRowHeight(20+ matrixTable.getRowMargin());
        TableColumnModel cm = matrixTable.getColumnModel();
        ColumnGroup groupCapacity = new ColumnGroup("Объем");
        groupCapacity.add(cm.getColumn(0));
        groupCapacity.add(cm.getColumn(1));
        groupCapacity.add(cm.getColumn(2));

        GroupableTableHeader header = (GroupableTableHeader) matrixTable.getTableHeader();
        header.addColumnGroup(groupCapacity);
        header.setPreferredSize(new Dimension(50,40));

        // fixedTable
        MultiSpanCellTable fixedTable = new MultiSpanCellTable(fixedModel);
        fixedTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        fixedTable.setDefaultRenderer(Object.class, new RowHeaderRenderer(fixedTable));
        fixedTable.setGridColor(matrixTable.getTableHeader().getBackground());
        fixedTable.setRowHeight(20);

        JScrollPane scroll = new JScrollPane(matrixTable);
        JViewport viewport = new JViewport();
        viewport.setView(fixedTable);
        viewport.setPreferredSize(fixedTable.getPreferredSize());
        scroll.setRowHeaderView(viewport);
        scroll.setPreferredSize(new Dimension(380,108));

        add(scroll);
    }

}
