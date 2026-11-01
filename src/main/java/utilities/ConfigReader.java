package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private Properties properties;

    public ConfigReader() {
        String pathname = System.getProperty("user.dir") + "/src/test/resources/config.properties";
        try (FileInputStream fileInputStream = new FileInputStream(pathname)) {
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException("Could not load config.properties file.", e);
        }
    }

    public String getConfigProperty(String key) {
        String value = properties.getProperty(key);
        if (value != null) {
            return value;
        } else {
            throw new RuntimeException("Key '" + key + "' not found in config.properties!");
        }
    }
}
