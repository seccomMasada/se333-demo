package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("LeftPadUtils.leftPad() Test Suite")
class LeftPadUtilsTest {

    // ===== NULL INPUT CASES =====
    
    @Test
    @DisplayName("Should return null when input string is null")
    void testNullInput() {
        String result = LeftPadUtils.leftPad(null, 5, "x");
        assertNull(result);
    }

    @Test
    @DisplayName("Should return null when string is null, regardless of size")
    void testNullInputVariousSize() {
        String result = LeftPadUtils.leftPad(null, 0, "x");
        assertNull(result);
    }

    @Test
    @DisplayName("Should return null when string is null with null padStr")
    void testNullInputNullPadStr() {
        String result = LeftPadUtils.leftPad(null, 5, null);
        assertNull(result);
    }

    // ===== NULL OR EMPTY PADSTR (TREAT AS SPACE) =====
    
    @Test
    @DisplayName("Should treat null padStr as single space")
    void testNullPadStr() {
        String result = LeftPadUtils.leftPad("hi", 5, null);
        assertEquals("   hi", result);
    }

    @Test
    @DisplayName("Should treat empty padStr as single space")
    void testEmptyPadStr() {
        String result = LeftPadUtils.leftPad("hi", 5, "");
        assertEquals("   hi", result);
    }

    @Test
    @DisplayName("Should pad with space when padStr is empty string")
    void testEmptyPadStrVariation() {
        String result = LeftPadUtils.leftPad("test", 6, "");
        assertEquals("  test", result);
    }

    // ===== NO PADDING NEEDED =====
    
    @Test
    @DisplayName("Should return original string when size equals string length")
    void testNoNeedPaddingExact() {
        String result = LeftPadUtils.leftPad("hello", 5, "x");
        assertEquals("hello", result);
    }

    @Test
    @DisplayName("Should return original string when size is less than string length")
    void testNoNeedPaddingSmaller() {
        String result = LeftPadUtils.leftPad("hello", 3, "x");
        assertEquals("hello", result);
    }

    @Test
    @DisplayName("Should return original string when size equals 0 and string is empty")
    void testEmptyStringNoNeed() {
        String result = LeftPadUtils.leftPad("", 0, "x");
        assertEquals("", result);
    }

    @Test
    @DisplayName("Should return original string when size is significantly smaller")
    void testPadSmallerThanNeeded() {
        String result = LeftPadUtils.leftPad("verylongstring", 2, "x");
        assertEquals("verylongstring", result);
    }

    // ===== EXACT PADDING (PADS == PADLEN) =====
    
    @Test
    @DisplayName("Should pad exactly with single char padStr")
    void testExactPaddingSingleChar() {
        String result = LeftPadUtils.leftPad("hi", 3, "x");
        assertEquals("xhi", result);
    }

    @Test
    @DisplayName("Should pad exactly with multi-char padStr")
    void testExactPaddingMultiChar() {
        String result = LeftPadUtils.leftPad("hi", 4, "xy");
        assertEquals("xyhi", result);
    }

    @Test
    @DisplayName("Should pad exactly with three-char padStr")
    void testExactPaddingThreeChar() {
        String result = LeftPadUtils.leftPad("x", 4, "abc");
        assertEquals("abcx", result);
    }

    // ===== PARTIAL PADDING (PADS < PADLEN) =====
    
    @Test
    @DisplayName("Should use substring of padStr when padding is less than padStr length")
    void testPartialPaddingSingleChar() {
        String result = LeftPadUtils.leftPad("hi", 3, "xyz");
        assertEquals("xhi", result);
    }

    @Test
    @DisplayName("Should use correct portion of padStr")
    void testPartialPaddingTwoChars() {
        String result = LeftPadUtils.leftPad("ab", 4, "wxyz");
        assertEquals("wxab", result);
    }

    @Test
    @DisplayName("Should take first character when padding by 1 from multi-char padStr")
    void testPartialPaddingOneNeeded() {
        String result = LeftPadUtils.leftPad("test", 5, "1234");
        assertEquals("1test", result);
    }

    // ===== MULTIPLE REPEAT PADDING (PADS > PADLEN) =====
    
    @Test
    @DisplayName("Should repeat single char padStr multiple times")
    void testMultipleRepeatSingleChar() {
        String result = LeftPadUtils.leftPad("hi", 6, "x");
        assertEquals("xxxxhi", result);
    }

