/**
 * Autor: Ikram Hallouz
 * Descripción: Clase que representa la llista d'inscripcions amb un array dinàmic.
 */

package llistes;

import java.io.*;

import excepcions.InscripcioDuplicada;
import excepcions.InscripcioNoTrobada;
import inscripcions.Inscripcio;

/**
 * Classe que gestiona la llista d'inscripcions fent servir un array dinàmic
 * Implementa Serializable per poder guardar-se directament al fitxer .dat
 */
public class LlistaInscripcio implements Serializable {

    private static final long serialVersionUID = 1L;

    // Atributs
    private Inscripcio[] llista; // Array on guardarem les inscripcions
    private int nElems;          // Número real d'elements que tenim
    private int capacitat;       // Mida total de l'array actual

    /**
     * Constructor.
     * Inicialitza la llista amb una capacitat inicial (10, però augmenta si és necessari)
     */
    public LlistaInscripcio() {
        // Aquí he d'inicialitzar el comptador d'elements a 0 perquè la llista comença buida
        // He de definir una capacitat inicial per a l'array, per exemple 10 posicions
        // Finalment, he d'instanciar l'array 'llista' amb aquesta capacitat que he decidit
        this.llista = new Inscripcio[10];
        this.nElems = 0;
        this.capacitat = 10;
    }

    // --- MÈTODES DE GESTIÓ ---

    /**
     * Afegeix una inscripció al final de la llista
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
        for (int i=0; i<nElems; i++) {
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
     * Cerca la posició d'una inscripció a l'array
     * @return índex de la inscripció o -1 si no la troba
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
     * Comprova si existeix una inscripció
     * @return true si existeix, false si no
     */
    public boolean existeix(String idUsuari, String idActivitat) {
        // Uso el mètode cercaPosicio per saber si hi és
        return cercaPosicio(idUsuari, idActivitat) != -1;
    }

    /**
     * Retorna l'objecte Inscripcio per poder consultar-lo o modificar-lo (ex: posar nota)
     */
    public Inscripcio getInscripcio(String idUsuari, String idActivitat) {
        int pos = cercaPosicio(idUsuari, idActivitat);
        if (pos != -1) {
            return llista[pos];
        }
        return null; // Si recorro tota la llista i no hi és, retorno null
    }

    /**
     * Compta quantes persones hi ha inscrites a una activitat concreta
     * Útil per saber si l'activitat està plena
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

    /**
     * Retorna un array amb totes les inscripcions d'un usuari
     * Necessari per la opció "Mostrar activitats d'un usuari" cas 11 del main
     */
    public Inscripcio[] getInscripcionsUsuari(String idUsuari) {
        // 1. Comptem quantes en té per saber la mida de l'array a retornar
        int total = 0;
        for (int i = 0; i < nElems; i++) {
            if (llista[i].getIdUsuari().equals(idUsuari)) {
                total++;
            }
        }

        // 2. Creem l'array i l'omplim
        Inscripcio[] resultat = new Inscripcio[total];
        int j = 0;
        for (int i = 0; i < nElems; i++) {
            if (llista[i].getIdUsuari().equals(idUsuari)) {
                resultat[j] = llista[i].copia(); // Fem servir copia() per seguretat
                j++;
            }
        }
        return resultat;
    }

    /**
     * Retorna totes les inscripcions d'una activitat concreta ordenades per antiguitat
     * Útil per calcular qui entra i qui està en llista d'espera
     * @param idActivitat Nom de l'activitat
     * @return Array amb les inscripcions
     */
    public Inscripcio[] getInscripcionsActivitat(String idActivitat) {
        // 1. Comptem quantes n'hi ha
        int total = comptarInscripcionsActivitat(idActivitat);

        // 2. Creem l'array resultat
        Inscripcio[] resultat = new Inscripcio[total];
        int j = 0;

        // 3. Omplim l'array mantenint l'ordre original
        for (int i = 0; i < nElems; i++) {
            if (llista[i].getIdActivitat().equalsIgnoreCase(idActivitat)) {
                resultat[j] = llista[i].copia(); // Copia per seguretat
                j++;
            }
        }
        return resultat;
    }

    // -----------------------------------------------------------------
    // MÈTODES PER GESTIONAR AFORAMENT I LLISTA D'ESPERA
    // -----------------------------------------------------------------

    /**
     * Comprova si una activitat ha arribat al seu límit de places.
     * @param idActivitat Nom de l'activitat.
     * @param placesMaximes Aforament màxim de l'activitat.
     * @return true si està plena (o hi ha llista d'espera), false si queda lloc.
     */
    public boolean activitatEstaPlena(String idActivitat, int placesMaximes) {
        // Si placesMaximes és molt gran (ex: Integer.MAX_VALUE per Online), mai estarà plena
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
     * 
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
     * Retorna array NOMÉS amb les inscripcions que estan EN ESPERA.
     * (Les que han arribat després d'omplir les places).
     */
    public Inscripcio[] getLlistaEspera(String idActivitat, int placesMaximes) {
        // 1. Obtenim tots els inscrits ordenats per arribada
        Inscripcio[] tots = getInscripcionsActivitat(idActivitat);
        int numEspera = getNumEnEspera(idActivitat, placesMaximes);
        
        if (numEspera == 0) return new Inscripcio[0]; // Array buit

        // 2. Creem l'array de resultats
        Inscripcio[] espera = new Inscripcio[numEspera];
        
        // 3. Copiem només els que sobren (a partir de l'índex 'placesMaximes')
        // Exemple: si 20 places, el 21è està a l'índex 20.
        for (int i = 0; i < numEspera; i++) {
            espera[i] = tots[placesMaximes + i];
        }
        
        return espera;
    }

    /**
     * Retorna array NOMÉS amb les inscripcions admeses (dins del límit).
     */
    public Inscripcio[] getAdmesos(String idActivitat, int placesMaximes) {
        Inscripcio[] tots = getInscripcionsActivitat(idActivitat);
        int total = tots.length;
        
        // Si n'hi ha menys que el límit, tots són admesos. Si més, tallem al límit.
        int numAdmesos = (total < placesMaximes) ? total : placesMaximes;
        
        Inscripcio[] admesos = new Inscripcio[numAdmesos];
        for (int i = 0; i < numAdmesos; i++) {
            admesos[i] = tots[i];
        }
        
        return admesos;
    }

    /**
     * Guarda la llista serialitzada (l'objecte sencer) al fitxer
     */
    public void guardarFitxer(String rutaFitxer) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaFitxer));
            oos.writeObject(this); // Guardem "this" (la llista sencera amb l'array i el nElems)
            oos.close();
        } catch (IOException e) {
            System.out.println("Error guardant inscripcions: " + e.getMessage());
        }
    }

    /**
     * Carrega la llista des del fitxer i la retorna
     * És static perquè crea una nova instància de la llista
     */
    public static LlistaInscripcio carregarFitxer(String rutaFitxer) {
        LlistaInscripcio llistaLlegida = new LlistaInscripcio();
        
        File f = new File(rutaFitxer);
        if (f.exists()) {
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaFitxer));
                llistaLlegida = (LlistaInscripcio) ois.readObject();
                ois.close();
            } catch (Exception e) {
                System.out.println("Error carregant inscripcions: " + e.getMessage());
            }
        }
        return llistaLlegida;
    }

    @Override
    public String toString() {
        // Creo un String o un StringBuilder per anar muntant el text
        // Recorro la llista i vaig afegint el toString() de cada inscripció seguit d'un salt de línia
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nElems; i++) {
            sb.append(llista[i].toString()).append("\n");
        }
        return sb.toString(); // Retorno el text final complet
    }
}