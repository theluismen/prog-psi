package tests;

import java.time.LocalDate;
import activitats.ActivitatUnDia;   //Importo la clase que quiero testear.

public class UsaActivitatUnDia {

    public static void main(String[] args) {

        String nom = "Taller de respiració";
        String[] collectius = {"PDI", "PTGAS", "Estudiant"};
        LocalDate dataIniciInscripcio = LocalDate.of(2025, 11, 25);
        LocalDate dataFiInscripcio = LocalDate.of(2025, 12, 5);
        LocalDate dataActivitat = LocalDate.of(2025, 12, 10);
        int hora = 18; 
        int minuto = 50;
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
            minuto,
            hora,
            places,
            preu,
            ciutat
        );

        System.out.println("--- TEST ActivitatUnDia ---");

        //Probamos los getters heredados de la clase Activitat.
        System.out.println("Nom: " + activitatUnDia.getNom());  //Muestra el nombre de la actividad.
        System.out.println("Collectius: " + String.join(", ", activitatUnDia.getCollectius())); //Une el array en una sola línea.

        //Probamos los getters definidos en ActivitatUnDia.
        System.out.println("Data activitat: " + activitatUnDia.getData());      //Fecha de la actividad.
        System.out.println("Hora activitat: " + activitatUnDia.getHora() + ":" + activitatUnDia.getMinuto());        //Hora como union de hora y minnuto.
        System.out.println("Places: " + activitatUnDia.getPlaces());            //Número de plazas.
        System.out.println("Preu: " + activitatUnDia.getPreu());                //Precio.
        System.out.println("Ciutat: " + activitatUnDia.getCiutat());            //Ciudad.

        //Probamos si la actividad está activa en una fecha específica.
        LocalDate avuiActiva = LocalDate.of(2025, 11, 30);  //Dentro del período de inscripción.
        System.out.println("Esta activa el " + avuiActiva + "? " + activitatUnDia.estaActiva(avuiActiva)); //Debería ser true.

        LocalDate avuiFora = LocalDate.of(2025, 12, 10);  //Fuera del período de inscripción.
        System.out.println("Esta activa el " + avuiFora + "? " + activitatUnDia.estaActiva(avuiFora)); //Debería ser false.

        //Probamos el método tipusActivitat().
        System.out.println("Tipus activitat: " + activitatUnDia.tipusActivitat());  //Muestra: "Activitat d'un dia".

        //Probamos el método toString() que devuelve toda la información formateada.
        System.out.println("\n--- toString() ---");
        System.out.println(activitatUnDia);
        
        System.out.println("--- FI DEL TEST ---");
    }
}
