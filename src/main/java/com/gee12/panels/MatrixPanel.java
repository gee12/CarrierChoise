package com.gee12.panels;

import com.gee12.panels.groupTableHeader.ColumnGroup;
import com.gee12.tablemodels.MatrixTableModel;
import com.gee12.panels.groupTableHeader.GroupableTableHeader;
import com.gee12.panels.groupTableHeader.RowHeaderRenderer;
import com.gee12.panels.multiSpanCellTable.AttributiveCellTableModel;
import com.gee12.panels.multiSpanCellTable.CellSpan;
import com.gee12.panels.multiSpanCellTable.MultiSpanCellTable;
import com.gee12.other.MatrixTableListener;
import com.gee12.structures.Matrix;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Enumeration;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Иван
 */
public class MatrixPanel extends JPanel {

    public final static int CELL_WIDTH = 80;
    public final static int CELL_HEIGHT = 30;
    
//    private final MatrixTableListener listener;
    private final JTable matrixTable;
    private final MatrixTableModel matrixTM;
    
//    public MatrixPanel() {
//        this.listener = null;
//        matrixTable = new JTable();
//        matrixTM = new MatrixTableModel(null);
//    }
    
    public MatrixPanel( ) {
//            Double[] capacities, Double[] volumes) {
//        this.listener = listener;
//        if (capacities == null) {
//            capacities = new Double[3];
//        }
//        if (volumes == null) {
//            volumes = new Double[3];
//        }
        
        
        // rowHeaderModel
        Object[][] rowHeaders = new Object[][]{
            {"Грузопод-ть", "10"},
            {"", "15"},
            {"", "20"}};
        AttributiveCellTableModel rowHeaderModel = new AttributiveCellTableModel(
                rowHeaders, new Object[]{"", ""}) {
            public boolean CellEditable(int row, int col) {
                return false;
            }
        };

        CellSpan cellAtt = (CellSpan) rowHeaderModel.getCellAttribute();
        cellAtt.combine(new int[]{0, 1, 2}, new int[]{0});
        
        // rowHeaderTable
        MultiSpanCellTable rowHeaderTable = new MultiSpanCellTable(rowHeaderModel);
        rowHeaderTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        rowHeaderTable.setDefaultRenderer(Object.class, new RowHeaderRenderer(rowHeaderTable));
        rowHeaderTable.setRowHeight(CELL_HEIGHT);
        rowHeaderTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        rowHeaderTable.getColumnModel().getColumn(1).setPreferredWidth(CELL_WIDTH);
        
        // model
        String[] volumes = new String[]{"200", "300", "400"};
        matrixTM = new MatrixTableModel(volumes);
//        matrixTM.addTableModelListener(new TableModelListener() {
//            public void tableChanged(TableModelEvent e) {
//                listener.tableChanged();
//            }
//        });

        // table
        matrixTable = new JTable(matrixTM) {
            protected JTableHeader createDefaultTableHeader() {
                return new GroupableTableHeader(columnModel);
            }
            
        };
        matrixTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        matrixTable.setRowHeight(CELL_HEIGHT + matrixTable.getRowMargin());
        TableColumnModel cm = matrixTable.getColumnModel();
        cm.setColumnMargin(5);
        Enumeration cols = matrixTable.getColumnModel().getColumns();
        while (cols.hasMoreElements()) {
           TableColumn col = ((TableColumn)cols.nextElement());
           col.setPreferredWidth(CELL_WIDTH);
        }
        matrixTable.setShowGrid(true);
        matrixTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        matrixTable.setColumnSelectionAllowed(true);
        matrixTable.setRowSelectionAllowed(true);
    
        ColumnGroup groupCapacity = new ColumnGroup("Объем");
        groupCapacity.add(cm.getColumn(0));
        groupCapacity.add(cm.getColumn(1));
        groupCapacity.add(cm.getColumn(2));

        GroupableTableHeader header = (GroupableTableHeader) matrixTable.getTableHeader();
        header.addColumnGroup(groupCapacity);
        header.setPreferredSize(new Dimension(CELL_WIDTH,CELL_HEIGHT * 2));
        header.setBorder(new BevelBorder(BevelBorder.RAISED));
        
        // scroll pane
        JScrollPane scroll = new JScrollPane(matrixTable);
        JViewport viewport = new JViewport();
        viewport.setView(rowHeaderTable);
        viewport.setPreferredSize(rowHeaderTable.getPreferredSize());
        scroll.setRowHeaderView(viewport);
        scroll.setPreferredSize(new Dimension(430,156));

        add(scroll);

    }
    
    public void addTableModelListener(final MatrixTableListener listener) {
        matrixTM.addTableModelListener(new TableModelListener() {
            public void tableChanged(TableModelEvent e) {
                listener.tableChanged();
            }
        });
    }
    
    public void setMatrixTableModel(Matrix m) {
        matrixTM.setData(m);
    }
    
    public Matrix getMatrix() {
        return matrixTM.getData();
    }

}
