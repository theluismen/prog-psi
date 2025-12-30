/**
 * Autor(@s):Ainara Sofía Cabrera Robles
 *          Ikram Hallouz Safa 
            Aesha Naz Mahmood Bibi
            Alexandra Núñez González
 * Descripción: Classe principal de la interfície gràfica del programa Benestar URV.
 * Carrega les dades dels fitxers i construeix l'estructura bàsica de la finestra principal,
 * dividida en 3 panells: formulari, calendari i quadre de text.
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
    //Llistes carregades des dels fitxers
    private LlistaActivitats llistaActivitats;
    private LlistaUsuaris llistaUsuaris;
    private LlistaInscripcions llistaInscripcions;

    //Paneles de la GUI
    private JPanel formulario;
    private JPanel tablaDeCalendario;
    private JPanel cuadroDeTexto;


    /* Constructor */
    public AppGrafica() {

        super("Programa Benestar URV");
        //Configuració bàsica del JFrame
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
        formulario.setBackground(Color.LIGHT_GRAY);                 //COLOR RANDOM PARA DISTINGUIR LOS JPANELS TEMPORAL
        formulario.setLayout(new FlowLayout(FlowLayout.LEFT));
        add(formulario,BorderLayout.NORTH);

        // TODO: AÑADIR AQUI TODOS LOS BOTONES Y COMBOBOX NECESARIOS EN FORMULARIO
        
       
        /* JPanel tablaDeCalendario */
        tablaDeCalendario = new JPanel();
        tablaDeCalendario.setBackground(Color.PINK);                //COLOR RANDOM PARA DISTINGUIR LOS JPANELS TEMPORAL
        tablaDeCalendario.setLayout(new GridLayout(6,7));
        add(tablaDeCalendario, BorderLayout.CENTER);

        // TODO: AÑADIR AQUI TODO LONECESARIO PARA TABLLACALENDARIO


        /* JPanel cuadroDeTexto */
        cuadroDeTexto = new JPanel();
        cuadroDeTexto.setBackground(Color.GREEN);                   //COLOR RANDOM PARA DISTINGUIR LOS JPANELS TEMPORAL
        cuadroDeTexto.setPreferredSize(new Dimension(25,0));
        add(cuadroDeTexto, BorderLayout.EAST);

        // TODO: AÑADIR AQUI TODO LO NECESARIO PARA CUADRO DE TEXTO

        // pack();  COMANDO PARA QUE UNA VEZ QUE ESTEN TODOS LOS BOXES Y BOTONES SE AJUSTE AUTOMATICAMENTE EL TAMAÑO DEL JPANEL
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
