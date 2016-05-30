package com.reader;

import org.apache.commons.io.FilenameUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Class that contains reader methods to read different types of Files
 */
public class FactoryReader {
    protected enum EXTENSION {
        CSV,
        TXT,
        XML
    }

    private static final Map<EXTENSION, String> EXTENSION_TYPE = new HashMap<>();

    static {
        EXTENSION_TYPE.put(EXTENSION.CSV, "txt");
        EXTENSION_TYPE.put(EXTENSION.TXT, "csv");
        EXTENSION_TYPE.put(EXTENSION.XML, "xml");
    }

    /**
     * Method to return a reader
     *
     * @param filePath
     * @return reader
     */
    public static AbstractReader createReader(String filePath) {
        AbstractReader reader = null;
        String extension = FilenameUtils.getExtension(filePath);
        if (extension.equalsIgnoreCase(EXTENSION.TXT.toString())) {
            reader = new TXTReader(filePath);
        }
        if (extension.equalsIgnoreCase(EXTENSION.CSV.toString())) {
            reader = new CSVReader(filePath);
        }
        return reader;
    }
}
