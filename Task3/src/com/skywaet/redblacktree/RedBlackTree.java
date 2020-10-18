package com.skywaet.redblacktree;

import java.util.NoSuchElementException;

/**
 * My realization of Red Black Tree.
 */

public class RedBlackTree<keyType, valType> {
    public RedBlackTreeItem<keyType, valType> root;

    public RedBlackTree() {
        this.root = null;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    /**
     * Unique method for Red Black Tree. It is used for black height computation
     */
    public int blackHeight() {
        int bh = 1;
        RedBlackTreeItem<keyType, valType> curr = this.root;
        while (curr.left != null) {
            curr = curr.left;
            if (curr.color) {
                bh++;
            }
        }
        return bh + 1;
    }
    /** blackHeight end */

    /**
     * These methods are used for balancing the tree.
     */
    void bigRotation(RedBlackTreeItem<keyType, valType> elem, int dir) {
        RedBlackTreeItem<keyType, valType> dad = findParent(elem);
        RedBlackTreeItem<keyType, valType> grandDad = findParent(dad);
        RedBlackTreeItem<keyType, valType> ggDad = findParent(grandDad);
        if (dir == 0) {
            grandDad.left = dad.right;
            dad.right = grandDad;
        } else {
            grandDad.right = dad.left;
            dad.left = grandDad;
        }
        dad.color = true;
        grandDad.color = false;
        if (ggDad == null) {
            this.root = dad;
        } else {
            if (ggDad.left == grandDad) {
                ggDad.left = dad;
            } else {
                ggDad.right = dad;
            }
        }
    }

    void balance(RedBlackTreeItem<keyType, valType> elem) {
        RedBlackTreeItem<keyType, valType> dad = findParent(elem);
        if (dad == null) {
            elem.color = true;
        } else {

            if (!dad.color) {
                RedBlackTreeItem<keyType, valType> grandDad = findParent(dad);
                if (grandDad == null) {
                    dad.color = true;
                } else {
                    RedBlackTreeItem<keyType, valType> uncle = grandDad.left == dad ? grandDad.right : grandDad.left;
                    boolean color = uncle == null ? true : uncle.color;
                    if (!color) {
                        uncle.color = true;
                        dad.color = true;
                        grandDad.color = false;
                        this.balance(grandDad);
                    } else {

                        if (elem == dad.right && dad == grandDad.left) {

                            elem.left = dad;
                            grandDad.left = elem;
                            dad.right = null;
                            bigRotation(dad, 0);
                        } else if (elem == dad.left && dad == grandDad.right) {

                            elem.right = dad;
                            grandDad.right = elem;
                            dad.left = null;
                            bigRotation(dad, 1);
                        } else if (dad == grandDad.left) {

                            bigRotation(elem, 0);
                        } else {

                            bigRotation(elem, 1);
                        }
                    }
                }
            }
        }
    }

    /**
     * End of balance section
     */

    public valType add(keyType key, valType value, RedBlackTreeItem<keyType, valType> root) {
        RedBlackTreeItem<keyType, valType> newItem = new RedBlackTreeItem<>(null, null, key, value, false);
        if (value == null) {
            throw new NullPointerException("Null values are not permitted!");
        } else if (this.isEmpty()) {
            this.root = newItem;
            this.root.color = true;
        } else if (root.compareTo(key) < 0) {
            if (root.left == null) {
                root.left = newItem;
                balance(root.left);

            } else {
                this.add(key, value, root.left);
            }
        } else if (root.compareTo(key) > 0) {
            if (root.right == null) {
                root.right = newItem;
                //System.out.println("Root key: " + root.key);
                balance(root.right);

            } else {
                this.add(key, value, root.right);
            }
        } else {
            valType oldVal = root.value;
            root.value = value;
            return oldVal;
        }
        return null;
    }

    public boolean contains(keyType key) {
        RedBlackTreeItem<keyType, valType> current = this.root;
        while (current != null) {
            if (current.compareTo(key) < 0) {
                current = current.left;
            } else if (current.compareTo(key) > 0) {
                current = current.right;
            } else {
                return true;
            }
        }
        return false;
    }

    public valType get(keyType key) {
        if (!this.contains(key)) {
            throw new NoSuchElementException("No Such Key in the tree");
        } else {
            RedBlackTreeItem<keyType, valType> current = this.root;
            while (current != null) {
                if (current.compareTo(key) < 0) {
                    current = current.left;
                } else if (current.compareTo(key) > 0) {
                    current = current.right;
                } else {
                    return current.value;
                }
            }
            return null;
        }
    }

    public RedBlackTreeItem<keyType, valType> findMin(RedBlackTreeItem<keyType, valType> root) {
        RedBlackTreeItem<keyType, valType> current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    public RedBlackTreeItem<keyType, valType> findParent(RedBlackTreeItem<keyType, valType> elem) {
        if (elem != this.root) {

            RedBlackTreeItem<keyType, valType> current = this.root;
            while (current.left != elem && current.right != elem) {
                if (elem.compareTo(current.key) > 0) {
                    current = current.left;
                } else if (elem.compareTo(current.key) < 0) {
                    current = current.right;
                } else {
                    return current;
                }
            }
            return current;
        }
        return null;
    }

    public valType remove(keyType key) {
        if (!this.contains(key)) {
            throw new NoSuchElementException("No Such Key in the tree");
        } else {
            RedBlackTreeItem<keyType, valType> current = this.root;
            while (current != null) {
                if (current.compareTo(key) < 0) {
                    current = current.left;
                } else if (current.compareTo(key) > 0) {
                    current = current.right;
                } else {

                    RedBlackTreeItem<keyType, valType> currParent = findParent(current);
                    RedBlackTreeItem<keyType, valType> minInRight = findMin(current.right);
                    minInRight.left = current.left;
                    if (minInRight != current.right) {
                        RedBlackTreeItem<keyType, valType> buffParent = findParent(minInRight);
                        buffParent.left = minInRight.right;
                        minInRight.right = current.right;
                    }
                    if (currParent != null) {
                        if (current == currParent.left) {
                            currParent.left = minInRight;
                        } else {
                            currParent.right = minInRight;
                        }

                    } else {
                        this.root = minInRight;
                    }
                    balance(minInRight);
                    return current.value;
                }
            }
            return null;
        }
    }

    public void print(RedBlackTreeItem<keyType, valType> root) {
        if (root.left != null) this.print(root.left);
        System.out.print(root.value + " ");
        if (root.right != null) this.print(root.right);
    }

    public int size(RedBlackTreeItem<keyType, valType> root) {
        if (this.isEmpty()) {
            return 0;
        } else {
            int size = 1;
            if (root.left != null) {
                size += this.size(root.left);
            }
            if (root.right != null) {
                size += this.size(root.right);
            }
            return size;
        }
    }
}
