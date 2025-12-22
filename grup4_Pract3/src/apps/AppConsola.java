/**
 * Autor(@s):   Ainara Sofia Cabrera Robles
                Ikram Kheira Hallouz Safa
                Aesha Naz Mahmood Bibi
                Alexandra Núñez González
 * Descripción:
 */
package apps;


import java.util.Scanner;


import javax.xml.bind.ValidationException;


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
                    // Indicar/Canviar la data del sistema
                    case1();
                    break;


                case 2:
                    // Mostrar les dades de les llistes
                    case2();
                    break;


                // --- CONSULTES ---
                case 3:
                    // Activitats en període d'inscripció amb places disponibles
                    case3();
                    break;


                case 4:
                    // Activitats que tenen classe AVUI
                    case4();
                    break;


                case 5:
                    // Activitats actives AVUI (dins període)
                    case5();
                    break;


                case 6:
                    // Activitats amb places disponibles (qualsevol data)
                    case6();
                    break;


                case 7:
                    // Detall d'una activitat pel seu nom
                    case7();
                    break;


                case 8:
                    // Detall d'un usuari pel seu àlies
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
                    // Afegir nova activitat periòdica
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
                    // Usuari més actiu d'un col·lectiu
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


    private static void case9(){
        System.out.println("\n--- Activitats on està apuntat un usuari ---");
        System.out.println("Introdueix l'àlies de l'usuari: ");

        String alies = teclat.nextLine();

        Usuari u = llistaUsuaris(cerca(alies));

        if (u == null) {
            System.out.println("No existeix cap usuari amb aquest àlies. ");
            return;
        }

        Inscripcio[] ins = llistaInscripcions.getInscripcionsUsuari(alies);

        if(ins.length == 0) {
            System.out.println("Aquest usuari no està inscrit a cap activitat.");
            return;
        }

        System.out.println("\nActivitats on està inscrit" + alies + ":");
        for (int i = 0; i < ins.length; i++) {
            Activitat act = llistaActivitats.cerca(ins[i].getIdActivitat());
            if(act != null){
                System.out.println("-" + act.getNom());
            }
        }
       

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


    private static void case15(){
       
    }


    private static void case16(){
       
    }


    private static void case17(){
        boolean hiHa = false;

        System.out.println("\n--- Resum valoracions d'activitats acabades ---");

        for (int i = 0; i < llistaActivitats.getNumElements(); i++) {
            Activitat act;
            try {
                act = llistaActivitats.getActivitatIesima(i);
            } catch (ValorInexistent e) {
                continue;
            }

            if (act.getDataFinal().esDataInferiorOigual(dataActual)) {
                Inscripcio[] ins = llistaInscripcions.comptarInscripcionsActivitat(act.getNom());

                int suma = 0;
                int comptador = 0;

                for(int j = 0; j < ins.length; j++) {
                    if(ins[j].esValorada()) {
                        suma += ins.getValoracio();
                        comptador++;
                    }
                }

                if (comptador > 0) {
                    double mitjana = (double) suma / comptador;
                    System.out.println("Activitat: %s | Mitjana: %.2f\n", act.getNom(), mitjana);                
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

        Usuari u = llistaUsuaris(cerca(alies));

        if (u == null) {
            System.out.println("No existeix cap usuari amb aquest àlies. ");
            return;
        }

        Inscripcio[] ins = llistaInscripcions.getInscripcionsUsuari(alies);

        boolean hiHa = false;
        for(int i = 0; i < ins.length; i++) {
            if(ins[i].esValorada()) {
                System.out.println("- Activitat: " + ins[i].getIdActivitat() + " | Valoració: " + ins[i].getValoracio());
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


    //metodo para pedir una fecha y comprobar que sea correcta
    private static Data demanarData(){
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
    private static Data demanarDiaYHora(){
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


        try{
                data = new Data(dia, mes, any, hora, min);
                res = true;
            }catch(ValorInexistent e){
                System.out.println("La data o l'hora no existeixen, proba amb una altra\n");
            }
   
        }


        return data;
    }


}