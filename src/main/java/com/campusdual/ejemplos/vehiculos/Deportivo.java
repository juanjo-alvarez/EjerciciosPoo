package com.campusdual.ejemplos.vehiculos;

public class Deportivo extends Coche{

    public Integer nitro;


    public Deportivo(Integer velocidadMax, Integer potencia, String marca, String combustible, String color, Integer nitro) {
        super(2, velocidadMax, potencia, marca, combustible, 2, color);
        this.nitro = nitro;
    }

    @Override
    public void getDetails() {
        super.getDetails();
        System.out.println("Este coche tiene "+nitro+"g de nitro");
    }
}
