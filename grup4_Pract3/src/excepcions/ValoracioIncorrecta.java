package excepcions;

public class ValoracioIncorrecta extends Exception {
    private static final long serialVersionUID = 1L;

    public ValoracioIncorrecta(String missatge) {
        super(missatge);
    }
}
