package com.campusdual.ejemplos;

public class PolimorfismoMain {
    public static void main(String[] args) {
        Polimorfismo poli1= new Polimorfismo(1);
        poli1.printValues();

        Polimorfismo poli2= new Polimorfismo("coche");
        poli2.printValues();

        Polimorfismo poli3= new Polimorfismo("silla",2);
        poli3.printValues();

        Polimorfismo poli4= new Polimorfismo(3,"mesa");
        poli4.printValues();
    }
}
