/**
 * Autor(@s): Ainara Sofía Cabrera Robles
 * Descripción: Esta clase crea instancias de actividad de un día y la gestiona.
 */

package activitats;

import enumeracions.Collectius;
import excepcions.*;
import extras.*;


public class ActivitatUnDia extends Activitat{

    /* Atributos de la Clase */
    private final Data dataActivitatIhora; 
    private int horaDurada;
    private int minutosDurada;              
    private int places;                
    private double preu;               
    private final String ciutat;            

    /* Constructor */
    /**
     * Constructor de ActivitatUnDia.
     * @param nom Nombre de la actividad.
     * @param collectius Colectivo al que pertenece la persona.
     * @param dataIniInscripcio Fecha de inicio de inscripción.
     * @param dataFiInscripcio Fecha de fin de inscripción.
     * @param dataActivitat Fecha de la actividad y hora de inicio.
     * @param horaDurada Duración de horas de la actividad (XX:00)
     * @param minutosDurada Duración de minutos de la actividad (00:XX).
     * @param places Número de plazas disponibles.
     * @param preu Precio de la actividad.
     * @param ciutat Ciudad de la actividad.
     */
    public ActivitatUnDia(String nom, 
                          Collectius[] collectiu, 
                          Data dataIniciInscripcio, 
                          Data dataFiInscripcio, 
                          Data dataActivitatIhora,
                          int horaDurada, 
                          int minutosDurada,
                          int places, 
                          double preu, 
                          String ciutat)throws CollectiuDesconegut, ValorInexistent {

        super(nom, collectiu, dataIniciInscripcio, dataFiInscripcio); 
        this.dataActivitatIhora = dataActivitatIhora;
        this.ciutat = ciutat;

        //La hora ha de ser 0 <= hora <= 24.
        if (horaDurada >= 0 && horaDurada <= 24){
            this.horaDurada = horaDurada;
        }else{
            throw new ValorInexistent("Valor de hora inexistent");
        }
        //Los minutos han de ser 0 < minutos <= 59.
        if (minutosDurada >= 0 && minutosDurada <= 59){
            this.minutosDurada = minutosDurada;
        }else{
            throw new ValorInexistent("Valor de minuts inexistent");
        }
        //El número de plazas > 0.
        if (places > 0){
            this.places = places;
        }else{
            throw new ValorInexistent("Valor negatiu");
        }
        //El precio > 0.
        if (preu > 0){
            this.preu = preu;
        }else{
            throw new ValorInexistent("Valor inexistent");
        }
    }


    /* Getters y Setters */
    /**
     * Getter de fecha de la actividad.
     * @return fecha de tipo Data.
     */
    public Data getDataInici(){
        return dataActivitatIhora;
    }

    /**
     * Getter de minuto de la actividad.
     * @return minuto de la actividad.
     */
    public int getMinuto(){
        return dataActivitatIhora.getMinuts();
    }

    /**
     * Getter de hora de la actividad.
     * @return hora de la actividad.
     */
    public int getHora(){
        return dataActivitatIhora.getHora();
    }

    /**
     * Getter de minuto de la actividad.
     * @return minuto de la actividad.
     */
    public int getMinutoDurada(){
        return minutosDurada;
    }

    /**
     * Getter de hora de la actividad.
     * @return hora de la actividad.
     */
    public int getHoraDurada(){
        return horaDurada;
    }

    /**
     * Getter de número de plazas para la actividad.
     * @return Número de plazastipo int.
     */
    @Override
    public int getPlacesMaximes(){
        return places;
    }

    /**
     * Getter de precio de actividad.
     * @return precio de la actividad tipo double.
     */
    public double getPreu(){
        return preu;
    }

    /**
     * Getter del nombre de la ciudad donde tiene lugar la actividad.
     * @return Nombre de  la ciudad tipo string.
     */
    public String getCiutat(){
        return ciutat;
    }

    /**
     * getter para saber la fecha del final de la actividad
     * @return fecha final
     */
    @Override
    public Data getDataFinal(){    
        return dataActivitatIhora;      
    }


