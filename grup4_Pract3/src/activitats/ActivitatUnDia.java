package activitats;

import java.time.LocalDate;

public class ActivitatUnDia extends Activitat{
    private LocalDate dataActivitat;    
    private String hora;              
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
     * @param hora Hora de la actividad (String tipo XX:XX).
     * @param places Número de plazas disponibles.
     * @param preu Precio de la actividad.
     * @param ciutat Ciudad de la actividad.
     */
    public ActivitatUnDia(String nom, String[] collectius, LocalDate dataIniInscripcio, LocalDate dataFiInscripcio, LocalDate dataActivitat, String hora, int places, double preu, String ciutat){
        super(nom, collectius, dataIniInscripcio, dataFiInscripcio); //Llama al constructor de la clase padre
        this.dataActivitat = dataActivitat;
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
    public LocalDate getData(){
        return dataActivitat;
    }

    /**
     * Getter de hora de la actividad.
     * @return hora de tipo String (XX:XX).
     */
    public String getHora(){
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
    public boolean estaActiva(LocalDate avui) {
    return (avui != null &&
            (avui.isEqual(dataIniInscripcio) || avui.isAfter(dataIniInscripcio)) &&
            (avui.isEqual(dataFiInscripcio) || avui.isBefore(dataFiInscripcio)));
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
            "\tPeríode d'inscripció: del " + dataIniInscripcio + " al " + dataFiInscripcio + "\n" +
            "\tData: " + dataActivitat + " a les " + hora + "\n" +
            "\tCiutat: " + ciutat + "\n" +
            "\tPlaces disponibles: " + places + "\n" +
            "\tPreu: " + preu + "€";
    }
}

