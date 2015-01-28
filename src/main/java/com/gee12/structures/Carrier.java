package com.gee12.structures;

import java.util.Comparator;
import java.util.List;

/**
 * Перевозчик
 * @author Иван
 */
public class Carrier implements Comparator<Carrier>, Comparable<Carrier> {
    
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
    protected Stage[] stages;
    
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
        this.stages = stages;
    }
        
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
    
    ////////////////////////////////////////////////////////////////////////////
    //
    public void defineCapacityRepeat(List<MatrixCriterion> matrixCrits) {
        if (matrixCrits == null) return;
        
        int[] repeats = new int[Matrix.DEF_SIZE];
        for (int i = 0; i < matrixCrits.size(); i++) {
            repeats[matrixCrits.get(i).getRow()]++;
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
    
    ////////////////////////////////////////////////////////////////////////////
    // set
    public void setId(int id) {
        this.id = id;
    }    

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

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

    @Override
    public int compare(Carrier o1, Carrier o2) {
        return o2.getRepeatNum() - o1.getRepeatNum();
    }

    @Override
    public int compareTo(Carrier o) {
        return o.getRepeatNum() - repeatNum;
    }
}
