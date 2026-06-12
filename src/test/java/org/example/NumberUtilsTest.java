package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("NumberUtils Addition Tests")
class NumberUtilsTest {

    // ============ Null and Empty Input Tests ============

    @Test
    @DisplayName("Both inputs are null should return null")
    void testBothNull() {
        assertNull(NumberUtils.add(null, null));
    }

    @Test
    @DisplayName("Left null, right non-null should return null")
    void testLeftNull() {
        assertNull(NumberUtils.add(null, Arrays.asList(1, 2)));
    }

    @Test
    @DisplayName("Right null, left non-null should return null")
    void testRightNull() {
        assertNull(NumberUtils.add(Arrays.asList(1, 2), null));
    }

    @Test
    @DisplayName("Both empty lists should return [0]")
    void testBothEmpty() {
        List<Integer> result = NumberUtils.add(new LinkedList<>(), new LinkedList<>());
        assertEquals(Arrays.asList(0), result);
    }

    @Test
    @DisplayName("Left empty, right has value should return right value")
    void testLeftEmpty() {
        List<Integer> result = NumberUtils.add(new LinkedList<>(), Arrays.asList(1, 2));
        assertEquals(Arrays.asList(1, 2), result);
    }

    @Test
    @DisplayName("Right empty, left has value should return left value")
    void testRightEmpty() {
        List<Integer> result = NumberUtils.add(Arrays.asList(1, 2), new LinkedList<>());
        assertEquals(Arrays.asList(1, 2), result);
    }

    // ============ Basic Addition Tests ============

    @Test
    @DisplayName("Simple addition 23 + 42 = 65")
    void testSimpleAddition() {
        List<Integer> left = Arrays.asList(2, 3);
        List<Integer> right = Arrays.asList(4, 2);
        List<Integer> result = NumberUtils.add(new LinkedList<>(left), new LinkedList<>(right));
        assertEquals(Arrays.asList(6, 5), result);
    }

    @Test
    @DisplayName("Single digit addition 5 + 3 = 8")
    void testSingleDigitAddition() {
        List<Integer> result = NumberUtils.add(Arrays.asList(5), Arrays.asList(3));
        assertEquals(Arrays.asList(8), result);
    }

    @Test
    @DisplayName("Addition with zero 10 + 0 = 10")
    void testAdditionWithZero() {
        List<Integer> result = NumberUtils.add(Arrays.asList(1, 0), Arrays.asList(0));
        assertEquals(Arrays.asList(1, 0), result);
    }

    // ============ Carry Tests ============

    @Test
    @DisplayName("Addition with single carry 9 + 1 = 10")
    void testSingleCarry() {
        List<Integer> result = NumberUtils.add(Arrays.asList(9), Arrays.asList(1));
        assertEquals(Arrays.asList(1, 0), result);
    }

    @Test
    @DisplayName("Addition with carry 99 + 1 = 100")
    void testMultipleCarry() {
        List<Integer> result = NumberUtils.add(Arrays.asList(9, 9), Arrays.asList(1));
        assertEquals(Arrays.asList(1, 0, 0), result);
    }

    @Test
    @DisplayName("Addition with propagating carry 999 + 1 = 1000")
    void testPropagatingCarry() {
        List<Integer> result = NumberUtils.add(Arrays.asList(9, 9, 9), Arrays.asList(1));
        assertEquals(Arrays.asList(1, 0, 0, 0), result);
    }

    @Test
    @DisplayName("Both numbers with carry 99 + 99 = 198")
    void testBothNumbersWithCarry() {
        List<Integer> result = NumberUtils.add(Arrays.asList(9, 9), Arrays.asList(9, 9));
        assertEquals(Arrays.asList(1, 9, 8), result);
    }

    // ============ Different Length Tests ============

    @Test
    @DisplayName("Different lengths: short + long")
    void testDifferentLengthsShortPlusLong() {
        List<Integer> result = NumberUtils.add(Arrays.asList(1), Arrays.asList(9, 9, 9));
        assertEquals(Arrays.asList(1, 0, 0, 0), result);
    }

    @Test
    @DisplayName("Different lengths: long + short")
    void testDifferentLengthsLongPlusShort() {
        List<Integer> result = NumberUtils.add(Arrays.asList(9, 9, 9), Arrays.asList(1));
        assertEquals(Arrays.asList(1, 0, 0, 0), result);
    }

    // ============ Leading Zero Tests ============

    @Test
    @DisplayName("Result with leading zeros should be removed 100 + 0 = 100")
    void testLeadingZeroRemoval() {
        List<Integer> result = NumberUtils.add(Arrays.asList(1, 0, 0), Arrays.asList(0));
        assertEquals(Arrays.asList(1, 0, 0), result);
    }

    @Test
    @DisplayName("Input with leading zeros: 001 + 002 = 003")
    void testInputWithLeadingZeros() {
        List<Integer> result = NumberUtils.add(Arrays.asList(0, 0, 1), Arrays.asList(0, 0, 2));
        assertEquals(Arrays.asList(3), result);
    }

