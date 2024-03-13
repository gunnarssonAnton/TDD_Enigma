package com.dt042g.enigma.controllers;

import com.dt042g.enigma.enigma.EnigmaMachine;
import com.dt042g.enigma.enigma.Reflector;
import com.dt042g.enigma.enigma.Rotor;
import com.dt042g.enigma.views.EnigmaMainView;
import com.dt042g.enigma.views.IMainViewInterface;
import com.dt042g.enigma.views.KeyboardButton;

import javax.swing.*;
import java.awt.event.*;

/**
 * Main Controller for the enigma
 * @author Anton Gunnarsson
 */
public class MainEnigmaController implements IMainViewInterface {
    private final EnigmaMainView mainView;
    private EnigmaMachine enigmaMachine;
    private String prevButton;

    /**
     * Constructor for MainEnigmaController
     * @param mainView the view
     */
    public MainEnigmaController(EnigmaMainView mainView){
        this.mainView = mainView;
        this.initEnigma();
        this.addButtonListener();
        this.addListenerToReset();
        this.mainView.setIRLKeyboardCallback(this);
        this.addDropdownListener();
    }

    /**
     * method used to initialize the view
     */
    public void initView(){
        this.mainView.setVisible(true);
    }

    /**
     * method used to initialize the Enigma
     */
    public void initEnigma(){
        this.enigmaMachine = new EnigmaMachine(EnigmaMachine.chars);
        this.enigmaMachine.addRotor(new Rotor(0,0));
        this.enigmaMachine.addRotor(new Rotor(0,0));
        this.enigmaMachine.addRotor(new Rotor(0,0));
        this.enigmaMachine.setReflector(new Reflector(0,0));
    }


    /**
     * Adds click listeners for all buttons
     */
    private void addButtonListener() {
        this.mainView.addListenerToButtons(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                String input = getTitleOnButton(e);
                String encrypted = getEncryptedTextFromModel(input);

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(50);
                            killLamp(encrypted);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            }
        });

        this.mainView.addBtnListenerToPickerOneIncreaseOffset(e ->
                mainView.setOffsetOnPickerOne(enigmaMachine.incrementRotor(2)));

        this.mainView.addBtnListenerToPickerOneDecreaseOffset(e ->
                mainView.setOffsetOnPickerOne(enigmaMachine.decrementRotor(2)));

        this.mainView.addBtnListenerToPickerTwoIncreaseOffset(e ->
            mainView.setOffsetOnPickerTwo(enigmaMachine.incrementRotor(1)));


        this.mainView.addBtnListenerToPickerTwoDecreaseOffset(e ->
                mainView.setOffsetOnPickerTwo(enigmaMachine.decrementRotor(1)));

        this.mainView.addBtnListenerToPickerThreeIncreaseOffset(e ->
                mainView.setOffsetOnPickerThree(enigmaMachine.incrementRotor(0)));

        this.mainView.addBtnListenerToPickerThreeDecreaseOffset(e ->
                mainView.setOffsetOnPickerThree(enigmaMachine.decrementRotor(0)));


    }

    @Override
    public String getEncryptedTextFromModel(String nonEncryptedText) {
        if(nonEncryptedText.equals("SPACE")){
            nonEncryptedText = " ";
        } else if (nonEncryptedText.equals("←")) {
            if (!mainView.getInputText().isEmpty())
                enigmaMachine.backspace();
            nonEncryptedText = "";
            mainView.inputBackspace();
            mainView.outputBackspace();
        }
        prevButton = this.enigmaMachine.getEncoded(nonEncryptedText);
        mainView.setInputText(nonEncryptedText.toUpperCase());
        mainView.setOutText(prevButton);
        mainView.turnOnAndOffLamp(prevButton);
        mainView.updateOffsets(enigmaMachine.getReelOffsets());
        return prevButton;
    }

    @Override
    public void killLamp(String lampTitle) {
        mainView.turnOnAndOffLamp(lampTitle.toUpperCase());
    }

    @Override
    public void forceOffsetUpdate() {
        mainView.updateOffsets(enigmaMachine.getReelOffsets());
    }

    /**
     * used to get the title on the button
     * @param event button
     * @return the title
     */
    private String getTitleOnButton(MouseEvent event){
        Object o = event.getSource();
        KeyboardButton b = null;
        String btnText="";
        if (o instanceof KeyboardButton){
            b = (KeyboardButton)o;
        }
        if(b != null) {
            btnText = b.getText();
        }
        return btnText;
    }

    /**
     * method sets the listener on the reset button
     */
    private void addListenerToReset(){
        this.mainView.addListenerToResetButton(e ->{
                    enigmaMachine.reset();
                    mainView.resetInput();
                    mainView.resetOutput();
                    mainView.setOffsetOnPickerOne(0);
                    mainView.setOffsetOnPickerTwo(0);
                    mainView.setOffsetOnPickerThree(0);
                });

    }

    /**
     * adds listener to the dropdowns
     */
    private void addDropdownListener(){
        this.mainView.addDropdownListenerToDropdownOne(e -> {
            JComboBox item = (JComboBox) e.getSource();
            int rotorNumber= item.getSelectedIndex();
            enigmaMachine.replaceRotorAtIndex(0,new Rotor(rotorNumber,0));
        });
        this.mainView.addDropdownListenerToDropdownTwo(e -> {
            JComboBox item = (JComboBox) e.getSource();
            int rotorNumber= item.getSelectedIndex();
            enigmaMachine.replaceRotorAtIndex(1,new Rotor(rotorNumber,0));
        });
        this.mainView.addDropdownListenerToDropdownThree(e -> {
            JComboBox item = (JComboBox) e.getSource();
            int rotorNumber= item.getSelectedIndex();
            enigmaMachine.replaceRotorAtIndex(2,new Rotor(rotorNumber,0));
        });
    }



}
