//Aesha Naz

package activitats;
import extras.Data;

public abstract class Activitat {

    protected String nom;
    protected String[] collectius;
    protected Data dataIniciInscripcio;
    protected Data dataFiInscripcio;

    /**
     * Constructor de la classe Activitat
     *
     * @param nom nom identificador de l'activitat (no pot ser repetit)
     * @param collectius array de cadenes que indica els col·lectius als quals
     *                  s'ofereix l'activitat ("PDI", "PTGAS" i/o "Estudiant")
     * @param dataIniciInscripcio data a partir de la qual l'usuari es pot inscriure
     * @param dataFiInscripcio data límit fins a la qual es permet fer inscripcions
     */

    public Activitat (String nom, String[] collectius, Data dataIniciInscripcio, Data dataFiInscripcio) {
        this.nom = nom;
        this.collectius = collectius;
        this.dataIniciInscripcio = dataIniciInscripcio;
        this.dataFiInscripcio = dataFiInscripcio;
    }

    /**
     * Metode que retorna el nom de l'usuari
     * 
     * @return un String que representa el nom de l'usuari
     */
    public String getNom() {    
        return nom;  
    }

    /**
     * Metode que retorna la llista de col·lectius als que pot pertanyer l'usuari
     * 
     * @return un array de cadenes amb els col·lectius
     */
    public String[] getCollectius() {   
        return collectius;  
        }

    /**
     * Metode que retorna la data d'inici del període d'inscripció
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

}