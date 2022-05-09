package ru.job4j.concurrent;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

public class SimpleBlockingQueueTest {

    @Test
    public void WhenProducerConsumer() {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(10);
        Thread producer = new Thread(
                () -> {
                    queue.offer(6);
                }
        );
        Thread consumer = new Thread(
                () -> {
                    queue.poll();
                }
        );
        producer.start();
        consumer.start();
        Assert.assertThat(queue.poll(), is(6));

    }
}
