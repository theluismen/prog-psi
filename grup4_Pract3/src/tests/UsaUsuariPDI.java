package tests;

import enumeraciones.CampusURV;
import enumeraciones.DepartamentURV;
import usuaris.UsuariPDI;

public class UsaUsuariPDI {
    public static void main(String[] args) {
        System.out.println("\n---TEST UsuariPDI---");
        testConstructor();
        testSetters();
        testTipusUsuari();
        testCopia();
    }

    private static void testConstructor(){
        System.out.println("\n\n---TEST CONSTRUCTOR---\n");
        UsuariPDI pdi = new UsuariPDI("david", "david.G", "PDI", DepartamentURV.DEIM, CampusURV.SESCELADES);
        System.out.println(pdi);
    }

    private static void testSetters(){
        System.out.println("\n\n---TEST SETTERS---");
        UsuariPDI pdi = new UsuariPDI("jhonson", "jhonsonsb", "PDI", DepartamentURV.DEQ, CampusURV.CATALUNYA);
        System.out.println("\nAntes: ");
        System.out.println(pdi);

        pdi.setDepartament(DepartamentURV.DEIM);
        pdi.setCampus(CampusURV.SESCELADES);
        System.out.println("\nDespués: ");
        System.out.println(pdi); 
    }

    private static void testTipusUsuari(){
        System.out.println("\n\n---TEST TIPUS USUARI---");
        UsuariPDI pdi = new UsuariPDI("lucia", "luciaaerna", "PDI", DepartamentURV.DEQ, CampusURV.SESCELADES);
        System.out.println(pdi.tipusUsuari());
    }

    private static void testCopia(){
        System.out.println("\n\n---TEST COPIA---");
        UsuariPDI original = new UsuariPDI("jhonson", "jhonsonsb", "PDI", DepartamentURV.DEQ, CampusURV.CATALUNYA);
        UsuariPDI copia = original.copia();
        System.out.println("\nOriginal: \n" + original);
        System.out.println("\nCopia: \n" + copia);
    }
}