    /* Métodos */
    /**
     * Método que determina si la actividad está activa en la fecha dada.
     * 
     * @param avui Fecha a comprovar.
     * @return true si la fecha coincide con el día de la actividad, false en caso contrario.
     */
    @Override
    public boolean estaActiva(Data hoy) {
        return this.dataActivitatIhora.esIgual(hoy);
    }

    /**
     * Método que determina si la actividad tiene clase en la fecha dada.
     * es necesario para un metodo de la lista pero es igual que "estaActiva"
     * 
     * @param avui Fecha a comprovar.
     * @return true si la fecha coincide con el día de la actividad, false en caso contrario.
     */
    @Override
    public boolean avuiHiHaClase(Data hoy) {
        return this.dataActivitatIhora.esIgual(hoy);
    }

    /**
     * Metodo que devuelve un duplicado de la instancia
     * @return duplicado
     * @throws ValorInexistent
     * @throws CollectiuDesconegut
     */
    @Override
    public ActivitatUnDia copia(){
        ActivitatUnDia copia;
        try{
            copia = new ActivitatUnDia(super.nom, 
                                       super.collectiu, 
                                       super.dataIniciInscripcio, 
                                       super.dataFiInscripcio, 
                                       this.dataActivitatIhora,
                                       this.horaDurada, 
                                       this.minutosDurada, 
                                       this.places, 
                                       this.preu, 
                                       this.ciutat);
        }catch(ValorInexistent e){  //Es una copia asi que nunca dará ninguna de las excepciones, se tratan por obligatoriedad
            System.out.println("Error de valor inexistent de copia en activitat d'un día.");
            copia = null;

        }catch(CollectiuDesconegut e){
            System.out.println("Error de collectiu desconegut de copia en activitat d'un dia");
            copia = null;
        }
        return copia;
    }

    /**
     * Método que transforma la información de la clase en una String.
     * @return String con la informaciónd e la clase.
     */
    @Override
    public String toString(){
        return"--- ACTIVITAT D'UN DIA ---\n" + 
            super.toString()+
            "\tData d'inici de període de l'inscripció: " + dataIniciInscripcio.getDia() + "/" + dataIniciInscripcio.getMes() + "/" + dataIniciInscripcio.getAny() + "\n" +
            "\tData de fi de període de l'inscripció: " + dataFiInscripcio.getDia() + "/" + dataFiInscripcio.getMes() + "/" + dataFiInscripcio.getAny() + "\n" +
            "\tData: " + dataActivitatIhora.getDia() + "/" + dataActivitatIhora.getMes() + "/" + dataActivitatIhora.getAny() + 
            " a les " + String.format("%02d", dataActivitatIhora.getHora()) + ":" + String.format("%02d", dataActivitatIhora.getMinuts()) + "\n" + 
            "\tDurada: " + String.format("%02d", horaDurada) +  ":" + String.format("%02d", minutosDurada) + "\n" +
            "\tCiutat: " + ciutat + "\n" +
            "\tPlaces disponibles: " + places + "\n" +
            "\tPreu: " + preu + " euros";
    }

    /**
     * Método que convierte el objeto de la clase a una líneade CSV.
     * @return Stirng con formato .CSV.
     */
    @Override
    public String toCSV(){
        String aux = "Activitat d'un dia;" + super.nom + ";";

        if (super.collectiu != null) {
            for (int i = 0; i < super.collectiu.length; i++){
                aux += super.collectiu[i].name();
            } 
        } else {
            aux += ";"; // si no hi ha collectius
        }

        aux += super.dataIniciInscripcio.getDia() + ";" +
               super.dataIniciInscripcio.getMes() + ";" +
               super.dataIniciInscripcio.getAny() + ";" +
               super.dataFiInscripcio.getDia() + ";" +
               super.dataFiInscripcio.getMes() + ";" +
               super.dataFiInscripcio.getAny() + ";" +
               this.dataActivitatIhora.getDia() + ";" +
               this.dataActivitatIhora.getMes() + ";" +
               this.dataActivitatIhora.getAny() + ";" +
               this.dataActivitatIhora.getHora() + ";" +
               this.dataActivitatIhora.getMinuts() + ";" +
               this.horaDurada + ";" +
               this.minutosDurada + ";" +
               this.places + ";" +
               this.preu + ";" +
               this.ciutat;
               
        return aux;
    }
}