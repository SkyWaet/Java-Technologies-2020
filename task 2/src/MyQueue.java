import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

public class MyQueue<Type> implements Queue<Type> {

    public MyQueue() {
        super();
        this.storage = new MyList<>();
    }

    private MyList<Type> storage;

    @Override
    public int size() {
        return this.storage.size();
    }

    @Override
    public boolean isEmpty() {
        return this.storage.isEmpty();
    }

    @Override
    public boolean add(Type type) {
        if (type == null) {
            throw new NullPointerException("Null values are not permitted");
        } else {
            return this.storage.add(type);
        }
    }

    @Override
    public boolean offer(Type type) {
        if (type == null) {
            throw new NullPointerException("Null values are not permitted");
        } else {
            return this.storage.add(type);
        }
    }

    @Override
    public Type remove() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("The queue is empty. There is nothing to remove");
        } else {
            return this.storage.remove(0);
        }
    }

    @Override
    public Type poll() {
        if (this.isEmpty()) {
            return null;
        } else {
            return this.storage.remove(0);
        }
    }

    @Override
    public Type element() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("The queue is empty. There is nothing to retrieve");
        } else {
            return this.storage.get(0);
        }
    }

    @Override
    public Type peek() {
        if (this.isEmpty()) {
            return null;
        } else {
            return this.storage.get(0);
        }
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException("This operation is not supported");
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
}
