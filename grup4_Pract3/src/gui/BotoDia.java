/**
 * Autor(@s): Alexandra Núñez González
 *       
 * Descripción: archivo usado para generar la accion al clickar en el boton de cada dia del calendario
 * Clickar una vez hará que se borre lo que ya esta escrito y aparezcan las nuevas actividades
 */

package gui;

import java.awt.event.*;
import apps.AppGrafica;
import extras.Data;
import llistes.LlistaActivitats;

public class BotoDia implements ActionListener{

    private AppGrafica ventana;
    private final Data dia;
    private final LlistaActivitats llista;
    private final boolean online;
    private final boolean periodiques;
    private final boolean unDia;

    /**
     * Constructor del actionListener de los botones del calendario
     * @param ventana
     */
    public BotoDia (AppGrafica ventana, Data dia, LlistaActivitats llista, boolean online, boolean periodiques, boolean unDia){
        this.ventana = ventana;
        this.dia = dia;
        this.llista = llista;
        this.online = online;
        this.periodiques = periodiques;
        this.unDia = unDia;
    }

    public void actionPerformed (ActionEvent evt){
        try{
            //creem les llistes de cada tipus d'activitat unicament amb les que tenen clase en la data d'avui
            LlistaActivitats actOnline = this.llista.tipusLlista("online").claseAvui(this.dia);
            LlistaActivitats actPer = this.llista.tipusLlista("activitat periodica").claseAvui(this.dia);
            LlistaActivitats actDia = this.llista.tipusLlista("Activitat d'un dia").claseAvui(this.dia);
            
            String aux = "";
            
            //a l'String s'afegeixen totes les activitats amb clase avui
            if (this.online){
                aux += actOnline.toString();
            }
            if (this.periodiques){
                aux += actPer.toString();
            }
            if (this.unDia){
                aux += actDia.toString();
            }

            //es crida al metodo de l'appGrafica per afegir text al quadre de text
            ventana.afegirText(aux);

        }catch(Exception e){
            //no hauria de pasar
            System.out.println("Error desconegut en botoDia");
        }

    }
    
}
