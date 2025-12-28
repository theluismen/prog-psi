/**
 * Autor(@s): Alexandra Núñez y Ainara Sofia Cabreras
 * Descripción: el uso principal de esta clase es tratar las actividades guardadas en un
 * txt y añadir las que se quieran (dentro de lo posible)
 */
package llistes;

import activitats.*;
import enumeracions.Collectius;
import enumeracions.DiaSetmana;
import excepcions.*;
import extras.Data;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class LlistaActivitats {        //falta crear llista
    
    /* Atributos de la Clase */
    private Activitat llista[];
    private int nElems;


    /* Constructor */
    /**
     * Constructor con el parametro para el tamaño de la lista
     * @param n tamaño total de la lista
     */
    public LlistaActivitats(int n){
        this.llista = new Activitat[n];
        nElems = 0;
    }


    /* Getters y Setters */
    /**
     * Metode que retorna el nombre d'elements actuals a la llista
     * @return el total d'elements a la llista
     */
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


    /* Métodos */
    /**
     * Metodo para añadir una actividad a la lista ordenado alfabeticamente por su nombre
     * si ya existe una actividad con el mismo nombre (ignora las mayusculas), lanzara un error
     * @param act
     * @throws ActivitatDuplicada
     */
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
     * @throws ActivitatDesconeguda
     */
    public Activitat cerca(String id)throws ActivitatDesconeguda{
        Activitat act = null;
        boolean encontrada = false;
        int i = 0;

        while(i < nElems && !encontrada){
            if(this.llista[i].getNom().equalsIgnoreCase(id)){
                act = this.llista[i];
                encontrada = true;
            }
            i++;
        }

        if (!encontrada){
            throw new ActivitatDesconeguda(id);
        }

        return act.copia();
    }

    /**
     * Metodo que comprueba si una actividad ya existe en la lista
     * @param nomAct
     * @return
     */
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
     * @throws ActivitatDesconeguda
     */
    public void eliminar(String act)throws ActivitatDesconeguda{
        int borrar = -1;
        boolean encontrado = false;

        for(int i = 0; i < nElems && !encontrado; i++){
            if(this.llista[i].getNom().equalsIgnoreCase(act)){
                borrar = i;
                encontrado = true;
            }
        }

        if(!encontrado){
            throw new ActivitatDesconeguda(act);
        }
        //Mover todos los ellementos a la izquierda
        for(int i = borrar; i < nElems -1; i++){
            this.llista[i]= this.llista[i + 1];
        }
        //Eliminar el valor de la última posición (duplicado).
        this.llista[nElems -1] = null;
        nElems--;
        reduir();
    }

    /**
     * Metodo que devuelve una nueva lista de actividades con solo las actividades de un tipo
     * @param tipus tipo de actividades de la nueva lista (periodicas, online o de un dia)
     * @return una llista amb el tipus d'activitat demanada
     * @throws ActivitatDesconeguda
     */
    public LlistaActivitats tipusLlista(String tipus)throws ActivitatDesconeguda{
        LlistaActivitats nova = new LlistaActivitats(1);

        for (int i = 0; i < this.nElems; i++){
            boolean coincideix = false;

            if (tipus.equalsIgnoreCase("periodica") && this.llista[i] instanceof ActivitatPeriodica) {
                coincideix = true;
            } else if (tipus.equalsIgnoreCase("online") && this.llista[i] instanceof ActivitatOnline) {
                coincideix = true;
            } else if (tipus.equalsIgnoreCase("un dia") && this.llista[i] instanceof ActivitatUnDia) {
                coincideix = true;
            } else{
                throw new ActivitatDesconeguda(tipus);
            }

            if (coincideix){
                try{
                    nova.afegir(this.llista[i]);
                } catch(ActivitatDuplicada e){

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
            System.out.println("ERROR INESPERAT EN CLASEAVUI");
        }
        
        return act;
    }

    /**
     * Método que lee las actividades de un fichero.csv y lo guarda en una llista.
     * @param nomFichero    Nombre del fichero a leer.
     */
    public void carregarFitxer(String nomFichero){
        
        BufferedReader archivo = null;
        String linea;   //Variable almacena cada linea leida del fichero
        int nLinea = 1; //Contador de line apara indicar el error

        try{
            //Abro el archivo y lo leo.
            archivo = new BufferedReader(new FileReader(nomFichero));
            linea = archivo.readLine();

            while (linea != null) {
            Scanner scanner = new Scanner(linea);   //Trabajamos la linea de manera individual.
            scanner.useDelimiter(";");              //Separo la linea en cada elemento del objeto.

            try{
                Activitat nuevaAct = null;

                //Posiciones comunes para todas las actividades.
                String tipoAct = scanner.next();    //Leo el tipo de actividad.
                String nom = scanner.next();        //Nombre actividad.

                //Lectura y tratamiento de collectius
                String collectiusStr = scanner.next();                                              //Lectura de string.
                String[] strCollectiusSplit = collectiusStr.split(",");                             //Separación de colectivos por comas.
                Collectius[] collectius = new Collectius[strCollectiusSplit.length];                //Creamos instancia del enum.

                for(int i = 0; i < strCollectiusSplit.length; i++){                                 //Colocar cada colectivo en array de tipo enum.

                    try{
                        collectius[i] = Collectius.valueOf(strCollectiusSplit[i].trim().toUpperCase());
                    }catch(IllegalArgumentException e){
                        throw new CollectiuDesconegut(strCollectiusSplit[i]);
                    }
                }

                //Fecha de inicio del período de inscripción.
                int iniciInscripcioDia = scanner.nextInt();         
                int iniciInscripcioMes = scanner.nextInt();         
                int iniciInscripcioAny = scanner.nextInt(); 
                Data iniciInscripcio = new Data(iniciInscripcioDia, iniciInscripcioMes, iniciInscripcioAny); 

                //Fecha de fin del período de inscripción.       
                int fiInscripcioDia = scanner.nextInt();            
                int fiInscripcioMes = scanner.nextInt();            
                int fiInscripcioAny = scanner.nextInt();    
                Data fiInscripcio = new Data(fiInscripcioDia, fiInscripcioMes, fiInscripcioAny);            

                //Tipo actividad.
                if (tipoAct.equalsIgnoreCase("Activitat periodica")) {

                    DiaSetmana diaSetmana = DiaSetmana.valueOf(scanner.next().toUpperCase());

                    double duracio = scanner.nextDouble();

                    int diaAct = scanner.nextInt();
                    int mesAct = scanner.nextInt();
                    int anyAct = scanner.nextInt();
                    int hora = scanner.nextInt();
                    int minutos = scanner.nextInt();
                    Data diaYHoraInicio = new Data(diaAct, mesAct, anyAct, hora, minutos);

                    int setmanes = scanner.nextInt();
                    int places = scanner.nextInt();
                    double preu = scanner.nextDouble();
                    String centre = scanner.next();
                    String ciutat = scanner.next();

                    //Creo activitat.
                    nuevaAct = new ActivitatPeriodica(nom, collectius, iniciInscripcio, fiInscripcio,
                                                        diaSetmana, duracio, diaYHoraInicio, setmanes, 
                                                        places, preu, centre, ciutat);
                }else if(tipoAct.equalsIgnoreCase("Activitat d'un dia")){

                    int diaAct = scanner.nextInt();
                    int mesAct = scanner.nextInt();
                    int anyAct = scanner.nextInt();
                    int hora = scanner.nextInt();
                    int minutos = scanner.nextInt();
                    Data diaYHoraInicio = new Data(diaAct, mesAct, anyAct, hora, minutos);

                    int horaDurada = scanner.nextInt();
                    int minutosDurada = scanner.nextInt();
                    int places = scanner.nextInt();
                    int preu = scanner.nextInt();
                    String ciutat = scanner.next();

                    //Creo activitat.
                    nuevaAct = new ActivitatUnDia(nom, collectius, iniciInscripcio, fiInscripcio, diaYHoraInicio, 
                                                        horaDurada, minutosDurada, places, preu, ciutat);
                        
                }else if(tipoAct.equalsIgnoreCase("Activitat Online")){   
                    int diaAct = scanner.nextInt();
                    int mesAct = scanner.nextInt();
                    int anyAct = scanner.nextInt();
                    Data diaInicio = new Data(diaAct, mesAct, anyAct);

                    int periodoVisualizacion = scanner.nextInt();
                    String enlace = scanner.next();
                    
                    //Creo activitat.
                    nuevaAct = new ActivitatOnline(nom, collectius, iniciInscripcio, fiInscripcio, 
                                                        diaInicio, periodoVisualizacion, enlace);   
                    
                }else {
                    throw new IllegalArgumentException("Tipus d'activitat deconegut");
                }

                if(nuevaAct != null){
                    this.afegir(nuevaAct);  //Añadir a la lsita el objeto.
                }
            } catch (InputMismatchException e) {
                System.out.println("Línia " + nLinea + ": Error de format numèric");
            } catch (ValorInexistent e) {
                System.out.println("Línia " + nLinea + ": Valor inexistent");
            } catch (CollectiuDesconegut e) {
                System.out.println("Línia " + nLinea + ": Col·lectiu desconegut");
            } catch (ActivitatDuplicada e) {
                System.out.println("Línia " + nLinea + ": Activitat duplicada");
            } catch (IllegalArgumentException e) {
                System.out.println("Línia " + nLinea + ": Error de format");
            } finally {
                scanner.close();
            }

            linea = archivo.readLine();
            nLinea++;
            }
        }catch (FileNotFoundException e) {
            System.out.println("No s'ha trobat el fitxer: " + nomFichero);
        } catch (IOException e) {
            System.out.println("Error llegint el fitxer");
        } finally {
            try {
                if (archivo != null) {
                    archivo.close();
                }
            } catch (IOException e) {
                System.out.println("Error tancant el fitxer");
            }
        }
    }

    /**
     * Metodo para guardar la lista en un fichero en formato CSV
     * @param fitxer nombre del fichero en el que se guardan los datos
     * @throws IOException
     */
    public void guardarLlista(String fitxer) {
        try{
            BufferedWriter f = new BufferedWriter(new FileWriter(fitxer));

            for (int i = 0; i < this.nElems; i++){
                String linea = this.llista[i].toCSV();
                f.write(linea);
                f.newLine();
            }

            f.close();
        }catch(FileNotFoundException e){
            System.out.println("La referencia del fichero es incorrecta o no existe: "+fitxer);
            System.out.println(super.toString());

        }catch(IOException e){
            System.out.println("Ha habido un error en la apertura del fichero" + super.toString());
        }
    }

    /**
     * Método que convierte el objeto en una string.
     * @return String del objeto.
     */
    @Override
    public String toString(){
        String aux = "";
        
        for (int i = 0; i < nElems; i++){
            aux += this.llista[i]+"\n\n";
        }
        return aux;
    }


    /* Métodos privados de la clase*/
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

    /**
     * Mètode que retorna un objecte i no una copia de l'activitat
     * 
     * @param n 
     * @return la llista d'activitats (no una còpia)
     * @throws ValorInexistent excepcio 
     */
    public Activitat getActivitatReal(int n) throws ValorInexistent {
        if (n < 0 || n >= nElems) {
            throw new ValorInexistent("Índex fora de rang");
        }
        return llista[n]; // Objecte real
    }

}