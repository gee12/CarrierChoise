/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gee12.groupTableHeader;

import java.awt.Color;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Иван
 */
public class MultiLineHeaderRenderer extends JList implements TableCellRenderer {

    public MultiLineHeaderRenderer() {
        setOpaque(true);
        setForeground(UIManager.getColor("TableHeader.foreground"));
        setBackground(UIManager.getColor("TableHeader.background"));
//        setBorder(UIManager.getBorder("TableHeader.cellBorder"));
         setBorder(new BevelBorder(BevelBorder.RAISED));
        ListCellRenderer renderer = getCellRenderer();
        ((JLabel) renderer).setHorizontalAlignment(JLabel.CENTER);
        setCellRenderer(renderer);
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        setFont(table.getFont());
        String str = (value == null) ? "" : value.toString();
        BufferedReader br = new BufferedReader(new StringReader(str));
        String line;
        Vector v = new Vector();
        try {
            while ((line = br.readLine()) != null) {
                v.addElement(line);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        setListData(v);
        return this;
    }
}
