package tests;

import enumeraciones.CampusURV;
import usuaris.UsuariPTGAS;

public class UsaUsuariPTGAS {
    public static void main(String[] args){
        System.out.println("PROVES UsuariPTGAS");

        testConstructor();
        testSetter();
        //dades_extra ya se prueba con el toString
        testTipusUsuari();

    }

    private static void testConstructor(){
        System.out.println("\n\nTest del constructor");
        UsuariPTGAS u1 = new UsuariPTGAS("joanp", "PTGAS", "juan.perez", CampusURV.CATALUNYA);
        System.out.println(u1);
    }

    private static void testSetter(){
        System.out.println("\n\nTest del setter");
        UsuariPTGAS u1 = new UsuariPTGAS("joanp", "PTGAS", "juan.perez", CampusURV.CATALUNYA);
        System.out.println(u1);

        u1.setCampus(CampusURV.SESCELADES);
        System.out.println(u1);
    }

    private static void testTipusUsuari(){
        System.out.println("\n\nTest del tipo de usuario");
        UsuariPTGAS u1 = new UsuariPTGAS("joanp", "PTGAS", "juan.perez", CampusURV.CATALUNYA);
        System.out.println(u1.tipusUsuari());
    }

}
