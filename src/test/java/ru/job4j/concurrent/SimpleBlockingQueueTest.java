package ru.job4j.concurrent;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

public class SimpleBlockingQueueTest {

    @Test
    public void whenProducerConsumer() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        Thread producer = new Thread(
                () -> {
                    try {
                        queue.offer(6);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        Thread consumer = new Thread(
                () -> {
                    try {
                        queue.poll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        producer.start();
        consumer.start();
        Assert.assertThat(queue.poll(), is(6));

    }
}
