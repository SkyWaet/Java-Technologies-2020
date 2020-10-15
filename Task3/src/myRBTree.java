import java.util.NoSuchElementException;

public class myRBTree<Type> {
    myRBItem<Type> root;

    public myRBTree() {
        this.root = null;
    }

    boolean isEmpty() {
        return this.root == null ? true : false;
    }

    public void bigRotation(myRBItem<Type> elem, int dir) {
        myRBItem<Type> dad = findParent(elem);
        myRBItem<Type> grandDad = findParent(dad);
        myRBItem<Type> ggDad = findParent(grandDad);
        if (dir == 0) {
            grandDad.left = dad.right;
            dad.right = grandDad;
        } else {
            grandDad.right = dad.left;
            dad.left = grandDad;
        }
        dad.color = 'b';
        grandDad.color = 'r';
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

    public void balance(myRBItem<Type> elem) {
        myRBItem<Type> dad = findParent(elem);
        if (dad == null) {
            elem.color = 'b';
        } else {

            System.out.println("Dad key = " + dad.key + "; Dad color = " + dad.color);
            System.out.println("Elem key = " + elem.key + "; Elem color = " + elem.color);
            if (dad.color == 'r') {
                myRBItem<Type> grandDad = findParent(dad);
                if (grandDad == null) {
                    dad.color = 'b';
                } else {
                    myRBItem<Type> uncle = grandDad.left == dad ? grandDad.right : grandDad.left;
                    char color = uncle == null ? 'b' : uncle.color;
                    if (color == 'r') {
                        uncle.color = 'b';
                        dad.color = 'b';
                        grandDad.color = 'r';
                        this.balance(grandDad);
                    } else {

                        if (elem == dad.right && dad == grandDad.left) {
                            System.out.println("left dad, right elem");
                            elem.left = dad;
                            grandDad.left = elem;
                            dad.right = null;
                            bigRotation(dad, 0);
                        } else if (elem == dad.left && dad == grandDad.right) {
                            System.out.println("right dad, left elem");
                            elem.right = dad;
                            grandDad.right = elem;
                            dad.left = null;
                            bigRotation(dad, 1);
                        } else if (dad == grandDad.left) {
                            System.out.println("left dad, left elem");
                            bigRotation(elem, 0);
                        } else {
                            System.out.println("right dad, right elem");
                            bigRotation(elem, 1);
                        }
                    }
                }
            }
        }
    }

    public void add(int key, Type value, myRBItem<Type> root) {
        myRBItem<Type> newItem = new myRBItem<>(null, null, key, value, 'r');
        if (value == null) {
            throw new NullPointerException("Null values are not permitted!");
        } else if (this.isEmpty()) {
            this.root = newItem;
            this.root.color = 'b';
            //balance(root);
        } else if (key < root.key) {
            if (root.left == null) {
                root.left = newItem;
                balance(root.left);
            } else {
                this.add(key, value, root.left);
            }
        } else if (key > root.key) {
            if (root.right == null) {
                root.right = newItem;
                balance(root.right);
            } else {
                this.add(key, value, root.right);
            }
        } else {
            throw new IllegalArgumentException("Key is occupied");
        }
    }

    public boolean contains(int key) {
        myRBItem<Type> current = this.root;
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
            myRBItem<Type> current = this.root;
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

    public myRBItem<Type> findMin(myRBItem<Type> root) {
        myRBItem<Type> current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    public myRBItem<Type> findParent(myRBItem<Type> elem) {
        if (elem != this.root) {
            myRBItem<Type> current = this.root;
            while (current.left != elem && current.right != elem) {
                if (elem.key < current.key) {
                    current = current.left;
                } else if (elem.key > current.key) {
                    current = current.right;
                } else {
                    return current;
                }
            }
            return current;
        }
        return null;
    }

    public Type remove(int key) {
        if (!this.contains(key)) {
            throw new NoSuchElementException("No Such Key in the tree");
        } else {
            myRBItem<Type> current = this.root;
            while (current != null) {
                if (key < current.key) {
                    current = current.left;
                } else if (key > current.key) {
                    current = current.right;
                } else {
                    myRBItem<Type> currParent = findParent(current);
                    myRBItem<Type> minInLeft = findMin(current.right);
                    minInLeft.left = current.left;
                    if (minInLeft != current.right) {
                        myRBItem<Type> buffParent = findParent(minInLeft);
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

    public void print(myRBItem<Type> root) {
        System.out.print(root.value + " " + root.color + "; ");
        if (root.left != null) this.print(root.left);
        if (root.right != null) this.print(root.right);
    }

}
