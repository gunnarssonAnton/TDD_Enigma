package com.dt042g.enigma.views;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;

/**
 * RotorPicker, JPanel class used when a rotor shall be picked to set offset.
 * @author Anton Gunnarsson
 */
public class RotorPicker extends JPanel {
    private final JButton increaseOffsetBtn = new JButton("▲");
    private final JButton decreaseOffsetBtn = new JButton("▼");
    private final JPanel mainRotorPickerPanel = new JPanel();
    private final JTextPane offsetField = new JTextPane();

    private String[] rotorNameList = {"Rotor one", "Rotor two", "Rotor three", "Rotor four","Rotor five","Rotor six",
    "Rotor seven", "Rotor eight", "Rotor nine","Rotor ten"};
    private final JComboBox<String> dropdown;

    private int offset;

    /**
     * Constructor for RotorPicker
     */
    RotorPicker(){
        this.initMainRotorPickerPanel();
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.dropdown = new JComboBox<>(rotorNameList);
        this.setBorder(BorderFactory.createEmptyBorder(5,10,2,1));
        this.offsetField.setText(String.valueOf(offset));
        this.add(this.dropdown);
        this.add(this.mainRotorPickerPanel);
    }


    /**
     * method initMainRotorPickerPanel, used to initialize the mainRotorPickerPanel
     */
    private void initMainRotorPickerPanel(){
        this.mainRotorPickerPanel.setLayout(new BoxLayout(mainRotorPickerPanel,BoxLayout.Y_AXIS));
        this.mainRotorPickerPanel.setBorder(BorderFactory.createEmptyBorder(2,10,2,1));
        this.centerTextPane();
        this.offsetField.setEditable(false);
        this.offsetField.setFont(new Font("Arial",Font.PLAIN,30));
        this.offsetField.setSize(5,40);

        this.offsetField.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.mainRotorPickerPanel.add(this.increaseOffsetBtn);
        this.mainRotorPickerPanel.add(this.offsetField);
        this.mainRotorPickerPanel.add(this.decreaseOffsetBtn);
    }

    /**
     * method centerTextPane, used to center the JTextPane offsetField
     */
    private void centerTextPane(){
        StyledDocument doc = offsetField.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center,StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0,doc.getLength(),center,false);
    }

    /**
     * getter method
     * @return the rotorNameList
     */
    public String[] getRotorNameList() {
        return this.rotorNameList;
    }

    /**
     * setter method, used to set the text offsetField
     * @param offset what shall be presented in offsetField
     */
    public void setOffsetOnScreen(int offset){
        this.offsetField.setText(String.valueOf(offset));
    }

    /**
     * getter
     * @return the JButton decreaseOffsetBtn
     */
    public JButton getDecreaseOffsetBtn() {
        return this.decreaseOffsetBtn;
    }

    /**
     * getter
     * @return the JButton increaseOffsetBtn
     */
    public JButton getIncreaseOffsetBtn(){
        return this.increaseOffsetBtn;
    }

    /**
     * getter
     * @return the dropdown
     */
    public JComboBox<String> getDropdown(){
        return this.dropdown;
    }

    /**
     * setter, used to set a text in the offsetField
     * @param offsetText offset
     */
    public void setOffsetField(int offsetText) {
        this.offsetField.setText(String.valueOf(offsetText));
    }
}
