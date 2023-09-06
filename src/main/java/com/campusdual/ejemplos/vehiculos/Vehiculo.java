package com.campusdual.ejemplos.vehiculos;

public abstract class Vehiculo implements Maquina {
    private Integer pasajeros;
    private Integer velocidadMax;
    private Integer potencia;
    private String marca;

    public Vehiculo(Integer pasajeros, Integer velocidadMax, Integer potencia, String marca) {
        this.pasajeros = pasajeros;
        this.velocidadMax = velocidadMax;
        this.potencia = potencia;
        this.marca = marca;
    }

    public abstract void getDetails();

    public Integer calcularMovimiento(Integer segundos){
        return ((velocidadMax*segundos)*(potencia/pasajeros));
    }
}
