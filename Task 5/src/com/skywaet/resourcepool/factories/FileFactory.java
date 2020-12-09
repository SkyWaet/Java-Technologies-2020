package com.skywaet.resourcepool.factories;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;

public class FileFactory implements ResourceFactory<File> {
    private final String workingDirectory;
    AtomicInteger fileId = new AtomicInteger(0);

    public FileFactory(String path) {
        this.workingDirectory = path;
    }

    @Override
    public File create() {
        File dir = new File(workingDirectory);
        if (!dir.exists()) {
            Path rawDir = Paths.get(workingDirectory);
            try {
                Files.createDirectory(rawDir);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Integer newFileId = fileId.incrementAndGet();
        String newFileName = workingDirectory + "\\" + newFileId.toString() + ".txt";

        return new File(newFileName);

    }

    @Override
    public File destroy() {
        return null;
    }
}
