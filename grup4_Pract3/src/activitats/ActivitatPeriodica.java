package activitats;

import java.time.LocalDate;

public class ActivitatPeriodica extends Activitat {
    private DiaSetmana dia;
    private double horaIni;
    private double durada;
    private LocalDate dataIni;
    private int setmanes;
    private int places;
    private double preu;
    private String centre;
    private String ciutat;


    private static String[] mesos31 = {"Enero", "Marzo", "Mayo", "Julio", "Agosto", "Octubre", "Diciembre"};
    private static String[] mesos30 = {"Abril", "Junio", "Setiembre", "Noviembre"};

    public enum DiaSetmana{
        LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO, DOMINGO
    }


    public ActivitatPeriodica (DiaSetmana dia, double horaIni, double durada, LocalDate dataIni, LocalDate avui,
                                int setmanes, int places, double preu, String centre, String ciutat){
        
        this.dia = dia;
        this.setmanes = setmanes;
        this.centre = centre;
        this.ciutat = ciutat;
        this.preu = preu;

        this.places = 0;
        if (places > 0)
            this.places = places;
        else System.out.println("Las plazas no pueden ser 0, usa otro valor");  
        
        if (horaExisteix(horaIni))
            this.horaIni = horaIni;
        else this.horaIni = 0;

        if (durada > 0)
            this.durada = durada;
        else{
            this.durada = 0;
            System.out.println("La duración de la actividad no puede ser de "+durada+", cambialo");
        }

        this.dataIni = dataIni;
        dataExisteix(dataIni, avui);
        
    }


//toString
    public String toString(){
        String aux = "Actividad: "+/*nom*/+"\nCentro: "+this.centre+"\nCiudad: "+this.ciutat+
                    "\nPrecio: "+this.preu+"\nPlazas totales: "+this.places+"\nTotal de semanas: "+
                    this.setmanes+"\n"+getHorari();
        return aux;
    }

//getters
    public String getDia(){
        return this.dia.toString();
    }

    public double getHoraIni(){
        return this.horaIni;
    }

    public double getDurada(){
        return this.durada;
    }

    public String getHorari(){
        String aux = "El horario es los "+this.dia+" de";
        
        int hora = (int)this.horaIni;
        int minutos = (int)((this.horaIni - hora) * 60);
        aux = aux+" "+hora+":"+minutos+" a ";

        double horaFin = this.horaIni + this.durada;
        hora = (int)(horaFin);
        minutos = (int)((horaFin - hora) * 60);
        aux = aux + hora+":"+minutos;
    }

    public int getPlaces(){
        return this.places;
    }

    public LocalDate getDataFinal(){
        return this.dataIni.plusDays(this.setmanes*7);
    }



//metodos para usar en el main
    public boolean avuiHiHaClase(LocalDate hoy){
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






//metodos privados
    private boolean horaExisteix (double hora){
        if ((hora >= 24) || (hora < 0)){
            System.out.println("Esta hora no existe, usa una distinta");
            return false;
        }
        return true;
    }

    private boolean dataExisteix (LocalDate data, LocalDate fechaActual){
        if (fechaActual.isBefore(data))
            return true;
        System.out.println("La fecha "+data+" es anterior a la actual, modificalo si se trata de un error");
        return false;
    }
}
