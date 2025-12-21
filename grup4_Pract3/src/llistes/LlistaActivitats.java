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
import extras.*;
import excepcions.ActivitatDuplicada;
import excepcions.ValorInexistent;

public class LlistaActivitats implements Llista<Activitat>{        //falta crear llista
    private Activitat llista[];
    private int nElems;

    
    /**
     * Constructor con el parametro para el tamaño de la lista
     * @param n tamaño total de la lista
     */
    public LlistaActivitats(int n){
        this.llista = new Activitat[n];
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
     * Metodo que devuelve la actividad en la posicion n
     * @param n
     * @return actividad
     * @throws ValorInexistent si se pide un valor fuera de la tabla
     */
    public Activitat getActivitatIesima(int n)throws ValorInexistent{
        if (n >= this.nElems){
            throw new ValorInexistent("Valor fora de la taula");
        }
        return this.llista[n].copia();  
    }

    
    /**
     * Metodo para añadir una actividad a la lista ordenado alfabeticamente por su nombre
     * si ya existe una actividad con el mismo nombre (ignora las mayusculas), lanzara un error
     * @param act
     * @throws ActivitatDuplicada
     */
    @Override
    public void afegir(Activitat act)throws ActivitatDuplicada {
        if (this.existeix(act.getNom())){
            throw new ActivitatDuplicada(act.getNom());
        }
        
        if (this.llista.length >= nElems){
            this.amplia();
        }

        int pos = this.nElems - 1;
        while ((pos >= 0) && (this.llista[pos].getNom().compareToIgnoreCase(act.getNom()) > 0)){
            this.llista[pos+1] = this.llista[pos];
            pos--;
        }
        this.llista[pos+1] = act.copia();
        this.nElems++;
    }

    
    /**
     * Método que busca una actividad por el id de esta.
     * @param id Nombre de la actividad
     * @return objeto de esa actividad.
     */
    @Override
    public Activitat cerca(String id){
        Activitat act = null;
        boolean encontrada = false;

        for(int i = 0; i < nElems && !encontrada; i++){
            if(this.llista[i].getNom().equalsIgnoreCase(id)){
                act = this.llista[i];
                encontrada = true;
            }
        }
        return act.copia();
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
     * Método que elimina una actividad de al lista por su nombre.
     * @param act   Nombre de la actividad a eliminar.
     */
    public void eliminar(String act){
        int borrar = -1;
        boolean encontrado = false;

        for(int i = 0; i < nElems && !encontrado; i++){
            if(this.llista[i].getNom().equalsIgnoreCase(act)){
                borrar = i;
                encontrado = true;
            }
        }
        if(encontrado){
            //Mover todos los ellementos a la izquierda
            for(int i = borrar; i < nElems -1; i++){
                this.llista[i]= this.llista[i + 1];
            }
            //Eliminar el valor de la última posición (duplicado).
            this.llista[nElems -1] = null;
            nElems--;
            reduir();
        }
    }


    /**
     * Metodo que devuelve una nueva lista de actividades con solo las actividades de un tipo
     * @param tipus tipo de actividades de la nueva lista (periodicas, online o de un dia)
     * @return
     */
    public LlistaActivitats tipusLlista(String tipus){
        LlistaActivitats nova = new LlistaActivitats(1);

        for (int i = 0; i < this.nElems; i++){
            if (tipus.equalsIgnoreCase(this.llista[i].tipusActivitat())){
                try{
                    nova.afegir(this.llista[i]);
                }catch(ActivitatDuplicada e){
                    //nunca dará este error porque la lista de la que se copian las actividades ya lo ha verificado
                }
            }
        }

        return nova;
    }


    /**
     * Metodo que devuelve una nueva lista con unicamente las actividades que tienen clase hoy
     * @param data
     * @return
     */
    public LlistaActivitats claseAvui(Data data){
        LlistaActivitats act = new LlistaActivitats(1);

        try{
            for (int i = 0; i < this.nElems; i++){
                act.afegir(this.llista[i]);
            }
        }catch(ActivitatDuplicada e){
            //no puede dar nunca este error porque la lista de la que esta copiando la informacion ya lo ha comprobado
        }
        
        return act;
    }


    public void fromCSV(String nomFichero){
        
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
    

    /**
     * Método que reduce en uno el tamaño físico del array.
     */
    private void reduir(){
        Activitat aux[] = new Activitat[this.llista.length-1];

        for(int i = 0; i < this.nElems; i++){
            aux[i] = this.llista[i];
        }
        this.llista = aux;
    }
    

}
