package com.campusdual.ejercicio4;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Kb {
    public static Integer nextInt(){
        Scanner keyboard = new Scanner(System.in);
        String resultString = keyboard.nextLine();
        Integer result = 0;
        try{
            result = Integer.parseInt(resultString);
        }catch (Exception e){
            throw new InputMismatchException();
        }
        return result;
    }

    public static String nextLine(){
        Scanner keyboard = new Scanner(System.in);
        String result = keyboard.nextLine();
        return result;
    }
}
