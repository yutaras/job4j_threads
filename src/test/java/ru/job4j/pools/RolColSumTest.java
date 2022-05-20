package ru.job4j.pools;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
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
    public void asyncSum() {
    }
}