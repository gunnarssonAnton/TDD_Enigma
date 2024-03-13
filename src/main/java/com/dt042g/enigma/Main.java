package com.dt042g.enigma;

import com.dt042g.enigma.controllers.MainEnigmaController;
import com.dt042g.enigma.views.EnigmaMainView;

import javax.swing.*;

/**
 * Main entry point for the enigma
 * @author Anton Gunnarsson
 */
public class Main {
    /**
     * main method for the enigma project
     * uses SwingUtilities.invokeLater and starts the program
     * @param args argument
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(()->{
            EnigmaMainView enigmaMainView = new EnigmaMainView();
            MainEnigmaController enigmaController = new MainEnigmaController(enigmaMainView);
            enigmaController.initView();

        });
    }
}
