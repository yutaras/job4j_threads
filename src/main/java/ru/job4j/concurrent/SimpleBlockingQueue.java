package ru.job4j.concurrent;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    private int limit;
    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();


    public SimpleBlockingQueue(int limit) {
        this.limit = limit;
    }

    public synchronized void offer(T value) throws InterruptedException {
        while (queue.size() == limit) {
            wait();
        }
        queue.add(value);
        notifyAll();
    }

    public synchronized T poll() throws InterruptedException {
        while (isEmpty()) {
            wait();
        }
        T rsl = queue.remove();
        notifyAll();
        return rsl;
    }

    public synchronized boolean isEmpty() {
        return queue.size() == 0;
    }
}
