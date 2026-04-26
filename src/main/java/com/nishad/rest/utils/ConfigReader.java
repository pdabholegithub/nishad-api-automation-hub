package com.nishad.rest.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 📖 ConfigReader Utility
 * Responsible for reading key-value pairs from the config.properties file.
 */
public class ConfigReader {
    private static Properties properties;

    static {
        try {
            // Load the properties file from the resources folder
            String filePath = "src/test/resources/config.properties";
            FileInputStream inputStream = new FileInputStream(filePath);
            properties = new Properties();
            properties.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("❌ Failed to load config.properties file.");
        }
    }

    /**
     * Retrieves a property value by its key.
     * @param key The property name (e.g., "baseUrl")
     * @return The value of the property
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
