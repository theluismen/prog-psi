package usuaris;


public class UsuariPDI extends Usuari{

    private String departament;
    private String campus;

    /**
     * Constructor UsuariPDI.
     * @param alies Alias de la persona.
     * @param email Correo electrónico.
     * @param collectiu Colectivo que es PDI para esta clase.
     * @param department Departamento.
     * @param campus Campus de la persona.
     */
    public UsuariPDI(String alies, String email, String collectiu, String departament, String campus){
        super(alies, email, "PDI");
        this.departament = departament;
        this.campus = campus;
    }

    //Getters...

    /**
     * Getter de departamento.
     * @return nombre del departammento.
     */
    public String getdepartament(){
        return departament;
    }

    /**
     * Getter de campus.
     * @return nombre del campus.
     */
    public String getCampus(){
        return campus;
    }
    

    /**
     * Mètode que retorna la información extra del usuario PDI.
     * PDI -> departament + campus
     * 
     * @return informació específica del usuario PDI.
     */
    @Override
    public String dadesExtra(){
        return departament + " " + campus;
    }

    /**
     * Mètode que retorna el tipo de usuario.
     * 
     * @return String del tipo de usuario.
     */
    @Override
    public String tipusUsuari(){
        return "PDI";
    }


    @Override
    public String toString() {
        return "Àlies: " + getAlies() + 
                "\nCorreu: " + getEmailComplet() + 
                "\nCollectiu: " + tipusUsuari() +
                "\nDepartament: " + departament +
                "\nCampus: " + campus;
    }
}
