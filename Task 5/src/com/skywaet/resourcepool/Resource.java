package com.skywaet.resourcepool;

public class Resource<Long, T> {
    private T value;
    private long time;

    public Resource(long time, T value) {
        this.value = value;
        this.time = time;
    }

    public T getValue() {
        return this.value;
    }

    public long getTime() {
        return this.time;
    }

    public boolean isAlive(long maxWaitingTime) {
        long currTime = System.currentTimeMillis();
        return currTime - this.time < maxWaitingTime;
    }

}
