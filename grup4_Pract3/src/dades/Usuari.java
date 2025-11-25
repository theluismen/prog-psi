package dades;

public class Usuari {
    protected String alies; // Identificador únic (ID)
    protected String email; // Només la part esquerra de @urv.cat

    public Usuari(String alies, String email) {
        this.alies = alies;
        this.email = email;
    }

    public String getAlies() { return alies; }
    public String getEmail() { return email; }

    // Mètode abstracte per forçar als fills a definir com es guarden al fitxer
    public abstract String dadesPerFitxer();

    @Override
    public String toString() {
        return "Usuari{" + "alies='" + alies + '\'' + ", email='" + email + '\'' + '}';
    }
}
