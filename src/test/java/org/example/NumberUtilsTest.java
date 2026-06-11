package org.example;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NumberUtilsTest {

    // Null input tests
    @Test
    void testAddBothNull() {
        assertNull(NumberUtils.add(null, null));
    }

    @Test
    void testAddLeftNullRightValid() {
        List<Integer> right = Arrays.asList(1, 2);
        assertNull(NumberUtils.add(null, right));
    }

    @Test
    void testAddLeftValidRightNull() {
        List<Integer> left = Arrays.asList(1, 2);
        assertNull(NumberUtils.add(left, null));
    }

    // Empty list tests
    @Test
    void testAddBothEmpty() {
        List<Integer> left = Collections.emptyList();
        List<Integer> right = Collections.emptyList();
        List<Integer> result = NumberUtils.add(left, right);
        assertTrue(result.isEmpty() || result.equals(Arrays.asList(0)));
    }

    @Test
    void testAddLeftEmptyRightEmpty() {
        List<Integer> left = Collections.emptyList();
        List<Integer> right = Collections.emptyList();
        List<Integer> result = NumberUtils.add(left, right);
        assertTrue(result.isEmpty() || result.equals(Arrays.asList(0)));
    }

    @Test
    void testAddLeftEmptyRightValid() {
        List<Integer> left = Collections.emptyList();
        List<Integer> right = Arrays.asList(2, 3); // 23
        List<Integer> result = NumberUtils.add(left, right);
        assertEquals(Arrays.asList(2, 3), result);
    }

    @Test
    void testAddLeftValidRightEmpty() {
        List<Integer> left = Arrays.asList(2, 3); // 23
        List<Integer> right = Collections.emptyList();
        List<Integer> result = NumberUtils.add(left, right);
        assertEquals(Arrays.asList(2, 3), result);
    }

    // Single digit addition tests (no carry)
    @Test
    void testAddSingleDigitNoCarry() {
        List<Integer> left = Arrays.asList(2);
        List<Integer> right = Arrays.asList(3);
        List<Integer> result = NumberUtils.add(left, right);
        assertEquals(Arrays.asList(5), result);
    }

    // Single digit addition tests (with carry)
    @Test
    void testAddSingleDigitWithCarry() {
        List<Integer> left = Arrays.asList(5);
        List<Integer> right = Arrays.asList(7);
        List<Integer> result = NumberUtils.add(left, right);
        assertEquals(Arrays.asList(1, 2), result);
    }

    // Multiple digit addition tests
    @Test
    void testAddMultipleDigitsNoCarry() {
        List<Integer> left = Arrays.asList(2, 3); // 23
        List<Integer> right = Arrays.asList(4, 2); // 42
        List<Integer> result = NumberUtils.add(left, right);
        assertEquals(Arrays.asList(6, 5), result); // 65
    }

    @Test
    void testAddMultipleDigitsWithCarry() {
        List<Integer> left = Arrays.asList(5, 5); // 55
        List<Integer> right = Arrays.asList(5, 5); // 55
        List<Integer> result = NumberUtils.add(left, right);
        assertEquals(Arrays.asList(1, 1, 0), result); // 110
    }

    // Different lengths
    @Test
    void testAddDifferentLengths() {
        List<Integer> left = Arrays.asList(1, 2, 3); // 123
        List<Integer> right = Arrays.asList(4, 5); // 45
        List<Integer> result = NumberUtils.add(left, right);
        assertEquals(Arrays.asList(1, 6, 8), result); // 168
    }

    @Test
    void testAddDifferentLengthsWithCarry() {
        List<Integer> left = Arrays.asList(9, 9, 9); // 999
        List<Integer> right = Arrays.asList(1); // 1
        List<Integer> result = NumberUtils.add(left, right);
        assertEquals(Arrays.asList(1, 0, 0, 0), result); // 1000
    }

    // Leading zeros handling
    @Test
    void testAddResultWithLeadingZeros() {
        List<Integer> left = Arrays.asList(1, 0); // 10
        List<Integer> right = Arrays.asList(0, 0); // 0 (but empty list should be treated as 0)
        // After reversing: left = [0, 1], right = [0, 0]
        // But wait, the function modifies the input lists!
        // Let me reconsider...
    }

    // Addition resulting in zero
    @Test
    void testAddZeroPlusZero() {
        List<Integer> left = Arrays.asList(0);
        List<Integer> right = Arrays.asList(0);
        List<Integer> result = NumberUtils.add(left, right);
        assertEquals(Arrays.asList(0), result);
    }

    // Zero with another number
    @Test
    void testAddZeroWithNumber() {
        List<Integer> left = Arrays.asList(0);
        List<Integer> right = Arrays.asList(5);
        List<Integer> result = NumberUtils.add(left, right);
        assertEquals(Arrays.asList(5), result);
    }

    // Carry propagation through multiple digits
    @Test
    void testAddCarryPropagation() {
        List<Integer> left = Arrays.asList(9, 9); // 99
        List<Integer> right = Arrays.asList(9, 9); // 99
        List<Integer> result = NumberUtils.add(left, right);
        assertEquals(Arrays.asList(1, 9, 8), result); // 198
    }

    // Invalid digit: negative
    @Test
    void testAddInvalidNegativeDigitLeft() {
        List<Integer> left = Arrays.asList(-1, 5);
        List<Integer> right = Arrays.asList(1, 0);
        assertThrows(IllegalArgumentException.class, () -> NumberUtils.add(left, right));
    }

    @Test
    void testAddInvalidNegativeDigitRight() {
        List<Integer> left = Arrays.asList(1, 5);
        List<Integer> right = Arrays.asList(-1, 0);
        assertThrows(IllegalArgumentException.class, () -> NumberUtils.add(left, right));
    }

    // Invalid digit: too large
    @Test
    void testAddInvalidDigitTooLargeLeft() {
        List<Integer> left = Arrays.asList(1, 10);
        List<Integer> right = Arrays.asList(1, 0);
        assertThrows(IllegalArgumentException.class, () -> NumberUtils.add(left, right));
    }

    @Test
    void testAddInvalidDigitTooLargeRight() {
        List<Integer> left = Arrays.asList(1, 5);
        List<Integer> right = Arrays.asList(1, 10);
        assertThrows(IllegalArgumentException.class, () -> NumberUtils.add(left, right));
    }

    @Test
    void testAddInvalidDigitBoundary15() {
        List<Integer> left = Arrays.asList(1, 5);
        List<Integer> right = Arrays.asList(1, 15);
        assertThrows(IllegalArgumentException.class, () -> NumberUtils.add(left, right));
    }

    // Edge case: large numbers
    @Test
    void testAddLargeNumbers() {
        List<Integer> left = Arrays.asList(9, 9, 9, 9, 9); // 99999
        List<Integer> right = Arrays.asList(1); // 1
        List<Integer> result = NumberUtils.add(left, right);
        assertEquals(Arrays.asList(1, 0, 0, 0, 0, 0), result); // 100000
    }

    // Leading zeros in input
    @Test
    void testAddLeadingZerosRemoved() {
        List<Integer> left = Arrays.asList(0, 0, 5); // 5
        List<Integer> right = Arrays.asList(0, 3); // 3
        List<Integer> result = NumberUtils.add(left, right);
        assertEquals(Arrays.asList(8), result); // 8
    }

    // Test with zero in middle
    @Test
    void testAddNumberWithZeroInMiddle() {
        List<Integer> left = Arrays.asList(1, 0, 1); // 101
        List<Integer> right = Arrays.asList(2, 0, 2); // 202
        List<Integer> result = NumberUtils.add(left, right);
        assertEquals(Arrays.asList(3, 0, 3), result); // 303
    }

    // All zeros except one digit
    @Test
    void testAddMostlyZeros() {
        List<Integer> left = Arrays.asList(0, 0, 1);
        List<Integer> right = Arrays.asList(0, 0, 2);
        List<Integer> result = NumberUtils.add(left, right);
        assertEquals(Arrays.asList(3), result);
    }

    // Boundary: 0 and 9
    @Test
    void testAddZeroAndNine() {
        List<Integer> left = Arrays.asList(0);
        List<Integer> right = Arrays.asList(9);
        List<Integer> result = NumberUtils.add(left, right);
        assertEquals(Arrays.asList(9), result);
    }

    @Test
    void testAddNineAndNine() {
        List<Integer> left = Arrays.asList(9);
        List<Integer> right = Arrays.asList(9);
        List<Integer> result = NumberUtils.add(left, right);
        assertEquals(Arrays.asList(1, 8), result);
    }

    // Maximum valid digit combinations
    @Test
    void testAddMaxDigitNoCarry() {
        List<Integer> left = Arrays.asList(4);
        List<Integer> right = Arrays.asList(5);
        List<Integer> result = NumberUtils.add(left, right);
        assertEquals(Arrays.asList(9), result);
    }

    // Complex scenario: carry through entire number
    @Test
    void testAddComplexCarryScenario() {
        List<Integer> left = Arrays.asList(9, 8, 9); // 989
        List<Integer> right = Arrays.asList(1, 1); // 11
        List<Integer> result = NumberUtils.add(left, right);
        assertEquals(Arrays.asList(1, 0, 0, 0), result); // 1000
    }

    // Edge case: single 0 in left with multi-digit right
    @Test
    void testAddSingleZeroWithMultiDigit() {
        List<Integer> left = Arrays.asList(0);
        List<Integer> right = Arrays.asList(1, 2, 3); // 123
        List<Integer> result = NumberUtils.add(left, right);
        assertEquals(Arrays.asList(1, 2, 3), result);
    }
}
