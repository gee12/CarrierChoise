package com.gee12.structures;

import com.gee12.other.ExcelParser;
import java.util.Arrays;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellReference;

/**
 *
 * @author Иван
 */
public class DataField implements Comparable<DataField> {

    public enum Types {
        BASE("Базовый"),
        OTHER("Дополнительный");
        
        private final String name;
        
        Types(String name) {
            this.name = name;
        }
        
        public String getName() {
            return name;
        }
        
        public static Types getType(String name) {
//            Arrays.asList(Types.values()).forEach((Types t) -> t.getName().equalsIgnoreCase(name));
            for (Types type : values()) {
                if (type.getName().equalsIgnoreCase(name)) {
                    return type;
                }
            }
            return BASE;
        }

        @Override
        public String toString() {
            return name;
        }
        
    }
    
    protected String name;
    protected String value;
    protected CellReference nameRef;
    protected CellReference valueRef;
    protected Types type;

    public DataField() {
        this("","","A1", Types.BASE);
    }   
    
    public DataField(Cell nameCell, Cell valueCell, Types type) {
        this.name = ExcelParser.parseCell(nameCell);
        this.value = ExcelParser.parseCell(valueCell);
        nameRef = new CellReference(nameCell);
        valueRef = new CellReference(nameCell.getRowIndex(), nameCell.getColumnIndex() + 1);
        this.type = type;
    }
    
    public DataField(String name, String value, String nameStringRef, Types type) {
        this.name = name;
        this.value = value;
        this.nameRef = new CellReference(nameStringRef);
        valueRef = new CellReference(nameRef.getRow(), nameRef.getCol() + 1);
        this.type = type;
    }
    
    public static CellReference lastDataFieldNameRef(List<DataField> dataFields, Types type) {
        CellReference lastRef = (type == Types.BASE) ? ExcelParser.baseDataFieldsRef :
            ExcelParser.otherDataFieldsRef;//new CellReference(0, 0);
        for(DataField dataField : dataFields) {
            if (dataField.getType() == type && lastRef.getRow() < dataField.getNameRow()) {
                lastRef = dataField.getNameRef();
            }
        }
        return lastRef;
    }

    public void setType(Types type) {
        this.type = type;
    }

    public CellReference getNameRef() {
        return nameRef;
    }

    public CellReference getValueRef() {
        return valueRef;
    }

    public Types getType() {
        return type;
    }

    public void setNameRef(CellReference ref) {
        this.nameRef = ref;
    }
    

    public void setValueRef(CellReference ref) {
        this.valueRef = ref;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public int getNameRow() {
        return nameRef.getRow();
    }

    public int getNameCol() {
        return nameRef.getCol();
    }
        
    public String getNameCellReference() {
        return nameRef.formatAsString();
    }
    
    public String getValueCellReference() {
        return valueRef.formatAsString();
    }

    @Override
    public int compareTo(DataField o) {
        final DataField.Types type2 = o.getType();
        return (type == DataField.Types.BASE && type2 == DataField.Types.OTHER) ? -1 :
                (type2 == DataField.Types.BASE && type == DataField.Types.OTHER) ? 1 : 0;
    }
        
}
