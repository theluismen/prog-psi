package gui;

import enumeracions.Mesos;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

// TODO: AESHA -- Importar aquí LlistaActivitats i Data quan implementis els filtres
// import llistes.LlistaActivitats;
// import extras.Data;

/**
 * Classe que gestiona l'esdeveniment del botó "Actualitzar".
 * Calcula els dies del mes seleccionat i dibuixa la graella del calendari.
 */
public class Actualitzador implements ActionListener {

    // Calcular dies
    private JComboBox<Mesos> comboMesos;
    private JPanel tablaDeCalendario;

    // TODO: AESHA -- Aquí hauràs d'afegir els atributs per als filtres i la llista
    // private JCheckBox chkUnDia, chkPeriodica, chkOnline;
    // private LlistaActivitats llistaActivitats;

    /**
     * Constructor.
     * Rep els components de la finestra que necessitem manipular.
     */
    public Actualitzador(JComboBox<Mesos> comboMesos, JPanel tablaDeCalendario /*, TODO: AESHA -- Afegir CheckBoxes i Llista aquí */) {
        this.comboMesos = comboMesos;
        this.tablaDeCalendario = tablaDeCalendario;
        
        // TODO: AESHA -- Inicialitzar els teus atributs aquí
        // this.chkUnDia = chkUnDia; ...
        // this.llistaActivitats = llistaActivitats;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Netejar la taula actual (esborrar el mes anterior)
        tablaDeCalendario.removeAll();

        // Obtenim el mes que ha triat l'usuari
        Mesos mesSeleccionat = (Mesos) comboMesos.getSelectedItem();
        int any = 2025; // Any

        // Càlculs de calendari (Matemàtiques de dates)
        // El nostre Enum (9..12) vs Calendar de Java (8..11). Restem 1.
        int mesJava = mesSeleccionat.getNumeroMes() - 1;

        Calendar cal = new GregorianCalendar(any, mesJava, 1);
        int diesAlMes = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        int primerDiaSetmana = cal.get(Calendar.DAY_OF_WEEK); // 1=Diumenge, 2=Dilluns...

        // Ajust visual: volem que la setmana comenci en Dilluns (0)
        // Si és Diumenge (1) deixem 6 buits. Si és Dilluns (2) en deixem 0.
        int casellesBuidesInici = (primerDiaSetmana == Calendar.SUNDAY) ? 6 : (primerDiaSetmana - 2);

        // Bucle per crear les 42 caselles (6 files x 7 dies)
        int totalCaselles = 42;
        int diaActual = 1;

        for (int i = 0; i < totalCaselles; i++) {
            JButton botoDia = new JButton();

            // CAS A: Dies buits abans de començar el mes
            if (i < casellesBuidesInici) {
                botoDia.setEnabled(false); // Deshabilitat
                botoDia.setBackground(new Color(240, 240, 240)); // Grisenc
            }
            // CAS B: Dies vàlids del mes (de l'1 al 30/31)
            else if (diaActual <= diesAlMes) {
                botoDia.setText(String.valueOf(diaActual));
                botoDia.setBackground(Color.WHITE); // Blanc per defecte

                /* * =================================================================
                 * TODO: AESHA -- AQUI VA LA TEVA LÒGICA DE FILTRATGE
                 * =================================================================
                 * 1. Crear objecte Data: Data d = new Data(diaActual, mesSeleccionat.getNumeroMes(), any);
                 * 2. Recórrer llistaActivitats.
                 * 3. Comprovar si cada activitat és del tipus marcat als CheckBoxes.
                 * 4. Si és del tipus marcat i hi ha classe avui (act.avuiHiHaClase(d)):
                 * botoDia.setBackground(Color.GREEN); // Pintar verd
                 * break; // Ja hem trobat una, no cal mirar més
                 */

                diaActual++;
            }
            // CAS C: Dies buits després d'acabar el mes
            else {
                botoDia.setEnabled(false); // Deshabilitat
                botoDia.setBackground(new Color(240, 240, 240));
            }

            // Afegim el botó a la graella visual
            tablaDeCalendario.add(botoDia);
        }

        // Refresquem la pantalla perquè es vegin els canvis
        tablaDeCalendario.revalidate();
        tablaDeCalendario.repaint();
    }
}