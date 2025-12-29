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
import llistes.LlistaActivitats;
import llistes.LlistaInscripcions;
import llistes.LlistaUsuaris;

public class AppGrafica extends JFrame{

    /* Atributos de la Clase */
    private LlistaActivitats llistaActivitats;
    private LlistaUsuaris llistaUsuaris;
    private LlistaInscripcions llistaInscripcions;

    private JPanel formulario;
    private JPanel tablaDeCalendario;
    private JPanel cuadroDeTexto;

    /* Constructor */
    public AppGrafica() {

        super("Programa Benestar URV");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Terminar aplicación cuando se cierra la ventana.
        setLocationRelativeTo(null);    //Centramos la ventana en la pantalla.
        setLayout(new BorderLayout());

        //CARREGA FITXERS  
        try {
            llistaInscripcions = LlistaInscripcions.carregarFitxer(
                "grup4_Pract3/src/fitxers/inscripcions.dat"
            );

            llistaUsuaris = new LlistaUsuaris();
            llistaUsuaris.carregarFitxer(
                "grup4_Pract3/src/fitxers/usuaris.txt"
            );

            llistaActivitats = new LlistaActivitats(100);
            llistaActivitats.carregarFitxer(
                "grup4_Pract3/src/fitxers/activitat.txt"
            );

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                this,
                "Error carregant dades: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
}
        /* JPanel formulario */
        formulario = new JPanel();
        formulario.setBackground(Color.LIGHT_GRAY);
        formulario.setLayout(new FlowLayout(FlowLayout.LEFT));
        add(formulario,BorderLayout.NORTH);
       
        /* JPanel tablaDeCalendario */
        tablaDeCalendario = new JPanel();
        tablaDeCalendario.setBackground(Color.PINK);
        tablaDeCalendario.setLayout(new GridLayout(4,7));
        add(tablaDeCalendario, BorderLayout.CENTER);

        /* JPanel cuadroDeTexto */
        cuadroDeTexto = new JPanel();
        cuadroDeTexto.setBackground(Color.GREEN);
        cuadroDeTexto.setPreferredSize(new Dimension(25,0));
        add(cuadroDeTexto, BorderLayout.EAST);
        
        setSize(900, 600);
    }

    /* Main */
    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
        AppGrafica appGrafica = new AppGrafica();
        appGrafica.setVisible(true);
        });

    }
}
