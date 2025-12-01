//cambiar todos los print por excepciones
//es posible que se cambien los localDate por la clase propia Data en un futuro


package activitats;


import java.time.LocalDate;
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




    public ActivitatPeriodica (String nom, String[] collectius, LocalDate dataIniInscripcio, LocalDate dataFiInscripcio,
                                DiaSetmana dia, double durada, Data diaYHoraInicio, int setmanes,
                                int places, double preu, String centre, String ciutat){
       
        super(nom, collectius, dataIniInscripcio, dataFiInscripcio);
        this.dia = dia;
        this.setmanes = setmanes;
        this.centre = centre;
        this.ciutat = ciutat;
        this.preu = preu;
        this.dataHoraIni = diaYHoraInicio;

        if (places > 0)
            this.places = places;
        else{
            throw new exception; //crear excepcion correspondiente
        }

        if (durada > 0)
            this.durada = durada;
        else{
            throw new exception; //crear excepcion correspondiente
        }
       
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


//getters
    public String getDia()  {   return this.dia.toString();     }


    public double getDurada(){      return this.durada;     }


    public int getPlaces(){     return this.places;     }


    public Data getDataFinal(){    return this.dataHoraIni.dataPlusDies(this.setmanes*7);      }


    public int getSetmanes(){     return this.setmanes;    }
   

    public double getPreu(){    return this.preu;    }


    public String getCentre(){      return this.centre;     }


    public String getCiutat(){      return this.ciutat;     }


    public Data getDataHoraIni(){       return this.dataHoraIni;      }




    public String getHorari(){
        String aux = "El horario es: "+this.dia+" de"+this.dataHoraIni.getHora()+":"+this.dataHoraIni.getMinutos()+
        " a "+;
       
        Data horaFin = this.dataHoraIni.copia();
        

        double horaFin = this.horaIni + this.durada;
        hora = (int)(horaFin);
        minutos = (int)((horaFin - hora) * 60);
        aux = aux + hora+":"+minutos;


        return aux;
    }






//metodos propios
    /**
     * Metodo que comprueba si hay clase en la fecha indicada
     *
     * @param hoy
     * @return true si hay clase en esa fecha, false en caso contrario
     */
    public boolean estaActiva(LocalDate hoy){
        int totalDiesDurada = this.setmanes * 7;
        LocalDate diaComparar = this.dataIni.plusDays(totalDiesDurada);
       
        while (totalDiesDurada > 0){
            if (hoy.isEqual(diaComparar))
            {
                return true;
            }
            totalDiesDurada -= 7;
            diaComparar = this.dataIni.plusDays(totalDiesDurada);
        }
        return false;
    }






//metodos del padre
    /**
     * Metodo que comprueba si una actividad esta activa en la fecha indicada
     *
     * @param hoy
     * @return false si no esta activa, true en caso contrario
     */
    public boolean activesAvui(LocalDate hoy){
        LocalDate dataFi = getDataFinal();
        if (hoy.isBefore(dataFi) && hoy.isAfter(this.dataIni)){
            return true;
        }
        else if (hoy.isEqual(dataFi) || hoy.isEqual(this.dataIni)){
            return true;
        }
        return false;
    }


    /**
     * Metode que retorna el tipus d'activitat
     *
     * @return una cadena que indica el tipus d'activitat
     */
    public String tipusActivitat(){
        return "Activitat periodica";
    }






//metodos privados




}