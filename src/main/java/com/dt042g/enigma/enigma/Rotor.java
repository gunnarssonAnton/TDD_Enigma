package com.dt042g.enigma.enigma;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * A Reel of type Rotor
 * @author Ruben Madsen
 */
public class Rotor extends Reel{
    private static List<Integer> rotor0 = Arrays.asList(23, 14, 4, 9, 11, 19, 2, 15, 7, 1, 22, 12, 26, 20, 3, 6, 5, 25, 13, 0, 16, 10, 8, 17, 24, 21, 18);
    private static final List<Integer> rotor1 = Arrays.asList(5, 25, 1, 14, 6, 3, 13, 15, 4, 0, 23, 22, 20, 21, 10, 2, 11, 9, 16, 17, 19, 8, 18, 26, 12, 24, 7);
    private static final List<Integer> rotor2 = Arrays.asList(13, 4, 22, 19, 14, 20, 5, 12, 2, 6, 0, 17, 11, 1, 8, 3, 7, 21, 9, 25, 16, 26, 15, 24, 10, 18, 23);
    private static final List<Integer> rotor3 = Arrays.asList(23, 19, 13, 16, 12, 2, 0, 20, 25, 17, 26, 18, 4, 1, 8, 11, 10, 5, 22, 24, 3, 7, 6, 21, 14, 15, 9);
    private static final List<Integer> rotor4 = Arrays.asList(26, 0, 1, 21, 9, 19, 5, 20, 23, 16, 12, 11, 15, 2, 18, 7, 25, 17, 22, 13, 24, 8, 4, 14, 6, 3, 10);
    private static final List<Integer> rotor5 = Arrays.asList(6, 8, 0, 13, 25, 23, 21, 5, 10, 15, 4, 14, 19, 7, 17, 2, 20, 16, 9, 18, 26, 1, 11, 22, 12, 3, 24);
    private static final List<Integer> rotor6 = Arrays.asList(13, 16, 26, 20, 21, 23, 22, 25, 7, 9, 14, 19, 2, 17, 8, 24, 18, 1, 12, 4, 5, 15, 11, 0, 6, 10, 3);
    private static final List<Integer> rotor7 = Arrays.asList(19, 10, 2, 1, 24, 6, 14, 26, 17, 20, 18, 11, 8, 3, 25, 21, 0, 7, 9, 22, 4, 5, 13, 12, 23, 16, 15);
    private static final List<Integer> rotor8 = Arrays.asList(6, 20, 15, 9, 2, 14, 19, 0, 26, 18, 16, 12, 21, 3, 7, 25, 8, 11, 17, 23, 1, 5, 22, 10, 4, 13, 24);
    private static final List<Integer> rotor9 = Arrays.asList(7, 16, 8, 20, 3, 0, 21, 17, 9, 5, 11, 19, 24, 4, 26, 13, 14, 2, 1, 22, 18, 25, 15, 10, 6, 12, 23);


    /**
     * Constructor for a Rotor
     * @param reelNumber An id for a Rotor reel
     * @param startPosition Start position for the Rotor
     */
    public Rotor(int reelNumber, int startPosition) {
        super(reelNumber, startPosition);
    }

    @Override
    List<Integer> withReel(int reelNumber) {
        List<List<Integer>> rotors = new ArrayList<>();
        rotors.add(rotor0);
        rotors.add(rotor1);
        rotors.add(rotor2);
        rotors.add(rotor3);
        rotors.add(rotor4);
        rotors.add(rotor5);
        rotors.add(rotor6);
        rotors.add(rotor7);
        rotors.add(rotor8);
        rotors.add(rotor9);
        return rotors.get(reelNumber);
    }

    /**
     * A helper method for generating the console output needed to
     * copy and paste a List of integers values as a Rotor
     * @param characters A string of characters
     * @return A list of integers
     */
    static public List<Integer> generateRotor(String characters){
        Random r = new Random();
        String str = characters;
        int len = str.length();
        List<Integer> base = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            base.add(i);
        }
        List<Integer> gen = new ArrayList<>();

        while (!base.isEmpty()){
            int i = r.nextInt(base.size());
            gen.add(base.remove(i));
        }
        String result = "private static final List<Integer> rotorN = Arrays.asList(" + gen.toString().substring(1,gen.toString().length()-1) + ");";
        System.out.println(result);
        return gen;
    }

}
