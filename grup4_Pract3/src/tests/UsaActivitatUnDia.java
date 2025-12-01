package tests;

import extras.Data;
import activitats.ActivitatUnDia;   //Importo la clase que quiero testear.

public class UsaActivitatUnDia {

    public static void main(String[] args) {

        String nom = "Taller de respiració";
        String[] collectius = {"PDI", "PTGAS", "Estudiant"};
        Data dataIniciInscripcio = new Data(25,11,2025,18,50);
        Data dataFiInscripcio = new Data(5,12,2025,23,00);
        Data dataActivitat = new Data(10,12,2025,18,30);
        int places = 25;
        double preu = 5.0;
        String ciutat = "Tarragona";

        //Creamos una instancia de ActivitatUnDia.
        ActivitatUnDia activitatUnDia = new ActivitatUnDia(
            nom,
            collectius,
            dataIniciInscripcio,
            dataFiInscripcio,
            dataActivitat,
            places,
            preu,
            ciutat
        );

        System.out.println("--- TEST ActivitatUnDia ---");

        //Probamos los getters heredados de la clase Activitat.
        System.out.println("Nom: " + activitatUnDia.getNom());  //Muestra el nombre de la actividad.
        System.out.println("Collectius: " + String.join(", ", activitatUnDia.getCollectius())); //Une el array en una sola línea.

        //Probamos los getters definidos en ActivitatUnDia.
        System.out.println("Data activitat: " + dataActivitat.getDia() + "/" + dataActivitat.getMes() + "/" + dataActivitat.getAny());      //Fecha de la actividad.
        System.out.println("Hora activitat: " + activitatUnDia.getHora() + ":" + activitatUnDia.getMinuto());        //Hora como union de hora y minnuto.
        System.out.println("Places: " + activitatUnDia.getPlaces());            //Número de plazas.
        System.out.println("Preu: " + activitatUnDia.getPreu() + " euros");                //Precio.
        System.out.println("Ciutat: " + activitatUnDia.getCiutat());            //Ciudad.

        //Probamos el método toString() que devuelve toda la información formateada.
        System.out.println("\n--- toString() ---");
        System.out.println(activitatUnDia);
        
        System.out.println("--- FI DEL TEST ---");
    }
}
