package by.training.matrix.service;

import by.training.matrix.bean.Matrix;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Matrix matrix = new Matrix(12);
        DiagonalInitializer diagonalInitializer = new DiagonalInitializer(matrix, 6);
        diagonalInitializer.initializeMatrix();
        TimeUnit.SECONDS.sleep(3);
        System.out.println(matrix);

    }
}
