package tests;

import extras;
import activitats.ActivitatOnline;

public class UsaActivitatOnline {

    public static void main(String[] args) {
        System.out.println("-------------------------------------------------");
        System.out.println(" INICI TEST ACTIVITAT ONLINE");
        System.out.println("-------------------------------------------------");

        try {
            // 1. Preparació de dades de prova
            
            // Definim les dates (Any, Mes, Dia)
            Data iniciInscripcio = Data.of(2025, 9, 1);
            Data fiInscripcio = Data.of(2025, 9, 15);
            Data dataIniciActivitat = Data.of(2025, 10, 1);
            
            // Definim els col·lectius (ARA ÉS UN ARRAY)
            String[] targetCollectius = {"Estudiants", "PDI"};

            // 2. Creació de l'objecte ActivitatOnline
            // Constructor: (nom, collectius[], iniciIns, fiIns, dataIniciAct, duradaDies, url)
            ActivitatOnline activitat = new ActivitatOnline(
                "Curs de Java Avançat", // Nom
                targetCollectius,       // Array de col·lectius
                iniciInscripcio,        // Inici inscripció
                fiInscripcio,           // Fi inscripció
                dataIniciActivitat,     // Inici activitat
                30,                     // Durada (dies)
                "https://campus.urv.cat/curs-java" // Enllaç
            );

            // 3. Mostrar informació (Test del toString)
            System.out.println(">> Dades de l'activitat creada:");
            System.out.println(activitat.toString());

            // 4. Test de lògica de dates (Mètodes heretats i propis)
            
            // Cas A: Comprovar si avui (data simulada) està en període d'inscripció
            Data diaInscripcio = Data.of(2025, 9, 10); // Dins termini
            boolean pucInscriure = activitat.esEnPeriodeInscripcio(diaInscripcio);
            System.out.println("Es pot inscriure el " + diaInscripcio + "?: " + pucInscriure);

            // Cas B: Comprovar si l'activitat està activa (visible)
            Data diaActivitat = Data.of(2025, 10, 5); // Dins dels 30 dies
            boolean estaActiva = activitat.estaActiva(diaActivitat);
            System.out.println("Està activa el " + diaActivitat + "?: " + estaActiva);
            
            // Cas C: Comprovar una data fora de termini
            Data diaTard = Data.of(2025, 12, 1); // Passats els 30 dies
            System.out.println("Està activa el " + diaTard + "?: " + activitat.estaActiva(diaTard));

            // 5. Test de Getters específics
            System.out.println("\n>> Test Getters específics:");
            System.out.println("Enllaç: " + activitat.getEnllac());
            System.out.println("Dies durada: " + activitat.getPeriodeVisualitzacio());

        } catch (Exception e) {
            System.out.println("ERROR DURANT EL TEST:");
            e.printStackTrace();
        }

        System.out.println("-------------------------------------------------");
        System.out.println(" FI DEL TEST");
        System.out.println("-------------------------------------------------");
    }
}