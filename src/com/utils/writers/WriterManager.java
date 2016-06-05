package com.utils.writers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Class that represent a Write Manager
 */
public class WriterManager {

    /**
     * Export a new file with txt extension
     *
     * @param str The string that contains the text to be written
     */
    public static void exportTxtFile(String str) throws IOException {

        String folder = "./results";
        new File(folder).mkdirs();

        DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy_hh_mm_ssaa");
        String nameFile = "sudoku_" + dateFormat.format(new Date()) + ".txt";

        String filePath = folder + File.separator + nameFile;

        File txtFile = new File(filePath);
        FileWriter fw = null;

        try {
            fw = new FileWriter(txtFile);
            fw.write(str);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                fw.close();
            }
        }
    }
}
