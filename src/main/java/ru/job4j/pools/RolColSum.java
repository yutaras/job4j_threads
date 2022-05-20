package ru.job4j.pools;

/*Дан каркас класса. Ваша задача подсчитать суммы по строкам и столбцам квадратной матрицы.*/

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {
    public static class Sums {
        private int rowSum;
        private int colSum;

        public Sums(int rowSum, int colSum) {
            this.rowSum = rowSum;
            this.colSum = colSum;
        }

        public int getRowSum() {
            return rowSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Sums sums = (Sums) o;
            return rowSum == sums.rowSum && colSum == sums.colSum;
        }

        @Override
        public int hashCode() {
            return Objects.hash(rowSum, colSum);
        }
    }


    public static Sums[] sum(int[][] matrix) {
        int n = matrix.length;
        Sums[] rsl = new Sums[n];
        for (int i = 0; i < n; i++) {
            int rowSum = 0;
            int colSum = 0;
            for (int j = 0; j < n; j++) {
                rowSum += matrix[i][j];
                colSum += matrix[j][i];
            }
            rsl[i] = new Sums(rowSum, colSum);
        }
        return rsl;
    }

    public static Sums[] asyncSum1(int[][] matrix) throws ExecutionException, InterruptedException {
        int n = matrix.length;
        Sums[] rsl = new Sums[n];
        for (int i = 0; i < n; i++) {
            int finalI = i;
            CompletableFuture<Integer> rowSum = CompletableFuture.supplyAsync(() -> {
                int rowS = 0;
                for (int j = 0; j < n; j++) {
                    rowS += matrix[finalI][j];
                }
                return rowS;
            });
            CompletableFuture<Integer> colSum = CompletableFuture.supplyAsync(() -> {
                int colS = 0;
                for (int[] ints : matrix) {
                    colS += ints[finalI];
                }
                return colS;
            });
            rsl[i] = new Sums(rowSum.get(), colSum.get());
        }
        return rsl;
    }

}
