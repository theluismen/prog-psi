/**
 * Autor: Aesha Naz Mahmood Bibi
 * Descripció: classe excepció Col·lectiu Desconegut
 */
package excepcions;

public class CollectiuDesconegut extends Exception {

    /**
     * Constructor de l'excepcio Col·lectiu desconegut
     * 
     * @param col nom del col·lectiu invàlid
     */
    public CollectiuDesconegut(String col) {
        super("Col·lectiu deconegut: " + col);
    }
}