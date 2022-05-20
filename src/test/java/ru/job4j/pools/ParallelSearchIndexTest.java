package ru.job4j.pools;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ParallelSearchIndexTest {

    @Test
    public void whenParallelSearch() {
        Integer[] arr = new Integer[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        int rsl1 = ParallelSearchIndex.findIndex(arr, 50);
        int expected1 = 49;
        int rsl2 = ParallelSearchIndex.findIndex(arr, 101);
        int expected2 = -1;
        assertThat(rsl1, is(expected1));
        assertEquals(rsl2, expected2);
    }

    @Test
    public void whenLinealSearch() {
        String[] arr = new String[]{
                "Самолет", "Аэропорт", "Летчик"
        };
        int rsl1 = ParallelSearchIndex.findIndex(arr, "Летчик");
        int expected1 = 2;
        int rsl2 = ParallelSearchIndex.findIndex(arr, "Дом");
        int expected2 = -1;
        assertThat(rsl1, is(expected1));
        assertEquals(rsl2, expected2);
    }
}