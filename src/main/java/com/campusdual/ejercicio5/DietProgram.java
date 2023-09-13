package com.campusdual.ejercicio5;

import com.campusdual.ejercicio5.enums.CustomerField;
import com.campusdual.ejercicio5.enums.Days;
import com.campusdual.ejercicio5.enums.DietField;
import com.campusdual.ejercicio5.exceptions.MaxCaloriesReachedException;
import com.campusdual.ejercicio5.exceptions.MaxCarbsReachedException;
import com.campusdual.ejercicio5.exceptions.MaxFatsReachedException;
import com.campusdual.ejercicio5.exceptions.MaxProteinsReachedException;
import com.campusdual.ejercicio5.model.*;

import java.util.*;

public class DietProgram {
    public static final String OPERATION_CANCLLED_MSG = "Operación cancelada";
    public static final String ERROR_ELEMENT_NOT_FOUND_MSG = "Error - No se encontró el elemento";
    public static final String ATT_DIET_NOT_VALID_MSG = "La dieta no tiene ese atributo";

    private Map<String, Diet> dietList;

    private List<Customer> customerList;

    private List<Food> foodList;

    public DietProgram(){
        foodList = new ArrayList<>();
        dietList = new HashMap<>();
        customerList = new ArrayList<>();
    }

    public void showMenuProgram(){
        System.out.println("########################################################");
        System.out.println("################# Programa de dietas ###################");
        System.out.println("########################################################");
        Integer option;
        do{
            System.out.println("Escriba una opción:");
            System.out.println("===================================");
            System.out.println("1-Gestión de Dietas");
            System.out.println("2-Gestión de Pacientes");
            System.out.println("3-Salir del programa");
            option = Kb.getOption(1,3);
            switch (option){
                case 1:
                    dietManager();
                    break;
                case 2:
                    customerManager();
                    break;
                case 3:
                    System.out.println("Gracias por usar el programa, hasta pronto :)");
                    break;
            }
        }while(option != 3);
    }

    private void dietManager() {
        System.out.println("########################################################");
        System.out.println("################# Gestión de Dietas# ###################");
        System.out.println("########################################################");
        Integer option;
        do{
            System.out.println("Escriba una opción:");
            System.out.println("===================================");
            System.out.println("1-Agregar nueva dieta");
            System.out.println("2-Listar dietas");
            System.out.println("3-Eliminar dieta");
            System.out.println("4-Salir");
            option = Kb.getOption(1,4);
            switch (option){
                case 1:
                    createMenu();
                    break;
                case 2:
                    manageDiets();
                    break;
                case 3:
                    deleteDiet();
                    break;
            }
        }while(option != 4);
    }

    private void manageDiets() {
        System.out.println("Escoja una dieta a modificar:");
        String selected = getSelectedDiet();
        if(selected == null){
            System.out.println(OPERATION_CANCLLED_MSG);
        }else{
            Diet selectedDiet = dietList.get(selected);
            Integer option = null;
            do{
                selectedDiet.showDietDetails();
                System.out.println("1-Cambiar Calorías máximas");
                System.out.println("2-Cambiar Carbohidratos máximos");
                System.out.println("3-Cambiar Grasa máximas");
                System.out.println("4-Cambiar Proteinas máximas");
                System.out.println("5-Añadir alimento");
                System.out.println("6-Eliminar alimento");
                System.out.println("7-salir");
                option = Kb.getOption(1,7);
                switch (option){
                    case 1: updateDiet(selectedDiet, DietField.MAXCALORIES);
                        break;
                    case 2: updateDiet(selectedDiet, DietField.MAXCARBS);
                        break;
                    case 3: updateDiet(selectedDiet, DietField.MAXFATS);
                        break;
                    case 4: updateDiet(selectedDiet, DietField.MAXPROTEINS);
                        break;
                    case 5: addFoodMenu(selectedDiet);
                        break;
                    case 6: deleteFoodMenu(selectedDiet);
                        break;
                }
            }while (option!=7);
        }
    }

    private void deleteFoodMenu(Diet updateDiet) {
        if(updateDiet==null){
            System.out.println("Para eliminar alimentos hace falta iniciar una dieta");
            return;
        }
        if(updateDiet.getIntakes().size()==0){
            System.out.println("Para eliminar alimentos hace falta que exista al menos uno");
            return;
        }
        System.out.println("Indique una Ingesta a eliminar");
        int i = 1;
        for(Intake intake:updateDiet.getIntakes()){
            System.out.println(i+"-"+intake.getName()+":"+intake.getGrams());
            i++;
        }
        System.out.println(i+"-Cancelar");
        Integer selected = Kb.getOption(1,i);
        if(i==selected){
            System.out.println(OPERATION_CANCLLED_MSG);
            return;
        }
        Intake intakeSelected = updateDiet.getIntakes().get(i-1);
        System.out.println("Se va a eliminar la ingesta "+intakeSelected.getName()+":"+intakeSelected.getGrams());
        updateDiet.getIntakes().remove(intakeSelected);
    }

