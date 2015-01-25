package com.gee12.structures;

import com.gee12.other.XLSParser;
import java.util.Comparator;
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
    
//    public static final Criterion EMPTY = new Criterion("","","","","A1", Types.BASE);
 
    public static Comparator<Criterion> COMPARATOR = new Comparator<Criterion>() {
	@Override
	public int compare(Criterion crit1, Criterion crit2) {
            final Types type1 = crit1.getType();
            final Types type2 = crit2.getType();
	    return (type1 == Types.BASE && type2 == Types.OTHER) ? -1 :
		    (type2 == Criterion.Types.BASE && type1 == Types.OTHER) ? 1 : 0;
	}
    };
    
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
        CellReference lastRef = (type == Types.BASE) ? XLSParser.baseCriterionsRef :
            XLSParser.otherCriterionsRef;//new CellReference(0, 0);
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
}
