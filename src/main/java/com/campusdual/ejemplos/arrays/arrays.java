package com.campusdual.ejemplos.arrays;

import java.util.ArrayList;

public class arrays {
    public static void main(String[] args) {
        Integer[] array = {1,2,3,4,5};
        Integer[] array2 = new Integer[5];

        for(int i=0;i<array.length;i++){
            System.out.println("valor "+i+":"+array[i]);
        }

        for(Integer elemento:array){
            System.out.println("valor:"+elemento);
        }

        ArrayList<Integer> arrayL = new ArrayList<>();
        arrayL.add(1);
        arrayL.add(2);
        arrayL.add(3);
        ArrayList<Integer> arrayL2 = new ArrayList<>();
        arrayL2.addAll(arrayL);
        arrayL2.add(4);
        for(Integer elemento:arrayL2){
            System.out.println("valor:"+elemento);
        }
        if(arrayL.contains(4)){
            System.out.println("contiene el 4");
        }else{
            System.out.println("no contiene el 4");
        }

        String frase = "Tres tristes tigres tomaban trigo en un trigal";
        String[] fraseSplit = frase.split(" ");

    }
}