    @Test
    @DisplayName("Result should keep at least one digit when zero")
    void testZeroResultHasOneZero() {
        List<Integer> result = NumberUtils.add(Arrays.asList(0), Arrays.asList(0));
        assertEquals(Arrays.asList(0), result);
    }

    // ============ Invalid Digit Tests ============

    @Test
    @DisplayName("Negative digit in left should throw IllegalArgumentException")
    void testNegativeDigitInLeft() {
        assertThrows(IllegalArgumentException.class,
            () -> NumberUtils.add(Arrays.asList(-1, 2), Arrays.asList(1, 0)));
    }

    @Test
    @DisplayName("Digit > 9 in left should throw IllegalArgumentException")
    void testDigitGreaterThan9InLeft() {
        assertThrows(IllegalArgumentException.class,
            () -> NumberUtils.add(Arrays.asList(10, 2), Arrays.asList(1, 0)));
    }

    @Test
    @DisplayName("Negative digit in right should throw IllegalArgumentException")
    void testNegativeDigitInRight() {
        assertThrows(IllegalArgumentException.class,
            () -> NumberUtils.add(Arrays.asList(1, 2), Arrays.asList(-1, 0)));
    }

    @Test
    @DisplayName("Digit > 9 in right should throw IllegalArgumentException")
    void testDigitGreaterThan9InRight() {
        assertThrows(IllegalArgumentException.class,
            () -> NumberUtils.add(Arrays.asList(1, 2), Arrays.asList(10, 0)));
    }

    // ============ Edge Cases ============

    @Test
    @DisplayName("Maximum single digit values 9 + 9 = 18")
    void testMaximumSingleDigits() {
        List<Integer> result = NumberUtils.add(Arrays.asList(9), Arrays.asList(9));
        assertEquals(Arrays.asList(1, 8), result);
    }

    @Test
    @DisplayName("All zeros 0 + 0 = 0")
    void testAllZeros() {
        List<Integer> result = NumberUtils.add(Arrays.asList(0), Arrays.asList(0));
        assertEquals(Arrays.asList(0), result);
    }

    @Test
    @DisplayName("Multiple digit zeros 000 + 000 = 0")
    void testMultipleZeros() {
        List<Integer> result = NumberUtils.add(Arrays.asList(0, 0, 0), Arrays.asList(0, 0, 0));
        assertEquals(Arrays.asList(0), result);
    }

    @Test
    @DisplayName("Large numbers addition")
    void testLargeNumbersAddition() {
        List<Integer> result = NumberUtils.add(
            Arrays.asList(9, 9, 9, 9, 9),
            Arrays.asList(9, 9, 9, 9, 9)
        );
        assertEquals(Arrays.asList(1, 9, 9, 9, 9, 8), result);
    }

    @Test
    @DisplayName("Addition without carry in middle digits")
    void testAdditionWithoutCarryInMiddle() {
        List<Integer> result = NumberUtils.add(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6));
        assertEquals(Arrays.asList(5, 7, 9), result);
    }

    // ============ Additional Edge Cases for Coverage ============

    @Test
    @DisplayName("Invalid digit in middle of left list")
    void testInvalidDigitMiddleLeft() {
        assertThrows(IllegalArgumentException.class,
            () -> NumberUtils.add(Arrays.asList(1, 10, 3), Arrays.asList(1, 0)));
    }

    @Test
    @DisplayName("Invalid digit in middle of right list")
    void testInvalidDigitMiddleRight() {
        assertThrows(IllegalArgumentException.class,
            () -> NumberUtils.add(Arrays.asList(1, 2), Arrays.asList(1, -1, 3)));
    }

    @Test
    @DisplayName("Boundary: minimum valid digit 0")
    void testMinimumValidDigit() {
        List<Integer> result = NumberUtils.add(Arrays.asList(0), Arrays.asList(0));
        assertEquals(Arrays.asList(0), result);
    }

    @Test
    @DisplayName("Boundary: maximum valid digit 9")
    void testMaximumValidDigit() {
        List<Integer> result = NumberUtils.add(Arrays.asList(9), Arrays.asList(0));
        assertEquals(Arrays.asList(9), result);
    }

    @Test
    @DisplayName("Mix of valid digits across list")
    void testMixOfValidDigits() {
        List<Integer> result = NumberUtils.add(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9),
                                              Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8));
        assertEquals(Arrays.asList(1, 3, 5, 8, 0, 2, 4, 6, 7), result);
    }

    @Test
    @DisplayName("Very large carry cascade")
    void testVeryLargeCarryCascade() {
        List<Integer> result = NumberUtils.add(Arrays.asList(9, 9, 9, 9, 9, 9, 9, 9),
                                              Arrays.asList(1));
        assertEquals(Arrays.asList(1, 0, 0, 0, 0, 0, 0, 0, 0), result);
    }

    @Test
    @DisplayName("Single zero in list with other values")
    void testZeroWithOtherValues() {
        List<Integer> result = NumberUtils.add(Arrays.asList(1, 0, 1), Arrays.asList(2, 0, 3));
        assertEquals(Arrays.asList(3, 0, 4), result);
    }
}
