package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LeftPadUtilsTest {

    @Test
    public void testNullString() {
        assertNull(LeftPadUtils.leftPad(null, 10, "x"));
    }

    @Test
    public void testNullStringAnySize() {
        assertNull(LeftPadUtils.leftPad(null, 0, null));
    }

    @Test
    public void testEmptyString() {
        // Padding empty string to size 3 with "x" = "xxx"
        String result = LeftPadUtils.leftPad("", 3, "x");
        assertEquals("xxx", result);
    }

    @Test
    public void testEmptyStringSize0() {
        // Padding empty string to size 0 = ""
        String result = LeftPadUtils.leftPad("", 0, "x");
        assertEquals("", result);
    }

    @Test
    public void testNullPadStr() {
        // null padStr should be treated as space
        String result = LeftPadUtils.leftPad("test", 8, null);
        assertEquals("    test", result);
    }

    @Test
    public void testEmptyPadStr() {
        // empty padStr should be treated as space
        String result = LeftPadUtils.leftPad("test", 8, "");
        assertEquals("    test", result);
    }

    @Test
    public void testSizeEqualToLength() {
        // No padding needed
        String result = LeftPadUtils.leftPad("hello", 5, "x");
        assertEquals("hello", result);
    }

    @Test
    public void testSizeLessThanLength() {
        // No padding needed, return original
        String result = LeftPadUtils.leftPad("hello", 3, "x");
        assertEquals("hello", result);
    }

    @Test
    public void testSizeZero() {
        // Size 0, string "test" (length 4) -> no padding
        String result = LeftPadUtils.leftPad("test", 0, "x");
        assertEquals("test", result);
    }

    @Test
    public void testNegativeSize() {
        // Negative size, string "test" (length 4) -> pads = negative -> no padding
        String result = LeftPadUtils.leftPad("test", -5, "x");
        assertEquals("test", result);
    }

    @Test
    public void testSingleCharPadStr() {
        // Pad "hi" to size 5 with "x" -> "xxxhi"
        String result = LeftPadUtils.leftPad("hi", 5, "x");
        assertEquals("xxxhi", result);
    }

    @Test
    public void testPadsEqualsPadLen() {
        // Pad "hi" to size 5 with "abc" -> pads=3, padLen=3 -> "abchi"
        String result = LeftPadUtils.leftPad("hi", 5, "abc");
        assertEquals("abchi", result);
    }

    @Test
    public void testPadsLessThanPadLen() {
        // Pad "hi" to size 4 with "abcd" -> pads=2, padLen=4 -> "abhi"
        String result = LeftPadUtils.leftPad("hi", 4, "abcd");
        assertEquals("abhi", result);
    }

    @Test
    public void testPadsGreaterThanPadLen() {
        // Pad "hi" to size 8 with "ab" -> pads=6, padLen=2 -> "abababhi"
        String result = LeftPadUtils.leftPad("hi", 8, "ab");
        assertEquals("abababhi", result);
    }

    @Test
    public void testLargePadding() {
        // Pad "x" to size 10 with "0" -> "000000000x"
        String result = LeftPadUtils.leftPad("x", 10, "0");
        assertEquals("000000000x", result);
    }

    @Test
    public void testMultiCharPadPattern() {
        // Pad "hi" to size 10 with "123" -> pads=8, padLen=3 -> "12312312hi" (8 chars: 1,2,3,1,2,3,1,2)
        String result = LeftPadUtils.leftPad("hi", 10, "123");
        assertEquals("12312312hi", result);
    }

    @Test
    public void testSpacePadStr() {
        // Pad "test" to size 6 with " " -> "  test"
        String result = LeftPadUtils.leftPad("test", 6, " ");
        assertEquals("  test", result);
    }

    @Test
    public void testSpecialCharPadStr() {
        // Pad "x" to size 4 with "*" -> "***x"
        String result = LeftPadUtils.leftPad("x", 4, "*");
        assertEquals("***x", result);
    }

    @Test
    public void testEmptyStringWithLargePadding() {
        // Pad "" to size 5 with "x" -> "xxxxx"
        String result = LeftPadUtils.leftPad("", 5, "x");
        assertEquals("xxxxx", result);
    }

    @Test
    public void testEmptyPadStrWithEmptyString() {
        // Pad "" to size 5 with "" (treated as space) -> "     "
        String result = LeftPadUtils.leftPad("", 5, "");
        assertEquals("     ", result);
    }

    @Test
    public void testLongString() {
        // Pad "verylongstring" (14 chars) to size 20 with "-" -> "------verylongstring"
        String result = LeftPadUtils.leftPad("verylongstring", 20, "-");
        assertEquals("------verylongstring", result);
    }

    @Test
    public void testPadWithNumber() {
        // Pad "123" to size 7 with "0" -> "0000123"
        String result = LeftPadUtils.leftPad("123", 7, "0");
        assertEquals("0000123", result);
    }

    @Test
    public void testPadPatternCycle() {
        // Pad "a" to size 8 with "xy" -> pads=7, padLen=2 -> "xyxyxyxa"
        String result = LeftPadUtils.leftPad("a", 8, "xy");
        assertEquals("xyxyxyxa", result);
    }

    @Test
    public void testPadSingleChar() {
        // Pad "a" to size 1 -> no padding needed
        String result = LeftPadUtils.leftPad("a", 1, "x");
        assertEquals("a", result);
    }

    @Test
    public void testDefaultSpacePadding() {
        // When padStr is null, should use space
        String result = LeftPadUtils.leftPad("test", 10, null);
        assertEquals("      test", result);
    }

    @Test
    public void testPadsExactlyPadLen() {
        // Pad "abc" to size 6 with "xyz" -> pads=3, padLen=3 -> "xyzabc"
        String result = LeftPadUtils.leftPad("abc", 6, "xyz");
        assertEquals("xyzabc", result);
    }

    @Test
    public void testComplexPatternRepeating() {
        // Pad "END" to size 15 with "ABCD" -> pads=12, padLen=4
        // "ABCDABCDABCDEND"
        String result = LeftPadUtils.leftPad("END", 15, "ABCD");
        assertEquals("ABCDABCDABCDEND", result);
    }

    @Test
    public void testZeroSize() {
        // Size 0 with any string -> no padding
        String result = LeftPadUtils.leftPad("hello", 0, "x");
        assertEquals("hello", result);
    }

    @Test
    public void testSizeOne() {
        // Size 1, string "" -> pad to size 1 with "x" -> "x"
        String result = LeftPadUtils.leftPad("", 1, "x");
        assertEquals("x", result);
    }
}
