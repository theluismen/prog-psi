/**
 * Autor: Ikram Hallouz
 * Descripción: Clase que representa la classe de test per a la Inscripció.
 */

package tests;

import java.io.Serializable;

import inscripcions.Inscripcio;

/**
 * Classe de test per validar la funcionalitat de la classe Inscripcio.
 * Comprova el cicle de vida, còpia, excepcions i serialització.
 */
public class UsaInscripcio {

    public static void main(String[] args) {
        System.out.println("-------------------------------------------------");
        System.out.println(" INICI TEST INSCRIPCIÓ (Validació Classe)");
        System.out.println("-------------------------------------------------");


        // 1. TEST DE FUNCIONAMENT CORRECTE 

        System.out.println("\n>>> 1. Creació i Estat Inicial");
        
        // Creació amb dades d'exemple
        Inscripcio ins = new Inscripcio("mar.garcia", "Curs Java Avançat");

        // Verificació de dades
        System.out.println("Usuari (esperat: mar.garcia): " + ins.getIdUsuari());
        System.out.println("Activitat (esperat: Curs Java...): " + ins.getIdActivitat());
        System.out.println("Està valorada? (esperat: false): " + ins.esValorada());

        // Assignació d'una nota vàlida
        System.out.println("\n>>> 2. Assignació de Valoració Vàlida");
        try {
            System.out.println("Assignem una nota de 8...");
            ins.setValoracio(8);
            System.out.println("Nova valoració (esperat: 8.0): " + ins.getValoracio());
            System.out.println("Està valorada? (esperat: true): " + ins.esValorada());
        } catch (Exception e) {
            System.out.println("ERROR: No hauria d'haver fallat amb una nota vàlida.");
        }

        System.out.println("\n>>> 3. Verificació toString()");
        System.out.println(ins.toString());

        // 2. TEST DE ROBUSTESA (CONTROL D'ERRORS I EXCEPCIONS)

        System.out.println(" TEST DE ROBUSTESA (Control d'Excepcions)");
        System.out.println("\n");
        // Cas 2.1: Nota inferior a 0
        System.out.println("\n>>> Intentant posar nota -1 (Hauria de fallar)");
        try {
            ins.setValoracio(-1);
            System.out.println("ERROR GREU: El sistema ha acceptat una nota negativa!");
        } catch (IllegalArgumentException e) {
            System.out.println("CORRECTE: S'ha capturat l'excepció esperada -> " + e.getMessage());
        } catch (Exception e) {
            System.out.println("AVÍS: S'ha capturat una excepció genèrica: " + e.getClass().getSimpleName());
        }

        // Cas 2.2: Nota superior a 10
        System.out.println("\n>>> Intentant posar nota 11 (Hauria de fallar)");
        try {
            ins.setValoracio(11);
            System.out.println("ERROR GREU: El sistema ha acceptat una nota superior a 10!");
        } catch (IllegalArgumentException e) {
            System.out.println("CORRECTE: S'ha capturat l'excepció esperada -> " + e.getMessage());
        } catch (Exception e) {
            System.out.println("AVÍS: S'ha capturat una excepció genèrica.");
        }

        // 3. TEST DE CÒPIA I REFERÈNCIES

        System.out.println(" TEST DE CÒPIA I REFERÈNCIES");
        System.out.println("\n");
        Inscripcio copia = ins.copia();
        
        System.out.println("Objecte original: " + ins);
        System.out.println("Còpia realitzada: " + copia);
        System.out.println("Són el mateix objecte en memòria? (esperat: false): " + (ins == copia));
        // Modifiquem l'original per veure si afecta la còpia
        try {
            ins.setValoracio(5); 
            System.out.println("Hem canviat nota original a 5.");
            System.out.println("Nota de la còpia (esperat: 8.0, inalterada): " + copia.getValoracio());
            
            if (copia.getValoracio() == 8.0 && ins.getValoracio() == 5.0) {
                System.out.println("CORRECTE: La còpia és independent.");
            } else {
                System.out.println("ERROR: Modificar l'original ha afectat la còpia.");
            }
        } catch (Exception e) {
            System.out.println("Error durant el test de còpia.");
        System.out.println("\n");
        }

        // 4. TEST DE REQUISITS TÈCNICS (Serialització)

        System.out.println(" TEST DE REQUISITS TÈCNICS");
        System.out.println("\n");

        // Validar que implementa Serializable (obligatori per inscripcions segons PDF)
        if (ins instanceof Serializable) {
            System.out.println("CORRECTE: La classe Inscripcio implementa Serializable.");
        } else {
            System.out.println("ERROR FATAL: La classe Inscripcio NO és Serializable (Requeriment PDF p.3).");
        }

        System.out.println(" FI DEL TEST");

    }
}