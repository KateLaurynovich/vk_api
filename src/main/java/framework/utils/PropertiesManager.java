package framework.utils;

import framework.logger.MyLogger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesManager {
    private static final MyLogger LOGGER = new MyLogger();
    private static FileInputStream fileInputStream;
    private static Properties properties = new Properties();
    private static String path = "src/main/resources/%s.properties";

    public static void loadProperties(String name) {
        try {
            fileInputStream = new FileInputStream(String.format(path, name));
        } catch (FileNotFoundException e) {
            LOGGER.error("properties file doesn't find", e);
        }
        try {
            properties.load(fileInputStream);
        } catch (IOException e) {
            LOGGER.error("line in properties file doesn't find", e);
        }
    }

    public static String getProperties(String key) {
        return properties.getProperty(key);
    }

    public static String getPropertiesLocale(String key) {
        try {
            fileInputStream = new FileInputStream(String.format(path, PropertiesManager.getProperties("locale")));
        } catch (FileNotFoundException e) {
            LOGGER.error("properties file doesn't find", e);
        }
        try {
            properties.load(fileInputStream);
        } catch (IOException e) {
            LOGGER.error("line in properties file doesn't find", e);
        }
        return properties.getProperty(key);
    }
}
