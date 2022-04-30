package ru.job4j;

public class Node<T> {
    private final Node<T> next;
    private final T value;

    public Node(final Node<T> next, T value) {
        this.next = next;
        this.value = value;
    }

    public Node<T> getNext() {
        return next;
    }

    public T getValue() {
        return value;
    }

}