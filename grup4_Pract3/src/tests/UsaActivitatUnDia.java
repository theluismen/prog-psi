/**
 * Autor(@s): Ainara Sofía Cabrera Robles
 * Descripción: Test per a la classe ActivitatUnDia.
 */
package tests;

import activitats.ActivitatUnDia;
import enumeracions.Collectius;
import excepcions.*;
import extras.Data;

public class UsaActivitatUnDia {

    public static void main(String[] args) {

        try {
            // 1. Col·lectius
            Collectius[] collectius = {
                Collectius.ESTUDIANT,
                Collectius.PDI
            };

            // 2. Dates d'inscripció
            Data dataIniInsc = new Data(1, 3, 2025);
            Data dataFiInsc  = new Data(10, 3, 2025);

            // 3. Data i hora de l'activitat
            Data dataActivitat = new Data(15, 3, 2025, 10, 30);

            // 4. Crear ActivitatUnDia
            ActivitatUnDia act = new ActivitatUnDia(
                "Visita laboratori",
                collectius,
                dataIniInsc,
                dataFiInsc,
                dataActivitat,
                2,          // hores durada
                15,         // minuts durada
                20,         // places
                12.5,       // preu
                "Tarragona" // ciutat
            );

            // 5. Provar toString()
            System.out.println("---- toString() ----");
            System.out.println(act);

            // 6. Provar getters
            System.out.println("\n---- Getters ----");
            System.out.println("Ciutat: " + act.getCiutat());
            System.out.println("Hora inici: " + act.getDataInici().getHora() + ":" + act.getDataInici().getMinuts());
            System.out.println("Durada: " + act.getHoraDurada() + ":" + act.getMinutoDurada());
            System.out.println("Places màximes: " + act.getPlacesMaximes());
            System.out.println("Preu: " + act.getPreu());

            // 7. Provar estaActiva / avuiHiHaClase
            Data avui = new Data(15, 3, 2025);
            System.out.println("\n---- Activitat avui? ----");
            System.out.println("estaActiva: " + act.estaActiva(avui));
            System.out.println("avuiHiHaClase: " + act.avuiHiHaClase(avui));

            // 8. Provar copia()
            System.out.println("\n---- Copia ----");
            ActivitatUnDia copia = act.copia();
            System.out.println(copia);

            // 9. Provar toCSV()
            System.out.println("\n---- toCSV() ----");
            System.out.println(act.toCSV());

        } catch (ValorInexistent e) {
            System.out.println("Error de valor: " + e.getMessage());
        } catch (CollectiuDesconegut e) {
            System.out.println("Error de col·lectiu: " + e.getMessage());
        }
    }
}
