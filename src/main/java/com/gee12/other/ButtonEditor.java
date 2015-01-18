package com.gee12.other;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

/**
 *
 * @author Иван
 */
public class ButtonEditor extends DefaultCellEditor {

    protected JButton button;
//    private String value;
    private boolean isPushed;
    private RowListener listener;
    private int curRow;
    private int curCol;
    private JTable table;
    private int editCol;
    private int deleteCol;
    
    public ButtonEditor(JCheckBox checkBox, RowListener listener,
            int editCol, int deleteCol) {
        super(checkBox);
        this.listener = listener;
        this.editCol = editCol;
        this.deleteCol = deleteCol;
        
        button = new JButton();
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setOpaque(false);
//        button.setIcon(new ImageIcon(MainFrame.class.getResource("/images/delete.jpg")));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object v,
            boolean isSelected, int row, int col) {
//        if (isSelected) {
//            button.setForeground(table.getSelectionForeground());
//            button.setBackground(table.getSelectionBackground());
//        } else {
//            button.setForeground(table.getForeground());
//            button.setBackground(table.getBackground());
//        }
        this.curRow = row;
        this.curCol = col;
        this.table = table;
        button.setBorder(BorderFactory.createEmptyBorder());
//        this.value = (v == null) ? "" : v.toString();
//        button.setText(label);
        isPushed = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        Object res = null;
        if (isPushed) {
            if (curCol == editCol) {
                res = listener.editRow(table, curRow);
            }
            else if (curCol == deleteCol) {
                listener.deleteRow(table, curRow);
            }
        }
        isPushed = false;
//        return value;
        return res;
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }

    @Override
    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}
