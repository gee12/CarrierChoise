package com.gee12.structures;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Иван
 */
public class MatrixCriterion {
    
    public static MatrixCriterion[] BASE_CRITERIONS = 
            new MatrixCriterion[] {
                new MatrixCriterion("Критерий Лапласа", Types.LAPLACE),
                new MatrixCriterion("Критерий Вальда", Types.VALD),
                new MatrixCriterion("Критерий оптимизма", Types.OPTIMISM),
                new MatrixCriterion("Критерий пессимизма", Types.PESSIMISM),
                new MatrixCriterion("Критерий Севиджа", Types.SAVAGE),
                new MatrixCriterion("Критерий Гурвица", Types.HURWITZ),
            };
    
    public enum Types {
        LAPLACE,
        VALD,
        OPTIMISM,
        PESSIMISM,
        SAVAGE,
        HURWITZ
    }
    
    protected String name;
    protected Matrix matrix;
    protected int row;
    protected double value;
    protected Types type;
    
    public MatrixCriterion(String name, Matrix m, Types type) {
        this.name = name;
        this.matrix = m;
        this.type = type;
        
        reBuild();
    }
    
    public MatrixCriterion(String name, Types type) {
        this.name = name;
        this.type = type;
        this.matrix = new Matrix();
        
        reBuild();
    }    
    
    public void reBuild() {
        reBuild(matrix);
    }
    
    public void reBuild(Matrix m) {
        this.matrix = m;
        
        switch (type) {
            case LAPLACE:
                buildLaplace(m);
                break;
            case VALD:
                buildVald(m);
                break;
            case OPTIMISM:
                buildOptimism(m);
                break;
            case PESSIMISM:
                buildPessimism(m);
                break;
            case SAVAGE:
                buildSavage(m);
                break;
            case HURWITZ:
                buildHurwitz(m);
                break;
        }
    }
    
    ////////////////////////////////////////////////////////////////////////////
    // build
    public void buildLaplace(Matrix m) {
        row = 0;
        value = m.getAt(row, row);
    }
    
    public void buildVald(Matrix m) {
        double max = m.getMin()[0];
        row = 0;
        for (int i = 1; i < m.getRows(); i++) {
            if (max < m.getMin()[i]) {
                max = m.getMin()[i];
                row = i;
            }
        }
        value = max;
    }
    
    public void buildOptimism(Matrix m) {
        double max = m.getMax()[0];
        row = 0;
        for (int i = 1; i < m.getRows(); i++) {
            if (max < m.getMax()[i]) {
                max = m.getMax()[i];
                row = i;
            }
        }
        value = max;
    }
    
    public void buildPessimism(Matrix m) {
        double min = m.getMin()[0];
        row = 0;
        for (int i = 1; i < m.getRows(); i++) {
            if (min > m.getMin()[i]) {
                min = m.getMin()[i];
                row = i;
            }
        }
        value = min;
    }
    
    public void buildSavage(Matrix m) {
        double min = m.getMax()[0];
        row = 0;
        for (int i = 1; i < m.getRows(); i++) {
            if (min > m.getMax()[i]) {
                min = m.getMax()[i];
                row = i;
            }
        }
        value = min;
    }
    
    public void buildHurwitz(Matrix m) {
        row = 0;
        value = m.getAt(row, row);
    }
    
    ////////////////////////////////////////////////////////////////////////////
    // get
    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }
    
    public double getCapacity(Double[] capacities) {
        if (capacities == null || capacities.length <= row)
            return 0;
        return capacities[row];
    }
    
    public int getRow() {
        return row;
    }

}
