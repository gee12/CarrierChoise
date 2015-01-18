package com.gee12.other;


import com.gee12.panels.MainFrame;
import com.gee12.structures.Carrier;
import com.gee12.structures.Criterion;
import com.gee12.structures.DataField;
import com.gee12.structures.Matrix;
import com.gee12.structures.Project;
import com.gee12.structures.Project.Stages;
import com.gee12.structures.Stage;
import java.io.File;
import java.io.FileInputStream;
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
    public static final String resultsSheetTitle = "Результат";
    
    public static final CellReference carrierNameRef = new CellReference(0, 1);
    public static final CellReference carrierCapacityRef = new CellReference(6, 1);
    public static final CellReference carrierRepeatRef = new CellReference(6, 2);
    public static final CellReference carrierMatrixRef = new CellReference(3, 1);
    public static final int CARRIER_ROWS_NUM = 8;
    
    public static final CellReference curCarrierRef = new CellReference(1, 0);
//    public static final CellReference resultsRef = new CellReference(3, 2);
    
    public static final CellReference baseDataFieldsRef = new CellReference(5, 0);
    public static final CellReference otherDataFieldsRef = new CellReference(5, 3);
    public static final CellReference baseCriterionsRef = new CellReference(5, 7);
    public static final CellReference otherCriterionsRef = new CellReference(5, 9);
    
    ////////////////////////////////////////////////////////////////////////////
    public static Project readXLSProject(String fileName) {
        
        List<Carrier> carriers = null;
        
        String curCarrierName = null;
        Stage stage2 = null, stage3 = null;
        double[] results = null;
        
        try {
            FileInputStream inputFile = new FileInputStream(new File(fileName));
            HSSFWorkbook workBook = new HSSFWorkbook(inputFile);
            
            // carriers
            carriers = parseCarriers(workBook.getSheet(stage1SheetTitle));
            curCarrierName = parseCurrentCarrierName(workBook.getSheet(stage2SheetTitle));
            
            // stage 2
            List<DataField> dataFields2 = parseDataFields(workBook.getSheet(stage2SheetTitle), baseDataFieldsRef, DataField.Types.BASE); 
            dataFields2.addAll(parseDataFields(workBook.getSheet(stage2SheetTitle), otherDataFieldsRef, DataField.Types.OTHER));
           
            List<Criterion> criterions2 = parseCriterions(workBook.getSheet(stage2SheetTitle), baseCriterionsRef, Criterion.Types.BASE); 
            criterions2.addAll(parseCriterions(workBook.getSheet(stage2SheetTitle), otherCriterionsRef, Criterion.Types.OTHER));
            stage2 = new Stage(dataFields2, criterions2);
            
            // stage 3
            List<DataField> dataFields3 = parseDataFields(workBook.getSheet(stage3SheetTitle), baseDataFieldsRef, DataField.Types.BASE); 
            dataFields3.addAll(parseDataFields(workBook.getSheet(stage3SheetTitle), otherDataFieldsRef, DataField.Types.OTHER));
           
            List<Criterion> criterions3 = parseCriterions(workBook.getSheet(stage3SheetTitle), baseCriterionsRef, Criterion.Types.BASE); 
            criterions3.addAll(parseCriterions(workBook.getSheet(stage3SheetTitle), otherCriterionsRef, Criterion.Types.OTHER));
            stage3 = new Stage(dataFields3, criterions3);  
            
            // results
//            results = parseResults(workBook.getSheet(resultsSheetTitle), resultsRef);
            
            inputFile.close();
            workBook.close();
        } catch (IOException e) {
            onException(e);
        }
        
        Project res = new Project(carriers, 
                new Stage[] {stage2, stage3});
        res.setCurrentCarrier(curCarrierName);
        if (results != null && results.length >= 3) {
            res.setGeneralMark(results[0]);
            res.setMaxMark(results[1]);
            res.setDeviation(results[2]);
        }
        return res;
    }
    
    ////////////////////////////////////////////////////////////////////////////
    public static List<DataField> readXLSDataFields(String fileName, Project.Stages stage) {
        List<DataField> res = null;
        
        try {
            FileInputStream inputFile = new FileInputStream(new File(fileName));
            HSSFWorkbook workBook = new HSSFWorkbook(inputFile);
            
            String sheetName = (stage == Stages.STAGE2_COOPERATION) ? stage2SheetTitle :
                    (stage == Stages.STAGE3_RATING) ? stage3SheetTitle : "";
            res = parseDataFields(workBook.getSheet(sheetName), baseDataFieldsRef, DataField.Types.BASE); 
            res.addAll(parseDataFields(workBook.getSheet(sheetName), otherDataFieldsRef, DataField.Types.OTHER));
             
            inputFile.close();
            workBook.close();
        } catch (IOException e) {
            onException(e);
        }
        
        return res;
    }
    
    ////////////////////////////////////////////////////////////////////////////
    public static List<Criterion> readXLSCriterions(String fileName, Project.Stages stage) {
        List<Criterion> res = null;
        
        try {
            FileInputStream inputFile = new FileInputStream(new File(fileName));
            HSSFWorkbook workBook = new HSSFWorkbook(inputFile);
            
            String sheetName = (stage == Stages.STAGE2_COOPERATION) ? stage2SheetTitle :
                    (stage == Stages.STAGE3_RATING) ? stage3SheetTitle : "";
            res = parseCriterions(workBook.getSheet(sheetName), baseDataFieldsRef, Criterion.Types.BASE); 
            res.addAll(parseCriterions(workBook.getSheet(sheetName), otherDataFieldsRef, Criterion.Types.OTHER));
             
            inputFile.close();
            workBook.close();
        } catch (IOException e) {
            onException(e);
        }
        
        return res;
    }
    
    ////////////////////////////////////////////////////////////////////////////
    public static List<Carrier> readXLSCarriers(String fileName) {
        List<Carrier> res = null;
        
        try {
            FileInputStream inputFile = new FileInputStream(new File(fileName));
            HSSFWorkbook workBook = new HSSFWorkbook(inputFile);
            
            res = parseCarriers(workBook.getSheet(stage1SheetTitle));
            
            inputFile.close();
            workBook.close();
        } catch (IOException e) {
            onException(e);
        }
        
        return res;
    }    
    
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    // PARSE
    public static List<DataField> parseDataFields(Sheet sheet, CellReference ref, DataField.Types type) {
        if (sheet == null || ref == null) return null;
        
        List<DataField> res = new ArrayList<>();
        
        Iterator<Row> it = sheet.iterator();
        for (int i = 0; i < ref.getRow() && it.hasNext(); i++) {
            it.next();
        }
        
        while (it.hasNext()) {
            Row row = it.next();
            String name = null;

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
                res.add(new DataField(nameCell, valueCell, type));
            }
        }
        return res;
    }
    
        ////////////////////////////////////////////////////////////////////////////
