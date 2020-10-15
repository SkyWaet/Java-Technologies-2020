import java.util.NoSuchElementException;

public class myBst<Type> {
    public myBstItem<Type> root;

    public myBst() {
        this.root = null;
    }

    boolean isEmpty() {
        return this.root == null ? true : false;
    }

    public void add(int key, Type value, myBstItem<Type> root) {
        myBstItem<Type> newItem = new myBstItem<>(null, null, key, value);
        if (value == null) {
            throw new NullPointerException("Null values are not permitted!");
        } else if (this.isEmpty()) {
            this.root = newItem;
        } else if (key < root.key) {
            if (root.left == null) {
                root.left = newItem;
            } else {
                this.add(key, value, root.left);
            }
        } else if (key > root.key) {
            if (root.right == null) {
                root.right = newItem;
            } else {
                this.add(key, value, root.right);
            }
        } else {
            throw new IllegalArgumentException("Key is occupied");
        }
    }

    public boolean contains(int key) {
        myBstItem<Type> current = this.root;
        while (current != null) {
            if (key < current.key) {
                current = current.left;
            } else if (key > current.key) {
                current = current.right;
            } else {
                return true;
            }
        }
        return false;
    }

    public Type get(int key) {
        if (!this.contains(key)) {
            throw new NoSuchElementException("No Such Key in the tree");
        } else {
            myBstItem<Type> current = this.root;
            while (current != null) {
                if (key < current.key) {
                    current = current.left;
                } else if (key > current.key) {
                    current = current.right;
                } else {
                    return current.value;
                }
            }
            return null;
        }
    }

    public myBstItem<Type> findMin(myBstItem<Type> root) {
        myBstItem<Type> current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    public myBstItem<Type> findParent(myBstItem<Type> elem) {
        if (elem != this.root) {
            myBstItem<Type> current = this.root;
            while (current.left != elem || current.right != elem) {
                if (elem.key < current.key) {
                    current = current.left;
                } else if (elem.key > current.key) {
                    current = current.right;
                } else {
                    return current;
                }
            }
        }
        return null;
    }

    public Type remove(int key) {
        if (!this.contains(key)) {
            throw new NoSuchElementException("No Such Key in the tree");
        } else {
            myBstItem<Type> current = this.root;
            while (current != null) {
                if (key < current.key) {
                    current = current.left;
                } else if (key > current.key) {
                    current = current.right;
                } else {
                    myBstItem<Type> currParent = findParent(current);
                    myBstItem<Type> minInLeft = findMin(current.right);
                    minInLeft.left = current.left;
                    if (minInLeft != current.right) {
                        myBstItem<Type> buffParent = findParent(minInLeft);
                        buffParent.left = minInLeft.right;
                        minInLeft.right = current.right;
                    }
                    if (currParent != null) {
                        currParent.left = minInLeft;
                    }
                    return current.value;
                }
            }
            return null;
        }
    }
}
