import java.time.LocalDate;

public abstract class Activitat {

    protected String nom;
    protected String[] collectius;
    protected LocalDate dataIniInscripcio;
    protected LocalDate dataFiInscripcio;

    public Activitat (String nom, String[] collectius, LocalDate dataIniInscripcio, LocalDate dataFiInscripcio) {
        this.nom = nom;
        this.collectius = collectius;
        this.dataIniInscripcio = dataIniInscripcio;
        this.dataFiInscripcio = dataFiInscripcio;
    }

    //Getters

    public String getNom() {    return nom;  }

    public String[] getcollectius() {   return collectius;  }

    public LocalDate getDataIniInscripcio()  {  return dataIniInscripcio;  }

    public LocalDate getDataFiInscripcio()  {  return dataFiInscripcio;  }


    // Funcions base

    /**
     * Mètode que determina si avui es pot fer una inscripcio
     * 
     * @param avui la data d'aquell dia
     * @return true si es pot fer la inscripcio, sinó false
     */
    public boolean esEnPeriodeInscripcio(LocalDate avui) {
        return (avui.isEqual(dataIniInscripcio) || (avui.isAfter((dataIniInscripcio))) 
                && avui.isBefore(dataFiInscripcio) || (avui.isBefore(dataFiInscripcio)));

    }
    
    public abstract boolean estaActiva (LocalDate avui);

    public abstract String tipusActivitat();

    @Override
    public String toString() {
        String info = "Nom: " + nom + "\nCol·lectius: ";

        for (int i = 0; i < collectius.length; i++) {
            info += collectius[i];
            if (i < collectius.length - 1) info += ", ";
        }

        info += "\nPeríode inscripció: " + dataIniInscripcio +
                " fins " + dataFiInscripcio + "\n";

        return info;
    }
}
