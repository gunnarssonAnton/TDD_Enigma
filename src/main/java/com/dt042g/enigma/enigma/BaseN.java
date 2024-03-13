package com.dt042g.enigma.enigma;

import java.util.*;

/**
 * A class for a number with a given base
 * @author Ruben Madsen
 */
public class BaseN {
    private int base;
    private List<Integer> digits = new ArrayList<>();

    /**
     * Constructor for a BaseN number
     * @param digits The amount of digits
     * @param base The base for the number
     */
    public BaseN(int digits, int base){
        this.base = base;
        for (int i = 0; i < digits; i++) {
            this.digits.add(0);
        }
    }

    /**
     * Sets the current value of the number.
     * @param digitValues An array of unsigned integers below the number base
     */
    public void setValue(int[] digitValues){
        int count = Math.min(digitValues.length,this.digits.size());
        int c = digitValues.length;
        for(int i=0; i<count; i++) {
            c--;
            this.digits.set(i,Math.min(Math.abs(digitValues[c]),this.base-1));
        }
    }

    /**
     * Getter for a given digit in a BaseN number
     * @param nth The number of a digit
     * @return  The value of a digit
     */
    public int getNthDigit(int nth){
        return this.digits.get(nth);
    }


    /**
     * Sets the BaseN number to zero
     */
    public void zeroOut(){
        for (int i = 0; i < this.digits.size(); i++) {
            this.digits.set(i,0);
        }
    }

    /**
     * Increments a number.
     * @param number A number representing increments
     */
    public void increment(int number){
        for(int i=0; i<number; i++){
            increment();
        }
    }
    /**
     * Decrements a number.
     * @param number A number representing decrements
     */
    public void decrement(int number){
        for (int i=0; i<number; i++){
            this.decrement();
        }
    }

    /**
     * Increments a number by one
     */
    public void increment(){
        for (int i=0; i<this.digits.size(); i++){
            int digit = this.digits.get(i);
            digit++;
            this.digits.set(i,digit);
            if(digit == this.base){
                this.digits.set(i,0);
            }
            else{
                break;
            }
        }
    }

    /**
     * Decrements a number by one
     */
    public void decrement(){
        for (int i=0; i<this.digits.size(); i++){
            int digit = this.digits.get(i);
            digit--;
            this.digits.set(i,digit);
            if(digit < 0){
                this.digits.set(i,this.base-1);
            }
            else{
                break;
            }
        }
    }

    /**
     * Increments a specific digit by one
     * @param digit A digit number
     */
    public void incrementDigit(int digit){
        int step = this.digits.get(digit) + 1;
        if(step >= this.base)
            step = 0;
        this.digits.set(digit,step);
    }

    /**
     * Decrement a specific digit by one
     * @param digit A digit number
     */
    public void decrementDigit(int digit){
        int step = this.digits.get(digit) -1;
        if(step < 0)
            step = this.base - 1;
        this.digits.set(digit,step);
    }
    @Override
    public String toString(){
        String mess = "";
        for (int i=this.digits.size()-1; i>=0 ; i--) {
            int digit = this.digits.get(i);
            mess += digit;
            if(i != 0)
                mess += " ";
        }
        return mess;
    }

}
