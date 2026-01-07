/**
 * Autor(@s): Alexandra Núñez González i Ainara Sofía Cabrera Robles
 * Descripción: Test per a la classe LlistaActivitat.
 */
package tests;

import llistes.LlistaActivitats;
import activitats.*;
import excepcions.*;
import extras.*;

public class UsaLlistaActivitat {
    public static void main(String[] args) {
        // Crear lista con tamaño inicial
        LlistaActivitats llista = new LlistaActivitats(1);

        // Ruta del fitxer a carregar (ajusta si es necesario)
        String rutaFitxer = "grup4_Pract3/src/fitxers/activitats.txt";

        // Carreguem les activitats
        System.out.println("Carregant activitats des del fitxer...");
        llista.carregarFitxer(rutaFitxer);

        // Mostrem el contingut carregat
        System.out.println("\n--- Activitats carregades ---");
        System.out.println(llista);
        System.out.println("--- Total: " + llista.getNumElements() + " activitats ---");

        //proves de les funcions
        testCerca(llista);
        provarEliminar(llista);
        provarTipus(llista);
        provarClasseAvui(llista);
        guardarFitxer(llista);
    };

    /* PROBAR BÚSQUEDA */
    private static void testCerca(LlistaActivitats llista) {
        System.out.println("\n=== PROVA CERCA ===");
        try {
            Activitat a = llista.cerca("ioga");
            System.out.println("Activitat trobada:\n" + a);
        } catch (ActivitatDesconeguda e) {
            System.out.println(e.getMessage());
        }
    }

    /* PROBAR ELIMINAR */
    private static void provarEliminar(LlistaActivitats llista) {
        System.out.println("\n=== PROVA ELIMINAR ===");
        try {
            llista.eliminar("ioga"); // cambia por un nombre real
            System.out.println("Activitat eliminada correctament");
        } catch (ActivitatDesconeguda e) {
            System.out.println(e.getMessage());
        }

        System.out.println(llista);
    }

    /* PROBAR FILTRO POR TIPO */
    private static void provarTipus(LlistaActivitats llista) {
        System.out.println("\n=== PROVA LLISTA PER TIPUS ===");
        try {
            LlistaActivitats periodiques = llista.tipusLlista("Activitat periodica");
            System.out.println("ACTIVITATS PERIÒDIQUES:");
            System.out.println(periodiques);
        } catch (ActivitatDesconeguda e) {
            System.out.println(e.getMessage());
        }
    }

    /* PROBAR CLASE HOY */
    private static void provarClasseAvui(LlistaActivitats llista) {
        try{
            System.out.println("\n=== PROVA CLASE AVUI ===");
            Data avui = new Data(30, 9, 2025);
            LlistaActivitats avuiLlista = llista.claseAvui(avui);
            System.out.println(avuiLlista);
        }catch (Exception e){
            System.out.println("error desconegut");
        }
    }

    /* GUARDAR EN FITXER */
    private static void guardarFitxer(LlistaActivitats llista) {
        System.out.println("\n=== GUARDAR LLISTA ===");
        llista.guardarLlista("grup4_Pract3/src/fitxers/activitats.txt");
        System.out.println("Fitxer guardat correctament");
    }
}
