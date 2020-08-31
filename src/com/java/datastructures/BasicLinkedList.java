package com.java.datastructures;

public class BasicLinkedList<X> {
    private Node first;
    private Node last;
    private int nodeCount;

    BasicLinkedList() {
        first = last = null;
        nodeCount = 0;
    }

    public void add(X item) {
        Node newItem = new Node(item);

        // check if list is empty first
        if (first == null && last == null) {
            first = last = newItem;
        }
        else {
            last.setNextNode(newItem);
            last = newItem;
        }
        nodeCount++;
    }

    public void insert(X item, int position) {
        // check that position is <= nodeCount + 1 and position is realistic
        if (position > nodeCount + 1 || position < 1) {
            throw new IllegalArgumentException("Please provide viable position as list currently contains " + size() + " items");
        }
        // check that position is first
        else if (position == 1) {
            Node newItem = new Node(item);
            newItem.setNextNode(first);
            first = newItem;
        }
        // check that position is last
        else if (position == nodeCount + 1) {
            Node newItem = new Node(item);
            last.setNextNode(newItem);
            last = newItem;
        }
        // traverse and add to position
        else {
            int count = 1;
            Node newNode = new Node(item);
            Node prevNode = first;
            while (count < position - 1) {
                prevNode = prevNode.getNextNode();
                count++;
            }
            Node nextNde = prevNode.getNextNode();
            prevNode.setNextNode(newNode);
            prevNode = prevNode.getNextNode();
            prevNode.setNextNode(nextNde);
        }
        nodeCount ++;
    }

    public X removeAt(int position) {
        // check position exists in list
        if (position > size() || position < 1) {
            throw new IllegalArgumentException("Position is invalid integer or does not exist in list");
        }
        Node currentNode = first;
        Node removedNode = null;
        for (int i = 1; i < position - 1 && currentNode != null; i++) {
            currentNode = currentNode.getNextNode();
        }
        removedNode = currentNode.getNextNode();
        currentNode.setNextNode(removedNode.getNextNode());
        nodeCount--;
        return removedNode.getNodeItem();
    }

    public X remove() {
        // check if list is empty
        if (nodeCount == 0) {
            throw new IllegalStateException("List is empty baby");
        }

        X removeItem = first.getNodeItem();
        first = first.getNextNode();
        nodeCount--;
        return removeItem;
    }

    public int find(X item) {
        int count  = 1;
        Node currentNode = first;
        while (count <= size()) {
            if (currentNode.getNodeItem().equals(item)) {
                return count;
            }
            currentNode = currentNode.getNextNode();
            count++;
        }
        return -1;
    }

    public X get(int position) {
        // check position exists in list
        if (position > size() || position < 1) {
            throw new IllegalArgumentException("Position is invalid integer or does not exist in list");
        }
        int count = 1;
        Node currentNode = first;
        while (count < position) {
            currentNode = currentNode.getNextNode();
            count++;
        }
        return currentNode.getNodeItem();
    }

    @Override
    public String toString() {
        StringBuffer contents = new StringBuffer();
        Node curNode = first;

        while (curNode != null) {
            contents.append(curNode.getNodeItem());
            curNode = curNode.getNextNode();

            if(curNode != null) {
                contents.append(", ");
            }
        }
        return contents.toString();
    }

    public int size() {
        return nodeCount;
    }

    private class Node {
        private Node nextNode;
        private X nodeItem;

        public Node(X item) {
             this.nextNode = null;
             this.nodeItem = item;
        }

        public void setNextNode(Node nextNode) {
            this.nextNode = nextNode;
        }

        public Node getNextNode() {
            return nextNode;
        }

        public X getNodeItem() {
            return nodeItem;
        }
    }
}
