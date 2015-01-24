/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gee12.other;

import com.gee12.panels.MainFrame;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.JOptionPane;

/**
 *
 * @author Иван
 */
public class Utils {
    
 
    ////////////////////////////////////////////////////////////////////////////
    public static void onException(Exception ex) {
        ex.printStackTrace();
        showErrorDialog(ex.getMessage());
    }
    
    ////////////////////////////////////////////////////////////////////////////
    public static void showErrorDialog(String mes) {
        JOptionPane.showMessageDialog(MainFrame.getFrames()[0], mes, "Ошибка", JOptionPane.ERROR_MESSAGE);
    }
    
    ////////////////////////////////////////////////////////////////////////////
    // get file name without path & extension
    public static String withOutExtAndPath(String fullFileName) {
	Path path = Paths.get(fullFileName);
	String withoutPath = path.getFileName().toString();
	return withoutPath.replaceFirst("[.][^.]+$", "");
    }
    
    // get file name without extension
    public static String withOutExt(String fileName) {
	return fileName.replaceFirst("[.][^.]+$", "");
    }
    
    // get file name with .xls extension
    public static String withExt(String fileName, String ext) {
        return fileName.replaceFirst("[.][^.]+$", ext);
    }
    
    public static void copyFile(String src, String dest) throws IOException {
        Files.copy(Paths.get(src), Paths.get(dest), StandardCopyOption.REPLACE_EXISTING);
    }
}
