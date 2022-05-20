package ru.job4j.pools;

import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static ru.job4j.pools.RolColSum.asyncSum1;
import static ru.job4j.pools.RolColSum.sum;

public class RolColSumTest {

    @Test
    public void whenSum() {
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        RolColSum.Sums[] expected = {new RolColSum.Sums(6, 12),
                new RolColSum.Sums(15, 15),
                new RolColSum.Sums(24, 18)};
        assertThat(expected, is(sum(matrix)));
    }

    @Test
    public void whenAsyncSum() throws ExecutionException, InterruptedException {
        int[][] matrix = {{1, 2, 1}, {1, 2, 1}, {1, 2, 1}};
        RolColSum.Sums[] expected = {new RolColSum.Sums(4, 3),
                new RolColSum.Sums(4, 6),
                new RolColSum.Sums(4, 3)};
        assertThat(expected, is(asyncSum1(matrix)));
    }
}