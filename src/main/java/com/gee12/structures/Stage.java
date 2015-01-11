package com.gee12.structures;


import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Иван
 */
public class Stage {
    
    protected List<DataField> dataFileds;
    protected List<Criterion> criterions;

    public Stage() {
        this.dataFileds = new ArrayList<DataField>();
        this.criterions = new ArrayList<Criterion>();
    }
    
    public Stage(List<DataField> dataFileds, List<Criterion> criterions) {
        this.dataFileds = dataFileds;
        this.criterions = criterions;
    }

    public void setCriterions(List<Criterion> criterions) {
        this.criterions = criterions;
    }

    public void setDataFields(List<DataField> dataFileds) {
        this.dataFileds = dataFileds;
    }

    public List<Criterion> getCriterions() {
        return criterions;
    }

    public List<DataField> getDataFields() {
        return dataFileds;
    }
        
}