    @Test
    @DisplayName("Should repeat multi-char padStr correctly")
    void testMultipleRepeatMultiChar() {
        String result = LeftPadUtils.leftPad("hi", 7, "ab");
        // Need 5 padding chars (7 - 2), with pattern "ab" repeating: a(1)b(2)a(3)b(4)a(5) + hi = "ababahi"
        assertEquals("ababahi", result);
    }

    @Test
    @DisplayName("Should repeat padStr using modulo cycling")
    void testMultipleRepeatWithModulo() {
        String result = LeftPadUtils.leftPad("x", 8, "abc");
        assertEquals("abcabcax", result);
    }

    @Test
    @DisplayName("Should handle partial last repeat of padStr")
    void testMultipleRepeatPartialLast() {
        String result = LeftPadUtils.leftPad("x", 6, "ab");
        assertEquals("ababax", result);
    }

    @Test
    @DisplayName("Should handle large padding with repeating pattern")
    void testLargePadding() {
        String result = LeftPadUtils.leftPad("hi", 14, "xy");
        assertEquals("xyxyxyxyxyxyhi", result);
    }

    // ===== EMPTY STRING PADDING =====
    
    @Test
    @DisplayName("Should pad empty string to size")
    void testEmptyStringPadding() {
        String result = LeftPadUtils.leftPad("", 3, "x");
        assertEquals("xxx", result);
    }

    @Test
    @DisplayName("Should pad empty string with multi-char padStr")
    void testEmptyStringMultiCharPad() {
        String result = LeftPadUtils.leftPad("", 5, "ab");
        assertEquals("ababa", result);
    }

    @Test
    @DisplayName("Should pad empty string with space")
    void testEmptyStringSpacePad() {
        String result = LeftPadUtils.leftPad("", 4, null);
        assertEquals("    ", result);
    }

    // ===== SINGLE CHARACTER STRING =====
    
    @Test
    @DisplayName("Should pad single character string")
    void testSingleCharPadding() {
        String result = LeftPadUtils.leftPad("a", 4, "x");
        assertEquals("xxxa", result);
    }

    @Test
    @DisplayName("Should pad single character with multi-char padStr")
    void testSingleCharMultiCharPad() {
        String result = LeftPadUtils.leftPad("z", 6, "ab");
        assertEquals("ababaz", result);
    }

    // ===== SPECIAL CHARACTERS IN INPUT AND PADSTR =====
    
    @Test
    @DisplayName("Should handle special characters in string")
    void testSpecialCharsInString() {
        String result = LeftPadUtils.leftPad("!@#", 6, "x");
        assertEquals("xxx!@#", result);
    }

    @Test
    @DisplayName("Should handle space character in string")
    void testSpaceInString() {
        String result = LeftPadUtils.leftPad(" x ", 6, "-");
        // String " x " has length 3, need 3 padding chars (6-3)
        assertEquals("--- x ", result);
    }

    @Test
    @DisplayName("Should handle space as padStr")
    void testSpaceAsPadStr() {
        String result = LeftPadUtils.leftPad("hi", 5, " ");
        assertEquals("   hi", result);
    }

    @Test
    @DisplayName("Should handle digits as padStr")
    void testDigitsAsPadStr() {
        String result = LeftPadUtils.leftPad("test", 8, "123");
        assertEquals("1231test", result);
    }

    // ===== COMBINED SCENARIOS =====
    
    @Test
    @DisplayName("Should handle padding to size 0")
    void testPadToZero() {
        String result = LeftPadUtils.leftPad("abc", 0, "x");
        assertEquals("abc", result);
    }

    @Test
    @DisplayName("Should handle negative size (treated as <= 0)")
    void testNegativeSize() {
        String result = LeftPadUtils.leftPad("abc", -5, "x");
        assertEquals("abc", result);
    }

    @Test
    @DisplayName("Should maintain string content integrity")
    void testContentIntegrity() {
        String original = "data";
        String result = LeftPadUtils.leftPad(original, 10, "-");
        assertTrue(result.endsWith(original));
        assertEquals(10, result.length());
    }

    @Test
    @DisplayName("Should handle Unicode characters")
    void testUnicodeCharacters() {
        String result = LeftPadUtils.leftPad("café", 7, "*");
        assertEquals("***café", result);
    }

    @Test
    @DisplayName("Should preserve original behavior for already-sufficient length")
    void testOriginalStringPreserved() {
        String str = "already good";
        String result = LeftPadUtils.leftPad(str, 5, "x");
        assertSame(str, result); // Should be the same object
    }
}
