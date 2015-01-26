package com.gee12.other;

import com.gee12.panels.MainFrame;
import java.awt.Frame;
import java.awt.Window;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

/**
 *
 * @author Иван
 */
public class Utils {
    
    ////////////////////////////////////////////////////////////////////////////
    public static String[] getNames(Class<? extends Enum<?>> e) {
        return Arrays.toString(e.getEnumConstants()).replaceAll("^.|.$", "").split(", ");
    }
 
    ////////////////////////////////////////////////////////////////////////////
    public static void onException(Exception ex) {
        ex.printStackTrace();
        showErrorDialog(ex.getMessage());
    }
    
    ////////////////////////////////////////////////////////////////////////////
    public static void showErrorDialog(String mes) {
//        Frame activeFrame = null;
//        for(Frame frame : MainFrame.getFrames()) {
//            if (frame.isActive()) {
//                activeFrame = frame;
//                break;
//            }
//        }
//        JOptionPane.showMessageDialog(activeFrame, mes, "Ошибка", JOptionPane.ERROR_MESSAGE);
        JOptionPane.showMessageDialog(null, mes, "Ошибка", JOptionPane.ERROR_MESSAGE);
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
        return fileName.replaceFirst("[.][^.]+$", "") + ext;
    }
     
    // get file extension
    public static String getExt(String fileName) {
        int lastIndexOf = fileName.lastIndexOf(".");
        return (lastIndexOf != -1) ? fileName.substring(lastIndexOf + 1) : "";
    }
    
    public static void copyFile(String src, String dest) throws IOException {
        Files.copy(Paths.get(src), Paths.get(dest), StandardCopyOption.REPLACE_EXISTING);
    }
}
