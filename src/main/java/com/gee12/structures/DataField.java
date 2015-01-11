package com.gee12.structures;

import com.gee12.other.XLSParser;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellReference;

/**
 *
 * @author Иван
 */
public class DataField {
    
    public static final DataField EMPTY = new DataField("","","A1","A2");
    
    protected String name;
    protected String value;
    protected CellReference nameRef;
    protected CellReference valueRef;

    public DataField(Cell nameCell, Cell valueCell) {
        this.name = XLSParser.parseCell(nameCell);
        this.value = XLSParser.parseCell(valueCell);
        nameRef = new CellReference(nameCell);
        valueRef = new CellReference(valueCell);
    }
    
    public DataField(String name, String value, String nameRef, String valueRef) {
        this.name = name;
        this.value = value;
        this.nameRef = new CellReference(nameRef);
        this.valueRef = new CellReference(valueRef);
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
