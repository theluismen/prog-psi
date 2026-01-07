/**
 * Autor(@s): Alexandra Núñez González i Ainara Sofía Cabrera Robles
 * Descripción: Test per a la classe LlistaActivitat.
 */
package tests;

import llistes.LlistaActivitats;
import activitats.*;
import excepcions.*;
import extras.Data;
import enumeracions.*;

public class UsaLlistaActivitat {

    public static void main(String[] args) {

        System.out.println("=== INICI PROVES UNITÀRIES DE LlistaActivitats ===");

        // Creació d'una llista buida (cas base)
        LlistaActivitats llista = new LlistaActivitats(1);

        provaAfegirActivitat(llista);
        provaAfegirDuplicada(llista);
        provaCercaExistenta(llista);
        provaCercaInexistent(llista);
        provaFiltrarPerTipus(llista);
        provaFiltrarTipusInvalid(llista);
        provaEliminarExistenta(llista);
        provaEliminarInexistent(llista);
        provaClaseAvui(llista);
        provaGuardarFitxer(llista);

        System.out.println("=== FI PROVES UNITÀRIES ===");
    }

    /**
     * Prova 1: Afegir una activitat correcta a la llista
     * Cas comprovat: inserció bàsica i increment del nombre d'elements
     */
    private static void provaAfegirActivitat(LlistaActivitats llista) {
        System.out.println("\n[PROVA] Afegir activitat vàlida");

        try {
            Activitat a = new ActivitatOnline(
                    "Ioga Online",
                    new Collectius[]{Collectius.PTGAS},
                    new Data(1, 9, 2025),
                    new Data(10, 9, 2025),
                    new Data(15, 9, 2025),
                    30,
                    "http://ioga.com"
            );

            llista.afegir(a);
            System.out.println("OK → Activitat afegida correctament");
            System.out.println(llista);
        } catch (Exception e) {
            System.out.println("ERROR inesperat");
        }
    }

    /**
     * Prova 2: Afegir una activitat duplicada
     * Cas comprovat: detecció de duplicats per nom
     */
    private static void provaAfegirDuplicada(LlistaActivitats llista) {
        System.out.println("\n[PROVA] Afegir activitat duplicada");

        try {
            Activitat a = new ActivitatOnline(
                    "Ioga Online",
                    new Collectius[]{Collectius.PTGAS},
                    new Data(1, 9, 2025),
                    new Data(10, 9, 2025),
                    new Data(15, 9, 2025),
                    30,
                    "http://ioga.com"
            );

            llista.afegir(a);
            System.out.println("ERROR → No s'ha detectat duplicat");
        } catch (ActivitatDuplicada e) {
            System.out.println("OK → Duplicat detectat correctament");
            System.out.println(llista);
        }catch(Exception e){
            System.out.println("Error inesperat a afegirDuplicada");
        }
    }

    /**
     * Prova 3: Cerca d'una activitat existent
     * Cas comprovat: retorn correcte d'una activitat
     */
    private static void provaCercaExistenta(LlistaActivitats llista) {
        System.out.println("\n[PROVA] Cerca activitat existent");

        try {
            Activitat a = llista.cerca("Ioga Online");
            System.out.println("OK → Activitat trobada: " + a.getNom());
        } catch (ActivitatDesconeguda e) {
            System.out.println("ERROR → No s'ha trobat una activitat existent");
        }
    }

    /**
     * Prova 4: Cerca d'una activitat inexistent
     * Cas comprovat: llançament d'excepció
     */
    private static void provaCercaInexistent(LlistaActivitats llista) {
        System.out.println("\n[PROVA] Cerca activitat inexistent");

        try {
            llista.cerca("Zumba");
            System.out.println("ERROR → No s'ha llançat excepció");
        } catch (ActivitatDesconeguda e) {
            System.out.println("OK → Excepció llançada correctament");
        }
    }

    /**
     * Prova 5: Eliminació d'una activitat existent
     * Cas comprovat: eliminació correcta i reducció de la llista
     */
    private static void provaEliminarExistenta(LlistaActivitats llista) {
        System.out.println("\n[PROVA] Eliminar activitat existent");

        try {
            llista.eliminar("Ioga Online");
            System.out.println("OK → Activitat eliminada");
            System.out.println(llista);
        } catch (ActivitatDesconeguda e) {
            System.out.println("ERROR → No s'ha pogut eliminar");
        }
    }

    /**
     * Prova 6: Eliminació d'una activitat inexistent
     * Cas comprovat: gestió correcta de l'error
     */
    private static void provaEliminarInexistent(LlistaActivitats llista) {
        System.out.println("\n[PROVA] Eliminar activitat inexistent");

        try {
            llista.eliminar("Pilates");
            System.out.println("ERROR → No s'ha llançat excepció");
        } catch (ActivitatDesconeguda e) {
            System.out.println("OK → Excepció detectada correctament");
            System.out.println(llista);
        }
    }

    /**
     * Prova 7: Filtrar activitats per tipus correcte
     * Cas comprovat: filtratge per subclassa
     */
    private static void provaFiltrarPerTipus(LlistaActivitats llista) {
        System.out.println("\n[PROVA] Filtrar per tipus vàlid");
        LlistaActivitats online = null;

        try {
            online = llista.tipusLlista("online");
            System.out.println("OK → Filtrat realitzat");
            System.out.println(online);
        } catch (ActivitatDesconeguda e) {
            System.out.println("ERROR → Tipus vàlid no reconegut");
            System.out.println(online);
        }
    }

    /**
     * Prova 8: Filtrar per tipus incorrecte
     * Cas comprovat: validació del paràmetre tipus
     */
    private static void provaFiltrarTipusInvalid(LlistaActivitats llista) {
        System.out.println("\n[PROVA] Filtrar per tipus invàlid");

        try {
            llista.tipusLlista("esport");
            System.out.println("ERROR → No s'ha llançat excepció");
        } catch (ActivitatDesconeguda e) {
            System.out.println("OK → Tipus invàlid detectat");
        }
    }

    /**
     * Prova 9: Comprovació de classe avui
     * Cas comprovat: detecció d'activitats amb classe en una data
     */
    private static void provaClaseAvui(LlistaActivitats llista) {
        System.out.println("\n[PROVA] Classe avui");
        LlistaActivitats avuiLlista = null;
        try{
            Data avui = new Data(15, 9, 2025);
            avuiLlista = llista.claseAvui(avui);
            System.out.println("OK → Llista generada per la data");
            System.out.println(avuiLlista);
        }catch(Exception e){
            System.out.println("ERROR inesperat a claseAvui");
            System.out.println(avuiLlista);
        }
    }

    /**
     * Prova 10: Guardar llista en fitxer
     * Cas comprovat: persistència bàsica
     */
    private static void provaGuardarFitxer(LlistaActivitats llista) {
        System.out.println("\n[PROVA] Guardar fitxer");

        llista.guardarLlista("activitats_test.txt");
        System.out.println("OK → Fitxer guardat");
    }
}