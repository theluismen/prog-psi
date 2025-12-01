//Ikram Kheira

package usuaris;

public class UsuariEstudiant extends Usuari {

    private String ensenyament; // Ex: GEI, GESST, etc.
    private int anyInici;       // Any d'inici dels estudis

    /**
     * Constructor
     * @param alies Identificador
     * @param email Part esquerra del correu
     * @param ensenyament Sigles de l'ensenyament (GEI, etc.)
     * @param anyInici Any de matriculació
     */
    public UsuariEstudiant(String alies, String email, String ensenyament, int anyInici) {
        // Cridem al constructor del pare.
        // Important: Passem "Estudiant" com a col·lectiu automàticament
        // perquè el pare sàpiga quin sufix de correu posar.
        super(alies, email, "Estudiant");
        
        this.ensenyament = ensenyament;
        this.anyInici = anyInici;
    }

    // --- Getters i Setters ---
    
    public String getEnsenyament() {
        return ensenyament;
    }

    public void setEnsenyament(String ensenyament) {
        this.ensenyament = ensenyament;
    }

    public int getAnyInici() {
        return anyInici;
    }

    public void setAnyInici(int anyInici) {
        this.anyInici = anyInici;
    }

    // --- Implementació mètodes abstractes ---

    @Override
    public String tipusUsuari() {
        return "Estudiant";
    }

    @Override
    public String dadesExtra() {
        // Aquest string s'afegirà al toString del pare
        return "Ensenyament: " + ensenyament + "\nAny d'inici: " + anyInici;
    }

    // --- Mètode extra per format fitxer (opcional però recomanat) ---
    
    /**
     * Retorna les dades en format CSV per guardar al fitxer.
     * Format: alies;email;collectiu;ensenyament;anyInici
     */
    public String toCSV() {
        return alies + ";" + email + ";" + collectiu + ";" + ensenyament + ";" + anyInici;
    }
}