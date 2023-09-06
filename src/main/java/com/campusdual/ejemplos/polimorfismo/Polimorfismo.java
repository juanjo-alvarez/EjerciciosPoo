package com.campusdual.ejemplos.polimorfismo;

public class Polimorfismo {
    public Integer numero;
    public String valor;

    public Polimorfismo(){
        this.numero=0;
        this.valor="vacio";
    }

    public Polimorfismo(Integer numero){
        this.numero=numero;
        this.valor="vacio";
    }

    public Polimorfismo(String valor){
        this.valor=valor;
        this.numero=0;
    }

    public Polimorfismo(String valor, Integer numero){
        this.valor=valor;
        this.numero=numero;
    }

    public Polimorfismo(Integer x, String y){
        this.numero=x+100;
        this.valor="cadena "+y;
    }

    public void printValues(){
        System.out.println(this.valor + ":"+this.numero);
    }

    public class PolimorfismoSub{

    }
}
