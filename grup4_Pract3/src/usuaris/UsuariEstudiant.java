/**
 * Autor(@s): Ikram Kheira
 * Descripción: Classe que representa un usuari del col·lectiu Estudiant
 */

package usuaris;

public class UsuariEstudiant extends Usuari {

    /* Atributos de la Clase */
    private String ensenyament; // Ex: GEI, GESST, etc.
    private int anyInici;       // Any d'inici dels estudis

    /* Constructor */
    /**
     * Constructor de UsuariEstudiant
     * @param alies Identificador únic
     * @param email Part esquerra del correu
     * @param ensenyament Sigles de l'ensenyament
     * @param anyInici Any de matriculació
     */
    public UsuariEstudiant(String alies, String email, String ensenyament, int anyInici) {
        // Passem "Estudiant" automàticament al pare
        super(alies, email, "Estudiant");
        this.ensenyament = ensenyament;
        this.anyInici = anyInici;
    }

    /* Getters y Setters */
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

    /* Métodos */

    @Override
    public String tipusUsuari() {
        return "Estudiant";
    }

    @Override
    public String dadesExtra() {
        // Afegim el salt de línia inicial per mantenir format amb el pare
        return "\nEnsenyament: " + ensenyament + "\nAny d'inici: " + anyInici;
    }

    @Override
    public String toString() {
        // Cridem al pare i afegim les dades extra
        return super.toString() + this.dadesExtra();
    }

    /**
     * Retorna les dades en format CSV per guardar al fitxer.
     * Format: alies;email;collectiu;ensenyament;anyInici
     */
    public String toCSV() {
        return super.alies + ";" + super.email + ";" + super.collectiu + ";" + this.ensenyament + ";" + this.anyInici;
    }

    @Override
    public UsuariEstudiant copia() {
        // Retornem una nova instància amb les mateixes dades (còpia)
        return new UsuariEstudiant(super.alies, super.email, this.ensenyament, this.anyInici);
    }
}