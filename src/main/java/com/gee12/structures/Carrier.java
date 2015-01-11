package com.gee12.structures;

/**
 * Перевозчик
 * @author Иван
 */
public class Carrier {
    
    protected String name;
    protected double capacity;
    protected int repeatNum;
    protected Matrix matrix;

    public Carrier(String name, double capacity, int repNum, Matrix matrix) {
        this.name = name;
        this.capacity = capacity;
        this.repeatNum = repNum;
        this.matrix = matrix;
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
