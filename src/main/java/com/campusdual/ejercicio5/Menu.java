package com.campusdual.ejercicio5;

//--Teniendo en cuenta el programa anterior(ejercicio4):
//--Realiza los cambios necesarios para:
//  -La dieta ya no será única si no que se guardará en una lista de dietas. Esta lista tendrá un nombre para poder ser recuperada.
//      Será necesario cambiar el menú para gestionar esta lista. Ahora en ved de crear/reemplazar dieta lo que haremos será:
//    a- Agregar dieta: Añade una dieta a la lista. (Podemos reutilizar el menú antiguo de crear/reemplazar)
//    b- Mostrar detalles de dieta: Muestra los detalles de una dieta y permite modificarla
//    c- Eliminar dieta: Elimina una dieta de la lista(Siempre que no la tenga asignada un paciente)
//
//  -Se pueden dar de alta personas: Las personas tendrán los siguientes atributos: nombre, apellidos, peso, altura, edad y sexo.
//    -Las personas podrán tener asignadas una lista de dietas que se catalogarán de lunes a domingo sacadas de la lista de dietas anterior.
//    -Se agregara un apartado nuevo al menú de "Gestión de pacientes" con las siguientes funcionalidades:
//      1- Dar de alta un paciente : Agregará un paciente nuevo a la lista de pacientes
//      2- Listar y Mostrar detalles de un paciente : Mostrará el detalle de un paciente así como todas sus dietas listadas de Lunes a domingo y permite modificar sus datos y asignar dietas
//      3- Dar de baja un paciente: Elimina los datos de un paciente(No borra sus dietas asignadas dado que pueden estar asignadas a otro paciente)

import com.campusdual.ejercicio5.model.CaloriesDiet;
import com.campusdual.ejercicio5.model.Customer;
import com.campusdual.ejercicio5.model.Diet;
import com.campusdual.ejercicio5.model.MacroDiet;

public class Menu {
    public static void main(String[] args) {
        DietProgram program = new DietProgram();
        program.addDiet("Dieta sin límite",new Diet());
        program.addDiet("Dieta 2000",new CaloriesDiet(2000));
        program.addDiet("Dieta equilibrada",new MacroDiet(100,200,120));
        program.addCustomer(new Customer("Juanjo","Alvarez",80,173,44,"h"));
        program.addCustomer(new Customer("Arale","Slump",120,145,5,"m"));
        program.addCustomer(new Customer("André","El Gigante",200,210,67,"h"));
        program.showMenuProgram();
    }
}
