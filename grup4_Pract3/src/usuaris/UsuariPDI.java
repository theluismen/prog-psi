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
                     Collectius collectiu, 
                     DepartamentURV departament, 
                     CampusURV campus){

        super(alies, email, Collectius.PDI);
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
    @Override
    public String dadesExtra(){
        return "\nDepartament: " + departament.getNomDepartament() + 
               "\nCampus: " + campus.getNomCampus();
    }

    /**
     * Mètode que retorna el tipus d'usuari (Estudiant, PDI, PTGAS)
     * 
     * @return String del tipus d'Usuari
     */
    @Override
    public String tipusUsuari(){
        return "Usuari PDI";
    }

    /**
     * Mètode que retorna un duplicat de la instancia
     * @return duplicado
     */
    @Override
    public UsuariPDI copia(){
        return new UsuariPDI(super.alies,super.email,super.collectiu,this.departament,this.campus);
    }

    /**
     * Método toString
     */
    @Override
    public String toString() {
        return super.toString() + this.dadesExtra();
    }

    @Override
    public String toCSV() {
        return getAlies() + ";" + getEmailComplet() + ";" + getCollectiu() + ";" +
           this.departament + ";" + this.campus;
    }

}