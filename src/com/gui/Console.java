package com.gui;

import com.utils.readers.PropertiesReader;
import com.utils.writers.PropertiesWriter;

import java.util.List;
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

    /**
     * Display options
     *
     * @param options The options to be shown
     */
    protected void displayOptions(String  title, List<String> options) {
        display(String.format("::::::%s::::::", title));
        for(String option : options){
            display(option);
        }
        display("Select an option:");
    }

    /**
     * Display a error entry
     */
    protected void displayError() {
        display("ERROR::Enter a valid option");
    }

    public abstract void start();
}
