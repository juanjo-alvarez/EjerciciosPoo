package com.campusdual.ejercicio4;

import com.campusdual.ejemplos.alimentos.Food;
import com.campusdual.ejercicio4.exceptions.*;

import java.util.List;

/*
* Escribe una clase dieta, que teniendo en cuenta una serie de alimentos, vaya sumando cantidades en gramos y calcule cuentas calorías, carbohidratos, proteinas y grasas genera la ingesta
La clase dieta tiene que tener las siguientes funcionalidades:
	-Diet(): crea una dieta sin límite de calorías
	-Diet(Integer maxCalories): crea una dieta con un máximo de calorías, en cuanto se supera ese máximo cuando se adjunta un alimento se despliega un error
	-Diet(Integer maxFats, Integer maxCarbs, Integer maxProtein): crea una dieta con un máximo de estos tres valores, si se supera alguno de ellos cuando se adjunta un alimento se indicara que macronutriente y desplegará un error
	-Diet(Boolean women, Integer age, Integer height, Integer weight): Calcula el metabolismo basal de la persona y lo asigna como máximo de calorías en la dieta
		--CALCULAR METABOLISMO BASAL
			E = edad
			A = altura en centímetros
			P = peso en kilos

			Fórmula Hombres: TMB = 10P + 6,25A – 5E + 5
			Fórmula Mujeres: TMB = 10P + 6,25A – 5E – 161
	-addFood(Food,Integer): agrega un alimento y una cantidad en gramos
	-getTotalCalories(): devuelve el total de calorías
	-getTotalCarbs(): devuelve el total de carbohidratos
	-getTotalFats(): devuelve el total de grasas
	-getTotalProteins(): devuelve el total de proteinas
*
* */
public class Diet {
    public static final Integer GRAMS_PER_PORTION = 100;
    public static final String OK = "OK";
    public static final String MAX_CALORIES_REBASE = "MAX_CALORIES_REBASE";
    public static final String MAX_CARBS_REBASE = "MAX_CARBS_REBASE";
    public static final String MAX_FATS_REBASE = "MAX_FATS_REBASE";
    public static final String MAX_PROTEINS_REBASE = "MAX_PROTEINS_REBASE";

    private Integer maxCalories;

    private Integer maxCarbs;
    private Integer maxFats;
    private Integer maxProteins;
    private List<Intake> intakes;

    public Diet(){
    }

    public Diet(Integer maxCalories){
        this.maxCalories=maxCalories;
    }

    public Diet(Integer maxFats, Integer maxCarbs, Integer maxProteins){
        this.maxCarbs=maxCarbs;
        this.maxFats=maxFats;
        this.maxProteins=maxProteins;
    }

    public Diet(Boolean women, Integer age, Integer height, Integer weight){
        if(women){
            maxCalories = (int) ((10*weight) + (6.25*height))-(5*age)-161;
        }else{
            maxCalories = (int) ((10*weight) + (6.25*height))-(5*age)+5;
        }
    }

    public void addFood(Food food, Integer grams) throws MaxValuedReachedException {
        Intake intake = new Intake(food,grams);
        String validation = isValidIntake(intake);
        if(OK.equalsIgnoreCase(validation)){
            intakes.add(intake);
        }else{
            if(MAX_CALORIES_REBASE.equalsIgnoreCase(validation)){
                throw new MaxCaloriesReachedException();
            }
            if(MAX_CARBS_REBASE.equalsIgnoreCase(validation)){
                throw new MaxCarbsReachedException();
            }
            if(MAX_FATS_REBASE.equalsIgnoreCase(validation)){
                throw new MaxFatsReachedException();
            }
            if(MAX_PROTEINS_REBASE.equalsIgnoreCase(validation)){
                throw new MaxProteinsReachedException();
            }
        }
    }

    private String isValidIntake(Intake intake){
        Integer actualCaories = getTotalCalories();
        if(this.maxCalories < (actualCaories + intake.getFood().getCalories(intake.getGrams()))){
            return MAX_CALORIES_REBASE;
        }
        Integer actualCarbs = getTotalCarbs();
        if(this.maxCarbs < (actualCarbs + (intake.getFood().getCarbos() * intake.grams / GRAMS_PER_PORTION))){
            return MAX_CARBS_REBASE;
        }
        Integer actualFats = getTotalFats();

        if(this.maxFats < (actualFats + (intake.getFood().getFats() * intake.grams / GRAMS_PER_PORTION))){
            return MAX_FATS_REBASE;
        }
        Integer actualProteins = getTotalProteins();
        if(this.maxProteins < (actualProteins + (intake.getFood().getProteins() * intake.grams / GRAMS_PER_PORTION))){
            return MAX_PROTEINS_REBASE;
        }
        return OK;
    }

	public Integer getTotalCalories(){
        Integer totalCalories = 0;
        for(Intake intake:intakes){
            totalCalories = totalCalories+ intake.getFood().getCalories(intake.getGrams());
        }
        return totalCalories;
    }

	public Integer getTotalCarbs(){
        Integer totalCarbs = 0;
        for(Intake intake:intakes){
            totalCarbs = totalCarbs + intake.getFood().getCarbos()* intake.grams/GRAMS_PER_PORTION;
        }
        return totalCarbs;
    }

	public Integer getTotalFats(){
        Integer totalFats = 0;
        for(Intake intake:intakes){
            totalFats = totalFats + intake.getFood().getFats()* intake.grams/GRAMS_PER_PORTION;
        }
        return totalFats;
    }

	public Integer getTotalProteins(){
        Integer totalProtein = 0;
        for(Intake intake: intakes){
            totalProtein = totalProtein + intake.getFood().getProteins()*intake.grams/GRAMS_PER_PORTION;
        }
        return totalProtein;
    }

    public Integer getFoodNumber(){
        return intakes.size();
    }

    public Integer getMaxCalories(){
        return maxCalories;
    }

    public void setMaxCalories(Integer maxCalories) {
        this.maxCalories = maxCalories;
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

    public List<Intake> getIntakes() {
        return intakes;
    }

    public void setIntakes(List<Intake> intakes) {
        this.intakes = intakes;
    }

    private class Intake{
        private Food food;
        private Integer grams;

        public Intake(Food food, Integer grams) {
            this.food = food;
            this.grams = grams;
        }

        public Food getFood() {
            return food;
        }

        public void setFood(Food food) {
            this.food = food;
        }

        public Integer getGrams() {
            return grams;
        }

        public void setGrams(Integer grams) {
            this.grams = grams;
        }
    }
}
