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
    
    protected double generalMark;
    protected double maxMark;
    protected double deviation;

    ////////////////////////////////////////////////////////////////////////////
    public Project() {
        this.carriers = new ArrayList<Carrier>();
        stages = new Stage[] { new Stage(), new Stage() };
    }
    
    public Project(List<Carrier> carriers, Stage[] stages) {
        this.carriers = carriers;
        this.stages = stages;
    }
    
    public Project(List<Carrier> carriers, Stage[] stages, 
            double generalMark, double maxMark, double deviation) {
        this.carriers = carriers;
        this.stages = stages;
        this.generalMark = generalMark;
        this.maxMark = maxMark;
        this.deviation = deviation;
    }   
    
    ////////////////////////////////////////////////////////////////////////////
    // set
    public void setCurrentCarrier(Carrier curCarrier) {
        this.curCarrier = curCarrier;
    }
    public void setCurrentCarrier(String name) {
        if (name == null) return;
        for (Carrier car : carriers) {
            if (name.equalsIgnoreCase(car.getName()))
                this.curCarrier = car;
        }
    }
        
    public void setCurrentCarrier(int i) {
        if (i < 0 || i > carriers.size()) return;
        this.curCarrier = carriers.get(i);
    }

    public void setDeviation(double deviation) {
        this.deviation = deviation;
    }
    
    public Carrier getCurCarrier() {
        return curCarrier;
    }

    public double getGeneralMark() {
        return generalMark;
    }

    public double getMaxMark() {
        return maxMark;
    }

    public void setCarriers(List<Carrier> carriers) {
        this.carriers = carriers;
    }

    public void setCurCarrier(Carrier curCarrier) {
        this.curCarrier = curCarrier;
    }

    public void setStages(Stage[] stages) {
        this.stages = stages;
    }

    public void setGeneralMark(double generalMark) {
        this.generalMark = generalMark;
    }

    public void setMaxMark(double maxMark) {
        this.maxMark = maxMark;
    }

    ////////////////////////////////////////////////////////////////////////////
    // get
    public double getDeviation() {    
        return deviation;
    }

    public Carrier getCurrentCarrier() {
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
