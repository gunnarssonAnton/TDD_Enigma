package com.dt042g.enigma.enigma;

import java.util.List;

/**
 * Base for a Rotor or a Reflector
 * @author Ruben Madsen
 */
public abstract class Reel {
    private int offset = 0;
    private List<Integer> values;

    /**
     * Constructor for Subclasses
     * @param reelNumber The id of a reel
     * @param startPosition Start position for the selected reel
     */
    protected Reel(int reelNumber, int startPosition){
        this.values = this.withReel(reelNumber);
    }

    /**
     * Setter for offset
     * @param offset A non-negative number
     */
    public void setOffset(int offset){
        this.offset = Math.abs(offset);
    }

    /**
     * Returns the int of a given index
     * @param i An index
     * @return A value for the index
     */
    public int passForwards(int i){
        int index = (i + this.offset) % this.values.size();
        int i2 = this.values.get(index);
        return i2;
    }

    /**
     * Returns the index from where a given value is located
     * @param i A value
     * @return An index
     */
    public int passBackwards(int i){
        int index = (this.values.indexOf(i) - this.offset);
        if (index < 0)
            index += this.values.size();
        return index;
    }

    /**
     * Yields a list of integers
     * @param reelNumber An id of a reel
     * @return A list of integers
     */
    abstract List<Integer> withReel(int reelNumber);
}