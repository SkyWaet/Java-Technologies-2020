import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class myTreeMap implements Map {
    myRBTree<Object, Object> container;

    public myTreeMap() {
        this.container = new myRBTree();
    }

    @Override
    public int size() {
        return this.container.size(this.container.root);
    }

    @Override
    public boolean isEmpty() {
        return this.container.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return this.container.contains(key);
    }

    @Override
    public Object get(Object key) {
        return this.container.get(key);
    }

    @Override
    public Object put(Object key, Object value) {
        return this.container.add(key, value, this.container.root);
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public Object remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set keySet() {
        return null;
    }

    @Override
    public Collection values() {
        return null;
    }

    @Override
    public Set<Entry> entrySet() {
        return null;
    }
}
