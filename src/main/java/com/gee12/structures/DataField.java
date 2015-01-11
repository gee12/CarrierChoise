package com.gee12.structures;

import com.gee12.other.XLSParser;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellReference;

/**
 *
 * @author Иван
 */
public class DataField {
    
    public enum Types {
        BASE,
        OTHER
    }
    
    public static final DataField EMPTY = new DataField("","","A1","A2", Types.BASE);
    
    protected String name;
    protected String value;
    protected CellReference nameRef;
    protected CellReference valueRef;
    protected Types type;

    public DataField(Cell nameCell, Cell valueCell, Types type) {
        this.name = XLSParser.parseCell(nameCell);
        this.value = XLSParser.parseCell(valueCell);
        nameRef = new CellReference(nameCell);
        valueRef = new CellReference(valueCell);
        this.type = type;
    }
    
    public DataField(String name, String value, String nameRef, String valueRef, Types type) {
        this.name = name;
        this.value = value;
        this.nameRef = new CellReference(nameRef);
        this.valueRef = new CellReference(valueRef);
        this.type = type;
    }
    
    public static CellReference lastDataFieldNameRef(List<DataField> dataFields, Types type) {
        CellReference lastRef = new CellReference(0, 0);
        for(DataField dataField : dataFields) {
            if (dataField.getType() == type && lastRef.getRow() < dataField.getValueRow()) {
                lastRef = dataField.getValueRef();
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

    public void setNameRef(CellReference nameRef) {
        this.nameRef = nameRef;
    }

    public void setValueRef(CellReference valueRef) {
        this.valueRef = valueRef;
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

    public int getValueRow() {
        return valueRef.getRow();
    }

    public int getValueCol() {
        return valueRef.getCol();
    }
        
    public String getNameCellReference() {
        return nameRef.formatAsString();
    }
    public String getValueCellReference() {
        return valueRef.formatAsString();
    }
    
}
