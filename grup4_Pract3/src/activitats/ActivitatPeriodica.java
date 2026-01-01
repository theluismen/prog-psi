/**
 * Autor(@s): Alexandra Núñez
 * Descripción: esta clase sirve para crear las instancias de actividad periodica
 * contiene un atributo para guardar el dia de la semana que se hace,
 * la fecha de inicio y su hora (ambos se guardan en el mismo atributo, la fecha solo es la de inicio pero la hora es la misma cada semana),
 * la duración de la actividad en formato decimal (una hora y 30 min serian 1,5 horas),
 * la cantidad de semanas que se hara la actividad, las plazas que tiene, el precio y el lugar donde se hará (centro y ciudad)
 */
//falta añadir todas las excepciones y cambiar los print por excepciones

package activitats;


import enumeracions.*;
import excepcions.*;
import extras.*;


public class ActivitatPeriodica extends Activitat {
    private DiaSetmana dia;
    private double durada;
    private Data dataHoraIni;
    private int setmanes;
    private int places;
    private double preu;
    private String centre;
    private String ciutat;


    /**
     * Metodo constructor. Devolvera errores si un valor no es posible
     * @param nom
     * @param collectius
     * @param dataIniInscripcio
     * @param dataFiInscripcio
     * @param dia dia de la setmana (dilluns, dimarts, etc)
     * @param durada de la activitat (1,5 h = una hora i mitja)
     * @param diaYHoraInicio el primer día en que se da la actividad y la hora a la que se dará cada semana
     * @param setmanes cantidad de semanas en que se repetirá la actividad
     * @param places
     * @param preu
     * @param centre lugar en que se lleva a cabo la actividad
     * @param ciutat
     * @throws ValorInexistent
     */
    public ActivitatPeriodica (String nom, Collectius[] collectiu, Data dataIniInscripcio, Data dataFiInscripcio,
                                DiaSetmana dia, double durada, Data diaYHoraInicio, int setmanes,
                                int places, double preu, String centre, String ciutat)throws ValorInexistent{
       
        super(nom, collectiu, dataIniInscripcio, dataFiInscripcio);
        this.dia = dia;
        this.centre = centre;
        this.ciutat = ciutat;
        this.dataHoraIni = diaYHoraInicio;
       
        //Una activitat periodica ha de durar mínim 1 setmana
        if (setmanes > 0){
            this.setmanes = setmanes;
        }else{
            throw new ValorInexistent("Setmanes d'activitat periodica");
        }
       
        //el preu de l'activitat no pot ser negatiu
        if (preu >= 0){
            this.preu = preu;
        }else{
            throw new ValorInexistent("Preu negatiu");
        }
       
        //Una actividad no se puede dar si no hay plazas y un valor negativo es imposible
        if (places > 0){
            this.places = places;
        }else{
            throw new ValorInexistent("Places");
        }

        //la duración de una actividad no puede ser 0 o inferior
        if (durada > 0)
            this.durada = durada;
        else{
            throw new ValorInexistent("Durada");
        }
       
    }


//getters y setters
    /**
     * getter del dia de la semana de la actividad
     * @return dia de la semana
     */
    public String getDia(){  
        return this.dia.toString();    
    }


    /**
     * setter para cambiar el dia de la actividad
     * @param nouDia
     */
    public void setDia(DiaSetmana nouDia){  
        this.dia = nouDia;  
    }


    /**
     * getter de la duracion de la actividad (horas)
     * @return duracion
     */
    public double getDurada(){      
        return this.durada;    
    }


    /**
     * setter para cambiar la duracion de la actividad (horas)
     * @param novaDurada
     * @throws ValorInexistent
     */
    public void setDurada(double novaDurada)throws ValorInexistent{  
        //la duración de una actividad no puede ser 0 o inferior
        if (novaDurada > 0)
            this.durada = novaDurada;
        else{
            throw new ValorInexistent("Durada");
        }  
    }


    /**
     * getter de la cantidad de plazas de la actividad
     * @return plazas
     */
    @Override
    public int getPlacesMaximes(){    
        return this.places;    
    }


    /**
     * setter para cambiar las plazas de una actividad
     * @param nouPlaces
     * @throws ValorInexistent
     */
    public void setPlaces(int nouPlaces)throws ValorInexistent{  
        //Una actividad no se puede dar si no hay plazas y un valor negativo es imposible
        if (nouPlaces > 0){
            this.places = nouPlaces;
        }else{
            throw new ValorInexistent("Places");
        }    
    }


