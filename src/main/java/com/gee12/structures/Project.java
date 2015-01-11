package com.gee12.structures;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Иван
 */
public class Project {
    
    public enum Stages {
        STAGE0_START,
        STAGE1_CHOISE,         // этап выбора
        STAGE2_COOPERATION,    // этап сотрудничества
        STAGE3_RATING          // этап оценки
    }
    
    protected List<Carrier> carriers;
    protected Carrier curCarrier;
    protected Stage[] stages;

    ////////////////////////////////////////////////////////////////////////////
    public Project() {
        this.carriers = new ArrayList<Carrier>();
        stages = new Stage[] { new Stage(), new Stage() };
    }
    public Project(List<Carrier> carriers, Stage[] stages) {
        this.carriers = carriers;
        this.stages = stages;
    }
    
    ////////////////////////////////////////////////////////////////////////////
    // set
    public void setCurrentCarrier(Carrier curCarrier) {
        this.curCarrier = curCarrier;
    }
    
    public void setCurrentCarrier(int i) {
        if (i < 0 || i > carriers.size()) return;
        this.curCarrier = carriers.get(i);
    }

    ////////////////////////////////////////////////////////////////////////////
    // get
    public Carrier getCarrier() {
        return curCarrier;
    }

    public List<Carrier> getCarriers() {
        return carriers;
    }

    public Stage[] getStages() {
        return stages;
    }
    
    public Stage getStage(Stages stage) {
        switch (stage) {
            case STAGE2_COOPERATION: 
                return stages[0];
            case STAGE3_RATING:
                return stages[1];
        }
        return null;
    }
 
}
