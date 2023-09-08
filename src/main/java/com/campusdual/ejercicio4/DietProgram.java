package com.campusdual.ejercicio4;

import com.campusdual.ejemplos.alimentos.Food;
import com.campusdual.ejercicio4.exceptions.MaxCaloriesReachedException;
import com.campusdual.ejercicio4.exceptions.MaxCarbsReachedException;
import com.campusdual.ejercicio4.exceptions.MaxFatsReachedException;
import com.campusdual.ejercicio4.exceptions.MaxProteinsReachedException;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class DietProgram {

    private Diet diet=null;

    private Scanner keyboard;

    private List<Food> foodList;

    public DietProgram(){
        this.keyboard = new Scanner(System.in);
        foodList = new ArrayList<>();
    }

    private Integer getOption(Integer min,Integer max){
        Integer option = 0;
        Boolean notvalid = true;
        do{
            try {
                option = keyboard.nextInt();
                notvalid = option<min || option>max;
            }catch (InputMismatchException e){
                System.out.println("La opción debe ser un número");
                keyboard.nextLine();
            }
            if(notvalid){
                System.out.println("Opción no valida, se requiere un número entre "+min+" y "+max);
            }
        }while(notvalid);
        return option;
    }

    public void showMenuProgram(){
        System.out.println("########################################################");
        System.out.println("################# Programa de dietas ###################");
        System.out.println("########################################################");
        Integer option;
        do{
            System.out.println("Escriba una opción:");
            System.out.println("===================================");
            System.out.println("1-Crear/reiniciar dieta");
            System.out.println("2-Mostrar información de la dieta");
            System.out.println("3-Agregar alimento al plan actual");
            System.out.println("4-Salir del programa");
            option = getOption(1,4);
            switch (option){
                case 1:
                    createMenu();
                    break;
                case 2:
                    showDetailsMenu();
                    break;
                case 3:
                    addFoodMenu();
                    break;
                case 4:
                    System.out.println("Gracias por usar el programa, hasta pronto :)");
                    break;
            }
        }while(option != 4);
    }

    private void addFoodMenu() {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Crear/reiniciar dieta");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Escriba una opción:");
        System.out.println("===================================");
        System.out.println("1-Agregar un nuevo alimento");
        System.out.println("2-Agregar un alimento ya existente");

        Integer option = getOption(1,2);
        switch (option){
            case 1:
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Datos de nuevo alimento");
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Nombre del alimento:");
                String name = keyboard.nextLine();
                System.out.println("Carbohidratos:");
                Integer carbs = keyboard.nextInt();
                System.out.println("Grasas:");
                Integer fats = keyboard.nextInt();
                System.out.println("Proteínas:");
                Integer proteins = keyboard.nextInt();
                System.out.println("Gramos:");
                Integer grams = keyboard.nextInt();
                Food newFood = new Food(name,carbs,fats,proteins);
                validateAndAddFoodToDiet(newFood,grams);
                foodList.add(newFood);
                break;
            case 2:
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Escoja un alimento");
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                Integer i = 1;
                for(Food food:foodList){
                    System.out.println(i+"-"+food.getName());
                    i++;
                }
                Integer element = keyboard.nextInt();
                Food storedFood = foodList.get(element);
                System.out.println("indique el número de gramos de "+storedFood.getName());
                Integer foodGrams = keyboard.nextInt();
                validateAndAddFoodToDiet(storedFood,foodGrams);
                break;
        }
    }

    private void validateAndAddFoodToDiet(Food food, Integer grams){
        boolean notValidOperation = true;
        do{
            try{
                this.diet.addFood(food,grams);
                notValidOperation=false;
            }catch (MaxCaloriesReachedException ecal){
                System.out.println("Se ha alcanzado el máximo valor de calorías permitido");
            }catch (MaxCarbsReachedException ecar){
                System.out.println("Se ha alcanzado el máximo valor de carbohidratos permitido");
            }catch (MaxFatsReachedException efat){
                System.out.println("Se ha alcanzado el máximo valor de grasas permitido");
            }catch (MaxProteinsReachedException epro){
                System.out.println("Se ha alcanzado el máximo valor de proteínas permitido");
            }catch (Exception e) {
                throw new RuntimeException(e);
            }
        }while (notValidOperation);
    }

    private void createMenu() {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Crear/reiniciar dieta");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Escriba una opción:");
        System.out.println("===================================");
        System.out.println("1-Dieta sin límite");
        System.out.println("2-Dieta por calorías");
        System.out.println("3-Dieta por macronutrientes");
        System.out.println("4-Dieta por datos personales");
        Integer option = getOption(1,4);
        switch (option){
            case 1:
                this.diet = new Diet();
                System.out.println("Se ha creado una dieta sin límites");
                break;
            case 2:
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Escriba número de calorias");
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                Integer calories = keyboard.nextInt();
                this.diet = new Diet(calories);
                System.out.println("Se ha creado una dieta con "+calories+" calorías máximas");
                break;
            case 3:
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Escriba los macronutrientes");
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Carbohidratos:");
                Integer carbs = keyboard.nextInt();
                System.out.println("Grasas:");
                Integer fats = keyboard.nextInt();
                System.out.println("Proteínas:");
                Integer proteins = keyboard.nextInt();
                this.diet = new Diet(fats,carbs,proteins);
                System.out.println("Se ha creado una dieta con Carbohidratos:"+carbs+", Grasas:"+fats+" ,Proteínas:"+proteins);
                break;
            case 4:
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Escriba los datos personales del paciente");
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Peso:");
                Integer weight = keyboard.nextInt();
                System.out.println("Altura:");
                Integer height = keyboard.nextInt();
                System.out.println("Edad:");
                Integer age = keyboard.nextInt();
                System.out.println("Mujer u Hombre(m/h):");
                String sexCharacter = keyboard.nextLine();
                this.diet = new Diet("m".equalsIgnoreCase(sexCharacter),age,height,weight);
                System.out.println("Se ha creado una dieta de "+this.diet.getMaxCalories()+" calorías máximas");
                break;
        }
    }

    private void showDetailsMenu() {
        if(this.diet!=null){
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            System.out.println("Detalles de la dieta");
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            if(this.diet.getMaxCalories()!=null){
                System.out.println("El número máximo de calorías es:"+this.diet.getMaxCalories());
            }
            if(this.diet.getMaxCarbs() != null || this.diet.getMaxFats() != null || this.diet.getMaxProteins() != null){
                System.out.println("Los valores máximos de macronutrientes son: Carbohidratos:"+this.diet.getMaxCarbs()+" , Grasas:"+this.diet.getMaxFats()+" , Proteinas:"+this.diet.getMaxProteins());
            }
            System.out.println("Número de alimentos de la dieta:"+this.diet.getFoodNumber());
            System.out.println("Calorías:"+this.diet.getTotalCalories());
            System.out.println("Carbohidratos:"+this.diet.getTotalCarbs());
            System.out.println("Grasas:"+this.diet.getTotalFats());
            System.out.println("Proteínas:"+this.diet.getTotalProteins());
        }else{
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            System.out.println("La dieta no esta iniciada");
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        }
    }
}
