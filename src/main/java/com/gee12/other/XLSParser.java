package com.gee12.other;


import com.gee12.structures.Carrier;
import com.gee12.structures.Criterion;
import com.gee12.structures.DataField;
import com.gee12.structures.Matrix;
import com.gee12.structures.Project;
import com.gee12.structures.Project.Stages;
import com.gee12.structures.Stage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellReference;

/**
 *
 * @author Иван
 */
public class XLSParser {
    
    public static final String stage1SheetTitle = "1 этап";
    public static final String stage2SheetTitle = "2 этап";
    public static final String stage3SheetTitle = "3 этап";
    
    public static final CellReference carrierNameRef = new CellReference(0, 1);
    public static final CellReference carrierCapacityRef = new CellReference(6, 1);
    public static final CellReference carrierRepeatRef = new CellReference(6, 2);
    public static final CellReference carrierMatrixRef = new CellReference(3, 1);
    public static final int CARRIER_ROWS_NUM = 8;
    
    public static final CellReference baseDataFieldsRef = new CellReference(5, 0);
    public static final CellReference otherDataFieldsRef = new CellReference(5, 3);
    public static final CellReference baseCriterionsRef = new CellReference(5, 7);
    public static final CellReference otherCriterionsRef = new CellReference(5, 9);
    
    ////////////////////////////////////////////////////////////////////////////
    public static Project readXLSProject(String fileName) {
        
        List<Carrier> carriers = null;
        
        Carrier carrier = null;
        Stage stage2 = null, stage3 = null;
        
        try {
            FileInputStream inputFile = new FileInputStream(new File(fileName));
            HSSFWorkbook workBook = new HSSFWorkbook(inputFile);
            
            //
            carriers = readCarriers(workBook.getSheet(stage1SheetTitle));
//            
//            //
//            stage2 = new Stage(
//                    parseCriterions(workBook.getSheet(stage2SheetTitle), baseCriterionsRef), 
//                    parseCriterions(workBook.getSheet(stage2SheetTitle), otherCriterionsRef),
//                    parseDataFields(workBook.getSheet(stage2SheetTitle), baseDataFieldsRef), 
//                    parseDataFields(workBook.getSheet(stage2SheetTitle), otherDataFieldsRef));
//            stage3 = new Stage(
//                    parseCriterions(workBook.getSheet(stage3SheetTitle), baseCriterionsRef), 
//                    parseCriterions(workBook.getSheet(stage3SheetTitle), otherCriterionsRef),
//                    parseDataFields(workBook.getSheet(stage3SheetTitle), baseDataFieldsRef), 
//                    parseDataFields(workBook.getSheet(stage3SheetTitle), otherDataFieldsRef));
            
            inputFile.close();
            workBook.close();
        } catch (IOException e) {
            onException(e);
        }
        
        return new Project(
                carriers, 
                new Stage[] {stage2, stage3}
        );
    }
    
    ////////////////////////////////////////////////////////////////////////////
    public static List<DataField> parseDataFields(Sheet sheet, CellReference ref) {
        if (sheet == null || ref == null) return null;
        
        List<DataField> res = new ArrayList<>();
        
        Iterator<Row> it = sheet.iterator();
        for (int i = 0; i < ref.getRow() && it.hasNext(); i++) {
            it.next();
        }
        
        while (it.hasNext()) {
            Row row = it.next();

            String name = null;
//            String value = null;

            if (row.getLastCellNum() < ref.getCol()) {
                continue;
            }
            // name
            Cell nameCell = row.getCell(ref.getCol());

            if (nameCell != null) {
                if (nameCell.getCellType() != Cell.CELL_TYPE_STRING) {
                    continue;
                }
                name = nameCell.getStringCellValue();

                if (name.equalsIgnoreCase("")) {
                    continue;
                }
                // value
                Cell valueCell = row.getCell(ref.getCol() + 1);

//                if (valueCell != null) {
//                    int cellType = valueCell.getCellType();
//                    if (cellType == Cell.CELL_TYPE_NUMERIC) {
//                        value = Double.toString(valueCell.getNumericCellValue());
//                    } else if (cellType == Cell.CELL_TYPE_STRING) {
//                        value = valueCell.getStringCellValue();
//                        if (value.equalsIgnoreCase("-")) {
//                            continue;
//                        }
//                    } else {
//                        value = "";
//                    }
//                }

                res.add(new DataField(nameCell, valueCell));
            }
        }
        return res;
    }
    
    ////////////////////////////////////////////////////////////////////////////
    public static List<Carrier> readCarriers(Sheet sheet) {
        if (sheet == null) return null;
        
        List<Carrier> res = new ArrayList<>();
        for (int shift = 0; (shift + CARRIER_ROWS_NUM) < sheet.getLastRowNum(); shift += CARRIER_ROWS_NUM) {
            Carrier carrier = parseCarrier(sheet, shift);
            res.add(carrier);
        }
        
        return res;
    }
    
