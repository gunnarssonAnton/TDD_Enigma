package com.dt042g.enigma.enigma;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the EnigmaMachine class
 * @author Ruben Madsen
 */
class EnigmaMachineTest {
    /**
     * Validate EnigmaMachine object not being null
     */
    @Test
    void newIsNotNULL(){
        EnigmaMachine enigma = new EnigmaMachine("abc");
        assertNotNull(enigma);
    }

    /**
     * Checks if the added Rotor is in the rotors list
     * @throws NoSuchFieldException If the variable 'rotors' does not exist
     * @throws IllegalAccessException Throws if access to 'rotors' is denied
     */
    @Test
    void addedRotorExists() throws NoSuchFieldException, IllegalAccessException {
        EnigmaMachine enigma = new EnigmaMachine("abc");
        Rotor r1 = new Rotor(0,0);
        enigma.addRotor(r1);
        Field field = enigma.getClass().getDeclaredField("rotors");
        field.setAccessible(true);
        List<Rotor> ref = (List<Rotor>)field.get(enigma);
        assertEquals(r1,ref.get(0));
    }

    @Test
    void replaceARotorWithRotor() throws NoSuchFieldException, IllegalAccessException {
        EnigmaMachine enigma = new EnigmaMachine("abc");
        Rotor r1 = new Rotor(0,0);
        Rotor r2 = new Rotor(0,0);
        Rotor r3 = new Rotor(0,0);
        Rotor swap = new Rotor(0,0);

        enigma.addRotor(r1);
        enigma.addRotor(r2);
        enigma.addRotor(r3);

        enigma.replaceRotorAtIndex(1,swap);
        Field field = enigma.getClass().getDeclaredField("rotors");
        field.setAccessible(true);
        List<Rotor> ref = (List<Rotor>)field.get(enigma);
        assertEquals(swap,ref.get(1));
    }

    /**
     * Checks if the added Reflector exists
     * @throws NoSuchFieldException If the variable 'rotors' does not exist
     * @throws IllegalAccessException Throws if access to 'rotors' is denied
     */
    @Test
    void addedReflectorExists() throws NoSuchFieldException, IllegalAccessException {
        EnigmaMachine enigma = new EnigmaMachine("abc");
        Reflector r1 = new Reflector(0,0);
        enigma.setReflector(r1);
        Field field = enigma.getClass().getDeclaredField("reflector");
        field.setAccessible(true);
        Reflector ref = (Reflector)field.get(enigma);
        assertEquals(r1,ref);
    }

    /**
     * Checks if the correct character is returned when sending "a"
     * through the Enigma using ONE Rotor
     */
    @Test
    void encryptCharacterUsingOneRotor(){
        EnigmaMachine enigma = new EnigmaMachine(EnigmaMachine.chars);
        Rotor r1 = new Rotor(0,0);
        Reflector ref1 = new Reflector(0,0);
        enigma.addRotor(r1);
        enigma.setReflector(ref1);
        int a = r1.passForwards(0);
        int b = ref1.passForwards(a);
        int c = r1.passBackwards(b);
        String expected = EnigmaMachine.chars.charAt(c) + "";
        assertEquals(expected,enigma.getEncoded("a"));
    }
    /**
     * Checks if the correct character is returned when sending "a"
     * through the Enigma using THREE Rotors
     */
    @Test
    void encryptCharacterUsingThreeRotors(){
        EnigmaMachine enigma = new EnigmaMachine(EnigmaMachine.chars);
        Rotor r1 = new Rotor(0,0);
        Rotor r2 = new Rotor(0,0);
        Rotor r3 = new Rotor(0,0);
        Reflector ref1 = new Reflector(0,0);
        enigma.addRotor(r1);
        enigma.addRotor(r2);
        enigma.addRotor(r3);
        enigma.setReflector(ref1);
        int a = r1.passForwards(0);
        int b = r2.passForwards(a);
        int c = r3.passForwards(b);
        int d = ref1.passForwards(c);
        int e = r3.passBackwards(d);
        int f = r2.passBackwards(e);
        int g = r1.passBackwards(f);
        String expected = EnigmaMachine.chars.charAt(g) + "";
        assertEquals(expected,enigma.getEncoded("a"));
    }

    /**
     * Checks if the counter has ticked 4 steps when encrypting
     * a four character string
     *
     * @throws NoSuchFieldException If the variable 'counter' does not exist
     * @throws IllegalAccessException Throws if access to 'counter' is denied
     */
    @Test
    void counterAfterFourCharsWithThreeRotorsEquals004() throws NoSuchFieldException, IllegalAccessException {
        EnigmaMachine enigma = new EnigmaMachine(EnigmaMachine.chars);
        Rotor r1 = new Rotor(0,0);
        Rotor r2 = new Rotor(0,0);
        Rotor r3 = new Rotor(0,0);
        Reflector ref1 = new Reflector(0,0);
        enigma.addRotor(r1);
        enigma.addRotor(r2);
        enigma.addRotor(r3);
        enigma.setReflector(ref1);
        enigma.getEncoded("abcd");

        Field field = enigma.getClass().getDeclaredField("counter");
        field.setAccessible(true);
        BaseN counter = (BaseN)field.get(enigma);

        assertEquals("0 0 4",counter.toString());
    }

    /**
     * Checks if the counter is 0 0 0 when encrypting
     * a four character string and then resetting the Rotors
     * @throws NoSuchFieldException If the variable 'counter' does not exist
     * @throws IllegalAccessException Throws if access to 'counter' is denied
     */
    @Test
    void resetRotors() throws NoSuchFieldException, IllegalAccessException {
        EnigmaMachine enigma = new EnigmaMachine(EnigmaMachine.chars);
        Rotor r1 = new Rotor(0,0);
        Rotor r2 = new Rotor(0,0);
        Rotor r3 = new Rotor(0,0);
        Reflector ref1 = new Reflector(0,0);
        enigma.addRotor(r1);
        enigma.addRotor(r2);
        enigma.addRotor(r3);
        enigma.setReflector(ref1);
        enigma.getEncoded("abcd");
        enigma.reset();

        Field field = enigma.getClass().getDeclaredField("counter");
        field.setAccessible(true);
        BaseN counter = (BaseN)field.get(enigma);

        assertEquals("0 0 0",counter.toString());
    }
    @Test
    void test(){
        EnigmaMachine enigma = new EnigmaMachine(EnigmaMachine.chars);
        enigma.addRotor(new Rotor(0,0));
        enigma.addRotor(new Rotor(0,0));
        enigma.addRotor(new Rotor(0,0));
        enigma.setReflector(new Reflector(0,0));
        enigma.getEncoded("Enigma");
        assertEquals("[6, 0, 0]",enigma.getReelOffsets().toString());
    }
    @Test
    void ABCEqualsBackspaceABC(){
        EnigmaMachine enigma = new EnigmaMachine(EnigmaMachine.chars);
        enigma.addRotor(new Rotor(0,0));
        enigma.addRotor(new Rotor(0,0));
        enigma.addRotor(new Rotor(0,0));
        enigma.setReflector(new Reflector(0,0));
        String str1 = enigma.getEncoded("abc");
        enigma.backspace();
        enigma.backspace();
        enigma.backspace();
        String str2 = enigma.getEncoded("abc");
        assertEquals(str1,str2);
    }
}