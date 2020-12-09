package com.skywaet.resourcepool.factories;

public class ThreadFactory implements ResourceFactory<Thread> {

    @Override
    public Thread create() {
        return new Thread();
    }

    @Override
    public Thread destroy() {
        return null;
    }
}
