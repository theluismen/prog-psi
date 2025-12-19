/**
 * Autor(@s): Alexandra Núñez y Ainara Sofia Cabreras
 * Descripción: el uso principal de esta clase es tratar las actividades guardadas en un
 * txt y añadir las que se quieran (dentro de lo posible)
 */
package llistes;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import activitats.*;

public class LlistaActivitats implements Llista<Activitat>{        //falta crear llista
    private Activitat llista[];
    private int nElems;

    
    /**
     * Constructor con el parametro para el tamaño de la lista
     * @param n tamaño total de la lista
     */
    public LlistaActivitats(int n){
        this.llista = new Activitat[n];         //añadir excepcion
        nElems = 0;
    }


    /**
     * Metode que retorna el nombre d'elements actuals a la llista
     * @return el total d'elements a la llista
     */
    @Override
    public int getNumElements(){
        return this.nElems;
    }

    
    /**
     * Metodo para añadir una actividad a la lista ordenado alfabeticamente por su nombre
     * si ya existe una actividad con el mismo nombre (ignora las mayusculas), lanzara un error
     * @param act
     */
    @Override
    public void afegir(Activitat act) {
        if (this.existeix(act.getNom())){
            //lanzar excepcion
        }
        
        if (this.llista.length > nElems){
            this.amplia();
        }

        int pos = this.nElems - 1;
        while ((pos >= 0) && (this.llista[pos].getNom().compareToIgnoreCase(act.getNom()) > 0)){
            this.llista[pos+1] = this.llista[pos];
            pos--;
        }
        this.llista[pos+1] = act;
        this.nElems++;
    }


    /**
     * Metodo que comprueba si una actividad ya existe en la lista
     * @param nomAct
     * @return
     */
    @Override
    public boolean existeix(String nomAct){
        boolean trobat = false;
        int i = 0;

        while (!trobat && (i < nElems)){
            if (this.llista[i].getNom().equalsIgnoreCase(nomAct)){
                trobat = true;
            }
            i++;
        }
        
        return trobat;
    }


    /**
     * Metodo para guardar la lista en un fichero en formato CSV
     * @param fitxer nombre del fichero en el que se guardan los datos
     * @throws IOException
     */
    public void guardarLlista(String fitxer) throws IOException{       //tratar excepciones (no estan bien tratadas, es solo temporal para probar que funciona)
        BufferedWriter f = new BufferedWriter(new FileWriter(fitxer));
        
        for (int i = 0; i < this.nElems; i++){
            String linea = this.llista[i].toCSV();
            f.write(linea);
            f.newLine();
        }

        f.close();
    }


//toString
    public String toString(){
        String aux = "";
        
        for (int i = 0; i < nElems; i++){
            aux += this.llista[i]+"\n";
        }

        return aux;
    }



//Metodos privados de la clase
    /**
     * Metodo que amplia el tamaño del array de la lista
     */
    private void amplia(){
        Activitat aux[] = new Activitat[this.llista.length+1];
        
        for (int i = 0; i < this.nElems; i++){
            aux[i] = this.llista[i];
        }
        this.llista = aux;
    }
    

    
    /*public LlistaActivitats llegirFitxer(String nomFitxer){
        
    }*/

}
