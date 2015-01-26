package com.gee12.structures;

import com.gee12.other.ExcelParser;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellReference;

/**
 *
 * @author Иван
 */
public class Criterion implements Comparable<Criterion> {

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
    protected String formula;
    protected String value;
    protected String max;
    protected CellReference nameRef;
    protected Types type;
   
    public Criterion() {
        this("","","","","A1", Types.BASE);
    }
    
    public Criterion(String name, String formula, String value, String max, Cell nameCell, Types type) {
        this.name = name;
        this.formula = formula;
        this.value = value;
        this.max = max;
        this.nameRef = new CellReference(nameCell);
        this.type = type;
    }
    
    public Criterion(String name, String formula, String value, String max, String nameRef, Types type) {
        this.name = name;
        this.formula = formula;
        this.value = value;
        this.max = max;
        this.nameRef = new CellReference(nameRef);
        this.type = type;
    }
    
    public static CellReference lastCriterionNameRef(List<Criterion> criterions, Types type) {
        CellReference lastRef = (type == Types.BASE) ? ExcelParser.baseCriterionsRef :
            ExcelParser.otherCriterionsRef;//new CellReference(0, 0);
        for(Criterion crit : criterions) {
            if (crit.getType() == type && lastRef.getRow() < crit.getNameRow()) {
                lastRef = crit.getNameRef();
            }
        }
        return lastRef;
    }

    public CellReference getNameRef() {
        return nameRef;
    }
    
    public String getMax() {
        return max;
    }
    
    public void setMax(String max) {
        this.max = max;
    }

    public void setType(Types type) {
        this.type = type;
    }

    public Types getType() {
        return type;
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
        
    public String getNameCellReference() {
        return nameRef.formatAsString();
    }

    @Override
    public int compareTo(Criterion o) {
        final Types type2 = o.getType();
        return (type == Types.BASE && type2 == Types.OTHER) ? -1 :
                (type2 == Criterion.Types.BASE && type == Types.OTHER) ? 1 : 0;
    }
}
