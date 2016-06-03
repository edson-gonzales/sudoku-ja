package com.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class PropertiesWriter {
    private OutputStream output = null;
    private Properties prop = new Properties();
    private static final String CONFIG_PATH = "./config.properties";
    private File file;

    public enum CONFIG {
        ALGORITHM("algorithm"),
        OUTPUT_PATH("outputPath"),
        OUTPUT_FILE_NAME("outputFileName"),
        LEVEL("level");

        private String name;

        private CONFIG(String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }

    public PropertiesWriter() {
        file = new File(CONFIG_PATH);
        if (!file.exists()) {
            createConfig();
        }
    }

    /**
     * Configure the default values in
     * properties file
     */
    public void createConfig() {
        try {
            output = new FileOutputStream(CONFIG_PATH);
            prop.setProperty(CONFIG.ALGORITHM.toString(), "Backtracking");
            prop.setProperty(CONFIG.OUTPUT_PATH.toString(), "./");
            prop.setProperty(CONFIG.OUTPUT_FILE_NAME.toString(), "outpathFile");
            prop.setProperty(CONFIG.LEVEL.toString(), "easy");
            // save properties to project root folder
            prop.store(output, null);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    /**
     * Verify if the file not is null and close the writer
     */
    public void closeWriter() {
        if (output != null) {
            try {
                output.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    /**
     * Configure the default values in
     * properties file
     */
    public void setProperty(String key, String value) {
        try {
            prop.clear();
            prop.setProperty(key, value);
            prop.store(output, null);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static void main(String[] args) {
        PropertiesWriter propertiesWriter = new PropertiesWriter();
        propertiesWriter.setProperty(CONFIG.ALGORITHM.toString(), "Peter Norvig");
        propertiesWriter.setProperty(CONFIG.LEVEL.toString(), "medium");
        propertiesWriter.closeWriter();
    }
}
