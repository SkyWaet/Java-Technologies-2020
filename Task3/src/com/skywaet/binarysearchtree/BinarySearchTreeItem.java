package com.skywaet.binarysearchtree;
/**
    This class defines the structure of Binary Search Tree element
*/
public class BinarySearchTreeItem<Type> {
    public BinarySearchTreeItem<Type> left; //left child
    public BinarySearchTreeItem<Type> right;//right child
    public int key;//key of the element
    public Type value;//value of the element

    //class constructor
    public BinarySearchTreeItem(BinarySearchTreeItem<Type> left, BinarySearchTreeItem<Type> right, int key, Type value) {
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
    }
}
