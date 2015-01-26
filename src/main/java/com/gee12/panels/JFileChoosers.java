package com.gee12.panels;

import com.gee12.other.ExcelParser;
import java.awt.Component;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Иван
 */
public class JFileChoosers {
    
    public static JFileChooser chooser = new JFileChooser();
    
    ////////////////////////////////////////////////////////////////////////////
    public static int showCreateChooser(Component parent) {
        chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setApproveButtonText("Создать");
        chooser.setDialogTitle("Создание нового проекта");
        chooser.setDialogType(JFileChooser.OPEN_DIALOG);
        chooser.setMultiSelectionEnabled(false);
        return chooser.showOpenDialog(parent);
    }
    
    ////////////////////////////////////////////////////////////////////////////
    public static int showOpenChooser(Component parent) {
        chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "Таблицы MS Office (*.xls, *.xlsx)", ExcelParser.XLS_EXTENSION, ExcelParser.XLSX_EXTENSION);
        chooser.setFileFilter(filter);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setApproveButtonText("Открыть");
        chooser.setDialogTitle("Открытие нового проекта");
        chooser.setDialogType(JFileChooser.OPEN_DIALOG);
        chooser.setMultiSelectionEnabled(false);
        return chooser.showOpenDialog(parent);
    }
       
    
    ////////////////////////////////////////////////////////////////////////////
    public static int showSaveChooser(Component parent) {
        chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        chooser.setApproveButtonText("Сохранить");
        chooser.setDialogTitle("Сохранение проекта как");
        chooser.setDialogType(JFileChooser.SAVE_DIALOG);
        chooser.setMultiSelectionEnabled(false);
        return chooser.showSaveDialog(parent);
    }
     
    ////////////////////////////////////////////////////////////////////////////
    public static JFileChooser getChooser() {
        return chooser;
    }
       
}
