package com.dt042g.enigma.views;

import javax.swing.*;
import java.awt.*;

/**
 * LampBoard, JPanel class used as a container for Lamp classes
 * @author Anton Gunnarsson
 */
public class LampsBoard extends JPanel {

    private final JPanel lampBoardRowOne = new JPanel();
    private final JPanel lampBoardRowTwo = new JPanel();
    private final JPanel lampBoardRowThree = new JPanel();

    /**
     * Constructor for LampsBoard
     */
    LampsBoard(){

        this.lampBoardRowOne.setBackground(Color.DARK_GRAY);
        this.lampBoardRowTwo.setBackground(Color.DARK_GRAY);
        this.lampBoardRowThree.setBackground(Color.DARK_GRAY);
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }


    /**
     * getter
     * @return lampBoardRowOne
     */
    public JPanel getLampBoardRowOne(){
        return this.lampBoardRowOne;
    }

    /**
     * getter
     * @return lampBoardRowTwo
     */
    public JPanel getLampBoardRowTwo(){
        return this.lampBoardRowTwo;
    }

    /**
     * getter
     * @return lampBoardRowThree
     */
    public JPanel getLampBoardRowThree(){
        return this.lampBoardRowThree;
    }
}
