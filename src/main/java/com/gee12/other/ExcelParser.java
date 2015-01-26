package com.gee12.other;


import static com.gee12.other.Utils.onException;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Иван
 */
public class ExcelParser {
    
    public static final String XLS_EXTENSION = "xls";
    public static final String XLSX_EXTENSION = "xlsx";
    
    public static final String stage1SheetTitle = "1 этап";
    public static final String stage2SheetTitle = "2 этап";
    public static final String stage3SheetTitle = "3 этап";
    public static final String[] carrierMatrixTitle = {"Грузоподъемность,тонн",
        "200","300","400"};
    public static final String[] matrixCapacitiesTitle = {
        "10", "15", "20"
    };
    
    public static final CellReference carrierNameRef = new CellReference(0, 0);
    public static final CellReference carrierMatrixRef = new CellReference(1, 0);
    public static final CellReference carrierResultRef = new CellReference(5, 0);
    public static final int CARRIER_ROWS_NUM = 7;
    
    public static final CellReference curCarrierRef = new CellReference(0, 0);
    
    public static final CellReference baseDataFieldsRef = new CellReference(3, 0);
    public static final CellReference otherDataFieldsRef = new CellReference(3, 3);
    public static final CellReference baseCriterionsRef = new CellReference(3, 7);
    public static final CellReference otherCriterionsRef = new CellReference(3, 10);

    
    ////////////////////////////////////////////////////////////////////////////
    public static Project readExcelFile(String fileName) {
        Project res = null;
        try {
            String ext = Utils.getExt(fileName);
            FileInputStream inputFile = new FileInputStream(new File(fileName));
            Workbook workBook = null;   // interface, accepts both HSSF and XSSF.
            if (ext.equalsIgnoreCase(XLS_EXTENSION)) {
                // HSSF
                workBook = new HSSFWorkbook(inputFile);
            } else if (ext.equalsIgnoreCase(XLSX_EXTENSION)) {
                // XSSF
                workBook = new XSSFWorkbook(inputFile);
            }
            res = readExcelCarrierProject(workBook);
        } catch (Exception ex) {
            Utils.onException(ex);
        }
        return res;
    }    
    
    ////////////////////////////////////////////////////////////////////////////
    public static Project readExcelCarrierProject(Workbook workBook) {
        Project res = new Project();
        String curCarrierName = null;
        Stage stage2 = null, stage3 = null;
        
        try {
            // carriers
            Sheet sheet1 = workBook.getSheet(stage1SheetTitle);
            res = new Project(parseCarriers(sheet1));
            
            // stage 2
            Sheet sheet2 = workBook.getSheet(stage2SheetTitle);
            curCarrierName = parseCurrentCarrierName(sheet2);
            List<DataField> dataFields2 = parseDataFields(sheet2, baseDataFieldsRef, DataField.Types.BASE); 
            dataFields2.addAll(parseDataFields(sheet2, otherDataFieldsRef, DataField.Types.OTHER));
           
            List<Criterion> criterions2 = parseCriterions(sheet2, baseCriterionsRef, Criterion.Types.BASE); 
            criterions2.addAll(parseCriterions(sheet2, otherCriterionsRef, Criterion.Types.OTHER));
            stage2 = new Stage(dataFields2, criterions2);
            
            // stage 3
            Sheet sheet3 = workBook.getSheet(stage3SheetTitle);
            List<DataField> dataFields3 = parseDataFields(sheet3, baseDataFieldsRef, DataField.Types.BASE); 
            dataFields3.addAll(parseDataFields(sheet3, otherDataFieldsRef, DataField.Types.OTHER));
           
            List<Criterion> criterions3 = parseCriterions(sheet3, baseCriterionsRef, Criterion.Types.BASE); 
            criterions3.addAll(parseCriterions(sheet3, otherCriterionsRef, Criterion.Types.OTHER));
            stage3 = new Stage(dataFields3, criterions3);  
            
            // set stages to carrier
            Carrier curCarrier = res.getCarrier(curCarrierName);
            if (curCarrier != null) {
                curCarrier.setStages(new Stage[] { stage2, stage3});
            }
            
            workBook.close();
        } catch (Exception e) {
            onException(e);
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
        } catch (Exception e) {
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
        } catch (Exception e) {
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
        } catch (Exception e) {
            onException(e);
        }
        
        return res;
    }    
    
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    // PARSE
    
