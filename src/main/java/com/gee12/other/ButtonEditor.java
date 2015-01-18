package com.gee12.other;

import com.gee12.panels.MainFrame;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author Иван
 */
public class ButtonEditor extends DefaultCellEditor {

    protected JButton button;
//    private String label;
    private boolean isPushed;
    private DeleteRowListener listener;
    private int curRow;
    private JTable table;

    public ButtonEditor(JCheckBox checkBox, DeleteRowListener listener) {
        super(checkBox);
        this.listener = listener;
        
        button = new JButton();
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setOpaque(false);
        button.setIcon(new ImageIcon(MainFrame.class.getResource("/images/delete.jpg")));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
//        if (isSelected) {
//            button.setForeground(table.getSelectionForeground());
//            button.setBackground(table.getSelectionBackground());
//        } else {
//            button.setForeground(table.getForeground());
//            button.setBackground(table.getBackground());
//        }
        this.curRow = row;
        this.table = table;
        button.setBorder(BorderFactory.createEmptyBorder());
//        label = (value == null) ? "" : value.toString();
//        button.setText(label);
        isPushed = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed) {
            int res = JOptionPane.showConfirmDialog(null, "Удалить строку?", "Удаление", 
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (res == JOptionPane.YES_OPTION) {
                listener.deleteRow(table, curRow);
            }
        }
        isPushed = false;
        return "";
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
