package com.dt042g.enigma.views;

import com.dt042g.enigma.enigma.EnigmaMachine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.FutureTask;

/**
 * Keyboard, JPanel class used as a container for KeyboardButtons
 * @author Anton Gunnarsson
 */
public class Keyboard extends JPanel implements KeyListener {
    private boolean isDown = false;
    private final JPanel keyboardRowOne = new JPanel();
    private final JPanel keyboardRowTwo = new JPanel();
    private final JPanel keyboardRowThree = new JPanel();
    private final JPanel keyboardSpaceRow = new JPanel();
    private final JPanel keyboardBackspaceRow = new JPanel();
    private IMainViewInterface keyBoardCallback;

    /**
     * constructor for Keyboard
     */
    public Keyboard(){
        this.setBackground(Color.DARK_GRAY);
        this.keyboardRowOne.setBackground(Color.DARK_GRAY);
        this.keyboardRowTwo.setBackground(Color.DARK_GRAY);
        this.keyboardRowThree.setBackground(Color.DARK_GRAY);
        this.keyboardSpaceRow.setBackground(Color.DARK_GRAY);
        this.keyboardBackspaceRow.setBackground(Color.DARK_GRAY);
        this.keyboardBackspaceRow.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

    }

    /**
     * Setter for keyBoardCallback
     * @param callback the callback
     */
    public void setKeyBoardCallback(IMainViewInterface callback){
        this.keyBoardCallback = callback;
    }
    /**
     * getter
     * @return keyboardRowOne
     */
    public JPanel getKeyboardRowOne(){
        return this.keyboardRowOne;
    }

    /**
     * getter
     * @return keyboardRowTwo
     */
    public JPanel getKeyboardRowTwo(){
        return this.keyboardRowTwo;
    }

    /**
     * getter
     * @return keyboardRowThree
     */
    public JPanel getKeyboardRowThree(){
        return this.keyboardRowThree;
    }

    /**
     * getter
     * @return keyboardSpaceRow
     */
    public JPanel getKeyboardSpaceRow(){
        return this.keyboardSpaceRow;
    }

    /**
     * getter
     * @return keyboardBackspaceRow
     */
    public JPanel getKeyboardBackspaceRow(){
        return this.keyboardBackspaceRow;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        String input = e.getKeyChar() + "";
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            input = "SPACE";
        }
        String encrypted = this.keyBoardCallback.getEncryptedTextFromModel(input);
        String finalInput = encrypted;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(50);
                    keyBoardCallback.killLamp(encrypted);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
