package com.campusdual.ejercicio4.exceptions;

public class MaxFatsReachedException extends MaxValuedReachedException{

    public MaxFatsReachedException() {
        super("Max fats reached for the actual diet");
    }
}
