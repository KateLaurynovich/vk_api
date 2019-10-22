package framework.files;

import framework.logger.MyLogger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileManager {
    private static MyLogger LOGGER = new MyLogger();

    public static void deleteDownloadFile(String path) {
        java.io.File file = new java.io.File(path);

        if (file.exists()) {
            file.delete();
            LOGGER.info("file " + file.getName() + " will be deleted");
        } else {
            LOGGER.info("file " + file.getName() + " not found");
        }
    }

    public static boolean isFile(String path) {
        java.io.File file = new java.io.File(path);
        LOGGER.info(file.getName() + " - download");
        return file.isFile();
    }

    public static File createFile(String path) {
        return new File(path);
    }

    public static void saveImg(String url, String path) {
        try (InputStream in = new URL(url).openStream()) {
            Files.copy(in, Paths.get(path));
        } catch (IOException e) {
            LOGGER.error("IOException", e);
        }
    }
}
