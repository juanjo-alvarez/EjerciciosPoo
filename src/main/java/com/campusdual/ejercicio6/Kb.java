package com.campusdual.ejercicio6;

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

    public static Integer forceNextInt(){
        Integer resultado = null;
        boolean notvalid=true;
        do {
            try {
                resultado = nextInt();
                notvalid=false;
            } catch (InputMismatchException e) {
                System.out.println("Es necesario que sea un número");
            }
        }while(notvalid);
        return resultado;
    }

    public static String nextLine(){
        Scanner keyboard = new Scanner(System.in);
        String result = keyboard.nextLine();
        return result;
    }

    public static Integer getOption(Integer min,Integer max){
        Integer option = 0;
        Boolean notvalid = true;
        do{
            try {
                option = Kb.forceNextInt();
                notvalid = option<min || option>max;
            }catch (InputMismatchException e){
                System.out.println("La opción debe ser un número");
                Kb.nextLine();
            }
            if(notvalid){
                System.out.println("Opción no valida, se requiere un número entre "+min+" y "+max);
            }
        }while(notvalid);
        return option;
    }
}
