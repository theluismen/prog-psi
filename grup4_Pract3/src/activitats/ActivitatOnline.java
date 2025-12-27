/**
 * Autor: Ikram Hallouz
 * Descripción: Clase que representa una actividad online.
 */

package activitats;

import enumeracions.Collectius;
import extras.Data;

public class ActivitatOnline extends Activitat {

    private Data dataInici;
    private int periodeVisualitzacio; // Dies que està disponible
    private String enllac;

    /**
     * Constructor de la classe ActivitatOnline.
     * * @param nom Nom de l'activitat
     * @param collectiu Col·lectiu al qual va dirigida (Singular)
     * @param dataIniInscripcio Data d'inici de les inscripcions
     * @param dataFiInscripcio Data de fi de les inscripcions
     * @param dataInici Data d'inici de l'activitat
     * @param periodeVisualitzacio Durada en dies de l'activitat
     * @param enllac URL de l'activitat
     */
    public ActivitatOnline(String nom, Collectius[] collectiu, Data dataIniInscripcio, 
                           Data dataFiInscripcio, Data dataInici, 
                           int periodeVisualitzacio, String enllac) { 
        
        // Cridem al constructor del pare passant el col·lectiu en singular
        super(nom, collectiu, dataIniInscripcio, dataFiInscripcio);
        
        this.dataInici = dataInici;
        this.periodeVisualitzacio = periodeVisualitzacio;
        this.enllac = enllac;
    }

    // --- Getters i Setters ---

    /**
     * Retorna la data d'inici de l'activitat.
     * @return data d'inici
     */
    public Data getDataInici() { 
        return dataInici; 
    }

    /**
     * Assigna una nova data d'inici.
     * @param dataInici nova data
     */
    public void setDataInici(Data dataInici) { 
        this.dataInici = dataInici; 
    }

    /**
     * Calcula i retorna la data final de l'activitat sumant el període a la data d'inici.
     * @return data final
     */
    @Override
    public Data getDataFinal(){    
        return dataInici.dataPlusDies(periodeVisualitzacio);      
    }

    /**
     * Retorna el període de visualització en dies.
     * @return dies de visualització
     */
    public int getPeriodeVisualitzacio() { 
        return periodeVisualitzacio; 
    }

    /**
     * Assigna un nou període de visualització.
     * @param periode dies nous
     */
    public void setPeriodeVisualitzacio(int periode) { 
        this.periodeVisualitzacio = periode; 
    }

    /**
     * Retorna l'enllaç de l'activitat.
     * @return url de l'activitat
     */
    public String getEnllac() { 
        return enllac; 
    }

    /**
     * Assigna un nou enllaç.
     * @param enllac nova url
     */
    public void setEnllac(String enllac) { 
        this.enllac = enllac; 
    }


    // --- Implementació dels mètodes abstractes ---

    /**
     * Retorna el tipus d'activitat en format text.
     * @return "Online"
     */
    @Override
    public String tipusActivitat() {
        return "Online";
    }

    /**
     * Retorna el nombre màxim de places.
     * @return Integer.MAX_VALUE (il·limitades per a online)
     */
    @Override
    public int getPlacesMaximes() {
        return Integer.MAX_VALUE;
    }

    /**
     * Comprova si l'activitat està activa en una data concreta.
     * Per a online: data >= dataInici i data <= dataInici + periode
     * @param avui data a comprovar
     * @return true si està activa
     */
    @Override
    public boolean estaActiva(Data avui) {
        Data dataFi = this.dataInici.dataPlusDies(this.periodeVisualitzacio);
        return this.dataInici.esDataInferiorOigual(avui) && 
               avui.esDataInferiorOigual(dataFi);
    }

    /**
     * Comprova si hi ha classe avui. En online és equivalent a estar activa.
     * @param avui data a comprovar
     * @return true si hi ha classe (està disponible)
     */
    @Override
    public boolean avuiHiHaClase(Data avui) {
        return estaActiva(avui);
    }

    /**
     * Retorna una representació en text de l'activitat.
     * @return String amb les dades
     */
    @Override
    public String toString() {
        return super.toString() +
               "Tipus: Online" + "\n" +
               "Data Inici Activitat: " + this.dataInici + "\n" +
               "Període: " + this.periodeVisualitzacio + " dies" + "\n" +
               "Enllaç: " + this.enllac + "\n";
    }
    
    /**
     * Retorna la informació de l'activitat en format CSV.
     * Format: Online;Nom;Col·lectiu;IniciIns;FiIns;DataInici;Periode;Enllac
     * @return cadena CSV
     */
    @Override
    public String toCSV() {
        // Construir la cadena de col·lectius separats per comes
        String cols = "";
        for (int i = 0; i < super.collectiu.length; i++) {
            cols += super.collectiu[i].name();
            if (i < super.collectiu.length - 1) {
                cols += ",";
            }
        }
        // Accedim directament a super.collectiu (singular) perquè ara és un sol Enum
        return "Online;" + 
               super.nom + ";" + 
               cols + ";" + 
               super.dataIniciInscripcio.getDia() + ";" + super.dataIniciInscripcio.getMes() + ";" + super.dataIniciInscripcio.getAny() + ";" +
               super.dataFiInscripcio.getDia() + ";" + super.dataFiInscripcio.getMes() + ";" + super.dataFiInscripcio.getAny() + ";" +
               this.dataInici.getDia() + ";" + this.dataInici.getMes() + ";" + this.dataInici.getAny() + ";" +
               this.periodeVisualitzacio + ";" + 
               this.enllac;
    }

    /**
     * Crea una còpia exacta d'aquesta activitat.
     * @return nova instància d'ActivitatOnline
     */
    @Override
    public ActivitatOnline copia() {
        return new ActivitatOnline(
            super.nom,
            super.collectiu, // Passem l'Enum directament
            super.dataIniciInscripcio.copia(),
            super.dataFiInscripcio.copia(),
            this.dataInici.copia(),
            this.periodeVisualitzacio,
            this.enllac
        );
    }
}