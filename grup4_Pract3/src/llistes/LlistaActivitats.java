package llistes;

import dades.*;
import dades.Activitat; // Suposant que existeix la classe Activitat

public class LlistaActivitats {
    // Classe interna per guardar cada element
    private class Node {
        Activitat dada;
        Node seguent;
    }

    private Node primer;
    private int nElems;

    public void afegirActivitat(Activitat a) {
        // Codi per afegir manualment el node...
    }
    
    // Mètodes per buscar, eliminar, etc.
}
