package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LeftPadUtilsTest {

    // Null string tests
    @Test
    void testLeftPadNullString() {
        assertNull(LeftPadUtils.leftPad(null, 5, "x"));
    }

    @Test
    void testLeftPadNullStringWithNullPad() {
        assertNull(LeftPadUtils.leftPad(null, 5, null));
    }

    @Test
    void testLeftPadNullStringWithEmptyPad() {
        assertNull(LeftPadUtils.leftPad(null, 5, ""));
    }

    // No padding needed tests
    @Test
    void testLeftPadStringLongerThanSize() {
        String str = "hello";
        String result = LeftPadUtils.leftPad(str, 3, "x");
        assertEquals("hello", result);
    }

    @Test
    void testLeftPadStringEqualToSize() {
        String str = "hello";
        String result = LeftPadUtils.leftPad(str, 5, "x");
        assertEquals("hello", result);
    }

    @Test
    void testLeftPadZeroSize() {
        String str = "hello";
        String result = LeftPadUtils.leftPad(str, 0, "x");
        assertEquals("hello", result);
    }

    @Test
    void testLeftPadNegativeSize() {
        String str = "hello";
        String result = LeftPadUtils.leftPad(str, -5, "x");
        assertEquals("hello", result);
    }

    // Empty string tests
    @Test
    void testLeftPadEmptyString() {
        String str = "";
        String result = LeftPadUtils.leftPad(str, 3, "x");
        assertEquals("xxx", result);
    }

    @Test
    void testLeftPadEmptyStringWithSpace() {
        String str = "";
        String result = LeftPadUtils.leftPad(str, 3, " ");
        assertEquals("   ", result);
    }

    // Null pad string (should use space)
    @Test
    void testLeftPadNullPadString() {
        String str = "hi";
        String result = LeftPadUtils.leftPad(str, 5, null);
        assertEquals("   hi", result);
    }

    // Empty pad string (should use space)
    @Test
    void testLeftPadEmptyPadString() {
        String str = "hi";
        String result = LeftPadUtils.leftPad(str, 5, "");
        assertEquals("   hi", result);
    }

    // Single character pad string
    @Test
    void testLeftPadSingleCharPad() {
        String str = "hi";
        String result = LeftPadUtils.leftPad(str, 5, "x");
        assertEquals("xxxhi", result);
    }

    // Pad length equals required padding length
    @Test
    void testLeftPadLengthEqualsPadding() {
        String str = "hi";
        String result = LeftPadUtils.leftPad(str, 5, "abc");
        assertEquals("abchi", result);  // pads=3, padLen=3, so exactly "abc"
    }

    // Pad length less than required padding length
    @Test
    void testLeftPadLengthLessThanPadding() {
        String str = "hello";
        String result = LeftPadUtils.leftPad(str, 8, "xy");
        assertEquals("xyxhello", result);
    }

    // Pad length greater than required padding length
    @Test
    void testLeftPadLengthGreaterThanPadding() {
        String str = "world";
        String result = LeftPadUtils.leftPad(str, 7, "abcde");
        assertEquals("abworld", result);
    }

    // Multi-character pad string with exact match
    @Test
    void testLeftPadMultiCharPadExact() {
        String str = "test";
        String result = LeftPadUtils.leftPad(str, 7, "xy");
        assertEquals("xyxtest", result);  // pads=3, padStr="xy", so xy[0%2]xy[1%2]xy[2%2] = xyx
    }

    // Multi-character pad string with partial use
    @Test
    void testLeftPadMultiCharPadPartial() {
        String str = "hi";
        String result = LeftPadUtils.leftPad(str, 6, "abcd");
        assertEquals("abcdhi", result);  // pads=4, padLen=4, so pads==padLen branch takes substring
    }

    // Multi-character pad string with repetition
    @Test
    void testLeftPadMultiCharPadRepeat() {
        String str = "a";
        String result = LeftPadUtils.leftPad(str, 8, "ab");
        assertEquals("abababaa", result);  // pads=7, padStr="ab", pattern repeats: ab[0%2]ab[1%2]ab[0%2]ab[1%2]ab[0%2]ab[1%2]ab[0%2]
    }

    // Size = string length + 1
    @Test
    void testLeftPadSizeByOne() {
        String str = "x";
        String result = LeftPadUtils.leftPad(str, 2, "y");
        assertEquals("yx", result);
    }

    // Single character string
    @Test
    void testLeftPadSingleCharString() {
        String str = "a";
        String result = LeftPadUtils.leftPad(str, 5, "b");
        assertEquals("bbbba", result);
    }

    // Long pad string with small required padding
    @Test
    void testLeftPadLongPadSmallPadding() {
        String str = "short";
        String result = LeftPadUtils.leftPad(str, 6, "verylongpadstring");
        assertEquals("vshort", result);
    }

    // Pad with numbers as string
    @Test
    void testLeftPadWithNumberPad() {
        String str = "value";
        String result = LeftPadUtils.leftPad(str, 8, "0");
        assertEquals("000value", result);
    }

    // Pad with special characters
    @Test
    void testLeftPadWithSpecialCharPad() {
        String str = "data";
        String result = LeftPadUtils.leftPad(str, 7, "-");
        assertEquals("---data", result);
    }

    // Pad with space character explicitly
    @Test
    void testLeftPadWithSpacePad() {
        String str = "text";
        String result = LeftPadUtils.leftPad(str, 7, " ");
        assertEquals("   text", result);
    }

    // Large size requirement
    @Test
    void testLeftPadLargeSize() {
        String str = "a";
        String result = LeftPadUtils.leftPad(str, 100, "x");
        assertEquals(100, result.length());
        assertTrue(result.endsWith("a"));
        assertTrue(result.startsWith("x"));
    }

    // Unicode characters in string
    @Test
    void testLeftPadUnicodeString() {
        String str = "日本";
        String result = LeftPadUtils.leftPad(str, 4, "*");
        assertEquals("**日本", result);
    }

    // Unicode characters in pad
    @Test
    void testLeftPadUnicodePad() {
        String str = "test";
        String result = LeftPadUtils.leftPad(str, 6, "·");
        assertEquals("··test", result);
    }

    // Mixed pad string
    @Test
    void testLeftPadMixedCharsPad() {
        String str = "go";
        String result = LeftPadUtils.leftPad(str, 8, "a1b2");
        assertEquals("a1b2a1go", result);
    }

    // Exact padding calculation
    @Test
    void testLeftPadExactCalculation() {
        String str = "hi";
        String result = LeftPadUtils.leftPad(str, 5, "x");
        assertEquals(5, result.length());
        assertEquals("xxxhi", result);
    }

    // Return original string when pads <= 0
    @Test
    void testLeftPadReturnOriginalWhenPadsNegative() {
        String str = "hello";
        String result = LeftPadUtils.leftPad(str, 3, "x");
        assertSame(str, result); // Should be same object (returns original)
    }

    // Whitespace characters
    @Test
    void testLeftPadWithWhitespace() {
        String str = "word";
        String result = LeftPadUtils.leftPad(str, 8, "\t");
        assertEquals(8, result.length());
    }

    // Newline in pad string
    @Test
    void testLeftPadWithNewlinePad() {
        String str = "line";
        String result = LeftPadUtils.leftPad(str, 6, "\n");
        assertEquals(6, result.length());
        assertTrue(result.endsWith("line"));
    }

    // Very long string, small size (no padding)
    @Test
    void testLeftPadVeryLongStringSmallSize() {
        String str = "this is a very long string";
        String result = LeftPadUtils.leftPad(str, 5, "x");
        assertEquals(str, result);
    }

    // Size = string length exactly with multi-char pad
    @Test
    void testLeftPadExactSizeMultiCharPad() {
        String str = "test";
        String result = LeftPadUtils.leftPad(str, 4, "abc");
        assertEquals("test", result);
    }

    // Repeated pattern verification
    @Test
    void testLeftPadRepeatedPatternCorrect() {
        String str = "end";
        String result = LeftPadUtils.leftPad(str, 11, "ab");
        assertEquals("ababababend", result);
        assertEquals(11, result.length());  // pads=8, filled with modulo pattern: abababab
    }

    // Test the modulo operation for pad characters
    @Test
    void testLeftPadModuloRepetition() {
        String str = "X";
        String result = LeftPadUtils.leftPad(str, 6, "123");
        assertEquals("12312X", result);
    }

    // Edge case: size = 1, string = empty
    @Test
    void testLeftPadSizeOneEmptyString() {
        String result = LeftPadUtils.leftPad("", 1, "a");
        assertEquals("a", result);
    }

    // Edge case: size = 1, string = one character
    @Test
    void testLeftPadSizeOneOneCharString() {
        String result = LeftPadUtils.leftPad("x", 1, "a");
        assertEquals("x", result);
    }
}
