package activitats;

import extras.Data;


public class ActivitatUnDia extends Activitat{
    private Data dataActivitat;    
    private int minuto;
    private int hora;              
    private int places;                
    private double preu;               
    private String ciutat;            


    /**
     * Constructor de ActivitatUnDia.
     * @param nom Nombre de la actividad.
     * @param collectius Colectivo al que pertenece la persona.
     * @param dataIniInscripcio Fecha de inicio de inscripción.
     * @param dataFiInscripcio Fecha de fin de inscripción.
     * @param dataActivitat Fecha de la actividad.
     * @param minuto Minuto para la hora de la actividad.
     * @param hora Hora de la actividad.
     * @param places Número de plazas disponibles.
     * @param preu Precio de la actividad.
     * @param ciutat Ciudad de la actividad.
     */
    public ActivitatUnDia(String nom, String[] collectius, Data dataIniciInscripcio, 
                            Data dataFiInscripcio, Data dataActivitat, int minuto, int hora, 
                            int places, double preu, String ciutat){
        super(nom, collectius, dataIniciInscripcio, dataFiInscripcio); //Llama al constructor de la clase padre
        this.dataActivitat = dataActivitat;
        this.minuto = minuto;
        this.hora = hora;
        this.places = places;
        this.preu = preu;
        this.ciutat = ciutat;
    }


    //Getters...
    
    /**
     * Getter de fecha de la actividad.
     * @return fecha de tipo LocalDate.
     */
    public Data getData(){
        return dataActivitat;
    }

    /**
     * Getter de minuto de la actividad.
     * @return minuto de la actividad.
     */
    public int getMinuto(){
        return minuto;
    }

    /**
     * Getter de hora de la actividad.
     * @return hora de la actividad.
     */
    public int getHora(){
        return hora;
    }

    /**
     * Getter de número de plazas para la actividad.
     * @return Número de plazastipo int.
     */
    public int getPlaces(){
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
     * Método que determina si la actividad está activa en la fecha dada.
     * Está activa si se encuentra entre la fecha inicio y final, ambas incluidas.
     * 
     * @param avui Fecha a comprovar.
     * @return true si la fecha está dentro del período de inscripción, false en caso contrario.
     */
    @Override
    public boolean estaActiva(Data avui) {
    return (avui != null &&
            (avui.esIgual(dataIniciInscripcio)) && (avui.esDataInferiorOigual(dataFiInscripcio)));
    }

    /**
     * Método que devuelve el tipo de actividad como String.
     *
     * @return Tipo de actividad como String.
     */
    @Override
    public String tipusActivitat() {
        return "Activitat d'un dia";
    }

    //Método para pasar toda la clase por string.
    public String toString(){
        return"--- ACTIVITAT D'UN DIA ---\n" + 
            "\tNom: " + nom + "\n" +
            "\tCollectius: "  + String.join(", ", collectius) + "\n" +
            "\tPeríode d'inscripció: del " + dataIniciInscripcio + " al " + dataFiInscripcio + "\n" +
            "\tData: " + dataActivitat + " a les " + hora + ":" + minuto + "\n" +
            "\tCiutat: " + ciutat + "\n" +
            "\tPlaces disponibles: " + places + "\n" +
            "\tPreu: " + preu + " euros";
    }
}

