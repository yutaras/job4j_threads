package ru.job4j.concurrent;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class UserStorageTest {

    @Test
    public void addUser() {
        User user = new User(1, 55);
        UserStorage userStorage = new UserStorage();
        assertTrue(userStorage.add(user));
        assertThat(user.getAmount(), is(55));
    }

    @Test
    public void deleteUser() {
        User user1 = new User(1, 55);
        User user2 = new User(2, 55);
        UserStorage userStorage = new UserStorage();
        assertTrue(userStorage.add(user1));
        assertTrue(userStorage.delete(user1));
        assertFalse(userStorage.delete(user2));
    }

    @Test
    public void updateUserAndTransfer() {
        User user1 = new User(1, 55);
        User user1New = new User(1, 60);
        User user2 = new User(2, 0);
        UserStorage userStorage = new UserStorage();
        assertTrue(userStorage.add(user1));
        assertTrue(userStorage.add(user2));
        assertTrue(userStorage.update(user1New));
        assertTrue(userStorage.transfer(1,2, 60));
    }
}