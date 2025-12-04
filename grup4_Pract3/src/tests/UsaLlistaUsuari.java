package test;

import enumeraciones.CampusURV;
import llistes.LlistaUsuaris;
import usuaris.*;

public class UsaLlistaUsuaris {

    public static void main(String[] args) {
        LlistaUsuaris llista = new LlistaUsuaris();

        System.out.println("-- Test LlistaUsuaris --");

        llista.afegirUsuari(new UsuariEstudiant("joan2", "joan.perez", "GEI", 2022));
        llista.afegirUsuari(new UsuariPDI("marta", "marta.sole","DEIM", "Sescelades"));
        llista.afegirUsuari(new UsuariPTGAS("pau",  "pau.sala", CampusURV.TERRES_EBRE));

        System.out.println("\nTotal usuaris: " + llista.getNumUsuaris());

        System.out.println("\n--- Usuaris Estudiants ---");
        llista.mostrarUCollectiu("Estudiant");

        System.out.println("\n--- Tots els usuaris ---");
        llista.mostrarU();

        System.out.println("\n--- Cerca usuari `marta` ---");
        System.out.println(llista.cerca("marta"));

    }




}