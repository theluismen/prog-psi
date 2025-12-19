/**
 * Autor: Ikram Hallouz
 * Descripción: Clase que representa la excepció d'una inscripció no trobada.
 */

package excepcions;

public class InscripcioNoTrobada extends Exception {

    public InscripcioNoTrobada(String idUsuari, String idActivitat) {
        super("ERROR: L'usuari " + idUsuari + " no està inscrit a l'activitat " + idActivitat);
    }
}
