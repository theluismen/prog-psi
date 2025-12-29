/**
 * Autor(@s):Ainara Sofía Cabrera Robles
 *          Ikram Hallouz Safa 
            Aesha Naz Mahmood Bibi
            Alexandra Núñez González
 * Descripción: [Breve descripción de la clase, si es necesario]
 */
package apps;

import java.awt.*;
import java.util.Scanner;
import javax.swing.*;
import fitxers.*;

public class AppGrafica extends JFrame{

    /* Atributos de la Clase */
    private JPanel formulario;
    private JPanel tablaDeCalendario;
    private JPanel cuadroDeTexto;

    /* Constructor */
    public AppGrafica() {
        super("Programa Benestar URV");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Terminar aplicación cuando se cierra la ventana.
        setSize(400,300);
        setLocationRelativeTo(null);    //Centramos la ventana en la pantalla.
    }

    /* Main */
    public static void main(String[] args){
        AppGrafica appGrafica = new AppGrafica();
        appGrafica.setVisible(true);
    }
}
