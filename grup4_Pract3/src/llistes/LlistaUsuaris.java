package llistes;
import usuaris.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



public class LlistaUsuaris {
    
    private Usuari[] usuaris;   //array q conté els usuaris
    private int compt;          //nombre d'usuaris guardats

    /**
     * Constructor que inicialitza la llista amb capacitat inicial 10
     */
    public LlistaUsuaris() {
        usuaris = new Usuari[10];  //capacitat inicial?
        compt = 0;
    }

    //Metodes

    /**
     * Afegeix un usuari a la llista de forma ordenada pel seu alies
     * No es poden afegir duplicats
     * 
     * @param u usuari que es vol llegir 
     * @throws IllegalArgumentException si ja existeix un usuari amb el mateix alies
     */
    public void afegirUsuari(Usuari u) {
        if(existeix(u.getAlies())) {
            throw new IllegalArgumentException( "ERROR: Ja existeix un usuari amb aquest àlies " + u.getAlies() );
        }

        if (compt == usuaris.length) {
            ampliar();
        }

        //Insercio ordenada per alies
        int pos = compt - 1;
        while(pos >= 0 && usuaris[pos].getAlies().compareTo(u.getAlies()) > 0) {
            usuaris[pos + 1] = usuaris[pos];
            pos --;
        }
        usuaris[pos + 1 ] = u;
        compt++;

    }


    /**
     * Cerca un usuari pel seu alies
     * 
     * @param alies àlies de l'usuari
     * @return l'usuari si es troba, sino null
     */
    public Usuari cerca(String alies) {
        for(int i = 0; i < compt; i++) {
            if (usuaris[i].getAlies().equalsIgnoreCase(alies)) {
                return usuaris[i];
            }
        }
        return null;
        
    }

    /**
     * Metode que detemina si existeix un usuari amb l'àlies indicat
     * 
     * @param alies àlies de l'usuari per comprovar
     * @return true si la cerca retorna un usuari diferent de null sino false
     */
    public boolean existeix(String alies) {
        Usuari trobat = cerca(alies);
        return trobat != null;
    }


    /**
     *  Retorna el nombre d'usuaris guardats a la llista
     * 
     * @return nombre d'usuaris
     */
    public int getNumUsuaris() {
        return compt;
    }


    /**
     * Metode que mostra per pantalla tots els usuaris de la llista
     */
    public void mostrarU() {
        for(int i = 0; i < compt; i++) {
            System.out.println(usuaris[i]);
        }
    }


    /**
     * Metode que mostra els usuaris que pertanyen a un col·lectiu indicat
     * 
     * @param col col·lectiu (Estudiant, PDI, PTGAS)
     */
    public void mostrarUCollectiu(String col) {
        for(int i = 0; i < compt; i++) {
            if(usuaris[i].getCollectiu().equalsIgnoreCase(col)) {
                System.out.println(usuaris[i]);
            }
        }
    }



    /**
     * Metode que carrega usuaris des d'un fitxer de text, cada línia representa un usuari que separa les dades amb ";"
     * i segons el col·lectiu es crearà l'objecte 
     * 
     * @param nomFitxer nom del fitxer a carregar
     * @throws IOException excepcio si hi han problemes de lectura del fitxer
     * @throws IllegalArgumentException excepcio per si hi ha un col·lectiu desconegut
     */
    public void carregaFitxer(String nomFitxer) throws IOException  {
        BufferedReader br = new BufferedReader(new FileReader(nomFitxer));
        String linia;

        while  ((linia = br.readLine()) != null) {
            String[] dades = linia.split(";");

            String alies = dades[0];
            String email = dades[1];
            String col = dades[2];

            Usuari u;

            switch (col) {
                case "Estudiant":
                    u = new Estudiant(alies, email, dades[3], Integer.parseInt(dades[4]));
                    break;
                case "PDI":
                    u = new PDI(alies, email, dades[3], dades[4]);
                    break;
                case "PTGAS":
                    u = new PTGAS(alies, email, dades[3]);
                    break;
                default:
                    throw new IllegalArgumentException("Col·lectiu desconegur: " + col);


            }

            afegirUsuari(u);
        }

        br.close();
    }
 


    //Metode auxiliar
    /**
     * Metode que duplica la capacitat de l'array d'usuaris si d'arriba al limit
     */
    private void ampliar() {
        Usuari[] nou = new Usuari[usuaris.length *2];
        for(int i =0; i < compt; i++) {
            nou[i] = usuaris[i];
        }
        usuaris = nou;
    }
}
