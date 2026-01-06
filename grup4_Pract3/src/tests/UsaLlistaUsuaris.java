/**
 * Autor: Aesha Naz Mahmood Bibi
 * Descripció: classe Usa Llista Usuaris de test per probar la classe
 */
package test;

import enumeracions.*;
import excepcions.*;
import java.io.IOException;
import llistes.LlistaUsuaris;

public class UsaLlistaUsuaris {

    public static void main(String[] args) {
        LlistaUsuaris llista = new LlistaUsuaris();

        System.out.println("-- Test LlistaUsuaris --");

        try {
            // Carrega des de fitxer
            System.out.println("\nLlegint fitxer usuaris.txt ...");
            llista.carregarFitxer("usuaris.txt");

            // Mostrar quantitat d'usuaris
            System.out.println("\nTotal d'usuaris carregats: " + llista.getNumUsuaris());

            // Mostrar col·lectiu Estudiant
            System.out.println("\n--- Usuaris del col·lectiu Estudiant ---");
            llista.mostrarUCollectiu(Collectius.ESTUDIANT);

            // Cerca d'un usuari
            System.out.println("\n--- Cercant usuari `marta` ---");
            System.out.println(llista.cerca("marta"));

        } catch (UsuariDuplicat e) {
            System.err.println("ERROR: Usuari duplicat → " + e.getMessage());

        } catch (FormatInvalid e) {
            System.err.println("ERROR de format → " + e.getMessage());

        } catch (CollectiuDesconegut e) {
            System.err.println("ERROR col·lectiu → " + e.getMessage());

        } catch (IOException e) {
            System.err.println("No s'ha pogut llegir el fitxer: " + e.getMessage());
        }
        System.out.println("\n-- FI DEL TEST --");
    }
}