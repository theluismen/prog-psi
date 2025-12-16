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
    public static void main(String[] args){
        System.out.println("Juego de pruebas de LlistaActivitats\n");

        //testConstructorAfegir();
        //testExisteix();
        testGuardarLlista();
        
    }

    private static void testConstructorAfegir(){
        System.out.println("Test del constructor, del afegir, del toString y del metodo privado \"amplia\"\n");
        Data dataIni = new Data(10, 1, 2025, 10, 0);
        Data dataIniIns = new Data(1, 1, 2025);
        Data dataFiIns = new Data(20, 1, 2025);
        ActivitatPeriodica act = new ActivitatPeriodica("ioga", new String[]{"PDI", "PTGAS"}, dataIniIns, dataFiIns,
                                    DiaSetmana.DILLUNS, 1.5, dataIni, 8, 20, 50.0,
                                    "Centre Blau", "Barcelona");
        ActivitatUnDia act1 = new ActivitatUnDia("Excursión al Delta", 
                                                new String[]{"PDI", "Estudiant"}, 
                                                dataIniIns, 
                                                dataFiIns, 
                                                dataIni, 
                                                25, 
                                                11, 
                                                "Amposta");
        ActivitatOnline act2 = new ActivitatOnline("Curs de Java Avançat", new String[]{"PDI", "PTGAS"}, dataIniIns, 
                                                        dataFiIns, dataIni, 30, "https://campus.urv.cat/curs-java");
        LlistaActivitats l = new LlistaActivitats(1);
        l.afegir(act); //Se añaden desordenados alfabeticamente, el orden correcto seria act2 - act1 - act
        l.afegir(act1);
        l.afegir(act2);
        System.out.println(l);
    }

    private static void testExisteix(){
        System.out.println("Test del metode existeix per saber si una activitat ja hi es a la llista\n");
        Data dataIni = new Data(10, 1, 2025, 10, 0);
        Data dataIniIns = new Data(1, 1, 2025);
        Data dataFiIns = new Data(20, 1, 2025);
        ActivitatPeriodica act = new ActivitatPeriodica("ioga", new String[]{"PDI", "PTGAS"}, dataIniIns, dataFiIns,
                                    DiaSetmana.DILLUNS, 1.5, dataIni, 8, 20, 50.0,
                                    "Centre Blau", "Barcelona");
        ActivitatUnDia act1 = new ActivitatUnDia("Excursión al Delta", 
                                                new String[]{"PDI", "Estudiant"}, 
                                                dataIniIns, 
                                                dataFiIns, 
                                                dataIni, 
                                                25, 
                                                11, 
                                                "Amposta");
        LlistaActivitats l = new LlistaActivitats(1);
        l.afegir(act);
        System.out.println("Cas que no hi es:");
        System.out.println(l.existeix(act1.getNom()));
        System.out.println("\nCas que ja hi es:");
        System.out.println(l.existeix(act.getNom()));
    }

    private static void testGuardarLlista(){
        System.out.println("Test del metodo para guardar la lista en un fichero\n");
        Data dataIni = new Data(10, 1, 2025, 10, 0);
        Data dataIniIns = new Data(1, 1, 2025);
        Data dataFiIns = new Data(20, 1, 2025);
        ActivitatPeriodica act = new ActivitatPeriodica("ioga", new String[]{"PDI", "PTGAS"}, dataIniIns, dataFiIns,
                                    DiaSetmana.DILLUNS, 1.5, dataIni, 8, 20, 50.0,
                                    "Centre Blau", "Barcelona");
        ActivitatOnline act2 = new ActivitatOnline("Curs de Java Avançat", new String[]{"PDI", "PTGAS"}, dataIniIns, 
                                                        dataFiIns, dataIni, 30, "https://campus.urv.cat/curs-java");
        LlistaActivitats l = new LlistaActivitats(1);
        l.afegir(act);
        l.afegir(act2);
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
