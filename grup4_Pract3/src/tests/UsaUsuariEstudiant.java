package tests;

import usuaris.UsuariEstudiant;

public class UsaUsuariEstudiant {

    public static void main(String[] args) {
        System.out.println("-------------------------------------------------");
        System.out.println(" INICI TEST USUARI ESTUDIANT");
        System.out.println("-------------------------------------------------");

        try {
            // 1. Creació de l'objecte UsuariEstudiant
            // Constructor: (alies, email, ensenyament, anyInici)
            UsuariEstudiant estudiant = new UsuariEstudiant(
                "mar.garcia",    // Àlies
                "mar.garcia",    // Part esquerra del correu
                "GEI",           // Ensenyament
                2023             // Any d'inici
            );

            // 2. Mostrar la informació completa (Test del toString i dadesExtra)
            System.out.println(">> Dades de l'estudiant creat:");
            System.out.println(estudiant.toString());

            // 3. Verificació específica del Correu (Mètode del pare Usuari)
            String correuEsperat = "mar.garcia@estudiants.urv.cat";
            String correuReal = estudiant.getEmailComplet();
            
            System.out.println(">> Verificació del correu:");
            if (correuReal.equals(correuEsperat)) {
                System.out.println("CORRECTE: El correu és " + correuReal);
            } else {
                System.out.println("ERROR: Esperàvem " + correuEsperat + " però tenim " + correuReal);
            }

            // 4. Verificació dels Getters propis
            System.out.println("\n>> Verificació dades específiques:");
            System.out.println("Ensenyament: " + estudiant.getEnsenyament());
            System.out.println("Any d'inici: " + estudiant.getAnyInici());

        } catch (Exception e) {
            System.out.println("S'ha produït un error inesperat:");
            e.printStackTrace();
        }

        System.out.println("-------------------------------------------------");
        System.out.println(" FI DEL TEST");
        System.out.println("-------------------------------------------------");
    }
}