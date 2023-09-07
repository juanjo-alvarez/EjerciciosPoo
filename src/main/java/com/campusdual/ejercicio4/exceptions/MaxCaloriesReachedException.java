package com.campusdual.ejercicio4.exceptions;

public class MaxCaloriesReachedException extends MaxValuedReachedException{

    public MaxCaloriesReachedException() {
        super("Max calories reached for the actual diet");
    }
}
