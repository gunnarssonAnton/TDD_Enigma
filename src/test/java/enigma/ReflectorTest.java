package enigma;

import com.dt042g.enigma.enigma.EnigmaMachine;
import com.dt042g.enigma.enigma.Reel;
import com.dt042g.enigma.enigma.Reflector;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Test class for a Reflector
 * @author Ruben Madsen
 */
class ReflectorTest {
    /**
     * Checks if a Reel's values are null
     * @throws NoSuchFieldException Throws if a class variable is not found
     */
    @Test
    void valuesIsNotNull() throws NoSuchFieldException {
        Reflector reflector = new Reflector(0,0);
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
        Reflector reflector = new Reflector(0,0);
        assertEquals(5,reflector.passForwards(0));
    }
    /**
     * Asserts that the value for a given index is correct
     */
    @Test
    void passForwardsIndexSeven(){
        Reflector reflector = new Reflector(0,0);
        assertEquals(7,reflector.passForwards(10));
    }
    /**
     * Asserts that the value for a given index is correct
     */
    @Test
    void passForwardsIndexLast() throws NoSuchFieldException, IllegalAccessException {
        Reflector reflector = new Reflector(0,0);
        Field field = Reel.class.getDeclaredField("values");
        field.setAccessible(true);
        List<Integer> values = (List<Integer>) field.get(reflector);
        int lastIndex = values.size()-1;
        assertEquals(12,reflector.passForwards(lastIndex));
    }
    // with offset
    /**
     * Asserts that the value for a given index is correct with
     * a given offset
     */
    @Test
    void passForwardIndexZeroWithOffsetFourEqualsNineteen(){
        Reflector reflector = new Reflector(0,0);
        reflector.setOffset(4);
        assertEquals(19,reflector.passForwards(0));
    }
    /**
     * Asserts that the value for a given index is correct with
     * a given offset
     */
    @Test
    void passForwardIndexTenWithOffsetFourEqualsTwentyFour(){
        Reflector reflector = new Reflector(0,0);
        reflector.setOffset(4);
        assertEquals(24,reflector.passForwards(10));
    }
    /**
     * Asserts that the value for a given index is correct with
     * a given offset
     */
    @Test
    void passForwardIndexTwentySixWithOffsetFourEqualsTwenty(){
        Reflector reflector = new Reflector(0,0);
        reflector.setOffset(4);
        assertEquals(20,reflector.passForwards(26));
    }

    // Pass backwards
    /**
     * Asserts that the index is correct for the given value
     */
    @Test
    void passBackwardsIndexOfTwentyThreeShouldBeEight(){
        Reflector reflector = new Reflector(0,0);
        assertEquals(8,reflector.passBackwards(23));
    }
    /**
     * Asserts that the index is correct for the given value
     */
    @Test
    void passBackwardsIndexOfLastValueEqualsTwentySix() {
        Reflector reflector = new Reflector(0,0);
        assertEquals(26,reflector.passBackwards(12));
    }
    /**
     * Asserts that the index is correct for the given value
     */
    @Test
    void passBackwardsIndexOfTwentyTwoEqualsSeventeen() {
        Reflector reflector = new Reflector(0,0);
        assertEquals(17,reflector.passBackwards(22));
    }

    // with offset

    /**
     * Asserts that the index is correct for the given value with
     * an offset of 4
     */
    @Test
    void passBackwardValueZeroWithOffsetFourEqualsFifteen(){
        Reflector reflector = new Reflector(0,0);
        reflector.setOffset(4);
        assertEquals(1,reflector.passBackwards(0));
    }
    /**
     * Asserts that the index is correct for the given value with
     * an offset of 4
     */
    @Test
    void passBackwardValueTenWithOffsetFourEqualsSeventeen(){
        Reflector reflector = new Reflector(0,0);
        reflector.setOffset(4);
        assertEquals(3,reflector.passBackwards(10));
    }

    /**
     * Asserts that the index is correct for the given value with
     * an offset of 4
     */
    @Test
    void passBackwardValueTwentySixWithOffsetFourEqualsEight(){
        Reflector reflector = new Reflector(0,0);
        reflector.setOffset(4);
        assertEquals(8,reflector.passBackwards(26));
    }

    // Forwards equals backwards

    /**
     * Asserts that passing an index to passForward()
     * and passing the returned value to passBackwards()
     * returns the original index passed
     */
    @Test
    void passForwardPassBackwardFirstIndex(){
        Reflector reflector = new Reflector(0,0);
        int value = reflector.passForwards(0);
        assertEquals(0,reflector.passBackwards(value));

    }
    /**
     * Asserts that passing an index to passForward()
     * and passing the returned value to passBackwards()
     * returns the original index passed
     */
    @Test
    void passForwardPassBackwardIndexTen(){
        Reflector reflector = new Reflector(0,0);
        int value = reflector.passForwards(10);
        assertEquals(10,reflector.passBackwards(value));

    }
    /**
     * Asserts that passing an index to passForward()
     * and passing the returned value to passBackwards()
     * returns the original index passed
     */
    @Test
    void passForwardPassBackwardLastIndex(){
        Reflector reflector = new Reflector(0,0);
        int value = reflector.passForwards(26);
        assertEquals(26,reflector.passBackwards(value));

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
        Reflector reflector = new Reflector(0,0);
        reflector.setOffset(10);
        int value = reflector.passForwards(0);
        assertEquals(0,reflector.passBackwards(value));

    }
    /**
     * Asserts that passing an index to passForward()
     * and passing the returned value to passBackwards()
     * returns the original index passed with an initial offset
     * of 10
     */
    @Test
    void passForwardPassBackwardIndexTenWithOffsetTen(){
        Reflector reflector = new Reflector(0,0);
        reflector.setOffset(10);
        int value = reflector.passForwards(10);
        assertEquals(10,reflector.passBackwards(value));

    }
    /**
     * Asserts that passing an index to passForward()
     * and passing the returned value to passBackwards()
     * returns the original index passed with an initial offset
     * of 10
     */
    @Test
    void passForwardPassBackwardLastIndexWithOffsetTen(){
        Reflector reflector = new Reflector(0,0);
        reflector.setOffset(10);
        int value = reflector.passForwards(26);
        assertEquals(26,reflector.passBackwards(value));

    }

    /**
     * Assert that the using the value received from passForward() as an index
     * in a consecutive passForewards() call, retrieves the first index passed
     * @throws NoSuchFieldException Throws if a class variable is not found
     * @throws IllegalAccessException If access to the field is denied
     */
    @Test
    void indexValuePairing() throws NoSuchFieldException, IllegalAccessException {
        Reflector reflector = new Reflector(0,0);
        Field field = Reel.class.getDeclaredField("values");
        field.setAccessible(true);
        List<Integer> values = (List<Integer>) field.get(reflector);
        for (int i = 0; i < values.size(); i++) {
            int index1 = i;
            int value1 = reflector.passForwards(index1);
            int index2 = reflector.passForwards(value1);
            assertEquals(index1,index2);
        }
    }

    /**
     * Checks if using the  value for an index, as an index return a
     * value that is equals to the original index
     */
    @Test
    void generateRefletorValueOfIndexEqualsIndexOfValue(){
        List<Integer> values = Reflector.generateReflector(EnigmaMachine.chars);
        for (int i=0; i<values.size(); i++){
            int value = values.get(i);
            assertEquals(i,values.indexOf(value));
        }
    }
}