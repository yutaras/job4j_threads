package ru.job4j.nonblockingalgoritm;

import org.junit.Test;
import ru.job4j.concurrent.Count;
import ru.job4j.concurrent.CountTest;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class CASCountTest {

    @Test
    public void whenExecute2ThreadThen2() throws InterruptedException {
        CASCount casCount = new CASCount();
        Thread first = new Thread(
                casCount::increment
        );
        Thread second = new Thread(
                () -> {
                    for (int i = 0; i < 5; i++) {
                        casCount.increment();
                    }
                }
        );
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(casCount.get(), is(6));
    }
}