    ////////////////////////////////////////////////////////////////////////////
    public static String parseCurrentCarrierName(Sheet sheet) {
        if (sheet == null) return null;
        
        String res = null;
        Row nameRow = sheet.getRow(curCarrierRef.getRow());
        if (nameRow == null) return "";
        Cell cell = nameRow.getCell(curCarrierRef.getCol());
        if (cell == null) return "";
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
        for (int id = 0; ((id + 1) * CARRIER_ROWS_NUM - 2) <= sheet.getLastRowNum(); id++) {
            Carrier carrier = parseCarrier(sheet, id);
            if (carrier != null) {
                res.add(carrier);
            }
        }
        
        return res;
    }
    
    ////////////////////////////////////////////////////////////////////////////
    public static Carrier parseCarrier(Sheet sheet, int id) {
        if (sheet == null) return null;
        
        Cell cell;
        Row row;
        int shift = id * CARRIER_ROWS_NUM;
        // name
        String name = null;
        row = sheet.getRow(carrierNameRef.getRow() + shift);
        if (row != null) {
            cell = row.getCell(carrierNameRef.getCol());
            if (cell != null && cell.getCellType() == Cell.CELL_TYPE_STRING) {
                name = cell.getStringCellValue();
            }
        }
        if (name == null) return null;
        // capacity
        double capacity = 0.0;
        row = sheet.getRow(carrierResultRef.getRow() + shift);
        if (row != null) {
            cell = row.getCell(carrierResultRef.getCol() + 1);
            if (cell != null && cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                capacity = cell.getNumericCellValue();
            }
        }
        // repeat number
        int repeatNum = 0;
        row = sheet.getRow(carrierResultRef.getRow() + shift);
        if (row != null) {
            cell = row.getCell(carrierResultRef.getCol() + 2);
            if (cell != null && cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                repeatNum = (int)cell.getNumericCellValue();
            }
        }
        // matrix
        Matrix matrix = new Matrix();
        for(int i = 0; i < Matrix.DEF_SIZE; i++) {
            for(int j = 0; j < Matrix.DEF_SIZE; j++) {
                int rowNum = carrierMatrixRef.getRow() + shift + i + 1;
                int colNum = carrierMatrixRef.getCol() + j + 1;
                row = sheet.getRow(rowNum);
                if (row != null) {
                    cell = row.getCell(colNum);
                    if (cell != null && cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                        matrix.setAt(i, j, cell.getNumericCellValue());
                    }
                }
            }
        }
        
        return new Carrier(id, name, capacity, repeatNum, matrix);
    }
    
    ////////////////////////////////////////////////////////////////////////////
    public static List<DataField> parseDataFields(Sheet sheet, CellReference ref, DataField.Types type) {
        if (sheet == null || ref == null) return null;
        
        List<DataField> res = new ArrayList<>();

        for (int i = ref.getRow(); i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            DataField dataField = parseDataField(row, ref, type);
            if (dataField != null) {
                res.add(dataField);
            }
        }
                
        return res;
    }
    
    ////////////////////////////////////////////////////////////////////////////
    public static DataField parseDataField(Row row, CellReference ref, DataField.Types type) {
        if (row == null || ref == null) return null;

        if (row.getLastCellNum() < ref.getCol()) {
            return null;
        }
        // 
        Cell nameCell = row.getCell(ref.getCol());
        Cell valueCell = row.getCell(ref.getCol() + 1);
        if (nameCell == null || valueCell == null) return null;
        //
        String name = parseCell(nameCell);
        String value = parseCell(valueCell);
        if (name.isEmpty()) return null;

//        return new DataField(nameCell, valueCell, type);
        return new DataField(name, value, new CellReference(nameCell).formatAsString(), type);
    }

