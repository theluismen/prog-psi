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
                    case1();
                    break;

                case 2:
                    //Mostrar les dades de les llistes
                    case2();
                    break;

                // --- CONSULTES ---
                case 3:
                    // TODO: Activitats en període d'inscripció amb places disponibles
                    case3();
                    break;

                case 4:
                    // TODO: Activitats que tenen classe AVUI
                    case4();
                    break;

                case 5:
                    // TODO: Activitats actives AVUI (dins període)
                    case5();
                    break;

                case 6:
                    // TODO: Activitats amb places disponibles (qualsevol data)
                    case6();
                    break;

                case 7:
                    // TODO: Detall d'una activitat pel seu nom
                    case7();
                    break;

                case 8:
                    //Detall d'un usuari pel seu àlies
                    case8();
                    break;

                case 9:
                    // TODO: Activitats on està apuntat un usuari
                    case9();
                    break;

                // --- GESTIÓ D'INSCRIPCIONS ---
                case 10:
                    // TODO: Inscriure's a una activitat
                    case10();
                    break;

                case 11:
                    // TODO: Mostrar usuaris apuntats a una activitat i llista d'espera
                    case11();
                    break;

                case 12:
                    // TODO: Eliminar un usuari d'una activitat
                    case12();
                    break;

                // --- ALTES D'ACTIVITATS ---
                case 13:
                    // TODO: Afegir nova activitat d'un dia
                    case13();
                    break;

                case 14:
                    // TODO: Afegir nova activitat periòdica
                    case14();
                    break;

                case 15:
                    // TODO: Afegir nova activitat online
                    case15();
                    break;

                // --- VALORACIONS I ESTADÍSTIQUES ---
                case 16:
                    // TODO: Valorar una activitat
                    case16();
                    break;

                case 17:
                    // TODO: Resum de valoracions d'activitats acabades
                    case17();
                    break;

                case 18:
                    // TODO: Resum valoracions fetes per un usuari
                    case18();
                    break;

                case 19:
                    // TODO: Mitjana valoracions per col·lectiu
                    case19();
                    break;

                case 20:
                    // TODO: Usuari més actiu d'un col·lectiu
                    case20();
                    break;

                // --- MANTENIMENT ---
                case 21:
                    // TODO: Donar de baixa activitats amb poca participació
                    case21();
                    break;

                // --- SORTIDA ---
                case 22:
                    sortir = true;
                    case22();     // --- TANCAMENT I GUARDAT ---
                    break;

                default:
                    System.out.println("Opció no vàlida.");
            }
        }
    }

    // MÈTODES AUXILIARS BÀSICS

    private static void carregarDadesSistema(){
        System.out.println("Carregant dades...");
        // Inicialització segura per evitar errors si els fitxers no existeixen
        llistaInscripcions = LlistaInscripcio.carregarFitxer(FITXER_INSCRIPCIONS);
        llistaUsuaris = new LlistaUsuaris(); 
        // llistaUsuaris.carregarFitxer(FITXER_USUARIS);
        llistaActivitats = new LlistaActivitats(100);
        // llistaActivitats.carregarFitxer(FITXER_ACTIVITATS);
        
        System.out.println("Sistema inicialitzat correctament.");
    }

    private static void mostrarMenu() {
        System.out.println("\n--- MENÚ PRINCIPAL ---");
        System.out.println("1. Consulta la data d'avui | 2. Mostrar les llistes");
        System.out.println("3.  | 4. ");
        System.out.println("5.  | 6. ");
        System.out.println("7.  | 8. Buscar la informació d'un usuari");
        System.out.println("9.  | 10. Inscriure");
        System.out.println("11. | 12. ");
        System.out.println("13. Nova Activitat d'un dia | 14. ");
        System.out.println("15. | 16. ");
        System.out.println("17. | 18. ");
        System.out.println("19. | 20. ");
        System.out.println("21. | 22. Sortir");
        System.out.print("Opció: ");
    }

    private static int llegirEnter() {
        try {
            return Integer.parseInt(teclat.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }


    //-- METODOS PRINCIPALES PARA TRATAR CADA CASO -- 

    private static void case1(){

    }
    
    private static void case2(){
        int opcio;
        boolean res = false;
        
        while (!res){
            System.out.println("\nQuina llista vols veure?");
            System.out.println("\t(opció 1: Llista d'activitats)\n\t(opció 2: Llista d'usuaris)\n\t(opció 3: Llista d'inscripcions)");
            opcio = llegirEnter();

            switch(opcio){
                case 1:
                    tipusLlistaAct();
                    res = true;
                    break;

                case 2:
                    tipusLlistaU();
                    res = true;
                    break;

                case 3:
                    System.out.println(llistaInscripcions);
                    res = true;
                    break;
                
                default:
                    System.out.println("L'opció introduida no es valida, torna-ho a probar");


            }
        }
    }

    private static void case3(){
        
    }

    private static void case4(){
        
    }

    private static void case5(){
        
    }

    private static void case6(){
        
    }

    private static void case7(){
        
    }

    private static void case8(){
        //Mostrar el detall d’informació d’un usuari a partir del seu nom.
        String aux;
        System.out.println("\nQuin usuari vols buscar?");
        
        aux = teclat.nextLine();
        Usuari u = llistaUsuaris.cerca(aux);

        if (u == null){
            System.out.println("No existeix cap usuari amb l'àlies \"" + aux + "\"");

        }else{
            System.out.println(u);
        }

    }

    private static void case9(){
        
    }

    private static void case10(){
        
    }

    private static void case11(){
        
    }

    private static void case12(){
        
    }

    private static void case13(){
        
    }

    private static void case14(){
        //afegir una nova activitat periodica
        String nom, centre, ciutat;
        String[] col;
        Data dIniI, dFi, dH;
        DiaSetmana dia;
        double durada, preu;
        int setmanes, places;
        int diaAux, mesAux, anyAux;

        System.out.println("Nom de l'activitat?");  //nom
        nom = teclat.nextLine();

        col = demanarColectius();   //colectivos
        
        demanarPeriodeInscripcio(dIniI, dFi);   //periodo de inscripcion
        
        dia = demanarDia();     //dia de la semana




        
        ActivitatPeriodica act = new ActivitatPeriodica(nom, col, dIniI, dFi, 
        dia, 0, null, 0, 0, 0, null, null)
    }

    private static void case15(){
        
    }

    private static void case16(){
        
    }

    private static void case17(){
        
    }

    private static void case18(){
        
    }

    private static void case19(){
        
    }

    private static void case20(){
        
    }

    private static void case21(){
        
    }

    private static void case22() {
        String resp;
        boolean result = false;
        while (!result){
            System.out.print("\nVols guardar els canvis abans de sortir? (S/N): ");
            resp = teclat.nextLine();
            if (resp.equalsIgnoreCase("S")) {
                System.out.println("Guardant dades...");
                try {
                    llistaInscripcions.guardarFitxer(FITXER_INSCRIPCIONS);
                    // llistaUsuaris.guardarFitxer(FITXER_USUARIS);
                    llistaActivitats.guardarLlista(FITXER_ACTIVITATS);
                    System.out.println("Dades guardades.");
                    result = true;
                } catch (Exception e) {
                    System.out.println("Error guardant: " + e.getMessage());
                    System.out.println("Torna-ho a probar");
                }
            }else if (resp.equalsIgnoreCase("N")){
                result = true;
            }else{
                System.out.println("\nL'opció introduida no existeix, el format ha de ser S o N");
            }
            System.out.println("Adéu!");
        }
    }




//-- METODOS AUXILIARES PARA SIMPLIFICAR LOS PRINCIPALES --
    
    //metodo para tratar el caso en que el usuario quiere mostrar la informacion de las actividades
    private static void tipusLlistaAct(){
        boolean res = false;
        String aux;
        int op;
        LlistaActivitats llista = new LlistaActivitats(0);

        while (!res){
            System.out.println("\nVols mostrar totes les activitats? (S/N): ");
            aux = teclat.nextLine();

            if (aux.equalsIgnoreCase("S")){
                System.out.println(llistaActivitats);
                res = true;

            }else if (aux.equalsIgnoreCase("N")){
                while (!res){
                    System.out.println("Quin tipus d'activitats vols veure?");
                    System.out.println("\t(opció 1: Periòdiques)\n\t(opció 2: Online)\n\t(opció 3: Un dia)");
                    op = llegirEnter();

                    if (op == 1){
                        llista = llistaActivitats.tipusLlista("Activitat periodica");
                        res = true;
                    }else if (op == 2){
                        llista = llistaActivitats.tipusLlista("Online");
                        res = true;
                    }else if (op == 3){
                        llista = llistaActivitats.tipusLlista("Activitat d'un dia");
                        res = true;
                    }else{
                        System.out.println("L'opció introduida no existeix, prova una diferent");
                    }
                }
                System.out.println(llista);

            }else{
                System.out.println("L'opció introduida no es valida, escull una diferent");
            }
        }
    }


    //metodo para tratar el caso en que el usuario quiere mostrar la informacion de los usuarios
    private static void tipusLlistaU(){
        boolean res = false;
        String aux;
        int op;
        LlistaUsuaris llista = new LlistaUsuaris();

        while (!res){
            System.out.println("\nVols mostrar tots els usuaris? (S/N): ");
            aux = teclat.nextLine();

            if (aux.equalsIgnoreCase("S")){
                System.out.println(llistaUsuaris);
                res = true;

            }else if (aux.equalsIgnoreCase("N")){
                while (!res){
                    System.out.println("De quin colectiu vols veure els usuaris?");
                    System.out.println("\t(opció 1: PDI)\n\t(opció 2: PTGAS)\n\t(opció 3: Estudiant)");
                    op = llegirEnter();

                    if (op == 1){
                        llista = llistaUsuaris.tipusLlista("PDI");
                        res = true;
                    }else if (op == 2){
                        llista = llistaUsuaris.tipusLlista("PTGAS");
                        res = true;
                    }else if (op == 3){
                        llista = llistaUsuaris.tipusLlista("Estudiant");
                        res = true;
                    }else{
                        System.out.println("L'opció introduida no existeix, prova una diferent");
                    }
                }
                System.out.println(llista);

            }else{
                System.out.println("L'opció introduida no es valida, escull una diferent");
            }
        }
    }

    //metodo para comprobar si los colectivos enviados por parametro en un array existen
    private static boolean existeixenCols(String[] cols){
        boolean res = true;
        int i = 0;

        while (res && (i < cols.length)){
            if (!cols[i].equalsIgnoreCase("PDI") && !cols[i].equalsIgnoreCase("PTGAS") && !cols[i].equalsIgnoreCase("Estudiant")){
                res = false;
            }
        }

        return res;
    }

    //metodo para pedir los colectivos comprobando que sea correcto
    private static String[] demanarColectius(){
        boolean res = false;
        String[] col = null;

        while (!res){
            System.out.println("\nCol·lectius participants? (separats per comes: colaA,colB,...)");
            col = teclat.nextLine().split(",");
            res = existeixenCols(col);

            if (!res){
                System.out.println("Els colectius introduits no segueixen el format adequat o no existeixen, torna-ho a probar");
                System.out.println("Col·lectius participants? (separats per comes: colaA,colB,...)");
            }
        }

        return col;
    }

    //metodo para pedir el periodo de inscripcion y comprobar que es correcto
    private static void demanarPeriodeInscripcio(Data dIni, Data dFi){
        boolean res = false;
        int diaAux, mesAux, anyAux;
        while (!res){
            System.out.println("Dia inici inscripció?");
            diaAux = llegirEnter();
            System.out.println("Mes inici inscripció?");
            mesAux = llegirEnter();
            System.out.println("Any inici inscripció?");
            anyAux = llegirEnter();

            try{
                dIni = new Data(diaAux, mesAux, anyAux);
                res = true;
            }catch(ValorInexistent e){
                System.out.println("La data no existeix, proba amb una altra\n");
            }
        }

        res = false;
        while (!res){
            System.out.println("Dia fi inscripció?");
            diaAux = llegirEnter();
            System.out.println("Mes fi inscripció?");
            mesAux = llegirEnter();
            System.out.println("Any fi inscripció?");
            anyAux = llegirEnter();

            try{
                dFi = new Data(diaAux, mesAux, anyAux);
                res = true;
            }catch(ValorInexistent e){
                System.out.println("La data no existeix, proba amb una altra\n");
            }
        }
    }

    //metodo para pedir el dia de la semana en que se hace una actividad
    private static DiaSetmana demanarDia(){
        String aux;
        boolean res = false;
        DiaSetmana dia = null;

        while (!res){
            System.out.println("En quin dia de la setmana es farà l'activitat? (escrit en català i en minúscula)");
            aux = teclat.nextLine();

            try{
                dia = DiaSetmana.valueOf(aux.toUpperCase());
            }catch(IllegalArgumentException e){
                System.out.println("El dia no existeix, torna-ho a probar");
            }
        }

        return dia;
    }

    //metodo para pedir el dia y la hora de la actividad
    private static Data demanarDiaYHora(){
        boolean res = false;
        int dia, mes, any, hora, min;
        Data data = null;

        while (!res){
            System.out.println("Dia inici de l'activitat?");
            dia = llegirEnter();
            System.out.println("Mes inici de l'activitat?");
            mes = llegirEnter();
            System.out.println("Any inici de l'activitat?");
            any = llegirEnter();
            System.out.println("Hora inici de l'activitat?");
            hora = llegirEnter();
            System.out.println("Minut inici de l'activitat?");
            min = llegirEnter();
        }

        try{
                data = new Data(dia, mesAux, anyAux);
                res = true;
            }catch(ValorInexistent e){
                System.out.println("La data no existeix, proba amb una altra\n");
            }
    }


}