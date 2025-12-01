package activitats;

import java.time.LocalDate;

public class ActivitatUnDia extends Activitat{
    private LocalDate dataActivitat;    //Fecha de la actividad
    private String hora;                //Hora de la actividad
    private int places;                 //Núero de plazas de la actividad
    private double preu;                //Precio de la actividad
    private String ciutat;              //Ciudad de la actividad

    //Constructor
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