    private void updateDiet(Diet selectedDiet, DietField field) {
        System.out.println("Escriba un nuevo valor");
        Integer newValue = Kb.forceNextInt();
        switch (field){
            case MAXCALORIES:
                if(selectedDiet instanceof CaloriesDiet){
                    ((CaloriesDiet) selectedDiet).setMaxCalories(newValue);
                }else{
                    System.out.println(ATT_DIET_NOT_VALID_MSG);
                }
                break;
            case MAXCARBS:
                if(selectedDiet instanceof MacroDiet){
                    ((MacroDiet) selectedDiet).setMaxCarbs(newValue);
                }else{
                    System.out.println(ATT_DIET_NOT_VALID_MSG);
                }
                break;
            case MAXFATS:
                if(selectedDiet instanceof MacroDiet){
                    ((MacroDiet) selectedDiet).setMaxFats(newValue);
                }else{
                    System.out.println(ATT_DIET_NOT_VALID_MSG);
                }
                break;
            case MAXPROTEINS:
                if(selectedDiet instanceof MacroDiet){
                    ((MacroDiet) selectedDiet).setMaxProteins(newValue);
                }else{
                    System.out.println(ATT_DIET_NOT_VALID_MSG);
                }
        }
    }

    private void deleteDiet() {
        System.out.println("Escoja una dieta a eliminar:");
        String selected = getSelectedDiet();
        if(selected == null){
            System.out.println(OPERATION_CANCLLED_MSG);
        }else{
            if(isDietAsigned(selected)){
                System.out.println("La dieta no se puede eliminar dado que la tiene asignada un cliente");
                return;
            }
            Diet deleted = dietList.remove(selected);
            if(deleted==null){
                System.out.println(ERROR_ELEMENT_NOT_FOUND_MSG);
            }
        }
    }

    private boolean isDietAsigned(String dietName) {
        for(Customer customer:customerList){
            for(Days key:customer.getDietList().keySet()){
                if(dietName.equalsIgnoreCase(customer.getDietList().get(key))){
                    return true;
                }
            }
        }
        return false;
    }

    private String getSelectedDiet(){
        if(dietList.size()==0){
            System.out.println("No existen dietas a seleccionar");
            return null;
        }
        System.out.println("Lista de dietas:");
        Integer i=1;
        List<String> options = new ArrayList<>();
        for(String key:dietList.keySet()){
            options.add(key);
            System.out.println((i)+"-"+key);
            i++;
        }
        System.out.println((i)+"-Salir");
        Integer selected = Kb.getOption(1,i);
        if(selected == i){
            return null;
        }
        return options.get(selected-1);
    }

    private void customerManager() {
        Integer option = null;
        do{
            System.out.println("1-Añadir un paciente");
            System.out.println("2-Listar pacientes");
            System.out.println("3-Eliminar un paciente");
            System.out.println("4-Salir");
            option = Kb.getOption(1,4);
            switch (option){
                case 1: addCustomer();
                    break;
                case 2: showCustomers();
                    break;
                case 3: deleteCustomer();
                    break;
            }
        }while(option!=4);
    }

    private void deleteCustomer() {
        Customer selected = getSelectedCustomer();
        if(selected==null){
            System.out.println(OPERATION_CANCLLED_MSG);
        }else{
            if(customerList.remove(selected)){
                System.out.println("Cliente eliminado con éxito");
            }
        }
    }

    private void showCustomers() {
        Customer selected = getSelectedCustomer();
        if(selected==null){
            System.out.println(OPERATION_CANCLLED_MSG);
        }
        Integer option = null;
        do{
            showCustomerDetails(selected);
            System.out.println("1-Cambiar Nombre");
            System.out.println("2-Cambiar Apellidos");
            System.out.println("3-Cambiar Peso");
            System.out.println("4-Cambiar Altura");
            System.out.println("5-Cambiar Edad");
            System.out.println("6-Cambiar Sexo");
            System.out.println("7-Agregar Dieta");
            System.out.println("8-Eliminar Dieta");
            System.out.println("9-Salir");
            option = Kb.getOption(1,9);
            switch (option){
                case 1: updateCustomer(selected, CustomerField.NAME);
                    break;
                case 2: updateCustomer(selected, CustomerField.SURNAME);
                    break;
                case 3: updateCustomer(selected, CustomerField.WEIGHT);
                    break;
                case 4: updateCustomer(selected, CustomerField.HEIGHT);
                    break;
                case 5: updateCustomer(selected, CustomerField.AGE);
                    break;
                case 6: updateCustomer(selected, CustomerField.GENDER);
                    break;
                case 7: addDietToCustomer(selected);
                    break;
                case 8: deleteDietToCustomer(selected);
                    break;
            }
        }while(option!=9);
    }

