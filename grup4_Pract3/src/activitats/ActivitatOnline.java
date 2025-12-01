package activitats;

import java.time.LocalDate;

public class ActivitatOnline extends Activitat {

    private LocalDate dataInici;
    private int periodeVisualitzacio; // Dies que està disponible
    private String enllac;

    /**
     * Constructor
     * @param nom Nom de l'activitat
     * @param collectius Array amb els col·lectius (PDI, PAS, etc.)
     * @param dataIniInscripcio Data inici inscripció
     * @param dataFiInscripcio Data fi inscripció
     * @param dataInici Data inici de l'activitat online
     * @param periodeVisualitzacio Durada en dies
     * @param enllac URL de l'activitat
     */
    public ActivitatOnline(String nom, String[] collectius, LocalDate dataIniInscripcio, 
                           LocalDate dataFiInscripcio, LocalDate dataInici, 
                           int periodeVisualitzacio, String enllac) {
        
        // Cridem al pare. NOTA: No passem preu ni places perquè Activitat no en té.
        super(nom, collectius, dataIniInscripcio, dataFiInscripcio);
        
        this.dataInici = dataInici;
        this.periodeVisualitzacio = periodeVisualitzacio;
        this.enllac = enllac;
    }

    // --- Getters i Setters propis ---
    public LocalDate getDataInici() { return dataInici; }
    public void setDataInici(LocalDate dataInici) { this.dataInici = dataInici; }

    public int getPeriodeVisualitzacio() { return periodeVisualitzacio; }
    public void setPeriodeVisualitzacio(int periode) { this.periodeVisualitzacio = periode; }

    public String getEnllac() { return enllac; }
    public void setEnllac(String enllac) { this.enllac = enllac; }


    // --- Implementació dels mètodes abstractes del pare ---

    @Override
    public String tipusActivitat() {
        return "Online";
    }

    /**
     * Comprova si l'activitat està activa en una data concreta.
     * Per online: data >= dataInici i data <= dataInici + periode
     */
    @Override
    public boolean estaActiva(LocalDate avui) {
        LocalDate dataFi = this.dataInici.plusDays(this.periodeVisualitzacio);
        
        // Retorna true si avui és igual o posterior a l'inici I avui és anterior o igual al final
        return (avui.isEqual(dataInici) || avui.isAfter(dataInici)) && 
               (avui.isEqual(dataFi) || avui.isBefore(dataFi));
    }

    // --- ToString ---

    @Override
    public String toString() {
        // Aprofitem el toString del pare que ja mostra Nom, Col·lectius i Inscripció
        return super.toString() +
               "Tipus: Online" + "\n" +
               "Data Inici Activitat: " + this.dataInici + "\n" +
               "Període: " + this.periodeVisualitzacio + " dies" + "\n" +
               "Enllaç: " + this.enllac + "\n";
    }
    
    /**
     * Mètode auxiliar per si necessiteu guardar en fitxer separats per ;
     * Ja que el toString() del pare que m'has passat fa servir format "llegible" i no CSV.
     */
    public String toCSV() {
        // Necessitareu un mètode així si voleu complir el requisit del PDF de guardar amb ';'
        // ja que el toString() actual posa "Nom: ...".
        return getNom() + ";" + 
               // Aquí caldria gestionar l'array de col·lectius per guardar-lo
               getDataIniInscripcio() + ";" + getDataFiInscripcio() + ";" +
               dataInici + ";" + periodeVisualitzacio + ";" + enllac;
    }
}