//Aesha Naz

package usuaris;

public abstract class Usuari {
    
    protected String alies;           //Identificador del usuari
    protected String collectiu;      // PDI / PTGAS / Estudiant
    protected String email;          // Part principal del correu (sense @)

    /**
     * Constructor de la classe Usuari
     * @param alies àlies de l'usuari (únic)
     * @param email part inicial del correu institucional (sense @)
     * @param collectiu col·lectiu al qual pertany (PDI, PTGAS o Estudiant)
     */
    public Usuari (String alies, String email, String collectiu) {
        this.alies = alies;
        this.email = email;
        this.collectiu = collectiu;
    }

    /**
     * Mètode que retorna l'àlies indentificador de l'usuari
     * 
     * @return una cadena amb l'àlies de l'usuari
     */
    public String getAlies() {  
        return alies;  
    }

    /**
     * Mètode que genera i retorna l'email complet de l'usuari
     * Estudiant -> {@estudiants.urv.cat}
     * PDI i PTGAS -> {@urv.cat}
     * 
     * @return una cadena amb l'adreça de correu electrònic completa
     */
    public String getEmailComplet() {
        //Segons el tipus de col·lectiu: URV o PDI i PTGAS
        if (collectiu.equalsIgnoreCase("Estudiant")) {
            return email + "@estudiants.urv.cat";
        } else {
            return email + "@urv.cat";  //PDI i PTGAS
        }
    }

    /**
     * Metode que retorna el col·lectiu al qual pertany l'usuari
     * 3 tipus de col·lectius:
     * - Estudiant
     * - PDI
     * - PTGAS
     * 
     * @return una cadena de text que identifica el col·lectiu del usuari
     */
    public String getCollectiu() {  
        return collectiu;  
    }

    /**
     * Mètode que retorna informació extra de cada tipus d'usuari
     * 
     * Estudiant -> ensenyament + any inici
     * PDI -> departament + campus
     * PTGAS -> campus
     * 
     * @return informació específica de la subclasse
     */
    public abstract String dadesExtra();

    /**
     * Mètode que retorna el tipus d'usuari (Estudiant, PDI, PTGAS)
     * 
     * @return String del tipus d'Usuari
     */
    public abstract String tipusUsuari();

    /**
     * Metode que retorna una còpia del Usuari
     * 
     * @return una nova instància d'Usuari
     */
    public abstract Usuari copia();

    /**
     * Retorna una reperesentació textual de l'usuari, incloent
     * l'àlies, el correu complet i el col·lectiu
     * 
     * @return una cadena de text amb l'àlies, el correu i el col·lectiu
     */
    @Override
    public String toString() {
        return "Àlies: " + alies + 
                "\nCorreu: " + getEmailComplet() + 
                "\nCol·lectiu: " + collectiu;
    }

    
}
