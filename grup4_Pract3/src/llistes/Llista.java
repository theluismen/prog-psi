//Aesha Naz
package llistes;

/**
 * Interfície que defineix el comportament bàsic 
 * de qualsevol llista de l'aplicació
 * 
 * @param <T> tipus d'elements que conté la llista
 */
public interface Llista<T> {
    
    /**
     * Metode que afegeix un element a la llista
     * 
     * @param element element a afegir
     * @throws Exception si l'element no es pot afegir
     */
    void afegir(T element) throws Exception;

    /**
     * Metode que cerca un element de la llista amb un identificador
     * 
     * @param id identificador de l'element
     * @return l'element si existeix, sino null
     */
    T cerca(String id);

    /**
     * Metode que comprova si existeix un element amb l'identificador
     * 
     * @param id identificador a comprovar
     * @return true si existeix, sino false
     */
    boolean existeix(String id);

    /**
     * Metode que retorna el nombre d'elements de la llista
     * 
     * @return nombre d'elements
     */
    int getNumElements();

    /**
     * Metode que mostra tots els elements de la llista
     */
    void mostrar();

}