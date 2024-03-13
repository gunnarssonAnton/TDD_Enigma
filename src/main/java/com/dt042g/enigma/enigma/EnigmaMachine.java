package com.dt042g.enigma.enigma;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for an EnigmaMachine
 * @author Ruben Madsen
 */
public class EnigmaMachine {
    static public final String chars = "abcdefghijklmnopqrstuvwxyz ";

    private final String validChars;
    private BaseN counter;
    private List<Rotor> rotors = new ArrayList<>();
    private Reflector reflector;

    /**
     * Constructor for EnigmaMachine class
     * @param validChars A string of characters
     */
    public EnigmaMachine(String validChars){
        this.validChars = validChars;
    }

    /**
     * Adds a Rotor to the rotor list
     * @param rotor A Rotor object
     */
    public void addRotor(Rotor rotor){
        this.rotors.add(rotor);
        this.counter = new BaseN(rotors.size(), validChars.length());
    }

    /**
     * Replaces a Rotor with another Rotor
     * @param index An index of an existing Rotor
     * @param rotor A Rotor object
     */
    public void replaceRotorAtIndex(int index, Rotor rotor){
        this.rotors.set(index,rotor);
    }

    /**
     * Setter for Reflector
     * @param reflector A Reflector object
     */
    public void setReflector(Reflector reflector){
        this.reflector = reflector;
    }

    /**
     * Gets a list of the Rotor offsets
     * @return A list of integers
     */
    public List<Integer> getReelOffsets(){
        List<Integer> offsets = new ArrayList<>();
        for (int i = 0; i < this.rotors.size(); i++) {
            offsets.add(this.counter.getNthDigit(i));
        }
        return offsets;
    }

    /**
     * Increment the offset for a specific Rotor
     * @param rotorNumber A Rotor number
     * @return the current offset
     */
    public int incrementRotor(int rotorNumber){
        this.counter.incrementDigit(rotorNumber);
        updateReelsFromCounter();
        return this.counter.getNthDigit(rotorNumber);
    }
    /**
     * Decrement the offset for a specific Rotor
     * @param rotorNumber A Rotor number
     * @return the current offset
     */
    public int decrementRotor(int rotorNumber){
        this.counter.decrementDigit(rotorNumber);
        updateReelsFromCounter();
        return this.counter.getNthDigit(rotorNumber);
    }
    /**
     * Updates the Reel offsets based on the BaseN counter
     */
    private void updateReelsFromCounter(){
        for (int i = 0; i < this.rotors.size(); i++) {
            this.rotors.get(i).setOffset(this.counter.getNthDigit(i));
        }
    }

    /**
     * Decrements the Enigma encryption clock by one
     */
    public void backspace(){
        this.counter.decrement();
        updateReelsFromCounter();
    }
    /**
     * Resets the BaseN counter and the Reel offsets
     */
    public void reset(){
        this.counter.zeroOut();
        updateReelsFromCounter();
    }

    /**
     * Encrypts a message using The Enigma
     * @param mess A string object
     * @return A string object
     */
    public String getEncoded(String mess){
        String message = mess.toLowerCase();
        String encoded = "";
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            if(EnigmaMachine.chars.contains(c+"")) {
                int digit;
                digit = EnigmaMachine.chars.indexOf("" + message.charAt(i));
                int original = digit;
                digit = this.passthrough(digit);
                encoded += EnigmaMachine.chars.charAt(digit) + "";
            }
        }
        return encoded;
    }

    /**
     * Encrupt a character using the enigma machine
     * @param character A number representing a character in validChars
     * @return A number representing a character in validChars
     */
    public int passthrough(int character){
        int c = character;
        String mess = "";

        // Pass forwards
        for (int i = 0; i < this.rotors.size(); i++) {
            mess += c + " -> ";
            c = this.rotors.get(i).passForwards(c);
            mess += c + " ";
        }

        // Reflector
        mess += "[" + c + " -> ";
        c = this.reflector.passForwards(c);
        mess += c + "] ";

        // Pass backwards
        for (int i = this.rotors.size()-1; i>=0; i--) {
            mess += c + " -> ";
            c = this.rotors.get(i).passBackwards(c);
            mess += c + " ";
        }
        this.counter.increment();
        updateReelsFromCounter();
        return c;
    }
}
