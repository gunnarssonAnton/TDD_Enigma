package com.dt042g.enigma.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import java.util.List;

/**
 * EnigmaMainView, used as a gui class for the enigma machine
 * @author Anton Gunnarsson
 */
public class EnigmaMainView extends JFrame{
    private final Keyboard keyboardPanel = new Keyboard();
    private final LampsBoard lampsBoardPanel = new LampsBoard();
    private final JPanel inputContainer = new JPanel();
    private final List<KeyboardButton> btnRowOne = new ArrayList<>();
    private final List<KeyboardButton> btnRowTwo = new ArrayList<>();
    private final List<KeyboardButton> btnRowThree = new ArrayList<>();
    private final List<Lamp>lampRowOne = new ArrayList<>();
    private final List<Lamp>lampRowTwo = new ArrayList<>();
    private final List<Lamp>lampRowThree = new ArrayList<>();
    private final String keyboardLabels = "qwertzuioasdfghjkpyxcvbnml";
    private final JButton spaceButton= new KeyboardButton("SPACE");
    private final JButton backspaceButton= new KeyboardButton("←");
    private final JTextField inputField = new JTextField("INPUT:");
    private final JTextField outputField = new JTextField("OUTPUT:");
    private final JPanel rotorContainer = new JPanel();
    private final JButton resetEnigmaBtn = new JButton("RESET");
    private String inputText="";
    private String outputText="";
    private final RotorPicker rotorPickerOne = new RotorPicker();
    private final RotorPicker rotorPickerTwo = new RotorPicker();
    private final RotorPicker rotorPickerThree = new RotorPicker();
    private final JPanel rotorPickerContainer= new JPanel();


    /**
     * Constructor for EnigmaMainView
     */
    public EnigmaMainView(){
        this.initMainView();
        this.designResetBtn();
        this.setRotorPickerContainer();
        this.inputContainer.add(this.inputField);
        this.inputContainer.add(this.lampsBoardPanel);
        this.inputContainer.add(this.keyboardPanel);

        this.outputField.setFont(new Font("Ariel",Font.PLAIN,24));
        this.inputField.setFont(new Font("Ariel",Font.PLAIN,24));
        this.outputField.setEditable(false);
        this.inputField.setEditable(false);

        this.inputContainer.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.inputContainer.setLayout(new BoxLayout(inputContainer,BoxLayout.Y_AXIS));
        this.add(inputContainer, BorderLayout.SOUTH);
        this.add(rotorPickerContainer,BorderLayout.CENTER);
        this.add(rotorContainer,BorderLayout.EAST);
        this.add(outputField,BorderLayout.NORTH);
        this.addButtonsToKeyboard();
        this.addLampsToLampBoard();

        this.keyboardPanel.addKeyListener(this.keyboardPanel);
        this.keyboardPanel.requestFocus();
    }

    /**
     * used to set rotorPickers to container
     */
    public void setRotorPickerContainer(){
        this.rotorPickerContainer.setLayout(new FlowLayout());
        this.rotorPickerContainer.add(rotorPickerOne);
        this.rotorPickerContainer.add(rotorPickerTwo);
        this.rotorPickerContainer.add(rotorPickerThree);

    }

    /**
     * used to st the keyboard callback to the keyboardPanel
     * @param callback the callback
     */
    public void setIRLKeyboardCallback(IMainViewInterface callback){
        this.keyboardPanel.setKeyBoardCallback(callback);
    }

