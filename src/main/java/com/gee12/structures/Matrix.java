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
    private double[] mins;
    private double[] maxes;

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
        defineMinMax();
    }

    public void init(int rows, int cols) {
        this.rows = rows;        
        this.cols = cols;
        m = new double[rows][];
        for (int i = 0; i < rows; i++) {
            m[i] = new double[cols];
        }
        mins = new double[rows];
        maxes = new double[rows];
    }

//    public void reset() {
//	for (int i = 0; i < rows; i++) {
//	    for (int j = 0; j < cols; j++) {
//		m[i][j] = 0;
//	    }
//	}
//    }
    
    //////////////////////////////////////////////////
    // operations
    public void defineMinMax() {
        mins = new double[rows];
        maxes = new double[rows];
        for (int i = 0; i < rows; i++) {
            mins[i] = Double.MAX_VALUE;
            maxes[i] = 0.;
        }
        defineMins();
        defineMaxes();
    }
    
    public void defineMins() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mins[i] > m[i][j]) {
                    mins[i] = m[i][j];
                }
            }
        }
    }
    public void defineMaxes() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (maxes[i] < m[i][j]) {
                    maxes[i] = m[i][j];
                }
            }
        }
    }
    

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
        defineMinMax();
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
    

    public double[] getMin() {
        return mins;
    }

    public double[] getMax() {
        return maxes;
    }    
}
