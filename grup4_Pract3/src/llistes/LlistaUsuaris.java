/**
 * Autor: Aesha Naz Mahmood Bibi
 * Descripció: Clase que gestiona una llista d'usuaris del sistema
 * Permet, afegir, cercar, filtrar i carregar usuaris des s'un fitxer,
 * així com obtenir informació sobre el conujnt d'usuaris emmagatzemats.
 */

package llistes;

import enumeraciones.*;
import excepcions.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import usuaris.*;

public class LlistaUsuaris {
    
    /** Atributs de la classe */
    private Usuari[] usuaris;   // Array que conté els usuaris de la llista
    private int compt;          // Nombre d'usuaris emmagatzemats (actualment)

    /**
     * Constructor que inicialitza la llista amb capacitat inicial de 10 usuaris
     */
    public LlistaUsuaris() {
        usuaris = new Usuari[10];
        compt = 0;
    }

    /** Getters */

    /**
     *  Métode que retorna el nombre d'elements guardats a la llista
     * 
     * @return nombre d'elements
     */
    public int getNumElements() {
        return getNumUsuaris();
    }

    /**
     *  Métode que retorna el nombre d'usuaris guardats a la llista
     * 
     * @return nombre d'usuaris
     */
    public int getNumUsuaris() {
        return compt;
    }

    /**
     * Métode que retorna una còpia de l'usuari situat
     * a la posició indicada
     * 
     * @param n posició de l'usuari
     * @return còpia de l'usuari
     */
    public Usuari getUsuariIesim(int n){
        return this.usuaris[n].copia();
    }

    /** Métodes */

    /**
     * Métode que afegeix un usuari a la llista de forma ordenada pel seu àlies
     * No es poden afegir duplicats
     * 
     * @param u usuari que es vol llegir 
     * @throws UsuariDuplicat excepcio si ja existeix un usuari amb el mateix alies
     */
    public void afegir(Usuari u) throws UsuariDuplicat {
        if(existeix(u.getAlies())) {
            throw new UsuariDuplicat(u.getAlies());
        }

        if (compt == usuaris.length) {
            ampliar();
        }

        //Inserció ordenada per alies
        int pos = compt - 1;
        while(pos >= 0 && usuaris[pos].getAlies().compareTo(u.getAlies()) > 0) {
            usuaris[pos + 1] = usuaris[pos].copia();
            pos --;
        }
        usuaris[pos + 1 ] = u;
        compt++;
    }

    /**
     * Métode que cerca un usuari pel seu alies
     * 
     * @param alies àlies de l'usuari
     * @return l'usuari si existeix, sinó null
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
     * Métode que detemina si existeix un usuari amb l'àlies indicat
     * 
     * @param alies àlies de l'usuari per comprovar
     * @return true si existeix, false en cas contrari
     */
    public boolean existeix(String alies) {
        return cerca(alies) != null;
    }

    /**
     * Métode que mostra per pantalla tots els usuaris de la llista.
     * 
     * @return cadena amb la informació dels usuaris
     */
    @Override
    public String toString() {
        String aux = "";
        for(int i = 0; i < compt; i++) {
            aux += usuaris[i] + "\n";
        }
        return aux;
    }

    /**
     * Métode que mostra els usuaris que pertanyen a un col·lectiu determinat
     * 
     * @param col col·lectiu (Estudiant, PDI, PTGAS)
     */
    public void mostrarUCollectiu(Collectius col) {
        for(int i = 0; i < compt; i++) {
            if(usuaris[i].getCollectiu() == col) {
                System.out.println(usuaris[i] + "\n");
            }
        }
    }

