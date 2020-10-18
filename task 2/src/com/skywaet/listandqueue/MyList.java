package com.skywaet.listandqueue;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MyList<Type> implements List<Type> {
    private MyListItem<Type> head;
    private MyListItem<Type> tail;
    private int size;

    public MyList() {
        tail = null;
        head = null;
        size = 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.head == null;
    }

    @Override
    public boolean add(Type type) {
        if (type == null) {
            throw new NullPointerException("Null values are not permitted");
        } else if (this.isEmpty()) {
            MyListItem<Type> newElem = new MyListItem(null, null, type);
            this.size++;
            this.head = newElem;
            this.tail = newElem;
            return true;
        } else {
            MyListItem<Type> newElem = new MyListItem(this.tail, null, type);
            this.size++;
            this.tail.next = newElem;
            this.tail = newElem;
            return true;
        }
    }

    @Override
    public Type remove(int index) {
        if (index >= this.size()) {
            throw new IndexOutOfBoundsException("Index is greater than list size");
        } else if (index < 0) {
            throw new IndexOutOfBoundsException("Negative indexes are not supported");
        } else if (this.size() == 1) {
            Type data = this.head.data;
            this.head = null;
            this.tail = null;
            this.size--;
            return data;
        } else if (index == 0) {
            Type data = this.head.data;
            this.head.next.prev = null;
            this.head = this.head.next;
            this.size--;
            return data;
        } else {
            int currInd = 0;
            MyListItem<Type> current = this.head;
            while (currInd < index) {
                current = current.next;
                currInd++;
            }
            current.prev.next = current.next;
            current.next.prev = current.prev;
            this.size--;
            return current.data;
        }
    }


    @Override
    public boolean contains(Object o) {
        if (o == null) {
            throw new NullPointerException("Null elements are not supported");
        } else if (this.size() == 0) {
            return false;
        } else if (this.size() == 1) {
            return this.head.data == o;
        } else {
            MyListItem<Type> current = this.head;
            while (current.next != null) {
                if (current.data == o) {
                    return true;
                }
                current = current.next;
            }
            return false;
        }
    }

    @Override
    public Type get(int index) {
        if (index >= this.size()) {
            throw new IndexOutOfBoundsException("Index is greater than list size");
        } else if (index < 0) {
            throw new IndexOutOfBoundsException("Negative index are not supported");
        } else {
            int currInd = 0;
            MyListItem<Type> current = this.head;
            while (currInd < index) {
                current = current.next;
                currInd++;
            }
            return current.data;
        }
    }

    @Override
    public void add(int index, Type element) {
        if (index >= this.size()) {
            throw new IndexOutOfBoundsException("Index is greater than list size");
        } else if (index < 0) {
            throw new IndexOutOfBoundsException("Negative index are not supported");
        } else if (element == null) {
            throw new NullPointerException("Null elements are not permitted");
        } else {
            int currInd = 0;
            MyListItem<Type> current = this.head;
            while (currInd < index) {
                current = current.next;
                currInd++;
            }
            MyListItem<Type> newElem = new MyListItem(current.prev, current, element);
            this.size++;
            current.prev.next = newElem;
            current.prev = newElem;
        }
    }

    @Override
    public Iterator<Type> iterator() {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("This operation is not supported");
    }


    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    @Override
    public boolean addAll(Collection<? extends Type> c) {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    @Override
    public boolean addAll(int index, Collection<? extends Type> c) {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    @Override
    public Type set(int index, Type element) {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    @Override
    public ListIterator<Type> listIterator() {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    @Override
    public ListIterator<Type> listIterator(int index) {
        throw new UnsupportedOperationException("This operation is not supported");
    }

    @Override
    public List<Type> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("This operation is not supported");
    }
}
