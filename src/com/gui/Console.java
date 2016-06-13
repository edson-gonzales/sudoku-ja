package com.gui;

import com.utils.readers.PropertiesReader;
import com.utils.writers.PropertiesWriter;

import java.util.Scanner;

/**
 * This class represent the Game console interface
 */
public abstract class Console {

    protected PropertiesWriter propertiesWriter;
    protected PropertiesReader propertiesReader;
    protected Scanner input = new Scanner(System.in);

    public Console(){
        this.propertiesReader = new PropertiesReader();
        this.propertiesWriter = new PropertiesWriter();
        this.propertiesWriter.setProperties();
    }

    /**
     * Display a text
     *
     * @param text The text to be shown
     */
    protected void display(String text) {
        System.out.println(text);
    }

    public abstract void start();
}
