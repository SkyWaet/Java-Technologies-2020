package com.skywaet.nonStandartIO;

import java.io.File;

public class FileSystemAnalyzer {
    public static void directoryAnalyzer(File dir,int level) {
        if (dir.isDirectory()) {
            try {
                for (File item : dir.listFiles()) {

                    if (item.isDirectory()) {
                        System.out.println("\uD83D\uDCC1 " + item.getName());
                        directoryAnalyzer(item,level+1);

                    } else {
                        System.out.println(item.getName());
                    }
                }
            }catch (NullPointerException e){
                System.out.println(e);
            }

        }
    }


    public static void main(String[] args) {
        File dir = new File("D:\\учеба");
        // если объект представляет каталог
        directoryAnalyzer(dir,0);
    }
}
