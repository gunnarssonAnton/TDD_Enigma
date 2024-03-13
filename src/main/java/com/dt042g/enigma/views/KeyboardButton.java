package com.dt042g.enigma.views;

import javax.swing.*;
import java.awt.*;

/**
 * KeyboardButton, a JButton class
 * @author Anton Gunnarsson
 */
public class KeyboardButton extends JButton {
    private final String label;

    /**
     * Constructor for KeyboardButton
     * @param label the label for button
     */
    public KeyboardButton(String label){
        super(label);
        this.label=label;
        this.setFont(new Font("Arial",Font.PLAIN,24));
        this.setFocusPainted(false);
        this.setBorderPainted(false);

        this.setPreferredSize(new Dimension(40,40));
        if (this.label.equals("SPACE")){
            this.setPreferredSize(new Dimension(400,40));
        } else if (this.label.equals("←")) {
            this.setPreferredSize(new Dimension(70,40));
        }

        this.setContentAreaFilled(false);
        this.setForeground(Color.white);
        this.setBorder(BorderFactory.createLineBorder(Color.lightGray,5));
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.drawRoundRect(0,0,getSize().width-1,getSize().height-1,50,55);
    }
}
