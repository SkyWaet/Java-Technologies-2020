package com.skywaet.nonStandartIO;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

class FileSystemAnalyzerTest {
    void deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        directoryToBeDeleted.delete();
    }

    @Test
    void constructorWithCorrectPathsTest() {
        try {
            File dir = new File("./dir1").getCanonicalFile();
            if (!dir.exists()) {
                Path rawDir = Paths.get("./dir1");
                Files.createDirectory(rawDir);
            }

            File outFile = new File("./dir1/1.txt").getCanonicalFile();
            if (!outFile.exists()) {
                outFile.createNewFile();
            }
            FileSystemAnalyzer fsAn = new FileSystemAnalyzer("./dir1", "./dir1/1.txt");
            Assertions.assertEquals(dir, fsAn.getDir());
            Assertions.assertEquals(outFile, fsAn.getOutputFile());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void constructorWithWrongDirPathTest() {
        try {
            File dir = new File("./dir1").getCanonicalFile();
            if (dir.exists()) {
                deleteDirectory(dir);
            }

            File outFile = new File("1.txt").getCanonicalFile();
            if (!outFile.exists()) {
                outFile.createNewFile();
            }
            Assertions.assertThrows(NoSuchFileException.class, () -> new FileSystemAnalyzer("./dir1", "1.txt"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void constructorWithWrongFilePathTest() {
        try {
            File dir = new File("./dir1").getCanonicalFile();
            if (!dir.exists()) {
                Path rawDir = Paths.get("./dir1");
                Files.createDirectory(rawDir);
            }

            File outFile = new File("1.txt").getCanonicalFile();
            if (outFile.exists()) {
                outFile.delete();
            }

            Assertions.assertThrows(NoSuchFileException.class, () -> new FileSystemAnalyzer("./dir1", "1.txt"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void directoryAnalyserWithNotEmptyDirectoryTest() {
        try {
            ArrayList<String> correctAns = new ArrayList<>();
            File dir = new File("./dir1").getCanonicalFile();
            if (dir.exists()) {
                deleteDirectory(dir);
            }

            Path rawDir = Paths.get("./dir1");
            Files.createDirectory(rawDir);

            if (dir.listFiles().length == 0) {
                File childDir = new File("./dir1/childDir");
                if (!childDir.exists()) {
                    Path childDirPath = Paths.get("./dir1/childDir");
                    Files.createDirectory(childDirPath);
                }
                correctAns.add("\uD83D\uDCC1 childDir");
                for (int i = 0; i < 5; i++) {
                    String filename = "./dir1/" + i + ".txt";
                    correctAns.add(i + ".txt");
                    File dirFile = new File(filename).getCanonicalFile();
                    dirFile.createNewFile();
                }
                for (int i = 5; i < 10; i++) {
                    String childFilename = "./dir1/childDir/" + i + ".txt";
                    correctAns.add(i + ".txt");
                    File childDirFile = new File(childFilename).getCanonicalFile();
                    childDirFile.createNewFile();
                }
            }
            File outFile = new File("out.txt").getCanonicalFile();
            outFile.delete();
            outFile.createNewFile();

            FileSystemAnalyzer fsAn = new FileSystemAnalyzer("./dir1/", "out.txt");
            fsAn.directoryAnalyser(dir);
            Scanner fileScanner = new Scanner(outFile);
            ArrayList<String> dataInput = new ArrayList<>();
            while (fileScanner.hasNextLine()) {
                dataInput.add(fileScanner.nextLine());
            }
            for (String i : dataInput) {
                Assertions.assertTrue(correctAns.contains(i));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    void directoryAnalyserWithEmptyDirectoryTest() throws IOException {
        File dir = new File("./dir1").getCanonicalFile();
        if (dir.exists()) {
            deleteDirectory(dir);
        }
        Path rawDir = Paths.get("./dir1");
        Files.createDirectory(rawDir);
        File outFile = new File("out.txt").getCanonicalFile();
        outFile.delete();
        outFile.createNewFile();
        FileSystemAnalyzer fsAn = new FileSystemAnalyzer("./dir1", "out.txt");
        fsAn.directoryAnalyser(dir);
        Scanner fileScanner = new Scanner(outFile);
        Assertions.assertEquals("The directory " + dir + " is empty", fileScanner.nextLine());
    }

}