package com.utils.writers;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
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
    public static final String EASY_MIN = "EASY_MIN";
    public static final String MEDIUM_MIN = "MEDIUM_MIN";
    public static final String HARD_MIN = "HARD_MIN";
    public static final String CUSTOM_MIN = "CUSTOM_MIN";
    public static final String EASY_MAX = "EASY_MAX";
    public static final String MEDIUM_MAX = "MEDIUM_MAX";
    public static final String HARD_MAX = "HARD_MAX";
    public static final String CUSTOM_MAX = "CUSTOM_MAX";

    public static Map<String, CONFIG> CONFIG_MAP = new HashMap<>();
    {
        CONFIG_MAP.put(ALGORITHM, CONFIG.ALGORITHM);
        CONFIG_MAP.put(OUTPUT_PATH, CONFIG.OUTPUT_PATH);
        CONFIG_MAP.put(OUTPUT_FILE_NAME, CONFIG.OUTPUT_FILE_NAME);
        CONFIG_MAP.put(LEVEL, CONFIG.LEVEL);
        CONFIG_MAP.put(CHARACTER, CONFIG.CHARACTER);
        CONFIG_MAP.put(EASY_MIN, CONFIG.EASY_MIN);
        CONFIG_MAP.put(MEDIUM_MIN, CONFIG.MEDIUM_MIN);
        CONFIG_MAP.put(HARD_MIN, CONFIG.HARD_MIN);
        CONFIG_MAP.put(CUSTOM_MIN, CONFIG.CUSTOM_MIN);
        CONFIG_MAP.put(EASY_MAX, CONFIG.EASY_MAX);
        CONFIG_MAP.put(MEDIUM_MAX, CONFIG.MEDIUM_MAX);
        CONFIG_MAP.put(HARD_MAX, CONFIG.HARD_MAX);
        CONFIG_MAP.put(CUSTOM_MAX, CONFIG.CUSTOM_MAX);
    }

    public enum CONFIG {
        ALGORITHM("BackTracking"),
        OUTPUT_PATH("./"),
        OUTPUT_FILE_NAME("defaultName"),
        LEVEL("Easy"),
        CHARACTER("."),
        EASY_MIN("1"),
        MEDIUM_MIN("3"),
        HARD_MIN("6"),
        CUSTOM_MIN(""),
        EASY_MAX("3"),
        MEDIUM_MAX("5"),
        HARD_MAX("9"),
        CUSTOM_MAX("");

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

    public PropertiesWriter() throws NoSuchMethodException {
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
            prop.put(CONFIG.EASY_MIN.name(), CONFIG.EASY_MIN.toString());
            prop.put(CONFIG.MEDIUM_MIN.name(), CONFIG.MEDIUM_MIN.toString());
            prop.put(CONFIG.HARD_MIN.name(), CONFIG.HARD_MIN.toString());
            prop.put(CONFIG.CUSTOM_MIN.name(), CONFIG.CUSTOM_MIN.toString());
            prop.put(CONFIG.EASY_MAX.name(), CONFIG.EASY_MAX.toString());
            prop.put(CONFIG.MEDIUM_MAX.name(), CONFIG.MEDIUM_MAX.toString());
            prop.put(CONFIG.HARD_MAX.name(), CONFIG.HARD_MAX.toString());
            prop.put(CONFIG.CUSTOM_MAX.name(), CONFIG.CUSTOM_MAX.toString());
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
        CONFIG config = CONFIG_MAP.get(key);
        getConfigEnum(config, value);
        setProperties();

    }

    public static void getConfigEnum(CONFIG config, String value){
        config.setValue(value);
    }
}