    ////////////////////////////////////////////////////////////////////////////
    public static List<Criterion> parseCriterions(Sheet sheet, CellReference ref, Criterion.Types type) {
        if (sheet == null || ref == null) return null;
        
        List<Criterion> res = new ArrayList<>();
        
        for (int i = ref.getRow(); i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            Criterion crit = parseCriterion(row, ref, type);
            if (crit != null) {
                res.add(crit);
            }
        }
        
        return res;
    }
    
    ////////////////////////////////////////////////////////////////////////////
    public static Criterion parseCriterion(Row row, CellReference ref, Criterion.Types type) {
        if (row == null || ref == null) return null;
        
        Cell nameCell = row.getCell(ref.getCol());
        Cell valueCell = row.getCell(ref.getCol() + 1);
        Cell maxCell = row.getCell(ref.getCol() + 2);

        if (nameCell == null || valueCell == null || maxCell == null)
            return null;
        // name
        String name = nameCell.getStringCellValue();
        if (name.isEmpty()) return null;
        // formula
        String formula = "";
        if (valueCell.getCellType() == Cell.CELL_TYPE_FORMULA) {
            formula = valueCell.getCellFormula();
        }
        // value
        String value = parseCell(valueCell);
//        String value = "";
//        if (valueCell.getCachedFormulaResultType() != Cell.CELL_TYPE_ERROR) {
//            // if formula not error => parse numeric value
//            value = String.format(Locale.US, "%.2f", valueCell.getNumericCellValue());
//        } else {
//            value = String.format(Locale.US, "Error: %d", valueCell.getErrorCellValue());
//        }
        // max
        String max = parseCell(maxCell);
//        String max = "";
//        if (maxCell.getCellType() == Cell.CELL_TYPE_STRING) {
//            max = maxCell.getStringCellValue();
//        } else {
//            max = String.valueOf(maxCell.getNumericCellValue());
//        }

//        if (name.isEmpty() && formula.isEmpty() 
//                && value.isEmpty() && max.isEmpty())
//            return null;
        
        return new Criterion(name, formula, value, max, nameCell, type);
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
                    return String.format(Locale.US, "Error: %d", cell.getErrorCellValue());
//                    return cell.getCellFormula();
                }
            default:
                return "";
        }
    }    
    
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    // SAVE
    public static void saveXLSCarrierProject(String fileName, Project proj, Carrier car) {
        try {
            FileInputStream inputFile = new FileInputStream(new File(fileName));
            HSSFWorkbook workBook = new HSSFWorkbook(inputFile);
            
            // 1 - carriers
            Sheet sheet1 = workBook.getSheet(stage1SheetTitle);
            if (sheet1 == null) sheet1 = workBook.createSheet(stage1SheetTitle);
            if (proj != null) {
                saveXLSCarriers(sheet1, proj.getCarriers());
            }
            // 2 - cooperation stage
            Sheet sheet2 = workBook.getSheet(stage2SheetTitle);
            if (sheet2 == null) sheet2 = workBook.createSheet(stage2SheetTitle);
            saveXLSCurrentCarrierName(sheet2, car.getName());
            saveXLSCriterions(sheet2, car.getStage(Project.Stages.STAGE2_COOPERATION).getCriterions());
            saveXLSDataFields(sheet2, car.getStage(Project.Stages.STAGE2_COOPERATION).getDataFields());
            // 3 - rating stage
            Sheet sheet3 = workBook.getSheet(stage3SheetTitle);
            if (sheet3 == null) sheet3 = workBook.createSheet(stage3SheetTitle);
            saveXLSCurrentCarrierName(sheet3, car.getName());
            saveXLSCriterions(sheet3, car.getStage(Project.Stages.STAGE3_RATING).getCriterions());
            saveXLSDataFields(sheet3, car.getStage(Project.Stages.STAGE3_RATING).getDataFields());
            
            workBook.getCreationHelper().createFormulaEvaluator().evaluateAll();
            
            inputFile.close();
            FileOutputStream outputFile = new FileOutputStream(new File(fileName));
            workBook.write(outputFile);
            outputFile.close();
            workBook.close();
        } catch (Exception e) {
             onException(e);
        }
    }
    
    ////////////////////////////////////////////////////////////////////////////
    public static void saveXLSCurrentCarrierName(Sheet sheet, String carrierName) {
        if (sheet == null || carrierName == null) return;
        
        Cell nameCell = sheet.createRow(carrierNameRef.getRow()).createCell(carrierNameRef.getCol());
        nameCell.setCellValue(carrierName);
    }
    
    ////////////////////////////////////////////////////////////////////////////
    public static void saveXLSDataFields(String fileName, Carrier car) {
        try {
            FileInputStream inputFile = new FileInputStream(new File(fileName));
            HSSFWorkbook workBook = new HSSFWorkbook(inputFile);
            
            saveXLSDataFields(workBook.getSheet(stage2SheetTitle), 
                    car.getStage(Project.Stages.STAGE2_COOPERATION).getDataFields());
            saveXLSDataFields(workBook.getSheet(stage3SheetTitle), 
                    car.getStage(Project.Stages.STAGE3_RATING).getDataFields());
            
            workBook.getCreationHelper().createFormulaEvaluator().evaluateAll();
            
            inputFile.close();
            
            FileOutputStream outputFile = new FileOutputStream(new File(fileName));
            workBook.write(outputFile);
            outputFile.close();
            
            workBook.close();

        } catch (Exception e) {
             onException(e);
        }
    }
    
    ////////////////////////////////////////////////////////////////////////////
    public static void saveXLSDataFields(String fileName, Stages stage, List<DataField> dataFields) {
        try {
            FileInputStream inputFile = new FileInputStream(new File(fileName));
            HSSFWorkbook workBook = new HSSFWorkbook(inputFile);
            //
            String sheetName = (stage == Stages.STAGE2_COOPERATION) ? stage2SheetTitle :
                (stage == Stages.STAGE3_RATING) ? stage3SheetTitle : "";
            saveXLSDataFields(workBook.getSheet(sheetName), dataFields);
            //
            workBook.getCreationHelper().createFormulaEvaluator().evaluateAll();
            inputFile.close();
            FileOutputStream outputFile = new FileOutputStream(new File(fileName));
            workBook.write(outputFile);
            outputFile.close();
            workBook.close();

        } catch (Exception e) {
             onException(e);
        }        
    }
    
    ////////////////////////////////////////////////////////////////////////////
    public static void saveXLSDataFields(Sheet sheet, List<DataField> dataFields) {
        if (sheet == null) return;
        
        for (DataField dataField : dataFields) {
            
            Row row = sheet.getRow(dataField.getNameRow());
            if (row == null) row = sheet.createRow(dataField.getNameRow());
            
            Cell nameCell = row.createCell(dataField.getNameCol());
//            nameCell.setCellType(Cell.CELL_TYPE_STRING);
            nameCell.setCellValue(dataField.getName());
            
            Cell valueCell = row.createCell(dataField.getNameCol() + 1);
//            valueCell.setCellType(Cell.CELL_TYPE_STRING);
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
    public static void saveXLSCriterions(String fileName, Stages stage, List<Criterion> criterions) {
        try {
            FileInputStream inputFile = new FileInputStream(new File(fileName));
            HSSFWorkbook workBook = new HSSFWorkbook(inputFile);
            //
            String sheetName = (stage == Stages.STAGE2_COOPERATION) ? stage2SheetTitle :
                    (stage == Stages.STAGE3_RATING) ? stage3SheetTitle : "";
            saveXLSCriterions(workBook.getSheet(sheetName), criterions);
            //
            workBook.getCreationHelper().createFormulaEvaluator().evaluateAll();
            inputFile.close();
            FileOutputStream outputFile = new FileOutputStream(new File(fileName));
            workBook.write(outputFile);
            outputFile.close();
            workBook.close();
        } catch (Exception e) {
            onException(e);
        }
    }
        
    ////////////////////////////////////////////////////////////////////////////
    public static void saveXLSCriterions(Sheet sheet, List<Criterion> criterions) {
        if (sheet == null) return;
        
        for (Criterion crit : criterions) {
            Row row = sheet.getRow(crit.getNameRow());
            if (row == null) row = sheet.createRow(crit.getNameRow());
            // name
            Cell nameCell = row.createCell(crit.getNameCol());
            nameCell.setCellType(Cell.CELL_TYPE_STRING);
            nameCell.setCellValue(crit.getName());
            // value
            Cell valueCell = row.createCell(crit.getNameCol() + 1);
            String formula = crit.getFormula();
            if (formula.equalsIgnoreCase("")) {
                valueCell.setCellType(Cell.CELL_TYPE_STRING);
                valueCell.setCellValue("");
            } else {
                valueCell.setCellType(Cell.CELL_TYPE_FORMULA);
                valueCell.setCellFormula(formula);
            }           
            // max
            Cell maxCell = row.createCell(crit.getNameCol() + 2);
            maxCell.setCellType(Cell.CELL_TYPE_STRING);
            try {
                double d = Double.parseDouble(crit.getMax());
                maxCell.setCellValue(d);
            } catch (Exception ex) {
//                valueCell.setCellType(Cell.CELL_TYPE_FORMULA);
                maxCell.setCellValue(crit.getMax());
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
            int rowNum = crit.getNameRow();
            int colNum = crit.getNameCol();
            Row row = sheet.getRow(rowNum);
            if (row == null) row = sheet.createRow(rowNum);
            // name
            Cell nameCell = row.createCell(colNum);
            nameCell.setCellType(Cell.CELL_TYPE_STRING);
            nameCell.setCellValue(crit.getName());
            // formula
            Cell formulaCell = row.createCell(colNum + 1);
            formulaCell.setCellType(Cell.CELL_TYPE_FORMULA);
            formulaCell.setCellValue(crit.getFormula());
            // max
            Cell maxCell = row.createCell(colNum + 2);
            maxCell.setCellType(Cell.CELL_TYPE_NUMERIC);
            maxCell.setCellValue(crit.getMax());            
            
            workBook.getCreationHelper().createFormulaEvaluator().evaluateAll();
            inputFile.close();
            FileOutputStream outputFile = new FileOutputStream(new File(fileName));
            workBook.write(outputFile);
            outputFile.close();
            workBook.close();
        } catch (Exception e) {
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
            Row row = sheet.getRow(dataField.getNameRow());
            if (row == null) row = sheet.createRow(dataField.getNameRow());
            // name
            Cell nameCell = row.createCell(dataField.getNameCol());
            nameCell.setCellType(Cell.CELL_TYPE_STRING);
            nameCell.setCellValue(dataField.getName());
            // value
            Cell valueCell = row.createCell(dataField.getNameCol() + 1);
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
        } catch (Exception e) {
            onException(e);
        }
    }
    
    ////////////////////////////////////////////////////////////////////////////
    public static void saveXLSCarrier(String fileName, Carrier car) {
        try {
            FileInputStream inputFile = new FileInputStream(new File(fileName));
            HSSFWorkbook workBook = new HSSFWorkbook(inputFile);

            Sheet sheet = workBook.getSheet(stage1SheetTitle);
            saveXLSCarrier(sheet, car);
            
//            workBook.getCreationHelper().createFormulaEvaluator().evaluateAll();
            inputFile.close();
            FileOutputStream outputFile = new FileOutputStream(new File(fileName));
            workBook.write(outputFile);
            outputFile.close();
            workBook.close();
        } catch (Exception e) {
            onException(e);
        }
    }
    
    ////////////////////////////////////////////////////////////////////////////
    public static void saveXLSCarriers(Sheet sheet, List<Carrier> cars) {
        for (Carrier car : cars) {
            saveXLSCarrier(sheet, car);
        }
    }
    
    ////////////////////////////////////////////////////////////////////////////
    public static void saveXLSCarrier(Sheet sheet, Carrier car) {
        if (sheet == null || car == null) return;
        
        int shift = car.getId() * CARRIER_ROWS_NUM;
        // 1
        Row carrierNameRow = sheet.createRow(carrierNameRef.getRow() + shift);
        // name
        Cell nameCell = carrierNameRow.createCell(carrierNameRef.getCol());
        nameCell.setCellValue(car.getName());
        // matrix title
        CellRangeAddress matrixTitleRegion = new CellRangeAddress(carrierNameRef.getRow() + shift, 
                carrierNameRef.getRow() + shift, 
                carrierNameRef.getCol() + 1, 
                carrierNameRef.getCol() + 3);
        sheet.addMergedRegion(matrixTitleRegion);
        Cell matrixTitleCell = carrierNameRow.createCell(carrierNameRef.getCol() + 1);
        matrixTitleCell.setCellValue("Планируемый объём продаж, тонн");

        // 2
        // matrix columns title
        Row matrixTitleRow = sheet.createRow(carrierMatrixRef.getRow() + shift);
        for (int i = 0; i < carrierMatrixTitle.length; i++) {
            Cell cell = matrixTitleRow.createCell(carrierMatrixRef.getCol() + i);
            cell.setCellValue(carrierMatrixTitle[i]);
        }
        // matrix
        Matrix matrix = car.getMatrix();
        for(int i = 0; i < Matrix.DEF_SIZE; i++) {
            Row row = sheet.createRow(carrierMatrixRef.getRow() + shift + i + 1);
            Cell titleCell = row.createCell(carrierMatrixRef.getCol());
            titleCell.setCellValue(matrixCapacitiesTitle[i]);

            for(int j = 0; j < Matrix.DEF_SIZE; j++) {
                Cell cell = row.createCell(carrierMatrixRef.getCol() + j + 1);
                cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                cell.setCellValue(matrix.getAt(i, j));
            }
        }

        // 3 result
        Row resultRow = sheet.createRow(carrierResultRef.getRow() + shift);
        // title
        Cell resultTitleCell = resultRow.createCell(carrierResultRef.getCol());
        resultTitleCell.setCellValue("Результат");
        // capacity
        Cell capacityCell = resultRow.createCell(carrierResultRef.getCol() + 1);
        capacityCell.setCellValue(car.getCapacity());
        // repeat number
        Cell repeatCell = resultRow.createCell(carrierResultRef.getCol() + 2);
        repeatCell.setCellValue(car.getRepeatNum());
    }
   
    ////////////////////////////////////////////////////////////////////////////
    public static String redefineXLSCriterionValue(String fileName, Criterion crit, Stages stage) {
        if (fileName == null || crit == null) return null;
        String res = null;

        try {
            FileInputStream inputFile = new FileInputStream(new File(fileName));
            HSSFWorkbook workBook = new HSSFWorkbook(inputFile);
            
            String sheetTitle = (stage == Stages.STAGE2_COOPERATION) ? stage2SheetTitle : stage3SheetTitle;
            Sheet sheet = workBook.getSheet(sheetTitle);
            int rowNum = crit.getNameRow();
            Row row = sheet.getRow(rowNum);
            if (row == null) row = sheet.createRow(rowNum);
            // 1 - save formula
            Cell formulaCell = row.createCell(crit.getNameCol() + 1);
            formulaCell.setCellType(Cell.CELL_TYPE_FORMULA);
            formulaCell.setCellFormula(crit.getFormula());
            // 2 - redefine value 
            Cell valueCell = workBook.getCreationHelper().createFormulaEvaluator().evaluateInCell(formulaCell);
            if (valueCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                // if formula not error => parse numeric value
                res = String.format(Locale.US, "%.2f", valueCell.getNumericCellValue());
            } else {
                res = String.format(Locale.US, "Error: %d", valueCell.getErrorCellValue());
            }
            
            inputFile.close();
            FileOutputStream outputFile = new FileOutputStream(new File(fileName));
            workBook.write(outputFile);
            outputFile.close();
            workBook.close();
        } catch (Exception e) {
            onException(e);
        }
        
        return res;
    }
        
    ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    // CREATE
    public static void createXLSFile(String fileName) {
        try {
            HSSFWorkbook workBook = new HSSFWorkbook();
            //
            workBook.createSheet(stage1SheetTitle);
            workBook.createSheet(stage2SheetTitle);
            workBook.createSheet(stage3SheetTitle);
            
            FileOutputStream outputFile = new FileOutputStream(new File(fileName));
            workBook.write(outputFile);
            outputFile.close();
            workBook.close();
        } catch (Exception e) {
            onException(e);
        }
    }

    
    ////////////////////////////////////////////////////////////////////////////
    public static String addXLSExtension(String fileName) {
        return Utils.withExt(fileName, "." + XLS_EXTENSION);
    }  
}
