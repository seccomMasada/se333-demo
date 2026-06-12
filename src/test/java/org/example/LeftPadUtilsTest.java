package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("LeftPadUtils Padding Tests")
class LeftPadUtilsTest {

    // ============ Null Input Tests ============

    @Test
    @DisplayName("Null string should return null")
    void testNullStringReturnsNull() {
        assertNull(LeftPadUtils.leftPad(null, 5, "x"));
    }

    @Test
    @DisplayName("Null padStr should use single space")
    void testNullPadStrUsesSpace() {
        String result = LeftPadUtils.leftPad("hi", 5, null);
        assertEquals("   hi", result);
    }

    @Test
    @DisplayName("Empty padStr should use single space")
    void testEmptyPadStrUsesSpace() {
        String result = LeftPadUtils.leftPad("hi", 5, "");
        assertEquals("   hi", result);
    }

    // ============ No Padding Needed Tests ============

    @Test
    @DisplayName("String length equals size should return original string")
    void testSizeEqualsStringLength() {
        String result = LeftPadUtils.leftPad("hello", 5, "x");
        assertEquals("hello", result);
    }

    @Test
    @DisplayName("String length greater than size should return original string")
    void testStringLongerThanSize() {
        String result = LeftPadUtils.leftPad("hello", 3, "x");
        assertEquals("hello", result);
    }

    @Test
    @DisplayName("Negative size should return original string")
    void testNegativeSize() {
        String result = LeftPadUtils.leftPad("hi", -5, "x");
        assertEquals("hi", result);
    }

    @Test
    @DisplayName("Zero size should return original string")
    void testZeroSize() {
        String result = LeftPadUtils.leftPad("hi", 0, "x");
        assertEquals("hi", result);
    }

    // ============ Empty String Tests ============

    @Test
    @DisplayName("Empty string with positive size should pad with spaces")
    void testEmptyStringPaddedWithSpaces() {
        String result = LeftPadUtils.leftPad("", 5, null);
        assertEquals("     ", result);
    }

    @Test
    @DisplayName("Empty string with positive size and padStr should pad with padStr")
    void testEmptyStringPaddedWithPadStr() {
        String result = LeftPadUtils.leftPad("", 5, "x");
        assertEquals("xxxxx", result);
    }

    @Test
    @DisplayName("Empty string with zero size should return empty string")
    void testEmptyStringWithZeroSize() {
        String result = LeftPadUtils.leftPad("", 0, "x");
        assertEquals("", result);
    }

    // ============ Single Character Padding Tests ============

    @Test
    @DisplayName("Single character padStr: pad exactly")
    void testSingleCharPadStrExact() {
        String result = LeftPadUtils.leftPad("hi", 4, "x");
        assertEquals("xxhi", result);
    }

    @Test
    @DisplayName("Single character padStr: pad multiple times")
    void testSingleCharPadStrMultiple() {
        String result = LeftPadUtils.leftPad("hi", 7, "a");
        assertEquals("aaaaahi", result);
    }

    // ============ Multi-Character Padding Tests ============

    @Test
    @DisplayName("Multi-char padStr: exact multiple")
    void testMultiCharPadStrExact() {
        String result = LeftPadUtils.leftPad("hi", 6, "xy");
        assertEquals("xyxyhi", result);
    }

    @Test
    @DisplayName("Multi-char padStr: exact single instance")
    void testMultiCharPadStrSingleInstance() {
        String result = LeftPadUtils.leftPad("hi", 4, "xy");
        assertEquals("xyhi", result);
    }

    @Test
    @DisplayName("Multi-char padStr: partial last instance")
    void testMultiCharPadStrPartialLast() {
        String result = LeftPadUtils.leftPad("hi", 5, "xy");
        assertEquals("xyxhi", result);
    }

    @Test
    @DisplayName("Three-char padStr: repeat pattern")
    void testThreeCharPadStrRepeat() {
        String result = LeftPadUtils.leftPad("hi", 8, "abc");
        assertEquals("abcabchi", result);
    }

    @Test
    @DisplayName("Three-char padStr: partial")
    void testThreeCharPadStrPartial() {
        String result = LeftPadUtils.leftPad("hi", 5, "abc");
        assertEquals("abchi", result);
    }

    // ============ Space Padding Tests ============

    @Test
    @DisplayName("Default space padding")
    void testDefaultSpacePadding() {
        String result = LeftPadUtils.leftPad("test", 8, " ");
        assertEquals("    test", result);
    }

    // ============ Special Character Padding Tests ============

    @Test
    @DisplayName("Numeric character padding")
    void testNumericCharPadding() {
        String result = LeftPadUtils.leftPad("abc", 6, "0");
        assertEquals("000abc", result);
    }

    @Test
    @DisplayName("Special character padding")
    void testSpecialCharPadding() {
        String result = LeftPadUtils.leftPad("hi", 5, "-");
        assertEquals("---hi", result);
    }

    @Test
    @DisplayName("Multi-char with special characters")
    void testMultiCharSpecialPadding() {
        String result = LeftPadUtils.leftPad("x", 5, "->>");
        assertEquals("->>-x", result);
    }

    // ============ Single Character String Tests ============

    @Test
    @DisplayName("Single character string padding")
    void testSingleCharString() {
        String result = LeftPadUtils.leftPad("a", 5, "b");
        assertEquals("bbbba", result);
    }

    // ============ Unicode and Whitespace Tests ============

    @Test
    @DisplayName("Tab character as padStr")
    void testTabCharPadding() {
        String result = LeftPadUtils.leftPad("x", 3, "\t");
        assertEquals("\t\tx", result);
    }

    @Test
    @DisplayName("Multiple spaces as padStr")
    void testMultipleSpacesAsPadStr() {
        String result = LeftPadUtils.leftPad("hi", 6, "  ");
        assertEquals("    hi", result);
    }

    // ============ Edge Cases ============

    @Test
    @DisplayName("String with spaces")
    void testStringWithSpaces() {
        String result = LeftPadUtils.leftPad("hello world", 15, "-");
        assertEquals("----hello world", result);
    }

    @Test
    @DisplayName("Exact padding requirement")
    void testExactPaddingRequirement() {
        String result = LeftPadUtils.leftPad("cat", 6, "dog");
        assertEquals("dogcat", result);
    }

    @Test
    @DisplayName("Large padding requirement")
    void testLargePaddingRequirement() {
        String result = LeftPadUtils.leftPad("x", 100, "a");
        String expected = "a".repeat(99) + "x";
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Already padded string with additional padding")
    void testAlreadyPaddedString() {
        String result = LeftPadUtils.leftPad("  hi", 7, "x");
        assertEquals("xxx  hi", result);
    }
}
