package com.gee12.structures;

/**
 *
 * @author bondar
 */
public class Matrix {

    private static final int DEF_SIZE = 3;
    private double[][] m;
    private int rows;
    private int cols;

    public Matrix() {
	reinit();
    }
    
    public Matrix(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
        m = new double[rows][];
        for (int i = 0; i < rows; i++) {
            m[i] = new double[cols];
        }
    }

    public Matrix(double[][] m) {
	if (m == null) {
	    reinit();
	    return;
	}
	this.m = m;
	rows = m.length;
	if (rows > 0) {
	    cols = m[0].length;
	}
    }

    public void reinit() {
	m = new double[][]{
	    {1, 0, 0, 0},
	    {0, 1, 0, 0},
	    {0, 0, 1, 0},
	    {0, 0, 0, 1}
	};
	rows = cols = DEF_SIZE;
    }

    //////////////////////////////////////////////////
    public void multiply(double s) {
	for (int i = 0; i < rows; i++) {
	    for (int j = 0; j < cols; j++) {
		m[i][j] *= s;
	    }
	}
    }
    
    public Matrix multiplyConst(double s) {
	for (int i = 0; i < rows; i++) {
	    for (int j = 0; j < cols; j++) {
		m[i][j] *= s;
	    }
	}
        return new Matrix(m);
    }
    
    public Matrix multiply(Matrix other) {
	if (other == null) {
	    return null;
	}
	if (other.rows != cols) {
	    throw new RuntimeException("Столбцов 1 матрицы != строк 2 матрицы (размеры матриц не совпадают)");
	}
	double[][] res = new double[rows][other.cols];
	for (int i = 0; i < rows; i++) {
	    for (int j = 0; j < other.cols; j++) {
		for (int k = 0; k < cols; k++) {
		    res[i][j] += m[i][k] * other.m[k][j];
		}
	    }
	}
	return new Matrix(res);
    }
    
    //////////////////////////////////////////////////
    // возвращает вектор-СТРОКУ
    public final double[] applyTransform(double[] vector) {
	final int sizeVector = vector.length;
	if (sizeVector != rows) {
	    throw new RuntimeException("Строк матрицы != размеру вектора");
	}
	double[] res = new double[sizeVector];
	for (int i = 0; i < cols; i++) {
	    for (int j = 0; j < rows; j++) {
		res[i] += vector[j] * m[j][i];
	    }
	}
	return res;
    }

    //////////////////////////////////////////////////
    public void reset() {
	for (int i = 0; i < rows; i++) {
	    for (int j = 0; j < cols; j++) {
		int v = (i == j) ? 1 : 0;
		m[i][j] = v;
	    }
	}
    }

    //////////////////////////////////////////////////
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
}
