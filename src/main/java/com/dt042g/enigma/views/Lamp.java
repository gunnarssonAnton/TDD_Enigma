package com.dt042g.enigma.views;

import javax.swing.*;
import java.awt.*;

/**
 * Lamp class, a JPanel class used as a lamp on the enigma machine
 * @author Anton Gunnarsson
 */
public class Lamp extends JPanel {
    private boolean isTurnOn=false;
    private final JLabel title;

    /**
     * constructor for the Lamp class
     * @param label the title a lamp has
     */
    Lamp(String label){
        this.title = new JLabel(label);
        title.setFont(new Font("Arial",Font.PLAIN,24));

        this.add(title);
        this.repaint();
        this.setPreferredSize(new Dimension(40,40));

    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(this.getColor());
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.fillRoundRect(0,0,getSize().width-1,getSize().height-1,50,55);

    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.BLACK);
        g2.drawRoundRect(0,0,getSize().width-1,getSize().height-1,50,55);
    }

    /**
     * method used to turn on and turn off the lamp
     */
    public void switchLamp(){
        this.isTurnOn=!this.isTurnOn;
        this.repaint();
    }

    /**
     * method used to get what color a lamp should have, if it is turned on it will be yellow and if it turned off it
     * will be
     * @return the color of the lamp
     */
    private Color getColor(){
        if (isTurnOn){
            return Color.yellow;
        }
        else{
            return Color.black;
        }
    }

    /**
     * method works as a getter,
     * @return the text the lamp has
     */
    String getTitleString(){
        return this.title.getText();
    }

}
