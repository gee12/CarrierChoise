package com.gee12.structures;

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
        // average for rows
        double[] rowAverages = new double[m.getRows()];
        for (int i = 0; i < m.getRows(); i++) {
            for (int j = 0; j < m.getCols(); j++) {
                rowAverages[i] += m.getAt(i, j);
            }
            rowAverages[i] /= 3.;
        }    
        // max from averages
        double max = rowAverages[0];
        row = 0;
        for (int i = 1; i < rowAverages.length; i++) {
            if (max < rowAverages[i]) {
                max = rowAverages[i];
                row = i;
            }
        }        
        value = max;
    }
    
    public void buildVald(Matrix m) {
        double max = m.getRowMins()[0];
        row = 0;
        for (int i = 1; i < m.getRows(); i++) {
            if (max < m.getRowMins()[i]) {
                max = m.getRowMins()[i];
                row = i;
            }
        }
        value = max;
    }
    
    public void buildOptimism(Matrix m) {
        double max = m.getRowMaxes()[0];
        row = 0;
        for (int i = 1; i < m.getRows(); i++) {
            if (max < m.getRowMaxes()[i]) {
                max = m.getRowMaxes()[i];
                row = i;
            }
        }
        value = max;
    }
    
    public void buildPessimism(Matrix m) {
        double min = m.getRowMins()[0];
        row = 0;
        for (int i = 1; i < m.getRows(); i++) {
            if (min > m.getRowMins()[i]) {
                min = m.getRowMins()[i];
                row = i;
            }
        }
        value = min;
    }
    
    public void buildSavage(Matrix m) {
        double min = m.getRowMaxes()[0];
        row = 0;
        for (int i = 1; i < m.getRows(); i++) {
            if (min > m.getRowMaxes()[i]) {
                min = m.getRowMaxes()[i];
                row = i;
            }
        }
        value = min;
    }
    
    public void buildHurwitz(Matrix m) {
        // max/2 + min/2 for rows
        double[] minMaxHalfs = new double[m.getRows()];
        for (int i = 0; i < m.getRows(); i++) {
            minMaxHalfs[i] = m.getRowMaxes()[i] / 2. + m.getRowMins()[i] / 2.;
        }    
        // max from this rows
        double max = minMaxHalfs[0];
        row = 0;
        for (int i = 1; i < minMaxHalfs.length; i++) {
            if (max < minMaxHalfs[i]) {
                max = minMaxHalfs[i];
                row = i;
            }
        }        
        value = max;
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
