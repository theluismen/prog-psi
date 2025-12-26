/**
 * Autor(@s):   Ainara Sofia Cabrera Robles
                Ikram Hallouz Safa 
                Aesha Naz Mahmood Bibi
                Alexandra Núñez González
 * Descripción: Aplicación de consola para gestionar actividades, usuarios e inscripciones.
                Ainara: cas 1, 5, 7, 13 i 19
                Ikram: cas 10, 11, 12, 15 i 16
                Aesha: cas 3, 6, 9, 17, 18 i 21
                Alexandra: cas 2, 4, 8, 14, 20 i 22
 */
package apps;


import java.util.Scanner;

import java.io.*;


import llistes.*;
import inscripcions.*;
import usuaris.*;
import activitats.*;
import excepcions.*;
import extras.Data;


public class AppConsola {


    // 1. RUTES DELS FITXERS
    private static final String FITXER_INSCRIPCIONS = "src/fitxers/inscripcions.dat";
    private static final String FITXER_USUARIS = "src/fitxers/usuaris.txt";
    private static final String FITXER_ACTIVITATS = "src/fitxers/activitat.txt";


    // 2. VARIABLES GLOBALS (LLISTES)
    private static LlistaInscripcions llistaInscripcions;
    private static LlistaUsuaris llistaUsuaris;
    private static LlistaActivitats llistaActivitats;
   
    // Eines globals
    private static final Scanner teclat = new Scanner(System.in);
    private static Data dataActual;


    public static void main(String[] args) throws ValorInexistent {
       
        // --- INICIALITZACIÓ ---
        boolean sortir = false;
        int opcio = 0;
        try{
            dataActual = new Data(1, 9, 2025); // Data inicial per defecte
        }catch(ValorInexistent e){  //es obligatori tractar l'excepció, però com que es un valor per defecte no donarà error
            System.out.println("La data no existeix");
        }


        // --- CÀRREGA DE DADES ---
        try {
            carregarDadesSistema();
        } catch (IOException | FormatInvalid | CollectiuDesconegut | UsuariDuplicat e) {
            System.out.println("Error carregant dades del sistema: " + e.getMessage());
        }


        // --- BUCLE PRINCIPAL ---
        while (!sortir) {
            mostrarMenu();
            opcio = llegirEnter();

            switch (opcio) {
            // --- GESTIÓ BÀSICA ---
                             
                // Indicar/Canviar la data del sistema
                case 1:  case1();  break;
                // Mostrar les dades de les llistes
                case 2:  case2();  break;
            // --- CONSULTES ---
                // Activitats en període d'inscripció amb places disponibles
                case 3:  case3();  break;
                // Activitats que tenen classe AVUI
                case 4:  case4();  break;
                // Activitats actives AVUI (dins període)
                case 5:  case5();  break;
                // Activitats amb places disponibles (qualsevol data)
                case 6:  case6();  break;
                // Detall d'una activitat pel seu nom
                case 7:  case7();  break;
                // Detall d'un usuari pel seu àlies
                case 8:  case8();  break;
                // TODO: Activitats on està apuntat un usuari
                case 9:  case9();  break;
            // --- GESTIÓ D'INSCRIPCIONS ---
                // Inscriure's a una activitat
                case 10:  case10();  break;
                // TODO: Mostrar usuaris apuntats a una activitat i llista d'espera
                case 11:  case11();  break;
                // TODO: Eliminar un usuari d'una activitat
                case 12:  case12();  break;
            // --- ALTES D'ACTIVITATS ---
                // TODO: Afegir nova activitat d'un dia
                case 13:  case13();  break;
                // Afegir nova activitat periòdica
                case 14:  case14();  break;
                // TODO: Afegir nova activitat online
                case 15:  case15();  break;
            // --- VALORACIONS I ESTADÍSTIQUES ---
                // TODO: Valorar una activitat
                case 16:  case16();  break;
                // TODO: Resum de valoracions d'activitats acabades
                case 17:  case17();  break;
                // TODO: Resum valoracions fetes per un usuari
                case 18:  case18();  break;
                // TODO: Mitjana valoracions per col·lectiu
                case 19:  case19();  break;
                // Usuari més actiu d'un col·lectiu
                case 20:  case20();  break;
            // --- MANTENIMENT ---
                // TODO: Donar de baixa activitats amb poca participació
                case 21:  case21();  break;
            // --- SORTIDA ---
                // --- TANCAMENT I GUARDAT ---
                case 22:  sortir = true;  case22();  break;
                
                default:
                    System.out.println("Opció no vàlida.");
            }
        }
    }


