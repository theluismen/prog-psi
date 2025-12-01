import java.time.LocalDate;

public abstract class Activitat {

    protected String nom;
    protected String[] collectius;
    protected LocalDate dataIniciInscripcio;
    protected LocalDate dataFiInscripcio;


    /**
     * Constructor de la classe Activitat
     *
     * @param nom nom identificador de l'activitat (no pot ser repetit)
     * @param collectius array de cadenes que indica els col·lectius als quals
     *                  s'ofereix l'activitat ("PDI", "PTGAS" i/o "Estudiant")
     * @param dataIniciInscripcio data a partir de la qual l'usuari es pot inscriure
     * @param dataFiInscripcio data límit fins a la qual es permet fer inscripcions
     */

    public Activitat (String nom, String[] collectius, LocalDate dataIniciInscripcio, LocalDate dataFiInscripcio) {
        this.nom = nom;
        this.collectius = collectius;
        this.dataIniciInscripcio = dataIniciInscripcio;
        this.dataFiInscripcio = dataFiInscripcio;
    }

    //Getters

    public String getNom() {    return nom;  }

    public String[] getcollectius() {   return collectius;  }

    public LocalDate getDataIniInscripcio()  {  return dataIniciInscripcio;  }

    public LocalDate getDataFiInscripcio()  {  return dataFiInscripcio;  }


    // Funcions base

    /**
     * Mètode que determina si avui es pot fer una inscripcio
     * 
     * @param avui la data d'aquell dia
     * @return true si es pot fer la inscripcio, sinó false
     */
    public boolean esEnPeriodeInscripcio(LocalDate avui) {
    return !avui.isBefore(dataIniciInscripcio) && !avui.isAfter(dataFiInscripcio);
    }

    
    
    /**
     * Determina si l'activitat esta activa en la data indicada
     * 
     * @param avui data a comprovar
     * @return true si l'activitat esta activa en la data indicada, sino false
     */
    public abstract boolean estaActiva (LocalDate avui);

    /**
     * Metode que retorna el tipus d'activitat
     * 
     * @return una cadena que indica el tipus d'activitat
     */
    public abstract String tipusActivitat();


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