    /**
     * initMainView, initialize the view
     */
    private void initMainView(){
        this.setTitle("Enigma");
        this.pack();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500,670);
        this.setResizable(false);
        this.setBackground(Color.DARK_GRAY);
    }

    /**
     * private method for adding buttons to the keyboard
     */
    private void addButtonsToKeyboard(){
        for (int i = 0; i < this.keyboardLabels.length(); i++) {
            if (i<9){
                this.btnRowOne.add(new KeyboardButton(String.valueOf(this.keyboardLabels.toUpperCase().charAt(i))));

            } else if (i<17) {
                this.btnRowTwo.add(new KeyboardButton(String.valueOf(this.keyboardLabels.toUpperCase().charAt(i))));

            } else if (i<26) {
                this.btnRowThree.add(new KeyboardButton(String.valueOf(this.keyboardLabels.toUpperCase().charAt(i))));
            }
        }

        this.btnRowOne.forEach(btn-> keyboardPanel.getKeyboardRowOne().add(btn));
        this.btnRowTwo.forEach(btn-> keyboardPanel.getKeyboardRowTwo().add(btn));
        this.btnRowThree.forEach(btn-> keyboardPanel.getKeyboardRowThree().add(btn));

        this.keyboardPanel.getKeyboardSpaceRow().add(this.spaceButton);
        this.keyboardPanel.getKeyboardBackspaceRow().add(this.resetEnigmaBtn,BorderLayout.WEST);
        this.keyboardPanel.getKeyboardBackspaceRow().add(this.backspaceButton);
        this.keyboardPanel.add(this.keyboardPanel.getKeyboardBackspaceRow());
        this.keyboardPanel.add(this.keyboardPanel.getKeyboardRowOne());
        this.keyboardPanel.add(this.keyboardPanel.getKeyboardRowTwo());
        this.keyboardPanel.add(this.keyboardPanel.getKeyboardRowThree());
        this.keyboardPanel.add(this.keyboardPanel.getKeyboardSpaceRow());

    }
    /**
     * private method for adding lamps to the lampboard
     */
    private void addLampsToLampBoard(){
        for (int i = 0; i < this.keyboardLabels.length(); i++) {
            if (i<9){
                this.lampRowOne.add(new Lamp(String.valueOf(this.keyboardLabels.toUpperCase().charAt(i))));

            } else if (i<17) {
                this.lampRowTwo.add(new Lamp(String.valueOf(this.keyboardLabels.toUpperCase().charAt(i))));

            } else if (i<26) {
                this.lampRowThree.add(new Lamp(String.valueOf(this.keyboardLabels.toUpperCase().charAt(i))));
            }
        }

        this.lampRowOne.forEach(lamp -> this.lampsBoardPanel.getLampBoardRowOne().add(lamp));
        this.lampRowTwo.forEach(lamp -> this.lampsBoardPanel.getLampBoardRowTwo().add(lamp));
        this.lampRowThree.forEach(lamp -> this.lampsBoardPanel.getLampBoardRowThree().add(lamp));

        this.lampsBoardPanel.add(this.lampsBoardPanel.getLampBoardRowOne());
        this.lampsBoardPanel.add(this.lampsBoardPanel.getLampBoardRowTwo());
        this.lampsBoardPanel.add(this.lampsBoardPanel.getLampBoardRowThree());
    }

    /**
     * Method that is used by the controller to set listeners to KeyboardButtons
     * @param listener the listener that will be used
     */
    public void addListenerToButtons(MouseListener listener){
        this.btnRowOne.forEach(btn->btn.addMouseListener(listener));
        this.btnRowTwo.forEach(btn->btn.addMouseListener(listener));
        this.btnRowThree.forEach(btn->btn.addMouseListener(listener));
        this.spaceButton.addMouseListener(listener);
        this.backspaceButton.addMouseListener(listener);
    }

    /**
     * method used to set a text in the input field
     * @param input the text to set in the input field
     */
    public void setInputText(String input){

        if (input.equals("SPACE")){
            input=" ";
        }

        if (this.inputField.getText().equals("INPUT:")){
            this.inputField.setText("");
        }
        this.inputText+=input;

        this.inputField.setText(inputText);
    }

    /**
     * inputBackspace, used when the backspace button is pressed.
     * @return the inputText i without the last letter
     */
    public String inputBackspace(){

        if (this.inputText.length()!=0){
            this.inputText = this.inputText.substring(0,inputText.length()-1);
        }
        return this.inputText;
    }

    /**
     * outputBackspace, used when the backspace button is pressed.
     * @return the outputText i without the last letter
     */
    public String outputBackspace(){

        if (this.outputText.length()!=0){
            this.outputText = this.outputText.substring(0,this.outputText.length()-1);
        }

        return this.outputText;
    }


    /**
     * method used to set a text in the output field
     * @param text the text to set in the output field
     */
    public void setOutText(String text){
        this.outputText+=text;
        if (this.outputField.getText().equals("OUTPUT:")){
            this.outputField.setText("");
        }
        this.outputField.setText(outputText.toUpperCase());
    }

    /**
     * used to turn on and off
     * @param title of the lamp
     */
    public void turnOnAndOffLamp(String title){
        this.keyboardPanel.requestFocus();
        this.lampLoop(lampRowOne, title);
        this.lampLoop(lampRowTwo, title);
        this.lampLoop(lampRowThree, title);
    }

    /**
     * private method used to loop through the lamps
     * @param lampList a list of lamps
     * @param title the title of lamp
     */
    private void lampLoop(List<Lamp> lampList, String title) {
        lampList.forEach(lamp -> {
            if (lamp.getTitleString().equals(title.toUpperCase())) {
                lamp.switchLamp();
                lampsBoardPanel.repaint();
            }
        });
    }


    /**
     * getter to get a list of buttons
     * @param name the name of the list
     * @return a btnList
     */
    public List<KeyboardButton> getBtnList(String name){
        return switch (name) {
            case "RowOne" -> this.btnRowOne;
            case "RowTwo" -> this.btnRowTwo;
            case "RowThree" -> this.btnRowThree;
            default -> null;
        };

    }
    /**
     * getter to get a list of lamps
     * @param name the name of the list
     * @return a lampList
     */
    public List<Lamp> getLampList(String name){
        return switch (name) {
            case "RowOne" -> this.lampRowOne;
            case "RowTwo" -> this.lampRowTwo;
            case "RowThree" -> this.lampRowThree;
            default -> null;
        };

    }

    /**
     * method used to add a listener to the reset button
     * @param listener the listener
     */
    public void addListenerToResetButton(ActionListener listener){
        this.resetEnigmaBtn.addActionListener(listener);
    }

    /**
     * method used to reset the inputField
     */
    public void resetInput(){
        this.inputText="";
        this.inputField.setText("");
    }

    /**
     * method used to reset the outputField
     */
    public void resetOutput(){
        this.outputText = "";
        this.outputField.setText("");
    }

    /**
     * getter for text in the inputField
     * @return text in the inputField
     */
    public String getInputText(){
        return this.inputField.getText();
    }

    /**
     * getter for text in the outputField
     * @return text in the outputField
     */
    public String getOutputText(){
        return this.outputField.getText();
    }

    /**
     * private method used to design the reset button
     */
    private void designResetBtn(){
        this.resetEnigmaBtn.setSize(400,40);
        this.resetEnigmaBtn.setBackground(Color.RED);
        this.resetEnigmaBtn.setFocusable(false);
        this.resetEnigmaBtn.setFocusPainted(false);
        this.resetEnigmaBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    /**
     * adds a listener to the increase button in rotorPickerOne
     * @param listener a Listener
     */
    public void addBtnListenerToPickerOneIncreaseOffset(ActionListener listener){
        this.rotorPickerOne.getIncreaseOffsetBtn().addActionListener(listener);
    }

    /**
     * adds a listener to the decrease button in rotorPickerOne
     * @param listener a Listener
     */
    public void addBtnListenerToPickerOneDecreaseOffset(ActionListener listener){
        this.rotorPickerOne.getDecreaseOffsetBtn().addActionListener(listener);
    }
    /**
     * adds a listener to the increase button in rotorPickerTwo
     * @param listener a Listener
     */
    public void addBtnListenerToPickerTwoIncreaseOffset(ActionListener listener){
        this.rotorPickerTwo.getIncreaseOffsetBtn().addActionListener(listener);
    }
    /**
     * adds a listener to the decrease button in rotorPickerOne
     * @param listener a Listener
     */
    public void addBtnListenerToPickerTwoDecreaseOffset(ActionListener listener){
        this.rotorPickerTwo.getDecreaseOffsetBtn().addActionListener(listener);
    }
    /**
     * adds a listener to the increase button in rotorPickerThree
     * @param listener a Listener
     */
    public void addBtnListenerToPickerThreeIncreaseOffset(ActionListener listener){
        this.rotorPickerThree.getIncreaseOffsetBtn().addActionListener(listener);
    }

    /**
     * adds a listener to the decrease button in rotorPickerOne
     * @param listener a Listener
     */
    public void addBtnListenerToPickerThreeDecreaseOffset(ActionListener listener){
        this.rotorPickerThree.getDecreaseOffsetBtn().addActionListener(listener);
    }

    /**
     * setter, used to set the offset in rotorPickerOne
     * @param offset the offset
     */
    public void setOffsetOnPickerOne(int offset){
        this.rotorPickerOne.setOffsetOnScreen(offset);
    }

    /**
     * setter, used to set the offset in rotorPickerTwo
     * @param offset the offset
     */
    public void setOffsetOnPickerTwo(int offset){
        this.rotorPickerTwo.setOffsetOnScreen(offset);
    }

    /**
     * setter, used to set the offset in rotorPickerThree
     * @param offset the offset
     */
    public void setOffsetOnPickerThree(int offset){
        this.rotorPickerThree.setOffsetOnScreen(offset);
    }

    /**
     * used to pick a rotor
     * @param name the name of the rotor
     * @return the index of the rotor
     */
    public int pickRotor(String name){
        int lenRotorDropdown = this.rotorPickerOne.getRotorNameList().length;
        for (int i = 0; i < lenRotorDropdown; i++) {
            if(this.rotorPickerOne.getRotorNameList()[i].equals(name)){
                return i;
            }
        }
        return 0;
    }

    /**
     * adds a listener ot the dropdown in rotorPickerOne
     * @param listener the listener
     */
    public void addDropdownListenerToDropdownOne(ActionListener listener){
        this.rotorPickerOne.getDropdown().addActionListener(listener);
    }

    /**
     * adds a listener ot the dropdown in rotorPickerTwo
     * @param listener the listener
     */
    public void addDropdownListenerToDropdownTwo(ActionListener listener){
        this.rotorPickerTwo.getDropdown().addActionListener(listener);
    }

    /**
     * adds a listener ot the dropdown in rotorPickerThree
     * @param listener the listener
     */
    public void addDropdownListenerToDropdownThree(ActionListener listener){
        this.rotorPickerThree.getDropdown().addActionListener(listener);
    }

    /**
     * used to update the offset field
     * @param offsets list of offsets.
     */
    public void updateOffsets(List<Integer> offsets){
        this.rotorPickerOne.setOffsetField(offsets.get(2));
        this.rotorPickerTwo.setOffsetField(offsets.get(1));
        this.rotorPickerThree.setOffsetField(offsets.get(0));
    }
}
