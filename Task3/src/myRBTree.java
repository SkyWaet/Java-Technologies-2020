import java.util.NoSuchElementException;

public class myRBTree<keyType, valType> {
    myRBItem<keyType, valType> root;

    public myRBTree() {
        this.root = null;
    }

    boolean isEmpty() {
        return this.root == null;
    }

    public void bigRotation(myRBItem<keyType, valType> elem, int dir) {
        myRBItem<keyType, valType> dad = findParent(elem);
        myRBItem<keyType, valType> grandDad = findParent(dad);
        myRBItem<keyType, valType> ggDad = findParent(grandDad);
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

    public int blackHeight() {
        int bh = 1;
        myRBItem<keyType, valType> curr = this.root;
        while (curr.left != null) {
            curr = curr.left;
            if (curr.color == 'b') {
                bh++;
            }
        }
        return bh + 1;
    }

    public void balance(myRBItem<keyType, valType> elem) {
        myRBItem<keyType, valType> dad = findParent(elem);
        if (dad == null) {
            elem.color = 'b';
        } else {

            if (dad.color == 'r') {
                myRBItem<keyType, valType> grandDad = findParent(dad);
                if (grandDad == null) {
                    dad.color = 'b';
                } else {
                    myRBItem<keyType, valType> uncle = grandDad.left == dad ? grandDad.right : grandDad.left;
                    char color = uncle == null ? 'b' : uncle.color;
                    if (color == 'r') {
                        uncle.color = 'b';
                        dad.color = 'b';
                        grandDad.color = 'r';
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

    public valType add(keyType key, valType value, myRBItem<keyType, valType> root) {
        myRBItem<keyType, valType> newItem = new myRBItem<>(null, null, key, value, 'r');
        if (value == null) {
            throw new NullPointerException("Null values are not permitted!");
        } else if (this.isEmpty()) {
            this.root = newItem;
            this.root.color = 'b';            
        } else if ( root.compareTo(key) < 0) {
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
        myRBItem<keyType, valType> current = this.root;
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
            myRBItem<keyType, valType> current = this.root;
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

    public myRBItem<keyType, valType> findMin(myRBItem<keyType, valType> root) {
        myRBItem<keyType, valType> current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    public myRBItem<keyType, valType> findParent(myRBItem<keyType, valType> elem) {
        if (elem != this.root) {
          
            myRBItem<keyType, valType> current = this.root;
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
            myRBItem<keyType, valType> current = this.root;
            while (current != null) {
                if (current.compareTo(key) < 0) {
                    current = current.left;
                } else if (current.compareTo(key) > 0) {
                    current = current.right;
                } else {

                    myRBItem<keyType, valType> currParent = findParent(current);
                    myRBItem<keyType, valType> minInRight = findMin(current.right);
                    minInRight.left = current.left;
                    if (minInRight != current.right) {
                        myRBItem<keyType, valType> buffParent = findParent(minInRight);
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

    public void print(myRBItem<keyType, valType> root) {
        if (root.left != null) this.print(root.left);
        if (root.right != null) this.print(root.right);
    }

    public int size(myRBItem<keyType, valType> root) {
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
