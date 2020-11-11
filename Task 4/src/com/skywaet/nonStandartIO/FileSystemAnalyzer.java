package com.skywaet.nonStandartIO;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.NoSuchFileException;

public class FileSystemAnalyzer {
    File dir;
    File outputFile;

    public FileSystemAnalyzer(String dirPath, String FilePath) throws NoSuchFileException {
        File dir = new File(dirPath);
        if (dir.exists()) {
            this.dir = dir;
        } else {
            throw new NoSuchFileException("Directory '"+dir+ "' not found");
        }
        File outputFile = new File(FilePath);
        if (outputFile.exists()) {
            this.outputFile = outputFile;
        } else {
            throw new NoSuchFileException("File '"+outputFile+ "' not found");
        }
    }

    public void directoryAnalyser(File dir) {
        if (dir.isDirectory()) {
            try {
                for (File item : dir.listFiles()) {
                    if (item.isDirectory()) {
                        FileWriter outStream = new FileWriter(this.outputFile, true);
                        outStream.write("\uD83D\uDCC1 " + item.getName() + '\n');
                        outStream.close();
                        directoryAnalyser(item);

                    } else {
                        FileWriter outStream = new FileWriter(this.outputFile, true);
                        outStream.write(item.getName() + '\n');
                        outStream.close();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String @NotNull [] args) throws IOException {
        File dir = new File(args[0]);//.getCanonicalFile();
        System.out.println(args[0]);
        File outputFile = new File(args[1]).getCanonicalFile();

/*        if (outputFile.exists()) {
            outputFile.delete();
        }
        if (dir.exists()) {
            directoryAnalyser(dir, outputFile);
        } else {
            System.out.println("Directory not found");
        }*/
    }
}
