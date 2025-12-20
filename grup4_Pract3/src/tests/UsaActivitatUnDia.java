package tests;

import extras.Data;
import activitats.ActivitatUnDia;   //Importo la clase que quiero testear.

public class UsaActivitatUnDia {
    public static void main(String[] args) {
        System.out.println("\n\n---TEST ActivitatUnDia---");   
        testConstructor();
        testGetters();     
        testCopia();
        testEstaActiva();
        testTipusActivitat();
        testToString();
        testToCSV();
    }

    private static void testConstructor(){
        System.out.println("\n---TEST  CONSTRUCTOR---");
        ActivitatUnDia act1 = new ActivitatUnDia("Excursión al Delta", 
                                                new String[]{"PDI", "Estudiant"}, 
                                                new Data(1,11,2025,23,59), 
                                                new Data(30,11,2025,23,59), 
                                                new Data(5, 12, 2025, 10, 00), 
                                                25, 
                                                11, 
                                                "Amposta");
        System.out.println(act1);
    }

    private static void testGetters(){
        System.out.println("\n\n---TEST GETTERS---");
        ActivitatUnDia act = new ActivitatUnDia("Excursión al Delta", 
                                                new String[]{"PDI", "Estudiant"}, 
                                                new Data(1,11,2025,23,59), 
                                                new Data(30,11,2025,23,59), 
                                                new Data(5, 12, 2025, 10, 00), 
                                                25, 
                                                11, 
                                                "Amposta");
        System.out.println("Hora: " + act.getHora());
        System.out.println("Minuto: " + act.getMinuto());
        System.out.println("Preu: " + act.getPreu());
        System.out.println("Places: " + act.getPlaces());
        System.out.println("Ciutat: " + act.getCiutat());
        System.out.println("Data activitat: " + act.getData());
    }

    private static void testCopia(){
        System.out.println("\n\n---TEST COPIA---");
        ActivitatUnDia original = new ActivitatUnDia("Excursión al Delta", 
                                                new String[]{"PDI", "Estudiant"}, 
                                                new Data(1,11,2025,23,59), 
                                                new Data(30,11,2025,23,59), 
                                                new Data(5, 12, 2025, 10, 00), 
                                                25, 
                                                11, 
                                                "Amposta");

        ActivitatUnDia copia = original.copia();

        System.out.println("\nOriginal: " + original);
        System.out.println("\nCopia: " + copia);
    }

    private static void testEstaActiva(){
        System.out.println("\n\n---TEST ESTA ACTIVA---");
        ActivitatUnDia dia = new ActivitatUnDia("Excursión al Delta", 
                                                new String[]{"PDI", "Estudiant"}, 
                                                new Data(1,11,2025,23,59), 
                                                new Data(30,11,2025,23,59), 
                                                new Data(5, 12, 2025, 10, 00), 
                                                25, 
                                                11, 
                                                "Amposta");
        
        //Dia de otro año.                                       
        Data test1 = new Data(20,9,2023);
        System.out.println("\n¿Activa el " + test1 + "?" + dia.estaActiva(test1));

        //Mismo dia, pero hora previa al comienzo de la actividad.
        Data test2 = new Data(5, 12, 2025,9,00);
        System.out.println("\n¿Activa el " + test2 + "?" + dia.estaActiva(test2));

        //Mismo dia, en hora de actividad.
        Data test3 = new Data(5, 12, 2025,12,00);
        System.out.println("\n¿Activa el " + test3 + "?" + dia.estaActiva(test3));

        //Dia siguiente a la actividad.
        Data test4 = new Data(6, 12, 2025,9,00);
        System.out.println("\n¿Activa el " + test4 + "?" + dia.estaActiva(test4));
    }

    private static void testTipusActivitat(){
        System.out.println("\n\n---TEST TIPUS ACTIVITAT---");
        ActivitatUnDia act = new ActivitatUnDia("Excursión al Delta", 
                                                new String[]{"PDI", "Estudiant"}, 
                                                new Data(1,11,2025,23,59), 
                                                new Data(30,11,2025,23,59), 
                                                new Data(5, 12, 2025, 10, 00), 
                                                25, 
                                                11, 
                                                "Amposta");
        System.out.println("\nTipus: " + act.tipusActivitat());
    }

    private static void testToString() {
        System.out.println("\n\n---TEST toString()---");
        ActivitatUnDia act = new ActivitatUnDia("Excursión al Delta", 
                                                new String[]{"PDI", "Estudiant"}, 
                                                new Data(1,11,2025,23,59), 
                                                new Data(30,11,2025,23,59), 
                                                new Data(5, 12, 2025, 10, 00), 
                                                25, 
                                                11, 
                                                "Amposta");
        System.out.println(act.toString());
    }

    private static void testToCSV(){
        System.out.println("\n\n---TEST toCSV()---");
        ActivitatUnDia act = new ActivitatUnDia("Excursión al Delta", 
                                                new String[]{"PDI", "Estudiant"}, 
                                                new Data(1,11,2025,23,59), 
                                                new Data(30,11,2025,23,59), 
                                                new Data(5, 12, 2025, 10, 00), 
                                                25, 
                                                11, 
                                                "Amposta");
        System.out.println(act.toCSV());
    }
}
