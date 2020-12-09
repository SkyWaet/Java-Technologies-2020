package com.skywaet.resourcepool.equation;

import com.skywaet.resourcepool.ResourcePool;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class EquationSolver implements Runnable {
    double[] coefficientA;
    double[] coefficientB;
    double[] coefficientC;
    int start, end;
    ResourcePool<File> filePool;
    ResourcePool<Thread> threadPool;

    public EquationSolver(double[] a, double[] b, double[] c, int s, int e, ResourcePool<File> filePool, ResourcePool<Thread> threadPool) {
        coefficientA = a;
        coefficientB = b;
        coefficientC = c;
        start = s;
        end = e;
        this.filePool = filePool;
        this.threadPool = threadPool;
    }

    @Override
    public void run() {
        File logFile = filePool.takeResource();
        try (FileWriter logWriter = new FileWriter(logFile,true)) {

            for (int i = start; i < end; i++) {
                String roots = findRoots(coefficientA[i], coefficientB[i], coefficientC[i]);
                logWriter.write(roots);
            }
            logWriter.close();
            filePool.releaseResource(logFile);
        } catch (IOException e) {
            filePool.releaseResource(logFile);
            System.out.println(e);
        }
    }

    public void execute(Runnable runnable) {
        Thread worker = threadPool.takeResource();
        try {
            runnable.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
        threadPool.releaseResource(worker);
    }


    public static String findRoots(double a, double b, double c) {
        double discriminant = pow(b, 2) - 4 * a * c;
        if (discriminant < 0) {
            return "No real roots\n";
        }
        if (a == 0) {
            if (b == 0) {
                if (c == 0) {
                    return "All real numbers\n";
                } else {
                    return "Incorrect equation\n";
                }
            }
            double x = -(c / b);
            return String.format("%f\n", x);
        }
        if (discriminant == 0) {
            double x = (-b / (2 * a));
            return String.format("D = 0 => One root: %f\n", x);
        }
        discriminant = sqrt(discriminant);
        double x1 = (-b + discriminant) / (2 * a);
        double x2 = (-b - discriminant) / (2 * a);
        return String.format("D > 0 => root 1: %f, root 2: %f\n", x1, x2);
    }
}


