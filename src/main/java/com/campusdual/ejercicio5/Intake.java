package com.campusdual.ejercicio5;

public class Intake extends Food {

    public static final Integer GRAMS_PER_PORTION = 100;

    Integer grams;

    public Intake(String name) {
        super(name);
    }

    public Intake(String name, Integer carbos, Integer fats, Integer proteins,Integer grams) {
        super(name, carbos, fats, proteins);
        this.grams = grams;
    }

    public Intake(Food food, Integer grams){
        super(food.getName(), food.getCarbos(), food.getFats(), food.getProteins());
        this.setGrams(grams);
    }

    public Integer calculatedCalories(){
        return this.getCalories(this.grams);
    }

    public Integer calculatedCarbos(){
        return this.getCarbos() * grams / GRAMS_PER_PORTION;
    }

    public Integer calculatedFats(){
        return this.getFats() * grams / GRAMS_PER_PORTION;
    }

    public Integer calculatedProteins(){
        return this.getProteins() * grams / GRAMS_PER_PORTION;
    }

    public Integer getGrams() {
        return grams;
    }

    public void setGrams(Integer grams) {
        this.grams = grams;
    }
}