    /**
     * Métode que carrega usuaris des d'un fitxer de text.
     * Cada línia del fitxer representa un usuari amb el format especificat
     * segons el seu col·lectiu
     * 
     * 
     * @param nomFitxer nom del fitxer
     * @throws IOException excepcio si no es pot llegir el fitxer
     * @throws FormatInvalid excepcio si el format és incorrecta
     * @throws CollectiuDesconegut excepcio si el col·lectiu no és reconegut
     * @throws UsuariDuplicat excepcio si un àlies ja existeix
     */
    public void carregaFitxer(String nomFitxer) throws IOException, FormatInvalid, CollectiuDesconegut, UsuariDuplicat  {

        try (BufferedReader br = new BufferedReader(new FileReader(nomFitxer))) {
            String linia;
        
            while  ((linia = br.readLine()) != null) {

                String[] dades = linia.split(";");

                if (dades.length < 3) {
                    throw new FormatInvalid("Falten camps obligatoris a la línia: " + linia);
                }

                String alies = dades[0];
                String email = dades[1];

                Collectius col;
                try {
                    col = Collectius.valueOf(dades[2].toUpperCase());
                } catch (IllegalArgumentException e) {
                    throw new CollectiuDesconegut(dades[2]);
                }

                Usuari u;

                switch (col) {
                    case ESTUDIANT:
                        if (dades.length != 5){
                            throw new FormatInvalid("Format uncorrecte per Estudiant: " + linia);
                        }

                        String ensenyament = dades[3];
                        int anyInici = Integer.parseInt(dades[4]);

                        u = new UsuariEstudiant(alies, email, col, ensenyament, anyInici);
                        break;

                    case PDI:

                        if (dades.length != 5){
                            throw new FormatInvalid("Format uncorrecte per PDI: " + linia);
                        }

                        DepartamentURV dept = DepartamentURV.valueOf(dades[3].toUpperCase());
                        CampusURV campPDI = CampusURV.valueOf(dades[4].toUpperCase());
                    
                        u = new UsuariPDI(alies, email, col, dept, campPDI);
                        break;

                    case PTGAS:

                        if (dades.length != 4){
                            throw new FormatInvalid("Format uncorrecte per PTGAS: " + linia);
                        }

                        CampusURV campusPTGAS = CampusURV.valueOf(dades[3].toUpperCase());
                        
                        u = new UsuariPTGAS(alies, email, col, campusPTGAS);
                        break;
                        
                    default:
                        throw new CollectiuDesconegut("Col·lectiu desconegut: " + col);
                }
                afegir(u);
            }
        }
    }
 
    /**
     * Métode que duplica la capacitat de l'array d'usuaris si arriba al límit
     */
    private void ampliar() {
        Usuari[] nou = new Usuari[usuaris.length *2];
        for(int i =0; i < compt; i++) {
            nou[i] = usuaris[i];
        }
        usuaris = nou;
    }

    /**
     * Métode que retorna una nova llista de usuaris amb només els usuaris d'un col·lectiu
     * 
     * @param col col·lectiu dels usuaris
     * @return nova llista amb els usuaris filtrats
     */
    public LlistaUsuaris tipusLlista(Collectius col){
        LlistaUsuaris nova = new LlistaUsuaris();

        for (int i = 0; i < this.compt; i++){
            if (usuaris[i].getCollectiu() == col){
                try {
                    nova.afegir(this.usuaris[i]);
                } catch(UsuariDuplicat e){
                    
                }
            }
        }
        return nova;
    }


    /**
    * Retorna la llista d'usuaris en format CSV.
     * Cada usuari ocupa una línia del CSV.
     *
     * @return cadena amb els usuaris en format CSV
    */
    public String toCSV() {
        String aux = "";

        for (int i = 0; i < compt; i++) {
            aux += usuaris[i].toCSV() + "\n";
        }

        return aux;
    }

    /**
     * Mètode que guarda la llista d'usuaris en un fitxer CSV
     * 
     * @param nomFitxer nom del fitxer csv on es guardaran els usuaris
     * @throws IOException si hi ha algun error d'escriptura del fitxer
     */
    public void guardarLlista (String nomFitxer) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nomFitxer))) {
            
            for (int i = 0; i < compt; i++) {
                bw.write(usuaris[i].toCSV());
                bw.newLine();
            }
        }
    }
}