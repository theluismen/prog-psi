//Aesha Naz
package excepcions;

public class FormatInvalid extends Exception {

    /**
     * Constructor de l'excepció FormatInvalid
     * Especifica un missatge
     * 
     * @param missatge descripció del problema en el format
     */
    public FormatInvalid(String missatge) {
        super("ERROR de format en el fitxer: " + missatge);
    }
}