package com.crud.first;

import java.util.stream.IntStream;

public class FirstNumber {

    public boolean isGivenNumberIsFirst(int number) {
        double square = Math.sqrt(number);
        for(int i = 2; i < square; i++) {
            if(number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
