/**
 * Autor: Ikram Hallouz
 * Descripción: Clase que representa les Inscripcions dels usuaris a les activitats.
 */

package inscripcions;

import java.io.Serializable;

/**
 * Classe que representa la inscripció d'un usuari a una activitat.
 * Implementa Serializable per poder guardar-se en fitxers binaris (.dat).
 */
public class Inscripcio implements Serializable {

    private String idUsuari;      // Guardem l'àlies de l'usuari (Clau forana)
    private String idActivitat;   // Guardem el nom de l'activitat (Clau forana)
    private Integer valoracio;    // Nota de 0 a 10. Fem servir Integer (objecte) per permetre null

    /**
     * Constructor
     * @param idUsuari Àlies de l'usuari que s'inscriu
     * @param idActivitat Nom de l'activitat a la qual s'inscriu
     */
    public Inscripcio(String idUsuari, String idActivitat) {
        this.idUsuari = idUsuari;
        this.idActivitat = idActivitat;
        this.valoracio = null; // Inicialment no està valorada (null indica "pendent")
    }

    // --- Getters i Setters ---

    public String getIdUsuari() {
        return idUsuari;
    }

    public String getIdActivitat() {
        return idActivitat;
    }

    /**
     * Retorna la valoració.
     * @return nota (0-10) o null si encara no s'ha valorat.
     */
    public Integer getValoracio() {
        return valoracio;
    }

    /**
     * Assigna una valoració a l'activitat un cop finalitzada.
     * @param nota Valor entre 0 i 10
     * @throws IllegalArgumentException si la nota no és vàlida
     */
    public void setValoracio(int nota) {
        if (nota < 0 || nota > 10) {
            throw new IllegalArgumentException("La valoració ha de ser entre 0 i 10.");
        }
        this.valoracio = nota;
    }

    /**
     * Comprova si l'activitat ja ha estat valorada.
     * @return true si té nota, false si és null.
     */
    public boolean esValorada() {
        return valoracio != null;
    }

    /**
     * Crea una còpia de la inscripció (Deep Copy).
     * @return Una nova instància amb les mateixes dades.
     */
    public Inscripcio copia() {
        Inscripcio nova = new Inscripcio(this.idUsuari, this.idActivitat);
        // Si té valoració, la copiem també. Si és null, es queda null al constructor.
        if (this.valoracio != null) {
            nova.setValoracio(this.valoracio);
        }
        return nova;
    }

    @Override
    public String toString() {
        String valText = (valoracio == null) ? "Pendent de valoració" : valoracio.toString();
        return "Inscripció [Usuari: " + idUsuari + " | Activitat: " + idActivitat + " | Nota: " + valText + "]";
    }
}
