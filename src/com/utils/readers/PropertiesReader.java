package com.utils.readers;

import com.utils.writers.PropertiesWriter;
import com.utils.writers.PropertiesWriter.CONFIG;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Class that represent the Configuration Properties Reader class
 */
public class PropertiesReader {
    private InputStream input = null;
    private Properties prop = new Properties();
    public static final String CONFIG_PATH = "./config.properties";

    public PropertiesReader() {
    }

    /**
     * Get a specific property in properties in file
     *
     * @param key the key of properties file
     * @return value The value obtained from selected key
     */
    public String getProperty(String key) {
        String value = null;
        try {
            this.input = new FileInputStream(CONFIG_PATH);
            this.prop.load(input);
            CONFIG config = PropertiesWriter.CONFIG_MAP.get(key);
            value = config.toString();
            this.input.close();
        } catch (FileNotFoundException fileNotFound) {
            Logger.getLogger(PropertiesReader.class).error("Unable to find file", fileNotFound);
        } catch (IOException ioException) {
            Logger.getLogger(PropertiesReader.class).error("Unable to read file", ioException);
        }
        return value;
    }
}
