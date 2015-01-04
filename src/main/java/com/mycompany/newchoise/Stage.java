package com.mycompany.newchoise;


import java.util.List;

/**
 *
 * @author Иван
 */
public class Stage {
    
    protected final List<Criterion> baseCriterions;
    protected final List<Criterion> otherCriterions;
    protected List<DataField> baseDataFileds;
    protected List<DataField> otherDataFileds;

    public Stage(List<Criterion> baseCriterions, List<Criterion> otherCriterions, List<DataField> baseDataFileds, List<DataField> otherDataFileds) {
        this.baseCriterions = baseCriterions;
        this.otherCriterions = otherCriterions;
        this.baseDataFileds = baseDataFileds;
        this.otherDataFileds = otherDataFileds;
    }

    public List<Criterion> getBaseCriterions() {
        return baseCriterions;
    }

    public List<Criterion> getOtherCriterions() {
        return otherCriterions;
    }
    
    public List<DataField> getBaseDataFileds() {
        return baseDataFileds;
    }

    public List<DataField> getOtherDataFileds() {
        return otherDataFileds;
    }
    
}
