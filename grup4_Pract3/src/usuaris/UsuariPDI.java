package usuaris;

public class UsuariPDI extends Usuari{

    private String departament;
    private String campus;

    /**
     * Constructor UsuariPDI.
     * @param alies Alias de la persona
     * @param email
     * @param collectiu
     * @param department
     * @param campus
     */
    public UsuariPDI(String alies, String email, String collectiu, String department, String campus){
        super(alies, email, "PDI");
        this.departament = department;
        this.campus = campus;
    }
}
