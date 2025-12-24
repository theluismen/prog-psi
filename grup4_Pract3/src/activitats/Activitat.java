/**
 * Autor: Aesha Naz Mahmood Bibi
 * Descripció: Classe abstracta que representa una acticitat del sistema
 * Defineix els atributs i mètodes comuns a tots els tipus d'activitats.
 */

package activitats;

import excepcions.CollectiuDesconegut;
import extras.Data;

public abstract class Activitat {

    /** Atributs de la classe */
    protected String nom;
    protected String[] collectius;
    protected Data dataIniciInscripcio;
    protected Data dataFiInscripcio;

    /**
     * Constructor de la classe Activitat
     *
     * @param nom nom identificador de l'activitat (no pot ser repetit)
     * @param collectius col·lectius als quals s'ofereix l'activitat
     * @param dataIniciInscripcio data a partir de la qual l'usuari es pot inscriure
     * @param dataFiInscripcio data límit fins a la qual es permet fer inscripcions
     */
    public Activitat (String nom, Collectius collectius, Data dataIniciInscripcio, Data dataFiInscripcio) {
        this.nom = nom;
        this.collectius = collectius;
        this.dataIniciInscripcio = dataIniciInscripcio;
        this.dataFiInscripcio = dataFiInscripcio;
    }

    /** Getters */

    /**
     * Mètode que retorna el nom de l'activitat
     * 
     * @return nom de l'activitat
     */
    public String getNom() {    
        return nom;  
    }

    /**
     * Mètode que retorna la llista de col·lectius als que pot pertanyer l'usuari
     * 
     * @return tipus de col·lectius
     */
    public Collectius getCollectius() {   
        return collectius;  
        }

    /**
     * Mètode que retorna la data d'inici del període d'inscripció
     * 
     * @return un objecte {@code Data} que representa la data inicial d'inscripció
     */
    public Data getDataIniInscripcio()  {  
        return dataIniciInscripcio;  
    }

    /**
     * Mètode que retorna la data de finalització del període d'inscripció
     * 
     * @return un objecte {@code Data} que representa la data final d'inscripció
     */
    public Data getDataFiInscripcio()  {  
        return dataFiInscripcio;  
    }

    /**
    * Mètode que retona la data de fi  de l'activitat
    * 
    * @return un objecte {@code Data} que representa la data final d'inscripció
    */
    public abstract Data getDataFinal();

    /**
     * Mètode que retorna el nombre de places que hi ha en una activitat
     * 
     * @return el nombre de places que hi ha
     */
    public abstract int getPlacesMaximes();

    /**
     * Mètode que determina si avui es pot fer una inscripcio
     * 
     * @param avui la data d'aquell dia
     * @return true si es pot fer la inscripcio, sinó false
     */
    public boolean esEnPeriodeInscripcio(Data avui) {
    return dataIniciInscripcio.esDataInferiorOigual(avui) &&
            avui.esDataInferiorOigual(dataFiInscripcio);
    }
    
    /**
     * Determina si l'activitat esta activa en la data indicada
     * 
     * @param avui data a comprovar
     * @return true si l'activitat esta activa en la data indicada, sino false
     */
    public abstract boolean estaActiva (Data avui);

    /**
     * Métode que determina si l'activitat s'ha acabat o no
     * 
     * @param avui data a comprovar
     * @return true si s'ha acabat l'activitat, sinó false
     */
    public boolean estaAcabada(Data avui) {
        return getDataFinal().esDataInferior(avui);
    }

    /**
     * Determina si una actividad tiene clase en la fecha indicada
     * para las actividades Online y las de un dia, el metodo sera igual que 
     * "estaActiva" pero para las periodicas diferirá
     * 
     * @param avui
     * @return true si en la fecha hay clase
     */
    public abstract boolean avuiHiHaClase (Data avui);

    /**
     * Metode que retorna el tipus d'activitat
     * 
     * @return una cadena que indica el tipus d'activitat
     */
    public abstract String tipusActivitat();

    /**
     * Metode que retorna una còpia de l'activitat
     * 
     * @return una nova instància d'activitat
     */
    public abstract Activitat copia();

    /**
     * Metode que retorna un string en format CSV amb tota la informació de la activitat
     * 
     * @return informacio de l'activitat en format CSV
     */
    public abstract String toCSV();

    /**
     * Retorna una representació textual de l'activitat, incloent
     * totes les seves dades (nom, col·lectius i període d'inscripció)
     * 
     * @return una cadena de text amb el nom, els col·lectius i el període d'inscripció
     */
    @Override
    public String toString() {
        String info = "Nom: " + nom + "\nCol·lectius: ";

        for (int i = 0; i < collectius.length; i++) {
            info += collectius[i];
            if (i < collectius.length - 1) info += ", ";
        }

        info += "\nPeríode inscripció: " + dataIniciInscripcio +
                " fins " + dataFiInscripcio + "\n";

        return info;
    }

    /**
     * Mètode que comprova si el col·lectiu es correcte (dels tipus que hi han)
     * 
     * @param cols Collectius que hi han (ESTUDIANT, PDI, PTGAS)
     * @return true si es correcte, sinó false
     */
    private boolean colectiuCorrecte(Collectius cols){
        boolean res = true;
        int i = 0;

        while (res && (i < cols.length)){
            if (!cols[i].equalsIgnoreCase("PDI") && !cols[i].equalsIgnoreCase("PTGAS") && !cols[i].equalsIgnoreCase("Estudiant")){
                res = false;
            }
            i++;
        }

        return res;
    }
}