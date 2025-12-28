package tests;

import usuaris.UsuariPDI;
import enumeracions.*;

public class UsaUsuariPDI {

    public static void main(String[] args) {

        // 1. Crear un Usuari PDI
        UsuariPDI pdi = new UsuariPDI(
            "ainara",
            "ainara@urv.cat",
            Collectius.PDI,
            DepartamentURV.DEIM,
            CampusURV.SESCELADES
        );

        // 2. Provar toString()
        System.out.println("---- toString() ----");
        System.out.println(pdi);

        // 3. Provar getters
        System.out.println("\n---- Getters ----");
        System.out.println("Departament: " + pdi.getdepartament());
        System.out.println("Campus: " + pdi.getCampus());

        // 4. Provar dadesExtra()
        System.out.println("\n---- dadesExtra() ----");
        System.out.println(pdi.dadesExtra());

        // 5. Provar tipusUsuari()
        System.out.println("\n---- tipusUsuari() ----");
        System.out.println(pdi.tipusUsuari());

        // 6. Provar copia()
        System.out.println("\n---- copia() ----");
        UsuariPDI copia = pdi.copia();
        System.out.println(copia);

        // 7. Provar toCSV()
        System.out.println("\n---- toCSV() ----");
        System.out.println(pdi.toCSV());
    }
}
