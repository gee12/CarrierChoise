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
//    protected String curCarrierName;
//    protected Stage[] stages;
//    protected Carrier carrier;
//    
//    protected double generalMark;
//    protected double maxMark;
//    protected double deviation;

    ////////////////////////////////////////////////////////////////////////////
    public Project() {
        this.carriers = new ArrayList<Carrier>();
//        this.stages = new Stage[] { new Stage(), new Stage() };
//        this.curCarrierName = "";
//        this.carrier = Carrier.EMPTY;
    }
    
    public Project(List<Carrier> carriers) {
        this.carriers = carriers;
    }
//    public Project(Carrier car, Stage[] stages) {
//        this.carrier = car;
//        this.stages = stages;
//    }
//    public Project(List<Carrier> carriers, Stage[] stages) {
//        this.carriers = carriers;
//        this.stages = stages;
//    }
//    
//    public Project(List<Carrier> carriers, Stage[] stages, 
//            double generalMark, double maxMark, double deviation) {
//        this.carriers = carriers;
//        this.stages = stages;
//        this.generalMark = generalMark;
//        this.maxMark = maxMark;
//        this.deviation = deviation;
//    }   
    ////////////////////////////////////////////////////////////////////////////
    //
    
    
    ////////////////////////////////////////////////////////////////////////////
    // set
//    public void setCurrentCarrier(Carrier curCarrier) {
//        this.curCarrier = curCarrier;
//    }
    public Carrier getCarrier(String name) {
        for (Carrier car : carriers) {
            if (car.getName().equalsIgnoreCase(name))
                return car;
        }
        return null;
    }
//    public void setCurrentCarrier(String name) {
//        if (name == null) return;
//        for (Carrier car : carriers) {
//            if (name.equalsIgnoreCase(car.getName()))
//                this.curCarrier = car;
//        }
//    }
//        
//    public void setCurrentCarrier(int i) {
//        if (i < 0 || i > carriers.size()) return;
//        this.curCarrier = carriers.get(i);
//    }

    public void setCarriers(List<Carrier> carriers) {
        this.carriers = carriers;
    }

//    public void setCurCarrierName(String name) {
//        this.curCarrierName = name;
//    }

    ////////////////////////////////////////////////////////////////////////////
    // get
//    public String getCurCarrierName() {
//        return curCarrierName;
//    }


    public List<Carrier> getCarriers() {
        return carriers;
    }

 
}
