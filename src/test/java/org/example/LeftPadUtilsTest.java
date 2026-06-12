package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LeftPadUtilsTest {

    // Test null input string
    @Test
    void testLeftPadNullString() {
        assertNull(LeftPadUtils.leftPad(null, 5, "x"));
    }

    // Test null pad string (should default to space)
    @Test
    void testLeftPadNullPadString() {
        assertEquals("  hi", LeftPadUtils.leftPad("hi", 4, null));
    }

    // Test empty pad string (should default to space)
    @Test
    void testLeftPadEmptyPadString() {
        assertEquals("  hi", LeftPadUtils.leftPad("hi", 4, ""));
    }

    // Test no padding needed (size <= str length)
    @Test
    void testLeftPadSizeEqualLength() {
        assertEquals("hello", LeftPadUtils.leftPad("hello", 5, "x"));
    }

    @Test
    void testLeftPadSizeLessThanLength() {
        assertEquals("hello", LeftPadUtils.leftPad("hello", 3, "x"));
    }

    // Test exact padding (pads == padStr length)
    @Test
    void testLeftPadExactFit() {
        assertEquals("xhello", LeftPadUtils.leftPad("hello", 6, "x"));
    }

    @Test
    void testLeftPadExactFitMultiChar() {
        assertEquals("abhello", LeftPadUtils.leftPad("hello", 7, "ab"));
    }

    // Test padding with truncation (pads < padStr length)
    @Test
    void testLeftPadTruncate() {
        assertEquals("xhello", LeftPadUtils.leftPad("hello", 6, "xyz"));
    }

    @Test
    void testLeftPadTruncateMultiChar() {
        assertEquals("abhello", LeftPadUtils.leftPad("hello", 7, "abcdef"));
    }

    // Test padding with cycling (pads > padStr length)
    @Test
    void testLeftPadCycle() {
        assertEquals("xxxxhello", LeftPadUtils.leftPad("hello", 9, "x"));
    }

    @Test
    void testLeftPadCycleMultiChar() {
        assertEquals("abababhello", LeftPadUtils.leftPad("hello", 11, "ab"));
    }

    @Test
    void testLeftPadCycleThreeCharPad() {
        assertEquals("abcabcabchello", LeftPadUtils.leftPad("hello", 14, "abc"));
    }

    // Test with single space as default
    @Test
    void testLeftPadDefaultSpace() {
        assertEquals("  hi", LeftPadUtils.leftPad("hi", 4, null));
    }

    // Test with space as explicit pad string
    @Test
    void testLeftPadExplicitSpace() {
        assertEquals("  hi", LeftPadUtils.leftPad("hi", 4, " "));
    }

    // Test empty string input
    @Test
    void testLeftPadEmptyStringNopad() {
        assertEquals("", LeftPadUtils.leftPad("", 0, "x"));
    }

    @Test
    void testLeftPadEmptyStringWithPad() {
        assertEquals("xxx", LeftPadUtils.leftPad("", 3, "x"));
    }

    // Test zero size
    @Test
    void testLeftPadZeroSize() {
        assertEquals("hello", LeftPadUtils.leftPad("hello", 0, "x"));
    }

    // Test negative size
    @Test
    void testLeftPadNegativeSize() {
        assertEquals("hello", LeftPadUtils.leftPad("hello", -1, "x"));
    }

    // Test single character string
    @Test
    void testLeftPadSingleChar() {
        assertEquals("xxxxa", LeftPadUtils.leftPad("a", 5, "x"));
    }

    // Test single character pad string
    @Test
    void testLeftPadSingleCharPad() {
        assertEquals("xxxhello", LeftPadUtils.leftPad("hello", 8, "x"));
    }

    // Test multi-character string
    @Test
    void testLeftPadMultiChar() {
        assertEquals("xxxhello", LeftPadUtils.leftPad("hello", 8, "x"));
    }

    // Test with digits as pad
    @Test
    void testLeftPadDigitPad() {
        assertEquals("000123", LeftPadUtils.leftPad("123", 6, "0"));
    }

    // Test large padding
    @Test
    void testLeftPadLargePadding() {
        String result = LeftPadUtils.leftPad("test", 100, "x");
        assertEquals(100, result.length());
        assertTrue(result.endsWith("test"));
    }

    // Test cycle with specific pattern verification
    @Test
    void testLeftPadCyclePatternAb() {
        String result = LeftPadUtils.leftPad("x", 6, "ab");
        assertEquals("ababax", result);
    }

    // Test special characters as pad
    @Test
    void testLeftPadSpecialCharPad() {
        assertEquals("...hello", LeftPadUtils.leftPad("hello", 8, "."));
    }

    // Test multi-char special pad with cycling
    @Test
    void testLeftPadSpecialMultiCharCycle() {
        assertEquals("-*-*-*hello", LeftPadUtils.leftPad("hello", 11, "-*"));
    }

    // Test original string returned when size == length
    @Test
    void testLeftPadOriginalReturned() {
        String original = "test";
        String result = LeftPadUtils.leftPad(original, 4, "x");
        assertEquals(original, result);
    }

    // Test size one more than string length
    @Test
    void testLeftPadOneMoreSize() {
        assertEquals("xtest", LeftPadUtils.leftPad("test", 5, "x"));
    }
}
