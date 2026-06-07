package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("NumberUtils.add() Test Suite")
class NumberUtilsTest {

    // ===== NULL AND EMPTY CASES =====
    
    @Test
    @DisplayName("Should return null when left is null")
    void testLeftNull() {
        List<Integer> result = NumberUtils.add(null, Arrays.asList(4, 2));
        assertNull(result);
    }

    @Test
    @DisplayName("Should return null when right is null")
    void testRightNull() {
        List<Integer> result = NumberUtils.add(Arrays.asList(2, 3), null);
        assertNull(result);
    }

    @Test
    @DisplayName("Should return null when both left and right are null")
    void testBothNull() {
        List<Integer> result = NumberUtils.add(null, null);
        assertNull(result);
    }

    @Test
    @DisplayName("Should return right when left is empty (empty means 0)")
    void testLeftEmpty() {
        List<Integer> result = NumberUtils.add(Arrays.asList(), Arrays.asList(5, 3));
        assertEquals(Arrays.asList(5, 3), result);
    }

    @Test
    @DisplayName("Should return left when right is empty (empty means 0)")
    void testRightEmpty() {
        List<Integer> result = NumberUtils.add(Arrays.asList(5, 3), Arrays.asList());
        assertEquals(Arrays.asList(5, 3), result);
    }

    @Test
    @DisplayName("Should return [0] when both left and right are empty")
    void testBothEmpty() {
        List<Integer> result = NumberUtils.add(Arrays.asList(), Arrays.asList());
        assertEquals(Arrays.asList(0), result);
    }

    // ===== SIMPLE ADDITION WITHOUT CARRY =====
    
    @Test
    @DisplayName("Should add two single-digit numbers correctly")
    void testSimpleSingleDigitAddition() {
        List<Integer> result = NumberUtils.add(Arrays.asList(2), Arrays.asList(3));
        assertEquals(Arrays.asList(5), result);
    }

    @Test
    @DisplayName("Should add the example from spec: [2,3] + [4,2] = [6,5]")
    void testSpecExample() {
        List<Integer> result = NumberUtils.add(Arrays.asList(2, 3), Arrays.asList(4, 2));
        assertEquals(Arrays.asList(6, 5), result);
    }

