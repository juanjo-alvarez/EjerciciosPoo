package com.campusdual.ejemplos.vehiculos;

public class probarVehiculos {

    public static void main(String[] args) {
        Coche panda = new Coche(4,120,5,"Panda","Gasolina",5,"rojo");
        panda.getDetails();
        Deportivo porche = new Deportivo(500,100,"Porche","Gasolina","rojo",1000);
        porche.getDetails();

        Maquina maquina = panda;
        System.out.println("Se mueve "+ maquina.calcularMovimiento(100));

        maquina = porche;
        System.out.println("Se mueve "+ maquina.calcularMovimiento(100));


    }

}
