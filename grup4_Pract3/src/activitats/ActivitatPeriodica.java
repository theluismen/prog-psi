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


//getters y setters
    public String getDia(){   return this.dia.toString();     }
    public void setDia(DiaSetmana nouDia){  this.dia = nouDia;  }


    public double getDurada(){      return this.durada;     }
    public void setDurada(double novaDurada){   this.durada = novaDurada;   }   //controlar excepciones


    public int getPlaces(){     return this.places;     }
    public void setPlaces(int nouPlaces){   this.places = nouPlaces;    }   //controlar excepciones


    public Data getDataFinal(){    return this.dataHoraIni.dataPlusDies(this.setmanes*7);      }


    public int getSetmanes(){     return this.setmanes;    }
    public void setSetmanes(int nouSetmanes){   this.setmanes = nouSetmanes;    }   //controlar excepciones
   

    public double getPreu(){    return this.preu;    }
    public void setPreu(double nouPreu){    this.preu = nouPreu;    }   //controlar excepciones


    public String getCentre(){      return this.centre;     }
    public void setCentre(String nouCentre){    this.centre = nouCentre;    }


    public String getCiutat(){      return this.ciutat;     }
    public void setCiutat(String novaCiutat){   this.ciutat = novaCiutat;   }


    public Data getDataHoraIni(){       return this.dataHoraIni;      }
    public void setDataHoraIni(Data nouDataHoraIni){    this.dataHoraIni = nouDataHoraIni;    }


    public String getHorari(){
        String aux = "El horario es: "+this.dia+" de"+this.dataHoraIni.getHora()+":"+this.dataHoraIni.getMinutos()+
        " a ";

        int hora = (int)(this.durada);
        int minutos = (int)((this.durada - hora) * 60);
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