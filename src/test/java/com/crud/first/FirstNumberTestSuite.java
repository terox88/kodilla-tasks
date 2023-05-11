package com.crud.first;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FirstNumberTestSuite {
    @Test
    @DisplayName("Test with first number")
    void isGivenNumberIsFirstTest() {
        //Given
        FirstNumber first = new FirstNumber();
        //When
        boolean result = first.isGivenNumberIsFirst(3);
        //Then
        Assertions.assertTrue(result);
    }
    @Test
    @DisplayName("Test with not first number")
    void anotherIsGivenTest() {
        //Given
        FirstNumber first = new FirstNumber();
        //When
        boolean result = first.isGivenNumberIsFirst(200);
        //Then
        Assertions.assertFalse(result);
    }
}
