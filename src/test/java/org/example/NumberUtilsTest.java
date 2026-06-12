package org.example;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NumberUtilsTest {

    @Test
    void testConstructor() {
        new NumberUtils();
    }

    // Test null cases
    @Test
    void testAddLeftNull() {
        List<Integer> right = Arrays.asList(1, 2, 3);
        assertNull(NumberUtils.add(null, right));
    }

    @Test
    void testAddRightNull() {
        List<Integer> left = Arrays.asList(1, 2, 3);
        assertNull(NumberUtils.add(left, null));
    }

    @Test
    void testAddBothNull() {
        assertNull(NumberUtils.add(null, null));
    }

    // Test empty cases
    @Test
    void testAddLeftEmpty() {
        List<Integer> left = Collections.emptyList();
        List<Integer> right = Arrays.asList(5);
        assertEquals(Arrays.asList(5), NumberUtils.add(left, right));
    }

    @Test
    void testAddRightEmpty() {
        List<Integer> left = Arrays.asList(5);
        List<Integer> right = Collections.emptyList();
        assertEquals(Arrays.asList(5), NumberUtils.add(left, right));
    }

    @Test
    void testAddBothEmpty() {
        List<Integer> left = Collections.emptyList();
        List<Integer> right = Collections.emptyList();
        assertEquals(Arrays.asList(0), NumberUtils.add(left, right));
    }

    // Test basic single digit
    @Test
    void testAddSingleDigitNoCarry() {
        List<Integer> left = Arrays.asList(3);
        List<Integer> right = Arrays.asList(4);
        assertEquals(Arrays.asList(7), NumberUtils.add(left, right));
    }

    @Test
    void testAddSingleDigitWithCarry() {
        List<Integer> left = Arrays.asList(9);
        List<Integer> right = Arrays.asList(1);
        assertEquals(Arrays.asList(1, 0), NumberUtils.add(left, right));
    }

    // Test basic multi-digit
    @Test
    void testAddTwoDigitNoCarry() {
        List<Integer> left = Arrays.asList(2, 3);
        List<Integer> right = Arrays.asList(4, 2);
        assertEquals(Arrays.asList(6, 5), NumberUtils.add(left, right));
    }

    @Test
    void testAddTwoDigitWithCarry() {
        List<Integer> left = Arrays.asList(1, 9);
        List<Integer> right = Arrays.asList(1, 1);
        assertEquals(Arrays.asList(3, 0), NumberUtils.add(left, right));
    }

    @Test
    void testAddTwoDigitCarryPropagate() {
        List<Integer> left = Arrays.asList(9, 9);
        List<Integer> right = Arrays.asList(1);
        assertEquals(Arrays.asList(1, 0, 0), NumberUtils.add(left, right));
    }

    // Test different length inputs
    @Test
    void testAddLeftLonger() {
        List<Integer> left = Arrays.asList(1, 2, 3, 4);
        List<Integer> right = Arrays.asList(1);
        assertEquals(Arrays.asList(1, 2, 3, 5), NumberUtils.add(left, right));
    }

    @Test
    void testAddRightLonger() {
        List<Integer> left = Arrays.asList(1);
        List<Integer> right = Arrays.asList(1, 2, 3, 4);
        assertEquals(Arrays.asList(1, 2, 3, 5), NumberUtils.add(left, right));
    }

    // Test zero cases
    @Test
    void testAddZeroPlusZero() {
        List<Integer> left = Arrays.asList(0);
        List<Integer> right = Arrays.asList(0);
        assertEquals(Arrays.asList(0), NumberUtils.add(left, right));
    }

    @Test
    void testAddZeroPlusNumber() {
        List<Integer> left = Arrays.asList(0);
        List<Integer> right = Arrays.asList(5, 3);
        assertEquals(Arrays.asList(5, 3), NumberUtils.add(left, right));
    }

    // Test leading zeros removal
    @Test
    void testAddResultWithLeadingZeros() {
        List<Integer> left = Arrays.asList(0, 5);
        List<Integer> right = Arrays.asList(0, 5);
        assertEquals(Arrays.asList(1, 0), NumberUtils.add(left, right));
    }

    // Test large numbers
    @Test
    void testAddLargeNumbers() {
        List<Integer> left = Arrays.asList(9, 9, 9, 9, 9);
        List<Integer> right = Arrays.asList(1);
        assertEquals(Arrays.asList(1, 0, 0, 0, 0, 0), NumberUtils.add(left, right));
    }

    // Test invalid digits - negative
    @Test
    void testAddNegativeDigitLeft() {
        List<Integer> left = Arrays.asList(-1, 5);
        List<Integer> right = Arrays.asList(2, 3);
        assertThrows(IllegalArgumentException.class, () -> NumberUtils.add(left, right));
    }

    @Test
    void testAddNegativeDigitRight() {
        List<Integer> left = Arrays.asList(1, 5);
        List<Integer> right = Arrays.asList(-2, 3);
        assertThrows(IllegalArgumentException.class, () -> NumberUtils.add(left, right));
    }

    // Test invalid digits - greater than 9
    @Test
    void testAddDigitGreaterThan9Left() {
        List<Integer> left = Arrays.asList(10, 5);
        List<Integer> right = Arrays.asList(2, 3);
        assertThrows(IllegalArgumentException.class, () -> NumberUtils.add(left, right));
    }

    @Test
    void testAddDigitGreaterThan9Right() {
        List<Integer> left = Arrays.asList(1, 5);
        List<Integer> right = Arrays.asList(2, 15);
        assertThrows(IllegalArgumentException.class, () -> NumberUtils.add(left, right));
    }

    // Test edge case: all zeros in result except final carry
    @Test
    void testAddAllZerosWithCarry() {
        List<Integer> left = Arrays.asList(0, 0, 0, 9);
        List<Integer> right = Arrays.asList(0, 0, 0, 1);
        assertEquals(Arrays.asList(1, 0), NumberUtils.add(left, right));
    }

    // Test multiple carries across multiple positions
    @Test
    void testAddMultipleCarries() {
        List<Integer> left = Arrays.asList(9, 9, 9);
        List<Integer> right = Arrays.asList(9, 9, 9);
        assertEquals(Arrays.asList(1, 9, 9, 8), NumberUtils.add(left, right));
    }

    // Test combination: empty left, right with all 9s
    @Test
    void testAddEmptyLeftAllNinesRight() {
        List<Integer> left = Collections.emptyList();
        List<Integer> right = Arrays.asList(9, 9, 9);
        assertEquals(Arrays.asList(9, 9, 9), NumberUtils.add(left, right));
    }

    // Test single zero digit against multi-digit
    @Test
    void testAddSingleZeroAgainstMultiDigit() {
        List<Integer> left = Arrays.asList(0);
        List<Integer> right = Arrays.asList(1, 2, 3);
        assertEquals(Arrays.asList(1, 2, 3), NumberUtils.add(left, right));
    }

    // Test mixed valid digits
    @Test
    void testAddMixedValidDigits() {
        List<Integer> left = Arrays.asList(1, 4, 7);
        List<Integer> right = Arrays.asList(2, 5, 8);
        assertEquals(Arrays.asList(4, 0, 5), NumberUtils.add(left, right));
    }
}
