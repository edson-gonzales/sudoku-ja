package com.readers;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesReader {
    File file = new File("config.properties");
    public enum CONFIG {
        ALGORITHM,
        OUTPUT_PATH,
        OUTPUT_FILE_NAME,
        LEVEL
    }

    protected static final Map<CONFIG, String> CONFIG_MAP = new HashMap<>();

    static {
        CONFIG_MAP.put(CONFIG.ALGORITHM, "algorithm");
        CONFIG_MAP.put(CONFIG.OUTPUT_PATH, "OutputPath");
        CONFIG_MAP.put(CONFIG.OUTPUT_FILE_NAME, "OutputFileName");
        CONFIG_MAP.put(CONFIG.LEVEL,"Level");
    }


    public PropertiesReader(){

    }

    public void read(){

        Properties prop = new Properties();
        InputStream output = null;

        try {

            output = new FileInputStream("config.properties");

            // set the properties value
            prop.setProperty(CONFIG.ALGORITHM.name(), "localhost");
            prop.setProperty(CONFIG.OUTPUT_PATH.name(), "mkyong");
            prop.setProperty(CONFIG.OUTPUT_FILE_NAME.name(), "password");
            prop.setProperty(CONFIG.LEVEL.name(), "mkyong");

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
