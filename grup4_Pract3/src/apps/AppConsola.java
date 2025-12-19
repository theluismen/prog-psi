/**
 * Autor(@s):   Ainara Sofia Cabrera Robles
                Ikram Kheira Hallouz Safa
                Aesha Naz Mahmood Bibi
                Alexandra Núñez González
 * Descripción: 
 */
package apps;

import java.util.Scanner;
import java.io.*;

// Imports de les teves classes
import llistes.*;
import inscripcions.*;
import usuaris.*;
import activitats.*;
import enumeraciones.*;
import excepcions.*;
import extras.Data;

public class AppConsola {

    // 1. RUTES DELS FITXERS
    private static final String FITXER_INSCRIPCIONS = "src/fitxers/inscripcions.dat";
    private static final String FITXER_USUARIS = "src/fitxers/usuaris.txt";
    private static final String FITXER_ACTIVITATS = "src/fitxers/activitat.txt";

    // 2. VARIABLES GLOBALS (LLISTES)
    private static LlistaInscripcio llistaInscripcions;
    private static LlistaUsuaris llistaUsuaris;
    private static LlistaActivitats llistaActivitats;
    
    // Eines globals
    private static Scanner teclat = new Scanner(System.in);
    private static Data dataActual;

    public static void main(String[] args) {
        
        // --- INICIALITZACIÓ ---
        boolean sortir = false;
        int opcio = 0;
        try{
            dataActual = new Data(1, 9, 2025); // Data inicial per defecte
        }catch(ValorInexistent e){  //es obligatori tractar l'excepció, però com que es un valor per defecte no donarà error
            System.out.println("La data no existeix");
        }

        // --- CÀRREGA DE DADES ---
        carregarDadesSistema();

        // --- BUCLE PRINCIPAL ---
        while (!sortir) {
            mostrarMenu();
            opcio = llegirEnter();

            switch (opcio) {
                // --- GESTIÓ BÀSICA ---
                case 1:
                    // TODO: Indicar/Canviar la data del sistema
                    break;
                case 2:
                    // TODO: Mostrar les dades de les llistes
                    /*Mostrar les dades de les llistes. Es demanarà de quina llista es vol mostrar la informació. En el
                    cas de llistes amb diferents tipus d’elements, per exemple usuaris de diferents col·lectius, es
                    demanarà també si es volen mostrat tots o només els d’un tipus. Aplica també a les activitats.*/
                    
                    
                    break;

                // --- CONSULTES ---
                case 3:
                    // TODO: Activitats en període d'inscripció amb places disponibles
                    break;
                case 4:
                    // TODO: Activitats que tenen classe AVUI
                    break;
                case 5:
                    // TODO: Activitats actives AVUI (dins període)
                    break;
                case 6:
                    // TODO: Activitats amb places disponibles (qualsevol data)
                    break;
                case 7:
                    // TODO: Detall d'una activitat pel seu nom
                    break;
                case 8:
                    // TODO: Detall d'un usuari pel seu àlies
                    break;
                case 9:
                    // TODO: Activitats on està apuntat un usuari
                    break;

                // --- GESTIÓ D'INSCRIPCIONS ---
                case 10:
                    // TODO: Inscriure's a una activitat
                    break;
                case 11:
                    // TODO: Mostrar usuaris apuntats a una activitat i llista d'espera
                    break;
                case 12:
                    // TODO: Eliminar un usuari d'una activitat
                    break;

                // --- ALTES D'ACTIVITATS ---
                case 13:
                    // TODO: Afegir nova activitat d'un dia
                    break;
                case 14:
                    // TODO: Afegir nova activitat periòdica
                    break;
                case 15:
                    // TODO: Afegir nova activitat online
                    break;

                // --- VALORACIONS I ESTADÍSTIQUES ---
                case 16:
                    // TODO: Valorar una activitat
                    break;
                case 17:
                    // TODO: Resum de valoracions d'activitats acabades
                    break;
                case 18:
                    // TODO: Resum valoracions fetes per un usuari
                    break;
                case 19:
                    // TODO: Mitjana valoracions per col·lectiu
                    break;
                case 20:
                    // TODO: Usuari més actiu d'un col·lectiu
                    break;

                // --- MANTENIMENT ---
                case 21:
                    // TODO: Donar de baixa activitats amb poca participació
                    break;

                // --- SORTIDA ---
                case 22:
                    sortir = true;
                    gestionarSortida();     // --- TANCAMENT I GUARDAT ---
                    break;

                default:
                    System.out.println("Opció no vàlida.");
            }
        }
    }

    // MÈTODES AUXILIARS BÀSICS

    private static void carregarDadesSistema() {
        System.out.println("Carregant dades...");
        // Inicialització segura per evitar errors si els fitxers no existeixen
        llistaInscripcions = LlistaInscripcio.carregarFitxer(FITXER_INSCRIPCIONS);
        llistaUsuaris = new LlistaUsuaris(); 
        // llistaUsuaris.carregarFitxer(FITXER_USUARIS);
        llistaActivitats = new LlistaActivitats(100);
        // llistaActivitats.carregarFitxer(FITXER_ACTIVITATS);
        
        System.out.println("Sistema inicialitzat correctament.");
    }

    private static void gestionarSortida() {
        System.out.print("\nVols guardar els canvis abans de sortir? (S/N): ");
        String resp = teclat.nextLine();
        boolean res = false;
        while (!res){
            if (resp.equalsIgnoreCase("S")) {
                System.out.println("Guardant dades...");
                try {
                    llistaInscripcions.guardarFitxer(FITXER_INSCRIPCIONS);
                    // llistaUsuaris.guardarFitxer(FITXER_USUARIS);
                    llistaActivitats.guardarLlista(FITXER_ACTIVITATS);
                    System.out.println("Dades guardades.");
                    res = true;
                } catch (Exception e) {
                    System.out.println("Error guardant: " + e.getMessage());
                    System.out.println("Torna-ho a probar");
                }
            }else if (resp.equalsIgnoreCase("N")){
                res = true;
            }else{
                System.out.println("\nL'opció introduida no existeix, el format ha de ser S o N");
            }
            System.out.println("Adéu!");
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n--- MENÚ PRINCIPAL ---");
        System.out.println("1. Consulta la data d'avui | 2. Mostrar les llistes");
        System.out.println("10. Inscriure | 13. Nova Activitat");
        System.out.println("22. Sortir");
        System.out.print("Opció: ");
    }

    private static int llegirEnter() {
        try {
            return Integer.parseInt(teclat.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /*Mostrar les dades de les llistes. Es demanarà de quina llista es vol mostrar la informació. En el
            cas de llistes amb diferents tipus d’elements, per exemple usuaris de diferents col·lectius, es
            demanarà també si es volen mostrat tots o només els d’un tipus. Aplica també a les activitats.*/
    private static void case2(){
        System.out.println("\nQuina llista vols veure?");
        System.out.println("\t(opció 1: Llista d'activitats)\n\t(opció 2: Llista d'usuaris)\n\t(opció 3: Llista d'inscripcions)");
    }


}