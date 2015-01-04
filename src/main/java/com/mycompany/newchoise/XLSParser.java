package com.mycompany.newchoise;


import com.mycompany.newchoise.Project.Stages;
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
    
    public static final String stage2SheetTitle = "2 этап";
    public static final String stage3SheetTitle = "3 этап";
    
    public static final String carrierTitle = "Перевозчик";
    public static final String carrierWeightTitle = "Общий объём услуг, т";
    public static final String carrierPriceTitle = "Тариф на перевозку, грн";
    
    public static final CellReference baseDataFieldsRef = new CellReference(5, 0);
    public static final CellReference otherDataFieldsRef = new CellReference(5, 3);
    public static final CellReference baseCriterionsRef = new CellReference(5, 7);
    public static final CellReference otherCriterionsRef = new CellReference(5, 9);
    
    ////////////////////////////////////////////////////////////////////////////
    public static Project readXLSProject(String fileName) {
        Carrier carrier = null;
        Stage stage2 = null, stage3 = null;
        
        try {
            FileInputStream inputFile = new FileInputStream(new File(fileName));
            HSSFWorkbook workBook = new HSSFWorkbook(inputFile);
            
            carrier = parseCarrier(workBook.getSheet(stage2SheetTitle));
            stage2 = new Stage(
                    parseCriterions(workBook.getSheet(stage2SheetTitle), baseCriterionsRef), 
                    parseCriterions(workBook.getSheet(stage2SheetTitle), otherCriterionsRef),
                    parseDataFields(workBook.getSheet(stage2SheetTitle), baseDataFieldsRef), 
                    parseDataFields(workBook.getSheet(stage2SheetTitle), otherDataFieldsRef));
            stage3 = new Stage(
                    parseCriterions(workBook.getSheet(stage3SheetTitle), baseCriterionsRef), 
                    parseCriterions(workBook.getSheet(stage3SheetTitle), otherCriterionsRef),
                    parseDataFields(workBook.getSheet(stage3SheetTitle), baseDataFieldsRef), 
                    parseDataFields(workBook.getSheet(stage3SheetTitle), otherDataFieldsRef));
            
            inputFile.close();
            workBook.close();
        } catch (IOException e) {
            onException(e);
        }
        
        return new Project(
                carrier, 
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
    public static Carrier parseCarrier(Sheet sheet) {
        if (sheet == null) return null;
        
        String name = null;
        double weight = 0, price = 0;
        
        Row titleRow = sheet.getRow(0);
        Row valuesRow = sheet.getRow(1);
        if (titleRow != null && valuesRow != null) {
            Iterator<Cell> cells = titleRow.iterator();
            while (cells.hasNext()) {
                Cell cell = cells.next();
                int cellType = cell.getCellType();
                if (cellType == Cell.CELL_TYPE_STRING) {
                    switch (cell.getStringCellValue()) {
                        case carrierTitle:
                            name = valuesRow.getCell(cell.getColumnIndex()).getStringCellValue();
                            break;
                        case carrierWeightTitle:
                            weight = valuesRow.getCell(cell.getColumnIndex()).getNumericCellValue();
                            break;
                        case carrierPriceTitle:
                            price = valuesRow.getCell(cell.getColumnIndex()).getNumericCellValue();
                            break;
                    }
                }
            }
        }
        return new Carrier(name, weight, price);
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
