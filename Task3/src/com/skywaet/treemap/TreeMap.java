package com.skywaet.treemap;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import com.skywaet.redblacktree.RedBlackTree;

/** Implementation of Map interface, based on RedBlackTree class. */

public class TreeMap implements Map {
    public RedBlackTree<Object, Object> container;

    public TreeMap() {
        this.container = new RedBlackTree();
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
