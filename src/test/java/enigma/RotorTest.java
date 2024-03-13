package enigma;

import com.dt042g.enigma.enigma.EnigmaMachine;
import com.dt042g.enigma.enigma.Reel;
import com.dt042g.enigma.enigma.Rotor;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for a Rotor
 * @author Ruben Madsen
 */
class RotorTest {
    /**
     * Checks if a Reel's values are null
     * @throws NoSuchFieldException Throws if a class variable is not found
     */
    @Test
    void valuesIsNotNull() throws NoSuchFieldException {
        Rotor rotor = new Rotor(0,0);
        Field field = Reel.class.getDeclaredField("values");
        field.setAccessible(true);
        assertNotNull(field);
    }
    // Pass forwards

    /**
     * Asserts that the value for a given index is correct
     */
    @Test
    void passForwardsIndexZero(){
        Rotor rotor = new Rotor(0,0);
        assertEquals(23,rotor.passForwards(0));
    }
    /**
     * Asserts that the value for a given index is correct
     */
    @Test
    void passForwardsIndexTen(){
        Rotor rotor = new Rotor(0,0);
        assertEquals(22,rotor.passForwards(10));
    }
    /**
     * Asserts that the value for a given index is correct
     */
    @Test
    void passForwardsIndexLast() throws NoSuchFieldException, IllegalAccessException {
        Rotor rotor = new Rotor(0,0);
        Field field = Reel.class.getDeclaredField("values");
        field.setAccessible(true);
        List<Integer> values = (List<Integer>) field.get(rotor);
        int lastIndex = values.size()-1;
        assertEquals(18,rotor.passForwards(lastIndex));
    }
    // with offset
    /**
     * Asserts that the value for a given index is correct with
     * a given offset
     */
    @Test
    void passForwardIndexZeroWithOffsetFourEqualsEleven(){
        Rotor rotor = new Rotor(0,0);
        rotor.setOffset(4);
        assertEquals(11,rotor.passForwards(0));
    }
    /**
     * Asserts that the value for a given index is correct with
     * a given offset
     */
    @Test
    void passForwardIndexTenWithOffsetFourEqualsThree(){
        Rotor rotor = new Rotor(0,0);
        rotor.setOffset(4);
        assertEquals(3,rotor.passForwards(10));
    }
    /**
     * Asserts that the value for a given index is correct with
     * a given offset
     */
    @Test
    void passForwardIndexTwentySixWithOffsetFourEqualsNine(){
        Rotor rotor = new Rotor(0,0);
        rotor.setOffset(4);
        assertEquals(9,rotor.passForwards(26));
    }

    // Pass backwards
    /**
     * Asserts that the index is correct for the given value
     */
    @Test
    void passBackwardsIndexOfTwentyThreeShouldBeZero(){
        Rotor rotor = new Rotor(0,0);
        assertEquals(0,rotor.passBackwards(23));
    }
    /**
     * Asserts that the index is correct for the given value
     */
    @Test
    void passBackwardsIndexOfLastValueEqualsTwentySix() {
        Rotor rotor = new Rotor(0,0);
        assertEquals(26,rotor.passBackwards(18));
    }
    /**
     * Asserts that the index is correct for the given value
     */
    @Test
    void passBackwardsIndexOfTwentyTwoEqualsTen() {
        Rotor rotor = new Rotor(0,0);
        assertEquals(10,rotor.passBackwards(22));
    }

    // with offset

    /**
     * Asserts that the index is correct for the given value with
     * an offset of 4
     */
    @Test
    void passBackwardValueZeroWithOffsetFourEqualsFifteen(){
        Rotor rotor = new Rotor(0,0);
        rotor.setOffset(4);
        assertEquals(15,rotor.passBackwards(0));
    }
    /**
     * Asserts that the index is correct for the given value with
     * an offset of 4
     */
    @Test
    void passBackwardValueTenWithOffsetFourEqualsSeventeen(){
        Rotor rotor = new Rotor(0,0);
        rotor.setOffset(4);
        assertEquals(17,rotor.passBackwards(10));
    }
    /**
     * Asserts that the index is correct for the given value with
     * an offset of 4
     */
    @Test
    void passBackwardValueTwentySixWithOffsetFourEqualsEight(){
        Rotor rotor = new Rotor(0,0);
        rotor.setOffset(4);
        assertEquals(8,rotor.passBackwards(26));
    }
    // Forwards equals backwards

    /**
     * Asserts that passing an index to passForward()
     * and passing the returned value to passBackwards()
     * returns the original index passed
     */
    @Test
    void passForwardPassBackwardFirstIndex(){
        Rotor rotor = new Rotor(0,0);
        int value = rotor.passForwards(0);
        assertEquals(0,rotor.passBackwards(value));

    }
    /**
     * Asserts that passing an index to passForward()
     * and passing the returned value to passBackwards()
     * returns the original index passed
     */
    @Test
    void passForwardPassBackwardIndexTen(){
        Rotor rotor = new Rotor(0,0);
        int value = rotor.passForwards(10);
        assertEquals(10,rotor.passBackwards(value));

    }
    /**
     * Asserts that passing an index to passForward()
     * and passing the returned value to passBackwards()
     * returns the original index passed
     */
    @Test
    void passForwardPassBackwardLastIndex(){
        Rotor rotor = new Rotor(0,0);
        int value = rotor.passForwards(26);
        assertEquals(26,rotor.passBackwards(value));

    }
    // Forwards equals backwards with offset
    /**
     * Asserts that passing an index to passForward()
     * and passing the returned value to passBackwards()
     * returns the original index passed with an initial offset
     * of 10
     */
    @Test
    void passForwardPassBackwardFirstIndexWithOffsetTen(){
        Rotor rotor = new Rotor(0,0);
        rotor.setOffset(10);
        int value = rotor.passForwards(0);
        assertEquals(0,rotor.passBackwards(value));

    }
    /**
     * Asserts that passing an index to passForward()
     * and passing the returned value to passBackwards()
     * returns the original index passed with an initial offset
     * of 10
     */
    @Test
    void passForwardPassBackwardIndexTenWithOffsetTen(){
        Rotor rotor = new Rotor(0,0);
        rotor.setOffset(10);
        int value = rotor.passForwards(10);
        assertEquals(10,rotor.passBackwards(value));

    }
    /**
     * Asserts that passing an index to passForward()
     * and passing the returned value to passBackwards()
     * returns the original index passed with an initial offset
     * of 10
     */
    @Test
    void passForwardPassBackwardLastIndexWithOffsetTen(){
        Rotor rotor = new Rotor(0,0);
        rotor.setOffset(10);
        int value = rotor.passForwards(26);
        assertEquals(26,rotor.passBackwards(value));

    }

    /**
     * Checks if values occur once and only once
     */
    @Test
    void RotorShouldNotHaveDuplicateValues(){
        List<Integer> values = Rotor.generateRotor(EnigmaMachine.chars);
        int[] duplicates = new int[values.size()];
        for(int i=0; i<values.size(); i++){
            int value = values.get(i);
            duplicates[value]++;
        }
        for(Integer value : duplicates){
            assertEquals(1,value);
        }
    }
}
