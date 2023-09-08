package com.campusdual.ejemplos.alimentos;

public class FoodUtility {
    public static void main(String[] args) {
        Food zanahoria = new Food("Zanahoria",12,0,1);
        System.out.println("Calorias de 100g de "+zanahoria.getName()+":"+zanahoria.getCalories(100));
    }
}
