package org.esg.exercise.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StringCalculatorTest {
    private StringCalculator stringCalculator;

    @BeforeEach
    void setUp() {
        stringCalculator = new StringCalculator();
    }

    @Test
    void shouldReturnZeroWhenBlankString() throws Exception {
        assertEquals(0, stringCalculator.add(""));
    }

    @Test
    void shouldReturnOneWhenStringOfOneGiven() throws Exception {
        assertEquals(1, stringCalculator.add("1"));
    }

    @Test
    void shouldSumTwoNumbersWhenTwoNumbersInString() throws Exception {
        assertEquals(3, stringCalculator.add("1,2"));
    }

    @Test
    void shouldIgnoreNonDigitAlfa() throws Exception {
        assertEquals(10, stringCalculator.add("6,r,4"));
    }

    @Test
    void shouldIgnoreReturnCharactersInString() throws Exception {
        assertEquals(6, stringCalculator.add("1\n2,3"));
    }

    @Test
    void shouldReturnTotalWithDifferentDelimiter() throws Exception {
        assertEquals(3, stringCalculator.add("//;\n1;2"));
    }

    @Test
    void shouldThrowExceptionIncludingNegativeNumberWhenNegativeNumbersPresent() {
        Exception thrown = assertThrows(Exception.class, () -> stringCalculator.add("-1,2"));
        assertEquals("Negatives not allowed: -1", thrown.getMessage());
    }

    @Test
    void shouldThrowExceptionIncludingListOfNegativeNumberWhenNegativeNumbersPresent() {
        Exception thrown = assertThrows(Exception.class, () -> stringCalculator.add("2,-4,3,-5"));
        assertEquals("Negatives not allowed: -4,-5", thrown.getMessage());
    }

    @Test
    void shouldIgnoreNumbersGreaterThatOneThousand() throws Exception {
        assertEquals(2, stringCalculator.add("1001,2"));
    }

    @Test
    void shouldUseDelimiterOfLenthGreaterThanOne() throws Exception {
        assertEquals(6, stringCalculator.add("//[|||]\n1|||2|||3"));
    }

    @Test
    void shouldUseMultiDelimitersOfLengthOne() throws Exception {
        assertEquals(6, stringCalculator.add("//[|][%]\n1|2%3"));
    }

    @Test
    void shouldUseMultiDelimitersOfLengthGreaterThanOne() throws Exception {
        assertEquals(10, stringCalculator.add("//[|||][£@!]\n1|||2|||3£@!4"));
    }
}