package extras;

import enumeracions.DiaSetmana;
import excepcions.ValorInexistent;

/**
 * Classe per guardar dates.
 *
 * @author Professores de programació.
 *
 */


public class Data {
    private int dia;
    private int mes;
    private int any;
    private int hora;
    private int minuts;


    //Esta fecha corresponde a un lunes dia 1, asi se pueden clacular en que dia de la semana cae cada fecha
    private static int diaGuia = 1;
    private static int mesGuia = 9;
    private static int anyGuia = 2025;


	/**
	 * Constructor sin hora, solo con el dia. La hora se guardara como 0 horas, 0 minutos
	 * @param dia
	 * @param mes
	 * @param any
	 */
	public Data(int dia, int mes, int any) throws ValorInexistent {
		if (esDataCorrecta(dia, mes, any)) { // ens asegurem que és una data valida
            this.dia = dia;
            this.mes = mes;
            this.any = any;
			hora = 0;
			minuts = 0;
        } else {
            throw new ValorInexistent("Data incorrecta");
        }
	}

    /**
     * Constructor que rep la data per paràmetre
     * Ha de validar que la data és correcta. Si rep una data incorrecta inicialitza la instància
     * amb la data de referència.
     * @param dia
     * @param mes
     * @param any
     */
    public Data(int dia, int mes, int any, int hora, int minuts) throws ValorInexistent {
        if (esDataCorrecta(dia, mes, any)) { // ens asegurem que és una data valida
            this.dia = dia;
            this.mes = mes;
            this.any = any;
        } else {
            throw new ValorInexistent("Data o hora incorrecta");
        }

        if (horaExisteix(hora, minuts)){
			this.hora = hora;
			this.minuts = minuts;
		}
    }


    /**
     * Getter
     * @return dia de la data
     */
    public int getDia() {
        return dia;
    }


    /**
     * Getter
     * @return mes de la data
     */
    public int getMes() {
        return mes;
    }


    /**
     * Getter
     * @return any de la data
     */
    public int getAny() {
        return any;
    }

    /**
     * Getter
     * @return hora de la data
     */
    public int getHora() {
        return hora;
    }

    /**
     * Getter
     * @return minutos de la hora de la data
     */
    public int getMinuts() {
        return minuts;
    }


    /**
     * Setter conjunt per a poder validar la correctesa de la data rebuda.
     * Només es fa la modificació de la data si el que es rep per paràmetre és correcte.
     * @param dia
     * @param mes
     * @param any
     */
    public void setData(int dia, int mes, int any) {
        if (esDataCorrecta(dia, mes, any)) { // ens asegurem que hi ha una data vàlida
            this.dia = dia;
            this.mes = mes;
            this.any = any;
        }
		else{
			//lanzar excepcion
		}
    }


	/**
     * Setter conjunt per a poder validar la correctesa de la hora rebuda.
     * Només es fa la modificació de l'hora si el que es rep per paràmetre és correcte.
     * @param hora
     * @param minutos
     */
    public void setHora(int hora, int minuts) {
        if (horaExisteix(hora, minuts)) { // ens asegurem que hi ha una data vàlida
            this.hora = hora;
			this.minuts = minuts;
        }
		else{
			//lanzar excepcion
		}
    }


    /**
     * Mètode que comprova si la data actual és la mateixa a la que es rep per paràmetre
     * @param data - valor de data a comparar amb l'actual
     * @return si són iguals
     */
    public boolean esIgual(Data data) {
        if (this.dia == data.getDia() && this.mes == data.getMes() && this.any == data.getAny()) {
            return true;
        }
        
        return false;
    }