    // MÈTODES AUXILIARS BÀSICS


    private static void carregarDadesSistema() throws IOException, FormatInvalid, CollectiuDesconegut, UsuariDuplicat {
        System.out.println("Carregant dades...");
        // Inicialització segura per evitar errors si els fitxers no existeixen
        llistaInscripcions = LlistaInscripcions.carregarFitxer(FITXER_INSCRIPCIONS);
        llistaUsuaris = new LlistaUsuaris();
        llistaUsuaris.carregarFitxer(FITXER_USUARIS);
        llistaActivitats = new LlistaActivitats(100);
        llistaActivitats.carregarFitxer(FITXER_ACTIVITATS);
       
        System.out.println("Sistema inicialitzat correctament.");
    }


    private static void mostrarMenu() {
        System.out.println("\n--- MENÚ PRINCIPAL ---");
        System.out.println("1. Consulta la data d'avui | 2. Mostrar les llistes");
        System.out.println("3. Consultar les activitats en període d'inscripció | 4. Consultar les activitats amb clase avui");
        System.out.println("5.  | 6. Consultar les activitats amb places disponibles");
        System.out.println("7.  | 8. Buscar la informació d'un usuari");
        System.out.println("9.  | 10. Inscriure");
        System.out.println("11. | 12. ");
        System.out.println("13. Afegir activitat d'un dia | 14. Afegir activitat periodica");
        System.out.println("15. | 16. ");
        System.out.println("17. | 18. ");
        System.out.println("19. | 20. Consultar l'usuari més actiu");
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
        System.out.println("\nData actual del systema: " + dataActual);

        System.out.println("\nVols canviar la data actual? (S/N): ");
        String respuesta = teclat.nextLine();

        if(respuesta.equalsIgnoreCase("S")){
            System.out.println("\n\tIntrodueix la nova data: ");
            dataActual = demanarData();
            System.out.println("\n\tLa nova data actual del systema és: " + dataActual);

        }else if(respuesta.equalsIgnoreCase("N")){
            System.out.println("La data no s'ha modificat.");

        }else{
            System.out.println("Opció no válida. Has d'introduir S o N.");
        }
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
        boolean hiHa = false;
        Activitat act = null;

        System.out.println("\n--- Activitats en període d'inscripció amb places disponibles ---");


        for (int i = 0; i < llistaActivitats.getNumElements(); i++) {
            try{
                act = llistaActivitats.getActivitatIesima(i);
            
            }catch(ValorInexistent e){ //es controla que no surti de la llista amb el for, no hauria de donar error mai
                System.out.println("ERROR INESPERAT EN CASE3");
            }

            if (act != null && act.esEnPeriodeInscripcio(dataActual)) {
               
                int inscrits = llistaInscripcions.comptarInscripcionsActivitat(act.getNom());
                int placesMax = act.getPlacesMaximes();

                System.out.println(act);

                if (inscrits < placesMax) {
                    if (placesMax == Integer.MAX_VALUE) {
                        System.out.println("Places disponibles: Il·limitades ");
                    } else {
                        System.out.println("Places disponibles: " + (placesMax - inscrits));
                    }
                } else {
                    System.out.println("Activitat completa (no hi ha places disponibles)");
                }


                System.out.println();
                hiHa = true;
            }
        }


        if (!hiHa) {
            System.out.println("No hi ha activitats amb inscripció oberta.");
        }
       
    }


