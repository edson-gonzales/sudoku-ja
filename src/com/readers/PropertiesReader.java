package com.readers;

import com.utils.PropertiesWriter.CONFIG;

import java.io.*;
import java.util.Properties;

public class PropertiesReader {
    File file = new File("config.properties");


    public PropertiesReader(){

    }

    public void read(){

        Properties prop = new Properties();
        InputStream input = null;

        try {

            input = new FileInputStream("config.properties");

            // set the properties value
            prop.setProperty(CONFIG.ALGORITHM.name(), "localhost");
            prop.setProperty(CONFIG.OUTPUT_PATH.name(), "mkyong");
            prop.setProperty(CONFIG.OUTPUT_FILE_NAME.name(), "password");
            prop.setProperty(CONFIG.LEVEL.name(), "mkyong");

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static void main(String[] args) {

    }
}
