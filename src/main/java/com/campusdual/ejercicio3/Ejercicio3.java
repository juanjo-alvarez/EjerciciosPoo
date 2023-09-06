package com.campusdual.ejercicio3;

import java.util.Scanner;

public class Ejercicio3 {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Escriba un número del 1 al 20");
        Integer numero = teclado.nextInt();
        switch (numero){
            case 2:
            case 3:
            case 5:
            case 7:
            case 11:
            case 13:
            case 17:
            case 19:
                System.out.println("El número es primo");
               break;
            default:
                System.out.println("El número no es primo");
                break;
        }
    }
}
