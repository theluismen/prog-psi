//Ainara Sofia

package usuaris;

import enumeraciones.*;

public class UsuariPDI extends Usuari{

    private DepartamentURV departament;
    private CampusURV campus;

    /**
     * Constructor UsuariPDI.
     * @param alies Alias de la persona.
     * @param email Correo electrónico.
     * @param collectiu Colectivo.
     * @param department Departamento.
     * @param campus Campus de la persona.
     */
    public UsuariPDI(String alies, 
                     String email, 
                     String collectiu, 
                     DepartamentURV departament, 
                     CampusURV campus){

        super(alies, email, collectiu);
        this.departament = departament;
        this.campus = campus;
    }


    //Getters 

    /**
     * Getter de departamento.
     * @return nombre del departammento.
     */
    public String getdepartament(){
        return departament.getNomDepartament();
    }

    /**
     * Getter de campus.
     * @return nombre del campus.
     */
    public String getCampus(){
        return campus.getNomCampus();
    }
    

    //Setters

    public void setCampus(CampusURV noCampusURV){
        this.campus = noCampusURV;
    }

    public void setDepartament(DepartamentURV nouDepartamentURV){
        this.departament = nouDepartamentURV;
    }


    //Métodos abstractos del padre.
    
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
        return "\nDepartament: " + departament.getNomDepartament() + 
               "\nCampus: " + campus.getNomCampus();
    }

    /**
     * Mètode que retorna el tipus d'usuari (Estudiant, PDI, PTGAS)
     * 
     * @return String del tipus d'Usuari
     */
    public String tipusUsuari(){
        return "Usuari PDI";
    }

    /**
     * Método toString
     */
    public String toString() {
        return super.toString() + this.dadesExtra();
    }
}
