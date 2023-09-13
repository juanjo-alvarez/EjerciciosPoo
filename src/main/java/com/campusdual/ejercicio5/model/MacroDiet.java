package com.campusdual.ejercicio5.model;

import com.campusdual.ejercicio5.exceptions.*;

public class MacroDiet extends Diet{

    private Integer maxCarbs;
    private Integer maxFats;
    private Integer maxProteins;

    public MacroDiet(Integer maxCarbs, Integer maxFats, Integer maxProteins) {
        this.maxCarbs = maxCarbs;
        this.maxFats = maxFats;
        this.maxProteins = maxProteins;
    }

    @Override
    public void showDietDetails() {
        super.showDietDetails();
        System.out.println("##########################");
        System.out.println("Limitaciones");
        System.out.println("##########################");
        System.out.println("Los valores máximos de macronutrientes son: Carbohidratos:"+this.getMaxCarbs()+" , Grasas:"+this.getMaxFats()+" , Proteinas:"+this.getMaxProteins());
    }

    @Override
    public void addFood(Food food, Integer grams) throws MaxValuedReachedException {
        Intake newIntake = new Intake(food,grams);
        if(getTotalCarbs() + newIntake.calculatedCarbos() > this.maxCarbs){
            throw new MaxCarbsReachedException();
        }
        if(getTotalFats() + newIntake.calculatedFats() > this.maxFats){
            throw new MaxFatsReachedException();
        }
        if(getTotalProteins() + newIntake.calculatedProteins() > this.maxProteins){
            throw new MaxProteinsReachedException();
        }
        super.addFood(food, grams);
    }

    public Integer getMaxCarbs() {
        return maxCarbs;
    }

    public void setMaxCarbs(Integer maxCarbs) {
        this.maxCarbs = maxCarbs;
    }

    public Integer getMaxFats() {
        return maxFats;
    }

    public void setMaxFats(Integer maxFats) {
        this.maxFats = maxFats;
    }

    public Integer getMaxProteins() {
        return maxProteins;
    }

    public void setMaxProteins(Integer maxProteins) {
        this.maxProteins = maxProteins;
    }
}
