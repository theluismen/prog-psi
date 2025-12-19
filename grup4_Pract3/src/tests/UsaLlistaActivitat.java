/**
 * Autor(@s): Alexandra Núñez y Ainara Sofia Cabreras
 * Descripción: app para probar los metodos de la clase LlistaActivitats
 */
package tests;

import java.io.IOException;

import activitats.*;
import enumeraciones.*;
import extras.*;
import llistes.*;

public class UsaLlistaActivitat {
    
    private static LlistaActivitats l = new LlistaActivitats(0); //cambiar por la leida del fichero en el test correspondiente cuando este hecha
    
    public static void main(String[] args){
        System.out.println("Juego de pruebas de LlistaActivitats\n");

        //testExisteix();
        testGuardarLlista();
        
    }


    //Cuando se haga el test de la leída de ficheros, en el mismo metodo ya se estaran probando el constructor y el añadir (ordenado alfabeticamente)


    private static void testExisteix(){
        System.out.println("Test del metode existeix per saber si una activitat ja hi es a la llista\n");
        
        LlistaActivitats l;
        System.out.println("Cas que no hi es:");
        //System.out.println(l.existeix(act1.getNom()));
        System.out.println("\nCas que ja hi es:");
        //System.out.println(l.existeix(act.getNom()));
    }

    private static void testGuardarLlista(){
        System.out.println("Test del metodo para guardar la lista en un fichero\n");
        
        //se guarda en el fichero enviado por parametro, en nuestra practica es activitat.txt
        //cada vez que se guarda se sobreescribe lo que hay
        //para poder evaluar si funciona hay que abrir el txt
        try{
            l.guardarLlista("activitat.txt");
        }catch (IOException e){
            System.out.println("Ha habido un error en la escritura del fichero");
        }
    }
}
