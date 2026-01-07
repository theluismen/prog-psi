/**
 * Autor: Ikram Hallouz
 * Descripción: Clase que representa la classe de test per a la Inscripció.
 */
package tests;

import inscripcions.Inscripcio;
import llistes.LlistaInscripcions;
import excepcions.InscripcioDuplicada;
import excepcions.InscripcioNoTrobada;

public class UsaLlistaInscripcions {
    public static void main(String[] args) {
        System.out.println("-------------------------------------------------");
        System.out.println(" INICI TEST LLISTA INSCRIPCIONS (AMB OBJECTES LLISTA)");
        System.out.println("-------------------------------------------------");

        // 1. CREACIÓ
        LlistaInscripcions llista = new LlistaInscripcions();
        System.out.println("Llista creada correctament.");

        // 2. AFEGIR (Happy Path)
        System.out.println("\n>>> 2. Afegint inscripcions bàsiques...");
        try {
            llista.afegirInscripcio(new Inscripcio("mar.garcia", "Java"));
            llista.afegirInscripcio(new Inscripcio("pere.pi", "Python"));
            llista.afegirInscripcio(new Inscripcio("mar.garcia", "Python")); // Mateix usuari, altra activitat
            System.out.println("3 inscripcions afegides correctament.");
        } catch (InscripcioDuplicada e) {
            System.out.println("Error inesperat: " + e.getMessage());
        }

        // 3. PROVAR DUPLICATS (Excepció)
        System.out.println("\n>>> 3. Provant d'afegir duplicat...");
        try {
            llista.afegirInscripcio(new Inscripcio("mar.garcia", "Java"));
            System.out.println("ERROR: Ha deixat afegir un duplicat!");
        } catch (InscripcioDuplicada e) {
            System.out.println("CORRECTE: S'ha capturat l'error -> " + e.getMessage());
        }

        // 4. CONSULTES
        System.out.println("\n>>> 4. Consultes:");
        System.out.println("Quants inscrits a 'Python'? (Esperat: 2) -> " + llista.comptarInscripcionsActivitat("Python"));
        
        System.out.println("Inscripcions de 'mar.garcia':");
        // CANVI: Ara retorna un objecte LlistaInscripcions, no un array
        LlistaInscripcions deLaMar = llista.getInscripcionsUsuari("mar.garcia");
        
        // CANVI: Bucle usant mètodes de la classe llista
        for (int i = 0; i < deLaMar.getNumElements(); i++) {
            Inscripcio ins = deLaMar.getInscripcioIesima(i);
            System.out.println(" - " + ins.getIdActivitat());
        }

        // 5. ELIMINAR
        System.out.println("\n>>> 5. Eliminant inscripció...");
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
        System.out.println("\n>>> 6. Eliminant inscripció fantasma...");
        try {
            llista.eliminarInscripcio("ningu", "res");
            System.out.println("ERROR: Hauria d'haver fallat.");
        } catch (InscripcioNoTrobada e) {
            System.out.println("CORRECTE: S'ha capturat l'error -> " + e.getMessage());
        }

        // 7. TEST LLISTA D'ESPERA (NOU)
        System.out.println("\n-------------------------------------------------");
        System.out.println(" >>> 7. TEST LLISTA D'ESPERA I AFORAMENT");
        System.out.println("-------------------------------------------------");
        
        // Simulem una activitat anomenada "Taller" amb capacitat per a NOMÉS 2 persones
        String nomActivitat = "Taller";
        int placesMaximes = 2;
        
        System.out.println("Creant escenari: Activitat '" + nomActivitat + "' amb " + placesMaximes + " places.");
        System.out.println("Inscrivim 3 usuaris (u1, u2 i u3)...");

        try {
            // Afegim 3 persones
            llista.afegirInscripcio(new Inscripcio("u1", nomActivitat));
            llista.afegirInscripcio(new Inscripcio("u2", nomActivitat));
            llista.afegirInscripcio(new Inscripcio("u3", nomActivitat)); // Aquest serà el 3r, per tant, hauria de ser excedent (espera)

            // 7.1 Comprovar si la llista detecta que està plena
            boolean plena = llista.activitatEstaPlena(nomActivitat, placesMaximes);
            System.out.println("Activitat plena? (Esperat: true) -> " + plena);

            // 7.2 Comprovar quanta gent hi ha en espera
            int numEspera = llista.getNumEnEspera(nomActivitat, placesMaximes);
            System.out.println("Persones en espera (Esperat: 1) -> " + numEspera);

            // 7.3 Recuperar i mostrar la llista d'ADMESOS
            System.out.println("\n--- LLISTA D'ADMESOS (Haurien de ser u1 i u2) ---");
            // CANVI: Tipus de retorn LlistaInscripcio
            LlistaInscripcions admesos = llista.getAdmesos(nomActivitat, placesMaximes);
            
            for (int i = 0; i < admesos.getNumElements(); i++) {
                Inscripcio ins = admesos.getInscripcioIesima(i);
                System.out.println("ADMÈS: " + ins.getIdUsuari());
            }

            // 7.4 Recuperar i mostrar la llista d'ESPERA
            System.out.println("\n--- LLISTA D'ESPERA (Hauria de ser u3) ---");
            // CANVI: Tipus de retorn LlistaInscripcio
            LlistaInscripcions espera = llista.getLlistaEspera(nomActivitat, placesMaximes);
            
            for (int i = 0; i < espera.getNumElements(); i++) {
                Inscripcio ins = espera.getInscripcioIesima(i);
                System.out.println("ESPERA: " + ins.getIdUsuari());
            }

        } catch (Exception e) {
            System.out.println("Error durant el test d'espera: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n-------------------------------------------------");
        System.out.println(" FI DEL TEST");
        System.out.println("-------------------------------------------------");
    }
}
