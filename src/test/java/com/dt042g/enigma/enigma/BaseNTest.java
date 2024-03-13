package com.dt042g.enigma.enigma;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test cases for BaseN
 * @author Ruben Madsen
 */
class BaseNTest {

    /**
     * Assert not null
     */
    @Test
    void newBaseNNotNULL(){
        BaseN base = new BaseN(3,5);
        assertNotNull(base);
    }

    /**
     * Created BaseN should be Zero
     */
    @Test
    void createdBaseNEqualsZero(){
        BaseN base = new BaseN(3,5);
        assertEquals("0 0 0",base.toString());
    }

    /**
     * Assert setting the value of a BaseN number
     */
    @Test
    void setBaseNTo123(){
        BaseN base = new BaseN(3,5);
        base.setValue(new int[]{1,2,3});
        assertEquals("1 2 3",base.toString());
    }

    /**
     * Assert calling ZeroOut() sets the number to zero
     */
    @Test
    void zeroedOutEqualsZero(){
        BaseN base = new BaseN(3,5);
        base.setValue(new int[]{1,2,3});
        base.zeroOut();
        assertEquals("0 0 0",base.toString());
    }

    /**
     * Calling increment() should increase the number by one
     */
    @Test
    void incrementation(){
        BaseN base = new BaseN(3,5);
        base.setValue(new int[]{1,2,3});
        base.increment();
        assertEquals("1 2 4",base.toString());
    }

    /**
     * Increments digit two by three
     */
    @Test
    void incrementDigitTwoByThree(){
        BaseN base = new BaseN(3,5);
        base.setValue(new int[]{1,2,3});
        base.incrementDigit(2);
        base.incrementDigit(2);
        base.incrementDigit(2);
        assertEquals("4 2 3",base.toString());
    }
    /**
     * Increments digit two by three passed the number base
     */
    @Test
    void incrementDigitTwoByThreePassedBase(){
        BaseN base = new BaseN(3,5);
        base.setValue(new int[]{1,2,3});
        base.incrementDigit(1);
        base.incrementDigit(1);
        base.incrementDigit(1);
        base.incrementDigit(1);
        assertEquals("1 1 3",base.toString());
    }
    /**
     * Calling increment(N) should increase the number by N
     */
    @Test
    void incrementBySeven(){
        BaseN base = new BaseN(3,5);
        base.setValue(new int[]{1,2,3});
        base.increment(7);
        assertEquals("1 4 0",base.toString());
    }
    /**
     * Incrementing the number passed the number base should carry over to the
     * next digit
     */
    @Test
    void incrementPassedBase(){
        BaseN base = new BaseN(3,5);
        base.setValue(new int[]{1,2,4});
        base.increment();
        assertEquals("1 3 0",base.toString());
    }
    /**
     * Calling decrement() should decrease the number by one
     */
    @Test
    void decrementation(){
        BaseN base = new BaseN(3,5);
        base.setValue(new int[]{1,2,3});
        base.decrement();
        assertEquals("1 2 2",base.toString());
    }

    /**
     * Decrement the third digit by two
     */
    @Test
    void decrementDigitTwoByTwo(){
        BaseN base = new BaseN(3,5);
        base.setValue(new int[]{1,2,3});
        base.decrementDigit(1);
        base.decrementDigit(1);
        assertEquals("1 0 3",base.toString());
    }
    /**
     * Decrement the second digit by seven passing zero
     */
    @Test
    void decrementDigitOneBySevenPassingZero(){
        BaseN base = new BaseN(3,5);
        base.setValue(new int[]{1,2,3});
        for(int i=0; i<7; i++){
            base.decrementDigit(1);
        }
        assertEquals("1 0 3",base.toString());
    }
    /**
     * Decrementing the number passed zero should borrow from the
     * previous digit
     */
    @Test
    void decrementPassedZero(){
        BaseN base = new BaseN(3,5);
        base.setValue(new int[]{1,2,0});
        base.decrement();
        assertEquals("1 1 4",base.toString());
    }
    /**
     * Calling decrement(N) should decrease the number by N
     */
    @Test
    void decrementBySeven(){
        BaseN base = new BaseN(3,5);
        base.setValue(new int[]{1,4,0});
        base.decrement(7);
        assertEquals("1 2 3",base.toString());
    }

    /**
     * Confirm the value of the first digit
     */
    @Test
    void firstDigitEqualsOne(){
        BaseN base = new BaseN(3,5);
        base.setValue(new int[]{1,2,3});
        assertEquals(3,base.getNthDigit(0));
    }
    /**
     * Confirm the value of the second digit
     */
    @Test
    void secondDigitEqualsTwo(){
        BaseN base = new BaseN(3,5);
        base.setValue(new int[]{2,4,1});
        assertEquals(4,base.getNthDigit(1));

    }
    /**
     * Confirm the value of the third digit
     */
    @Test
    void threeDigitEqualsThree(){
        BaseN base = new BaseN(3,5);
        base.setValue(new int[]{2,4,0});
        assertEquals(2,base.getNthDigit(2));

    }
}