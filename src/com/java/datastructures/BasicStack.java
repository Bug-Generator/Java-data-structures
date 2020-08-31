package com.java.datastructures;

public class BasicStack<X> implements BasicStackInterface<X>{
    private X[] data;
    private int stackPointer;

    public BasicStack() {
        data = (X[]) new Object[1000];
        stackPointer = 0;
    }

    @Override
    public void push(X element) {
        data[stackPointer++] = element;
    }

    @Override
    public X pop() {
        if (stackPointer == 0) {
            throw new IllegalStateException("No more items in stack");
        }
       return data[--stackPointer];
    }

    @Override
    public boolean contains(X item) {
        boolean found = false;
        for (int i = 0; i < stackPointer; i++) {
            if (data[i].equals(item)) {
                found = true;
                break;
            }
        }
        return found;
    }

    @Override
    public X access(X item) {
//        while (stackPointer > 0) {
//            X tmpItem = pop();
//            if(item.equals(tmpItem)) {
//                return tmpItem;
//            }
//        }
//        throw new IllegalArgumentException("Could not find this item in the stack " + item);

        if (contains(item)) {
            for (int i = 0; i < stackPointer; i++) {
                if (data[i].equals(item)){
                    X tmpItem = pop();
                    return tmpItem;
                }
            }
        }
        throw new IllegalArgumentException("Could not find this item in the stack " + item);
    }

    @Override
    public int size() {
        return stackPointer;
    }
}

