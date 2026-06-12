package org.example;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NumberUtilsTest {

    @Test
    public void testBothNull() {
        assertNull(NumberUtils.add(null, null));
    }

    @Test
    public void testLeftNull() {
        assertNull(NumberUtils.add(null, Arrays.asList(1, 2)));
    }

    @Test
    public void testRightNull() {
        assertNull(NumberUtils.add(Arrays.asList(1, 2), null));
    }

    @Test
    public void testBothEmpty() {
        List<Integer> result = NumberUtils.add(Arrays.asList(), Arrays.asList());
        assertEquals(Arrays.asList(0), result);
    }

    @Test
    public void testLeftEmpty() {
        List<Integer> result = NumberUtils.add(Arrays.asList(), Arrays.asList(5));
        assertEquals(Arrays.asList(5), result);
    }

    @Test
    public void testRightEmpty() {
        List<Integer> result = NumberUtils.add(Arrays.asList(5), Arrays.asList());
        assertEquals(Arrays.asList(5), result);
    }

    @Test
    public void testSimpleAddition() {
        // [2,3] + [4,2] = [6,5] (23 + 42 = 65)
        List<Integer> result = NumberUtils.add(Arrays.asList(2, 3), Arrays.asList(4, 2));
        assertEquals(Arrays.asList(6, 5), result);
    }

    @Test
    public void testSingleDigits() {
        // [3] + [4] = [7]
        List<Integer> result = NumberUtils.add(Arrays.asList(3), Arrays.asList(4));
        assertEquals(Arrays.asList(7), result);
    }

    @Test
    public void testSingleDigitsWithCarry() {
        // [5] + [6] = [1,1] (5 + 6 = 11)
        List<Integer> result = NumberUtils.add(Arrays.asList(5), Arrays.asList(6));
        assertEquals(Arrays.asList(1, 1), result);
    }

    @Test
    public void testMultipleDigitsNoCarry() {
        // [1,2,3] + [4,5,6] = [5,7,9] (123 + 456 = 579)
        List<Integer> result = NumberUtils.add(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6));
        assertEquals(Arrays.asList(5, 7, 9), result);
    }

    @Test
    public void testMultipleDigitsWithCarry() {
        // [9,9,9] + [1] = [1,0,0,0] (999 + 1 = 1000)
        List<Integer> result = NumberUtils.add(Arrays.asList(9, 9, 9), Arrays.asList(1));
        assertEquals(Arrays.asList(1, 0, 0, 0), result);
    }

    @Test
    public void testCarryAtEveryPosition() {
        // [9,9] + [1] = [1,0,0] (99 + 1 = 100)
        List<Integer> result = NumberUtils.add(Arrays.asList(9, 9), Arrays.asList(1));
        assertEquals(Arrays.asList(1, 0, 0), result);
    }

    @Test
    public void testDifferentLengths() {
        // [1,2] + [3,4,5] = [4,6,7] (12 + 345 = 357)
        List<Integer> result = NumberUtils.add(Arrays.asList(1, 2), Arrays.asList(3, 4, 5));
        assertEquals(Arrays.asList(3, 5, 7), result);
    }

    @Test
    public void testZeroAndNumber() {
        // [0] + [5] = [5]
        List<Integer> result = NumberUtils.add(Arrays.asList(0), Arrays.asList(5));
        assertEquals(Arrays.asList(5), result);
    }

    @Test
    public void testZeroAndZero() {
        // [0] + [0] = [0]
        List<Integer> result = NumberUtils.add(Arrays.asList(0), Arrays.asList(0));
        assertEquals(Arrays.asList(0), result);
    }

    @Test
    public void testLeadingZeroRemoval() {
        // [0,0,5] + [0,0,4] = [9] (005 + 004 = 9)
        // After adding: [0, 0, 9], then leading zeros removed to [9]
        List<Integer> result = NumberUtils.add(Arrays.asList(0, 0, 5), Arrays.asList(0, 0, 4));
        assertEquals(Arrays.asList(9), result);
    }

    @Test
    public void testLeadingZeroRemovalMultiple() {
        // [0,0,1,2] + [0,0,3,4] = [4,6] (0012 + 0034 = 46)
        List<Integer> result = NumberUtils.add(Arrays.asList(0, 0, 1, 2), Arrays.asList(0, 0, 3, 4));
        assertEquals(Arrays.asList(4, 6), result);
    }

    @Test
    public void testPreserveSingleZero() {
        // [0,0,0,1] + [0,0,0,9] = [1,0] (0001 + 0009 = 10)
        List<Integer> result = NumberUtils.add(Arrays.asList(0, 0, 0, 1), Arrays.asList(0, 0, 0, 9));
        assertEquals(Arrays.asList(1, 0), result);
    }

    @Test
    public void testInvalidNegativeLeft() {
        assertThrows(IllegalArgumentException.class, () -> {
            NumberUtils.add(Arrays.asList(-1, 2), Arrays.asList(1, 0));
        });
    }

    @Test
    public void testInvalidNegativeRight() {
        assertThrows(IllegalArgumentException.class, () -> {
            NumberUtils.add(Arrays.asList(1, 2), Arrays.asList(-1, 0));
        });
    }

    @Test
    public void testInvalidGreaterThan9Left() {
        assertThrows(IllegalArgumentException.class, () -> {
            NumberUtils.add(Arrays.asList(10, 2), Arrays.asList(1, 0));
        });
    }

    @Test
    public void testInvalidGreaterThan9Right() {
        assertThrows(IllegalArgumentException.class, () -> {
            NumberUtils.add(Arrays.asList(1, 2), Arrays.asList(1, 10));
        });
    }

    @Test
    public void testInvalidMixedRange() {
        assertThrows(IllegalArgumentException.class, () -> {
            NumberUtils.add(Arrays.asList(15, 2), Arrays.asList(1, 20));
        });
    }

    @Test
    public void testValidBoundaries() {
        // [0,0] + [9,9] = [9,9]
        List<Integer> result = NumberUtils.add(Arrays.asList(0, 0), Arrays.asList(9, 9));
        assertEquals(Arrays.asList(9, 9), result);
    }

    @Test
    public void testMaxDigit() {
        // [9] + [9] = [1,8] (9 + 9 = 18)
        List<Integer> result = NumberUtils.add(Arrays.asList(9), Arrays.asList(9));
        assertEquals(Arrays.asList(1, 8), result);
    }

    @Test
    public void testAsymmetricLength() {
        // [9,9,9,9,9] + [1] = [1,0,0,0,0,0] (99999 + 1 = 100000)
        List<Integer> result = NumberUtils.add(Arrays.asList(9, 9, 9, 9, 9), Arrays.asList(1));
        assertEquals(Arrays.asList(1, 0, 0, 0, 0, 0), result);
    }

    @Test
    public void testSmallNumberLargeNumber() {
        // [5] + [1,2,3,4] = [1,2,3,9] (5 + 1234 = 1239)
        List<Integer> result = NumberUtils.add(Arrays.asList(5), Arrays.asList(1, 2, 3, 4));
        assertEquals(Arrays.asList(1, 2, 3, 9), result);
    }

    @Test
    public void testLeadingZeroInResult() {
        // [0,5,0] + [0,4,5] = [9,5] (050 + 045 = 95)
        List<Integer> result = NumberUtils.add(Arrays.asList(0, 5, 0), Arrays.asList(0, 4, 5));
        assertEquals(Arrays.asList(9, 5), result);
    }

    @Test
    public void testCarryOverflow() {
        // [9,9,9,9,9,9,9,9,9] + [1] = [1,0,0,0,0,0,0,0,0,0]
        List<Integer> result = NumberUtils.add(
            Arrays.asList(9, 9, 9, 9, 9, 9, 9, 9, 9),
            Arrays.asList(1)
        );
        assertEquals(Arrays.asList(1, 0, 0, 0, 0, 0, 0, 0, 0, 0), result);
    }
}
