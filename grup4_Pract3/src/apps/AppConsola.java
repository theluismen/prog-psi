/**
 * Autor(@s):   Ainara Sofia Cabrera Robles
                Ikram Kheira Hallouz Safa
                Aesha Naz Mahmood Bibi
                Alexandra Núñez González
 * Descripción: 
 */
package apps;

import java.util.Scanner;
import llistes.LlistaInscripcio;
import inscripcions.Inscripcio;
import excepcions.InscripcioDuplicada;

public class AppConsola {

    // Ruta on es guardarà el fitxer. Assegura't que la carpeta 'fitxers' existeix dins de src
    private static final String RUTA_FITXER = "src/fitxers/inscripcions.dat";
    
    // Variables globals de l'aplicació
    private static LlistaInscripcio llistaInscripcions;
    private static Scanner teclat = new Scanner(System.in);

    public static void main(String[] args) {
        
        // 1. CÀRREGA INICIAL 
        // Deleguem a la classe Llista la feina de llegir el fitxer
        carregarDadesSistema();

        // 2. BUCLE D'EXECUCIÓ PRINCIPAL
        boolean sortir = false;
        while (!sortir) {
            mostrarMenu();
            int opcio = llegirEnter();

            switch (opcio) {
                // --- GESTIÓ DE DATA I VISUALITZACIÓ GENERAL ---
                case 1: 
                    // Indicar la data del dia d'avui (simulació) 
                    System.out.println("Funcionalitat: Canviar data sistema (Actual: " + dataActual + ")");
                    // TODO: Demanar nova data i actualitzar variable 'dataActual'
                    break;

                case 2:
                    // Mostrar dades de les llistes (Usuaris, Activitats o Inscripcions)
                    System.out.println("Funcionalitat: Mostrar llistes completes.");
                    // TODO: Preguntar quina llista vol veure i fer el toString() corresponent
                    break;

                // --- CONSULTES D'ACTIVITATS ---
                case 3:
                    // Activitats en període d'inscripció + places disponibles 
                    System.out.println("Funcionalitat: Veure activitats obertes a inscripció.");
                    break;

                case 4:
                    // Activitats que tenen classe AVUI 
                    System.out.println("Funcionalitat: Veure activitats amb classe el dia " + dataActual);
                    break;

                case 5:
                    // Activitats actives AVUI (dins periode inici-fi)
                    System.out.println("Funcionalitat: Veure activitats actives en data " + dataActual);
                    break;

                case 6:
                    // Activitats amb places disponibles (qualsevol data) 
                    System.out.println("Funcionalitat: Veure activitats amb vacants.");
                    break;

                case 7:
                    // Detall d'una activitat pel seu nom 
                    System.out.println("Funcionalitat: Cercar activitat per nom.");
                    break;

                // --- CONSULTES D'USUARIS I INSCRIPCIONS ---
                case 8:
                    // Detall d'un usuari pel seu alies 
                    System.out.println("Funcionalitat: Cercar usuari per àlies.");
                    break;

                case 9:
                    // Activitats on està apuntat un usuari 
                    System.out.println("Funcionalitat: Veure inscripcions d'un usuari.");
                    // TODO: Fer servir llistaInscripcions.getInscripcionsUsuari(alies)
                    break;

                case 10:
                    // Inscriure's a una activitat 
                    System.out.println("Funcionalitat: Nova inscripció.");
                    // TODO: Demanar usuari i activitat, comprovar places i afegir.
                    // Ja tens part d'això fet al mètode 'tractarNovaInscripcio()'
                    tractarNovaInscripcio();
                    break;

                case 11:
                    // Usuaris apuntats a una activitat + Llista d'espera 
                    System.out.println("Funcionalitat: Veure assistents d'una activitat.");
                    // TODO: Fer servir llistaInscripcions.getInscripcionsActivitat(nomAct)
                    break;

                case 12:
                    // Eliminar un usuari d'una activitat (Gestió llista espera)
                    System.out.println("Funcionalitat: Baixa d'inscripció.");
                    // TODO: Cridar llistaInscripcions.eliminarInscripcio(...)
                    break;

                // --- GESTIÓ D'ALTES D'ACTIVITATS ---
                case 13:
                    // Afegir nova activitat d'un dia [
                    System.out.println("Funcionalitat: Crear activitat Jornada/Taller.");
                    break;

                case 14:
                    // Afegir nova activitat periòdica 
                    System.out.println("Funcionalitat: Crear activitat Periòdica.");
                    break;

                case 15:
                    // Afegir nova activitat online 
                    System.out.println("Funcionalitat: Crear activitat Online.");
                    break;

                // --- VALORACIONS I ESTADÍSTIQUES ---
                case 16:
                    // Valorar activitat (si ha acabat i usuari ha assistit) 
                    System.out.println("Funcionalitat: Posar nota a una activitat.");
                    break;

                case 17:
                    // Resum valoracions d'activitats acabades 
                    System.out.println("Funcionalitat: Veure estadístiques d'activitats acabades.");
                    break;

                case 18:
                    // Resum valoracions fetes per un usuari
                    System.out.println("Funcionalitat: Veure valoracions d'un usuari.");
                    break;

                case 19:
                    // Mitjana valoracions per col·lectiu (PDI, PTGAS, Estudiants)
                    System.out.println("Funcionalitat: Comparativa de satisfacció per col·lectius.");
                    break;

                case 20:
                    // Usuari més actiu d'un col·lectiu 
                    System.out.println("Funcionalitat: Trobar l'usuari més participatiu.");
                    break;

                // --- MANTENIMENT AUTOMÀTIC ---
                case 21:
                    // Donar de baixa activitats amb poca participació (<10% o <20 online)
                    System.out.println("Funcionalitat: Cancel·lació automàtica d'activitats.");
                    break;

                // --- SORTIDA ---
                case 22:
                    // Sortir 
                    sortir = true;
                    break;

                default:
                    System.out.println("ATENCIÓ: Opció no vàlida. Tria entre 1 i 22.");
            }
        }

        // 3. TANCAMENT I GUARDAT 
        // Aquí és on preguntem i cridem al mètode de la llista
        gestionarSortida();
    }