    private static void case4(){
        LlistaActivitats claseAvui = llistaActivitats.claseAvui(dataActual);
        String aux = "";
        System.out.println("\n-- A continuació es mostrarà el detall d'informació de cada activitat, seguit de les places ocupades y de la gent en espera,"+
                                "amb clase en la data: "+dataActual+" --");
        
        for (int i = 0; i < claseAvui.getNumElements(); i++){
            try{
                Activitat act = claseAvui.getActivitatIesima(i);
                System.out.println("\n" + act);
                aux = "Places: "+llistaInscripcions.getNumAdmesos(act.getNom(), act.getPlacesMaximes()) + " / ";

                if(claseAvui.getActivitatIesima(i).getPlacesMaximes() == Integer.MAX_VALUE){
                    aux += "il·limitat";
                    System.out.println(aux);
                }else{
                    aux += act.getPlacesMaximes();
                    System.out.println(aux);
                    System.out.println("Llista d'espera: " + llistaInscripcions.getNumEnEspera(act.getNom(), act.getPlacesMaximes()) + " / 10");
                }
            
            }catch(ValorInexistent e){
                //nunca puede dar esta excepcion porque se controla con el for
                System.out.println("ERROR INESPERAT EN CASE4");
            }
        }

    }


    private static void case5(){
        boolean actActiva = false;

        System.out.println("\n--- Activitats actives en la data d'avui ---");

        for (int i = 0; i < llistaActivitats.getNumElements(); i++) {
            try {
                Activitat act = llistaActivitats.getActivitatIesima(i);

                if (act.estaActiva(dataActual)) {
                    System.out.println("-> " + act.getNom());
                    actActiva = true;
                }

            } catch (ValorInexistent e) {
                
                System.out.println("ERROR INESPERAT EN CASE5");
            }
        }

        if (!actActiva) {
            System.out.println("No hi ha cap activitat activa en la data d'avui.");
        }
    }


    private static void case6(){
        boolean hiHa = false;
        Activitat act = null;

        System.out.println("\n--- Activitats amb places disponibles ---");


        for(int i = 0; i < llistaActivitats.getNumElements(); i++) {
            try{
                act = llistaActivitats.getActivitatIesima(i);
            
            }catch(ValorInexistent e){ //es controla que no surti de la llista amb el for, no hauria de donar error mai
                System.out.println("ERROR INESPERAT EN CASE6");
            }


            if (act != null) {
                int inscrits = llistaInscripcions.comptarInscripcionsActivitat(act.getNom());
                int placesMax = act.getPlacesMaximes();

                if(inscrits < placesMax) {
                    System.out.println("- " + act.getNom());
                    hiHa = true;
                }
            }
        }

        if(!hiHa) {
            System.out.println("No hi ha cap activitat amb places disponibles");
        }
       
    }


