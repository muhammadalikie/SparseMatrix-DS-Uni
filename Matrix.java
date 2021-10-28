import javafx.util.Pair;

import java.util.ArrayList;

public class Matrix {

    private int rows;
    private int cols;

    private ArrayList<ArrayList<Integer>> matrix;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        initial();
    }

    private void initial() {
        matrix = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            matrix.add(new ArrayList<>());
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix.get(i).add(j, 0);
            }
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }


    public void insert(int row, int col, int val) {

        matrix.get(row).add(col, val);

    }

    public void remove(int row, int col) {

        matrix.get(row).set(col, 0);

    }

    public int get(int row, int col) {

        return matrix.get(row).get(col);


    }

    public Pair<Integer, Integer> size() {

        return new Pair<>(rows, cols);
    }

    public static Matrix sparseToArray2D(Matrix sparse) {
        int maxRow = 0;

        for (int i = 0; i < sparse.rows; i++) {
            if (maxRow < sparse.get(i, 1)) {
                maxRow = sparse.get(i, 1);
            }
        }

        int maxCol = 0;

        for (int i = 0; i < sparse.rows; i++) {
            if (maxCol < sparse.get(i, 2)) {
                maxCol = sparse.get(i, 2);
            }
        }

        Matrix matrix = new Matrix(maxRow, maxCol);

        for (int i = 0; i < maxRow; i++) {
            for (int j = 0; j < maxCol; j++) {
                matrix.insert(sparse.get(j, 1), sparse.get(j, 2), sparse.get(j, 3));
            }
        }

        return matrix;
    }

    public static Matrix array2DToSparse(Matrix array2D) {
        int size = 0;

        for (int i = 0; i < array2D.getRows(); i++) {
            for (int j = 0; j < array2D.getCols(); j++) {
                if (array2D.get(i, j) != 0) {
                    size++;
                }
            }
        }


        Matrix sparse = new Matrix(size, 3);

        int cRow = 0;

        for (int i = 0; i < array2D.getRows(); i++) {
            for (int j = 0; j < array2D.getCols(); j++) {
                if (array2D.get(i, j) != 0) {
                    sparse.insert(cRow, 1, i);
                    sparse.insert(cRow, 2, j);
                    sparse.insert(cRow, 3, array2D.get(i, j));
                    cRow++;
                }
            }
        }

        return sparse;
    }

    public static Matrix add(Matrix matrix1, Matrix matrix2) {

        Matrix matrix = new Matrix(matrix1.rows, matrix1.cols);
        for (int i = 0; i < matrix.rows; i++) {
            for (int j = 0; j < matrix.cols; j++) {
                matrix.insert(i, j, matrix1.get(i, j) + matrix2.get(i, j));
            }
        }
        return matrix;
    }

    public static Matrix sub(Matrix matrix1, Matrix matrix2) {

        Matrix matrix = new Matrix(matrix1.rows, matrix1.cols);
        for (int i = 0; i < matrix.rows; i++) {
            for (int j = 0; j < matrix.cols; j++) {
                matrix.insert(i, j, matrix1.get(i, j) - matrix2.get(i, j));
            }
        }
        return matrix;
    }

    public void printSparse() {

        for (int j = 0; j < this.getRows(); j++) {

            System.out.printf("%d ", this.get(j, 1));

        }

        System.out.println();
        for (int j = 0; j < this.getRows(); j++) {

            System.out.printf("%d ", this.get(j, 2));

        }
        System.out.println();
        for (int j = 0; j < this.getRows(); j++) {

            System.out.printf("%d ", this.get(j, 3));

        }


    }

    public void printMatrix() {


        for (int j = 0; j < this.getRows(); j++) {
            for (int i = 0; i < this.getCols(); i++) {
                System.out.printf("%d ", this.get(j, i));
            }
            System.out.println();
        }


    }
}