    /**
     * Mètode que calcula i retorna una instància amb el valor del dia següent
     * * @return la data del dia seguent
     */
    public Data diaSeguent() {
        Data novaData = this.copia();

        novaData.dia++;
        if (novaData.dia > diesMes(novaData.mes, novaData.any)) {
            novaData.dia = 1;
            novaData.mes++;
            if (novaData.mes > 12) {
                novaData.mes = 1;
                novaData.any++;
            }
        }
        return novaData;
    }

   
    /**
     * FIXAR-SE AMB LA FORMA DIFERENT COM S'HA IMPLEMENTAT EL MÈTODE RESPECTE AL diaSeguent
     *
     * en l'anterior es crea una nova instància, en aquest es modifica el valor de la instància actual
     *
     * Mètode que calcula sobre la instància actual el valor del dia anterior
     */
    public void diaAnterior() {
        this.dia--;
        if (this.dia < 1) {
            this.mes--;
            if (this.mes < 1) {
                this.mes = 12;
                this.any--;
            }
            this.dia = diesMes(this.mes, this.any);
        }
    }

   
    /**
     * Mètode que calcula quants dies falten per arribar des de la data actual a la
     * que rebem per paràmetre. No considera la hora, solo el dia
     *
     * @param data - valor de data a la que calcular el número de dies que falten
     * @return si la data que es rep per paràmetre és superior o igual a la data de
     *         la instància sobre la que es crida el mètode es retorna el número de
     *         dies. Si la data que es rep per paràmetre és inferior es retorna -1.
     */
    public int numDiesAData(Data data) throws ValorInexistent { // compta el nombre de dies entre dos dates
        Data dataTemp;
        int contador;
        // és necessari una nova instancia per no modificar la data actual
        dataTemp = new Data(dia, mes, any, hora, minuts);
        if (dataTemp.esDataInferiorOigual(data)) {
            contador = 0;
            // la data rebuda és major que l'actual
            while (!dataTemp.esIgual(data)) {
                dataTemp=dataTemp.diaSeguent(); // augmenta la data actual fins coincidir amb la rebuda per paràmetre
                contador++;
            }
        } else
            contador = -1;
        return contador;
    }


    /**
     * Mètode per comprova si la data actual és inferior a la passada per paràmetre
     *
     * @param nova
     * @return
     */
    public boolean esDataInferiorOigual(Data nova) {
        boolean esInferior;
        if (any<nova.any) esInferior=true;
        else if (any>nova.any) esInferior=false;
        else {
            // cas anys iguals
            if (mes<nova.mes) esInferior=true;
            else if (mes>nova.mes) esInferior=false;
            else {
                // anys i mes iguals
                if (dia<=nova.dia) esInferior=true;
                else esInferior=false;
            }      
        }
        return esInferior;
    }

   
    /**
     * Mètode que comprova si la data actual correspon a un any de traspas
     * @return si és any de traspas
     */
    public boolean esDataAnyTraspas() {
        if (esAnyTraspas(this.any)) { // només amb l'any hi ha prou
            return true;
        } else {
            return false;
        }
    }


    /**
     * Mètode que transforma el contingut d'un objecte en una cadena de caracters per ser
     * mostrat per pantalla
     */
    @Override
    public String toString() {
        return("\tDATA => dia "+dia+" mes "+mes+" any "+any+" hora "+hora+":"+minuts);
    }


    /**
     * Mètode que crea un duplicat d'un objecte data
     * @return un nou objecte amb el mateix contingut
     */
    public Data copia() {
        try {
            return new Data(dia, mes, any, hora, minuts);
        } catch (ValorInexistent e) {
            // No hauria de passar mai
            return null;
        }
    }


	/**
	 * metodo que suma la cantidad de dias pasados por parametro a la fecha actual
	 * 
	 * @param dias
	 * @return nueva instancia con la fecha actual mas los dias pasados
	 */
	public Data dataPlusDies(int dias) {
		Data res = this.copia();
		
		for (int i = 0; i < dias; i++){
			res = res.diaSeguent();
		}

		return res;
	}


    // Mètodes de classe (STATIC).
    // no s'apliquen sobre el contingut d'una instància de data sinó sobre valors
    // que es reben per paràmetre.
    // són mètodes auxiliars i per això estan definits com a private


