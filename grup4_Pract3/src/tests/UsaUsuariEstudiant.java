/**
 * Autor(@s): Ikram Kheira
 * Descripción: Test per a la classe UsuariEstudiant
 */


package tests;

import usuaris.UsuariEstudiant;

public class UsaUsuariEstudiant {
    public static void main(String[] args) {
        // Crear un usuari estudiant amb dades d'exemple
        UsuariEstudiant estudiant = new UsuariEstudiant(
            "mar.garcia",    // Àlies
            "mar.garcia",    // Part esquerra del correu
            "GEI",           // Ensenyament
            2023             // Any d'inici
        );

        // Imprimir les propietats bàsiques (heretades del pare)
        System.out.println("Àlies: " + estudiant.getAlies());
        System.out.println("Correu complet: " + estudiant.getEmailComplet()); // Verifica la generació del correu
        System.out.println("Col·lectiu: " + estudiant.getCollectiu());

        // Imprimir les propietats específiques d'Estudiant
        System.out.println("Ensenyament: " + estudiant.getEnsenyament());
        System.out.println("Any d'inici: " + estudiant.getAnyInici());

        // Verificar el format textual complet (toString)
        System.out.println("\n--- Informació completa (toString) ---");
        System.out.println(estudiant.toString());

        // Verificar el format CSV (per fitxers)
        System.out.println("\n--- Format CSV ---");
        System.out.println(estudiant.toCSV());

        // Verificar el funcionament de la còpia
        UsuariEstudiant copia = estudiant.copia();
        System.out.println("\n--- Verificació de Còpia ---");
        System.out.println("Àlies de la còpia: " + copia.getAlies());
        System.out.println("És el mateix objecte? " + (estudiant == copia)); // Ha de donar false (són instàncies diferents)
    }
}