package com.dt042g.enigma.views;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for enigmaMainView
 * @author Anton Gunnarsson
 */
class EnigmaMainViewTest {
    EnigmaMainView enigmaMainView;
    @BeforeEach
    void setUp() {
        enigmaMainView = new EnigmaMainView();
    }

    @AfterEach
    void tearDown() {}

    /**
     * tests if the length of btnRowOne is nine
     */
    @Test
    void testsIfTheLengthOfBtnRowOneIsNine(){
        assertEquals(9, this.enigmaMainView.getBtnList("RowOne").size());
    }
    /**
     * tests if the length of btnRowTwo is eight
     */
    @Test
    void testsIfTheLengthOfBtnRowTwoIsEight(){
        assertEquals(8, this.enigmaMainView.getBtnList("RowTwo").size());
    }

    /**
     * tests if the length of btnRowThee is nine
     */
    @Test
    void testsIfTheLengthOfBtnRowThreeIsNine(){
        assertEquals(9, this.enigmaMainView.getBtnList("RowThree").size());
    }

    /**
     * tests if the length of lampRowOne is nine
     */
    @Test
    void testsIfTheLengthOfLampRowOneIsNine(){
        assertEquals(9, this.enigmaMainView.getLampList("RowOne").size());
    }

    /**
     * tests if the length of lampRowTwo is eight
     */
    @Test
    void testsIfTheLengthOfLampRowTwoIsEight(){
        assertEquals(8, this.enigmaMainView.getLampList("RowTwo").size());
    }

    /**
     * tests if the length of lampRowThree is nine
     */
    @Test
    void testsIfTheLengthOfLampRowThreeIsNine(){
        assertEquals(9, this.enigmaMainView.getLampList("RowThree").size());
    }

    /**
     * test if the inputField is reset
     */
    @Test
    void testIfInputFieldIsReset(){
        enigmaMainView.resetInput();
        assertEquals("",enigmaMainView.getInputText());
    }

    /**
     * test if the outputField is reset
     */
    @Test
    void testIfOutputFieldIsReset(){
        enigmaMainView.resetOutput();
        assertEquals("",enigmaMainView.getOutputText());
    }

    /**
     * test if backspace return AB when input is ABC
     */
    @Test
    void testInputBackspace(){
        enigmaMainView.setInputText("ABC");
        assertEquals("AB",enigmaMainView.inputBackspace());
    }

    /**
     * test if backspace return AB when output is ABC
     */
    @Test
    void testOutputBackspace(){
        enigmaMainView.setOutText("ABC");
        assertEquals("AB",enigmaMainView.outputBackspace());
    }

    /**
     * test so integer zero is returned when "Rotor one" is picked
     */
    @Test
    void testSoIfRotorOneIsPickedInDropDown0ShouldReturn(){
        assertEquals(0,enigmaMainView.pickRotor("Rotor one"));
    }

    /**
     * test so integer one is returned when "Rotor two" is picked
     */
    @Test
    void testSoIfRotorTwoIsPickedInDropDown1ShouldReturn(){
        assertEquals(1,enigmaMainView.pickRotor("Rotor two"));
    }

    /**
     * test so integer two is returned when "Rotor three" is picked
     */
    @Test
    void testSoIfRotorThreeIsPickedInDropDown2ShouldReturn(){
        assertEquals(2,enigmaMainView.pickRotor("Rotor three"));
    }

    /**
     * test so integer three is returned when "Rotor four" is picked
     */
    @Test
    void testSoIfRotorFourIsPickedInDropDown3ShouldReturn(){
        assertEquals(3,enigmaMainView.pickRotor("Rotor four"));
    }

    /**
     * test so integer four is returned when "Rotor five" is picked
     */
    @Test
    void testSoIfRotorFiveIsPickedInDropDown4ShouldReturn(){
        assertEquals(4,enigmaMainView.pickRotor("Rotor five"));
    }


}