    private static boolean esDataCorrecta(int dia, int mes, int any) {
        boolean hoEs=true;
        if (dia < 1 || dia > 31) { // dia incorrecte
            hoEs= false;
        }
        if (mes < 1 || mes > 12) { // mes incorrecte
            hoEs= false;
        }
        if (dia > diesMes(mes, any)) { // dia del mes incorrecte
            hoEs= false;
        }
        return hoEs;
    }


    private static boolean esAnyTraspas(int any) { // ens estalviem crear una instancia de data
        if ((any % 4 == 0) && ((any % 100 != 0) || (any % 400 == 0))) {
            return true;
        } else {
            return false;
        }
    }


    private static int diesMes(int mes, int any) { // per saber quants dies te un mes d'un any
        int diesMes;
        if (mes == 2) {
            if (esAnyTraspas(any)) {
                diesMes = 29;
            } else {
                diesMes = 28;
            }
        } else {
            if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
                diesMes = 30;
            } else {
                diesMes = 31;
            }
        }
        return diesMes;
    }


    private static boolean horaExisteix (int hora, int minutos){
        boolean res = true;
		if ((hora >= 24) || (hora < 0)){
            //lanzar una excepcion cuando este creada
            res = false;
        }

		if ((minutos > 59) || (minutos < 0)){
			//lanzar excepcion cuando este creada
			res = false;
		}
        return res;
    }

    public boolean teHora() {
        return hora >= 0 && minuts >= 0;
    }

    /**
    * Comprova si aquesta data és estrictament anterior a una altra
    * @param altra data amb la qual es compara
     * @return true si aquesta data és anterior a altra
     */
    public boolean esDataInferior(Data altra) {

        if (this.any < altra.any) {
            return true;
        }

        if (this.any > altra.any) {
            return false;
        }

        if (this.mes < altra.mes) {
            return true;
        }
        
        if (this.mes > altra.mes) {
            return false;
        }

        if (this.dia < altra.dia) {
            return true;
        }

        if (this.dia > altra.dia) {
            return false;
        }

        // Si no hi ha hora, considerem que són iguals
        if (this.teHora() && altra.teHora()) {

            if (this.hora < altra.hora) {
                return true;
            }

            if (this.hora > altra.hora) {
                return false;
            }

            if (this.minuts < altra.minuts) {
                return true;
            }

            if (this.minuts > altra.minuts) {
                return false;
            }
        } return false; // són iguals o aquesta no és inferior
    
    }


    /**
     * Metodo para sumar una cantidad de tiempo a la hora, devuelve una nueva instancia con la hora cambiada
     * @param hora
     * @param min
     * @return nova data
     */
    public Data sumarTemps(int hora, int min){
        Data nova = this.copia();

        nova.minuts = this.minuts + min;
        if (nova.minuts >= 60){
            nova.minuts -= 60;
            nova.hora += 1;
        }

        nova.hora = this.hora + hora;
        if (nova.hora >= 24){
            nova.hora -= 24;
            nova.dia += 1;
        }

        return nova;
    }


    /**
     * Metodo que permite saber en que dia de la semana cae una fecha usando de referencia el lunes 1 de setiembre de 2025
     * Para fechas inferiores a la de referencia, el metodo devolvera null
     * @return dia de la semana correspondiente
     */
    public DiaSetmana diaSetmana(){
        try{
            Data guia = new Data(diaGuia, mesGuia, anyGuia);
            int op = guia.numDiesAData(this);
            DiaSetmana nom;

            op = op % 7;
            switch (op){
                case 0: nom = DiaSetmana.DILLUNS; break;
                case 1: nom = DiaSetmana.DIMARTS; break;
                case 2: nom = DiaSetmana.DIMECRES; break;
                case 3: nom = DiaSetmana.DIJOUS; break;
                case 4: nom = DiaSetmana.DIVENDRES; break;
                case 5: nom = DiaSetmana.DISSABTE; break;
                case 6: nom = DiaSetmana.DIUMENGE; break;
                default: nom = null;
            }

            return nom;

        }catch(ValorInexistent e){
            System.out.println("ERROR IESPERAT A DIASETMANA");
            return null;
        }
    }
}