package com.utils.writers;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

/**
 * Class that represent the Configuration Properties Writer class
 */
public class PropertiesWriter {
    private OutputStream output = null;
    private Properties prop = new Properties();
    public static final String CONFIG_PATH = "./config.properties";
    private File file;
    public static final String ALGORITHM = "ALGORITHM";
    public static final String OUTPUT_PATH = "OUTPUT_PATH";
    public static final String OUTPUT_FILE_NAME = "OUTPUT_FILE_NAME";
    public static final String LEVEL = "LEVEL";
    public static final String CHARACTER = "CHARACTER";

    public enum CONFIG {
        ALGORITHM("Backtracking"),
        OUTPUT_PATH("./"),
        OUTPUT_FILE_NAME("defaultName"),
        LEVEL("Easy"),
        CHARACTER(".");

        private String value;

        private CONFIG(String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }

        public void setValue(String name) {
            this.value = name;
        }
    }

    public PropertiesWriter() {
        file = new File(CONFIG_PATH);
        if (!file.exists()) {
            setProperties();
        }
    }

    /**
     * Set the default values (ALGORITHM,OUTPUT_PATH,OUTPUT_FILE_NAME)in properties file
     *
     */
    public void setProperties() {
        try {
            this.output = new FileOutputStream(CONFIG_PATH);
            prop.put(CONFIG.ALGORITHM.name(), CONFIG.ALGORITHM.toString());
            prop.put(CONFIG.OUTPUT_PATH.name(), CONFIG.OUTPUT_PATH.toString());
            prop.put(CONFIG.OUTPUT_FILE_NAME.name(), CONFIG.OUTPUT_FILE_NAME.toString());
            prop.put(CONFIG.LEVEL.name(), CONFIG.LEVEL.toString());
            prop.put(CONFIG.CHARACTER.name(), CONFIG.CHARACTER.toString());
            prop.store(this.output, null);
        } catch (IOException ioException) {
            Logger.getLogger(PropertiesWriter.class).error("Unable to read file", ioException);
        }
    }

    /**
     * Verify if the file is not null and close the writer
     */
    public void closeWriter() {
        if (this.output != null) {
            try {
                this.output.close();
            } catch (IOException ioException) {
                Logger.getLogger(PropertiesWriter.class).error("Unable to read file", ioException);
            }
        }
    }

    /**
     * Set a specific property in
     * properties file
     *
     * @param key   the key that will be set
     * @param value the value that will be assigned to this key
     */
    public void setProperty(String key, String value) {
        switch (key) {
            case ALGORITHM:
                CONFIG.ALGORITHM.setValue(value);
                break;
            case OUTPUT_FILE_NAME:
                CONFIG.OUTPUT_FILE_NAME.setValue(value);
                break;
            case OUTPUT_PATH:
                CONFIG.OUTPUT_PATH.setValue(value);
                break;
            case LEVEL:
                CONFIG.LEVEL.setValue(value);
                break;
            case CHARACTER:
                CONFIG.CHARACTER.setValue(value);
                break;
            default:
                break;
        }
        setProperties();
    }
}
