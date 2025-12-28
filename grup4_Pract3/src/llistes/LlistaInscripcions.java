package llistes;

import java.io.*;

import excepcions.InscripcioDuplicada;
import excepcions.InscripcioNoTrobada;
import inscripcions.Inscripcio;

/**
 * Classe que gestiona la llista d'inscripcions fent servir un array dinàmic
 * Implementa Serializable per poder guardar-se directament al fitxer .dat
 */
public class LlistaInscripcions implements Serializable {

    private static final long serialVersionUID = 1L;

    // Atributs
    private Inscripcio[] llista; // Array on guardarem les inscripcions
    private int nElems;          // Número real d'elements que tenim
    private int capacitat;       // Mida total de l'array actual

    /**
     * Constructor.
     * Inicialitza la llista amb una capacitat inicial (10, però augmenta si és necessari)
     */
    public LlistaInscripcions() {
        this.llista = new Inscripcio[10];
        this.nElems = 0;
        this.capacitat = 10;
    }

    // --- MÈTODES DE GESTIÓ BÀSICS I AUXILIARS ---

    /**
     * Retorna el número d'elements actuals a la llista.
     * Necessari per recórrer la llista quan es retorna com a objecte.
     * @return enter amb el nombre d'elements.
     */
    public int getNumElements() {
        return nElems;
    }

    /**
     * Retorna la inscripció de la posició n.
     * Necessari per accedir als elements quan es retorna la llista com a objecte.
     * @param n Índex de l'element.
     * @return Còpia de la inscripció.
     * @throws IndexOutOfBoundsException si l'índex no és vàlid.
     */
    public Inscripcio getInscripcioIesima(int n) {
        if (n < 0 || n >= nElems) {
            throw new IndexOutOfBoundsException("Índex fora de rang: " + n);
        }
        return llista[n].copia();
    }

    /**
     * Afegeix una inscripció al final de la llista.
     * @param ins Objecte Inscripcio a afegir.
     * @throws InscripcioDuplicada Si l'usuari ja està inscrit a aquesta activitat.
     */
    public void afegirInscripcio(Inscripcio ins) throws InscripcioDuplicada {
        // 1. PRIMER comprovem si ja existeix
        if (existeix(ins.getIdUsuari(), ins.getIdActivitat())) {
            throw new InscripcioDuplicada(ins.getIdUsuari(), ins.getIdActivitat());
        }

        // 2. Si no existeix, fem la lògica normal d'afegir
        if (nElems >= llista.length) {
            ampliarCapacitat();
        }
        llista[nElems] = ins.copia();
        nElems++;
    }

    /**
     * Mètode privat per duplicar la mida de l'array quan s'omple
     */
    private void ampliarCapacitat() {
        int novaCapacitat = capacitat + 10;
        Inscripcio[] nouArray = new Inscripcio[novaCapacitat];
        for (int i = 0; i < nElems; i++) {
            nouArray[i] = llista[i];
        }
        llista = nouArray;
        capacitat = novaCapacitat;
    }

    /**
     * Elimina una inscripció concreta donat l'usuari i l'activitat.
     * @param idUsuari Àlies de l'usuari
     * @param idActivitat Nom de l'activitat
     * @throws InscripcioNoTrobada Si no existeix la inscripció.
     */
    public void eliminarInscripcio(String idUsuari, String idActivitat) throws InscripcioNoTrobada {
        int pos = cercaPosicio(idUsuari, idActivitat);

        if (pos == -1) {
            // Si no el trobem, llancem l'error
            throw new InscripcioNoTrobada(idUsuari, idActivitat);
        }

        // Si el trobem, fem la lògica d'esborrar i moure
        for (int i = pos; i < nElems - 1; i++) {
            llista[i] = llista[i + 1];
        }
        llista[nElems - 1] = null;
        nElems--;
    }

