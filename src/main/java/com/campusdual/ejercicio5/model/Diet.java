package com.campusdual.ejercicio5.model;

import com.campusdual.ejercicio5.exceptions.*;

import java.util.ArrayList;
import java.util.List;
public class Diet {
    private List<Intake> intakes;

    public Diet(){
        this.intakes = new ArrayList<>();
    }

    public void addFood(Food food, Integer grams) throws MaxValuedReachedException {
        Intake intake = new Intake(food,grams);
        intakes.add(intake);
    }

	public Integer getTotalCalories(){
        Integer totalCalories = 0;
        for(Intake intake:intakes){
            totalCalories = totalCalories+ intake.calculatedCalories();
        }
        return totalCalories;
    }

	public Integer getTotalCarbs(){
        Integer totalCarbs = 0;
        for(Intake intake:intakes){
            totalCarbs = totalCarbs + intake.calculatedCarbos();
        }
        return totalCarbs;
    }

	public Integer getTotalFats(){
        Integer totalFats = 0;
        for(Intake intake:intakes){
            totalFats = totalFats + intake.calculatedFats();
        }
        return totalFats;
    }

	public Integer getTotalProteins(){
        Integer totalProtein = 0;
        for(Intake intake: intakes){
            totalProtein = totalProtein + intake.calculatedProteins();
        }
        return totalProtein;
    }

    public Integer getFoodNumber(){
        return intakes.size();
    }

    public List<Intake> getIntakes() {
        return intakes;
    }

    public void setIntakes(List<Intake> intakes) {
        this.intakes = intakes;
    }

    public String getDietIntakes(){
        String result = "";
        boolean first=true;
        for(Intake intake:intakes){
            if(first){
                first = false;
                result = intake.getName()+":"+intake.getGrams();
            }else{
                result = result + ", "+intake.getName()+":"+intake.getGrams();
            }
        }
        return result;
    }

    public void showDietDetails(){
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Detalles de la dieta");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Número de alimentos de la dieta:"+this.getFoodNumber());
        System.out.println("Calorías:"+this.getTotalCalories());
        System.out.println("Carbohidratos:"+this.getTotalCarbs());
        System.out.println("Grasas:"+this.getTotalFats());
        System.out.println("Proteínas:"+this.getTotalProteins());
        System.out.println("Alimentos de la dieta:"+this.getDietIntakes());
    }
}
