/**
 * Autor: Aesha Naz Mahmood Bibi
 * 
 * Descripció: classe controlador del panell Formulari de la interfície gràfica
 * S’encarrega de gestionar els esdeveniments del formulari, especialment
 * el filtratge de les activitats segons el tipus
 * (Online, Periòdica i Un Dia) mitjançant CheckBoxes
 * 
 */
package gui;

import activitats.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import llistes.LlistaActivitats;

public class ControladorFormulari implements ActionListener {

    /* Atributs de la classe */
    private final JCheckBox checkOnline;
    private final JCheckBox checkPeriodica;
    private final JCheckBox checkUnDia;

    private final LlistaActivitats llistaActivitats;
    
    /** Constructor */
    public ControladorFormulari (JCheckBox checkOnline, JCheckBox checkPeriodica, 
        JCheckBox checkUnDia, LlistaActivitats llistaActivitats) {
            
            this.checkOnline = checkOnline;
            this.checkPeriodica = checkPeriodica;
            this.checkUnDia = checkUnDia;
            this.llistaActivitats = llistaActivitats;
        }

    @Override
    public void actionPerformed(ActionEvent e) {

        boolean mostrarOnline = checkOnline.isSelected();
        boolean mostrarPeriodica = checkPeriodica.isSelected();
        boolean mostrarUnDia = checkUnDia.isSelected();

        System.out.println("=== FILTRE ACTIVITATS ===");
        System.out.println("Activitat Online: " + mostrarOnline);
        System.out.println("Activitat Periòdica: " + mostrarPeriodica);
        System.out.println("Activitat Un dia: " + mostrarUnDia);

        //Comprovem que el filtratge funciona
        for (int i = 0; i < llistaActivitats.getNumElements(); i++) {
            try {
                Activitat act = llistaActivitats.getActivitatReal(i);

                if (act instanceof ActivitatOnline && mostrarOnline) {
                    System.out.println("ACTIVITAT ONLINE → " + act.getNom());
                } else if (act instanceof  ActivitatPeriodica && mostrarPeriodica) {
                    System.out.println("ACTIVITAT PERIODICA → " + act.getNom());
                } else if (act instanceof ActivitatUnDia && mostrarUnDia) {
                    System.out.println("ACTIVITAT UN DIA → " + act.getNom());
                }

            } catch (Exception exc) {
                //No hauria de passar
            }
        }
    }
}