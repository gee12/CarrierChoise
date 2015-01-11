package com.gee12.structures;

/**
 *
 * @author bondar
 */
public class Matrix {

    public static final int DEF_SIZE = 3;
    private double[][] m;
    private int rows;
    private int cols;
    private double min = 0;
    private double max = 0;

    public Matrix() {
	init(DEF_SIZE, DEF_SIZE);
    }
    
    public Matrix(int rows, int cols) {
        init(rows, cols);
    }

    public Matrix(double[][] m) {
	if (m == null) {
	    init(DEF_SIZE, DEF_SIZE);
	    return;
	}
	this.m = m;
	rows = m.length;
	if (rows > 0) {
	    cols = m[0].length;
	}
    }

    public void init(int rows, int cols) {
        this.rows = rows;        
        this.cols = cols;
        m = new double[rows][];
        for (int i = 0; i < rows; i++) {
            m[i] = new double[cols];
        }
    }

    public void reset() {
	for (int i = 0; i < rows; i++) {
	    for (int j = 0; j < cols; j++) {
		m[i][j] = 0;
	    }
	}
    }
    
    //////////////////////////////////////////////////
    // operations
    

    //////////////////////////////////////////////////
    // get
    public double[][] getMatrix() {
        return m;
    }
    
    public double getAt(int i, int j) {
	return m[i][j];
    }
    
    public void setAt(int i, int j, double value) {
	m[i][j] = value;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
    

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }    
}
