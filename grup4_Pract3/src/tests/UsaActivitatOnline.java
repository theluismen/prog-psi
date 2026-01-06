/**
 * Autor: Ikram Hallouz
 * Descripción: Test per a la classe ActivitatOnline
 */

package tests;

import activitats.ActivitatOnline;
import enumeracions.Collectius;
import extras.Data;
import excepcions.ValorInexistent;
import excepcions.EnllacIncorrecte;

public class UsaActivitatOnline {

    public static void main(String[] args) {
        System.out.println("--------------------------------------------------");
        System.out.println("   INICI DEL TEST: ActivitatOnline");
        System.out.println("--------------------------------------------------");

        try {
            Data dataAvui = new Data(1, 9, 2025);
            Collectius[] llistaCollectius = {Collectius.ESTUDIANT, Collectius.PDI};

            // ----------------------------------------------------------------
            // PROVA 1: Validació de l'enllaç (URL) amb espais
            // ----------------------------------------------------------------
            System.out.println("\n1. PROVA: URL amb espais");
            try {
                // Això ha de fallar i llançar EnllacIncorrecte
                new ActivitatOnline("Curs Java", llistaCollectius, dataAvui, dataAvui, dataAvui, 10, "urv.cat/ curs");
                
                System.out.println("   RESULTAT: INCORRECTE (Ha creat l'objecte sense error)");

            } catch (EnllacIncorrecte e) { // ARA CAPTUREM LA NOSTRA EXCEPCIÓ
                System.out.println("   RESULTAT: CORRECTE (Ha detectat l'error: " + e.getMessage() + ")");
            } catch (Exception e) {
                System.out.println("   RESULTAT: INCORRECTE (Error diferent de l'esperat: " + e.getClass().getSimpleName() + ")");
            }


            // ----------------------------------------------------------------
            // PROVA 2: Validació de l'enllaç (URL) buit
            // ----------------------------------------------------------------
            System.out.println("\n2. PROVA: URL buida");
            try {
                // Això ha de fallar i llançar EnllacIncorrecte
                new ActivitatOnline("Curs Buit", llistaCollectius, dataAvui, dataAvui, dataAvui, 10, "");
                System.out.println("   RESULTAT: INCORRECTE (Ha creat l'objecte sense error)");

            } catch (EnllacIncorrecte e) { // ARA CAPTUREM LA NOSTRA EXCEPCIÓ
                System.out.println("   RESULTAT: CORRECTE (Ha detectat l'error: " + e.getMessage() + ")");
            } catch (Exception e) {
                System.out.println("   RESULTAT: INCORRECTE (Error diferent de l'esperat)");
            }


            // ----------------------------------------------------------------
            // PROVA 3: Places màximes
            // ----------------------------------------------------------------
            System.out.println("\n3. PROVA: Places il·limitades");
            try {
                ActivitatOnline act = new ActivitatOnline("Curs Bo", llistaCollectius, dataAvui, dataAvui, dataAvui, 10, "urv.cat/java");
                
                if (act.getPlacesMaximes() == Integer.MAX_VALUE) {
                    System.out.println("   RESULTAT: CORRECTE");
                } else {
                    System.out.println("   RESULTAT: INCORRECTE");
                }
            } catch (Exception e) {
                System.out.println("   Error inesperat: " + e.getMessage());
            }

            // ----------------------------------------------------------------
            // PROVA 4: Dates d'activitat (Està activa?)
            // ----------------------------------------------------------------
            System.out.println("\n4. PROVA: Dates");
            try {
                Data diaInici = new Data(10, 1, 2025);
                ActivitatOnline act = new ActivitatOnline("Curs Dates", llistaCollectius, dataAvui, dataAvui, diaInici, 5, "urv.cat");

                Data diaAbans = new Data(9, 1, 2025);
                if (!act.estaActiva(diaAbans)) {
                    System.out.println("   - Dia abans: CORRECTE");
                } else {
                    System.out.println("   - Dia abans: INCORRECTE");
                }

                if (act.estaActiva(diaInici)) {
                    System.out.println("   - Dia inici: CORRECTE");
                } else {
                    System.out.println("   - Dia inici: INCORRECTE");
                }

            } catch (Exception e) {
                System.out.println("   Error en la prova de dates.");
            }

            // ----------------------------------------------------------------
            // PROVA 5: Deep Copy
            // ----------------------------------------------------------------
            System.out.println("\n5. PROVA: Còpia");
            try {
                ActivitatOnline original = new ActivitatOnline("Original", llistaCollectius, dataAvui, dataAvui, dataAvui, 5, "original.com");
                ActivitatOnline copia = original.copia();
                copia.setEnllac("copia.com");

                if (original.getEnllac().equals("original.com")) {
                    System.out.println("   RESULTAT: CORRECTE");
                } else {
                    System.out.println("   RESULTAT: INCORRECTE");
                }
            } catch (Exception e) {
                System.out.println("   Error en la prova de còpia.");
            }

        } catch (ValorInexistent e) {
            System.out.println("Error creant dades inicials.");
        }

        System.out.println("--------------------------------------------------");
        System.out.println("   FINAL DEL TEST");
        System.out.println("--------------------------------------------------");
    }
}