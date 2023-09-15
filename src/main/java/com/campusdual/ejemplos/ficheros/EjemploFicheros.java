package com.campusdual.ejemplos.ficheros;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

public class EjemploFicheros {
    public static void main(String[] args) {
        String line = "";
        //URL readPath = EjemploFicheros.class.getResource("readFile.txt");
        //URL writePath = EjemploFicheros.class.getResource("writeFile.txt");
        File readFile = new File("src/main/resources/com/campusdual/ejemplos/ficheros/readFile.txt");
        File  writeFile = new File("src/main/resources/com/campusdual/ejemplos/ficheros/writeFile.txt");
        try(BufferedReader br = new BufferedReader(new FileReader(readFile));
            PrintWriter pw = new PrintWriter(new FileWriter(writeFile))){
            Integer i=1;
            while((line = br.readLine()) != null){
                //leo mientras no llego al final del fichero
                pw.println("linea "+i+":"+line);
                i++;
            }
            boolean borrado = readFile.delete();
            if(borrado){
                System.out.println("El fichero se ha borrado");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