    private void deleteDietToCustomer(Customer selected) {
        System.out.println("Seleccione un día de la semana a eliminar");
        System.out.println("1-Lunes");
        System.out.println("2-Martes");
        System.out.println("3-Miércoles");
        System.out.println("4-Jueves");
        System.out.println("5-Viernes");
        System.out.println("6-Sábado");
        System.out.println("7-Domingo");
        System.out.println("8-Salir");
        Integer day = Kb.getOption(1,8);
        if(day==8){
            return;
        }
        Days key = Days.getDayFromPosition(day);
        selected.getDietList().remove(key);
    }

    private void addDietToCustomer(Customer selected) {
        System.out.println("Seleccione un día de la semana");
        System.out.println("1-Lunes");
        System.out.println("2-Martes");
        System.out.println("3-Miércoles");
        System.out.println("4-Jueves");
        System.out.println("5-Viernes");
        System.out.println("6-Sábado");
        System.out.println("7-Domingo");
        System.out.println("8-Salir");
        Integer day = Kb.getOption(1,8);
        if(day==8){
            return;
        }
        String dietName = getSelectedDiet();
        selected.getDietList().put(Days.getDayFromPosition(day),dietName);
    }

    private void updateCustomer(Customer customer, CustomerField field) {
        System.out.println("Escriba un nuevo valor");
        if(field.isText()){
            String newValue = Kb.nextLine();
            switch (field){
                case NAME: customer.setName(newValue);
                    break;
                case SURNAME: customer.setSurname(newValue);
                    break;
                case GENDER: customer.setGender(newValue);
                    break;
            }
        }else{
            Integer newValue = Kb.nextInt();
            switch (field){
                case WEIGHT: customer.setWeight(newValue);
                    break;
                case HEIGHT: customer.setHeigth(newValue);
                    break;
                case AGE: customer.setAge(newValue);
                    break;
            }
        }
    }

    private void showCustomerDetails(Customer customer) {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Datos del paciente "+customer.getName());
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Lista de dietas");
        listDietDays(customer.getDietList());
        System.out.println("Nombre: "+customer.getName());
        System.out.println("Apellidos: "+customer.getSurname());
        System.out.println("Altura: "+customer.getHeigth());
        System.out.println("Peso: "+customer.getWeight());
        System.out.println("Edad: "+customer.getAge());
        String gender = "m".equalsIgnoreCase(customer.getGender()) ? "Mujer" : "Hombre";
        System.out.println("Sexo: "+gender);
    }

