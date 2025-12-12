//Aesha Naz
package llistes;
import enumeraciones.*;
import excepcions;
import usuaris.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LlistaUsuaris {
    
    private Usuari[] usuaris;   //array que conté els usuaris
    private int compt;          //nombre d'usuaris guardats

    /**
     * Constructor que inicialitza la llista amb capacitat inicial 10
     */
    public LlistaUsuaris() {
        usuaris = new Usuari[10];  //capacitat inicial?
        compt = 0;
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
     * Afegeix un usuari a la llista de forma ordenada pel seu alies
     * No es poden afegir duplicats
     * 
     * @param u usuari que es vol llegir 
     * @throws UsuariDuplicat excepcio si ja existeix un usuari amb el mateix alies
     */
    public void afegirUsuari(Usuari u) throws UsuariDuplicat {
        if(existeix(u.getAlies())) {
            throw new UsuariDuplicat(u.getAlies());
        }

        if (compt == usuaris.length) {
            ampliar();
        }

        //Inserció ordenada per alies
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
        return cerca(alies) != null;
    }

    /**
     * Metode que mostra per pantalla tots els usuaris de la llista
     */
    public void mostrarU() {
        for(int i = 0; i < compt; i++) {
            System.out.println(usuaris[i] + "\n");
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
                System.out.println(usuaris[i] + "\n");
            }
        }
    }

    /**
     * Metode que carrega usuaris des d'un fitxer de text
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
                String col = dades[2];

                Usuari u;

                switch (col) {

                    case "Estudiant":
                        if (dades.length != 5){
                            throw new FormatInvalid("Format uncorrecte per Estudiant: " + linia);
                        }

                        String ensenyament = dades[3];
                        u = new UsuariEstudiant(alies, email, ensenyament, Integer.parseInt(dades[4]));
                        break;

                    case "PDI":

                    if (dades.length != 5){
                            throw new FormatInvalid("Format uncorrecte per PDI: " + linia);
                        }
                        DepartamentURV dept = DepartamentURV.valueOf(dades[3]);
                        CampusURV campPDI = CampusURV.valueOf(dades[4]);
                    
                        u = new UsuariPDI(alies, email, col, dept, campPDI);
                        break;

                    case "PTGAS":

                        if (dades.length != 4){
                            throw new FormatInvalid("Format uncorrecte per PTGAS: " + linia);
                        }

                        CampusURV campusPTGAS = CampusURV.valueOf(dades[3]);
                        u = new UsuariPTGAS(alies, col, email, campusPTGAS);
                        break;
                        
                    default:
                        throw new CollectiuDesconegut("Col·lectiu desconegut: " + col);


                }
                afegirUsuari(u);
            }
        br.close();
        }
    }
 
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