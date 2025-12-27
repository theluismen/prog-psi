/**
 * Autor(@s): Alexandra Núñez
 * Descripción: esta excepcion sirve para las situaciones en que se busca una actividad y esta no existe.
 * Se puede buscar por cualquier valor como tipo de actividad o id de esta
 */
package excepcions;

public class ActivitatDesconeguda extends Exception{

    /**
    * Constructor para indicar la excepcion
    * @param act valor de busqueda de la actividad
    */
    public ActivitatDesconeguda (String act) {
        super("ERROR: El valor d'activitat demanat no existeix: " + act);
    }
    
}
