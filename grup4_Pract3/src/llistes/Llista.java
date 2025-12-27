/**
 * Autor(@s): Aesha Naz Mahmood Bibi
 * Descripció: Interfície que defineix el comportament bàsic 
 * de qualsevol llista de l'aplicació
 */
package llistes;

public interface Llista {

    /**
     * Métode que comprova si existeix un element amb l'identificador
     * 
     * @param id identificador a comprovar
     * @return true si existeix, sino false
     */
    boolean existeix(String id);

    /**
     * Métode que retorna el nombre d'elements de la llista
     * 
     * @return nombre d'elements
     */
    int getNumElements();

    /**
     * Métode que guarda la llista en un fitxer
     * 
     * @param rutaFitxer nom o ruta del fitxer
     */
    void guardarLlista(String rutaFitxer);

}