    private static void case7(){
        System.out.println("\n---Mostrar el detall d'informació d'una activitat---");

        System.out.println("\nIntrodueix el nom de l'activitat:");
        String nom = teclat.nextLine();

        Activitat act = llistaActivitats.cerca(nom);

        if (act == null) {
            System.out.println("No existeix cap activitat amb el nom " + nom + ".");
        } else {
            System.out.println("\nDetall de l'activitat: ");
            System.out.println(act);
        }
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


    private static void case9() {
        System.out.println("\n--- Activitats on està apuntat un usuari ---");
        System.out.println("Introdueix l'àlies de l'usuari: ");

        String alies = teclat.nextLine();

        Usuari u = llistaUsuaris.cerca(alies);

        if (u == null) {
            System.out.println("No existeix cap usuari amb aquest àlies. ");
            return;
        }

        LlistaInscripcions ins = llistaInscripcions.getInscripcionsUsuari(alies);

        if(ins.getNumElements() == 0) {
            System.out.println("Aquest usuari no està inscrit a cap activitat.");
            return;
        }

        Activitat act = null;
        System.out.println("\nActivitats on està inscrit" + alies + ":");
        for (int i = 0; i < ins.getNumElements(); i++) {
            act = llistaActivitats.cerca(ins.getInscripcioIesima(i).getIdActivitat());
            if(act != null){
                System.out.println("-" + act.getNom());
            }
        }
       

    }
       


    private static void case10() {
        try {
            System.out.println("\n--- Inscripció a una activitat ---");
            
            // 1. Demanar i validar l'usuari
            System.out.print("Introdueix l'àlies de l'usuari: ");
            String alias = teclat.nextLine();
            Usuari u = llistaUsuaris.cerca(alias);

            if (u == null) {
                System.out.println("Error: No existeix cap usuari amb l'àlies \"" + alias + "\".");
                return;
            }

            // 2. Demanar i validar l'activitat
            System.out.print("Introdueix el nom de l'activitat: ");
            String nomAct = teclat.nextLine();
            Activitat act = llistaActivitats.cerca(nomAct);

            if (act == null) {
                System.out.println("Error: No existeix cap activitat amb el nom \"" + nomAct + "\".");
                return;
            }
            
            // 4. Intentar fer la inscripció
            Inscripcio novaInscripcio = new Inscripcio(alias, nomAct);
            llistaInscripcions.afegirInscripcio(novaInscripcio);

            // 5. Informar l'usuari del seu estat (Admès o Llista d'Espera)
            int placesMax = act.getPlacesMaximes();
            
            // Si placesMax és MAX_VALUE (Online), sempre entra.
            if (placesMax == Integer.MAX_VALUE) {
                System.out.println("Inscripció realitzada amb èxit! (Activitat Online sense límit).");
            } else {
                // Comprovem quants n'hi ha ara mateix
                int totalInscrits = llistaInscripcions.comptarInscripcionsActivitat(nomAct);
                
                if (totalInscrits <= placesMax) {
                    System.out.println("Inscripció realitzada amb èxit! Tens plaça assignada.");
                } else {
                    int posicioEspera = totalInscrits - placesMax;
                    System.out.println("L'activitat està plena. Has quedat inscrit en la LLISTA D'ESPERA.");
                    System.out.println("La teva posició a la cua és: " + posicioEspera);
                }
            }

        } catch (InscripcioDuplicada e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("S'ha produït un error inesperat: " + e.getMessage());
        }
    }


    private static void case11() {
        System.out.println("\n--- Consulta d'inscrits i llista d'espera ---");
        System.out.print("Introdueix el nom de l'activitat: ");
        String nomAct = teclat.nextLine();

        // 1. Busquem l'activitat per saber les seves places màximes
        Activitat act = llistaActivitats.cerca(nomAct);

        if (act == null) {
            System.out.println("Error: No existeix cap activitat amb el nom \"" + nomAct + "\".");
            return;
        }

        int placesMax = act.getPlacesMaximes();

        // 2. Recuperem les llistes d'admesos i d'espera
        // Aquests mètodes retornen objectes LlistaInscripcio (segons els últims canvis)
        LlistaInscripcio llistaAdmesos = llistaInscripcions.getAdmesos(nomAct, placesMax);
        LlistaInscripcio llistaEspera = llistaInscripcions.getLlistaEspera(nomAct, placesMax);

        // 3. Mostrem els ADMESOS
        System.out.println("\n=== USUARIS ADMESOS (" + llistaAdmesos.getNumElements() + " / " + 
                           (placesMax == Integer.MAX_VALUE ? "Il·limitades" : placesMax) + ") ===");
        
        if (llistaAdmesos.getNumElements() == 0) {
            System.out.println("   (Cap usuari inscrit)");
        } else {
            for (int i = 0; i < llistaAdmesos.getNumElements(); i++) {
                // Recuperem la inscripció i després l'usuari complet per mostrar dades
                Inscripcio ins = llistaAdmesos.getInscripcioIesima(i);
                Usuari u = llistaUsuaris.cerca(ins.getIdUsuari());
                
                if (u != null) {
                    // Mostrem l'àlies i el tipus (o toString reduït)
                    System.out.println("   - " + u.getAlies());
                } else {
                    System.out.println("   - " + ins.getIdUsuari() + " (Usuari no trobat a la llista)");
                }
            }
        }

        // 4. Mostrem la LLISTA D'ESPERA
        System.out.println("\n=== LLISTA D'ESPERA (" + llistaEspera.getNumElements() + ") ===");
        
        if (llistaEspera.getNumElements() == 0) {
            System.out.println("   (La llista d'espera és buida)");
        } else {
            for (int i = 0; i < llistaEspera.getNumElements(); i++) {
                Inscripcio ins = llistaEspera.getInscripcioIesima(i);
                Usuari u = llistaUsuaris.cerca(ins.getIdUsuari());

                // Mostrem la posició (i+1) perquè és una cua ordenada
                if (u != null) {
                    System.out.println("   " + (i + 1) + ". " + u.getAlies());
                } else {
                    System.out.println("   " + (i + 1) + ". " + ins.getIdUsuari());
                }
            }
        }
    }


    private static void case12() {
        System.out.println("\n--- Cancel·lar inscripció (Eliminar usuari d'una activitat) ---");

        // 1. Demanar l'usuari
        System.out.print("Introdueix l'àlies de l'usuari: ");
        String alias = teclat.nextLine();
        Usuari u = llistaUsuaris.cerca(alias);

        if (u == null) {
            System.out.println("Error: No existeix cap usuari amb l'àlies \"" + alias + "\".");
            return;
        }

        // 2. Demanar l'activitat
        System.out.print("Introdueix el nom de l'activitat: ");
        String nomAct = teclat.nextLine();
        Activitat act = llistaActivitats.cerca(nomAct);

        if (act == null) {
            System.out.println("Error: No existeix cap activitat amb el nom \"" + nomAct + "\".");
            return;
        }

        try {
            // 3. Preparació per gestionar la Llista d'Espera (Requisit PDF)
            // Abans d'esborrar, mirem si hi ha algú esperant que pugui entrar.
            int placesMax = act.getPlacesMaximes();
            String candidatEntrar = null;

            // Si hi ha llista d'espera, el primer de la cua és el candidat
            if (llistaInscripcions.getNumEnEspera(nomAct, placesMax) > 0) {
                LlistaInscripcio espera = llistaInscripcions.getLlistaEspera(nomAct, placesMax);
                // El primer de la llista d'espera (posició 0)
                candidatEntrar = espera.getInscripcioIesima(0).getIdUsuari();
            }

            // 4. ELIMINACIÓ
            llistaInscripcions.eliminarInscripcio(alias, nomAct);
            System.out.println("S'ha eliminat la inscripció correctament.");

            // 5. Comprovació post-eliminació (Gestió automàtica de la cua)
            // Si teníem un candidat i ara resulta que està a la llista d'ADMESOS, ho notifiquem.
            if (candidatEntrar != null) {
                // Obtenim la nova llista d'admesos
                LlistaInscripcio admesos = llistaInscripcions.getAdmesos(nomAct, placesMax);
                boolean haEntrat = false;
                
                // Busquem si el candidat ara està admès
                for (int i = 0; i < admesos.getNumElements(); i++) {
                    if (admesos.getInscripcioIesima(i).getIdUsuari().equals(candidatEntrar)) {
                        haEntrat = true;
                        break;
                    }
                }

                if (haEntrat) {
                    System.out.println("En alliberar-se una plaça, l'usuari de la llista d'espera \"" 
                                       + candidatEntrar + "\" ha passat a estar ADMÈS.");
                }
            }

        } catch (InscripcioNoTrobada e) {
            System.out.println("Error: Aquest usuari no estava inscrit a l'activitat.");
        } catch (Exception e) {
            System.out.println("S'ha produït un error " + e.getMessage());
        }
    }


    private static void case13(){
       
    }


    private static void case14(){
        //afegir una nova activitat periodica
        String nom, centre, ciutat;
        String[] col;
        Data dIniI = null, dFi = null, dH;
        DiaSetmana dia;
        double durada, preu;
        int setmanes, places;
        boolean res = false;
        ActivitatPeriodica act = null;


        System.out.println("\nNom de l'activitat?");  //nom
        nom = teclat.nextLine();
       
        System.out.println("\nEscull la data d'inici d'inscripcions:");   //data inicio de inscripcion
        dIniI = demanarData();


        System.out.println("\nEscull la data de fi d'inscripcions");   //data fin de inscripcion
        dFi = demanarData();
       
        dia = demanarDia();     //dia de la semana


        dH = demanarDiaYHora();     //dia y hora d'inici de l'activitat


        System.out.println("\nCiutat on es farà l'activitat?");  //ciutat
        ciutat = teclat.nextLine();


        System.out.println("\nCentre on es farà l'activitat?");  //centre
        centre = teclat.nextLine();


        System.out.println("\nCol·lectius participants? (separats per comes: colaA,colB,...)");     //colectivos
        col = teclat.nextLine().split(",");
           
        System.out.println("\nQuina serà la durada de l'activitat? (utilitzar punt com a separador decimal)");         //duracion
        durada = Double.parseDouble(teclat.nextLine().replace(",", "."));
           
        System.out.println("\nQuantes setmanes durarà l'activitat?");     //semanas
        setmanes = llegirEnter();


        System.out.println("\nQuantes places tè?");       //plazas
        places = llegirEnter();


        System.out.println("\nQuin preu tè l'activitat?");        //precio
        preu = Double.parseDouble(teclat.nextLine().replace(",", "."));
       
        while (!res){
            try{
                act = new ActivitatPeriodica(nom, col, dIniI, dFi,
                dia, durada, dH, setmanes, places, preu, centre, ciutat);
                res = true;


            }catch(ValorInexistent e){
                System.out.println("Un dels valors es incorrecte."+ e);


                System.out.println("Quina serà la durada de l'activitat?");
                durada = teclat.nextDouble();
               
                System.out.println("Quantes setmanes durarà l'activitat?");
                setmanes = llegirEnter();


                System.out.println("Quantes places tè?");
                places = llegirEnter();


                System.out.println("Quin preu tè l'activitat?");
                preu = teclat.nextDouble();


            }catch(CollectiuDesconegut e){
                System.out.println("\n" + e + "\nTorna a introduir");
                System.out.println("\nCol·lectius participants? (separats per comes: colaA,colB,...)");
                col = teclat.nextLine().split(",");
            }
        }


        res = false;
        while (!res){
            try{
                llistaActivitats.afegir(act);
                res = true;


            }catch(ActivitatDuplicada e){
                System.out.println(e + "\nEscull un nom diferent");
            }
        }
    }


    private static void case15() {
        System.out.println("\n--- Afegir nova Activitat Online ---");

        try {
            // 1. Dades bàsiques
            System.out.print("Nom de l'activitat: ");
            String nom = teclat.nextLine();

            // 2. Dates
            System.out.println(">> Data d'INICI d'inscripcions:");
            Data dIniInsc = demanarData();

            System.out.println(">> Data de FI d'inscripcions:");
            Data dFiInsc = demanarData();

            System.out.println(">> Data d'INICI de l'activitat:");
            Data dIniAct = demanarData();

            // 3. Durada
            System.out.print("Dies de visualització (durada): ");
            int dies = llegirEnter();

            // 4. Enllaç (Validació: Sense espais en blanc)
            String enllac = "";
            boolean enllacValid = false;
            while (!enllacValid) {
                System.out.print("Enllaç (URL) de l'activitat: ");
                enllac = teclat.nextLine();
                
                if (enllac.contains(" ")) {
                    System.out.println("Error: L'enllaç no pot contenir espais en blanc. Torna-ho a intentar.");
                } else if (enllac.isEmpty()) {
                    System.out.println("Error: L'enllaç no pot estar buit.");
                } else {
                    enllacValid = true;
                }
            }

            // 5. Col·lectiu
            // Demanem un únic col·lectiu perquè el constructor d'ActivitatOnline accepta només un
            System.out.print("A quin col·lectiu va dirigida? (ESTUDIANT, PDI, PTGAS): ");
            String colStr = teclat.nextLine().toUpperCase();
            Collectius col = Collectius.valueOf(colStr); // Converteix String a Enum

            // 6. Creació i Inserció
            ActivitatOnline novaAct = new ActivitatOnline(
                nom, 
                col, 
                dIniInsc, 
                dFiInsc, 
                dIniAct, 
                dies, 
                enllac
            );

            llistaActivitats.afegir(novaAct);
            System.out.println("Activitat Online \"" + nom + "\" afegida correctament!");

        } catch (ActivitatDuplicada e) {
            System.out.println("Error: Ja existeix una activitat amb aquest nom.");
        } catch (CollectiuDesconegut e) {
            System.out.println("Error: El col·lectiu indicat no és vàlid.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: Col·lectiu desconegut o dades incorrectes.");
        } catch (Exception e) {
            System.out.println("Error inesperat creant l'activitat: " + e.getMessage());
        }
    }


    private static void case16() {
        System.out.println("\n--- Valorar una activitat ---");

        // 1. Demanar dades
        System.out.print("Introdueix l'àlies de l'usuari: ");
        String alias = teclat.nextLine();
        Usuari u = llistaUsuaris.cerca(alias);

        if (u == null) {
            System.out.println("Error: L'usuari no existeix.");
            return;
        }

        System.out.print("Introdueix el nom de l'activitat: ");
        String nomAct = teclat.nextLine();
        Activitat act = llistaActivitats.cerca(nomAct);

        if (act == null) {
            System.out.println("Error: L'activitat no existeix.");
            return;
        }

        // 2. Comprovar si està inscrit
        // Fem servir el mètode getInscripcio que retorna l'objecte únic per poder modificar-lo
        Inscripcio ins = llistaInscripcions.getInscripcio(alias, nomAct);

        if (ins == null) {
            System.out.println("Error: Aquest usuari no està inscrit a aquesta activitat (o està en llista d'espera).");
            return;
        }

        // 3. Comprovar si l'activitat ha acabat
        // Requisit: "l'activitat ha d'haver acabat" -> dataFinal < dataActual
        if (!act.getDataFinal().esDataInferior(dataActual)) {
            System.out.println("Error: No pots valorar l'activitat perquè encara no ha finalitzat.");
            System.out.println("       Data fi activitat: " + act.getDataFinal());
            System.out.println("       Data actual sistema: " + dataActual);
            return;
        }
        
        // Comprovar si ja estava valorada (Opcional, però recomanable)
        if (ins.esValorada()) {
            System.out.println("Avís: Aquesta activitat ja té una valoració de: " + ins.getValoracio());
            System.out.println("Si continues, la sobreescriuràs.");
        }

        // 4. Demanar i assignar la nota
        System.out.print("Introdueix la teva valoració (0 - 10): ");
        int nota = llegirEnter();

        try {
            ins.setValoracio(nota);
            System.out.println("Valoració registrada correctament!");
        } catch (ValoracioIncorrecta e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperat: " + e.getMessage());
        }
    }


    private static void case17(){  
        boolean hiHa = false;

        System.out.println("\n--- Resum valoracions d'activitats acabades ---");

        for (int i = 0; i < llistaActivitats.getNumElements(); i++) {
            Activitat act = null;
            try {
                act = llistaActivitats.getActivitatIesima(i);
            } catch (ValorInexistent e) {
                
            }

            if (act.getDataFinal().esDataInferiorOigual(dataActual)) {
                LlistaInscripcions llistaIns = llistaInscripcions.getInscripcionsActivitat(act.getNom());

                int suma = 0;
                int comptador = 0;

                for(int j = 0; j < llistaIns.getNumElements(); j++) {
                    Inscripcio ins = llistaIns.getInscripcioIesima(j);
                    
                    if(ins.esValorada()) {
                        suma += ins.getValoracio();
                        comptador++;
                    }
                }

                if (comptador > 0) {
                    double mitjana = (double) suma / comptador;
                    System.out.printf("Activitat: %s | Mitjana: %.2f\n", act.getNom(), mitjana);                
                } else {
                    System.out.println("Activitat: " + act.getNom() + " | Sense valoracions");
                }

                hiHa = true;
            }
        }

        if(!hiHa) {
            System.out.println("No hi ha activitats acabades. ");
        }
       
    }

    private static void case18(){ 
        System.out.println("\n--- Resum de valoracions fetes per un usuari ---");
        System.out.println("Introdueix l'àlies de l'usuari: ");

        String alies = teclat.nextLine();

        Usuari u = llistaUsuaris.cerca(alies);

        if (u == null) {
            System.out.println("No existeix cap usuari amb aquest àlies. ");
            return;
        }

        LlistaInscripcions ins = llistaInscripcions.getInscripcionsUsuari(alies);

        boolean hiHa = false;
        for(int i = 0; i < ins.getNumElements(); i++) {
            Inscripcio inscripcio = ins.getInscripcioIesima(i);

            if(inscripcio.esValorada()) {
                System.out.println("- Activitat: " + inscripcio.getIdActivitat() + " | Valoració: " + inscripcio.getValoracio());
                hiHa = true;
            }
        }

        if(!hiHa) {
            System.out.println("Aquest usuari encara no ha fet cap valoracó.");
        }
            
    }


    private static void case19(){
       
    }


    private static void case20(){
        boolean res = false;
        int op;
        LlistaUsuaris llista = null;
        Inscripcio[] u1, u2;

        while (!res){
            System.out.println("\nDe quin colectiu vols veure l'usuari més actiu?");
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
        
        u1 = llistaInscripcions.getInscripcionsUsuari(llista.getUsuariIesim(0).getAlies());
        for (int i = 1; i < llista.getNumElements(); i++){
            u2 = llistaInscripcions.getInscripcionsUsuari(llista.getUsuariIesim(i).getAlies());
            if (u1.length < u2.length){     //si el usuario actual tiene menos inscripciones que el siguiente, se cambia el valor
                u1 = u2;
            }
        }

        System.out.println("\nL'usuari més actiu del colectiu escollit es:\t" + u1[0].getIdUsuari());   //totes les inscripcions de u1 son del mateix usuari
                                                                                                        //serveix qualsevol posició de la taula

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
        LlistaUsuaris llista;

        while (!res){

            System.out.println("\nVols mostrar tots els usuaris? (S/N): ");
            aux = teclat.nextLine();

            if (aux.equalsIgnoreCase("S")){
                System.out.println(llistaUsuaris);
                res = true;

            }else if (aux.equalsIgnoreCase("N")){
                
                boolean opCorrecta = false;
                Collectius col = null;

                while (!opCorrecta) {
                
                    System.out.println("De quin colectiu vols veure els usuaris?");
                    System.out.println("\t(opció 1: PDI)\n\t(opció 2: PTGAS)\n\t(opció 3: Estudiant)");
                    op = llegirEnter();

                    switch (op) {
                        case 1:
                            col = Collectius.PDI;
                            opCorrecta = true;
                            break;

                        case 2:
                            col = Collectius.PTGAS;
                            opCorrecta = true;
                            break;

                     
                        case 3:
                            col = Collectius.ESTUDIANT;
                            opCorrecta = true;
                            break;

                        default:
                            System.out.println("L'opció introduïda no existeix, prova una altra.");
                    }
                }
                
                llista = llistaUsuaris.tipusLlista(col);
                System.out.println(llista);
                res = true;
            }else{
                System.out.println("L'opció introduida no es valida, escull una diferent");
            }
        }
    }


    //metodo para pedir una fecha y comprobar que sea correcta
    private static Data demanarData() throws ValorInexistent {
        boolean res = false;
        int diaAux, mesAux, anyAux;
        Data aux = null;


        while (!res){
            System.out.println("Dia?");
            diaAux = llegirEnter();
            System.out.println("Mes?");
            mesAux = llegirEnter();
            System.out.println("Any?");
            anyAux = llegirEnter();


            try{
                aux = new Data(diaAux, mesAux, anyAux);
                res = true;
            }catch(ValorInexistent e){
                System.out.println("La data no existeix, proba amb una altra\n");
            }
        }


        return aux;
    }


    //metodo para pedir el dia de la semana en que se hace una actividad
    private static DiaSetmana demanarDia(){
        String aux;
        boolean res = false;
        DiaSetmana dia = null;


        while (!res){
            System.out.println("\nEn quin dia de la setmana es farà l'activitat? (escrit en català i en minúscula)");
            aux = teclat.nextLine();


            try{
                dia = DiaSetmana.valueOf(aux.toUpperCase());
                res = true;
            }catch(IllegalArgumentException e){
                System.out.println("El dia no existeix, torna-ho a probar");
            }
        }


        return dia;
    }


    //metodo para pedir el dia y la hora de la actividad
    private static Data demanarDiaYHora() {
        boolean res = false;
        int dia, mes, any, hora, min;
        Data data = null;
       
        System.out.println("\nEscull l'horari setmanal de l'activitat i el primer dia que es farà");
        
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


            try {
                data = new Data(dia, mes, any, hora, min);
                res = true;
            }catch (ValorInexistent e){
                System.out.println("La data o l'hora no existeixen, proba amb una altra\n");
            }
   
        }

        return data;
    }

}