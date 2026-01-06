//faltara probar las excepciones una vez añadidas

package tests;
import activitats.*;
import extras.*;
import enumeracions.*;

public class UsaActivitatPeriodica {

    public static void main(String[] args) {
            // --- Datos base reutilizables ---
        try{
            Data dataIni = new Data(10, 1, 2025, 10, 0);
            Data dataIniIns = new Data(1, 1, 2025);
            Data dataFiIns = new Data(20, 1, 2025);

            testConstructor(dataIniIns, dataFiIns, dataIni);

            ActivitatPeriodica activitat = new ActivitatPeriodica("ioga", new Collectius[]{Collectius.PDI, Collectius.PTGAS}, dataIniIns, dataFiIns,
                                        DiaSetmana.DILLUNS, 1.5, dataIni, 8, 20, 50.0,
                                        "Centre Blau", "Barcelona");
            testHorariYDataFinal(activitat);
            testAvuiHiHaClasse(activitat);
            testEstaActiva(activitat);
            testToCSV(activitat);
        }catch (Exception e){
            System.out.println("error desconegut");
        }

    }

    private static void testConstructor(Data dataIniIns, Data dataFiIns, Data dataIni) throws Exception{
        System.out.println("Prueba del constructor");
        ActivitatPeriodica ap = new ActivitatPeriodica("Ioga", new Collectius[]{Collectius.PDI}, dataIniIns, dataFiIns,
                                DiaSetmana.DILLUNS, 1.5, dataIni, 8, 20, 50.0,
                                "Centre Blau", "Barcelona");

        System.out.println(ap);

    }

    private static void testHorariYDataFinal(ActivitatPeriodica act){
        System.out.println("\n\nPrueba del getHorari");
        System.out.println(act.getHorari());

        System.out.println("\n\nPrueba del getDataFinal");
        Data dataFi = act.getDataFinal();
        System.out.println("La data inicial es: "+act.getDataHoraIni()+"\nLa data final es: "+dataFi+"\nLa diferencia es de "+act.getSetmanes()+" setmanes");

    }

    private static void testAvuiHiHaClasse(ActivitatPeriodica act) {
        System.out.println("\n\nTest para probar si un dia hay clase");
        // Día exacto de una sesión
        System.out.println("\nCaso que si que hay clase");
        Data diaClase = act.getDataHoraIni().dataPlusDies(7); // una semana después
        System.out.println(act.avuiHiHaClase(diaClase));

        // Día sin clase
        System.out.println("\nCaso que no hay clase");
        Data diaSinClase = act.getDataHoraIni().dataPlusDies(1);  //solo hay clase una vez a la semana en un horario concreto, al dia siguiente del primero no puede haber
        System.out.println(act.avuiHiHaClase(diaSinClase));
    }

    private static void testEstaActiva(ActivitatPeriodica act) throws Exception{
        System.out.println("\n\nTest para probar si una actividad esta en periodo de activad un dia indicado");
        System.out.println("\nCaso que si esta activa");
        Data dentroPeriodo = new Data(20, 1, 2025);
        System.out.println(act.estaActiva(dentroPeriodo));

        System.out.println("\nCaso que esta antes del periodo");
        Data antesPeriodo = new Data(5, 1, 2025);
        System.out.println(act.estaActiva(antesPeriodo));

        System.out.println("\nCaso que esta despues del periodo");
        Data despuesPeriodo = new Data(10, 3, 2025);
        System.out.println(act.estaActiva(despuesPeriodo));
    }

    private static void testToCSV(ActivitatPeriodica act){
        String aux = act.toCSV();
        System.out.println(aux);
    }
}