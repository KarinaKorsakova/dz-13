package utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class PropertiesReader {
    public static String getProperty(String filePath, String propertyName) {
        Properties properties = new Properties();
        try {
            InputStream is = Files.newInputStream(Paths.get(filePath));
            properties.load(is);
        } catch (IOException ex) {
            throw new RuntimeException("The file could not be found" + filePath + ex);
        }
        return properties.getProperty(propertyName);
    }
}
