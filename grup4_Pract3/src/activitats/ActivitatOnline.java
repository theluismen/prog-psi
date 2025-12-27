/**
 * Autor: Ikram Hallouz
 * Descripción: Clase que representa una actividad online.
 */

package activitats;

import enumeracions.Collectius;
import excepcions.CollectiuDesconegut;
import extras.Data;

public class ActivitatOnline extends Activitat {

    private Data dataInici;
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
    public ActivitatOnline(String nom, Collectius[] collectiu, Data dataIniInscripcio, 
                           Data dataFiInscripcio, Data dataInici, 
                           int periodeVisualitzacio, String enllac) throws CollectiuDesconegut { // <--- 2. AFEGIR THROWS
        
        // Cridem al pare. Ara ja podem propagar l'error si el col·lectiu no és vàlid
        super(nom, collectiu, dataIniInscripcio, dataFiInscripcio);
        
        this.dataInici = dataInici;
        this.periodeVisualitzacio = periodeVisualitzacio;
        this.enllac = enllac;
    }

    // --- Getters i Setters propis ---
    public Data getDataInici() { return dataInici; }
    public void setDataInici(Data dataInici) { this.dataInici = dataInici; }

    /**
     * getter para saber la fecha del final de la actividad
     * @return fecha final
     */
    @Override
    public Data getDataFinal(){    
        return dataInici.dataPlusDies(periodeVisualitzacio);      
    }

    public int getPeriodeVisualitzacio() { return periodeVisualitzacio; }
    public void setPeriodeVisualitzacio(int periode) { this.periodeVisualitzacio = periode; }

    public String getEnllac() { return enllac; }
    public void setEnllac(String enllac) { this.enllac = enllac; }


    // --- Implementació dels mètodes abstractes del pare ---

    /**
     * Metode que retorna quantes places té una activitat -
     * en aquest cas en té il·limitades
     */
    @Override
    public int getPlacesMaximes() {
        return Integer.MAX_VALUE;
    }


    /**
     * Comprova si l'activitat està activa en una data concreta.
     * Per online: data >= dataInici i data <= dataInici + periode
     */
    @Override
    public boolean estaActiva(Data avui) {
        // Calculem la data final sumant els dies a la data d'inici
        Data dataFi = this.dataInici.dataPlusDies(this.periodeVisualitzacio);
        
        // Està activa si: dataInici <= avui  I  avui <= dataFi
        return this.dataInici.esDataInferiorOigual(avui) && 
               avui.esDataInferiorOigual(dataFi);
    }


    /**
     * Comprova si l'activitat te clase en una data concreta.
     * es necessari per un metode de la llista pero es igual que "estaActiva"
     * Per online: data >= dataInici i data <= dataInici + periode
     */
    @Override
    public boolean avuiHiHaClase(Data avui) {
        // Calculem la data final sumant els dies a la data d'inici
        Data dataFi = this.dataInici.dataPlusDies(this.periodeVisualitzacio);
        
        // Està activa si: dataInici <= avui  I  avui <= dataFi
        return this.dataInici.esDataInferiorOigual(avui) && 
               avui.esDataInferiorOigual(dataFi);
    }

    // --- ToString ---

    @Override
    public String toString() {
        // Aprofitem el toString del pare.
        return super.toString() +
               "Tipus: Online" + "\n" +
               "Data Inici Activitat: " + this.dataInici + "\n" +
               "Període: " + this.periodeVisualitzacio + " dies" + "\n" +
               "Enllaç: " + this.enllac + "\n";
    }
    
    /**
     * Retorna informació de l'objecte en format CSV separat per ;
     * Format pare + propis: 
     * Tipus;Nom;Colectius;IniciIns;FiIns;DataIniciAct;Periode;Enllac
     */
    @Override
    public String toCSV() {
        // Construïm la part dels col·lectius separats per comes dins del CSV
        String col = "";
        if (super.collectiu != null) {
            for (int i = 0; i < super.collectiu.length; i++){
                col += super.collectiu[i].name();
            }
        }
           

        return "Online;" + 
               super.nom + ";" + 
               col + ";" +
               super.dataIniciInscripcio.getDia() + ";" + super.dataIniciInscripcio.getMes() + ";" + super.dataIniciInscripcio.getAny() + ";" +
               super.dataFiInscripcio.getDia() + ";" + super.dataFiInscripcio.getMes() + ";" + super.dataFiInscripcio.getAny() + ";" +
               this.dataInici.getDia() + ";" + this.dataInici.getMes() + ";" + this.dataInici.getAny() + ";" +
               this.periodeVisualitzacio + ";" + 
               this.enllac;
    }

    @Override
    public ActivitatOnline copia() {
        try {
            return new ActivitatOnline(
                super.nom,
                super.collectiu,
                super.dataIniciInscripcio.copia(),
                super.dataFiInscripcio.copia(),
                this.dataInici.copia(),
                this.periodeVisualitzacio,
                this.enllac
            );
        } catch (CollectiuDesconegut e) {
            // Això no hauria de passar mai en una còpia d'un objecte ja vàlid, 
            return null; 
        }
    }

}