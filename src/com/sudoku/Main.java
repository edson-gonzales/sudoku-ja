package com.sudoku;

import com.gui.Menu;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        Menu menu = new Menu();
        menu.start();
    }
}