    ////////////////////////////////////////////////////////////////////////////
    public static Carrier parseCarrier(Sheet sheet, int shift) {
        if (sheet == null) return null;
        
        Cell cell;
        int cellType;
        
        String name = null;
        cell = sheet.getRow(carrierNameRef.getRow() + shift)
                .getCell(carrierNameRef.getCol());
        cellType = cell.getCellType();
        if (cell != null || cellType == Cell.CELL_TYPE_STRING) {
            name = cell.getStringCellValue();
        }
        
        double capacity = 0.0;
        cell = sheet.getRow(carrierCapacityRef.getRow() + shift)
                .getCell(carrierCapacityRef.getCol());
        cellType = cell.getCellType();
        if (cell != null || cellType == Cell.CELL_TYPE_FORMULA) {
            capacity = cell.getNumericCellValue();
        }
        
        int repeatNum = 0;
        cell = sheet.getRow(carrierRepeatRef.getRow() + shift)
                .getCell(carrierRepeatRef.getCol());
        cellType = cell.getCellType();
        if (cell != null || cellType == Cell.CELL_TYPE_FORMULA) {
            repeatNum = (int)cell.getNumericCellValue();
        }
        
        Matrix matrix = new Matrix();
        for(int i = 0; i < Matrix.DEF_SIZE; i++) {
            for(int j = 0; j < Matrix.DEF_SIZE; j++) {
                cell = sheet.getRow(carrierMatrixRef.getRow() + shift + i)
                        .getCell(carrierMatrixRef.getCol() + j);
                cellType = cell.getCellType();                
                if (cell != null/* || cellType == Cell.CELL_TYPE_FORMULA*/) {
                    matrix.setAt(i, j, cell.getNumericCellValue());
                }
            }
        }
        
        return new Carrier(name, capacity, repeatNum, matrix);
        
//        Row titleRow = sheet.getRow(0);
//        Row valuesRow = sheet.getRow(1);
//        if (titleRow != null && valuesRow != null) {
//            Iterator<Cell> cells = titleRow.iterator();
//            while (cells.hasNext()) {
//                Cell cell = cells.next();
//                int cellType = cell.getCellType();
//                if (cellType == Cell.CELL_TYPE_STRING) {
//                    switch (cell.getStringCellValue()) {
//                        case carrierTitle:
//                            name = valuesRow.getCell(cell.getColumnIndex()).getStringCellValue();
//                            break;
//                        case carrierWeightTitle:
//                            weight = valuesRow.getCell(cell.getColumnIndex()).getNumericCellValue();
//                            break;
//                        case carrierPriceTitle:
//                            price = valuesRow.getCell(cell.getColumnIndex()).getNumericCellValue();
//                            break;
//                    }
//                }
//            }
//        }
//        return new Carrier(name, weight, price);
    }
    
    ////////////////////////////////////////////////////////////////////////////
    public static List<Criterion> parseCriterions(Sheet sheet, CellReference ref) {
        if (sheet == null && ref == null) return null;
        
        List<Criterion> res = new ArrayList<>();
        
        Iterator<Row> it = sheet.iterator();
        for (int i = 0; i < ref.getRow() && it.hasNext(); i++) {
            it.next();
        }
        
        while (it.hasNext()) {
            Row row = it.next();
            Cell nameCell = row.getCell(ref.getCol());
            Cell valueCell = row.getCell(ref.getCol() + 1);
            if (nameCell != null && valueCell != null && valueCell.getCellType() == Cell.CELL_TYPE_FORMULA) {
                String name = nameCell.getStringCellValue();
                String formula = valueCell.getCellFormula();
                String value = String.format(Locale.US, "%.2f", valueCell.getNumericCellValue());
                if (name.equalsIgnoreCase("") && formula.equalsIgnoreCase("") && value.equalsIgnoreCase(""))
                    continue;
                res.add(new Criterion(name, formula, value, nameCell, valueCell));
            }
        }
        return res;
    }
    
    ////////////////////////////////////////////////////////////////////////////
    public static String parseCell(Cell cell) {
        if (cell == null) return null;
        int cellType = cell.getCellType();
        switch (cellType) {
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();
            case Cell.CELL_TYPE_NUMERIC:
                return String.format(Locale.US, "%.2f", cell.getNumericCellValue());
            case Cell.CELL_TYPE_BOOLEAN:
                return Boolean.toString(cell.getBooleanCellValue());
            case Cell.CELL_TYPE_ERROR:
                return String.format("Ошибка '%s'", cell.getErrorCellValue());
            case Cell.CELL_TYPE_FORMULA:
//                return String.format("%.2f", cell.getNumericCellValue());
                return cell.getCellFormula();
            default:
                return "";
        }
    }
    
