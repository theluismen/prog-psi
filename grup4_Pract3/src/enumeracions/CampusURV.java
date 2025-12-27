package enumeracions;

public enum CampusURV {
    CATALUNYA("Catalunya"),
    SESCELADES("Sescelades"),
    BELLISENS("Bellisens"),
    VILASECA("Vila-seca"),
    TERRES_EBRE("Terres de l'ebre"),
    BAIX_PENEDES("Seu Baix Penedés"),
    VILAFRANCA("Seu Vilafranca");

    private String nomCampus;

    CampusURV(String nomCampus){
        this.nomCampus = nomCampus;
    }

    public String getNomCampus(){
        return nomCampus;
    }
    
}
