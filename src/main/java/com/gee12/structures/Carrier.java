package com.gee12.structures;

import java.util.List;

/**
 * Перевозчик
 * @author Иван
 */
public class Carrier {
    
    public static final Double[] CAPACITIES = {10.,15.,20.};
    public static final Double[] VOLUMES = {200.,300.,400.};
    
    public enum States {
        NORM,
        BEST,
        ERROR
    }
    
    protected int id;
    protected String name;
    protected double capacity;
    protected int repeatNum;
    protected Matrix matrix;
    protected States state;

    public Carrier(int id, String name, double capacity, int repNum, Matrix matrix) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.repeatNum = repNum;
        this.matrix = matrix;
        this.state = States.NORM;
    }
    
    ////////////////////////////////////////////////////////////////////////////
    //
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
    
    ////////////////////////////////////////////////////////////////////////////
    // set
    public void setState(States state) {
        this.state = state;
    }
    
    public void setMatrix(Matrix m) {
        this.matrix = m;
    }    
    
    public void setName(String name) {
        this.name = name;
    }
    
    ////////////////////////////////////////////////////////////////////////////
    // get
    public States getState() {
        return state;
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
}