    ////////////////////////////////////////////////////////////////////////////
    public static void saveXLSProject(String fileName, Project proj) {
        try {
            FileInputStream inputFile = new FileInputStream(new File(fileName));
            HSSFWorkbook workBook = new HSSFWorkbook(inputFile);
            
            saveXLSProjectData(workBook.getSheet(stage2SheetTitle), 
                    proj.getStage(Project.Stages.STAGE2_COOPERATION).getBaseDataFileds());
            saveXLSProjectData(workBook.getSheet(stage2SheetTitle), 
                    proj.getStage(Project.Stages.STAGE2_COOPERATION).getOtherDataFileds());
            saveXLSProjectData(workBook.getSheet(stage3SheetTitle), 
                    proj.getStage(Project.Stages.STAGE3_RATING).getBaseDataFileds());
            saveXLSProjectData(workBook.getSheet(stage3SheetTitle), 
                    proj.getStage(Project.Stages.STAGE3_RATING).getOtherDataFileds());
            
            workBook.getCreationHelper().createFormulaEvaluator().evaluateAll();
            
            inputFile.close();
            
            FileOutputStream outputFile = new FileOutputStream(new File(fileName));
            workBook.write(outputFile);
            outputFile.close();
            
            workBook.close();

        } catch (IOException e) {
             onException(e);
        }
    }
    
    ////////////////////////////////////////////////////////////////////////////
    public static void saveXLSProjectData(Sheet sheet, List<DataField> dataFields) {
        if (sheet == null) return;
        
        for (DataField dataField : dataFields) {
            
            Cell nameCell = sheet.getRow(dataField.getNameRow()).getCell(dataField.getNameCol());
            nameCell.setCellType(Cell.CELL_TYPE_STRING);
            nameCell.setCellValue(dataField.getName());
            
            Cell valueCell = sheet.getRow(dataField.getValueRow()).getCell(dataField.getValueCol());
            valueCell.setCellType(Cell.CELL_TYPE_STRING);
            try {
                double d = Double.parseDouble(dataField.getValue());
                valueCell.setCellValue(d);
            } catch (Exception ex) {
                valueCell.setCellValue(dataField.getValue());
            }
        }
    }
    
    ////////////////////////////////////////////////////////////////////////////
    public static void saveXLSCriterion(String fileName, Criterion crit, Stages stage) {
        try {
            FileInputStream inputFile = new FileInputStream(new File(fileName));
            HSSFWorkbook workBook = new HSSFWorkbook(inputFile);
            
            String sheetTitle = (stage == Stages.STAGE2_COOPERATION) ? stage2SheetTitle : stage3SheetTitle;
            
            Sheet sheet = workBook.getSheet(sheetTitle);
            Cell nameCell = sheet.getRow(crit.getNameRow()).createCell(crit.getNameCol());
            nameCell.setCellType(Cell.CELL_TYPE_STRING);
            nameCell.setCellValue(crit.getName());
            
            Cell valueCell = sheet.getRow(crit.getValueRow()).createCell(crit.getValueCol());
            String formula = crit.getFormula();
            if (formula.equalsIgnoreCase("")) {
                valueCell.setCellType(Cell.CELL_TYPE_STRING);
                valueCell.setCellValue("");
            } else {
                valueCell.setCellType(Cell.CELL_TYPE_FORMULA);
                valueCell.setCellFormula(crit.getFormula());
            }
            
            workBook.getCreationHelper().createFormulaEvaluator().evaluateAll();
            inputFile.close();
            
            FileOutputStream outputFile = new FileOutputStream(new File(fileName));
            workBook.write(outputFile);
            outputFile.close();
            
            workBook.close();

        } catch (IOException e) {
            onException(e);
        }
    }
    
    ////////////////////////////////////////////////////////////////////////////
    public static void saveXLSDataField(String fileName, DataField dataField, Stages stage) {
        try {
            FileInputStream inputFile = new FileInputStream(new File(fileName));
            HSSFWorkbook workBook = new HSSFWorkbook(inputFile);
            
            String sheetTitle = (stage == Stages.STAGE2_COOPERATION) ? stage2SheetTitle : stage3SheetTitle;
            
            Sheet sheet = workBook.getSheet(sheetTitle);
            Cell nameCell = sheet.getRow(dataField.getNameRow()).createCell(dataField.getNameCol());
            nameCell.setCellType(Cell.CELL_TYPE_STRING);
            nameCell.setCellValue(dataField.getName());
            
            Cell valueCell = sheet.getRow(dataField.getValueRow()).createCell(dataField.getValueCol());
            
            try {
                double d = Double.parseDouble(dataField.getValue());
                valueCell.setCellType(Cell.CELL_TYPE_NUMERIC);
                valueCell.setCellValue(d);
            } catch (Exception ex) {
                valueCell.setCellType(Cell.CELL_TYPE_STRING);
                valueCell.setCellValue(dataField.getValue());
            }
            
            workBook.getCreationHelper().createFormulaEvaluator().evaluateAll();
            inputFile.close();
            
            FileOutputStream outputFile = new FileOutputStream(new File(fileName));
            workBook.write(outputFile);
            outputFile.close();
            
            workBook.close();

        } catch (IOException e) {
            onException(e);
        }
    }
    
    ////////////////////////////////////////////////////////////////////////////
    public static void onException(Exception ex) {
        ex.printStackTrace();
        showErrorDialog(ex.getMessage());
    }
    
    ////////////////////////////////////////////////////////////////////////////
    public static void showErrorDialog(String mes) {
        JOptionPane.showMessageDialog(null, mes, "Ошибка", JOptionPane.ERROR_MESSAGE);
    }
}
