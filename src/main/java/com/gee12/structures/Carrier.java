package com.gee12.structures;

import java.util.Comparator;
import java.util.List;

/**
 * Перевозчик
 * @author Иван
 */
public class Carrier {
    
    public static Comparator<Carrier> COMPARATOR = new Comparator<Carrier>() {
	@Override
	public int compare(Carrier car1, Carrier car2) {
            final double num1 = car1.getRepeatNum();
            final double num2 = car2.getRepeatNum();
	    return (num1 < num2) ? 1 :
		    (num1 > num2) ? -1 : 0;
	}
    };
    
    public static final Double[] CAPACITIES = {10.,15.,20.};
    public static final Double[] VOLUMES = {200.,300.,400.};
    
//    public enum States {
//        NORM,
//        BEST,
//        ERROR
//    }
    
    protected int id;
    protected String name;
    protected double capacity;
    protected int repeatNum;
    protected Matrix matrix;
//    protected States state;
//    protected Project project;
    protected Stage[] stages;
//    protected Carrier carrier;
    
    protected double generalMark;
    protected double maxMark;
    protected double deviation;
    
    public Carrier() {
        this(0, "", 0, 0, new Matrix());
    }
    
    public Carrier(int id, String name, double capacity, int repNum, Matrix matrix) {
        this(id, name, capacity, repNum, matrix, new Stage[] {
            new Stage(), new Stage()});
    }
    public Carrier(int id, String name, double capacity, int repNum, Matrix matrix, Stage[] stages) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.repeatNum = repNum;
        this.matrix = matrix;
//        this.state = States.NORM;
        this.stages = stages;
//        this.project = new Project();
    }

//    public Carrier(Project proj) {
//        this.id = 0;
//        this.name = proj.getCurCarrierName();
//        this.capacity = 0;
//        this.repeatNum = 0;
//        this.matrix = new Matrix();
//        this.state = States.NORM;
//        this.project = proj;
//    }
        
    ////////////////////////////////////////////////////////////////////////////
    //
    public void defineMarks() {
        generalMark = 0;
        maxMark = 0;
        for (Criterion crit: getStage(Project.Stages.STAGE2_COOPERATION).getCriterions()) {
            try {
                double value = Double.parseDouble(crit.getValue());
                generalMark += value;
            } catch(Exception ex) {}
            try {
                double max = Double.parseDouble(crit.getMax());
                maxMark += max;
            } catch(Exception ex) {}
        }
        for (Criterion crit: getStage(Project.Stages.STAGE3_RATING).getCriterions()) {
            try {
                double value = Double.parseDouble(crit.getValue());
                generalMark += value;
            } catch(Exception ex) {}
            try {
                double max = Double.parseDouble(crit.getMax());
                maxMark += max;
            } catch(Exception ex) {}
        }    
        deviation = (maxMark - generalMark) / 100.;
    }
    
    public void defineCapacityRepeat(List<MatrixCriterion> crits) {
        if (crits == null) return;
        
        int[] repeats = new int[Matrix.DEF_SIZE];
        for (int i = 0; i < crits.size(); i++) {
            repeats[crits.get(i).getRow()]++;
        }
        int repeatRow = 0;
        int max = 0;
        for (int i = 0; i < Matrix.DEF_SIZE; i++) {
            if (repeats[i] > max) {
                max = repeats[i];
                repeatRow = i;
            }
        }
        
        capacity = CAPACITIES[repeatRow];
        repeatNum = max;
    }
    
    public void setId(int id) {
        this.id = id;
    }    

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    ////////////////////////////////////////////////////////////////////////////
    // set
//    public void setProject(Project proj) {
//        this.project = proj;
//    }
//    public void setState(States state) {
//        this.state = state;
//    }
    public void setRepeatNum(int repeatNum) {    
        this.repeatNum = repeatNum;
    }

    public void setMatrix(Matrix m) {
        this.matrix = m;
    }    
    
    public void setName(String name) {
        this.name = name;
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

    public void setDeviation(double deviation) {
        this.deviation = deviation;
    }
    
    ////////////////////////////////////////////////////////////////////////////
    // get
//    public Project getProject() {
//        return project;
//    }

    public Stage[] getStages() {
        return stages;
    }
    
    public Stage getStage(Project.Stages stage) {
        switch (stage) {
            case STAGE2_COOPERATION: 
                return stages[0];
            case STAGE3_RATING:
                return stages[1];
        }
        return null;
    }
    
    public double getGeneralMark() {
        return generalMark;
    }

    public double getMaxMark() {
        return maxMark;
    }

    public double getDeviation() {    
        return deviation;
    }
    
//    public States getState() {
//        return state;
//    }
    
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getCapacity() {
        return capacity;
    }

    public int getRepeatNum() {
        return repeatNum;
    }

    public Matrix getMatrix() {
        return matrix;
    }
}
