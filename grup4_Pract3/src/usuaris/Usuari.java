package usuaris;

public abstract class Usuari {
    
    protected String alies;           //Identificador del usuari
    protected String collectiu;      // PDI / PTGAS / Estudiant
    protected String email;           // Part principal del correu (sense @)



    /**
     * Constructor de la classe Usuari
     * @param alies àlies de l'usuari (únic)
     * @param email part inicial del correu institucional (sense @)
     * @param colectiu col·lectiu al qual pertany (PDI, PTGAS o Estudiant)
     */
    public Usuari (String alies, String email, String collectiu) {
        this.alies = alies;
        this.email = email;
        this.collectiu = collectiu;
    }


    //Getters
    
    public String getAlies() {  return alies;  }

    public String getEmailComplet() {
        //Segons el tipus de col·lectiu: URV o PDI i PTGAS
        if (collectiu.equalsIgnoreCase("Estudiant")) {
            return email + "@estudiants.urv.cat";
        } else {
            return email + "@urv.cat";  //PDI i PTGAS
        }
    }

    public String getCollectiu() {  return collectiu;  }


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

    @Override
    public String toString() {
        return "Àlies: " + alies + 
                "\nCorreu: " + getEmailComplet() + 
                "\nCol·lectiu: " + collectiu +
                "\n" + dadesExtra() + "\n";
    }

    
}
