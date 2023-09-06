package com.campusdual.ejemplos.polimorfismo;

public class PolimorfismoMain {
    public static void main(String[] args) {
        Polimorfismo poli= new Polimorfismo(1);
        poli.printValues();
        poli.numero=1;

        poli= new Polimorfismo("coche");
        poli.printValues();

        poli= new Polimorfismo("silla",2);
        poli.printValues();

        poli= new Polimorfismo(3,"mesa");
        poli.printValues();

        poli = new Polimorfismo();
        poli.printValues();
    }
}
