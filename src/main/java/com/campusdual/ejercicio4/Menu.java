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
    public static void main(String[] args) {
        DietProgram program = new DietProgram();
        program.showMenuProgram();
    }
}
