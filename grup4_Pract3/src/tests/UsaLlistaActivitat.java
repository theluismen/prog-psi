package tests;

import llistes.LlistaActivitats;

public class UsaLlistaActivitat {
    public static void main(String[] args) {
        // Crear lista con tamaño inicial
        LlistaActivitats llista = new LlistaActivitats(100);

        // Ruta del fitxer a carregar (ajusta si es necesario)
        String rutaFitxer = "grup4_Pract3/src/fitxers/activitats.txt";

        // Carreguem les activitats
        System.out.println("Carregant activitats des del fitxer...");
        llista.carregarFitxer(rutaFitxer);

        // Mostrem el contingut carregat
        System.out.println("\n--- Activitats carregades ---");
        System.out.println(llista);
        System.out.println("--- Total: " + llista.getNumElements() + " activitats ---");
    }
}