    /**
     * getter para saber la fecha del final de la actividad
     * @return fecha final
     */
    @Override
    public Data getDataFinal(){    
        return this.dataHoraIni.dataPlusDies(this.setmanes*7);      
    }


    /**
     * getter de la cantidad de semanas que dura la actividad
     * @return semanas
     */
    public int getSetmanes(){    
        return this.setmanes;    
    }


    /**
     * setter para cambiar la cantidad de semanas que dura la actividad
     * @param nouSetmanes
     * @throws ValorInexistent
     */
    public void setSetmanes(int nouSetmanes)throws ValorInexistent{  
        //Una activitat periodica ha de durar mínim 1 setmana
        if (nouSetmanes > 0){
            this.setmanes = nouSetmanes;
        }else{
            throw new ValorInexistent("Setmanes d'activitat periodica");
        }    
    }
   
    /**
     * getter del precio de la actividad
     * @return precio
     */
    public double getPreu(){    
        return this.preu;    
    }


    /**
     * setter para cambiar el precio de la actividad
     * @param nouPreu
     * @throws ValorInexistent
     */
    public void setPreu(double nouPreu)throws ValorInexistent{    
        //el preu de l'activitat no pot ser negatiu
        if (nouPreu >= 0){
            this.preu = nouPreu;
        }else{
            throw new ValorInexistent("Preu negatiu");
        }    
    }


    /**
     * getter del centro donde se imparte la actividad
     * @return centro
     */
    public String getCentre(){      
        return this.centre;    
    }


    /**
     * setter para cambiar el centro donde se imparte la actividad
     * @param nouCentre
     */
    public void setCentre(String nouCentre){    
        this.centre = nouCentre;    
    }


    /**
     * getter de la ciudad donde se imparte la actividad
     * @return ciudad
     */
    public String getCiutat(){      
        return this.ciutat;    
    }


    /**
     * setter para cambiar la ciudad
     * @param novaCiutat
     */
    public void setCiutat(String novaCiutat){  
        this.ciutat = novaCiutat;  
    }


    /**
     * setter de la fecha y la hora de inicio de la actividad
     * la hora sera la misma cada semana
     * la fecha se refiere al primer dia de imparticion de la actividad
     * @return fecha y hora de inicio
     */
    public Data getDataHoraIni(){      
        return this.dataHoraIni;      
    }


    /**
     * setter para cambiar la fecha de inicio de la actividad, la hora mantiene la anterior
     * @param nouDataHoraIni
     */
    public void setDataIni(Data nouDataHoraIni) throws ValorInexistent {    
        nouDataHoraIni.setHora(this.dataHoraIni.getHora(), this.dataHoraIni.getMinuts());
        this.dataHoraIni = nouDataHoraIni;
        
    }


    /**
     * setter para cambiar la hora a la que se hace la actividad
     * @param h hora
     * @param m minutos
     * @throws ValorInexisten si la hora no existe
     */
    public void setHora(int h, int m) throws ValorInexistent{
        this.dataHoraIni.setHora(h, m);
    }




