package com.campusdual.ejercicio4;

import com.campusdual.ejemplos.alimentos.Food;
import com.campusdual.ejercicio4.exceptions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
* --Escribe un programa que utilice la clase Dieta y despliegue un menú donde puedas añadir alimentos. El menú tendrá las siguientes opciones.
	-1. Crear/reiniciar dieta: crea o remplaza la dieta inicial
		-a. Sin limite
		-b. Por calorías
		-c. Por macronutrientes
		-d. Por datos personales
	-2. Mostrar información: muestra calorías y macronutrientes de la dieta
	-3. Agregar alimento: agrega un alimento a la dieta actual y añade ese alimento a la lista de alimentos disponible
		-a. Nuevo alimento
		-b. Alimento existente
	-4. Salir
* */
public class Menu {
    public static void main(String[] args) throws NoOptionException {
        Scanner teclado = new Scanner(System.in);
        Integer option;
        Diet diet=null;
        List<Food> foods = new ArrayList<>();
        do{
            option = teclado.nextInt();
            teclado.nextLine();
            switch (option){
                case 1:
                    diet=createMenu();
                    break;
                case 2:
                    showDetailsMenu(diet);
                    break;
                case 3:
                    addFoodMenu(diet,foods);
                    break;
                case 4:
                    System.out.println("Gracias por usar el programa, hasta pronto :)");
                    break;
                default:
                    throw new NoOptionException();
            }
        }while(option != 4);
    }

    private static void addFoodMenu(Diet diet, List<Food> foods) throws NoOptionException {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Crear/reiniciar dieta");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Escriba una opción:");
        System.out.println("===================================");
        System.out.println("1-Agregar un nuevo alimento");
        System.out.println("2-Agregar un alimento ya existente");
        Scanner teclado = new Scanner(System.in);
        Integer option = teclado.nextInt();
        teclado.nextLine();
        switch (option){
            case 1:
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Datos de nuevo alimento");
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Nombre del alimento:");
                String name = teclado.nextLine();
                teclado.nextLine();
                System.out.println("Carbohidratos:");
                Integer carbs = teclado.nextInt();
                teclado.nextLine();
                System.out.println("Grasas:");
                Integer fats = teclado.nextInt();
                teclado.nextLine();
                System.out.println("Proteínas:");
                Integer proteins = teclado.nextInt();
                teclado.nextLine();
                System.out.println("Gramos:");
                Integer grams = teclado.nextInt();
                teclado.nextLine();
                Food newFood = new Food(name,carbs,fats,proteins);
                validateAndAddFoodToDiet(diet,newFood,grams);
                foods.add(newFood);
                break;
            case 2:
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Escoja un alimento");
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                Integer i = 1;
                for(Food food:foods){
                    System.out.println(i+"-"+food.getName());
                    i++;
                }
                Integer element = teclado.nextInt();
                teclado.nextLine();
                Food storedFood = foods.get(element);
                System.out.println("indique el número de gramos de "+storedFood.getName());
                Integer foodGrams = teclado.nextInt();
                teclado.nextLine();
                validateAndAddFoodToDiet(diet,storedFood,foodGrams);
                break;
            default:
                throw new NoOptionException();
        }
    }

    private static void validateAndAddFoodToDiet(Diet diet, Food food, Integer grams){
        boolean invalidOperation = true;
        do{
            try{
                diet.addFood(food,grams);
                invalidOperation=false;
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
        }while (invalidOperation);
    }

    private static Diet createMenu() throws NoOptionException {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Crear/reiniciar dieta");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Escriba una opción:");
        System.out.println("===================================");
        System.out.println("1-Dieta sin límite");
        System.out.println("2-Dieta por calorías");
        System.out.println("3-Dieta por macronutrientes");
        System.out.println("4-Dieta por datos personales");
        Scanner teclado = new Scanner(System.in);
        Integer option = teclado.nextInt();
        teclado.nextLine();
        Diet diet=null;
        switch (option){
            case 1: diet = new Diet();
                System.out.println("Se ha creado una dieta sin límites");
                break;
            case 2:
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Escriba número de calorias");
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                Integer calories = teclado.nextInt();
                teclado.nextLine();
                diet = new Diet(calories);
                System.out.println("Se ha creado una dieta con "+calories+" calorías máximas");
                break;
            case 3:
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Escriba los macronutrientes");
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Carbohidratos:");
                Integer carbs = teclado.nextInt();
                teclado.nextLine();
                System.out.println("Grasas:");
                Integer fats = teclado.nextInt();
                teclado.nextLine();
                System.out.println("Proteínas:");
                Integer proteins = teclado.nextInt();
                teclado.nextLine();
                diet = new Diet(fats,carbs,proteins);
                System.out.println("Se ha creado una dieta con Carbohidratos:"+carbs+", Grasas:"+fats+" ,Proteínas:"+proteins);
                break;
            case 4:
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Escriba los datos personales del paciente");
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Peso:");
                Integer weight = teclado.nextInt();
                teclado.nextLine();
                System.out.println("Altura:");
                Integer height = teclado.nextInt();
                teclado.nextLine();
                System.out.println("Edad:");
                Integer age = teclado.nextInt();
                teclado.nextLine();
                System.out.println("Mujer u Hombre(m/h):");
                String sexCharacter = teclado.nextLine();
                teclado.nextLine();
                diet = new Diet("m".equalsIgnoreCase(sexCharacter),age,height,weight);
                System.out.println("Se ha creado una dieta de "+diet.getMaxCalories()+" calorías máximas");
                break;
            default:
                throw new NoOptionException();
        }
        return diet;
    }

    private static void showDetailsMenu(Diet diet) {
        if(diet!=null){
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            System.out.println("Detalles de la dieta");
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            if(diet.getMaxCalories()!=null){
                System.out.println("El número máximo de calorías es:"+diet.getMaxCalories());
            }
            if(diet.getMaxCarbs() != null || diet.getMaxFats() != null || diet.getMaxProteins() != null){
                System.out.println("Los valores máximos de macronutrientes son: Carbohidratos:"+diet.getMaxCarbs()+" , Grasas:"+diet.getMaxFats()+" , Proteinas:"+diet.getMaxProteins());
            }
            System.out.println("Número de alimentos de la dieta:"+diet.getFoodNumber());
            System.out.println("Calorías:"+diet.getTotalCalories());
            System.out.println("Carbohidratos:"+diet.getTotalCarbs());
            System.out.println("Grasas:"+diet.getTotalFats());
            System.out.println("Proteínas:"+diet.getTotalProteins());
        }else{
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            System.out.println("La dieta no esta iniciada");
            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        }
    }

    public static class NoOptionException extends Exception{
        public NoOptionException(){
            super("No existe esa opción entre los valores disponibles");
        }
    }
}
