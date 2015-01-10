package com.gee12.structures;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellReference;

/**
 *
 * @author Иван
 */
public class Criterion {
    
    public static final Criterion EMPTY = new Criterion("","","","A1","A2");
    
    protected String name;
    protected String formula;
    protected String value;
    protected CellReference nameRef;
    protected CellReference valueRef;
    
    public Criterion(String name, String formula, String value, Cell nameCell, Cell valueCell) {
        this.name = name;
        this.formula = formula;
        this.value = value;
        this.nameRef = new CellReference(nameCell);
        this.valueRef = new CellReference(valueCell);
    }
    
    public Criterion(String name, String formula, String value, String nameRef, String valueRef) {
        this.name = name;
        this.formula = formula;
        this.value = value;
        this.nameRef = new CellReference(nameRef);
        this.valueRef = new CellReference(valueRef);
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setNameRef(CellReference nameRef) {
        this.nameRef = nameRef;
    }

    public void setValueRef(CellReference valueRef) {
        this.valueRef = valueRef;
    }
    
    public String getFormula() {
        return formula;
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
