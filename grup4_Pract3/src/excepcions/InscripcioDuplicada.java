/**
 * Autor: Ikram Hallouz
 * Descripción: Clase que representa la excepció d'una inscripció duplicada.
 */

package excepcions;

public class InscripcioDuplicada extends Exception {
    /**
     * Constructor de l'excepció InscripcioDuplicada
     * Especifica un missatge
     * 
     * @param idUsuari identificador de l'usuari
     * @param idActivitat identificador de l'activitat
     */
    public InscripcioDuplicada(String idUsuari, String idActivitat) {
        super("ERROR: L'usuari " + idUsuari + " ja està inscrit a l'activitat " + idActivitat);
    }
}
