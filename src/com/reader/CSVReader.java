package com.reader;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Class that contains the csv file reader methods verifying that the file exist
 *
 * @author Jose Cabrera
 */
public class CSVReader extends AbstractReader {

    public CSVReader(String filePath) {
        super(filePath);
    }

    /**
     * Method to read a csv file verifying that the file exist
     *
     * @return parsedText
     */
    @Override
    public String getContentFile() {
        String parsedText = "";
        File file = new File(filePath);
        if (file.exists()) {
            parsedText = read(file).replace(",", "");
        }
        return parsedText;
    }

    /**
     * Read a file
     *
     * @param file
     * @return text
     */
    private String read(File file) {
        String text = "";
        try {
            scanner = new Scanner(file);
            while (scanner.hasNext()) {
                text = text + scanner.next();
            }
        } catch (IOException io) {
            Logger.getLogger(CSVReader.class).error("Unable to read CSV", io);
        } catch (NullPointerException nullPointerException) {
            Logger.getLogger(CSVReader.class).error("The file is null", nullPointerException);
        } finally {
            scanner.close();
        }
        return text;
    }
}
