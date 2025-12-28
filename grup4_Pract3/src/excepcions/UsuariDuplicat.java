/**
 * Autor: Aesha Naz Mahmood Bibi
 * Descripció: exepció que indica que s'ha intentat afegir un usuari amb un 
 * àlies que ja existeix a la llista
 */
package excepcions;

public class UsuariDuplicat extends Exception {

    /**
     * Constructor de l'excepció UsuariDuplicat que indica 
     * que s'ha intentat afegir un usuari, peró l'àlies ja existeix
     * 
     * @param alies àlies duplicat
     */
    public UsuariDuplicat (String alies) {
        super("ERROR: Ja existeix un usuari amb l'àlies: " + alies);
    }
}