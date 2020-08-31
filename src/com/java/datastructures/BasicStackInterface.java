package com.java.datastructures;

public interface BasicStackInterface<X> {
    void push(X element);
    X pop();
    boolean contains(X item);
    X access(X item);
    int size();
}
