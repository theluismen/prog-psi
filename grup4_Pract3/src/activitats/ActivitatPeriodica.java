//Alexandra Núñez
//falta añadir todas las excepciones y cambiar los print por excepciones

package activitats;

import enumeraciones.*;
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
     * @param dia
     * @param durada
     * @param diaYHoraInicio
     * @param setmanes
     * @param places
     * @param preu
     * @param centre
     * @param ciutat
     */
    public ActivitatPeriodica (String nom, String[] collectius, Data dataIniInscripcio, Data dataFiInscripcio,
                                DiaSetmana dia, double durada, Data diaYHoraInicio, int setmanes,
                                int places, double preu, String centre, String ciutat){
       
        super(nom, collectius, dataIniInscripcio, dataFiInscripcio);
        this.dia = dia;
        this.centre = centre;
        this.ciutat = ciutat;
        this.dataHoraIni = diaYHoraInicio;
        
        this.setmanes = setmanes;       //controlar excepciones

        this.preu = preu;       //controlar excepciones

        if (places > 0)
            this.places = places;
        else{
            //crear excepcion correspondiente
        }

        if (durada > 0)
            this.durada = durada;
        else{
            //crear excepcion correspondiente
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
     */
    public void setDurada(double novaDurada){   
        this.durada = novaDurada;   
    }   //controlar excepciones

    /**
     * getter de la cantidad de plazas de la actividad
     * @return plazas
     */
    public int getPlaces(){     
        return this.places;     
    }

    /**
     * setter para cambiar las plazas de una actividad
     * @param nouPlaces
     */
    public void setPlaces(int nouPlaces){   
        this.places = nouPlaces;    
    }   //controlar excepciones

    /**
     * getter para saber la fecha del final de la actividad
     * @return fecha final
     */
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
     */
    public void setSetmanes(int nouSetmanes){   
        this.setmanes = nouSetmanes;    
    }   //controlar excepciones
   
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
     */
    public void setPreu(double nouPreu){    
        this.preu = nouPreu;    
    }   //controlar excepciones

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
     * setter para cambiar la fecha de inicio de la actividad o la hora a la que empieza
     * @param nouDataHoraIni
     */
    public void setDataHoraIni(Data nouDataHoraIni){    
        this.dataHoraIni = nouDataHoraIni;    
    }


    public String getHorari(){
        String aux = "El horario es: "+this.dia+" de"+this.dataHoraIni.getHora()+":"+this.dataHoraIni.getMinutos()+
        " a ";

        int hora = (int)(this.durada);
        int minutos = (int)((this.durada - hora) * 60);
        aux = aux + hora+":"+minutos;

        return aux;
    }


//Metodo copia
    /**
     * Metodo que devuelve un duplicado de la instancia
     * @return duplicado
     */
    public ActivitatPeriodica copia(){
        return new ActivitatPeriodica(super.nom, super.collectius, super.dataIniciInscripcio, super.dataFiInscripcio, 
        this.dia, this.durada, this.dataHoraIni, this.setmanes, this.places, this.preu, this.centre, this.ciutat);
    }


//toString
    public String toString(){
        //aprovechando el toString del padre
        String aux = super.toString()+
                    "\nCentre: "+this.centre+
                    "\nCiutat: "+this.ciutat+
                    "\nPreu: "+this.preu+
                    "\nPlaçes: "+this.places+
                    "\nTotal de setmanes: "+this.setmanes+
                    "\n"+getHorari();
        return aux;
    }


//toCSV
    /**
     * Metodo que transforma todos los atributos de la clase en un String en formato CSV de la siguiente forma:
     * nom;colectiu;DiaIniInsc;MesIniInsc;AnyIniInsc;DiaFiInsc;MesFiInsc;AnyFiInsc;Dia;Durada;DiaIni;MesIni;AnyIni;Hora;Minut;Setmanes;Places;Preu;Centre;Ciutat
     * siendo:
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
    public String toCSV(){
        String aux = super.getNom()+";";

        for (int i = 0; i < (super.collectius.length - 1); i++){    //-1 para evitar que al poner el ultimo colectivo quede una coma al final
            aux += super.collectius[i]+",";
        }
        aux += super.collectius[super.collectius.length-1]+";"+
        super.dataIniciInscripcio.getDia()+";"+
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
        this.dataHoraIni.getMinutos()+";"+
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


    /**
     * Metode que retorna el tipus d'activitat
     *
     * @return una cadena que indica el tipus d'activitat
     */
    @Override
     public String tipusActivitat(){
        return "Activitat periodica";
    }



}