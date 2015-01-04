package com.mycompany.newchoise;

import java.util.List;

/**
 *
 * @author Иван
 */
public class Project {
    
    public enum Stages {
        STAGE1_START,           // этап инициализации
        STAGE2_COOPERATION,    // этап сотрудничества
        STAGE3_RATING          // этап оценки
    }
    
    protected Carrier carrier;
    protected Stage[] stages;

    public Project(Carrier carrier, Stage[] stages) {
        this.carrier = carrier;
        this.stages = stages;
    }

    public Carrier getCarrier() {
        return carrier;
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
