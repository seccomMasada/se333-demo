error id: file:///C:/Users/ryanp/Desktop/se333-demo/src/test/java/org/example/NumberUtilsTest.java:_empty_/NumberUtils#
file:///C:/Users/ryanp/Desktop/se333-demo/src/test/java/org/example/NumberUtilsTest.java
empty definition using pc, found symbol in pc: _empty_/NumberUtils#
semanticdb not found
empty definition using fallback
non-local guesses:

offset: 663
uri: file:///C:/Users/ryanp/Desktop/se333-demo/src/test/java/org/example/NumberUtilsTest.java
text:
```scala
package org.example;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class NumberUtilsTest {

    // Constructor test
    @Test
    public void testConstructor() {
        // Test that the constructor can be instantiated
        NumberUtils utils = new NumberUtils();
        assertNotNull(utils);
    }

    // Normal cases
    @Test
    public void testAddSimpleNumbers() {
        List<Integer> left = Arrays.asList(2, 3);
        List<Integer> right = Arrays.asList(4, 2);
        List<Integer> result = NumberUti@@ls.add(left, right);
        assertEquals(Arrays.asList(6, 5), result);
    }

    @Test
    public void testAddWithCarry() {
        List<Integer> left = Arrays.asList(9, 9);
        List<Integer> right = Arrays.asList(1);
        List<Integer> result = NumberUtils.add(left, right);
        assertEquals(Arrays.asList(1, 0, 0), result);
    }

    @Test
    public void testAddZeroPlusNumber() {
        List<Integer> left = new LinkedList<>();
        List<Integer> right = Arrays.asList(5);
        List<Integer> result = NumberUtils.add(left, right);
        assertEquals(Arrays.asList(5), result);
    }

    @Test
    public void testAddNumberPlusZero() {
        List<Integer> left = Arrays.asList(7);
        List<Integer> right = new LinkedList<>();
        List<Integer> result = NumberUtils.add(left, right);
        assertEquals(Arrays.asList(7), result);
    }

    @Test
    public void testAddZeroPlusZero() {
        List<Integer> left = new LinkedList<>();
        List<Integer> right = new LinkedList<>();
        List<Integer> result = NumberUtils.add(left, right);
        assertEquals(Arrays.asList(), result);
    }

    @Test
    public void testAddDifferentLengths() {
        List<Integer> left = Arrays.asList(1, 2, 3);
        List<Integer> right = Arrays.asList(9);
        List<Integer> result = NumberUtils.add(left, right);
        assertEquals(Arrays.asList(1, 3, 2), result);
    }

    @Test
    public void testAddWithLeadingZeros() {
        List<Integer> left = Arrays.asList(0, 1, 2);
        List<Integer> right = Arrays.asList(0, 0, 3);
        List<Integer> result = NumberUtils.add(left, right);
        assertEquals(Arrays.asList(1, 5), result);
    }

    @Test
    public void testAddLargeNumbers() {
        List<Integer> left = Arrays.asList(9, 9, 9, 9);
        List<Integer> right = Arrays.asList(1);
        List<Integer> result = NumberUtils.add(left, right);
        assertEquals(Arrays.asList(1, 0, 0, 0, 0), result);
    }

    @Test
    public void testAddSingleDigits() {
        List<Integer> left = Arrays.asList(5);
        List<Integer> right = Arrays.asList(3);
        List<Integer> result = NumberUtils.add(left, right);
        assertEquals(Arrays.asList(8), result);
    }

    // Edge cases and null handling
    @Test
    public void testAddNullLeft() {
        List<Integer> result = NumberUtils.add(null, Arrays.asList(5));
        assertNull(result);
    }

    @Test
    public void testAddNullRight() {
        List<Integer> result = NumberUtils.add(Arrays.asList(5), null);
        assertNull(result);
    }

    @Test
    public void testAddBothNull() {
        List<Integer> result = NumberUtils.add(null, null);
        assertNull(result);
    }

    // Error cases - invalid digit values
    @Test
    public void testAddNegativeLeftDigit() {
        List<Integer> left = Arrays.asList(-1, 5);
        List<Integer> right = Arrays.asList(1);
        assertThrows(IllegalArgumentException.class, () -> NumberUtils.add(left, right));
    }

    @Test
    public void testAddDigitGreaterThan9() {
        List<Integer> left = Arrays.asList(1, 10);
        List<Integer> right = Arrays.asList(1);
        assertThrows(IllegalArgumentException.class, () -> NumberUtils.add(left, right));
    }

    @Test
    public void testAddNegativeRightDigit() {
        List<Integer> left = Arrays.asList(1);
        List<Integer> right = Arrays.asList(-1);
        assertThrows(IllegalArgumentException.class, () -> NumberUtils.add(left, right));
    }

    @Test
    public void testAddRightDigitGreaterThan9() {
        List<Integer> left = Arrays.asList(1);
        List<Integer> right = Arrays.asList(1, 15);
        assertThrows(IllegalArgumentException.class, () -> NumberUtils.add(left, right));
    }
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: _empty_/NumberUtils#