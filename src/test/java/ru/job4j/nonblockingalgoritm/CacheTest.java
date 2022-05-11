package ru.job4j.nonblockingalgoritm;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class CacheTest {

    @Test
    public void whenAdd() {
        Cache cache = new Cache();
        assertTrue(cache.add(new Base(1, 1)));
        assertFalse(cache.add(new Base(1, 2)));
        assertTrue(cache.add(new Base(2, 1)));
        assertThat(cache.size(), is(2));
    }

    @Test
    public void whenUpdate() {
        Cache cache = new Cache();
        assertTrue(cache.add(new Base(1, 1)));
        assertTrue(cache.update(new Base(1, 1)));
        assertFalse(cache.update(new Base(2, 1)));
    }

    @Test(expected = Exception.class)
    public void whenUpdateException() {
        Cache cache = new Cache();
        Base base = new Base(1, 1);
        Base base1 = new Base(1, 2);
        assertTrue(cache.add(base));
        assertTrue(cache.update(base1));
    }

    @Test
    public void delete() {
        Cache cache = new Cache();
        Base base1 = new Base(1, 1);
        Base base2 = new Base(2, 1);
        cache.add(base1);
        cache.add(base2);
        assertThat(cache.size(), is(2));
        cache.delete(base1);
        assertThat(cache.size(), is(1));
    }
}