    /**
     * Cerca la posició d'una inscripció a l'array.
     * @param idUsuari Identificador de l'usuari.
     * @param idActivitat Identificador de l'activitat.
     * @return índex de la inscripció o -1 si no la troba.
     */
    private int cercaPosicio(String idUsuari, String idActivitat) {
        for (int i = 0; i < nElems; i++) {
            // Comparem Strings amb equalsIgnoreCase o equals
            if (llista[i].getIdUsuari().equalsIgnoreCase(idUsuari) &&
                llista[i].getIdActivitat().equalsIgnoreCase(idActivitat)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Comprova si existeix una inscripció.
     * @param idUsuari Identificador de l'usuari.
     * @param idActivitat Identificador de l'activitat.
     * @return true si existeix, false si no.
     */
    public boolean existeix(String idUsuari, String idActivitat) {
        // Uso el mètode cercaPosicio per saber si hi és
        return cercaPosicio(idUsuari, idActivitat) != -1;
    }

    /**
     * Retorna l'objecte Inscripcio per poder consultar-lo o modificar-lo (ex: posar nota).
     * @param idUsuari Identificador de l'usuari.
     * @param idActivitat Identificador de l'activitat.
     * @return L'objecte Inscripcio trobat o null si no existeix.
     */
    public Inscripcio getInscripcio(String idUsuari, String idActivitat) {
        int pos = cercaPosicio(idUsuari, idActivitat);
        if (pos != -1) {
            return llista[pos];
        }
        return null; // Si recorro tota la llista i no hi és, retorno null
    }

    /**
     * Compta quantes persones hi ha inscrites a una activitat concreta.
     * Útil per saber si l'activitat està plena.
     * @param idActivitat Identificador de l'activitat.
     * @return Nombre total d'inscripcions per a l'activitat.
     */
    public int comptarInscripcionsActivitat(String idActivitat) {
        int comptador = 0;
        for (int i = 0; i < nElems; i++) {
            if (llista[i].getIdActivitat().equalsIgnoreCase(idActivitat)) {
                comptador++;
            }
        }
        return comptador; // Al final retorno el total que he comptat
    }

    // --- MÈTODES QUE ARA RETORNEN LlistaInscripcions  ---

    /**
     * Retorna una llista amb totes les inscripcions d'un usuari.
     * @param idUsuari Identificador de l'usuari.
     * @return LlistaInscripcions amb els elements trobats.
     */
    public LlistaInscripcions getInscripcionsUsuari(String idUsuari) {
        LlistaInscripcions resultat = new LlistaInscripcions();
        for (int i = 0; i < nElems; i++) {
            if (llista[i].getIdUsuari().equals(idUsuari)) {
                try {
                    // Afegim a la nova llista (ja fa copia() dins d'afegirInscripcio)
                    resultat.afegirInscripcio(llista[i]);
                } catch (InscripcioDuplicada e) {
                    // No hauria de passar mai aquí perquè filtrem d'una llista vàlida
                }
            }
        }
        return resultat;
    }

    /**
     * Retorna totes les inscripcions d'una activitat concreta ordenades per antiguitat
     * Útil per calcular qui entra i qui està en llista d'espera
     * @param idActivitat Nom de l'activitat
     * @return LlistaInscripcions amb els elements trobats
     */
    public LlistaInscripcions getInscripcionsActivitat(String idActivitat) {
        LlistaInscripcions resultat = new LlistaInscripcions();
        for (int i = 0; i < nElems; i++) {
            if (llista[i].getIdActivitat().equalsIgnoreCase(idActivitat)) {
                try {
                    resultat.afegirInscripcio(llista[i]);
                } catch (InscripcioDuplicada e) {
                    // Ignorar
                }
            }
        }
        return resultat;
    }

    // --- MÈTODES PER GESTIONAR AFORAMENT I LLISTA D'ESPERA ---

    /**
     * Comprova si una activitat ha arribat al seu límit de places.
     * @param idActivitat Nom de l'activitat.
     * @param placesMaximes Aforament màxim de l'activitat.
     * @return true si està plena (o hi ha llista d'espera), false si queda lloc.
     */
    public boolean activitatEstaPlena(String idActivitat, int placesMaximes) {

        if (placesMaximes == Integer.MAX_VALUE) return false;

        return comptarInscripcionsActivitat(idActivitat) >= placesMaximes;
    }

    /**
     * Retorna quantes persones hi ha en llista d'espera per a una activitat.
     * @param idActivitat Nom de l'activitat.
     * @param placesMaximes Aforament màxim de l'activitat.
     * @return 0 si no hi ha ningú en espera, o el número de persones que sobren.
     */
    public int getNumEnEspera(String idActivitat, int placesMaximes) {
        int totalInscrits = comptarInscripcionsActivitat(idActivitat);

        if (totalInscrits > placesMaximes) {
            return totalInscrits - placesMaximes;
        }
        return 0; // No hi ha ningú en espera
    }

    /**
     * Metode que retorna quantes places de les disponibles estan ocupades sense tenir en compte la llista d'espera
     * @param idActivitat nom de l'activitat a evaluar
     * @param placesMaximes maxim nombre de places de l'activitat
     * @return places oficials ocupades
     */
    public int getNumAdmesos(String idActivitat, int placesMaximes){
        int totalAdmesos = comptarInscripcionsActivitat(idActivitat);

        if (activitatEstaPlena(idActivitat, placesMaximes)) {
            return placesMaximes;
        }
        return totalAdmesos; // No hi ha ningú en espera
    }

    /**
     * Retorna LlistaInscripcions NOMÉS amb les inscripcions que estan EN ESPERA.
     * (Les que han arribat després d'omplir les places).
     * @param idActivitat Identificador de l'activitat.
     * @param placesMaximes Nombre màxim de places.
     * @return LlistaInscripcions amb els usuaris en espera.
     */
    public LlistaInscripcions getLlistaEspera(String idActivitat, int placesMaximes) {
        // 1. Obtenim tots els inscrits ordenats per arribada (ara és un objecte LlistaInscripcions)
        LlistaInscripcions tots = getInscripcionsActivitat(idActivitat);
        LlistaInscripcions espera = new LlistaInscripcions();

        int numEspera = getNumEnEspera(idActivitat, placesMaximes);

        if (numEspera == 0) return espera; // Llista buida

        // 2. Afegim només els que sobren (a partir de l'índex 'placesMaximes')
        // Usem getNumElements() i getInscripcioIesima()
        for (int i = placesMaximes; i < tots.getNumElements(); i++) {
            try {
                espera.afegirInscripcio(tots.getInscripcioIesima(i));
            } catch (InscripcioDuplicada e) {
                // Ignorar
            }
        }
        return espera;
    }

    /**
     * Retorna LlistaInscripcions NOMÉS amb les inscripcions admeses (dins del límit).
     * @param idActivitat Identificador de l'activitat.
     * @param placesMaximes Nombre màxim de places.
     * @return LlistaInscripcions amb els usuaris admesos.
     */
    public LlistaInscripcions getAdmesos(String idActivitat, int placesMaximes) {
        LlistaInscripcions tots = getInscripcionsActivitat(idActivitat);
        LlistaInscripcions admesos = new LlistaInscripcions();

        int total = tots.getNumElements();

        // Si n'hi ha menys que el límit, tots són admesos. Si més, tallem al límit.
        int numAdmesos = (total < placesMaximes) ? total : placesMaximes;

        for (int i = 0; i < numAdmesos; i++) {
            try {
                admesos.afegirInscripcio(tots.getInscripcioIesima(i));
            } catch (InscripcioDuplicada e) {
                // Ignorar
            }
        }
        return admesos;
    }

    /**
     * Guarda la llista serialitzada (l'objecte sencer) al fitxer.
     * @param rutaFitxer Ruta on es guardarà el fitxer .dat.
     */
    public void guardarLlista(String rutaFitxer) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaFitxer));
            oos.writeObject(this); // Guardem "this" (la llista sencera amb l'array i el nElems)
            oos.close();
        } catch (IOException e) {
            System.out.println("Error guardant inscripcions: " + e.getMessage());
        }
    }

    /**
     * Carrega la llista des del fitxer i la retorna.
     * És static perquè crea una nova instància de la llista.
     * @param rutaFitxer Ruta del fitxer .dat a carregar.
     * @return La LlistaInscripcions llegida o una buida si no existeix.
     */
    public static LlistaInscripcions carregarFitxer(String rutaFitxer) {
        LlistaInscripcions llistaLlegida = new LlistaInscripcions();

        File f = new File(rutaFitxer);
        if (f.exists()) {
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaFitxer));
                llistaLlegida = (LlistaInscripcions) ois.readObject();
                ois.close();
            } catch (Exception e) {
                System.out.println("Error carregant inscripcions: " + e.getMessage());
            }
        }
        return llistaLlegida;
    }

    /**
     * Retorna una representació en text de totes les inscripcions de la llista.
     * @return String amb les dades de les inscripcions, una per línia.
     */
    @Override
    public String toString() {
        // Creo un StringBuilder per anar muntant el text
        // Recorro la llista i vaig afegint el toString() de cada inscripció seguit d'un salt de línia
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nElems; i++) {
            sb.append(llista[i].toString()).append("\n");
        }
        return sb.toString(); // Retorno el text final complet
    }
}