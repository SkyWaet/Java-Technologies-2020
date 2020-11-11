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
        try {
            File dir = new File(dirPath).getCanonicalFile();
            if (dir.exists()) {
                this.dir = dir;
            } else {
                throw new NoSuchFileException("Directory '" + dir + "' not found. Current working directory is "+System.getProperty("user.dir"));
            }
        } catch (NoSuchFileException e) {
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            File outputFile = new File(FilePath).getCanonicalFile();
            if (outputFile.exists()) {
                this.outputFile = outputFile;
            } else {
                throw new NoSuchFileException("File '" + outputFile + "' not found. Current working directory is "+System.getProperty("user.dir"));
            }
        } catch (NoSuchFileException e) {
            throw e;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getDir() {
        return this.dir;
    }

    public File getOutputFile() {
        return this.outputFile;
    }

    public void directoryAnalyser(File dir) {
        if (dir.isDirectory()) {
            try {
                if (dir.listFiles().length == 0) {
                    FileWriter outStream = new FileWriter(this.outputFile, true);
                    outStream.write("The directory " + dir + " is empty\n");
                    outStream.close();
                } else {
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
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String @NotNull [] args) throws IOException {
        FileSystemAnalyzer fsAn = new FileSystemAnalyzer(args[0], args[1]);
        fsAn.directoryAnalyser(fsAn.dir);
    }
}
