package com.skywaet.resourcepool;

import com.skywaet.resourcepool.factories.ResourceFactory;

import java.util.concurrent.*;

public class ResourcePool<T> {
    private LinkedBlockingQueue<Resource<Long, T>> pool;
    private LinkedBlockingQueue<Resource<Long, T>> activatedResources;
    private final ResourceFactory<T> resourceFactory;
    private final ExecutorService executor;
    private final long maxWaitingTime;
    private boolean isTerminated;

    public ResourcePool(ResourceFactory<T> resourceFactory, long maxWaitingTime, int size) {
        this.resourceFactory = resourceFactory;
        this.maxWaitingTime = maxWaitingTime;
        this.isTerminated = false;
        this.pool = new LinkedBlockingQueue<>();
        this.activatedResources = new LinkedBlockingQueue<>();
        this.executor = Executors.newCachedThreadPool();

        this.pool = new LinkedBlockingQueue<>(size);
        for (var i = 0; i < size; i++) {
            this.pool.offer(createNewResource());
        }
    }

    public ResourcePool(ResourceFactory<T> resourceFactory, long maxWaitingTime) {
        this(resourceFactory, maxWaitingTime, Runtime.getRuntime().availableProcessors());
    }

    public Resource<Long, T> createNewResource() {
        if (this.isTerminated) {
            throw new IllegalStateException("Unable to add new resource: the pool is terminated.");
        }

        return new Resource<>(System.currentTimeMillis(), this.resourceFactory.create());
    }

    public T takeResource() {
        if (this.isTerminated) {
            throw new IllegalStateException("Unable to add new resource: the pool is terminated.");
        }
        try {
            Resource<Long, T> requestedResource = this.pool.take();
            if (requestedResource.isAlive(this.maxWaitingTime)) {
                this.activatedResources.offer(requestedResource);
                return requestedResource.getValue();
            } else {
                Resource<Long, T> newResource = createNewResource();
                this.activatedResources.offer(newResource);
                return newResource.getValue();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void releaseResource(T resourceValue) {
        if (this.isTerminated) {
            throw new IllegalStateException("The pool is terminated.");
        } else {
            this.activatedResources.remove(resourceValue);
            Resource<Long, T> releasedResource = new Resource<>(System.currentTimeMillis(), resourceValue);
            this.pool.offer(releasedResource);
        }
    }

    public void terminatePool() {
        this.isTerminated = true;
        executor.shutdownNow();
    }

}
