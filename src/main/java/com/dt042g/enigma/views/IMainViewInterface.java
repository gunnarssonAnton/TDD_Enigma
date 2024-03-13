package com.dt042g.enigma.views;

/**
 * Interface for the controller,
 * @author Anton Gunnarsson
 */
public interface IMainViewInterface {

    /**
     * method used to get the encrypted version of the input from the model
     * @param nonEncryptedText the input
     * @return an encrypted text
     */
    String getEncryptedTextFromModel(String nonEncryptedText);

    /**
     * Flips the state of the most recent lamp
     */
    public void killLamp(String lampTitle);

    /**
     * used to force an update of offset
     */
    void forceOffsetUpdate();
}
