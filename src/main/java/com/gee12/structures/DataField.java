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
public class DataField {
    
    public enum Types {
        BASE,
        OTHER
    }
    
//    public static final DataField EMPTY = new DataField("","","A1", Types.BASE);
    
    public static Comparator<DataField> COMPARATOR = new Comparator<DataField>() {
	@Override
	public int compare(DataField data1, DataField data2) {
            final DataField.Types type1 = data1.getType();
            final DataField.Types type2 = data2.getType();
	    return (type1 == DataField.Types.BASE && type2 == DataField.Types.OTHER) ? -1 :
		    (type2 == DataField.Types.BASE && type1 == DataField.Types.OTHER) ? 1 : 0;
	}
    };
    
    protected String name;
    protected String value;
    protected CellReference nameRef;
    protected CellReference valueRef;
    protected Types type;

    public DataField() {
        this("","","A1", Types.BASE);
    }   
    
    public DataField(Cell nameCell, Cell valueCell, Types type) {
        this.name = XLSParser.parseCell(nameCell);
        this.value = XLSParser.parseCell(valueCell);
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
        CellReference lastRef = (type == Types.BASE) ? XLSParser.baseDataFieldsRef :
            XLSParser.otherDataFieldsRef;//new CellReference(0, 0);
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
    
}
