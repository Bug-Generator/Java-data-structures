package com.java.datastructures;

public class BasicBinaryTree<X extends Comparable<X>> {
    private Node root;
    private int size;

    public BasicBinaryTree() {
        this.root = null;
    }

    public int size() {
        return size;
    }

    public void add(X item) {
    Node node = new Node(item);

        // if that is the first node in the tree we make it root
        if (root == null) {
            this.root = node;
            System.out.println("Set root: " + node.getItem());
            this.size++;
        }
        else {
            insert(this.root, node);
        }
    }

    public boolean contains(X item) {
        Node currentNode = getNode(item);

        if (currentNode == null) {
            return false;
        } else {
            return true;
        }
    }

    public boolean delete(X item) {
        boolean deleted = false;

        Node currentNode = getNode(item);

        if (currentNode != null) {
            // if currentNode has no children just delete it
            if (currentNode.getRight() == null && currentNode.getLeft() == null) {
                unlink(currentNode, null);
                deleted = true;
            }
            // if node to delete only has right child remove it
            else if (currentNode.getRight() != null && currentNode.getLeft() == null) {
                unlink(currentNode, currentNode.getRight());
                deleted = true;
            }
            else if (currentNode.getLeft() != null && currentNode.getRight() == null) {
                unlink(currentNode, currentNode.getLeft());
                deleted = true;
            }
            // the node has both children
            else {
                // you can swap out the node with the right most leaf node on the left side
                Node child = currentNode.getLeft();
                while (child.getRight() != null && child.getLeft() != null) {
                    child = child.getRight();
                }
                // we have the right most leaf node. We can replace the current node with this node
                child.getParent().setRight(null); //remov the leaf node from it's current position

                child.setLeft(currentNode.getLeft());
                child.setRight(currentNode.getRight());

                unlink(currentNode, child);
                deleted = true;
            }
        }

        if (deleted) {
            this.size--;
        }
        return deleted;
    }

    private void unlink(Node currentNode, Node newNode) {
        // check if the item is the root node
        if (currentNode == this.root) {
            newNode.setLeft(currentNode.getLeft());
            newNode.setRight(currentNode.getRight());
            this.root = newNode;
        } else if (currentNode.getParent().getRight() == currentNode) {
            currentNode.getParent().setRight(newNode);
        } else {
            currentNode.getParent().setLeft(newNode);
        }
    }

    private Node getNode(X item) {
        Node currentNode = this.root;

        while (currentNode != null) {
            int val = item.compareTo(currentNode.getItem());

            if (val == 0) {
                return currentNode;
            }
            else if (val < 0 ) {
                currentNode = currentNode.getLeft();
            }
            else {
                currentNode = currentNode.getRight();
            }
        }
        return null;
    }

    public void insert(Node parent, Node child) {
        // if the child is greater than the parent it goes on the left
        if (child.getItem().compareTo(parent.getItem()) < 0) {
            // if the left node is null we've found our spot
            if (parent.getLeft() == null) {
                parent.setLeft(child);
                child.setParent(parent);
                this.size++;
            }
            // otherwise call function again and test for left or right
            else {
                insert(parent.getLeft(), child);
            }
        }
        // if child is greater than the parent, it goes on the right
        else if (child.getItem().compareTo(parent.getItem()) > 0 ) {
            // if the right node is null we've found our spot
            if (parent.getRight() == null) {
                parent.setRight(child);
                child.setParent(parent);
                this.size++;
            }
            // otherwise check again
            else {
                insert(parent.getRight(), child);
            }
        }
        // since BST has unique elements, we do nothing if the items are equal
    }

    private class Node {
        private Node left;
        private Node right;
        private Node parent;
        private X item;

        public Node(X item) {
            this.item = item;
            this.right = null;
            this.left = null;
            this.parent = null;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public X getItem() {
            return item;
        }

        public void setItem(X item) {
            this.item = item;
        }
    }
}