    // -----------------------------------------------------------------
    // MÈTODES AUXILIARS
    // -----------------------------------------------------------------

    /**
     * S'encarrega de carregar les dades a l'inici utilitzant el mètode estàtic de la llista.
     */
    private static void carregarDadesSistema() {
        System.out.println("Iniciant sistema...");
        
        // CRIDA AL MÈTODE DE LA LLISTA: Ella sap com llegir el fitxer serialitzat
        llistaInscripcions = LlistaInscripcio.carregarFitxer(RUTA_FITXER);
        
        System.out.println("Dades carregades: " + llistaInscripcions.getNumElements() + " inscripcions a memòria.");
    }

    /**
     * Gestiona la pregunta final i ordena a la llista que es guardi.
     */
    private static void gestionarSortida() {
        System.out.print("\nVols guardar els canvis abans de sortir? (S/N): ");
        String resposta = teclat.nextLine();

        if (resposta.equalsIgnoreCase("S")) {
            System.out.println("Guardant dades...");
            
            try {
                // CRIDA AL MÈTODE DE LA LLISTA: Ella sap com escriure's al disc
                llistaInscripcions.guardarFitxer(RUTA_FITXER);
                System.out.println("OK! Dades guardades correctament a " + RUTA_FITXER);
            } catch (Exception e) { // Capturem excepcions generals per si falla el disc
                System.out.println("ERROR CRÍTIC: No s'ha pogut guardar el fitxer. " + e.getMessage());
            }
            
        } else {
            System.out.println("Tancant sense guardar canvis.");
        }
    }

    /**
     * Lògica per demanar dades a l'usuari i afegir a la llista en memòria.
     */
    private static void tractarNovaInscripcio() {
        System.out.println("\n--- NOVA INSCRIPCIÓ ---");
        System.out.print("Àlies Usuari: ");
        String usuari = teclat.nextLine();
        System.out.print("Nom Activitat: ");
        String activitat = teclat.nextLine();

        try {
            Inscripcio nova = new Inscripcio(usuari, activitat);
            // Modifiquem només la RAM
            llistaInscripcions.afegirInscripcio(nova);
            System.out.println("Inscripció realitzada correctament (en memòria).");
        } catch (InscripcioDuplicada e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: Dades incorrectes.");
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n--- MENÚ PRINCIPAL ---");
        System.out.println("2.  Mostrar totes les inscripcions");
        System.out.println("10. Inscriure's a una activitat");
        System.out.println("22. Sortir");
        System.out.print("Tria opció: ");
    }

    private static int llegirEnter() {
        try {
            int i = Integer.parseInt(teclat.nextLine());
            return i;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}