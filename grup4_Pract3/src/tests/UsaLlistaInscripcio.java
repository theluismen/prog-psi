package tests;

import inscripcions.Inscripcio;
import llistes.LlistaInscripcio;
import excepcions.InscripcioDuplicada;
import excepcions.InscripcioNoTrobada;

public class UsaLlistaInscripcio {
    public static void main(String[] args) {
        System.out.println("-------------------------------------------------");
        System.out.println(" INICI TEST LLISTA INSCRIPCIONS ");
        System.out.println("-------------------------------------------------");

        // 1. CREACIÓ
        LlistaInscripcio llista = new LlistaInscripcio();
        System.out.println("Llista creada correctament.");

        // 2. AFEGIR (Happy Path)
        System.out.println("\n>>> Afegint inscripcions...");
        try {
            llista.afegirInscripcio(new Inscripcio("mar.garcia", "Java"));
            llista.afegirInscripcio(new Inscripcio("pere.pi", "Python"));
            llista.afegirInscripcio(new Inscripcio("mar.garcia", "Python")); // Mateix usuari, altra activitat
            System.out.println("3 inscripcions afegides correctament.");
        } catch (InscripcioDuplicada e) {
            System.out.println("Error inesperat: " + e.getMessage());
        }

        // 3. PROVAR DUPLICATS (Excepció)
        System.out.println("\n>>> Provant d'afegir duplicat...");
        try {
            llista.afegirInscripcio(new Inscripcio("mar.garcia", "Java"));
            System.out.println("ERROR: Ha deixat afegir un duplicat!");
        } catch (InscripcioDuplicada e) {
            System.out.println("CORRECTE: S'ha capturat l'error -> " + e.getMessage());
        }

        // 4. CONSULTES
        System.out.println("\n>>> Consultes:");
        System.out.println("Quants inscrits a 'Python'? (Esperat: 2) -> " + llista.comptarInscripcionsActivitat("Python"));
        
        System.out.println("Inscripcions de 'mar.garcia':");
        Inscripcio[] deLaMar = llista.getInscripcionsUsuari("mar.garcia");
        for (Inscripcio i : deLaMar) {
            System.out.println(" - " + i.getIdActivitat());
        }

        // 5. ELIMINAR
        System.out.println("\n>>> Eliminant inscripció...");
        try {
            llista.eliminarInscripcio("pere.pi", "Python");
            System.out.println("Eliminat correctament.");
            
            // Verifiquem que ja no hi és
            if (!llista.existeix("pere.pi", "Python")) {
                System.out.println("CORRECTE: Ja no existeix a la llista.");
            }
        } catch (InscripcioNoTrobada e) {
            System.out.println("Error eliminant: " + e.getMessage());
        }

        // 6. ELIMINAR NO EXISTENT
        System.out.println("\n>>> Eliminant inscripció fantasma...");
        try {
            llista.eliminarInscripcio("ningu", "res");
            System.out.println("ERROR: Haura d'haver fallat.");
        } catch (InscripcioNoTrobada e) {
            System.out.println("CORRECTE: S'ha capturat l'error -> " + e.getMessage());
        }

        System.out.println("\n-------------------------------------------------");
        System.out.println(" FI DEL TEST");
        System.out.println("-------------------------------------------------");
    }
}
