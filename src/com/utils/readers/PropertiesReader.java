package com.utils.readers;

import com.utils.writers.PropertiesWriter.*;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.Properties;

import static com.utils.writers.PropertiesWriter.*;

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
            input = new FileInputStream(CONFIG_PATH);
            prop.load(input);
            switch (key) {
                case ALGORITHM:
                    value = prop.getProperty(CONFIG.ALGORITHM.name());
                    break;
                case OUTPUT_FILE_NAME:
                    value = prop.getProperty(CONFIG.OUTPUT_FILE_NAME.name());
                    break;
                case OUTPUT_PATH:
                    value = prop.getProperty(CONFIG.OUTPUT_PATH.name());
                    break;
                case LEVEL:
                    value = prop.getProperty(CONFIG.LEVEL.name());
                    break;
                default:
                    break;
            }
            input.close();
        } catch (FileNotFoundException fileNotFound) {
            Logger.getLogger(PropertiesReader.class).error("Unable to find file", fileNotFound);
        } catch (IOException ioException) {
            Logger.getLogger(PropertiesReader.class).error("Unable to read file", ioException);
        }
        return value;
    }
}
