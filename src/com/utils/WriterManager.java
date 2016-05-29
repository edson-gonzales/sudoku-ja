package com.utils;

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

    public static void exportTxtFile(String str) {
        try {

            String folder = "./results";
            new File(folder).mkdirs();

            DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy_hh_mm_ssaa");
            String nameFile = "sudoku_" + dateFormat.format(new Date()) + ".txt";

            String filePath = folder + "/" + nameFile;

            File txtFile = new File(filePath);
            FileWriter fw = new FileWriter(txtFile);
            fw.write(str);
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
