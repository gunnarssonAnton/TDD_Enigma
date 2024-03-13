package com.dt042g.enigma.enigma;

import java.util.*;

/**
 * A Reel of type Reflector
 * @author Ruben Madsen
 */
public class Reflector extends Reel{
    private static final List<Integer> reflector0 = Arrays.asList(5, 2, 1, 20, 19, 0, 9, 10, 23, 6, 7, 15, 26, 13, 24, 11, 25, 22, 18, 4, 3, 21, 17, 8, 14, 16, 12);
    /**
     * Constructor for a Reflector
     * @param reelNumber An id for a Reflector reel
     * @param startPosition Start position for the Reflector
     */
    public Reflector(int reelNumber, int startPosition) {
        super(reelNumber, startPosition);
    }

    @Override
    List<Integer> withReel(int reelNumber) {
        List<List<Integer>> reflectors = new ArrayList<>();
        reflectors.add(reflector0);
        return reflectors.get(reelNumber);
    }

    /**
     * A "helper" method for generating the necessary console output needed
     * for copying and pasting reflector values formatted as a List<Integer>
     * @param characters A string of characters
     * @return A generated List of integers
     */
    static public List<Integer> generateReflector(String characters){
        Random r = new Random();
        String str = characters;
        int len = str.length();
        List<Integer> base = new ArrayList<>();
        Map<Integer,Integer> buff = new HashMap<>();
        for (int i = 0; i < len; i++) {
            base.add(i);
        }
        while (!base.isEmpty()){
            int value = base.remove(r.nextInt(base.size()));
            if(!buff.containsValue(value)){
                int rKey = r.nextInt(len);
                while(buff.containsKey(rKey)) {
                    rKey = r.nextInt(len);
                }
                buff.put(rKey,value);
                buff.put(value,rKey);
                base.remove((Integer) rKey);
                base.remove((Integer) value);
            }
        }
        for (Integer integer : buff.keySet()) {
            String res = "";
            res += integer + "->" + buff.get(integer) + "->" + buff.get(buff.get(integer));
        }
        List<Integer> gen = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            gen.add(buff.remove(i));
        }

        String result = "private static final List<Integer> reflectorN = Arrays.asList(" + gen.toString().substring(1,gen.toString().length()-1) + ");";
        System.out.println(result);
        return base;
    }

}