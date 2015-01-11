package com.gee12.structures;

/**
 * Перевозчик
 * @author Иван
 */
public class Carrier {
    
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
    
    public void setState(States state) {
        this.state = state;
    }
    
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
