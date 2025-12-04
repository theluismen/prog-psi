//Alexandra Núñez

package usuaris;

import enumeraciones.*;

public class UsuariPTGAS extends Usuari{
    private CampusURV campus;


    public UsuariPTGAS(String alies, String collectiu, String email, CampusURV campus){
        super(alies, email, collectiu);
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
    public String dadesExtra(){
        return "\nCampus: "+campus.getNomCampus();
    }

    /**
     * Mètode que retorna el tipus d'usuari (Estudiant, PDI, PTGAS)
     * 
     * @return String del tipus d'Usuari
     */
    public String tipusUsuari(){
        return "Usuari PTGAS";
    }

    /**
     * Mètode que retorna un duplicat de la instancia
     * @return duplicado
     */
    public UsuariPTGAS copia(){
        return new UsuariPTGAS(super.alies, super.collectiu, super.email, this.campus);
    }

//toString
    public String toString(){
        return super.toString() + this.dadesExtra();
    }


}
