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

import enumeracions.*;
import gui.*;
import java.awt.*;
import javax.swing.*;
import llistes.*;

public class AppGrafica extends JFrame{

    /* Atributos de la Clase */
    //Llistes carregades des dels fitxers
    private LlistaActivitats llistaActivitats;

    //Paneles de la GUI
    private final JPanel formulario;
    private final JPanel tablaDeCalendario;
    private final JPanel infoActivitats;

    //Cuadro de texto
    private final JTextArea cuadroDeTexto;


    /* Constructor */
    public AppGrafica() {

        super("Programa Benestar URV");
        //Configuració bàsica del JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Terminar aplicación cuando se cierra la ventana.
        setLocationRelativeTo(null);    //Centramos la ventana en la pantalla.
        setLayout(new BorderLayout());

        //CARREGA FITXERS  
        try {
            llistaActivitats = new LlistaActivitats(100);
            llistaActivitats.carregarFitxer(
                "grup4_Pract3/src/fitxers/activitats.txt"
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
        formulario.setBackground(new Color(226, 220, 210));       
        formulario.setLayout(new FlowLayout(FlowLayout.LEFT));
        add(formulario,BorderLayout.NORTH);

        //CHECKBOXES TIPUS ACTIVITAT
        JCheckBox checkOnline = new JCheckBox("Activitat Online");
        JCheckBox checkPeriodica = new JCheckBox("Activitat Periòdica");
        JCheckBox checkUnDia = new JCheckBox("Activitat Un Dia");

        //per defecte tots estan seleccionats
        checkOnline.setSelected(true);
        checkPeriodica.setSelected(true);
        checkUnDia.setSelected(true);

        //Ho afegim al formulari
        formulario.add(new JLabel("Tipus d'Activitat"));
        formulario.add(checkOnline);
        formulario.add(checkPeriodica);
        formulario.add(checkUnDia);

        // DESPLEGABLE
        JLabel lblMes = new JLabel("Mes (2025):");
        formulario.add(lblMes);

        JComboBox<Mes> comboMesos = new JComboBox<>(Mes.values());
        comboMesos.setPreferredSize(new Dimension(120, 25));
        formulario.add(comboMesos);

        // BOTÓ "ACTUALITZAR"
        JButton btnBuscar = new JButton("Actualitzar Calendari");
        formulario.add(btnBuscar);

            
        /* JPanel tablaDeCalendario */
        tablaDeCalendario = new JPanel();
        tablaDeCalendario.setBackground(new Color(234, 228, 218));      
        tablaDeCalendario.setLayout(new GridLayout(6, 7));
        add(tablaDeCalendario, BorderLayout.CENTER);

        /* Instanciem el nou manejador passant-li els components que ha de controlar */
        Actualitzador manejador = new Actualitzador(comboMesos, tablaDeCalendario, checkOnline, checkPeriodica, checkUnDia, llistaActivitats, this);
        
        // Connectem el botó amb el manejador
        btnBuscar.addActionListener(manejador);

        /* JPanel cuadroDeTexto */
        infoActivitats = new JPanel();
        infoActivitats.setLayout(new BorderLayout());
        
        cuadroDeTexto = new JTextArea();
        cuadroDeTexto.setEditable(false);
        cuadroDeTexto.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        infoActivitats.setBackground(new Color(232, 224, 212));

        infoActivitats.setPreferredSize(new Dimension(550, 0));
        JScrollPane scroll = new JScrollPane(cuadroDeTexto);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        infoActivitats.add(scroll, BorderLayout.CENTER);

        add(infoActivitats, BorderLayout.EAST);
        
        setSize(1400, 800);
    }

    public void afegirText(String s){
        this.cuadroDeTexto.setText("");     //vaciar el cuadro de texto de los otros dias que se hayan revisado anteriormente
        this.cuadroDeTexto.append(s + "\n");
    }

    /* Main */
    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            AppGrafica appGrafica = new AppGrafica();
            appGrafica.setVisible(true);
        });

    }
}