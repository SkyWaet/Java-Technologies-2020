package com.skywaet.resourcepool.equation;

import com.skywaet.resourcepool.ResourcePool;
import com.skywaet.resourcepool.factories.FileFactory;
import com.skywaet.resourcepool.factories.ThreadFactory;

import java.io.File;

public class EquationMain {
    public static void main(String[] args) {
        int poolSize = 10;
        int maxWaitingTime = 1000;
        int start, end;
        double[] coefficientA = new double[10000];
        double[] coefficientB = new double[10000];
        double[] coefficientC = new double[10000];

        for (int i = 0; i < 10000; i++) {
            coefficientA[i] = (Math.random() * (10000 + 1)) - 5000;
            coefficientB[i] = (Math.random() * (10000 + 1)) - 5000;
            coefficientC[i] = (Math.random() * (10000 + 1)) - 5000;
        }

        ResourcePool<Thread> threadPool = new ResourcePool<>(new ThreadFactory(), maxWaitingTime, poolSize);
        ResourcePool<File> filePool = new ResourcePool<>(new FileFactory("logs"), maxWaitingTime);

        for (int i = 0; i < poolSize; i++) {
            start = (i * 10000) / poolSize;
            end = ((i + 1) * 10000) / poolSize;
            EquationSolver solverThread = new EquationSolver(coefficientA, coefficientB, coefficientC, start, end, filePool, threadPool);
            solverThread.execute(solverThread);
        }
        threadPool.terminatePool();
    }
}
