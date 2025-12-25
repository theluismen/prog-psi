/**
 * Autor(@s): Alexandra Núñez
 * Descripción: clase para crear instancias de usuariPTGAS, se usará para guardar la informacion de los 
 * usuarios del colectivo PTGAS
 */

package usuaris;

import enumeraciones.*;

public class UsuariPTGAS extends Usuari{
    private CampusURV campus;


    public UsuariPTGAS(String alies, String email, Collectius collectiu, CampusURV campus){
        super(alies, email, Collectius.PTGAS);
        this.campus = campus;
    }

//getter y setter
    public String getCampus(){  return campus.getNomCampus();    }
    public void setCampus(CampusURV nouCampus){     this.campus = nouCampus;    }


//metodos abstractos del padre
    /**
     * Mètode que retorna informació extra de cada tipus d'usuari
     * 
     * Estudiant -> ensenyament + any inici
     * PDI -> departament + campus
     * PTGAS -> campus
     * 
     * @return informació específica de la subclasse
     */
    @Override
    public String dadesExtra(){
        return "\nCampus: "+campus.getNomCampus();
    }

    /**
     * Mètode que retorna el tipus d'usuari (Estudiant, PDI, PTGAS)
     * 
     * @return String del tipus d'Usuari
     */
    @Override
    public String tipusUsuari(){
        return "Usuari PTGAS";
    }

    /**
     * Mètode que retorna un duplicat de la instancia
     * @return duplicado
     */
    @Override
    public UsuariPTGAS copia(){
        return new UsuariPTGAS(super.alies, super.email, super.collectiu, this.campus);
    }

//toString
@Override
    public String toString(){
        return super.toString() + this.dadesExtra();
    }

//toCSV
    @Override
    public String toCSV() {
        return getAlies() + ";" + getEmailComplet() + ";" +
           getCollectiu() + ";" + this.campus;
    }
}