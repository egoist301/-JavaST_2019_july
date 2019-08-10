package by.training.matrix.service;

import by.training.matrix.bean.Matrix;
import by.training.matrix.service.diagonal.DiagonalInitializerWithLock;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Matrix matrix = new Matrix(12);
        DiagonalInitializerWithLock diagonalInitializer = new DiagonalInitializerWithLock(matrix, 6);
        diagonalInitializer.initializeMatrix();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(matrix);

    }
}
