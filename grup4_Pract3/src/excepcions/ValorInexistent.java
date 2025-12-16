/**
 * Autor(@s): Alexandra Núñez
 * Descripción: esta excepcion sirve para referir a cualquier caso en que el rango de valores no es aceptable
 * como la hora o los dias del mes. La idea es enviar por parametro al constructor el rango de valores aceptables
 * en formato String o el nombre del "rango" (por ejemplo, "dia, mes, año")
 */
package excepcions;

public class ValorInexistent extends RuntimeException {
    
    /**
     * Contructor
     * @param rang
     */
    public ValorInexistent(String rang){
        super("El valor no existeix en el rang corresponent:\t"+rang);
    }
}
