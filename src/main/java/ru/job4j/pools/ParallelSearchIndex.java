package ru.job4j.pools;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelSearchIndex<T> extends RecursiveTask<Integer> {
    private final T[] array;
    private final T el;
    private final int from;
    private final int to;


    public ParallelSearchIndex(T[] array, T el, int from, int to) {
        this.array = array;
        this.el = el;
        this.from = from;
        this.to = to;
    }

    public int indexOf() {
        int rst = -1; /* если элемента нет в массиве, то возвращаем -1. */
        for (int index = from; index <= to; index++) {
            if (array[index] == el) {
                rst = index;
                break;
            }
        }
        return rst;
    }

    protected Integer compute() {
        if (to - from < 11) {
            return indexOf();
        }
        int mid = (from + to) / 2;
        ParallelSearchIndex<T> leftSearch = new ParallelSearchIndex<>(array, el, from, mid);
        ParallelSearchIndex<T> rightSearch = new ParallelSearchIndex<>(array, el,
                mid + 1, to);
        leftSearch.fork();
        rightSearch.fork();
        Integer left = leftSearch.join();
        Integer right = rightSearch.join();
        return Math.max(left, right);
    }

    public static <T> int findIndex(T[] array, T val) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelSearchIndex<>(array, val,
                0, array.length - 1));
    }
}
