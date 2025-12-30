package enumeracions;

public enum Mes {
    SETEMBRE(9, "Setembre"),
    OCTUBRE(10, "Octubre"),
    NOVEMBRE(11, "Novembre"),
    DESEMBRE(12, "Desembre");

    private final int numeroMes;
    private final String nomMostrar;

    Mes(int numeroMes, String nomMostrar) {
        this.numeroMes = numeroMes;
        this.nomMostrar = nomMostrar;
    }

    public int getNumeroMes() {
        return numeroMes;
    }

    @Override
    public String toString() {
        return nomMostrar;
    }
}