/**
 * Autor(@s): Alexandra Núñez
 * Descripción: esta excepcion sirve para referir principalmente a la situación en que se intenta añadir
 * una actividad nueva a una lista en la cual ya existe una actividad con el mismo nombre.
 * No se han considerado otros usos para esta excepcion
 */
package excepcions;

public class ActivitatDuplicada extends Exception {

    /**
     * Constructor para indicar la excepcion
     * @param act nombre de la actividad duplicada
     */
    public ActivitatDuplicada (String act) {
        super("ERROR: Ja existeix una activitat amb el nom: " + act);
    }
    
}
