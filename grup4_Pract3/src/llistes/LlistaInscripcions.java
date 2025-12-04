//Ikram Hallouz

package llistes;

import java.io.*;
import extras.Inscripcions;

/**
 * Classe que gestiona la llista d'inscripcions fent servir un array dinàmic.
 * Implementa Serializable per poder guardar-se directament al fitxer .dat.
 */
public class LlistaInscripcions implements Serializable {

    private static final long serialVersionUID = 1L;

    // Atributs
    private Inscripcions[] llista; // Array on guardarem les inscripcions
    private int nElems;          // Número real d'elements que tenim
    private int capacitat;       // Mida total de l'array actual

    /**
     * Constructor.
     * Inicialitza la llista amb una capacitat inicial (ex: 10).
     */
    public LlistaInscripcions() {
        // Aquí he d'inicialitzar el comptador d'elements a 0 perquè la llista comença buida.
        // He de definir una capacitat inicial per a l'array, per exemple 10 posicions.
        // Finalment, he d'instanciar l'array 'llista' amb aquesta capacitat que he decidit.
    }

    // --- MÈTODES DE GESTIÓ ---

    /**
     * Afegeix una inscripció al final de la llista.
     * Si l'array està ple, l'ha d'ampliar abans d'afegir.
     * @param ins La inscripció a afegir.
     */
    public void afegirInscripcio(Inscripcio ins) {
        // Primer he de comprovar si l'array està ple (si nElems és igual a capacitat).
        // Si està ple, he de cridar al meu mètode 'ampliarCapacitat()' per fer lloc.
        
        // Ara ja puc guardar la inscripció 'ins' a la posició 'nElems' de l'array.
        // I no m'he d'oblidar d'incrementar nElems perquè ara tinc un element més.
    }

    /**
     * Mètode privat per duplicar la mida de l'array quan s'omple.
     */
    private void ampliarCapacitat() {
        // He de calcular la nova capacitat, normalment la multiplico per 2.
        // Creo un nou array de tipus Inscripcio amb aquesta nova capacitat més gran.
        // Ara he de fer un bucle for per copiar tots els elements de l'array vell al nou, un per un.
        // Canvio la referència: l'atribut 'llista' ara ha d'apuntar a aquest nou array.
        // Finalment, actualitzo l'atribut 'capacitat' amb el nou valor.
    }

    /**
     * Elimina una inscripció concreta donat l'usuari i l'activitat.
     * @param idUsuari Àlies de l'usuari
     * @param idActivitat Nom de l'activitat
     */
    public void eliminarInscripcio(String idUsuari, String idActivitat) {
        // Primer he de buscar a quina posició (índex) està la inscripció que vull esborrar.
        // Faré un bucle for que recorri fins a nElems buscant la coincidència d'IDs.
        
        // Si trobo la posició:
        // 1. He de moure tots els elements que hi ha a la dreta una posició cap a l'esquerra per tapar el forat (llista[i] = llista[i+1]).
        // 2. Un cop moguts, decremento nElems.
        // 3. Opcionalment, puc posar a null l'última posició que ha quedat buida per netejar.
    }

    /**
     * Comprova si existeix una inscripció.
     * @return true si existeix, false si no.
     */
    public boolean existeix(String idUsuari, String idActivitat) {
        // He de recórrer la llista element per element amb un bucle.
        // Si trobo una inscripció que tingui exactament el mateix idUsuari i idActivitat, retorno true.
        // Si s'acaba el bucle i no l'he trobat, retorno false.
        return false; 
    }

    /**
     * Retorna l'objecte Inscripcio per poder consultar-lo o modificar-lo (ex: posar nota).
     */
    public Inscripcio getInscripcio(String idUsuari, String idActivitat) {
        // Faig un bucle per buscar la inscripció.
        // Si la trobo, retorno l'objecte llista[i] directament.
        return null; // Si recorro tota la llista i no hi és, retorno null.
    }

    /**
     * Compta quantes persones hi ha inscrites a una activitat concreta.
     * Útil per saber si l'activitat està plena.
     */
    public int comptarInscripcionsActivitat(String idActivitat) {
        // Inicialitzo un comptador a 0.
        // Recorro tota la llista d'inscripcions.
        // Cada vegada que trobi una inscripció amb aquest idActivitat, sumo 1 al comptador.
        return 0; // Al final retorno el total que he comptat.
    }

    /**
     * Retorna un array amb totes les inscripcions d'un usuari.
     * Necessari per la opció "Mostrar activitats d'un usuari".
     */
    public Inscripcio[] getInscripcionsUsuari(String idUsuari) {
        // Pas 1: Primer he de comptar quantes inscripcions té aquest usuari per saber quina mida tindrà l'array nou.
        int total = 0;
        // Aquí faig un bucle per comptar les coincidències.

        // Pas 2: Ara creo l'array resultat amb la mida 'total'.
        Inscripcio[] resultat = new Inscripcio[total];
        int j = 0; // Necessito aquest índex auxiliar per anar omplint el nou array.
        
        // Pas 3: Faig un altre bucle. Quan torni a trobar l'usuari, copio la inscripció a resultat[j] i incremento j.
        
        return resultat;
    }
    
    // --- MÈTODES DE PERSISTÈNCIA (SERIALITZACIÓ) ---

    /**
     * Guarda aquest objecte (la llista sencera) al fitxer.
     */
    public void guardarFitxer(String rutaFitxer) {
        try {
            // Primer creo un ObjectOutputStream connectat a la ruta del fitxer.
            // Després escric l'objecte actual sencer fent: oos.writeObject(this).
            // Tanco el stream i mostro un missatge per saber que s'ha guardat bé.
        } catch (Exception e) {
            System.out.println("Error guardant inscripcions: " + e.getMessage());
        }
    }

    /**
     * Carrega la llista des del fitxer i la retorna.
     * És static perquè crea una nova instància.
     */
    public static LlistaInscripcions carregarFitxer(String rutaFitxer) {
        LlistaInscripcions llistaLlegida = new LlistaInscripcions(); // Creo una llista buida per si falla la càrrega.
        
        // Primer creo un objecte File amb la ruta per comprovar si el fitxer existeix (f.exists()).
        // Si existeix:
        // 1. Obro un ObjectInputStream.
        // 2. Llegeixo l'objecte i el converteixo: llistaLlegida = (LlistaInscripcions) ois.readObject();
        // 3. Tanco el stream.
        // No m'he d'oblidar de gestionar les excepcions (IOException, ClassNotFoundException) amb un try-catch.
        
        return llistaLlegida;
    }

    @Override
    public String toString() {
        // Creo un String o un StringBuilder per anar muntant el text.
        // Recorro la llista i vaig afegint el toString() de cada inscripció seguit d'un salt de línia.
        return ""; // Retorno el text final complet.
    }
}