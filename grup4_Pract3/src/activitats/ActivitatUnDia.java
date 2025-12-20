//Ainara Sofia

package activitats;

import extras.*;


public class ActivitatUnDia extends Activitat{
    private Data dataActivitat;               
    private int places;                
    private double preu;               
    private String ciutat;            


    /**
     * Constructor de ActivitatUnDia.
     * @param nom Nombre de la actividad.
     * @param collectius Colectivo al que pertenece la persona.
     * @param dataIniInscripcio Fecha de inicio de inscripción.
     * @param dataFiInscripcio Fecha de fin de inscripción.
     * @param dataActivitat Fecha de la actividad y hora de inicio.
     * @param places Número de plazas disponibles.
     * @param preu Precio de la actividad.
     * @param ciutat Ciudad de la actividad.
     */
    public ActivitatUnDia(String nom, 
                          String[] collectius, 
                          Data dataIniciInscripcio, 
                          Data dataFiInscripcio, 
                          Data dataActivitat, 
                          int places, 
                          double preu, 
                          String ciutat){

        super(nom, collectius, dataIniciInscripcio, dataFiInscripcio); //Llama al constructor de la clase padre
        this.dataActivitat = dataActivitat;
        this.places = places;
        this.preu = preu;
        this.ciutat = ciutat;
    }


    //Getters...
    
    /**
     * Getter de fecha de la actividad.
     * @return fecha de tipo Data.
     */
    public Data getData(){
        return dataActivitat;
    }

    /**
     * Getter de minuto de la actividad.
     * @return minuto de la actividad.
     */
    public int getMinuto(){
        return dataActivitat.getMinutos();
    }

    /**
     * Getter de hora de la actividad.
     * @return hora de la actividad.
     */
    public int getHora(){
        return dataActivitat.getHora();
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


    //Métodos de la clase padre.

    /**
     * Método que determina si la actividad está activa en la fecha dada.
     * 
     * @param avui Fecha a comprovar.
     * @return true si la fecha coincide con el día de la actividad, false en caso contrario.
     */
    @Override
    public boolean estaActiva(Data hoy) {
        boolean resultado = false;
    
        if(hoy != null ){
            boolean mismoDia = hoy.getDia() == dataActivitat.getDia() &&
                           hoy.getMes() == dataActivitat.getMes() &&
                           hoy.getAny() == dataActivitat.getAny();
            if(mismoDia){
                if(hoy.getHora() > dataActivitat.getHora()){
                    resultado = hoy.getMinutos() >= dataActivitat.getMinutos();
                }
            }
        }
    return resultado;
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

    /**
     * Metodo que devuelve un duplicado de la instancia
     * @return duplicado
     */
    public ActivitatUnDia copia(){
        return new ActivitatUnDia(super.nom, super.collectius, super.dataIniciInscripcio, super.dataFiInscripcio, 
                                  this.dataActivitat, this.places, this.preu, this.ciutat);
    }

    //Método para pasar toda la clase por string.
    public String toString(){
        return"--- ACTIVITAT D'UN DIA ---\n" + 
            super.toString()+
            "\tData: " + dataActivitat.getDia() + "/" + dataActivitat.getMes() + "/" + dataActivitat.getAny() + 
            " a les " + String.format("%02d", dataActivitat.getHora()) + ":" + String.format("%02d", dataActivitat.getMinutos()) + "\n" +
            "\tCiutat: " + ciutat + "\n" +
            "\tPlaces disponibles: " + places + "\n" +
            "\tPreu: " + preu + " euros";
    }

    public String toCSV(){
        String aux = this.tipusActivitat() + ";" + super.nom + ";";

        for (int i = 0; i < (super.collectius.length - 1); i++){    //-1 para evitar que al poner el ultimo colectivo quede una coma al final
            aux += super.collectius[i]+",";
        }
        aux += super.collectius[super.collectius.length - 1] + ";" +
               super.dataIniciInscripcio.getDia() + ";" +
               super.dataIniciInscripcio.getMes() + ";" +
               super.dataIniciInscripcio.getAny() + ";" +
               super.dataFiInscripcio.getDia() + ";" +
               super.dataFiInscripcio.getMes() + ";" +
               super.dataFiInscripcio.getAny() + ";" +
               this.dataActivitat.getDia() + ";" +
               this.dataActivitat.getMes() + ";" +
               this.dataActivitat.getAny() + ";" +
               this.dataActivitat.getHora() + ";" +
               this.dataActivitat.getMinutos() + ";" +
               this.places + ";" +
               this.preu + ";" +
               this.ciutat;
               
        return aux;
    }
}