    @Test
    @DisplayName("Should add larger numbers without carry")
    void testLargerNumbersNoCarry() {
        List<Integer> result = NumberUtils.add(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6));
        assertEquals(Arrays.asList(5, 7, 9), result);
    }

    // ===== CARRY HANDLING =====
    
    @Test
    @DisplayName("Should handle carry when 9 + 1 = 10")
    void testSimpleCarry() {
        List<Integer> result = NumberUtils.add(Arrays.asList(9), Arrays.asList(1));
        assertEquals(Arrays.asList(1, 0), result);
    }

    @Test
    @DisplayName("Should handle multiple carries: 9 + 9 = 18")
    void testDoubleCarry() {
        List<Integer> result = NumberUtils.add(Arrays.asList(9), Arrays.asList(9));
        assertEquals(Arrays.asList(1, 8), result);
    }

    @Test
    @DisplayName("Should handle carry chain: [9,9] + [1] = [1,0,0]")
    void testCarryChain() {
        List<Integer> result = NumberUtils.add(Arrays.asList(9, 9), Arrays.asList(1));
        assertEquals(Arrays.asList(1, 0, 0), result);
    }

    @Test
    @DisplayName("Should handle carry chain: [9,9,9] + [1] = [1,0,0,0]")
    void testCarryChainLonger() {
        List<Integer> result = NumberUtils.add(Arrays.asList(9, 9, 9), Arrays.asList(1));
        assertEquals(Arrays.asList(1, 0, 0, 0), result);
    }

    @Test
    @DisplayName("Should handle partial carry propagation")
    void testPartialCarry() {
        List<Integer> result = NumberUtils.add(Arrays.asList(9, 9, 5), Arrays.asList(0, 0, 5));
        assertEquals(Arrays.asList(1, 0, 0, 0), result);
    }

    // ===== DIFFERENT LENGTH LISTS =====
    
    @Test
    @DisplayName("Should handle left list longer than right")
    void testLeftLonger() {
        List<Integer> result = NumberUtils.add(Arrays.asList(1, 2, 3), Arrays.asList(4, 5));
        assertEquals(Arrays.asList(1, 6, 8), result);
    }

    @Test
    @DisplayName("Should handle right list longer than left")
    void testRightLonger() {
        List<Integer> result = NumberUtils.add(Arrays.asList(4, 5), Arrays.asList(1, 2, 3));
        assertEquals(Arrays.asList(1, 6, 8), result);
    }

    @Test
    @DisplayName("Should handle significantly different lengths")
    void testVeryDifferentLengths() {
        List<Integer> result = NumberUtils.add(Arrays.asList(9), Arrays.asList(1, 2, 3, 4, 5));
        assertEquals(Arrays.asList(1, 2, 3, 5, 4), result);
    }

    // ===== LEADING ZEROS IN RESULT =====
    
    @Test
    @DisplayName("Should remove leading zeros from result")
    void testRemoveLeadingZeros() {
        List<Integer> result = NumberUtils.add(Arrays.asList(1, 0), Arrays.asList(0));
        assertEquals(Arrays.asList(1, 0), result);
    }

    @Test
    @DisplayName("Should return [0] when sum is exactly zero")
    void testResultIsZero() {
        List<Integer> result = NumberUtils.add(Arrays.asList(0), Arrays.asList(0));
        assertEquals(Arrays.asList(0), result);
    }

    @Test
    @DisplayName("Should return [0] when sum is zero with multiple zeros")
    void testResultIsZeroMultiple() {
        List<Integer> result = NumberUtils.add(Arrays.asList(0, 0, 0), Arrays.asList());
        assertEquals(Arrays.asList(0), result);
    }

    // ===== INVALID DIGIT VALUES =====
    
    @Test
    @DisplayName("Should throw IllegalArgumentException when left contains negative digit")
    void testLeftNegativeDigit() {
        assertThrows(IllegalArgumentException.class, 
            () -> NumberUtils.add(Arrays.asList(-1, 5), Arrays.asList(3, 2)));
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when left contains digit > 9")
    void testLeftDigitTooLarge() {
        assertThrows(IllegalArgumentException.class, 
            () -> NumberUtils.add(Arrays.asList(10, 5), Arrays.asList(3, 2)));
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when right contains negative digit")
    void testRightNegativeDigit() {
        assertThrows(IllegalArgumentException.class, 
            () -> NumberUtils.add(Arrays.asList(3, 2), Arrays.asList(-1, 5)));
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when right contains digit > 9")
    void testRightDigitTooLarge() {
        assertThrows(IllegalArgumentException.class, 
            () -> NumberUtils.add(Arrays.asList(3, 2), Arrays.asList(10, 5)));
    }

    @Test
    @DisplayName("Should throw on invalid digit at first position")
    void testInvalidDigitFirstPosition() {
        assertThrows(IllegalArgumentException.class, 
            () -> NumberUtils.add(Arrays.asList(15), Arrays.asList(1)));
    }

    @Test
    @DisplayName("Should throw on invalid digit in middle of list")
    void testInvalidDigitMiddle() {
        assertThrows(IllegalArgumentException.class, 
            () -> NumberUtils.add(Arrays.asList(1, 10, 2), Arrays.asList(3)));
    }

    // ===== EDGE CASES WITH ZEROS =====
    
    @Test
    @DisplayName("Should handle [0] + [0] correctly")
    void testZeroAndZero() {
        List<Integer> result = NumberUtils.add(Arrays.asList(0), Arrays.asList(0));
        assertEquals(Arrays.asList(0), result);
    }

    @Test
    @DisplayName("Should handle [0,0,5] + [0,0,4], removing leading zeros from result")
    void testLeadingZerosInInput() {
        List<Integer> result = NumberUtils.add(Arrays.asList(0, 0, 5), Arrays.asList(0, 0, 4));
        // 005 + 004 = 9, leading zeros are removed from result
        assertEquals(Arrays.asList(9), result);
    }

    @Test
    @DisplayName("Should handle numbers with internal zeros")
    void testInternalZeros() {
        List<Integer> result = NumberUtils.add(Arrays.asList(1, 0, 5), Arrays.asList(2, 0, 3));
        assertEquals(Arrays.asList(3, 0, 8), result);
    }

    // ===== COMPLEX SCENARIOS =====
    
    @Test
    @DisplayName("Should handle complex addition with multiple carries")
    void testComplexWithCarries() {
        // 999 + 111 = 1110
        List<Integer> result = NumberUtils.add(Arrays.asList(9, 9, 9), Arrays.asList(1, 1, 1));
        assertEquals(Arrays.asList(1, 1, 1, 0), result);
    }

    @Test
    @DisplayName("Should handle unequal length lists with carries")
    void testUnequalWithCarries() {
        // 99 + 9999 = 10098
        List<Integer> result = NumberUtils.add(Arrays.asList(9, 9), Arrays.asList(9, 9, 9, 9));
        assertEquals(Arrays.asList(1, 0, 0, 9, 8), result);
    }

    @Test
    @DisplayName("Should handle commutative property")
    void testCommutativity() {
        List<Integer> left = Arrays.asList(2, 3, 4);
        List<Integer> right = Arrays.asList(5, 6, 7);
        
        // Note: We need to use new lists since the method modifies them
        List<Integer> result1 = NumberUtils.add(
            Arrays.asList(2, 3, 4), 
            Arrays.asList(5, 6, 7)
        );
        List<Integer> result2 = NumberUtils.add(
            Arrays.asList(5, 6, 7), 
            Arrays.asList(2, 3, 4)
        );
        
        assertEquals(result1, result2);
    }
}
