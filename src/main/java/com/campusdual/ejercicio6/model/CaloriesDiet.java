package com.campusdual.ejercicio6.model;

import com.campusdual.ejercicio6.exceptions.MaxCaloriesReachedException;
import com.campusdual.ejercicio6.exceptions.MaxValuedReachedException;

public class CaloriesDiet extends Diet {

    private Integer maxCalories;
    public CaloriesDiet(Integer maxCalories) {
        super();
        this.maxCalories = maxCalories;
    }
    public CaloriesDiet(Boolean women, Integer age, Integer height, Integer weight){
        super();
        if(women){
            maxCalories = (int) ((10*weight) + (6.25*height))-(5*age)-161;
        }else{
            maxCalories = (int) ((10*weight) + (6.25*height))-(5*age)+5;
        }
    }

    @Override
    public void showDietDetails() {
        super.showDietDetails();
        System.out.println("##########################");
        System.out.println("Limitaciones");
        System.out.println("##########################");
        System.out.println("El número máximo de calorías es:"+this.getMaxCalories());
    }

    @Override
    public void addFood(Food food, Integer grams) throws MaxValuedReachedException {
        Intake newIntake = new Intake(food,grams);
        if(getTotalCalories() + newIntake.calculatedCalories() > this.maxCalories){
            throw new MaxCaloriesReachedException();
        }
        super.addFood(food, grams);
    }

    public Integer getMaxCalories() {
        return maxCalories;
    }

    public void setMaxCalories(Integer maxCalories) {
        this.maxCalories = maxCalories;
    }
}
