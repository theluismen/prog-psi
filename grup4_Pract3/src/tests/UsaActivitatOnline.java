/**
 * Autor: Ikram Hallouz
 * Descripción: Test per a la classe ActivitatOnline
 */

package tests;

import activitats.ActivitatOnline;
import extras.Data;

public class UsaActivitatOnline {
    public static void main(String[] args) {
        // Crear les dates necessàries
        Data iniciInscripcio = new Data(1, 9, 2025);
        Data fiInscripcio = new Data(15, 9, 2025);
        Data iniciActivitat = new Data(1, 10, 2025);

        // Crear un array de col·lectius
        String[] collectius = {"Estudiants", "PDI"};

        // Crear una activitat online amb les dades
        ActivitatOnline activitat = new ActivitatOnline(
            "Curs de Java Avançat", 
            collectius, 
            iniciInscripcio, 
            fiInscripcio, 
            iniciActivitat, 
            30, 
            "https://campus.urv.cat/curs-java"
        );

        // Imprimir les propietats de l'activitat
        System.out.println("Nom de l'activitat: " + activitat.getNom());
        System.out.println("Tipus d'activitat: " + activitat.tipusActivitat());
        System.out.println("Data inici activitat: " + activitat.getDataInici());
        System.out.println("Dies de visualització: " + activitat.getPeriodeVisualitzacio());
        System.out.println("Enllaç: " + activitat.getEnllac());
        
        System.out.println("Col·lectius admesos: ");
        for (String col : activitat.getCollectius()) {
            System.out.println(" - " + col);
        }

        // Comprovar si l'activitat està activa en una data concreta
        Data dataProva = new Data(5, 10, 2025);
        System.out.println("Està activa el " + dataProva + "? " + activitat.estaActiva(dataProva));

        // Comprovar el mètode copia
        ActivitatOnline copia = activitat.copia();
        System.out.println("Nom de la còpia: " + copia.getNom());
    }
}