//    public static double[] parseResults(Sheet sheet, CellReference ref) {
//        if (sheet == null || ref == null) return null;
//        
//        double[] res = new double[3];
//        Cell cell;
//        int cellType;
//        
//        // 0 - общая оценка
//        cell = sheet.getRow(resultsRef.getRow())
//                .getCell(resultsRef.getCol());
//        cellType = cell.getCellType();
//        if (cell != null || cellType == Cell.CELL_TYPE_FORMULA &&
//                ) {
//            res[0] = cell.getNumericCellValue();
//        }  
//        // 1 - эталонная оценка
//        cell = sheet.getRow(resultsRef.getRow() + 1)
//                .getCell(resultsRef.getCol());
//        cellType = cell.getCellType();
//        if (cell != null || cellType == Cell.CELL_TYPE_FORMULA) {
//            res[1] = cell.getNumericCellValue();
//        }
//        // 2 - отклонение
//        cell = sheet.getRow(resultsRef.getRow() + 2)
//                .getCell(resultsRef.getCol());
//        cellType = cell.getCellType();
//        if (cell != null || cellType == Cell.CELL_TYPE_FORMULA) {
//            res[2] = cell.getNumericCellValue();
//        } 
//        
//        return res;
//    }
    
    ////////////////////////////////////////////////////////////////////////////
    public static String parseCurrentCarrierName(Sheet sheet) {
        if (sheet == null) return null;
        
        String res = null;
        Cell cell = sheet.getRow(curCarrierRef.getRow())
                .getCell(curCarrierRef.getCol());
        int cellType = cell.getCellType();
        if (cell != null || cellType == Cell.CELL_TYPE_STRING) {
            res = cell.getStringCellValue();
        }    
        
        return res;
    }
    
    ////////////////////////////////////////////////////////////////////////////
    public static List<Carrier> parseCarriers(Sheet sheet) {
        if (sheet == null) return null;
        
        List<Carrier> res = new ArrayList<>();
        for (int id = 0; ((id + 1) * CARRIER_ROWS_NUM) < sheet.getLastRowNum(); id++) {
            Carrier carrier = parseCarrier(sheet, id);
            res.add(carrier);
        }
        
        return res;
    }
    
    ////////////////////////////////////////////////////////////////////////////
    public static Carrier parseCarrier(Sheet sheet, int id) {
        if (sheet == null) return null;
        
        Cell cell;
        int cellType;
        
        String name = null;
        cell = sheet.getRow(carrierNameRef.getRow() + id * CARRIER_ROWS_NUM)
                .getCell(carrierNameRef.getCol());
        cellType = cell.getCellType();
        if (cell != null || cellType == Cell.CELL_TYPE_STRING) {
            name = cell.getStringCellValue();
        }
        
        double capacity = 0.0;
        cell = sheet.getRow(carrierCapacityRef.getRow() + id * CARRIER_ROWS_NUM)
                .getCell(carrierCapacityRef.getCol());
        cellType = cell.getCellType();
        if (cell != null || cellType == Cell.CELL_TYPE_FORMULA) {
            capacity = cell.getNumericCellValue();
        }
        
        int repeatNum = 0;
        cell = sheet.getRow(carrierRepeatRef.getRow() + id * CARRIER_ROWS_NUM)
                .getCell(carrierRepeatRef.getCol());
        cellType = cell.getCellType();
        if (cell != null || cellType == Cell.CELL_TYPE_FORMULA) {
            repeatNum = (int)cell.getNumericCellValue();
        }
        
        Matrix matrix = new Matrix();
        for(int i = 0; i < Matrix.DEF_SIZE; i++) {
            for(int j = 0; j < Matrix.DEF_SIZE; j++) {
                cell = sheet.getRow(carrierMatrixRef.getRow() + id * CARRIER_ROWS_NUM + i)
                        .getCell(carrierMatrixRef.getCol() + j);
                cellType = cell.getCellType();                
                if (cell != null || cellType == Cell.CELL_TYPE_FORMULA) {
                    matrix.setAt(i, j, cell.getNumericCellValue());
                }
            }
        }
        
        return new Carrier(id, name, capacity, repeatNum, matrix);
    }
    
    ////////////////////////////////////////////////////////////////////////////
    public static List<Criterion> parseCriterions(Sheet sheet, CellReference ref, Criterion.Types type) {
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
            Cell maxCell = row.getCell(ref.getCol() + 2);
            
            if (nameCell != null && valueCell != null && valueCell.getCellType() == Cell.CELL_TYPE_FORMULA) {
                String name = nameCell.getStringCellValue();
                String formula = valueCell.getCellFormula();
                String value = "";
                // if formula not error => parse numeric value
                if (valueCell.getCachedFormulaResultType() != Cell.CELL_TYPE_ERROR) {
                    value = String.format(Locale.US, "%.2f", valueCell.getNumericCellValue());
                } else {
                    value = String.format(Locale.US, "Error: %d", valueCell.getErrorCellValue());
                }
                String max = "";
                if (maxCell.getCellType() == Cell.CELL_TYPE_STRING) {
                    max = maxCell.getStringCellValue();
                } else {
                    max = String.valueOf(maxCell.getNumericCellValue());
                }
                
                if (name.isEmpty() && formula.isEmpty() 
                        && value.isEmpty() && max.isEmpty())
                    continue;
                res.add(new Criterion(name, formula, value, max, nameCell, valueCell, type));
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
                // if formula not error => parse numeric value
                if (cell.getCachedFormulaResultType() != Cell.CELL_TYPE_ERROR) {
                    return String.format(Locale.US, "%.2f", cell.getNumericCellValue());
                } else {
//                    return String.format(Locale.US, "Error: %d", cell.getErrorCellValue());
                    return cell.getCellFormula();
                }
            default:
                return "";
        }
    }    
    
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    // SAVE
    public static void saveXLSProject(String fileName, Project proj) {
        try {
            FileInputStream inputFile = new FileInputStream(new File(fileName));
            HSSFWorkbook workBook = new HSSFWorkbook(inputFile);
            
//            saveXLSProjectData(workBook.getSheet(stage2SheetTitle), 
//                    proj.getStage(Project.Stages.STAGE2_COOPERATION).getBaseDataFileds());
//            saveXLSProjectData(workBook.getSheet(stage2SheetTitle), 
//                    proj.getStage(Project.Stages.STAGE2_COOPERATION).getOtherDataFileds());
//            saveXLSProjectData(workBook.getSheet(stage3SheetTitle), 
//                    proj.getStage(Project.Stages.STAGE3_RATING).getBaseDataFileds());
//            saveXLSProjectData(workBook.getSheet(stage3SheetTitle), 
//                    proj.getStage(Project.Stages.STAGE3_RATING).getOtherDataFileds());
            saveXLSProjectData(workBook.getSheet(stage2SheetTitle), 
                    proj.getStage(Project.Stages.STAGE2_COOPERATION).getDataFields());
            saveXLSProjectData(workBook.getSheet(stage3SheetTitle), 
                    proj.getStage(Project.Stages.STAGE3_RATING).getDataFields());
            
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
    public static void saveXLSProjectData(String fileName, Project proj) {
        try {
            FileInputStream inputFile = new FileInputStream(new File(fileName));
            HSSFWorkbook workBook = new HSSFWorkbook(inputFile);
            
            saveXLSProjectData(workBook.getSheet(stage2SheetTitle), 
                    proj.getStage(Project.Stages.STAGE2_COOPERATION).getDataFields());
            saveXLSProjectData(workBook.getSheet(stage3SheetTitle), 
                    proj.getStage(Project.Stages.STAGE3_RATING).getDataFields());
            
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
    public static void saveXLSProjectData(String fileName, Stages stage, List<DataField> dataFields) {
        try {
            FileInputStream inputFile = new FileInputStream(new File(fileName));
            HSSFWorkbook workBook = new HSSFWorkbook(inputFile);
            
            String sheetName = (stage == Stages.STAGE2_COOPERATION) ? stage2SheetTitle :
                (stage == Stages.STAGE3_RATING) ? stage3SheetTitle : "";
            saveXLSProjectData(workBook.getSheet(sheetName), dataFields);
            
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
    
    public static void saveXLSProjectData(Sheet sheet, List<DataField> dataFields) {
        if (sheet == null) return;
        
        for (DataField dataField : dataFields) {
            
            Cell nameCell = sheet.getRow(dataField.getNameRow()).createCell(dataField.getNameCol());
            nameCell.setCellType(Cell.CELL_TYPE_STRING);
            nameCell.setCellValue(dataField.getName());
            
            Cell valueCell = sheet.getRow(dataField.getValueRow()).createCell(dataField.getValueCol());
            valueCell.setCellType(Cell.CELL_TYPE_STRING);
            try {
                double d = Double.parseDouble(dataField.getValue());
                valueCell.setCellValue(d);
            } catch (Exception ex) {
//                valueCell.setCellType(Cell.CELL_TYPE_FORMULA);
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
    public static void saveXLSCarrier(String fileName, Carrier car) {
        try {
            FileInputStream inputFile = new FileInputStream(new File(fileName));
            HSSFWorkbook workBook = new HSSFWorkbook(inputFile);

            // name
            Sheet sheet = workBook.getSheet(stage1SheetTitle);
            Cell nameCell = sheet.getRow(carrierNameRef.getRow() + car.getId() * CARRIER_ROWS_NUM)
                    .createCell(carrierNameRef.getCol());
            nameCell.setCellType(Cell.CELL_TYPE_STRING);
            nameCell.setCellValue(car.getName());
            // matrix
            Matrix matrix = car.getMatrix();
            for(int i = 0; i < Matrix.DEF_SIZE; i++) {
                for(int j = 0; j < Matrix.DEF_SIZE; j++) {
                    Cell cell = sheet.getRow(carrierMatrixRef.getRow() + car.getId() * CARRIER_ROWS_NUM + i)
                            .createCell(carrierMatrixRef.getCol() + j);
                    cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                    cell.setCellValue(matrix.getAt(i, j));
                }
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
        JOptionPane.showMessageDialog(MainFrame.getFrames()[0], mes, "Ошибка", JOptionPane.ERROR_MESSAGE);
    }
}