    /**
     * Metodo para saber el horario semanal de la actividad (dia de la semana y hora)
     * Para devolver la información, lo hace con un String
     * @return String con el horario
     */
    public String getHorari(){
        String aux = "Horari: "+this.dia+" de "+this.dataHoraIni.getHora()+":"+this.dataHoraIni.getMinuts()+
        " a ";

        int hora = (int)(this.durada);
        int minutos = (int)((this.durada - hora) * 60) + this.dataHoraIni.getMinuts();
        Data fi = this.dataHoraIni.sumarTemps(hora, minutos);
        aux = aux + fi.getHora()+":"+fi.getMinuts()+" pm";

        return aux;
    }




//Metodo copia
    /**
     * Metodo que devuelve un duplicado de la instancia
     * @return duplicado
     */
    @Override
    public ActivitatPeriodica copia(){
        try{
            return new ActivitatPeriodica(super.nom, super.collectiu, super.dataIniciInscripcio, super.dataFiInscripcio,
            this.dia, this.durada, this.dataHoraIni, this.setmanes, this.places, this.preu, this.centre, this.ciutat);
        }catch(ValorInexistent e){
            System.out.println("ERROR INESPERAT EN COPIA");     //mai hauria de donar aquests errors perque son una copia d'un valor ja comprobat
        }
        
        return null;
    }




//toString
    @Override
    public String toString(){
        //aprovechando el toString del padre
        String aux = "\n---ACTIVITAT PERIODICA---\n"+
                    super.toString()+
                    "Centre: "+this.centre+
                    "\nCiutat: "+this.ciutat+
                    "\nPreu: "+this.preu+
                    "\nPlaces: "+this.places+
                    "\nTotal de setmanes: "+this.setmanes+
                    "\n"+getHorari();
        return aux;
    }




//toCSV
    /**
     * Metodo que transforma todos los atributos de la clase en un String en formato CSV de la siguiente forma:
     * tipusActivitat;nom;colectiu;DiaIniInsc;MesIniInsc;AnyIniInsc;DiaFiInsc;MesFiInsc;AnyFiInsc;Dia;Durada;DiaIni;MesIni;AnyIni;Hora;Minut;Setmanes;Places;Preu;Centre;Ciutat
     * siendo:
     * tipusActivitat = Activitat Periodica
     * Nom = nombre de la actividad
     * Colectius = la tabla se transcribe separando cada colectivo por comas (...;colectiuA,colectiuB,colectiuC;...)
     * DiaIniInsc, MesIniInsc, AnyIniInsc = fecha de inicio del plazo de inscripcion (dia, mes y año)
     * DiaFiInsc, MesFiInsc, AnyFiInsc = fecha de fin del plazo de iscripcion (dia, mes y año)
     * Dia = dia de la semana en que se realiza la actividad (lunes, martes, miercoles, etc)
     * Durada = duración de la actividad (2.5 = 2 horas y 30 minutos)
     * DiaIni, MesIni, AnyIni = primer dia en que se lleva a cabo la actividad (fecha inicial de la actividad)
     * Hora, Minut = horario de la actividad (hora de inicio, cada semana la misma)
     * Setmanes = cantidad de semanas en que se lleva a cabo la actividad
     * Places = cantidad de plazas de la actividad
     * Preu = precio de la actividad
     * Centre = lugar en el que se llevara a cabo la actividad
     * Ciutat = ciudad donde se hara la actividad
     * @return
     */
    @Override
    public String toCSV(){
        String aux = "Activitat periodica;" + super.nom + ";";

        if (super.collectiu != null) {
            for (int i = 0; i < super.collectiu.length; i++){
                if(i != super.collectiu.length - 1){
                    aux += super.collectiu[i].name() + ",";
                }else{
                    aux += super.collectiu[i].name() + ";";
                }
                
            }
        }else {
            aux += ";"; // si no hi ha collectius
        }

        aux += super.dataIniciInscripcio.getDia()+";"+
            super.dataIniciInscripcio.getMes()+";"+
            super.dataIniciInscripcio.getAny()+";"+
            super.dataFiInscripcio.getDia()+";"+
            super.dataFiInscripcio.getMes()+";"+
            super.dataFiInscripcio.getAny()+";"+
            this.dia+";"+
            this.durada+";"+
            this.dataHoraIni.getDia()+";"+
            this.dataHoraIni.getMes()+";"+
            this.dataHoraIni.getAny()+";"+
            this.dataHoraIni.getHora()+";"+
            this.dataHoraIni.getMinuts()+";"+
            this.setmanes+";"+
            this.places+";"+
            this.preu+";"+
            this.centre+";"+
            this.ciutat;

        return aux;
    }


//metodos propios
    /**
     * Metodo que comprueba si hay clase en la fecha indicada
     *
     * @param hoy
     * @return true si hay clase en esa fecha, false en caso contrario
     */
    @Override
    public boolean avuiHiHaClase(Data hoy){
        boolean res = false;
        int totalDiesDurada = this.setmanes * 7;
        Data diaComparar = this.dataHoraIni.dataPlusDies(totalDiesDurada);
       
        while (totalDiesDurada > 0){
            if (hoy.esIgual(diaComparar))
            {
                res = true;
            }
            totalDiesDurada -= 7;
            diaComparar = this.dataHoraIni.dataPlusDies(totalDiesDurada);
        }
        return res;
    }


//metodos del padre
    /**
     * Metodo que comprueba si una actividad esta activa en la fecha indicada
     *
     * @param hoy
     * @return false si no esta activa, true en caso contrario
     */
    @Override
     public boolean estaActiva(Data hoy){
        Data dataFi = getDataFinal();
   
        return hoy.esDataInferiorOigual(dataFi) && dataHoraIni.esDataInferiorOigual(hoy);
    }


}