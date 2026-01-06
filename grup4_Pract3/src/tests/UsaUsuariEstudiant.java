/**
 * Autor(@s): Ikram Kheira
 * Descripción: Test per a la classe UsuariEstudiant
 */

package tests;

import usuaris.UsuariEstudiant;
import enumeracions.Collectius;

public class UsaUsuariEstudiant {

    public static void main(String[] args) {
        System.out.println("--------------------------------------------------");
        System.out.println("   INICI DEL TEST: UsaUsuariEstudiant");
        System.out.println("--------------------------------------------------");

        // Variable per a les proves
        UsuariEstudiant estudiant = null;

        // ----------------------------------------------------------------
        // PROVA 1: Creació de l'estudiant (Corregit l'error del constructor)
        // ----------------------------------------------------------------
        System.out.println("\n1. PROVA: Creació de l'estudiant i dades bàsiques");
        try {
            // CORRECCIÓ: Ara passem els 5 paràmetres, inclòs Collectius.ESTUDIANT
            estudiant = new UsuariEstudiant("joan.garcia", "joan.garcia", Collectius.ESTUDIANT, "GEI", 2023);

            boolean nomCorrecte = estudiant.getAlies().equals("joan.garcia");
            boolean ensenyamentCorrecte = estudiant.getEnsenyament().equals("GEI");
            
            // Comprovem també que el tipus d'usuari sigui correcte
            boolean tipusCorrecte = estudiant.tipusUsuari().equals("Estudiant");

            if (nomCorrecte && ensenyamentCorrecte && tipusCorrecte) {
                System.out.println("   RESULTAT: CORRECTE (S'ha creat amb les dades correctes)");
            } else {
                System.out.println("   RESULTAT: INCORRECTE (Alguna dada no coincideix)");
            }

        } catch (Exception e) {
            System.out.println("   RESULTAT: INCORRECTE (Error creant l'objecte: " + e.getMessage() + ")");
        }


        // ----------------------------------------------------------------
        // PROVA 2: Funcionament dels Setters (Modificació)
        // ----------------------------------------------------------------
        System.out.println("\n2. PROVA: Modificació de dades (Setters)");
        try {
            // Canviem l'any i l'ensenyament
            estudiant.setAnyInici(2025);
            estudiant.setEnsenyament("GESST");

            if (estudiant.getAnyInici() == 2025 && estudiant.getEnsenyament().equals("GESST")) {
                System.out.println("   RESULTAT: CORRECTE (Dades actualitzades bé)");
            } else {
                System.out.println("   RESULTAT: INCORRECTE (Els setters no han funcionat)");
            }

        } catch (Exception e) {
            System.out.println("   Error inesperat: " + e.getMessage());
        }


        // ----------------------------------------------------------------
        // PROVA 3: Format CSV
        // ----------------------------------------------------------------
        System.out.println("\n3. PROVA: Generació de format CSV");
        try {
            // Verifiquem que el CSV conté les dades clau
            String resultatReal = estudiant.toCSV();

            // Comprovem que contingui les dades modificades (GESST i 2025)
            if (resultatReal.contains("joan.garcia") && resultatReal.contains("GESST") && resultatReal.contains("2025")) {
                System.out.println("   RESULTAT: CORRECTE (El CSV conté les dades clau)");
                System.out.println("      CSV Generat: " + resultatReal);
            } else {
                System.out.println("   RESULTAT: INCORRECTE");
                System.out.println("      Obtingut: " + resultatReal);
            }

        } catch (Exception e) {
            System.out.println("   Error al mètode toCSV: " + e.getMessage());
        }


        // ----------------------------------------------------------------
        // PROVA 4: Còpia de seguretat
        // ----------------------------------------------------------------
        System.out.println("\n4. PROVA: Còpia independent");
        try {
            // 1. Fem la còpia
            UsuariEstudiant copia = estudiant.copia();

            // 2. Modifiquem la còpia (li canviem l'any)
            copia.setAnyInici(2099);

            // 3. Comprovem l'original (que encara hauria de ser 2025)
            if (estudiant.getAnyInici() == 2025) {
                System.out.println("   RESULTAT: CORRECTE (L'original es manté intacte)");
            } else {
                System.out.println("   RESULTAT: INCORRECTE (L'original ha canviat a " + estudiant.getAnyInici() + "!)");
            }

        } catch (Exception e) {
            System.out.println("   Error fent la còpia: " + e.getMessage());
        }


        // ----------------------------------------------------------------
        // PROVA 5: Verificació del Col·lectiu (Herència)
        // ----------------------------------------------------------------
        System.out.println("\n5. PROVA: Verificació del Col·lectiu");
        try {
            // El col·lectiu ha de ser ESTUDIANT
            if (estudiant.getCollectiu() == Collectius.ESTUDIANT) {
                System.out.println("   RESULTAT: CORRECTE (És del col·lectiu ESTUDIANT)");
            } else {
                System.out.println("   RESULTAT: INCORRECTE (Col·lectiu detectat: " + estudiant.getCollectiu() + ")");
            }

        } catch (Exception e) {
            System.out.println("   Error comprovant col·lectiu: " + e.getMessage());
        }

        System.out.println("--------------------------------------------------");
        System.out.println("   FINAL DEL TEST");
        System.out.println("--------------------------------------------------");
    }
}