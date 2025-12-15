package tests;

import activitats.*;
import enumeraciones.*;
import extras.*;
import llistes.*;

public class UsaLlistaActivitat {
    public static void main(String[] args){
        System.out.println("Juego de pruebas de LlistaActivitats\n");

        //testConstructorAfegir();
        testExisteix();
        
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
        l.afegirActivitat(act); //Se añaden desordenados alfabeticamente, el orden correcto seria act2 - act1 - act
        l.afegirActivitat(act1);
        l.afegirActivitat(act2);
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
        l.afegirActivitat(act);
        System.out.println("Cas que no hi es:");
        System.out.println(l.existeix(act1.getNom()));
        System.out.println("\nCas que ja hi es:");
        System.out.println(l.existeix(act.getNom()));
    }

    private static void
}
