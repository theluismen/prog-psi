/**
 * Autor: Ikram Hallouz
 * Descripción: Test per a la classe ActivitatOnline
 */

package tests;

import activitats.ActivitatOnline;
import extras.Data;
import excepcions.CollectiuDesconegut;
import excepcions.ValorInexistent;

public class UsaActivitatOnline {
    public static void main(String[] args) {
        
        try {
            // --- INICI DEL BLOC PROTEGIT (TRY) ---

            // Crear les dates necessàries (Això pot fallar, per això està dins del try)
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
            // També hem de protegir la creació d'aquesta nova data
            Data dataProva = new Data(5, 10, 2025);
            System.out.println("Està activa el " + dataProva + "? " + activitat.estaActiva(dataProva));

            // Comprovar el mètode copia
            ActivitatOnline copia = activitat.copia();
            System.out.println("Nom de la còpia: " + copia.getNom());

            } catch (ValorInexistent e) {
                System.out.println("Error de dates: " + e.getMessage());
            } catch (CollectiuDesconegut e) {  // <--- AFEGIR AQUEST CATCH
                System.out.println("Error de col·lectiu: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Altre error: " + e.getMessage());
        }
    }
}