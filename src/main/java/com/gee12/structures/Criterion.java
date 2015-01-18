package com.gee12.structures;

import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellReference;

/**
 *
 * @author Иван
 */
public class Criterion {
    
    public enum Types {
        BASE,
        OTHER
    }
    
    public static final Criterion EMPTY = new Criterion("","","","","A1","A2", Types.BASE);
    
    protected String name;
    protected String formula;
    protected String value;
    protected String max;
    protected CellReference nameRef;
    protected CellReference valueRef;
    protected Types type;
   
    public Criterion(String name, String formula, String value, String max, Cell nameCell, Cell valueCell, Types type) {
        this.name = name;
        this.formula = formula;
        this.value = value;
        this.max = max;
        this.nameRef = new CellReference(nameCell);
        this.valueRef = new CellReference(valueCell);
        this.type = type;
    }
    
    public Criterion(String name, String formula, String value, String max, String nameRef, String valueRef, Types type) {
        this.name = name;
        this.formula = formula;
        this.value = value;
        this.max = max;
        this.nameRef = new CellReference(nameRef);
        this.valueRef = new CellReference(valueRef);
        this.type = type;
    }
    
    public static CellReference lastCriterionNameRef(List<Criterion> criterions, Types type) {
        CellReference lastRef = new CellReference(0, 0);
        for(Criterion crit : criterions) {
            if (crit.getType() == type && lastRef.getRow() < crit.getValueRow()) {
                lastRef = crit.getValueRef();
            }
        }
        return lastRef;
    }

    public CellReference getNameRef() {
        return nameRef;
    }

    public CellReference getValueRef() {
        return valueRef;
    }

    public void setType(Types type) {
        this.type = type;
    }

    public Types getType() {
        return type;
    }

    public String getMax() {
        return max;
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
