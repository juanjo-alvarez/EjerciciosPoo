package com.campusdual.ejercicio4.exceptions;

public class MaxProteinsReachedException extends MaxValuedReachedException{

    public MaxProteinsReachedException() {
        super("Max proteins reached for the actual diet");
    }
}