    private void addCustomer() {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Escriba los datos personales del paciente");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Nombre");
        String name = Kb.nextLine();
        System.out.println("Apellidos");
        String surname = Kb.nextLine();
        System.out.println("Peso:");
        Integer weight = Kb.forceNextInt();
        System.out.println("Altura:");
        Integer height = Kb.forceNextInt();
        System.out.println("Edad:");
        Integer age = Kb.forceNextInt();
        System.out.println("Mujer u Hombre(m/h):");
        String sexCharacter = Kb.nextLine();
        Customer newCustomer = new Customer(name,surname,weight,height,age,sexCharacter);
        customerList.add(newCustomer);
    }

    private Customer getSelectedCustomer(){
        System.out.println("Escoja un paciente:");
        Integer i=1;
        for(Customer customer:customerList){
            System.out.println(i+"-"+customer.getName());
            i++;
        }
        System.out.println(i+"-Salir");
        Integer selected = Kb.getOption(1,i);
        if(selected == i){
            return null;
        }else{
            return customerList.get(selected-1);
        }
    }

    private void addFoodMenu(Diet updateDiet) {
        if(updateDiet==null){
            System.out.println("Para agregar alimentos hace falta iniciar una dieta");
            return;
        }
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Agregar Alimentos a la dieta");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Escriba una opción:");
        System.out.println("===================================");
        System.out.println("1-Agregar un nuevo alimento");
        System.out.println("2-Agregar un alimento ya existente");

        Integer option = Kb.getOption(1,2);
        switch (option){
            case 1:
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Datos de nuevo alimento");
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Nombre del alimento:");
                String name = Kb.nextLine();
                System.out.println("Carbohidratos:");
                Integer carbs = Kb.forceNextInt();
                System.out.println("Grasas:");
                Integer fats = Kb.forceNextInt();
                System.out.println("Proteínas:");
                Integer proteins = Kb.forceNextInt();
                System.out.println("Gramos:");
                Integer grams = Kb.forceNextInt();
                Food newFood = new Food(name,carbs,fats,proteins);
                validateAndAddFoodToDiet(updateDiet,newFood,grams);
                foodList.add(newFood);
                break;
            case 2:
                if(foodList.size()==0){
                    System.out.println("Para agregar un alimento existente, tienen que existir alimentos previos");
                    return;
                }
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Escoja un alimento");
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                Integer i = 1;
                for(Food food:foodList){
                    System.out.println(i+"- "+food.getName());
                    i++;
                }
                System.out.println(i+"- Cancelar");
                Integer element = Kb.getOption(1,i);
                if(element==i){
                    System.out.println(OPERATION_CANCLLED_MSG);
                    return;
                }
                Food storedFood = foodList.get(element-1);
                System.out.println("indique el número de gramos de "+storedFood.getName());
                Integer foodGrams = Kb.forceNextInt();
                validateAndAddFoodToDiet(updateDiet,storedFood,foodGrams);
                break;
        }
    }

    private void validateAndAddFoodToDiet(Diet updateDiet,Food food, Integer grams){
        try{
            updateDiet.addFood(food,grams);
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
    }

    private void createMenu() {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Crear dieta");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("Escriba un nombre para la dieta");
        String dietName = null;
        boolean dietExists = false;
        do{
            dietName = Kb.nextLine();
            dietExists = dietList.containsKey(dietName);
            if(dietExists){
                System.out.println("El nombre de la dieta ya existe, escriba otro");
            }
        }while(dietExists);
        System.out.println("Escriba una opción:");
        System.out.println("===================================");
        System.out.println("1-Dieta sin límite");
        System.out.println("2-Dieta por calorías");
        System.out.println("3-Dieta por macronutrientes");
        System.out.println("4-Dieta por datos personales");
        System.out.println("5-Dieta por datos de paciente");
        Integer option = Kb.getOption(1,5);
        switch (option){
            case 1:
                dietList.put(dietName,new Diet());
                System.out.println("Se ha creado una dieta sin límites");
                break;
            case 2:
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Escriba número de calorias");
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                Integer calories = Kb.forceNextInt();
                dietList.put(dietName,new CaloriesDiet(calories));
                System.out.println("Se ha creado una dieta con "+calories+" calorías máximas");
                break;
            case 3:
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Escriba los macronutrientes");
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Carbohidratos:");
                Integer carbs = Kb.forceNextInt();
                System.out.println("Grasas:");
                Integer fats = Kb.forceNextInt();
                System.out.println("Proteínas:");
                Integer proteins = Kb.forceNextInt();
                dietList.put(dietName,new MacroDiet(fats,carbs,proteins));
                System.out.println("Se ha creado una dieta con Carbohidratos:"+carbs+", Grasas:"+fats+" ,Proteínas:"+proteins);
                break;
            case 4:
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Escriba los datos personales del paciente");
                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
                System.out.println("Peso:");
                Integer weight = Kb.forceNextInt();
                System.out.println("Altura:");
                Integer height = Kb.forceNextInt();
                System.out.println("Edad:");
                Integer age = Kb.forceNextInt();
                System.out.println("Mujer u Hombre(m/h):");
                String sexCharacter = Kb.nextLine();
                CaloriesDiet newDiet = new CaloriesDiet("m".equalsIgnoreCase(sexCharacter),age,height,weight);
                dietList.put(dietName,newDiet);
                System.out.println("Se ha creado una dieta de "+newDiet.getMaxCalories()+" calorías máximas");
                break;
            case 5:
                Customer customer = getSelectedCustomer();
                CaloriesDiet customerDiet = new CaloriesDiet("m".equalsIgnoreCase(customer.getGender()),customer.getAge(),customer.getHeigth(),customer.getWeight());
                dietList.put(dietName,customerDiet);
                System.out.println("Se ha creado una dieta de "+customerDiet.getMaxCalories()+" calorías máximas");
                break;
        }
    }

    private void listDietDays(Map<Days,String> map){
        System.out.println("######################################");
        System.out.println("Dieta semanal");
        System.out.println("######################################");
        for(int i=1;i<8;i++){
            Days day = Days.getDayFromPosition(i);
            if(map.containsKey(day)){
                if(day == Days.D){
                    System.out.println(day.getName()+"* - "+map.get(day));
                }else{
                    System.out.println(day.getName()+" - "+map.get(day));
                }

            }
        }
        System.out.println("######################################");
    }

    public void addDiet(String name, Diet diet){
        dietList.put(name,diet);
    }

    public void addCustomer(Customer customer){
        customerList.add(customer);
    }
}
